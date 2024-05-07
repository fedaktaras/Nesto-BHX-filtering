package com.example.demo.strategy;

import com.example.demo.model.Operable;
import com.example.demo.model.operations.CncDrillVertical;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class OperableStrategyTest {

    private static Operable operation;

    @Test
    void getOperationCncVerticalDrill_ok() {
        String block = """
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
        operation = OperableStrategy.getOperation(block);
        Assertions.assertInstanceOf(CncDrillVertical.class, operation);
    }

}