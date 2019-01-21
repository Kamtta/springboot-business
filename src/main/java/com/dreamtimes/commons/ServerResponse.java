package com.dreamtimes.commons;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

@Data
@JsonSerialize(include = JsonSerialize.Inclusion.NON_EMPTY)
public class ServerResponse<T> {

    private Integer status;
    private T data;
    private String msg;

    private ServerResponse() {}
    private ServerResponse(int status) {
        this.status = status;
    }

    private ServerResponse(int status,String msg) {
        this.status = status;
        this.msg = msg;
    }

    private ServerResponse(int status,T data) {
        this.status = status;
        this.data =data;
    }

    private ServerResponse(int status,T data,String msg) {
        this.status = status;
        this.data = data;
        this.msg = msg;
    }

//    测试服务器返回是否成功
    @JsonIgnore
    public boolean isSuccess(){
        return this.status == Const.SUCCESS_CODE;
    }

//    调用成功
    public static ServerResponse createServerResponseBySuccess(){
        return new ServerResponse(Const.SUCCESS_CODE);
    }

    public static ServerResponse createServerResponseBySuccess(String msg){
        return new ServerResponse(Const.SUCCESS_CODE,msg);
    }

    public static  <T> ServerResponse createServerResponseBySuccess(T data){
        return new ServerResponse(Const.SUCCESS_CODE,data);
    }

    public static <T> ServerResponse createServerResponseBySuccess(String msg,T data){
        return new ServerResponse(Const.SUCCESS_CODE,data,msg);
    }
//    调用失败
    public static ServerResponse createServerResponseByError(){
        return new ServerResponse(Const.ERROR_CODE);
    }

    public static ServerResponse createServerResponseByError(int status){
        return new ServerResponse(status);
    }

    public static ServerResponse createServerResponseByError(int status,String msg){
        return new ServerResponse(status,msg);
    }
}
