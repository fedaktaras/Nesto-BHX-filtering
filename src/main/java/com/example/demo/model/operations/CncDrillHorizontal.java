package com.example.demo.model.operations;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CncDrillHorizontal extends CncDrill {

    public CncDrillHorizontal(String block) {
        super(block);
    }

    @Override
    public boolean isApplicableToBhx() {
        return false;
    }

    @Override
    public boolean isApplicableToVenture() {
        return false;
    }
}
