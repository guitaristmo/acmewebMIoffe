package com.acme.statusmgr;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class SimpleServerStatusTests
{
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void simpleServerStatusTest() throws Exception
    {
        this.mockMvc.perform(get("/server/status/detailed")
                .param("details", "memory")
                .param("name", "Noach")
                .param("levelofdetail", "simple"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.contentHeader").doesNotExist())
                .andExpect(jsonPath("id").doesNotExist())
                .andExpect(jsonPath("name").doesNotExist())
                .andExpect(jsonPath("$.statusDesc").value("Server is up, and its memory is Running low"));
    }

    @Test
    public void complexServerStatusTest() throws Exception
    {
        this.mockMvc.perform(get("/server/status/detailed")
                .param("details", "memory")
                .param("levelofdetail", "complex"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.contentHeader").exists())
                .andExpect(jsonPath("id").exists())
                .andExpect(jsonPath("$.statusDesc").value("Server is up, and its memory is Running low"));
    }

    @Test
    public void defaultServerStatusIsComplexTest() throws Exception
    {
        this.mockMvc.perform(get("/server/status/detailed")
                .param("details", "memory"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.contentHeader").exists())
                .andExpect(jsonPath("id").exists())
                .andExpect(jsonPath("$.statusDesc").value("Server is up, and its memory is Running low"));
    }

    @Test
    public void invalidLevelOfDetailParameter() throws Exception
    {
        this.mockMvc.perform(get("/server/status/detailed")
                .param("levelofdetail", "junkERROR")).andExpect(status().is4xxClientError());
    }
}
