package gestionBar.view;

import gestionBar.model.entities.Dish;
import gestionBar.model.entities.Ingredient;
import gestionBar.model.entities.Product;
import gestionBar.model.entities.Wine;

import java.util.ArrayList;
import java.util.Vector;

public interface ViewModelLayer
{
    void PromptForLogin();
    Product PromptForNewProduct();
    Product PromptForUpdateProduct(Product p);

    void displayProducts();

    void showInfoMessage(String title, String message);

    ArrayList<Wine> getWines();
    ArrayList<Dish> getDishes();
    ArrayList<Ingredient> getIngredients();

    void quit();
}
