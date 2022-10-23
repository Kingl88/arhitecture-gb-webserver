package ru.gb.mvc;

public class Main {
    public static void main(String[] args) {
        ProductModel model = new ProductModel("Milk", 10.8);
        ProductView view = new ProductView();
        ProductController controller = new ProductController(model, view);
        controller.updateView();
        controller.setCost(controller.getCost() + 10);
        controller.setTitle("Chocolate");
        controller.updateView();
    }
}
