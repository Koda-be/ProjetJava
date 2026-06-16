package gestionBar.view.GUI.ViewModel;

import gestionBar.Controller.ControlLayer;
import gestionBar.model.accessors.Loggers.Logger;
import gestionBar.model.entities.Dish;
import gestionBar.model.entities.Ingredient;
import gestionBar.model.entities.Product;
import gestionBar.model.entities.Wine;
import gestionBar.view.GUI.Login.LoginWindow;

import gestionBar.view.GUI.MainWindow.MainWindow;
import gestionBar.view.ViewLayer;
import gestionBar.view.ViewModelLayer;

import javax.swing.*;
import java.util.ArrayList;

public class ViewModel implements ViewModelLayer, ViewLayer
{
    private ControlLayer controller;
    private MainWindow mainWindow;

    public ViewModel(ControlLayer c)
    {
        controller = c;
    }

    @Override
    public String[] PromptForLogin()
    {
        LoginWindow loginWindow = new LoginWindow("Authenticator", true);
        loginWindow.setVisible(true);
        String[] credentials = loginWindow.getCredentials();
        loginWindow.dispose();

        return credentials;
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
        if(mainWindow != null)
        {
            mainWindow.setVisible(false);
            mainWindow.dispose();
        }

        mainWindow = new MainWindow(this);
        mainWindow.setVisible(true);
    }

    @Override
    public void showInfoMessage(String title, String message)
    {
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void showErrorMessage(String title, String message)
    {
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.ERROR_MESSAGE);
    }


    public void changeLogin()
    {
        controller.changeLogin();
    }

    public void exit()
    {
        controller.exit();
    }

    public void createProduct()
    {
        controller.createProduct();
    }

    public void updateProduct(Product p)
    {
        controller.updateProduct(p);
    }

    public void deleteProduct(Product p)
    {
        controller.deleteProduct(p);
    }

    @Override
    public ArrayList<Wine> getWines()
    {
        return null;
    }

    @Override
    public ArrayList<Dish> getDishes()
    {
        return null;
    }

    @Override
    public ArrayList<Ingredient> getIngredients()
    {
        return null;
    }
}
