package de.codecentric.spring.additionalvalidation;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AdditionalValidationApplication.class)
@WebAppConfiguration
public class ValidationControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void shouldAcceptValidDataEn() throws Exception {
        this.mockMvc.perform(post("/validation")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .header(HttpHeaders.ACCEPT_LANGUAGE, "en-GB")
                .content("{" +
                        "\"someStringValue\":\"ab\"," +
                        "\"someIntValue\":2" +
                        "}"))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldAcceptValidDataDe() throws Exception {
        this.mockMvc.perform(post("/validation")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .header(HttpHeaders.ACCEPT_LANGUAGE, "de-DE")
                .content("{" +
                        "\"someStringValue\":\"abc\"," +
                        "\"someIntValue\":2" +
                        "}"))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldRejectInvalidNumber() throws Exception {
        this.mockMvc.perform(post("/validation")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("{" +
                        "\"someStringValue\":\"abc\"," +
                        "\"someIntValue\":0" +
                        "}"))
                .andExpect(status().isBadRequest());
    }

    /**
     * 141 characters are not allowed!
     */
    @Test
    public void shouldRejectInvalidStringEn() throws Exception {
        this.mockMvc.perform(post("/validation")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .header(HttpHeaders.ACCEPT_LANGUAGE, "en-GB")
                .content("{" +
                        "\"someStringValue\":\"MSsX0DHN2FKHCjkkyMuCnj9PNQ10wD0QmN863rQZOPZIXiJg2vvRMdC9AJDDXeX5AVnglYG2lkHtVv9L47gI40IBdKS4MrgPe13GaS0iKgZeukJJS3zcOHywx39QyjejadI46wSmhi8si\n\"," +
                        "\"someIntValue\":1" +
                        "}"))
                .andExpect(status().isBadRequest());
    }

    /**
     * Germans cannot write short sentences.
     * https://pbs.twimg.com/media/CaKX_r2UEAAf21U.jpg
     */
    @Test
    public void shouldAcceptLongerStringForDe() throws Exception {
        this.mockMvc.perform(post("/validation")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .header(HttpHeaders.ACCEPT_LANGUAGE, "de-DE")
                .content("{" +
                        "\"someStringValue\":\"MSsX0DHN2FKHCjkkyMuCnj9PNQ10wD0QmN863rQZOPZIXiJg2vvRMdC9AJDDXeX5AVnglYG2lkHtVv9L47gI40IBdKS4MrgPe13GaS0iKgZeukJJS3zcOHywx39QyjejadI46wSmhi8si\n\"," +
                        "\"someIntValue\":1" +
                        "}"))
                .andExpect(status().isBadRequest());
    }

}
