package com.example.demo.model.operations;

import com.example.demo.model.MprFileOperationTextBlock;
import com.example.demo.model.Operable;

public class CncSawVertical extends MprFileOperationTextBlock implements Operable {
    public CncSawVertical(String block) {
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
