package gestionBar.view;

import gestionBar.model.entities.Product;

public interface ViewLayer
{
    String[] PromptForLogin();
    Product PromptForNewProduct();
    Product PromptForUpdateProduct(Product p);

    void displayProducts();
    void showInfoMessage(String title, String message);
    void showErrorMessage(String title, String message);
}
