package gestionBar.view.GUI.ViewModel;

import gestionBar.Controller.ControlLayer;
import gestionBar.model.accessors.Loggers.Logger;
import gestionBar.model.accessors.authenticator.MapAuthenticator;
import gestionBar.model.entities.*;
import gestionBar.view.GUI.Login.LoginWindow;
import gestionBar.view.GUI.MainWindow.MainWindow;
import gestionBar.view.GUI.ProductWindow.ProductWindow;
import gestionBar.view.ViewModelLayer;

import javax.swing.*;
import java.time.LocalDate;
import java.util.Vector;

public class ViewTestModel implements ViewModelLayer
{
    private Logger logger;

    private Vector<Wine> wines;
    private Vector<Dish> dishes;
    private Vector<Ingredient> ingredients;

    private MainWindow mainWindow;

    public ViewTestModel()
    {
        logger = new Logger(null, new MapAuthenticator());

        wines = new Vector<>(3);
        dishes = new Vector<>(3);
        ingredients = new Vector<>(3);

        wines.add(new Wine("Wine1", null, 10f, 20f, 3, "Chardonnay", Colour.White, 2000));
        wines.add(new Wine("Wine2", new ImageIcon("src/ressources/images/Default.png"), 20f, 20f, 3, "Pommard", Colour.Red, 1970));

        ingredients.add(new Ingredient("ing1", null, 10, 5, LocalDate.now().plusMonths(2)));
        ingredients.add(new Ingredient("ing2", new ImageIcon("src/ressources/images/Default"), 20, 3, LocalDate.now().plusYears(1)));
        ingredients.add(new Ingredient("ing3", null, 30, 4, LocalDate.now().plusDays(5)));

        if(ingredients.clone() instanceof Vector<?>) dishes.add(new Dish("dish1", null, 100, (Vector<Ingredient>) ingredients.clone()));
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
    public Vector<Wine> getWines()
    {
        return wines;
    }

    @Override
    public Vector<Dish> getDishes()
    {
        return dishes;
    }

    @Override
    public Vector<Ingredient> getIngredients()
    {
        return ingredients;
    }

    @Override
    public void quit()
    {
        System.exit(0);
    }
}
