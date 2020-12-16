package com.shangma.cn.dto;

/**
 * @author luozuishuai
 * @Created on 2020-12-15 11:58
 */
public class SearchPageDto {
    private int firstTypeId;
    private int secondTypeId;
    private String search;

    public SearchPageDto() {
    }

    public SearchPageDto(int firstTypeId, int secondTypeId, String search) {

        this.firstTypeId = firstTypeId;
        this.secondTypeId = secondTypeId;
        this.search = search;
    }

    public int getFirstTypeId() {

        return firstTypeId;
    }

    public void setFirstTypeId(int firstTypeId) {
        this.firstTypeId = firstTypeId;
    }

    public int getSecondTypeId() {
        return secondTypeId;
    }

    public void setSecondTypeId(int secondTypeId) {
        this.secondTypeId = secondTypeId;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }
}
