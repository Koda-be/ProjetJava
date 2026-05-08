package gestionBar.Controller;

import gestionBar.model.entities.Dish;
import gestionBar.model.entities.Ingredient;
import gestionBar.model.entities.Wine;

import java.util.Vector;

public interface ControlLayer
{
    void ChangeLogin();
    Vector<Wine> getWines();
    Vector<Dish> getDishes();
    Vector<Ingredient> getIngredients();
}
