package com.example.entity;

public class Params {
    private  String name;
    private  Integer pageNum;
    private  Integer pageSize;
    private  Integer isshoucang;



    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getIsshoucang() {
        return isshoucang;
    }

    public void setIsshoucang(Integer isshoucang) {
        this.isshoucang = isshoucang;
    }
}
