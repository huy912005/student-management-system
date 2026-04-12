package com.JavaSpringBoot.BESpring.DTO.Response.page;

import java.util.List;

public class PageResponse<T>{
    private List<T> data;
    private Meta meta;

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }
}
