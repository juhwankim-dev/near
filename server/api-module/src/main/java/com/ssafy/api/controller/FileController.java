package com.ssafy.api.controller;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Api(tags = {"07. 파일"})
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/file")
@CrossOrigin(origins = "*")
public class FileController {

    @GetMapping("/get-text")
    public String getText() {
        return "Hello world";
    }



}
