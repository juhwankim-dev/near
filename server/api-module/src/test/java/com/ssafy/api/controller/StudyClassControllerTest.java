//package com.ssafy.api.controller;
//
//import com.fasterxml.jackson.core.type.TypeReference;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.ssafy.api.dto.req.*;
//import com.ssafy.api.dto.req.*;
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
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
//
//@AutoConfigureRestDocs
//@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
//@AutoConfigureMockMvc
//public class StudyClassControllerTest {
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    String auth_token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxIiwicm9sZXMiOlsiUk9MRV9VU0VSIl0sInVzZXJQayI6IjEiLCJpYXQiOjE2Mzk0NTc0NTIsImV4cCI6MTY0MjA0OTQ1Mn0.afQNA4zfLFMzSXuLG75SPSopDPtkg97Kh9TBptaJDYI";
//
//
//
//
//    @Test
//    @Order(1)
//    public void sortList_test() throws Exception {
//        StudyClassSortReqDTO dto = StudyClassSortReqDTO.builder().sort("latest").page(1).build();
//
//        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
//        Map<String, String> map = objectMapper.convertValue(dto, new TypeReference<Map<String, String>>() {});
//        params.setAll(map);
//        // ============================================================================================================================================
//
//        this.mockMvc.perform(get("/api/studyclass/list")
//                        .contentType(MediaType.APPLICATION_JSON_VALUE)
//                        .accept(MediaType.APPLICATION_JSON_VALUE)
//                        .characterEncoding("UTF-8")
//                        .header("X-AUTH-TOKEN", auth_token)
//                        .content(objectMapper.writeValueAsString(dto))
//                        .params( params )
//                )
//                .andDo(print())
//                .andDo(document("studyclass/list",
//                        requestHeaders(
//                                headerWithName(HttpHeaders.ACCEPT).description(MediaType.APPLICATION_JSON_VALUE),
//                                headerWithName("X-AUTH-TOKEN").description("????????? ??????????????? ???????????? ?????? ????????? (?????? ?????? ??????)")
//                        ),
//                        requestFields(
//                                fieldWithPath("sort").type(JsonFieldType.STRING).description("?????? (latest:?????????, old:?????????)"),
//                                fieldWithPath("page").type(JsonFieldType.NUMBER).description("?????????")
//                        ),
//                        responseFields(
//                                fieldWithPath("output").type(JsonFieldType.NUMBER).description("????????????"),
//                                fieldWithPath("msg").type(JsonFieldType.STRING).description("?????? ?????????"),
//                                fieldWithPath("data").type(JsonFieldType.ARRAY).optional().description("??????????????? ?????? ?????? ?????????"),
//                                fieldWithPath("data{}").type(JsonFieldType.OBJECT).optional().description("??????????????? ?????? ?????? ???????????? ??? ?????????"),
//                                fieldWithPath("data[].id").type(JsonFieldType.NUMBER).optional().description("?????? ??????"),
//                                fieldWithPath("data[].name").type(JsonFieldType.STRING).optional().description("?????????"),
//                                fieldWithPath("data[].info").type(JsonFieldType.STRING).optional().description("?????? ????????????"),
//                                fieldWithPath("data[].img").type(JsonFieldType.STRING).optional().description("?????? ?????????")
//                        ))
//                )
//        ;
//    }
//
//
//
//
//    @Test
//    @Order(2)
//    public void searchList_test() throws Exception {
//        StudyClassSearchReqDTO dto = StudyClassSearchReqDTO.builder().search("?????????").page(1).build();
//
//        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
//        Map<String, String> map = objectMapper.convertValue(dto, new TypeReference<Map<String, String>>() {});
//        params.setAll(map);
//        // ============================================================================================================================================
//
//        this.mockMvc.perform(get("/api/studyclass/search")
//                        .contentType(MediaType.APPLICATION_JSON_VALUE)
//                        .accept(MediaType.APPLICATION_JSON_VALUE)
//                        .characterEncoding("UTF-8")
//                        .header("X-AUTH-TOKEN", auth_token)
//                        .content(objectMapper.writeValueAsString(dto))
//                        .params( params )
//                )
//                .andDo(print())
//                .andDo(document("studyclass/search",
//                        requestHeaders(
//                                headerWithName(HttpHeaders.ACCEPT).description(MediaType.APPLICATION_JSON_VALUE),
//                                headerWithName("X-AUTH-TOKEN").description("????????? ??????????????? ???????????? ?????? ????????? (?????? ?????? ??????)")
//                        ),
//                        requestFields(
//                                fieldWithPath("search").type(JsonFieldType.STRING).optional().description("?????????"),
//                                fieldWithPath("page").type(JsonFieldType.NUMBER).description("?????????")
//                        ),
//                        responseFields(
//                                fieldWithPath("output").type(JsonFieldType.NUMBER).description("????????????"),
//                                fieldWithPath("msg").type(JsonFieldType.STRING).description("?????? ?????????"),
//                                fieldWithPath("data").type(JsonFieldType.ARRAY).optional().description("??????????????? ?????? ?????? ?????????"),
//                                fieldWithPath("data{}").type(JsonFieldType.OBJECT).optional().description("??????????????? ?????? ?????? ????????? ??? ?????????"),
//                                fieldWithPath("data[].id").type(JsonFieldType.NUMBER).optional().description("?????? ??????"),
//                                fieldWithPath("data[].name").type(JsonFieldType.STRING).optional().description("?????????"),
//                                fieldWithPath("data[].info").type(JsonFieldType.STRING).optional().description("?????? ????????????"),
//                                fieldWithPath("data[].img").type(JsonFieldType.STRING).optional().description("?????? ?????????")
//                        ))
//                )
//        ;
//    }
//
//
//
//
//    @Test
//    @Order(3)
//    public void detail_test() throws Exception {
//        // ============================================================================================================================================
//        // RestDocumentationRequestBuilders >> pathParametets ????????? ?????? "RestDocumentationRequestBuilders" ??????
//        this.mockMvc.perform(RestDocumentationRequestBuilders.get("/api/studyclass/detail/{classId}", 1)
//                        .contentType(MediaType.APPLICATION_JSON_VALUE)
//                        .accept(MediaType.APPLICATION_JSON_VALUE)
//                        .characterEncoding("UTF-8")
//                        .header("X-AUTH-TOKEN", auth_token)
//                )
//                .andDo(print())
//                .andDo(document("studyclass/detail",
//                        requestHeaders(
//                                headerWithName(HttpHeaders.ACCEPT).description(MediaType.APPLICATION_JSON_VALUE),
//                                headerWithName("X-AUTH-TOKEN").description("????????? ??????????????? ???????????? ?????? ????????? (?????? ?????? ??????)")
//                        ),
//                        pathParameters(
//                                parameterWithName("classId").description("???????????? ????????? ????????? ??????")
//                        ),
//                        responseFields(
//                                fieldWithPath("output").type(JsonFieldType.NUMBER).description("????????????"),
//                                fieldWithPath("msg").type(JsonFieldType.STRING).description("?????? ?????????"),
//                                fieldWithPath("data").type(JsonFieldType.OBJECT).optional().description("?????? ?????????"),
//                                fieldWithPath("data.id").type(JsonFieldType.NUMBER).description("?????? ??????"),
//                                fieldWithPath("data.name").type(JsonFieldType.STRING).description("?????????"),
//                                fieldWithPath("data.info").type(JsonFieldType.STRING).description("?????? ????????????"),
//                                fieldWithPath("data.img").type(JsonFieldType.STRING).optional().description("?????? ?????????"),
//                                fieldWithPath("data.participateYN").type(JsonFieldType.STRING).description("??????(???????????? ??????)??? ?????? ?????????????????? ??????????????? ?????? (Y, N)"),
//                                fieldWithPath("data.hostYN").type(JsonFieldType.STRING).description("??????(???????????? ??????)??? ?????? ?????????????????? ?????????(??????)?????? ?????? (Y, N)"),
//                                fieldWithPath("data.participant[]").type(JsonFieldType.ARRAY).optional().description("????????? ?????????"),
//                                fieldWithPath("data.participant{}").type(JsonFieldType.OBJECT).optional().description("????????? ????????? ??? ?????????"),
//                                fieldWithPath("data.participant[].id").type(JsonFieldType.NUMBER).optional().description("????????? ????????? > ?????? ??????"),
//                                fieldWithPath("data.participant[].nickname").type(JsonFieldType.STRING).optional().description("????????? ????????? > ?????? ?????????"),
//                                fieldWithPath("data.participant[].img").type(JsonFieldType.STRING).optional().description("????????? ????????? > ?????? ?????????")
//                        ))
//                )
//        ;
//    }
//
//
//
//
//    @Test
//    @Order(4)
//    public void createStudyClass_test() throws Exception {
//        CreateStudyClassReqDTO dto = CreateStudyClassReqDTO.builder().name("JAVA ???????????????").info("?????? ????????? JAVA ????????????????????????.").img(null).build();
//
//        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
//        Map<String, String> map = objectMapper.convertValue(dto, new TypeReference<Map<String, String>>() {});
//        params.setAll(map);
//        // ============================================================================================================================================
//
//        this.mockMvc.perform(post("/api/studyclass/")
//                        .contentType(MediaType.APPLICATION_JSON_VALUE)
//                        .accept(MediaType.APPLICATION_JSON_VALUE)
//                        .characterEncoding("UTF-8")
//                        .header("X-AUTH-TOKEN", auth_token)
//                        .content(objectMapper.writeValueAsString(dto))
//                        .params( params )
//                )
//                .andDo(print())
//                .andDo(document("studyclass/createStudyClass",
//                        requestHeaders(
//                                headerWithName(HttpHeaders.ACCEPT).description(MediaType.APPLICATION_JSON_VALUE),
//                                headerWithName("X-AUTH-TOKEN").description("????????? ??????????????? ???????????? ?????? ????????? (?????? ?????? ??????)")
//                        ),
//                        requestFields(
//                                fieldWithPath("name").type(JsonFieldType.STRING).description("??????????????????"),
//                                fieldWithPath("info").type(JsonFieldType.STRING).description("??????????????? ????????????"),
//                                fieldWithPath("img").type(JsonFieldType.OBJECT).optional().description("??????????????? ?????????")
//                        ),
//                        responseFields(
//                                fieldWithPath("output").type(JsonFieldType.NUMBER).description("????????????"),
//                                fieldWithPath("msg").type(JsonFieldType.STRING).description("?????? ?????????"),
//                                fieldWithPath("data").type(JsonFieldType.ARRAY).optional().description("?????? ??????????????? ?????????"),
//                                fieldWithPath("data{}").type(JsonFieldType.OBJECT).optional().description("?????? ??????????????? ????????? ??? ?????????"),
//                                fieldWithPath("data[].id").type(JsonFieldType.NUMBER).description("?????? ??????"),
//                                fieldWithPath("data[].name").type(JsonFieldType.STRING).optional().description("?????????"),
//                                fieldWithPath("data[].info").type(JsonFieldType.STRING).optional().description("?????? ????????????"),
//                                fieldWithPath("data[].img").type(JsonFieldType.STRING).optional().description("?????? ?????????")
//                        ))
//                )
//        ;
//    }
//
//
//
//
//    @Test
//    @Order(5)
//    public void participate_test() throws Exception {
//        // ============================================================================================================================================
//        this.mockMvc.perform(RestDocumentationRequestBuilders.post("/api/studyclass/participate/{classId}", 3)
//                        .contentType(MediaType.APPLICATION_JSON_VALUE)
//                        .accept(MediaType.APPLICATION_JSON_VALUE)
//                        .characterEncoding("UTF-8")
//                        .header("X-AUTH-TOKEN", auth_token)
//                )
//                .andDo(print())
//                .andDo(document("studyclass/participate",
//                        requestHeaders(
//                                headerWithName(HttpHeaders.ACCEPT).description(MediaType.APPLICATION_JSON_VALUE),
//                                headerWithName("X-AUTH-TOKEN").description("????????? ??????????????? ???????????? ?????? ????????? (?????? ?????? ??????)")
//                        ),
//                        pathParameters(
//                                parameterWithName("classId").description("??????????????? ????????? ??????")
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
//
//    @Test
//    @Order(6)
//    public void deleteStudyUser_test() throws Exception {
//        DeleteStudyUserReqDTO dto = DeleteStudyUserReqDTO.builder().classId(1).deleteUser(9).build();
//
//        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
//        Map<String, String> map = objectMapper.convertValue(dto, new TypeReference<Map<String, String>>() {});
//        params.setAll(map);
//        // ============================================================================================================================================
//
//        this.mockMvc.perform(delete("/api/studyclass/user")
//                        .contentType(MediaType.APPLICATION_JSON_VALUE)
//                        .accept(MediaType.APPLICATION_JSON_VALUE)
//                        .characterEncoding("UTF-8")
//                        .header("X-AUTH-TOKEN", auth_token)
//                        .content(objectMapper.writeValueAsString(dto))
//                        .params( params )
//                )
//                .andDo(print())
//                .andDo(document("studyclass/deleteStudyUser",
//                        requestHeaders(
//                                headerWithName(HttpHeaders.ACCEPT).description(MediaType.APPLICATION_JSON_VALUE),
//                                headerWithName("X-AUTH-TOKEN").description("????????? ??????????????? ???????????? ?????? ????????? (?????? ?????? ??????)")
//                        ),
//                        requestFields(
//                                fieldWithPath("classId").type(JsonFieldType.NUMBER).description("??????????????? ??????"),
//                                fieldWithPath("deleteUser").type(JsonFieldType.NUMBER).description("????????? ?????? ??????")
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
//
//    @Test
//    @Order(7)
//    public void delete_test() throws Exception {
//        // ============================================================================================================================================
//        this.mockMvc.perform(RestDocumentationRequestBuilders.delete("/api/studyclass/{classId}", 1)
//                        .contentType(MediaType.APPLICATION_JSON_VALUE)
//                        .accept(MediaType.APPLICATION_JSON_VALUE)
//                        .characterEncoding("UTF-8")
//                        .header("X-AUTH-TOKEN", auth_token)
//                )
//                .andDo(print())
//                .andDo(document("studyclass/delete",
//                        requestHeaders(
//                                headerWithName(HttpHeaders.ACCEPT).description(MediaType.APPLICATION_JSON_VALUE),
//                                headerWithName("X-AUTH-TOKEN").description("????????? ??????????????? ???????????? ?????? ????????? (?????? ?????? ??????)")
//                        ),
//                        pathParameters(
//                                parameterWithName("classId").description("????????? ????????? ??????")
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
//
//    @Test
//    @Order(8)
//    public void updateStudyClass_test() throws Exception {
//        UpdateStudyClassReqDTO dto = UpdateStudyClassReqDTO.builder().classId(2).name("Kotlin ???????????????").info("??????????????? ?????? (Java > Kotlin)").build();
//
//        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
//        Map<String, String> map = objectMapper.convertValue(dto, new TypeReference<Map<String, String>>() {});
//        params.setAll(map);
//        // ============================================================================================================================================
//
//        this.mockMvc.perform(put("/api/studyclass/")
//                        .contentType(MediaType.APPLICATION_JSON_VALUE)
//                        .accept(MediaType.APPLICATION_JSON_VALUE)
//                        .characterEncoding("UTF-8")
//                        .header("X-AUTH-TOKEN", auth_token)
//                        .content(objectMapper.writeValueAsString(dto))
//                        .params( params )
//                )
//                .andDo(print())
//                .andDo(document("studyclass/updateStudyClass",
//                        requestHeaders(
//                                headerWithName(HttpHeaders.ACCEPT).description(MediaType.APPLICATION_JSON_VALUE),
//                                headerWithName("X-AUTH-TOKEN").description("????????? ??????????????? ???????????? ?????? ????????? (?????? ?????? ??????)")
//                        ),
//                        requestFields(
//                                fieldWithPath("classId").type(JsonFieldType.NUMBER).description("??????????????? ??????"),
//                                fieldWithPath("name").type(JsonFieldType.STRING).description("??????????????????"),
//                                fieldWithPath("info").type(JsonFieldType.STRING).description("??????????????? ????????????"),
//                                fieldWithPath("img").type(JsonFieldType.OBJECT).optional().description("??????????????? ????????? (???????????? ?????? ?????? null??? ????????? ???)")
//                        ),
//                        responseFields(
//                                fieldWithPath("output").type(JsonFieldType.NUMBER).description("????????????"),
//                                fieldWithPath("msg").type(JsonFieldType.STRING).description("?????? ?????????")
//                        ))
//                )
//        ;
//    }
//
//}