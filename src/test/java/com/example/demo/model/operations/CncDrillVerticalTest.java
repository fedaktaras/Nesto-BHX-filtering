package com.example.demo.model.operations;

import com.example.demo.model.Operable;
import com.example.demo.strategy.OperableStrategy;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CncDrillVerticalTest {

    private static final String block = """
                <102 \\BohrVert\\
                XA="87"
                YA="288.4"
                ZA="t"
                TI="13"
                AB="0"
                BM="LS"
                DU="10"
                AN="1"
                MI="0"
                S_="1"
                WI="0"
                YVE="0"
                ORI=""
                KO="00"
                MNM="Drill"
                MX="0"
                MY="0"
                MZ="0"
                ??="_mirror
                """;

    private static CncDrillVertical cncDrillVertical;

    @BeforeAll
    static void beforeAll() {
        cncDrillVertical = (CncDrillVertical) OperableStrategy.getOperation(block);
    }

    @Test
    void getDrillDiameter_ok() {
        assertEquals(10.0, cncDrillVertical.getDiameter());
    }

    @Test
    void getDrillDepth_ok() {
        assertEquals(13.0, cncDrillVertical.getDepth());

    }

    @Test
    void isThroughDrillMode() {
        assertFalse(cncDrillVertical.isThroughDrillMode());
    }

    @Test
    void isApplicableToBhx() {
        assertFalse(cncDrillVertical.isApplicableToBhx());
    }

    @Test
    void isApplicableToVenture() {
        assertFalse(cncDrillVertical.isApplicableToVenture());
    }
}