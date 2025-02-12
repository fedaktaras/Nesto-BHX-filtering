package com.example.demo.service;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;

public class FilterReadingMprFileService implements FilterService {

    @Override
    public boolean isValid(File file, List<String> logger) {
        List<String> lines = null;
        boolean readSuccessful = false;
        try {
            lines = Files.readAllLines(file.toPath(), Charset.forName("Windows-1252"));
            readSuccessful = true; // Mark as successful if no exception is thrown
        } catch (IOException ignored) {

        }
        if (!readSuccessful) {
            try {
                lines = Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);
            } catch (IOException e) {
                String errorMessage = "Error reading file " + file.getName() + " with both UTF-8 and Windows-1252:";
                logger.add(errorMessage + "\n");
                return false; // File is considered invalid if it cannot be read with either encoding
            }
        }

        // Assuming lines were successfully read by this point
        String content = String.join(System.lineSeparator(), lines);
        if (content.contains("$")) {
            String logMessage = "File " + file.getName() + " contains contours";
            logger.add(logMessage + "\n");
            return false;
        }
        return true;
    }
}
