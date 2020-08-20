package cn.fudan.security.appcrawler.provider.crawler;

import cn.fudan.security.appcrawler.provider.crawler.downloader.Downloader;
import cn.fudan.security.appcrawler.provider.crawler.downloader.HttpClientDownloader;
import cn.fudan.security.appcrawler.provider.crawler.pipeline.CollectorPipeline;
import cn.fudan.security.appcrawler.provider.crawler.pipeline.ConsolePipeline;
import cn.fudan.security.appcrawler.provider.crawler.pipeline.Pipeline;
import cn.fudan.security.appcrawler.provider.crawler.pipeline.ResultItemsCollectorPipeline;
import cn.fudan.security.appcrawler.provider.crawler.processor.PageProcessor;
import cn.fudan.security.appcrawler.provider.crawler.proxy.Proxy;
import cn.fudan.security.appcrawler.provider.crawler.proxy.SimpleProxyProvider;
import cn.fudan.security.appcrawler.provider.crawler.scheduler.QueneScheduler;
import cn.fudan.security.appcrawler.provider.crawler.scheduler.Scheduler;
import cn.fudan.security.appcrawler.provider.crawler.thread.CountableThreadPool;
import cn.fudan.security.appcrawler.provider.crawler.utils.UrlUtils;
import cn.fudan.security.appcrawler.provider.crawler.utils.WMCollections;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.SerializationUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.io.Closeable;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author qiaoying
 * @date 2018/11/15 19:32
 */
public class Crawler implements Runnable, Task{

    protected Logger logger = LoggerFactory.getLogger(getClass());

    protected Downloader downloader;

    protected List<Pipeline> pipelineList = new ArrayList<>();

    protected PageProcessor pageProcessor;

    protected List<Request> startRequests;

    protected Site site;

    protected String crawlerId;

    protected Scheduler scheduler = new QueneScheduler();

    protected CountableThreadPool threadPool;

    protected ExecutorService executorService;

    protected int threadNum = 1;

    protected final static int SATA_INIT = 0;

    protected final static int STAT_RUNNING = 1;

    protected final static int STAT_STOPPED = 2;

    protected AtomicInteger stat = new AtomicInteger(SATA_INIT);

    protected boolean exitWhenComplete = true;

    protected boolean spawnUrl = true;

    protected boolean destroyWhenExit = true;

    private ReentrantLock newUrlLock = new ReentrantLock();

    private Condition newUrlCondition = newUrlLock.newCondition();

    private List<CrawlerListener> crawlerListeners;

    private final AtomicLong pageCount = new AtomicLong(0);

    private Date startDate;

    private int emptySleepTime = 30000;

    public Crawler(){

    }

    public Crawler(PageProcessor pageProcessor){
        this.pageProcessor = pageProcessor;
        this.site = pageProcessor.getSite();
    }

    public Crawler setTaskId(int taskId){
        this.crawlerId = String.valueOf(taskId);
        return this;
    }

    public Task toTask(){
        return new Task(){
            @Override
            public String getCrawlerId() {
                String id = String.valueOf(crawlerId);
                if (id.isEmpty()){
                    id = UUID.randomUUID().toString();
                }
                return id;
            }

            @Override
            public Site getSite() {
                return site;
            }
        };
    }

    protected void checkIfRunning(){
        if (stat.get() == STAT_RUNNING){
            throw new IllegalStateException("Crawler is already running");
        }
    }


    public static Crawler createWithTask(PageProcessor pageProcessor){
        return new Crawler(pageProcessor);
    }



    public Crawler startUrls(List<String> startUrls){
        checkIfRunning();
        this.startRequests = UrlUtils.convertToRequests(startUrls);
        return this;
    }

    public Crawler startRequest(List<Request> startRequests) {
        checkIfRunning();
        this.startRequests = startRequests;
        return this;
    }

    public Crawler setScheduler(Scheduler scheduler){
        checkIfRunning();
        Scheduler oldScheduler = this.scheduler;
        this.scheduler = scheduler;
        if (oldScheduler != null){
            Request request;
            while ((request = oldScheduler.poll(this)) != null){
                this.scheduler.push(request, this);
            }
        }
        return this;
    }

    public Crawler scheduler(Scheduler scheduler){
        return setScheduler(scheduler);
    }

    public Crawler addPipeline(Pipeline pipeline){
        checkIfRunning();
        this.pipelineList.add(pipeline);
        return this;
    }

    public Crawler pipline(Pipeline pipeline){
        return addPipeline(pipeline);
    }

    public Crawler clearPipeline() {
        pipelineList = new ArrayList<Pipeline>();
        return this;
    }

    public Crawler setDownloader(Downloader downloader) {
        checkIfRunning();
        this.downloader = downloader;
        return this;
    }

    public Crawler downloader(Downloader downloader) {
        return setDownloader(downloader);
    }

    private void addRequest(Request request) {
        if (site.getDomain() == null && request != null && request.getUrl() != null) {
            site.setDomain(UrlUtils.getDomain(request.getUrl()));
        }
        scheduler.push(request, this);
    }

    public Crawler thread(int threadNum){
        checkIfRunning();
        this.threadNum = threadNum;
        if (threadNum <= 0){
            throw new IllegalArgumentException("threadNum should be more than one!");
        }
        return this;
    }

    /**
     * 初始化crawler的组件
     */
    protected void initComponent(){
        if (downloader == null){
            this.downloader = new HttpClientDownloader();
            ((HttpClientDownloader) this.downloader).setProxyProvider(SimpleProxyProvider.from(new Proxy("127.0.0.1",1080)));
        }
        if (pipelineList.isEmpty()){
            pipelineList.add(new ConsolePipeline());
        }
        downloader.setThread(threadNum);
        if (threadPool == null || threadPool.isShutdown()){
            if (executorService != null && !executorService.isShutdown()){
                threadPool = new CountableThreadPool(threadNum, executorService);
            } else {
                threadPool = new CountableThreadPool(threadNum);
            }
        }

        if (startRequests != null){
            for (Request request : startRequests){
                addRequest(request);
            }
            startRequests.clear();
        }
        startDate = new Date();
    }

    private void checkRunningStat() {
        while (true) {
            int statNow = stat.get();
            if (statNow == STAT_RUNNING) {
                throw new IllegalStateException("Spider is already running!");
            }
            if (stat.compareAndSet(statNow, STAT_RUNNING)) {
                break;
            }
        }
    }

    private void processRequest(Request request){
        Page page = downloader.download(request,this);
        if (page.isDownloadSuccess()){
            onDownloadSuccess(request,page);
        }else {
            onDownloaderFail(request);
        }
    }

    private void onDownloadSuccess(Request request, Page page){
        if (site.getAcceptStatusCode().contains(page.getStatusCode())){
            pageProcessor.process(page);
            extractAndAddRequests(page,spawnUrl);
            if (!page.getResultItems().isSkip()){
                for (Pipeline pipeline : pipelineList){
                    pipeline.process(page.getResultItems(),this);
                }
            }
        } else {
          logger.info("page status code error, page {} , code: {}", request.getUrl(), page.getStatusCode());
        }
        sleep(site.getRetrySleepTime());
    }

    private void onDownloaderFail(Request request) {
        if (site.getCycleRetryTimes() == 0) {
            sleep(site.getSleepTime());
        } else {
            // for cycle retry
            doCycleRetry(request);
        }
    }

    private void doCycleRetry(Request request) {
        Object cycleTriedTimesObject = request.getExtra(Request.CYCLE_TRIED_TIMES);
        if (cycleTriedTimesObject == null) {
            addRequest(SerializationUtils.clone(request).setPriority(0).putExtra(Request.CYCLE_TRIED_TIMES, 1));
        } else {
            int cycleTriedTimes = (Integer) cycleTriedTimesObject;
            cycleTriedTimes++;
            if (cycleTriedTimes < site.getCycleRetryTimes()) {
                addRequest(SerializationUtils.clone(request).setPriority(0).putExtra(Request.CYCLE_TRIED_TIMES, cycleTriedTimes));
            }
        }
        sleep(site.getRetrySleepTime());
    }

    protected void extractAndAddRequests(Page page, boolean spawnUrl){
        if (spawnUrl && CollectionUtils.isNotEmpty(page.getTargetRequests())){
            for (Request request : page.getTargetRequests()){
                addRequest(request);
            }
        }
    }

    protected void sleep(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            logger.error("Thread interrupted when sleep",e);
        }
    }


    @Override
    public String getCrawlerId() {
        if (crawlerId != null) {
            return crawlerId;
        }
        if (site != null) {
            return site.getDomain();
        }
        crawlerId = UUID.randomUUID().toString();
        return crawlerId;
    }

    @Override
    public Site getSite() {
        return site;
    }

    @Override
    public void run() {
        checkRunningStat();
        initComponent();
        logger.info("Crawler {} started!",getCrawlerId());
        while (!Thread.currentThread().isInterrupted() && stat.get() == STAT_RUNNING){
            final Request request = scheduler.poll(this);
            if (request == null){
                if (threadPool.getThreadAlive() == 0 && exitWhenComplete){
                    break;
                }
                waitNewUrl();
            } else {
                threadPool.execute(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            processRequest(request);
                            onSuccess(request);
                        } catch (Exception e){
                            onError(request);
                            logger.info("process request " + request + " error", e);
                        } finally {
                            pageCount.incrementAndGet();
                            signalNewUrl();
                        }

                    }
                });
            }
        }

        stat.set(STAT_STOPPED);
        if (destroyWhenExit){
            close();
        }

        logger.info("Crawler {} closed! {} pages downloaded.", getCrawlerId(), pageCount.get());
    }

    protected void onSuccess(Request request){
        if (CollectionUtils.isNotEmpty(crawlerListeners)){
            for (CrawlerListener crawlerListener : crawlerListeners){
                crawlerListener.onSuccess(request);
            }
        }
    }

    protected void onError(Request request){
        if (CollectionUtils.isNotEmpty(crawlerListeners)){
            for (CrawlerListener crawlerListener : crawlerListeners){
                crawlerListener.onError(request);
            }
        }
    }

    private void waitNewUrl() {
        newUrlLock.lock();
        try {
            //double check
            if (threadPool.getThreadAlive() == 0 && exitWhenComplete) {
                return;
            }
            newUrlCondition.await(emptySleepTime, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            logger.warn("waitNewUrl - interrupted, error {}", e);
        } finally {
            newUrlLock.unlock();
        }
    }

    private void signalNewUrl() {
        try {
            newUrlLock.lock();
            newUrlCondition.signalAll();
        } finally {
            newUrlLock.unlock();
        }
    }

    public void close() {
        destroyEach(downloader);
        destroyEach(pageProcessor);
        destroyEach(scheduler);
        for (Pipeline pipeline : pipelineList) {
            destroyEach(pipeline);
        }
        threadPool.shutdown();
    }

    private void destroyEach(Object object) {
        if (object instanceof Closeable) {
            try {
                ((Closeable) object).close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public Crawler addUrl(String... urls){
        for (String url : urls){
            addRequest(new Request(url));
        }
        signalNewUrl();
        return this;
    }

    public <T> List<T> getAll(Collection<String> urls) {
        destroyWhenExit = false;
        spawnUrl = false;
        if (startRequests!=null){
            startRequests.clear();
        }
        for (Request request : UrlUtils.convertToRequests(urls)) {
            addRequest(request);
        }
        CollectorPipeline collectorPipeline = getCollectorPipeline();
        pipelineList.add(collectorPipeline);
        run();
        spawnUrl = true;
        destroyWhenExit = true;
        return collectorPipeline.getCollected();
    }

    protected CollectorPipeline getCollectorPipeline() {
        return new ResultItemsCollectorPipeline();
    }

    public <T> T get(String url) {
        List<String> urls = WMCollections.newArrayList(url);
        List<T> resultItemses = getAll(urls);
        if (resultItemses != null && resultItemses.size() > 0) {
            return resultItemses.get(0);
        } else {
            return null;
        }
    }

    public void start(){
        runAsync();
    }

    public void runAsync(){
        Thread thread = new Thread(this);
        thread.setDaemon(false);
        thread.start();
    }


}
