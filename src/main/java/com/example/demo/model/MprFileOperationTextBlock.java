package com.example.demo.model;

import java.util.HashMap;
import java.util.Map;
import lombok.Data;

@Data
public abstract class MprFileOperationTextBlock implements Operable {
    public String block;
    private Map<String, String> descriptionsMap;

    public MprFileOperationTextBlock(String block) {
        this.block = block;

        String[] lines = block.split("\\n");
        Map<String, String> descriptionsMap = new HashMap<>(lines.length);
        for (String line : lines) {
            if (line.contains("=")) {
                String[] parts = line.split("=");
                String key = parts[0].trim();
                String value = parts[1].replace("\"", "").trim();
                descriptionsMap.put(key, value);
            }
        }
        this.descriptionsMap = descriptionsMap;
    }
}
