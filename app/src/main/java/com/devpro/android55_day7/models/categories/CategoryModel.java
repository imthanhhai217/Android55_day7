package com.devpro.android55_day7.models.categories;

import java.util.ArrayList;

public class CategoryModel {
    private String categoryName;
    private int categorySize;
    private ArrayList<String> productDemo;
    private String url;

    public CategoryModel(String categoryName, int categorySize, ArrayList<String> productDemo, String url) {
        this.categoryName = categoryName;
        this.categorySize = categorySize;
        this.productDemo = productDemo;
        this.url = url;
    }

    public CategoryModel() {
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getCategorySize() {
        return categorySize;
    }

    public void setCategorySize(int categorySize) {
        this.categorySize = categorySize;
    }

    public ArrayList<String> getProductDemo() {
        return productDemo;
    }

    public void setProductDemo(ArrayList<String> productDemo) {
        this.productDemo = productDemo;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "CategoryModel{" +
                "categoryName='" + categoryName + '\'' +
                ", categorySize=" + categorySize +
                ", productDemo=" + productDemo +
                ", url='" + url + '\'' +
                '}';
    }
}
