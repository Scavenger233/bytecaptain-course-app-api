package com.bytecaptain.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = BasicAuthenticationController.class)
@AutoConfigureMockMvc(addFilters = false)
@ActiveProfiles("test")
class BasicAuthenticationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getCourse() throws Exception {

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/basicauth");

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        JSONAssert.assertEquals("{\"message\":\"You are authenticated\"}", result.getResponse().getContentAsString(), false);

    }

}
