package com.example.sales_crud.error

import com.example.sales_crud.error.dto.ErrorMessage
import com.example.sales_crud.error.exception.BadRequestException
import com.example.sales_crud.error.exception.NotFoundException
import com.example.sales_crud.error.exception.UnauthorizedException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class RestResponseEntityExceptionHandler {

    @ExceptionHandler(HttpMessageNotReadableException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleHttpMessageNotReadableException(ex: HttpMessageNotReadableException): ErrorMessage {
        val messages: List<String> = listOf(ex.message!!);
        val errorMessage = ErrorMessage(
            message = messages,
            statusCode = HttpStatus.BAD_REQUEST.value(),
            error = HttpStatus.BAD_REQUEST
        );

        println(errorMessage)

        return errorMessage;
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleValidationExceptions(ex: MethodArgumentNotValidException): ErrorMessage {
        val messages: List<String> = ex.allErrors.stream().map { err -> err.defaultMessage!!}.toList();
        println(messages);
        return ErrorMessage(
            message = messages,
            error = HttpStatus.BAD_REQUEST,
            statusCode = HttpStatus.BAD_REQUEST.value()
        );
    }

    @ExceptionHandler(NotFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun handleNotFoundException(ex: NotFoundException): ErrorMessage {
        val messages: List<String> = listOf(ex.message!!);
        return ErrorMessage(
            message = messages,
            error = HttpStatus.NOT_FOUND,
            statusCode =  HttpStatus.NOT_FOUND.value()
        );
    }
    @ExceptionHandler(UnauthorizedException::class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    fun handleResourceUnauthorizedException(ex:UnauthorizedException): ErrorMessage {
        val messages: List<String> = listOf(ex.message!!);
        return ErrorMessage(
            message = messages,
            error = HttpStatus.UNAUTHORIZED,
            statusCode =  HttpStatus.UNAUTHORIZED.value()
        );
    }
    @ExceptionHandler(BadRequestException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleResourceBadRequestException(ex:BadRequestException):ErrorMessage {
        val messages: List<String> = listOf(ex.message!!);
        return ErrorMessage(
            message = messages,
            error = HttpStatus.BAD_REQUEST,
            statusCode =  HttpStatus.BAD_REQUEST.value()
        );
    }

}
