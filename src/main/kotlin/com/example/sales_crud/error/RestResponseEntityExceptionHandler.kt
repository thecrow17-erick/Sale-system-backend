package com.example.sales_crud.error

import com.example.sales_crud.error.dto.ErrorMessage
import com.example.sales_crud.error.exception.BadRequestException
import com.example.sales_crud.error.exception.NotFoundException
import com.example.sales_crud.error.exception.UnauthorizedException
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.security.SignatureException
import org.springframework.http.HttpStatus
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class RestResponseEntityExceptionHandler {

    @ExceptionHandler(SignatureException::class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    fun handleHttpMessageSignatureException(ex: SignatureException): ErrorMessage {
        println(ex.message)
        return ErrorMessage(
            statusCode = HttpStatus.UNAUTHORIZED.value(),
            message = listOf(ex.message!!),
            error = HttpStatus.UNAUTHORIZED
        )
    }

    @ExceptionHandler(ExpiredJwtException::class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    fun handleHttpMessageExpiredJwtException(ex: ExpiredJwtException): ErrorMessage {
        println(ex.message)
        return ErrorMessage(
            statusCode = HttpStatus.UNAUTHORIZED.value(),
            message = listOf(ex.message!!),
            error = HttpStatus.UNAUTHORIZED
        )
    }

    @ExceptionHandler(HttpMessageNotReadableException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleHttpMessageNotReadableException(ex: HttpMessageNotReadableException): ErrorMessage {
        val messages: List<String> = listOf("Ingrese contenido al cuerpo");
        val errorMessage = ErrorMessage(
            message = messages,
            statusCode = HttpStatus.BAD_REQUEST.value(),
            error = HttpStatus.BAD_REQUEST
        );

        return errorMessage;
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleValidationExceptions(ex: MethodArgumentNotValidException): ErrorMessage {
        val messages = ex.allErrors.map { it.defaultMessage.toString() }
        val errorMessage =  ErrorMessage(
            statusCode = HttpStatus.BAD_REQUEST.value(),
            message = messages,
            error = HttpStatus.BAD_REQUEST
        )
        return errorMessage;
    }

    @ExceptionHandler(NotFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun handleNotFoundException(ex: NotFoundException): ErrorMessage {
        val messages = listOf(ex.message ?: "Resource not found")
        val err = ErrorMessage(
            message = messages,
            statusCode = HttpStatus.NOT_FOUND.value(),
            error = HttpStatus.NOT_FOUND
        )
        return err;
    }

    @ExceptionHandler(UnauthorizedException::class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    fun handleResourceUnauthorizedException(ex:UnauthorizedException): ErrorMessage {
        val messages: List<String> = listOf(ex.message!!);
        val errorMessage =  ErrorMessage(
            message = messages,
            error = HttpStatus.UNAUTHORIZED,
            statusCode =  HttpStatus.UNAUTHORIZED.value()
        );
        return  errorMessage;
    }

    @ExceptionHandler(BadRequestException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleResourceBadRequestException(ex:BadRequestException):ErrorMessage {
        val messages: List<String> = listOf(ex.message!!);
        val errorMessage= ErrorMessage(
            message = messages,
            error = HttpStatus.BAD_REQUEST,
            statusCode =  HttpStatus.BAD_REQUEST.value()
        );
        return errorMessage;
    }

}
