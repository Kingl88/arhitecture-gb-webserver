package ru.gb.mvc;

public class ProductModel {
    private String title;
    private Double cost;

    public ProductModel(String title, Double cost) {
        this.title = title;
        this.cost = cost;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "ProductModel{" +
                "title='" + title + '\'' +
                ", cost=" + cost +
                '}';
    }
}
