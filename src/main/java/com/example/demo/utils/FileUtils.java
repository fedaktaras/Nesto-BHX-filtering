package com.example.demo.utils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public final class FileUtils {

    // Prevent instantiation of utility class
    private FileUtils() {}

    public static String readFileToString(String path, Charset charset) throws IOException {
        Path filePath = Paths.get(path);
        return Files.readString(filePath, charset);
    }

    public static String readFileToString(String path) throws IOException {
        // Default to UTF-8 if charset is not specified
        return readFileToString(path, Charset.forName("UTF-8"));
    }
}