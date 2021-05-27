package com.shorturl.shorturl;

import com.shorturl.shorturl.dto.ErrorResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ErrorResponseDto> methodArgumentNotValidexception(final MethodArgumentNotValidException e){
        log.error("handleMethodArgumentNotValidException", e);
        final ErrorResponseDto response = ErrorResponseDto.of(ErrorCode.INVALID_INPUT_VALUE, e.getBindingResult().getAllErrors().get(0).getDefaultMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ErrorResponseDto> exception(final Exception e){
        log.error("Exception", e);
        final ErrorResponseDto response = ErrorResponseDto.of(ErrorCode.INVALID_INPUT_VALUE, e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}
