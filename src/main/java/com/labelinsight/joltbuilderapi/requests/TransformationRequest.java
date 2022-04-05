package com.labelinsight.joltbuilderapi.requests;

public class TransformationRequest {

    private String input;
    private String spec;

    public TransformationRequest() {
    }

    public TransformationRequest(String input, String spec) {
        this.input = input;
        this.spec = spec;
    }

    public String getInput() {
        return input;
    }


    public String getSpec() {
        return spec;
    }


}
