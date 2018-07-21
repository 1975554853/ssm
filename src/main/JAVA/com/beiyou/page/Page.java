package com.beiyou.page;

import com.github.pagehelper.PageInfo;

public class Page {
    private Integer code;
    private String msg;
    private long count;
    private Object data;

    public Page(PageInfo pageInfo, Integer code){
        this.data = pageInfo.getList();
        this.count = pageInfo.getTotal();
        this.code = code;
    }
public Page(){

}

    public Page(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Page(Integer code,Object data) {
        this.code = code;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
