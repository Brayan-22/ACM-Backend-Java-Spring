package dev.alejandro.libraryproject.presentation.dto;

import dev.alejandro.libraryproject.utils.Constants;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
public class ApiResponseDTO<T>{
    private T data;
    private String message;
    private boolean success;
    private int status;
    private LocalDateTime timestamp;

    public void SuccessOperation(T data){
        setData(data);
        setMessage(Constants.Message.SUCCESS_OPERATION);
        setSuccess(true);
        setStatus(HttpStatus.OK.value());
        setTimestamp(LocalDateTime.now());
    }
    public void SuccessOperation(){
        setData(null);
        setMessage(Constants.Message.SUCCESS_OPERATION);
        setSuccess(true);
        setStatus(HttpStatus.OK.value());
        setTimestamp(LocalDateTime.now());
    }

    public void FailedOperation(){
        setData(null);
        setMessage(Constants.Message.ERROR_OPERATION);
        setSuccess(false);
        setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        setTimestamp(LocalDateTime.now());
    }

    public void FailedOperation(T data){
        setData(data);
        setMessage(Constants.Message.ERROR_OPERATION);
        setSuccess(false);
        setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        setTimestamp(LocalDateTime.now());
    }
    public void BadOperation(){
        setData(null);
        setMessage(Constants.Message.BAD_OPERATION);
        setSuccess(false);
        setStatus(HttpStatus.BAD_REQUEST.value());
        setTimestamp(LocalDateTime.now());
    }

    public void BadOperation(T data){
        setData(data);
        setMessage(Constants.Message.BAD_OPERATION);
        setSuccess(false);
        setStatus(HttpStatus.BAD_REQUEST.value());
        setTimestamp(LocalDateTime.now());
    }

}
