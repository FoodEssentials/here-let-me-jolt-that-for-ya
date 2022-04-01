package com.labelinsight.joltbuilderapi.service;

import com.bazaarvoice.jolt.Chainr;
import com.bazaarvoice.jolt.JsonUtils;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JoltService {

    private String cachedInput;

    private List spec = JsonUtils.classpathToList("/specs/test-spec.json");

    public JoltService() {
    }
    public JoltService(String cachedInput) {
        this.cachedInput = cachedInput;
    }

    public String transform(String data){
        Chainr chainr = Chainr.fromSpec(spec);
        Object transformed = chainr.transform(data);
        return JsonUtils.toJsonString(transformed);
    }

    public String transformCachedInput(String specString) {
        List<Object> spec = JsonUtils.jsonToList(specString);
        Chainr chainr = Chainr.fromSpec(spec);
        Object transformed = chainr.transform(cachedInput);
        return JsonUtils.toJsonString(transformed);
    }

    public void updateInputCache(String input) {
        cachedInput = input;
    }

    public String transform(String input, String specString) {
        List<Object> spec = JsonUtils.jsonToList(specString);
        Chainr chainr = Chainr.fromSpec(spec);
        Object transformed = chainr.transform(input);
        return JsonUtils.toJsonString(transformed);
    }
}
