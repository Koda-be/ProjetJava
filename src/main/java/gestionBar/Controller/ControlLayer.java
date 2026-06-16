package gestionBar.Controller;

import gestionBar.model.entities.Dish;
import gestionBar.model.entities.Ingredient;
import gestionBar.model.entities.Product;
import gestionBar.model.entities.Wine;

import java.util.ArrayList;

public interface ControlLayer
{
    void exit();
    void changeLogin();

    void createProduct();
    ArrayList<Wine> readWines();
    ArrayList<Dish> readDishes();
    ArrayList<Ingredient> readIngredients();
    void updateProduct(Product oldProduct);
    void deleteProduct(Product p);
}
