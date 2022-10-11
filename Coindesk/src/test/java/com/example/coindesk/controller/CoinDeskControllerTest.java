package com.example.coindesk.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest
public class CoinDeskControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void delete() throws Exception {
        String jsonStr = "{ \"code\": \"TWD\",\"chartName\": \"TWD\", \"codeNameZh\" : \"新台幣\"}";
        this.mockMvc.perform(
                        post("/api/v2/delete")
                                .content(jsonStr.getBytes())
                                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk());
    }
    @Test
    public void update() throws Exception {
        String jsonStr = "{ \"code\": \"TWD\",\"chartName\": \"TWD\", \"codeNameZh\" : \"新台幣\"}";
        this.mockMvc.perform(
                        post("/api/v2/update")
                                .content(jsonStr.getBytes())
                                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk());
    }
    @Test
    public void add() throws Exception {
        String jsonStr = "{ \"code\": \"TWD\",\"chartName\": \"TWD\", \"codeNameZh\" : \"台幣\"}";
        this.mockMvc.perform(
                        post("/api/v2/add")
                                .content(jsonStr.getBytes())
                                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk());
    }
    @Test
    public void getList() throws Exception {
        this.mockMvc.perform(
                        post("/api/v2/getList")
                                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk());
    }
}