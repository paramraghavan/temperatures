package com.temperatures.helper;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static java.nio.file.StandardWatchEventKinds.*;

/**
 * Monitors a directory for new and updated files.
 */
public class DirectoryWatcher implements Iterator<String>, Iterable<String> {
    private final String fileSuffix;
    private boolean poll = true;
    private WatchService watchService = null;
    private String dirPath;
    public DirectoryWatcher(String dirPath, String fileSuffix) throws IOException {
        this.fileSuffix = fileSuffix;
        this.dirPath = dirPath;
        watchService = FileSystems.getDefault().newWatchService();
        Path path = Paths.get(dirPath);
//        path.register(watchService, ENTRY_CREATE, ENTRY_MODIFY, ENTRY_DELETE);
        path.register(watchService, ENTRY_CREATE, ENTRY_MODIFY);
    }

    List<String> itemList = new ArrayList<String>();

    @Override
    public Iterator<String> iterator() {
        return this;
    }

    @Override
    public boolean hasNext() {
        if(!itemList.isEmpty()) {
            return true;
        }
        long timeout = 100L;
        WatchKey key = null;
        try {
//            key = watchService.take();
            key =  watchService.poll(timeout, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        if ( key == null) {
            return false;
        }
        for (WatchEvent<?> event : key.pollEvents()) {
            String file = event.context().toString();
            if (file.endsWith(fileSuffix)) {
                itemList.add(dirPath + File.separator + event.context());
                System.out.println("Event kind : " + event.kind() + " - File : " + event.context());
            } else {
                System.out.println("Skipping Event kind : " + event.kind() + " - File : " + event.context());
            }
        }

        poll = key.reset();
        return poll;
    }

    @Override
    public String next() {
        return itemList.remove(0);
    }

    public static void main(String[] args) throws IOException,
            InterruptedException {
        String filePath = "/Users/praghavan/development/sparkpost-public/temperatures/src/";
        DirectoryWatcher fw = new DirectoryWatcher(filePath, ".csv");

        while(fw.hasNext()) {
            System.out.println(fw.next());
        }
    }
}
