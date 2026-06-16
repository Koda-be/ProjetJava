package gestionBar.model.accessors;

import gestionBar.model.accessors.authenticator.Authenticator;
import gestionBar.model.entities.Dish;
import gestionBar.model.entities.Ingredient;
import gestionBar.model.entities.Product;
import gestionBar.model.entities.Wine;

import java.util.ArrayList;

public interface DALayer
{
    void createProduct(Product product);
    ArrayList<Wine> readWines();
    ArrayList<Dish> readDishes();
    ArrayList<Ingredient> readIngredients();

    void updateProduct(Product oldProduct, Product newProduct);
    void deleteProduct(Product product);

    Authenticator getAuthenticator();
    void setupData(String user);

    void save();
}
