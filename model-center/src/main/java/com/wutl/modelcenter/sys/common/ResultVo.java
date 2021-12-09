package com.wutl.modelcenter.sys.common;

/**
 * @author Wutl
 * @version V1.0.0
 * @Description:
 * @date: 2021/11/12 9:28
 */
public class ResultVo {
    private int code;
    private String message;
    private Object data;
    private boolean success;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public ResultVo(int code, String message, Object data, boolean success) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.success = success;
    }

    public ResultVo() {
    }

    public static ResultVo insResultVo(int code, String message, Object data, boolean success) {
        return new ResultVo(code, message, data, success);
    }


    public static ResultVo returnError(String message) {
        return returnError(message, null);
    }

    public static ResultVo returnError(String message, Object data) {
        ResultVo resultVo = insResultVo(0, message, data, false);
        return resultVo;
    }

    public static ResultVo returnError() {
        return returnError("操作失败");
    }

    public static ResultVo returnError(Exception e) {
        return returnError("操作失败:" + e.getMessage());
    }

    public static ResultVo returnSuccess() {
        return returnSuccess("操作成功");
    }

    public static ResultVo returnSuccess(String message) {
        return returnSuccess(message, null);
    }

    public static ResultVo returnSuccess(String message, Object data) {
        return insResultVo(1, message, data, true);
    }

    public static ResultVo returnSuccess(Object data) {
        return insResultVo(1, "操作成功", data, true);
    }

}
