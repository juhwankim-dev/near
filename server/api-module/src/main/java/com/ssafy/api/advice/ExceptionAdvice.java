package com.ssafy.api.advice;

import com.ssafy.api.service.common.CommonResult;
import com.ssafy.api.service.common.ResponseService;
import com.ssafy.core.exception.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.UnexpectedTypeException;

@RequiredArgsConstructor
@Slf4j
// 예외 처리 + 객체를 리턴
@RestControllerAdvice
public class ExceptionAdvice {
    private final ResponseService responseService;

    private final MessageSource messageSource;



    @ExceptionHandler(CAuthenticationEntryPointException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public CommonResult authenticationEntryPointException(HttpServletRequest request, CAuthenticationEntryPointException e) {
        log.info(getMessage("entryPointException.msg"));
        return responseService.getFailResult(Integer.valueOf(getMessage("entryPointException.code")), getMessage("entryPointException.msg"));
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public CommonResult accessDeniedException(HttpServletRequest request, AccessDeniedException e) {
        log.info(getMessage("accessDenied.msg"));
        return responseService.getFailResult(Integer.valueOf(getMessage("accessDenied.code")), getMessage("accessDenied.msg"));
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected CommonResult dataIntegrityException(HttpServletRequest request, DataIntegrityViolationException e) {
        log.info(getMessage("unKnown.msg"));
        return responseService.getFailResult(Integer.valueOf(getMessage("unKnown.code")), getMessage("unKnown.msg"));
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected CommonResult defaultException(HttpServletRequest request, Exception e) {
        e.printStackTrace();
        // 예외 처리의 메시지를 MessageSource에서 가져오도록 수정
        log.info(getMessage("unKnown.msg"));
        return responseService.getFailResult(Integer.valueOf(getMessage("unKnown.code")), getMessage("unKnown.msg"));
    }

    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.OK)
    protected CommonResult defaultException(HttpServletRequest request, BindException e) {
        // validation
        e.printStackTrace();
        log.info(getMessage("dataNotExistError.msg"));
        return responseService.getFailResult(0, getMessage("dataNotExistError.msg"));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.OK)
    protected CommonResult defaultException(HttpServletRequest request, MethodArgumentNotValidException e) {
        // validation
        e.printStackTrace();
        log.info(getMessage("dataNotExistError.msg"));
        return responseService.getFailResult(0, getMessage("dataNotExistError.msg"));
    }

    @ExceptionHandler(UnexpectedTypeException.class)
    @ResponseStatus(HttpStatus.OK)
    protected CommonResult defaultException(HttpServletRequest request, UnexpectedTypeException e) {
        // validation
        e.printStackTrace();
        log.info(getMessage("dataNotExistError.msg"));
        return responseService.getFailResult(0, getMessage("dataNotExistError.msg"));
    }

    @ExceptionHandler(CUserNotFoundException.class)
    @ResponseStatus(HttpStatus.OK)
    protected CommonResult userNotFound(HttpServletRequest request, CUserNotFoundException e) {
        log.info(getMessage("userNotFound.msg"));
        return responseService.getFailResult(0, getMessage("userNotFound.msg"));
    }

    @ExceptionHandler(FileUploadException.class)
    @ResponseStatus(HttpStatus.OK)
    public CommonResult communicationException(HttpServletRequest request, FileUploadException e) {
        log.info(getMessage("FileUploadException.msg"));
        return responseService.getFailResult(Integer.valueOf(getMessage("FileUploadException.code")), getMessage("FileUploadException.msg"));
    }

    @ExceptionHandler(FileNotSupportException.class)
    @ResponseStatus(HttpStatus.OK)
    public CommonResult communicationException(HttpServletRequest request, FileNotSupportException e) {
        log.info(getMessage("FileNotSupportException.msg"));
        return responseService.getFailResult(Integer.valueOf(getMessage("FileNotSupportException.code")), getMessage("FileNotSupportException.msg"));
    }

    @ExceptionHandler(FileSizeException.class)
    @ResponseStatus(HttpStatus.OK)
    public CommonResult communicationException(HttpServletRequest request, FileSizeException e) {
        log.info(getMessage("FileSizeException.msg"));
        return responseService.getFailResult(Integer.valueOf(getMessage("FileSizeException.code")), getMessage("FileSizeException.msg"));
    }

    @ExceptionHandler(ApiMessageException.class)
    @ResponseStatus(HttpStatus.OK)
    protected CommonResult communicationException(HttpServletRequest request, ApiMessageException e) {
        return responseService.getFailResult(0, e.getMessage());
    }

    @ExceptionHandler(FailedException.class)
    @ResponseStatus(HttpStatus.OK)
    protected CommonResult communicationException(HttpServletRequest request, FailedException e) {
        log.info(StringUtils.isEmpty(e.getMessage()) ? getMessage("failedException.msg") : e.getMessage());
        return responseService.getFailResult(Integer.valueOf(getMessage("failedException.code")), StringUtils.isEmpty(e.getMessage()) ? getMessage("failedException.msg") : e.getMessage());
    }




    // code정보에 해당하는 메시지를 조회합니다.
    private String getMessage(String code) {
        return getMessage(code, null);
    }
    // code정보, 추가 argument로 현재 locale에 맞는 메시지를 조회합니다.
    private String getMessage(String code, Object[] args) {
        return messageSource.getMessage(code, args, LocaleContextHolder.getLocale());
    }
}
