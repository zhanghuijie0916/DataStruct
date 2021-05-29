package org.sunny.interview.important;

import java.io.*;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * 字符串的反转
 */
public class Alibaba2 {
    public static String reverse(int x){
        String s = String.valueOf(x);
        if (s==null | s.length()<=1){
            return s;
        }
        else
            return reverse(Integer.valueOf(s.substring(1)))+s.charAt(0);
    }

    /**
     * 拷贝文件副本
     * @param source
     * @param target
     * @throws IOException
     */
    public static void copyFile(String source,String target) throws IOException{
        try (FileInputStream in = new FileInputStream(source); FileOutputStream out = new FileOutputStream(target)){
            FileChannel inChannel = in.getChannel();
            FileChannel outChannel = out.getChannel();
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            while (inChannel.read(buffer) != -1){
                buffer.flip();
                outChannel.write(buffer);
                buffer.clear();
            }
        }
    }

    /**
     *
     * @param source
     * @return
     * @throws IOException
     */
    public static int wordCount(String source,String finder) throws IOException{
        Pattern pattern = Pattern.compile("(apache)");
        try(FileInputStream in = new FileInputStream(source)) {
            FileChannel channel = in.getChannel();
            ByteBuffer buffer = ByteBuffer.allocateDirect(10);
            Charset charset = StandardCharsets.UTF_8;
            Matcher matcher = null;
            int count = 0;
            while (channel.read(buffer)!=-1){
                buffer.flip();
                System.out.println(charset.decode(buffer).toString());
                String str = charset.decode(buffer).toString();

                matcher = pattern.matcher(str);
                //System.out.println(matcher.find());

                while (matcher.find()){
                    count++;
                }
                buffer.clear();

            }
            return count;
        }

    }

    public static void main(String[] args) throws IOException{
        /*copyFile("/Users/meng/林夕/阿里的演变史.pdf","/Users/meng/林夕/阿里的演变史_副本.pdf");

        String result = reverse(13424);
        System.out.println(result);
        */

        int count = wordCount("/Users/meng/林夕/README.txt","maven");
        System.out.println(count);

    }
}
