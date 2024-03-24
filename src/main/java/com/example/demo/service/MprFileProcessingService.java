package com.example.demo.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class MprFileProcessingService {

    private final Predicate<File> fileExtantionPredicate;
    private final FilterService filterService = new FilterReadingMprFileService();
    List<String> logger;

    public MprFileProcessingService(List<String> logger) {
        this.fileExtantionPredicate = file -> file.getName().toLowerCase().endsWith(".mpr");
        this.logger = logger;
    }

    public List<String> processFiles(String originalFolderPath, String newFolderPath) {
        File originalFolder = new File(originalFolderPath);
        if (originalFolder.exists() && originalFolder.isDirectory()) {
            try {
                processDirectory(originalFolder, originalFolderPath, newFolderPath);
            } catch (IOException e) {
                throw new RuntimeException("cant get list of files"); // Handle exceptions here, could log or notify the user instead
            }
        }
        return logger;
    }

    private void processDirectory(File directory, String originalBasePath, String newBasePath) throws IOException {
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    processDirectory(file, originalBasePath, newBasePath);
                } else {
                    processFile(file, originalBasePath, newBasePath);
                }
            }
        }
    }

    private void processFile(File file, String originalBasePath, String newBasePath) {
        Path sourcePath = file.toPath();
        String relativePath = sourcePath.toString().substring(originalBasePath.length());
        Path destinationPath = Paths.get(newBasePath + relativePath);

        if (fileExtantionPredicate.test(file)) {
            try {
                if (filterService.isValid(file, logger)) {
                    Files.createDirectories(destinationPath.getParent());
                    Files.copy(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);
                }
            } catch (IOException e) {
                logger.add("Can't process file - " + file.getName());
            }
        }
    }
}
