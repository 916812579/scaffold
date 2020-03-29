package org.example.response;

import lombok.Data;

@Data
public class Result {

    /**
     * 状态
     */
    private int status;

    /**
     * 返回的数据
     */
    private Object data;

    /**
     * 返回的信息
     */
    private String msg;

    public Result(Object data) {
        this.data = data;
    }

    public Result(int status, String msg) {
        this.status = status;
        this.msg = msg;
    }
}
