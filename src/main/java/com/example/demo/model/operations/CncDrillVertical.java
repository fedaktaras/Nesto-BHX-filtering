package com.example.demo.model.operations;

import com.example.demo.model.Operable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CncDrillVertical extends CncDrill implements Operable {
    private String drillMode;
    public CncDrillVertical(String block) {
        super(block);
        this.drillMode = getDescriptionsMap().get("BM");
    }

    public boolean isThroughDrillMode() {
        return drillMode.equals("LSL") || drillMode.equals("SSS");
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
