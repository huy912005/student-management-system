package com.JavaSpringBoot.BESpring.DTO.Response.page;

public class Meta {
    private int page;// Trang hiện tại
    private int size;// Số lượng lấy trên 1 trang
    private long total;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }
}
