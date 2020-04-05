package com.ynz.hplusapp.exceptions;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;

/**
 * applying on all controllers.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({ApplicationException.class, AsyncRequestTimeoutException.class})
    public String handleException() {
        System.out.println("in global exception handler.");
        return "error";
    }

}
