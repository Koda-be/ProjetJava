package gestionBar.view.GUI.ViewModel;

import gestionBar.model.accessors.Loggers.Logger;
import gestionBar.model.accessors.authenticator.MapAuthenticator;
import gestionBar.model.entities.*;
import gestionBar.view.GUI.Login.LoginWindow;
import gestionBar.view.GUI.MainWindow.MainWindow;
import gestionBar.view.GUI.ProductWindow.ProductWindow;
import gestionBar.view.ViewLayer;
import gestionBar.view.ViewModelLayer;

import javax.swing.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class ViewTestModel implements ViewModelLayer, ViewLayer
{
    private Logger logger;

    private ArrayList<Wine> wines;
    private ArrayList<Dish> dishes;
    private ArrayList<Ingredient> ingredients;

    private MainWindow mainWindow;

    public ViewTestModel()
    {
        logger = new Logger(null, new MapAuthenticator());

        wines = new ArrayList<>(3);
        dishes = new ArrayList<>(3);
        ingredients = new ArrayList<>(3);

        wines.add(new Wine("Wine1", null, 10f, 20f, 3, "Chardonnay", Colour.White, 2000));
        wines.add(new Wine("Wine2", new ImageIcon("src/ressources/images/Default.png"), 20f, 20f, 3, "Pommard", Colour.Red, 1970));

        ingredients.add(new Ingredient("ing1", null, 10, 5, LocalDate.now().plusMonths(2)));
        ingredients.add(new Ingredient("ing2", new ImageIcon("src/ressources/images/Default"), 20, 3, LocalDate.now().plusYears(1)));
        ingredients.add(new Ingredient("ing3", null, 30, 4, LocalDate.now().plusDays(5)));

        dishes.add(new Dish("dish1", null, 100,  ingredients.get(0)));
        dishes.add(new Dish("dish2", null, 120,  ingredients.get(1)));
        dishes.add(new Dish("dish3", null, 300,  ingredients.get(1)));
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
        ProductWindow w = new ProductWindow(getIngredients());

        Product p;
        while((p = w.getProduct()) == null) JOptionPane.showMessageDialog(null, "Invalid field value entered", "Invalid value", JOptionPane.ERROR_MESSAGE);



        return p;
    }

    @Override
    public Product PromptForUpdateProduct(Product p)
    {
        ProductWindow w = new ProductWindow(getIngredients());

        Product pTemp = null;

        while((pTemp = w.getProduct()) == null)
        {
            JOptionPane.showMessageDialog(null, "Invalid field value entered", "Invalid value", JOptionPane.ERROR_MESSAGE);
            w.setProduct(p);
        }

        p = pTemp;

        w.dispose();

        return p;
    }

    @Override
    public void displayProducts()
    {
        mainWindow = new MainWindow(this);
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
        PromptForLogin();
    }

    public void exit()
    {
        System.exit(0);
    }

    public void createProduct()
    {
        Product p = PromptForNewProduct();
        System.out.println(p);
    }

    public void updateProduct(Product p)
    {
        System.out.println(PromptForUpdateProduct(p));
    }

    public void deleteProduct(Product p)
    {
        showInfoMessage("Delete Product", "Product has been deleted: " + p);
    }

    @Override
    public ArrayList<Wine> getWines()
    {
        return wines;
    }

    @Override
    public ArrayList<Dish> getDishes()
    {
        return dishes;
    }

    @Override
    public ArrayList<Ingredient> getIngredients()
    {
        return ingredients;
    }


}
