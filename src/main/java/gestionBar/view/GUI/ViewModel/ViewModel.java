package gestionBar.view.GUI.ViewModel;

import gestionBar.Controller.ControlLayer;
import gestionBar.model.accessors.Loggers.Logger;
import gestionBar.model.entities.Dish;
import gestionBar.model.entities.Ingredient;
import gestionBar.model.entities.Product;
import gestionBar.model.entities.Wine;
import gestionBar.view.GUI.Login.LoginWindow;

import gestionBar.view.GUI.MainWindow.MainWindow;
import gestionBar.view.ViewModelLayer;

import javax.swing.*;
import java.util.Vector;

public class ViewModel implements ViewModelLayer
{
    private ControlLayer c;
    private Logger logger;

    private MainWindow mainWindow;

    public ViewModel(Logger l)
    {
        logger = l;
    }

    @Override
    public void PromptForLogin()
    {
        LoginWindow loginWindow = new LoginWindow("Authenticator", true, logger);
        loginWindow.setVisible(true);
        loginWindow.dispose();

        if(!(logger.getLoginState()))
        {
            JOptionPane.showMessageDialog(null, "Login cancelled, quitting", "Login cancelled", JOptionPane.INFORMATION_MESSAGE);
            System.out.println("Login cancelled");
            System.exit(1);
        }
    }

    @Override
    public Product PromptForNewProduct()
    {
        return null;
    }

    @Override
    public Product PromptForUpdateProduct(Product p)
    {
        return null;
    }

    @Override
    public void displayProducts()
    {
        mainWindow = new MainWindow(this);
    }

    @Override
    public void showInfoMessage(String title, String message)
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

    @Override
    public void quit()
    {
        System.exit(0);
    }
}
