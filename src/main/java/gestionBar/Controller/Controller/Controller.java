package gestionBar.Controller.Controller;

import gestionBar.Controller.ControlLayer;
import gestionBar.model.accessors.Loggers.Logger;
import gestionBar.model.entities.Dish;
import gestionBar.model.entities.Ingredient;
import gestionBar.model.entities.Wine;

import java.util.Vector;

public class Controller implements ControlLayer
{
    private Logger logger;
    public void ChangeLogin()
    {

    }

    @Override
    public Vector<Wine> getWines()
    {
        return null;
    }

    @Override
    public Vector<Dish> getDishes()
    {
        return null;
    }

    @Override
    public Vector<Ingredient> getIngredients()
    {
        return null;
    }
}
