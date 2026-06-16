package gestionBar.view;

import gestionBar.model.entities.Dish;
import gestionBar.model.entities.Ingredient;
import gestionBar.model.entities.Product;
import gestionBar.model.entities.Wine;

import java.util.ArrayList;

public interface ViewModelLayer
{
    void changeLogin();
    void exit();

    void createProduct();
    void updateProduct(Product p);
    void deleteProduct(Product p);

    ArrayList<Wine> getWines();
    ArrayList<Dish> getDishes();
    ArrayList<Ingredient> getIngredients();
}
