package com.computer.network.pojo;

import lombok.Data;

@Data
public class BaseResponse {
    //调用是否成功
    private Boolean success;
    //返回的提示信息
    private String message;
    //内容
    private Object content;

    public static BaseResponse buildSuccess(){
        BaseResponse response=new BaseResponse();
        response.setSuccess(true);
        return response;
    }

    public static BaseResponse buildSuccess(Object content){
        BaseResponse response=new BaseResponse();
        response.setContent(content);
        response.setSuccess(true);
        return response;
    }

    public static BaseResponse buildFailure(String message){
        BaseResponse response=new BaseResponse();
        response.setSuccess(false);
        response.setMessage(message);
        System.out.println(message);
        return response;
    }
}
