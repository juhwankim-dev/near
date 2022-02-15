package com.ssafy.api.controller;

import io.grpc.internal.IoUtils;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.InputStream;

@Api(tags = {"07. 파일"})
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/file")
@CrossOrigin(origins = "*")
public class FileController {

    @GetMapping(produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] getImageWithMediaType() throws IOException {
        InputStream in = getClass().getResourceAsStream("/images/love.png");
        return IoUtils.toByteArray(in);
    }
}
