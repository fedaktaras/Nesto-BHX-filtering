package com.example.demo.model;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MprFileAnalyzer {
    List<String> blocks;

    public MprFileAnalyzer(String content) {
        this.blocks = Arrays.stream(content.split("\\n\\s*\\n")).toList();
    }

    public boolean haveSawInXOnly() {
        List<String> allSawsBlocks = getAllSawsBlocks();
        for(var b : allSawsBlocks) {
            if (!isSawInX(b)){
                return false;
            }
        }
        return true;
    }

    private List<String> getAllSawsBlocks() {
        return blocks.stream()
                .filter(s -> s.startsWith("<109 \\Nuten\\"))
                .toList();
    }

    private boolean isSawInX(String block) {
        Integer number1 = null;
        Integer number2 = null;
        List<String> list1 = Arrays.stream(block.split("\\n"))
                .filter(s -> s.startsWith("YA") || s.startsWith("YE"))
                .toList();
        Pattern pattern = Pattern.compile("\\d+");

        Matcher matcher1 = pattern.matcher(list1.get(0));
        if (matcher1.find()) {
            number1 = Integer.parseInt(matcher1.group());
        }

        Matcher matcher2 = pattern.matcher(list1.get(1));
        if (matcher2.find()) {
            number2 = Integer.parseInt(matcher2.group());
        }
        return number1.equals(number2);
    }

}
