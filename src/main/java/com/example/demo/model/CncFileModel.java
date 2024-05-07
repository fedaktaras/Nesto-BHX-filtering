package com.example.demo.model;

import java.util.List;
import lombok.Data;

@Data
public class CncFileModel {
    private String header;
    private String variablesBlock;
    private String dimensionSettings;
    private String commentBlock;
    List<Operable> operations;

}
