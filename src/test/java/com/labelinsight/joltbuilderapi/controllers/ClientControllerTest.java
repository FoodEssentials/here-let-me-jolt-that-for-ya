package com.labelinsight.joltbuilderapi.controllers;

import com.labelinsight.joltbuilderapi.service.JoltService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ClientControllerTest {

    @Mock
    private JoltService joltService;

    private ClientController controller ;

    @BeforeEach
    private void setUp() {
        controller = new ClientController(joltService);
    }

    @Test
    void upsertInput() {
        String input = "{\"test\": \"worked\"}";

        controller.upsertInput(input);

        verify(joltService).updateInputCache(input);
    }

    @Test
    void transformPerSpec() {
        String input = "{\"test\": \"worked\"}";
        String spec = "[{\"operation\": \"shift\", \"spec\": {\"test\": \"result\"}}]";
        String result = "{\"result\": \"worked\"}";
        when(joltService.transformCachedInput(spec)).thenReturn(result);

        String actual = controller.transformPerSpec(spec);

        assertEquals(result, actual);
    }

}

