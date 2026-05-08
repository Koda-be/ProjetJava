package gestionBar.view.GUI.ProductWindow;

import gestionBar.model.entities.Dish;
import gestionBar.model.entities.Ingredient;
import gestionBar.model.entities.Product;
import gestionBar.model.entities.Wine;
import gestionBar.model.exceptions.EWrongTypeUsed;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Vector;

public class ProductWindow extends JDialog implements ProductEditor
{
    private Product p;
    private ImageIcon img;
    private Vector<Ingredient> ing;

    private JPanel mainPanel;
    private JButton chooseImageButton;
    private JPanel imagePanel;
    private JPanel fieldPanel;
    private JPanel currentEditor;
    private JPanel classPanel;
    private JLabel imageLabel;
    private JRadioButton wineRadioButton;
    private JRadioButton dishRadioButton;
    private JRadioButton ingredientRadioButton;
    private JButton okButton;
    private JButton cancelButton;

    private JPanel currEditor;
    private

    final static String WINE = "Wine";
    final static String DISH = "Dish";
    final static String INGREDIENT = "Ingredient";


    public ProductWindow(Vector<Ingredient> ingredients)
    {
        super((Frame) null,"Add product", true);
        setMinimumSize(new Dimension(600, 400));
        ing = ingredients;
        img = new ImageIcon("src/ressources/images/Default.png");
        imageLabel.setIcon(img);

        wineRadioButton.setText(WINE);
        dishRadioButton.setText(DISH);
        ingredientRadioButton.setText(INGREDIENT);

        fieldPanel.setLayout(new CardLayout());

        fieldPanel.

        ItemListener radioListener = new ItemListener()
        {
            @Override
            public void itemStateChanged(ItemEvent e)
            {
//                if(e.getSource() == wineRadioButton)
//                {
//                    //fieldPanel = new JPanel();
//                    fieldPanel.removeAll();
//                    fieldPanel.setLayout(new GridLayout());
//                    fieldPanel.add(new WinePanel());
//                }
//                else if(e.getSource() == dishRadioButton)
//                {
//                    //fieldPanel = new JPanel();
//                    fieldPanel.removeAll();
//                    fieldPanel.setLayout(new GridLayout());
//                    // for(Component c : fieldPanel.getComponents()) if(c instanceof ProductEditor) fieldPanel.remove(c);
//                    fieldPanel.add(new DishPanel(ing));
//                }
//                else if(e.getSource() == ingredientRadioButton)
//                {
//                    //fieldPanel = new JPanel();
//                    fieldPanel.removeAll();
//                    fieldPanel.setLayout(new GridLayout());
//                    // for(Component c : fieldPanel.getComponents()) if(c instanceof ProductEditor) fieldPanel.remove(c);
//                    fieldPanel.add(new IngredientPanel());
//                }

                CardLayout cl = fieldPanel.getLayout();

                fieldPanel.revalidate();
                fieldPanel.repaint();
            }
        };

        wineRadioButton.addItemListener(radioListener);
        dishRadioButton.addItemListener(radioListener);
        ingredientRadioButton.addItemListener(radioListener);

        okButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent actionEvent)
            {
                p = ((ProductEditor) fieldPanel.getComponent(0)).getProduct();

                if(p != null) p.setImage(img);;

                setVisible(false);
            }
        });

        cancelButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent actionEvent)
            {
                p = null;
                setVisible(false);
            }
        });

        addWindowListener(new WindowListener()
        {
            @Override
            public void windowOpened(WindowEvent windowEvent)
            {

            }

            @Override
            public void windowClosing(WindowEvent windowEvent)
            {
                System.exit(1);
            }

            @Override
            public void windowClosed(WindowEvent windowEvent)
            {

            }

            @Override
            public void windowIconified(WindowEvent windowEvent)
            {

            }

            @Override
            public void windowDeiconified(WindowEvent windowEvent)
            {

            }

            @Override
            public void windowActivated(WindowEvent windowEvent)
            {

            }

            @Override
            public void windowDeactivated(WindowEvent windowEvent)
            {

            }
        });
        setContentPane(mainPanel);
    }

    public Product getProduct()
    {
        return p;
    }

    @Override
    public void setProduct(Product prod)
    {
        setTitle("Modify product");
        img = p.getImage();
        imageLabel.setIcon(img);

        classPanel = new JPanel();

        ProductEditor pane;

        if(prod instanceof Wine)
        {
            pane = new WinePanel();
            p = new Wine(prod.getName(), prod.getImage(), prod.getBuyPrice(), ((Wine) prod).getSellPrice(), prod.getQuantity(), ((Wine) prod).getCepage(), ((Wine) prod).getColour(), ((Wine) prod).getMillesime());
        }
        else if(prod instanceof Dish)
        {
            pane = new DishPanel(ing);
            p = new Dish(prod.getName(), prod.getImage(), ((Dish) prod).getSellPrice(), ((Dish) prod).getIngredients());
        }
        else if(prod instanceof Ingredient)
        {
            pane = new IngredientPanel();
            p = new Ingredient(prod.getName(), prod.getImage(), prod.getBuyPrice(), prod.getQuantity(), ((Ingredient) prod).getExpirationDate());
        }
        else
        {
            Vector<java.lang.reflect.Type> ev = new Vector<>();
            ev.add(Wine.class);
            ev.add(Dish.class);
            ev.add(Ingredient.class);
            throw new EWrongTypeUsed("ProductTableModel.getColumnCount()", prod.getClass(), ev);
        }

        pane.setProduct(p);

        fieldPanel = new JPanel();
        fieldPanel.add((JPanel) pane);
        fieldPanel.revalidate();
    }

    public static void main(String[] args)
    {
        Vector<Ingredient> ingredients = new Vector<>(3);
        ingredients.add(new Ingredient("ing1", null, 10, 5, LocalDate.now().plusMonths(2)));
        ingredients.add(new Ingredient("ing2", new ImageIcon("src/ressources/images/Default"), 20, 3, LocalDate.now().plusYears(1)));
        ingredients.add(new Ingredient("ing3", null, 30, 4, LocalDate.now().plusDays(5)));

        ProductWindow wAdd = new ProductWindow(ingredients);

        wAdd.setVisible(true);

        Product p;
        while((p = wAdd.getProduct()) == null)
        {
            JOptionPane.showMessageDialog(null, "Invalid field value entered", "Invalid value", JOptionPane.ERROR_MESSAGE);
            wAdd.setVisible(true);
        }

        wAdd.dispose();

        System.out.println("New product: " + p);

        ProductWindow wMod = new ProductWindow(ingredients);
        wMod.setProduct(p);

        Product pTemp;

        wMod.setVisible(true);

        while((pTemp = wMod.getProduct()) == null)
        {
            JOptionPane.showMessageDialog(null, "Invalid field value entered", "Invalid value", JOptionPane.ERROR_MESSAGE);
            wMod.setProduct(p);
            wMod.setVisible(true);
        }

        p = pTemp;

        wMod.dispose();

        System.out.println("Modified product: " + p);
    }
}

