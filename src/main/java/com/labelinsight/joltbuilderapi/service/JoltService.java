package com.labelinsight.joltbuilderapi.service;

import com.bazaarvoice.jolt.Chainr;
import com.bazaarvoice.jolt.JsonUtils;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JoltService {


    public JoltService() {
    }

    public String transform(String input, String specString) {
        Chainr chainr = Chainr.fromSpec(parseSpecString(specString));
        Object transformed = chainr.transform(parseInputString(input));
        return transformationToJson(transformed);
    }

    private Object parseInputString(String input) {
        return JsonUtils.jsonToObject(input);
    }

    private List<Object> parseSpecString(String specString) {
        return JsonUtils.jsonToList(specString);
    }

    private String transformationToJson(Object transformed) {
        return JsonUtils.toJsonString(transformed);
    }
}
