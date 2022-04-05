package com.labelinsight.joltbuilderapi.service;

import org.junit.jupiter.api.Test;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.assertEquals;

class JoltServiceTest {

    JoltService service = new JoltService();


    @Test
    void transform_given_input_and_spec_transform() throws IOException {
        String input2 = "{ \"test\": { \"result\": \"passed\", \"tries\": 1 }}";
        String spec2 = "[{ \"operation\": \"shift\", \"spec\": { \"test\": { \"result\":  \"Result\"}} }]";

        String expected = "{\"Rating\":3,\"SecondaryRatings\":{\"quality\":{\"Id\":\"quality\",\"Value\":3,\"Range\":5}},\"Range\":5}";

        String actual = service.transform(getTestInput(), getTestSpec());

        assertEquals(expected, actual);
    }

    private String getTestInput() throws IOException {
        File inputFile = ResourceUtils.getFile("classpath:specs/test-input.json");
        return String.join("", Files.readAllLines(inputFile.toPath()));
    }

    private String getTestSpec() throws IOException {
        File inputFile = ResourceUtils.getFile("classpath:specs/test-spec.json");
        return String.join("", Files.readAllLines(inputFile.toPath()));
    }

}