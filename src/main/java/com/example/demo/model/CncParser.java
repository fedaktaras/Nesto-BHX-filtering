package com.example.demo.model;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CncParser {
    private final List<String> blocks;
    private String fileName;

    public CncParser(String content) {
        this.blocks = Arrays.stream(content.split("\\n\\s*\\n"))
                .collect(Collectors.toList());
    }

    public CncFileModel getCncFileModel() {
        CncFileModel cncFileModel = new CncFileModel();

        cncFileModel.setHeader(getHeaderFromBlocks());
        cncFileModel.setVariablesBlock(getVariablesBlock());
        cncFileModel.setDimensionSettings(getDimensionBlock());
        cncFileModel.setCommentBlock(getCommentBlock());

        return cncFileModel;
    }

    private String getHeaderFromBlocks() {
        List<String> listOfBlocks = blocks.stream()
                .filter(s -> s.startsWith("[H"))
                .toList();
        if (listOfBlocks.size() != 1) {
            throw new RuntimeException("Can not find header of CNC file ot there is more then one" +
                    " in file" + fileName);
        }
        return listOfBlocks.getFirst();
    }

    private String getVariablesBlock() {
        List<String> listOfBlocks = blocks.stream()
                .filter(s -> s.startsWith("[001"))
                .toList();
        if (listOfBlocks.size() != 1) {
            throw new RuntimeException("Can not find variables of CNC file ot there is more then "
                    + "one in file " + fileName);
        }
        return listOfBlocks.getFirst();
    }

    private String getDimensionBlock() {
        List<String> listOfBlocks = blocks.stream()
                .filter(s -> s.startsWith("<100"))
                .toList();
        if (listOfBlocks.size() != 1) {
            throw new RuntimeException("Can not find dimensions of CNC file ot there is more then"
                    + "one in file " + fileName);
        }
        return listOfBlocks.getFirst();
    }

    private String getCommentBlock() {
        List<String> listOfBlocks = blocks.stream()
                .filter(s -> s.startsWith("<101"))
                .toList();
        if (listOfBlocks.size() != 1) {
            throw new RuntimeException("Can not find comment block of CNC file ot there is more "
                    + "then one in file " + fileName);
        }
        return listOfBlocks.getFirst();
    }
}
