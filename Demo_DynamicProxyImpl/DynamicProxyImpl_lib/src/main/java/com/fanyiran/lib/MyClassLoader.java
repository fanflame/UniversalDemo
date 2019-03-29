package com.fanyiran.lib;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;

import static com.fanyiran.lib.Constants.JAVA_PATH;

/**
 * Created by fanqiang on 2019/3/27.
 */
public class MyClassLoader extends ClassLoader {
    private File dirPath = new File(JAVA_PATH);

    @Override
    protected Class<?> findClass(String name) {
        File file = new File(dirPath, name);
        byte[] bytes = getFileBytes(file);
        return defineClass(name, bytes, 0, bytes.length);
    }

    private byte[] getFileBytes(File file) {
        if (!file.exists()) {
            throw new IllegalArgumentException("java file not exists");
        }
        try {
            FileInputStream inputStream = new FileInputStream(file);
            FileChannel channel = inputStream.getChannel();
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            WritableByteChannel writableByteChannel = Channels.newChannel(byteArrayOutputStream);
            while (channel.read(byteBuffer) != -1) {
                byteBuffer.flip();
                writableByteChannel.write(byteBuffer);
                byteBuffer.clear();
            }
            inputStream.close();
            return byteArrayOutputStream.toByteArray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public File getDirPath(){
        return dirPath;
    }
}
