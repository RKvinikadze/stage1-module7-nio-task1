package com.epam.mjc.nio;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.sql.SQLOutput;


public class FileReader {

    public Profile getDataFromFile(File file) {
        StringBuilder sb = new StringBuilder();
        try(RandomAccessFile aFile = new RandomAccessFile(file, "r");
            FileChannel inChannel = aFile.getChannel();) {

            long fileSize = inChannel.size();

            ByteBuffer buffer = ByteBuffer.allocate((int) fileSize);
            inChannel.read(buffer);
            buffer.flip();

            for (int i = 0; i < fileSize; i++) {
                sb.append((char) buffer.get());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (sb == null) return new Profile();

        String[] arr = sb.toString().split("\n");
        Profile profile = new Profile();

        profile.setName(arr[0].split("\\s+")[1]);
        profile.setAge(Integer.parseInt(arr[1].split("\\s+")[1]));
        profile.setEmail(arr[2].split("\\s+")[1]);
        profile.setPhone(Long.parseLong(arr[3].split("\\s+")[1]));

        return profile;
    }
}
