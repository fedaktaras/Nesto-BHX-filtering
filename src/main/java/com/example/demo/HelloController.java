package com.example.demo;

import com.example.demo.service.MprFileProcessingService;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.DragEvent;
import javafx.scene.input.TransferMode;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    private TextArea logText;

    @FXML
    protected void onProcessButtonClick() {
        List<String> logger = new ArrayList<>();
        String originalFolderPath = welcomeText.getText();
        String bhxFolder = createBHXFolder(originalFolderPath);
        MprFileProcessingService service = new MprFileProcessingService(logger);
        List<String> logs = service.processFiles(originalFolderPath, bhxFolder);
        welcomeText.setText("Files processed successfully " + logs.size() + " files filtered!");
        String logOutput = logs.stream().collect(Collectors.joining(System.lineSeparator()));
        logText.setText(logOutput);
    }


    @FXML
    private void handleDragOver(DragEvent event) {
        // Only accept drag-over events if they have files
        if (event.getGestureSource() != welcomeText && event.getDragboard().hasFiles()) {
            event.acceptTransferModes(TransferMode.ANY);
        }
        event.consume();
    }

    @FXML
    private void handleDragDropped(DragEvent event) {
        var db = event.getDragboard();
        AtomicBoolean success = new AtomicBoolean(false);
        if (db.hasFiles()) {
            // Process the first file, check if it's a directory
            db.getFiles().stream().findFirst().ifPresent(file -> {
                if (file.isDirectory()) {
                    // Update the label with the directory path
                    welcomeText.setText(file.getAbsolutePath());
                    success.set(true);
                }
            });
        }
        event.setDropCompleted(success.get());
        event.consume();
    }

    @FXML
    protected void onCreateFolderButtonClick() {

    }

    private String createBHXFolder(String originalFolderPath) {
        try {
            Path originalPath = Paths.get(originalFolderPath);
            Path parentPath = originalPath.getParent();
            String returnString = "";
            if (parentPath != null) {
                String newFolderName = "BHX " + originalPath.getFileName();
                Path newFolderPath = parentPath.resolve(newFolderName);
                Files.createDirectories(newFolderPath);
                returnString = newFolderPath.toString();
                System.out.println("Folder created: " + newFolderPath);
            } else {
                System.err.println("Error: Original folder path does not have a parent.");
            }
            return returnString;
        } catch (Exception e) {
            throw new RuntimeException("Cant create folder with BHX + " + originalFolderPath);
        }

    }
}