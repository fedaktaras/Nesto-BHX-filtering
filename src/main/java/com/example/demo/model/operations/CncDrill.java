package com.example.demo.model.operations;

import com.example.demo.model.MprFileOperationTextBlock;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class CncDrill extends MprFileOperationTextBlock {

    private double diameter;
    private double depth;

    public CncDrill(String block) {
        super(block);
        diameter = Double.parseDouble(getDescriptionsMap().get("DU"));
        depth = Double.parseDouble(getDescriptionsMap().get("TI"));
    }
}
