package com.labelinsight.joltbuilderapi.service;

import org.junit.jupiter.api.Test;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.*;

class JoltServiceTest {

    JoltService service = new JoltService();

    @Test
    void transformCachedInput_given_spec_transform_cached_input() throws IOException {
        String expected = "{\"SecondaryRatings\":{},\"Range\":5}";
        service.updateInputCache(getTestInput());

        String actual = service.transformCachedInput(getTestSpec());

        assertEquals(expected, actual);
    }

    // ! note we can cut this test once everything else is working. we're just using this as a sanity check
    @Test
    void transform_given_input_transform_per_test_spec() throws IOException {
        String expected = "{\"SecondaryRatings\":{},\"Range\":5}";

        String actual = service.transform(getTestInput());

        assertEquals(expected, actual);
    }

    @Test
    void transform_given_input_and_spec_transform() throws IOException {
        // * these will work
        String input = "{\"rating\": {\"primary\": {\"value\": 3}, \"quality\": {\"value\": 3}}}";
        String spec = "[{\"operation\": \"shift\", \"spec\": {\"rating\": {\"primary\": {\"value\": \"Rating\"}, \"*\": {\"value\": \"SecondaryRatings.&1.Value\", \"$\": \"SecondaryRatings.&.Id\"}}}}, {\"operation\": \"default\", \"spec\": {\"Range\" : 5, \"SecondaryRatings\" : {\"*\" : {\"Range\" : 5}}}}]";
        // ! but these will not even though pulling these out and testing them with the jolt online tester will give a result
        String input2 = "{ \"test\": { \"result\": \"passed\", \"tries\": 1 }}";
        String spec2 = "[{ \"operation\": \"shift\", \"spec\": { \"test\": { \"result\":  \"Result\"}} }]";

        String expected = "{\"SecondaryRatings\":{},\"Range\":5}";
        String expected2 = "{\"Result\": \"passed\"}";

        String actual = service.transform(input, spec);
        // ! we get null as a result here for some reason :|
        String actual2 = service.transform(input2, spec2);

        assertEquals(expected, actual);
        assertEquals(expected2, actual2);
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