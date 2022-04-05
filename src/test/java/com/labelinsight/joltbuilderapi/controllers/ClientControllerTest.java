package com.labelinsight.joltbuilderapi.controllers;

import com.labelinsight.joltbuilderapi.requests.TransformationRequest;
import com.labelinsight.joltbuilderapi.service.JoltService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ClientControllerTest {

    @Mock
    private JoltService joltService;

    private ClientController controller;

    @BeforeEach
    private void setUp() {
        controller = new ClientController(joltService);
    }


    @Test
    void transform() {
        String input = "{\"test\": \"worked\"}";
        String spec = "[{\"operation\": \"shift\", \"spec\": {\"test\": \"result\"}}]";
        String expected = "{\"result\": \"worked\"}";

        TransformationRequest request = new TransformationRequest(input, spec);

        when(joltService.transform(input, spec)).thenReturn(expected);

        String actual = controller.transform(request);

        assertEquals(expected, actual);
    }

}

