package com.labelinsight.joltbuilderapi.controllers;

import com.labelinsight.joltbuilderapi.requests.TransformationRequest;
import com.labelinsight.joltbuilderapi.service.JoltService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/")
public class ClientController {

    @Autowired
    JoltService jolt;

    @Autowired
    public ClientController(JoltService jolt) {
        this.jolt = jolt;
    }

    // TODO: cut when rest of tool is built
    @GetMapping
    public String index() throws IOException {
        return "Hi";
    }

    @PostMapping("transform")
    public String transform(@RequestBody TransformationRequest request) {
        return jolt.transform(request.getInput(), request.getSpec());
    }
}
