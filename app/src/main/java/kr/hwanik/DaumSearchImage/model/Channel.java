package kr.hwanik.DaumSearchImage.model;

import java.util.List;

public class Channel {
    private String result;
    private String pageCount;
    private String title;
    private int totalCount;
    private List<Item> item;

    public String getDescription() {
        return description;
    }

    public String description;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getPageCount() {
        return pageCount;
    }

    public void setPageCount(String pageCount) {
        this.pageCount = pageCount;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<Item> getItem() {
        return item;
    }
}

