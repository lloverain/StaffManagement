package modular.api;

/**
 * 返回格式
 *
 * @author 杨佳颖
 */
public class ResponseResult<T> {
    /**
     * 返回状态码200成功
     */
    public int code;
    /**
     * 返回描述信息
     */
    private String msg;
    /**
     * 返回内容体
     */
    private T data;

    public ResponseResult<T> setCode(ResultCode retCode) {
        this.code = retCode.code;
        return this;
    }

    public int getCode() {
        return code;
    }

    public ResponseResult<T> setCode(int code) {
        this.code = code;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public ResponseResult<T> setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public T getData() {
        return data;
    }

    public ResponseResult<T> setData(T data) {
        this.data = data;
        return this;
    }

}