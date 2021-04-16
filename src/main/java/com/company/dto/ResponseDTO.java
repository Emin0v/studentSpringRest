package com.company.dto;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ResponseDTO {

    private Integer errorCode;
    private String errorMessage;
    private String successMessage;
    private Object object;

    private ResponseDTO(){

    }

    public static ResponseDTO of(Object obj){
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setObject(obj);
        return responseDTO;
    }

    public static ResponseDTO of(Object obj,String successMessage){
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setObject(obj);
        responseDTO.setSuccessMessage(successMessage);
        return responseDTO;
    }

    public static ResponseDTO of(Object obj,Integer errorCode , String errorMessage){
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setObject(obj);
        responseDTO.setErrorCode(errorCode);
        responseDTO.setErrorMessage(errorMessage);
        return responseDTO;
    }
}
