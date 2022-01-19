//package com.ssafy.api.controller;
//
//import com.fasterxml.jackson.core.type.TypeReference;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.apache.http.HttpHeaders;
//import org.junit.Test;
//import org.junit.jupiter.api.Order;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.restdocs.payload.JsonFieldType;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.util.LinkedMultiValueMap;
//import org.springframework.util.MultiValueMap;
//
//
//import java.util.Map;
//
//import static org.springframework.restdocs.headers.HeaderDocumentation.*;
//import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
//import static org.springframework.restdocs.payload.PayloadDocumentation.*;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//
//@AutoConfigureRestDocs
//@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
//@AutoConfigureMockMvc
//public class AuthControllerTest {
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//
//
//    @Test
//    @Order(1)
//    public void sendEmail_test() throws Exception {
//        AuthEmailSendReqDTO dto = AuthEmailSendReqDTO.builder().email("test@test.com").build();
//
//        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
//        Map<String, String> map = objectMapper.convertValue(dto, new TypeReference<Map<String, String>>() {});
//        params.setAll(map);
//        // ============================================================================================================================================
//
//        this.mockMvc.perform(post("/api/auth/send/email")
//                        .contentType(MediaType.APPLICATION_JSON_VALUE)
//                        .accept(MediaType.APPLICATION_JSON_VALUE)
//                        .characterEncoding("UTF-8")
//                        .content(objectMapper.writeValueAsString(dto))
//                        .params(params)
//                )
//                .andDo(print())
//                .andDo(document("auth/sendEmail",
//                        requestHeaders(
//                                headerWithName(HttpHeaders.ACCEPT).description(MediaType.APPLICATION_JSON_VALUE)
//                        ),
//                        requestFields(
//                                fieldWithPath("email").type(JsonFieldType.STRING).description("이메일")
//                        ),
//                        responseFields(
//                                fieldWithPath("output").type(JsonFieldType.NUMBER).description("결과코드"),
//                                fieldWithPath("msg").type(JsonFieldType.STRING).description("결과 메세지")
//                        ))
//                )
//                .andExpect(jsonPath("output").value(1))
//        ;
//    }
//
//
//
//    @Test
//    @Order(2)
//    public void checkEmail_test() throws Exception {
//        AuthEmailCheckReqDTO dto = AuthEmailCheckReqDTO.builder().authNum("999999").email("test@test.com").build();
//
//        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
//        Map<String, String> map = objectMapper.convertValue(dto, new TypeReference<Map<String, String>>() {});
//        params.setAll(map);
//        // ============================================================================================================================================
//
//        this.mockMvc.perform(get("/api/auth/check/email")
//                        .contentType(MediaType.APPLICATION_JSON_VALUE)
//                        .accept(MediaType.APPLICATION_JSON_VALUE)
//                        .characterEncoding("UTF-8")
//                        .content(objectMapper.writeValueAsString(dto))
//                        .params(params)
//                )
//                .andDo(print())
//                .andDo(document("auth/checkEmail",
//                        requestHeaders(
//                                headerWithName(HttpHeaders.ACCEPT).description(MediaType.APPLICATION_JSON_VALUE)
//                        ),
//                        requestFields(
//                                fieldWithPath("authNum").type(JsonFieldType.STRING).description("인증코드"),
//                                fieldWithPath("email").type(JsonFieldType.STRING).description("이메일")
//                        ),
//                        responseFields(
//                                fieldWithPath("output").type(JsonFieldType.NUMBER).description("결과코드"),
//                                fieldWithPath("msg").type(JsonFieldType.STRING).description("결과 메세지")
//                        ))
//                )
//                .andExpect(jsonPath("output").value(1))
//        ;
//    }
//}
