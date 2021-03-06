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
//import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
//import org.springframework.restdocs.payload.JsonFieldType;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.context.web.WebAppConfiguration;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.util.LinkedMultiValueMap;
//import org.springframework.util.MultiValueMap;
//
//import java.util.Map;
//
//import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
//import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
//import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
//import static org.springframework.restdocs.payload.PayloadDocumentation.*;
//import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
//import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
//import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//
//@WebAppConfiguration
//@AutoConfigureRestDocs
//@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
//@AutoConfigureMockMvc
//public class UserControllerTest {
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    String auth_token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxIiwicm9sZXMiOlsiUk9MRV9VU0VSIl0sInVzZXJQayI6IjEiLCJpYXQiOjE2Mzk0NTc0NTIsImV4cCI6MTY0MjA0OTQ1Mn0.afQNA4zfLFMzSXuLG75SPSopDPtkg97Kh9TBptaJDYI";
//
//
//    @Test
//    @Order(1)
//    public void myClass_test() throws Exception {
//        // ============================================================================================================================================
//        this.mockMvc.perform(get("/api/user/myClass")
//                        .contentType(MediaType.APPLICATION_JSON_VALUE)
//                        .accept(MediaType.APPLICATION_JSON_VALUE)
//                        .characterEncoding("UTF-8")
//                        .header("X-AUTH-TOKEN", auth_token)
//                )
//                .andDo(print())
//                .andDo(document("user/myClass",
//                        requestHeaders(
//                                headerWithName(HttpHeaders.ACCEPT).description(MediaType.APPLICATION_JSON_VALUE),
//                                headerWithName("X-AUTH-TOKEN").description("????????? ??????????????? ???????????? ?????? ????????? (?????? ?????? ??????)")
//                        ),
//                        responseFields(
//                                fieldWithPath("output").type(JsonFieldType.NUMBER).description("????????????"),
//                                fieldWithPath("msg").type(JsonFieldType.STRING).description("?????? ?????????"),
//                                fieldWithPath("data.hostClassList").type(JsonFieldType.ARRAY).optional().description("????????? ????????? ?????? ?????????"),
//                                fieldWithPath("data.hostClassList{}").type(JsonFieldType.OBJECT).optional().description("????????? ????????? ?????? ????????? ??? ?????????"),
//                                fieldWithPath("data.hostClassList[].id").type(JsonFieldType.NUMBER).optional().description("????????? ????????? ?????? > ?????? ??????"),
//                                fieldWithPath("data.hostClassList[].name").type(JsonFieldType.STRING).optional().description("????????? ????????? ?????? > ?????????"),
//                                fieldWithPath("data.hostClassList[].info").type(JsonFieldType.STRING).optional().description("????????? ????????? ?????? > ?????? ????????????"),
//                                fieldWithPath("data.hostClassList[].img").type(JsonFieldType.STRING).optional().description("????????? ????????? ?????? > ?????? ?????????"),
//                                fieldWithPath("data.participantClassList").type(JsonFieldType.ARRAY).optional().description("????????? ????????? ?????? ?????????"),
//                                fieldWithPath("data.participantClassList{}").type(JsonFieldType.OBJECT).optional().description("????????? ????????? ?????? ????????? ??? ?????????"),
//                                fieldWithPath("data.participantClassList[].id").type(JsonFieldType.NUMBER).optional().description("????????? ????????? ?????? > ?????? ??????"),
//                                fieldWithPath("data.participantClassList[].name").type(JsonFieldType.STRING).optional().description("????????? ????????? ?????? > ?????????"),
//                                fieldWithPath("data.participantClassList[].info").type(JsonFieldType.STRING).optional().description("????????? ????????? ?????? > ?????? ????????????"),
//                                fieldWithPath("data.participantClassList[].img").type(JsonFieldType.STRING).optional().description("????????? ????????? ?????? > ?????? ?????????")
//                        ))
//                )
//        ;
//    }
//
//
//    @Test
//    @Order(2)
//    public void pushUpdate_test() throws Exception {
//        // ============================================================================================================================================
//        this.mockMvc.perform(RestDocumentationRequestBuilders.put("/api/user/push/{pushYN}", "Y")
//                        .contentType(MediaType.APPLICATION_JSON_VALUE)
//                        .accept(MediaType.APPLICATION_JSON_VALUE)
//                        .characterEncoding("UTF-8")
//                        .header("X-AUTH-TOKEN", auth_token)
//                )
//                .andDo(print())
//                .andDo(document("user/updatePush",
//                        requestHeaders(
//                                headerWithName(HttpHeaders.ACCEPT).description(MediaType.APPLICATION_JSON_VALUE),
//                                headerWithName("X-AUTH-TOKEN").description("????????? ??????????????? ???????????? ?????? ????????? (?????? ?????? ??????)")
//                        ),
//                        pathParameters(
//                                parameterWithName("pushYN").description("?????? ?????????. (Y:on, N:off)")
//                        ),
//                        responseFields(
//                                fieldWithPath("output").type(JsonFieldType.NUMBER).description("????????????"),
//                                fieldWithPath("msg").type(JsonFieldType.STRING).description("?????? ?????????")
//                        ))
//                )
//        ;
//    }
//
//
//
//    @Test
//    @Order(6)
//    public void deleteUser_test() throws Exception {
//        // ============================================================================================================================================
//
//        this.mockMvc.perform(delete("/api/user/")
//                        .contentType(MediaType.APPLICATION_JSON_VALUE)
//                        .accept(MediaType.APPLICATION_JSON_VALUE)
//                        .characterEncoding("UTF-8")
//                        .header("X-AUTH-TOKEN", auth_token)
//                )
//                .andDo(print())
//                .andDo(document("user/withdrawal",
//                        requestHeaders(
//                                headerWithName(HttpHeaders.ACCEPT).description(MediaType.APPLICATION_JSON_VALUE),
//                                headerWithName("X-AUTH-TOKEN").description("????????? ??????????????? ???????????? ?????? ????????? (?????? ?????? ??????)")
//                        ),
//                        responseFields(
//                                fieldWithPath("output").type(JsonFieldType.NUMBER).description("????????????"),
//                                fieldWithPath("msg").type(JsonFieldType.STRING).description("?????? ?????????")
//                        ))
//                )
//        ;
//    }
//
//
//
//    @Test
//    @Order(3)
//    public void updateProfile_test() throws Exception {
//        UpdateProfileReqDTO dto = UpdateProfileReqDTO.builder().nickname("newNick").age(25).gender("M").img(null).build();
//
//        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
//        Map<String, String> map = objectMapper.convertValue(dto, new TypeReference<Map<String, String>>() {});
//        params.setAll(map);
//        // ============================================================================================================================================
//
//        this.mockMvc.perform(put("/api/user/profile")
//                        .contentType(MediaType.APPLICATION_JSON_VALUE)
//                        .accept(MediaType.APPLICATION_JSON_VALUE)
//                        .characterEncoding("UTF-8")
//                        .header("X-AUTH-TOKEN", auth_token)
//                        .content(objectMapper.writeValueAsString(dto))
//                        .params( params )
//                )
//                .andDo(print())
//                .andDo(document("user/updateProfile",
//                        requestHeaders(
//                                headerWithName(HttpHeaders.ACCEPT).description(MediaType.APPLICATION_JSON_VALUE),
//                                headerWithName("X-AUTH-TOKEN").description("????????? ??????????????? ???????????? ?????? ????????? (?????? ?????? ??????)")
//                        ),
//                        requestFields(
//                                fieldWithPath("nickname").type(JsonFieldType.STRING).description("?????????"),
//                                fieldWithPath("age").type(JsonFieldType.NUMBER).description("??????"),
//                                fieldWithPath("gender").type(JsonFieldType.STRING).description("??????(M, F, '')"),
//                                fieldWithPath("img").type(JsonFieldType.OBJECT).optional().description("????????? (???????????? ?????? ?????? null??? ????????? ???)")
//                        ),
//                        responseFields(
//                                fieldWithPath("output").type(JsonFieldType.NUMBER).description("????????????"),
//                                fieldWithPath("msg").type(JsonFieldType.STRING).description("?????? ?????????")
//                        ))
//                )
//        ;
//    }
//
//
//
//    @Test
//    @Order(4)
//    public void myPush_test() throws Exception {
//        // ============================================================================================================================================
//
//        this.mockMvc.perform(get("/api/user/myPush")
//                        .contentType(MediaType.APPLICATION_JSON_VALUE)
//                        .accept(MediaType.APPLICATION_JSON_VALUE)
//                        .characterEncoding("UTF-8")
//                        .header("X-AUTH-TOKEN", auth_token)
//                )
//                .andDo(print())
//                .andDo(document("user/myPush",
//                        requestHeaders(
//                                headerWithName(HttpHeaders.ACCEPT).description(MediaType.APPLICATION_JSON_VALUE),
//                                headerWithName("X-AUTH-TOKEN").description("????????? ??????????????? ???????????? ?????? ????????? (?????? ?????? ??????)")
//                        ),
//                        responseFields(
//                                fieldWithPath("output").type(JsonFieldType.NUMBER).description("????????????"),
//                                fieldWithPath("msg").type(JsonFieldType.STRING).description("?????? ?????????"),
//                                fieldWithPath("data").type(JsonFieldType.STRING).optional().description("????????? ????????? ??????. (Y:on, N:off)")
//                        ))
//                )
//        ;
//    }
//
//
//
//    @Test
//    @Order(5)
//    public void myInfo_test() throws Exception {
//        // ============================================================================================================================================
//
//        this.mockMvc.perform(get("/api/user/myInfo")
//                        .contentType(MediaType.APPLICATION_JSON_VALUE)
//                        .accept(MediaType.APPLICATION_JSON_VALUE)
//                        .characterEncoding("UTF-8")
//                        .header("X-AUTH-TOKEN", auth_token)
//                )
//                .andDo(print())
//                .andDo(document("user/myInfo",
//                        requestHeaders(
//                                headerWithName(HttpHeaders.ACCEPT).description(MediaType.APPLICATION_JSON_VALUE),
//                                headerWithName("X-AUTH-TOKEN").description("????????? ??????????????? ???????????? ?????? ????????? (?????? ?????? ??????)")
//                        ),
//                        responseFields(
//                                fieldWithPath("output").type(JsonFieldType.NUMBER).description("????????????"),
//                                fieldWithPath("msg").type(JsonFieldType.STRING).description("?????? ?????????"),
//                                fieldWithPath("data.id").type(JsonFieldType.NUMBER).description("????????? ??????"),
//                                fieldWithPath("data.nickname").type(JsonFieldType.STRING).optional().description("????????? ?????????"),
//                                fieldWithPath("data.gender").type(JsonFieldType.STRING).optional().description("????????? ??????"),
//                                fieldWithPath("data.age").type(JsonFieldType.NUMBER).optional().description("????????? ??????"),
//                                fieldWithPath("data.img").type(JsonFieldType.STRING).optional().description("????????? ????????? ?????????")
//                        ))
//                )
//        ;
//    }
//
//}