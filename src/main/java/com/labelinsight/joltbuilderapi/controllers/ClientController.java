package com.labelinsight.joltbuilderapi.controllers;

import com.bazaarvoice.jolt.JsonUtils;
import com.labelinsight.joltbuilderapi.service.JoltService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.nio.file.Files;
import java.util.List;

@RestController
@RequestMapping("/")
public class ClientController {

    JoltService jolt;

    @Autowired
    public ClientController(JoltService jolt) {
        this.jolt = jolt;
    }

    // TODO: cut when rest of tool is built
    @GetMapping
    public String index() throws IOException {
        String data = readInTestFile();
        return jolt.transform(data.toString());
    }

    @PostMapping("input")
    public void upsertInput(@RequestBody String input) {
        jolt.updateInputCache(input);
    }

    @PostMapping
    public String transformPerSpec(@RequestBody String spec) {
        return jolt.transformCachedInput(spec);
    }

    private String readInTestFile() throws IOException {
        File file = ResourceUtils.getFile("classpath:specs/test-input.json");
        List lines = Files.readAllLines(file.toPath());
        return String.join("", lines);
    }
}
