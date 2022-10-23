package ru.gb.mvc;

public class ProductController {
    private final ProductModel model;
    private final ProductView view;

    public ProductController(ProductModel model, ProductView view) {
        this.model = model;
        this.view = view;
    }
    public String getTitle() {
        return model.getTitle();
    }

    public void setTitle(String title) {
        model.setTitle(title);
    }

    public Double getCost() {
        return model.getCost();
    }

    public void setCost(Double cost) {
        model.setCost(cost);
    }

    public void updateView(){
        view.displayModel(model);
    }
}
