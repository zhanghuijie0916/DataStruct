package org.sunny.test;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.stream.Stream;

public class NIOtest {
    public static void main(String[] args){

        Path path = Paths.get("./","src","main","java","files","jet.txt");
        System.out.println(path.toAbsolutePath().getRoot());
        ByteBuffer buffer = ByteBuffer.allocate(10);
        FutureTask<Integer> futureTask = new FutureTask<Integer>(()->{
            Integer sum=0;
            for (int i=0;i<100;i++){
                sum+=i;
            }
            if (sum==4950){
                throw new RuntimeException("Callable可以抛出异常哦");
            }
            return sum;
        });

        new Thread(()->{
            int all = 1;
            for (int i=1;i<10;i++){
                all = all*i;
            }
            System.out.println(Thread.currentThread().getName()+" result="+all);
        },"Runnable类").start();

        try {
            Thread t = new Thread(futureTask,"线程1");
            t.start();
            System.out.println(t.getName()+"FutureTask是否完成："+futureTask.isDone()+futureTask.get());

        }
        catch (Exception e){
            e.printStackTrace();
        }

        /*try(FileChannel channel = FileChannel.open(path, StandardOpenOption.READ,StandardOpenOption.WRITE);) {
            Charset charset = Charset.forName("UTF-8");
            while (channel.read(buffer) != -1){
                buffer.flip();
                System.out.println(charset.decode(buffer));
                buffer.clear();

            }
            buffer.array()[0] = 'A';
            System.out.println(charset.decode(buffer));

            Path path1 = Files.walkFileTree(Paths.get("."), new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    System.out.println("正在访问：" + file);
                    if (file.startsWith("Sorting")) {
                        System.out.println("找到了");
                        return FileVisitResult.TERMINATE;
                    } else
                        return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                    System.out.println("正在访问文件：" + dir);
                    return FileVisitResult.CONTINUE;
                }
            });


            BasicFileAttributeView b = Files.getFileAttributeView(path, BasicFileAttributeView.class);
            BasicFileAttributes attrs = b.readAttributes();

            WatchService watchService = FileSystems.getDefault().newWatchService();
            Paths.get("").register(watchService,StandardWatchEventKinds.ENTRY_CREATE,
                    StandardWatchEventKinds.ENTRY_DELETE,StandardWatchEventKinds.ENTRY_MODIFY,
                    StandardWatchEventKinds.OVERFLOW);
            while (true){
                WatchKey key = watchService.take();
                for (WatchEvent event : key.pollEvents()){
                    WatchEvent.Kind kind= event.kind();
                    event.context();
                }
            }


        }
        catch (IOException|InterruptedException e){
            e.printStackTrace();
        }*/

    }
}
