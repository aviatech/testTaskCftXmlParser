package ru.maslenkin.testtaskcft.xmlparser.scanner;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;

public class FileScanner {
    private File folder;
    private ArrayList<File> findFiles = new ArrayList<File>();
    private ArrayList<File> parseFiles = new ArrayList<File>();

    public FileScanner(String folderPath) {
        folder = new File(folderPath);
        startParser();
    }

    private void startParser() {
        for (File findFile : folder.listFiles(new FileFilter() {
            public boolean accept(File pathname) {
                return pathname.getName().equals("src.xml");
            }
        })) {
            if (!findFiles.contains(findFile)) {
                findFiles.add(findFile);
            }
        }
    }

    public File getNext() {
        startParser();
        for (File fileForProcess : findFiles) {
            if (!parseFiles.contains(fileForProcess)) {
                parseFiles.add(fileForProcess);
                return fileForProcess;
            }
        }
        return null;
    }
}


