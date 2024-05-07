package com.example.demo.strategy;

import com.example.demo.model.MprFileOperationTextBlock;
import com.example.demo.model.Operable;
import com.example.demo.model.operations.CncDrillHorizontal;
import com.example.demo.model.operations.CncDrillVertical;
import com.example.demo.model.operations.CncSawVertical;
import java.util.HashMap;
import java.util.Map;

public class OperableStrategy {
    public static Operable getOperation(String block) {
        MprFileOperationTextBlock operable = switch (block.split("\\n")[0]) {
            case "<102 \\BohrVert\\" -> new CncDrillVertical(block);
            case "<103 \\BohrHoriz\\" -> new CncDrillHorizontal(block);
            case "<109 \\Nuten\\" -> new CncSawVertical(block);
            default -> throw new RuntimeException();
        };
        operable.setDescriptionsMap(fillDescription(block));
        return operable;
    }

    private static Map<String, String> fillDescription(String block) {
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
        return descriptionsMap;
    }
}
