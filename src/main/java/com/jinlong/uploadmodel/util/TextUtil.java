package com.jinlong.uploadmodel.util;

import java.io.File;

/**
 * @Author gzy
 * @Date 2021/1/5 11:31
 * @Version 1.0
 */

public class TextUtil {
    public static void deleteFolder(File folder){
        File[] files = folder.listFiles();
        if(files!=null){
            for(File f : files){
                if(f.isDirectory()){
                    deleteFolder(f);
                }else{
                    f.delete();
                }
            }
        }
        folder.delete();
    }
}
