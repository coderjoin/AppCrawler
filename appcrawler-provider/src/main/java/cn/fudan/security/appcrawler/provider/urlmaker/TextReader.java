package cn.fudan.security.appcrawler.provider.urlmaker;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author qiaoying
 * @date 2018/11/16 14:44
 */
public class TextReader {
    public static ArrayList<String> list = new ArrayList<>();
    public static String fileName = "./resources/res.txt/";
    public static ArrayList readFileByLines(String fileName) {
        File file = new File(fileName);
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            int line = 1;

            while ((tempString = reader.readLine()) != null) {
                list.add(tempString);
                //System.out.println("line " + line + ": " + tempString);
                line++;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
        System.out.println(list.size());

        return list;

    }
    public static void main(String[] args){

        String relativelyPath=System.getProperty("user.dir");

        System.out.println(relativelyPath);
        System.out.println(TextReader.class.getClassLoader());
        TextReader.readFileByLines(fileName);
    }
}
