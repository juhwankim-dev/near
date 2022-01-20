//package com.ssafy.api.controller;
//
//import com.fasterxml.jackson.core.type.TypeReference;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.ssafy.api.dto.req.LoginUserReqDTO;
//import com.ssafy.api.dto.req.SignUpReqDTO;
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
//import java.util.Map;
//
//import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
//import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
//import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
//import static org.springframework.restdocs.payload.PayloadDocumentation.*;
//import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//
//@AutoConfigureRestDocs
//@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
//@AutoConfigureMockMvc
//public class SignControllerTest {
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//
//    @Test
//    @Order(1)
//    public void signUp_test() throws Exception {
//        SignUpReqDTO dto = SignUpReqDTO.builder().uid("userId001").type("none").password("pwd1234").name("회원명").email("user@mail.com")
//                .phone("01012345678").address("서울특별시 금천구 가산디지털1로 168").addressDetail("우림라이온스밸리").build();
//
//        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
//        Map<String, String> map = objectMapper.convertValue(dto, new TypeReference<Map<String, String>>() {});
//        params.setAll(map);
//        // ============================================================================================================================================
//
//        this.mockMvc.perform(post("/api/sign/signup")
//                        .contentType(MediaType.APPLICATION_JSON_VALUE)
//                        .accept(MediaType.APPLICATION_JSON_VALUE)
//                        .characterEncoding("UTF-8")
//                        .content(objectMapper.writeValueAsString(dto))
//                        .params( params )
//                )
//                .andDo(print())
//                .andDo(document("sign/signup",
//                        requestHeaders(
//                                headerWithName(HttpHeaders.ACCEPT).description(MediaType.APPLICATION_JSON_VALUE)
//                        ),
//                        requestFields(
//                                fieldWithPath("uid").type(JsonFieldType.STRING).description("uid (일반회원:아이디, sns로그인:uid값)"),
//                                fieldWithPath("type").type(JsonFieldType.STRING).description("회원가입 타입 (none, sns)"),
//                                fieldWithPath("password").type(JsonFieldType.STRING).description("비밀번호"),
//                                fieldWithPath("name").type(JsonFieldType.STRING).description("이름"),
//                                fieldWithPath("email").type(JsonFieldType.STRING).description("이메일"),
//                                fieldWithPath("phone").type(JsonFieldType.STRING).optional().description("핸드폰번호('-'값 없이 입력)"),
//                                fieldWithPath("address").type(JsonFieldType.STRING).optional().description("주소"),
//                                fieldWithPath("addressDetail").type(JsonFieldType.STRING).optional().description("상세주소")
//                        ),
//                        responseFields(
//                                fieldWithPath("output").type(JsonFieldType.NUMBER).description("결과코드"),
//                                fieldWithPath("msg").type(JsonFieldType.STRING).description("결과 메세지"),
//                                fieldWithPath("data.id").type(JsonFieldType.NUMBER).optional().description("회원가입된 회원의 키값")
//                        ))
//                )
//        ;
//    }
//
//
//    @Test
//    @Order(2)
//    public void regProfile_test() throws Exception {
//        RegProfileReqDTO dto = RegProfileReqDTO.builder().id(1).nickname("nick01").age(24).gender("F").img(null).build();
//
//        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
//        Map<String, String> map = objectMapper.convertValue(dto, new TypeReference<Map<String, String>>() {});
//        params.setAll(map);
//        // ============================================================================================================================================
//
//        this.mockMvc.perform(post("/api/sign/regProfile")
//                        .contentType(MediaType.APPLICATION_JSON_VALUE)
//                        .accept(MediaType.APPLICATION_JSON_VALUE)
//                        .characterEncoding("UTF-8")
//                        .content(objectMapper.writeValueAsString(dto))
//                        .params(params)
//                )
//                .andDo(print())
//                .andDo(document("sign/regProfile",
//                        requestHeaders(
//                                headerWithName(HttpHeaders.ACCEPT).description(MediaType.APPLICATION_JSON_VALUE)
//                        ),
//                        requestFields(
//                                fieldWithPath("id").type(JsonFieldType.NUMBER).description("회원 아이디"),
//                                fieldWithPath("nickname").type(JsonFieldType.STRING).description("닉네임"),
//                                fieldWithPath("age").type(JsonFieldType.NUMBER).optional().description("나이"),
//                                fieldWithPath("gender").type(JsonFieldType.STRING).description("성별(M, F, '')"),
//                                fieldWithPath("img").type(JsonFieldType.OBJECT).optional().description("이미지")
//                        ),
//                        responseFields(
//                                fieldWithPath("output").type(JsonFieldType.NUMBER).description("결과코드"),
//                                fieldWithPath("msg").type(JsonFieldType.STRING).description("결과 메세지")
//                        ))
//                )
//        ;
//    }
//
//
//    @Test
//    @Order(3)
//    public void login_test() throws Exception {
//        LoginUserReqDTO dto = LoginUserReqDTO.builder().uid("userId001").password("pwd1234").type("none")
//                .token("ebbWwQ22T_OMpoJYq36vjc:APA91bFsAjvRRiiE63qZURN83Hsn8nKTXZLYRD8ztrtYRkQ17_gT5S86P6qrrEnsI-o_57w6SrybTb_OudCt6rFTvXQJVdfv5r0f47ZzMAxZSH_I1knJ8kR5gO_bNtUkLzm8L1-Yh7sU")
//                .build();
//
//        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
//        Map<String, String> map = objectMapper.convertValue(dto, new TypeReference<Map<String, String>>() {});
//        params.setAll(map);
//        // ============================================================================================================================================
//
//        this.mockMvc.perform(get("/api/sign/login")
//                        .contentType(MediaType.APPLICATION_JSON_VALUE)
//                        .accept(MediaType.APPLICATION_JSON_VALUE)
//                        .characterEncoding("UTF-8")
//                        .content(objectMapper.writeValueAsString(dto))
//                        .params(params)
//                )
//                .andDo(print())
//                .andDo(document("sign/login",
//                        requestHeaders(
//                                headerWithName(HttpHeaders.ACCEPT).description(MediaType.APPLICATION_JSON_VALUE)
//                        ),
//                        requestFields(
//                                fieldWithPath("uid").type(JsonFieldType.STRING).description("uid (일반회원:아이디, sns로그인:uid값)"),
//                                fieldWithPath("password").type(JsonFieldType.STRING).description("비밀번호"),
//                                fieldWithPath("token").type(JsonFieldType.STRING).optional().description("푸쉬 알림을 위한 fcm 토큰값"),
//                                fieldWithPath("deviceUid").type(JsonFieldType.STRING).optional().description("디바이스 고유 아이디"),
//                                fieldWithPath("type").type(JsonFieldType.STRING).description("로그인 타입 (none, sns)")
//                        ),
//                        responseFields(
//                                fieldWithPath("output").type(JsonFieldType.NUMBER).description("결과코드"),
//                                fieldWithPath("msg").type(JsonFieldType.STRING).description("결과 메세지"),
//                                fieldWithPath("data.id").type(JsonFieldType.NUMBER).optional().description("회원 아이디"),
//                                fieldWithPath("data.token").type(JsonFieldType.STRING).optional().description("인증된 사용자임을 증명하는 JWT 토큰값")
//                        ))
//                )
//        ;
//    }
//
//
//    @Test
//    @Order(4)
//    public void existsSocial_test() throws Exception {
//        SocialSignChkReqDTO dto = SocialSignChkReqDTO.builder().uid("userId001").type("sns").build();
//
//        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
//        Map<String, String> map = objectMapper.convertValue(dto, new TypeReference<Map<String, String>>() {});
//        params.setAll(map);
//        // ============================================================================================================================================
//
//        this.mockMvc.perform(get("/api/sign/exists/social")
//                        .contentType(MediaType.APPLICATION_JSON_VALUE)
//                        .accept(MediaType.APPLICATION_JSON_VALUE)
//                        .characterEncoding("UTF-8")
//                        .content(objectMapper.writeValueAsString(dto))
//                        .params(params)
//                )
//                .andDo(print())
//                .andDo(document("sign/existsSocial",
//                        requestHeaders(
//                                headerWithName(HttpHeaders.ACCEPT).description(MediaType.APPLICATION_JSON_VALUE)
//                        ),
//                        requestFields(
//                                fieldWithPath("uid").type(JsonFieldType.STRING).description("uid (일반회원:아이디, sns로그인:uid값)"),
//                                fieldWithPath("type").type(JsonFieldType.STRING).description("로그인 타입 (none, sns)")
//                        ),
//                        responseFields(
//                                fieldWithPath("output").type(JsonFieldType.NUMBER).description("결과코드"),
//                                fieldWithPath("msg").type(JsonFieldType.STRING).description("결과 메세지")
//                        ))
//                )
//        ;
//    }
//
//}