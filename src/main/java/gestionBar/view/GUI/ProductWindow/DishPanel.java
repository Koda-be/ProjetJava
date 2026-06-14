package gestionBar.view.GUI.ProductWindow;

import gestionBar.model.entities.Dish;
import gestionBar.model.entities.Ingredient;
import gestionBar.model.entities.Product;
import gestionBar.model.exceptions.EWrongTypeUsed;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Vector;

import static java.lang.Boolean.valueOf;

public class DishPanel implements ProductEditor
{
    private JPanel panel1;
    private JTextField nameField;
    private JSpinner sellPriceSpinner;
    private JTable ingTable;

    public DishPanel(ArrayList<Ingredient> v)
    {
        sellPriceSpinner.setModel(new SpinnerNumberModel(20f, 0, 1000000, 1));
        ingTable.setModel(new IngredientTableModel(v));
    }

    public JPanel getPanel()
    { return panel1; }

    @Override
    public Product getProduct()
    {
        try
        {
            sellPriceSpinner.commitEdit();
        }
        catch (ParseException e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Parse error", JOptionPane.ERROR_MESSAGE);
        }

        Double newSp = (Double) sellPriceSpinner.getValue();
        Ingredient newIng = ((IngredientTableModel) ingTable.getModel()).getIngredient();
        if(newSp < newIng.getBuyPrice()) newSp = newIng.getBuyPrice();

        return new Dish(nameField.getText(), null, newSp, newIng);
    }

    @Override
    public void setProduct(Product p)
    {
        if(!(p instanceof Dish))
        {
            Vector<Type> v = new Vector<>(1);
            v.add(Dish.class);
            throw new EWrongTypeUsed("DishPanel.setProduct()", p.getClass(), v);
        }

        Dish d = (Dish) p;

        nameField.setText(d.getName());
        sellPriceSpinner.setValue(d.getSellPrice());

        ((IngredientTableModel) ingTable.getModel()).setIngredient((d.getIngredient()));
    }
}

class IngredientTableModel extends AbstractTableModel
{
    private ArrayList<Ingredient> ingredients;
    private ArrayList<Boolean> booleans;
    private Dish dish;

    public IngredientTableModel(ArrayList<Ingredient> v)
    {
        ingredients = v;
        booleans = new ArrayList<>(ingredients.size());

        for(int i = 0; i < ingredients.size(); i++)
        {
            booleans.add(Boolean.FALSE);
        }
    }

    public IngredientTableModel(ArrayList<Ingredient> v, Dish d)
    {
        ingredients = v;
        booleans = new ArrayList<>();
        dish = d;

        for (Ingredient ingredient : ingredients)
        {
            booleans.add(d.getIngredient().equals(ingredient));
        }
    }

    @Override
    public Class getColumnClass(int c)
    {
        if (c == 0) return Boolean.class;
        if (c == 1) return String.class;
        return null;
    }

    @Override
    public int getRowCount() { return ingredients.size(); }

    @Override
    public int getColumnCount() { return 2; }

    @Override
    public Object getValueAt(int l,int c)
    {
        if(c < 3 && l < booleans.size())
        {
            if(c == 0) return booleans.get(l);

            else return ingredients.get(l).getName();
        }

        return null;
    }

    @Override
    public void setValueAt(Object value,int l,int c) {
        for(int i = 0; i < booleans.toArray().length; i++)
        {
            booleans.set(i, i == l);
        }


    }

    @Override
    public boolean isCellEditable(int row,int column) {
        return (column == 0);
    }

    public Ingredient getIngredientAt(int index) {
        return ingredients.get(index);
    }

    public Ingredient getIngredient()
    {
        Ingredient ing = null;
        for(int i = 0; i < ingredients.size() && ing == null; i++) if(booleans.get(i)) ing = ingredients.get(i);

        return ing;
    }

    public void setIngredient(Ingredient ing)
    {
        for(int i = 0; i < ingredients.size(); i++) booleans.set(i, ing.equals(ingredients.get(i)));
    }
}