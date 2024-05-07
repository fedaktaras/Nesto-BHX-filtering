package com.example.demo.service;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import com.example.demo.model.MprFileAnalyzer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class MprFileAnalyzerTest {

    @Test
    void haveSawInX_ok() {
        String content = loadFileContent("src/test/resources/Regular_X_Saw_wop.mpr",
                Charset.forName("windows-1252"));

        MprFileAnalyzer mprFileAnalyzer = new MprFileAnalyzer(content);
        boolean result = mprFileAnalyzer.haveSawInXOnly();
        Assertions.assertTrue(result);

        content = loadFileContent("src/test/resources/Mirrored_X_Saw_wop.mpr",
                Charset.forName("windows-1252"));
        mprFileAnalyzer = new MprFileAnalyzer(content);
        result = mprFileAnalyzer.haveSawInXOnly();
        Assertions.assertTrue(result);

        content = loadFileContent("src/test/resources/Regular_X_saw_imos.mpr",
                Charset.forName("windows-1252"));
        mprFileAnalyzer = new MprFileAnalyzer(content);
        result = mprFileAnalyzer.haveSawInXOnly();
        Assertions.assertTrue(result);

        content = loadFileContent("src/test/resources/Regular_X_Saw_wop.mpr",
                Charset.forName("windows-1252"));
        mprFileAnalyzer = new MprFileAnalyzer(content);
        result = mprFileAnalyzer.haveSawInXOnly();
        Assertions.assertTrue(result);
    }

    @Test
    void haveSawInY_notOk() {
        String content = loadFileContent("src/test/resources/Regular_Y_Saw_imos.mpr",
                Charset.forName("windows-1252"));

        MprFileAnalyzer mprFileAnalyzer = new MprFileAnalyzer(content);
        boolean result = mprFileAnalyzer.haveSawInXOnly();
        Assertions.assertFalse(result);

        content = loadFileContent("src/test/resources/Mirrored_Y_Saw_imos.mpr",
                Charset.forName("windows-1252"));
        mprFileAnalyzer = new MprFileAnalyzer(content);
        result = mprFileAnalyzer.haveSawInXOnly();
        Assertions.assertFalse(result);
    }


    private String loadFileContent(String filePath, Charset charset) {
        try {
            byte[] bytes = Files.readAllBytes(Paths.get(filePath));
            return new String(bytes, charset);
        } catch (Exception e) {
            throw new RuntimeException("CANT READ FILE " + filePath + " TRY TO USE DIFFERENT " +
                    "CHARSET OR CHECK FILE");
        }
    }
}