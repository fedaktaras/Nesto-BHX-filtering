package com.example.demo.model;

import com.example.demo.utils.FileUtils;
import java.io.IOException;
import java.nio.charset.Charset;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CncParserTest {

    @Test
    void headerOfCncFileExists_ok() {
        String content;
        try {
            content = FileUtils.readFileToString("src/test/resources/Mirrored_X_Saw_wop.mpr",
                    Charset.forName("windows-1252"));
        } catch (IOException e) {
            throw new RuntimeException("Cant open file", e);
        }

        CncParser cncParser = new CncParser(content);
        CncFileModel cncFileModel = cncParser.getCncFileModel();
        boolean isEquals = cncFileModel.getHeader().equals(content.split("\\n\\s*\\n")[0]);
        Assertions.assertTrue(isEquals);
    }
}