package gestionBar.Controller.Controller;

import gestionBar.Controller.ControlLayer;
import gestionBar.model.accessors.DALayer;
import gestionBar.model.accessors.authenticator.Authenticator;
import gestionBar.model.entities.Dish;
import gestionBar.model.entities.Ingredient;
import gestionBar.model.entities.Product;
import gestionBar.model.entities.Wine;
import gestionBar.view.ViewLayer;

import java.util.ArrayList;

public class Controller implements ControlLayer
{
    private Authenticator authenticator;
    private ViewLayer view;
    private DALayer model;
    private String currentUser;

    public Controller(ViewLayer v, DALayer m)
    {
        view = v;
        model = m;
        currentUser = null;

        authenticator = model.getAuthenticator();
    }

    public void start()
    {
        model.save();
        String[] credentials;

        while(true)
        {
            credentials = view.PromptForLogin();

            if(authenticator.authenticate(credentials[0], credentials[1]))
            {
                currentUser = credentials[0];
                break;
            }

            if(credentials[0] == null)
            {
                view.showInfoMessage("Login cancelled", "Login cancelled");
                if(currentUser == null)
                {
                    view.showInfoMessage("Login cancelled", "quitting");
                    System.exit(0);
                }

                break;
            }
            else view.showErrorMessage("Login error", "Incorrect username and/or password");
        }

        model.setupData(currentUser);
    }


    @Override
    public void exit()
    {
        model.save();
        System.exit(0);
    }

    @Override
    public void changeLogin()
    {
        model.save();
        view.PromptForLogin();
    }

    @Override
    public void createProduct()
    {
        Product product;
        if((product = view.PromptForNewProduct()) == null)
        {
            view.showInfoMessage("Error", "Invalid input");
            return;
        }

        model.createProduct(product);
    }

    @Override
    public ArrayList<Wine> readWines()
    {
        return model.readWines();
    }

    @Override
    public ArrayList<Dish> readDishes()
    {
        return model.readDishes();
    }

    @Override
    public ArrayList<Ingredient> readIngredients()
    {
        return model.readIngredients();
    }

    @Override
    public void updateProduct(Product oldProduct)
    {
        Product newProduct;

        if((newProduct = view.PromptForUpdateProduct(oldProduct)) == null)
        {
            view.showInfoMessage("Error", "Invalid input");
            return;
        }

        model.updateProduct(oldProduct, newProduct);
        view.displayProducts();
    }

    @Override
    public void deleteProduct(Product p)
    {
        model.deleteProduct(p);
        view.displayProducts();
    }
}
