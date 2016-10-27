package com.beiing.baseframe.utils;

import android.os.Environment;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOError;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by chenliu on 2016/7/1.<br/>
 * 描述：
 * </br>
 */
public class FileUtil {

    /**
     * @param filepath
     *            文件路径
     * @return byte数组
     */
    public static byte[] getFileBytes(String filepath) {
        File file = new File(filepath);
        ByteBuffer bytebuffer = null;
        FileInputStream fileInputStream = null;
        FileChannel channel = null;
        try {
            if (file.exists()) {
                fileInputStream = new FileInputStream(file);
                channel = fileInputStream.getChannel();
                bytebuffer = ByteBuffer.allocate((int) channel.size());
                bytebuffer.clear();
                channel.read(bytebuffer);
                return bytebuffer.array();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                close(channel);
                close(fileInputStream);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 关闭流
     * @param stream
     */
    public static void close(Object stream){
        if(stream != null){
            try{
                if(stream instanceof InputStream){
                    ((InputStream) stream).close();
                } else if(stream instanceof OutputStream){
                    ((OutputStream) stream).close();
                } else if(stream instanceof Reader){
                    ((Reader) stream).close();
                } else if(stream instanceof Writer){
                    ((Writer) stream).close();
                } else if(stream instanceof HttpURLConnection){
                    ((HttpURLConnection) stream).disconnect();
                } else if(stream instanceof FileChannel){
                    ((FileChannel) stream) .close();
                }
            }catch(Exception e){

            }
        }
    }

    public static String  saveFile(byte[] bytes, String dirpath, String name) {
        String path = null;
        try {
            if (hasSdcard()) {
                File dir = new File(Environment.getExternalStorageDirectory().getPath() + dirpath);
                if (!dir.exists()) dir.mkdir();

                path = Environment.getExternalStorageDirectory().getPath() + dirpath + name;

                File file = new File(path);
                FileOutputStream fileout;
                fileout = new FileOutputStream(file);
                FileChannel fc = fileout.getChannel();
                fileout.write(bytes);
                fc.close();
                fileout.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return path;
    }


    /**删除文件**/
    public static void deleteFile(String path){
        File file = new File(path);
        if(file.exists())
            file.delete();
    }

    /**判断文件是否存在**/
    public static boolean isFileExists(String path){
        File file = new File(path);
        return file.exists();
    }


    /** 是否有内存卡 */
    public static boolean hasSdcard() {
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            return true;
        } else {
            return false;
        }
    }


}
