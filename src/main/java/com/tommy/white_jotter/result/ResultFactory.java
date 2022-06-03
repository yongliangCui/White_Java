package com.tommy.white_jotter.result;

public class ResultFactory {
//  正常创建返回信息
    public static Result buildResult(int resultCode,String message,Object data){
        return new Result(resultCode,message,data);
    }
//  创建成功返回信息
    public static Result buildSuccessResult(Object data){
        return buildResult(ResultCode.SUCCESS.code,"成功",data);
    }
//  创建失败返回信息
    public static Result buildFailResult(String message){
        return buildResult(ResultCode.FAIL.code,message,null);
    }
}
