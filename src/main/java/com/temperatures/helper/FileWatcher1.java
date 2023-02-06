package com.temperatures.helper;

import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_DELETE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;

import java.io.IOException;
import java.nio.file.*;

public class FileWatcher1 {


    public static void main(String[] args) throws IOException,
            InterruptedException {
        String filePath = "/Users/praghavan/development/sparkpost-public/temperatures/src/";
        WatchService watchService = FileSystems.getDefault().newWatchService();
        Path path = Paths.get(filePath);
        path.register(watchService, ENTRY_CREATE, ENTRY_MODIFY, ENTRY_DELETE);
        boolean poll = true;
        while (poll) {
            WatchKey key = watchService.take();
            for (WatchEvent<?> event : key.pollEvents()) {
                System.out.println("Event kind : " + event.kind() + " - File : " + event.context());
            }

            poll = key.reset();
        }

    }
}