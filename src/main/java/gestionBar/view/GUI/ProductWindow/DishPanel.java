package gestionBar.view.GUI.ProductWindow;

import gestionBar.model.entities.Dish;
import gestionBar.model.entities.Ingredient;
import gestionBar.model.entities.Product;
import gestionBar.model.exceptions.EWrongTypeUsed;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.util.Vector;

public class DishPanel extends JPanel implements ProductEditor
{
    private JPanel panel1;
    private JTextField nameField;
    private JSpinner sellPriceSpinner;
    private JTable ingTable;

    public DishPanel(Vector<Ingredient> v)
    {
        sellPriceSpinner.setModel(new SpinnerNumberModel(20f, 0, 1000000, 1));
        ingTable.setModel(new IngredientTableModel(v));
    }

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

        return new Dish(nameField.getText(), null, (Float) sellPriceSpinner.getValue(), ((IngredientTableModel) ingTable.getModel()).getIngredients());
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

        ((IngredientTableModel) ingTable.getModel()).setIngredients((d.getIngredients()));
    }
}

class IngredientTableModel extends AbstractTableModel
{
    private Vector<Ingredient> ingredients;
    private Vector<JCheckBox> checkBoxes;

    public IngredientTableModel(Vector<Ingredient> v)
    {
        ingredients = v;
        checkBoxes = new Vector<>(ingredients.size());

        for(int i = 0; i < ingredients.size(); i++)
        {
            JCheckBox jcb = new JCheckBox();
            jcb.setSelected(false);
            checkBoxes.add(jcb);
        }
    }

    public IngredientTableModel(Vector<Ingredient> v, Dish d)
    {
        ingredients = v;
        checkBoxes = new Vector<>(ingredients.size());

        for (Ingredient ingredient : ingredients)
        {
            JCheckBox jcb = new JCheckBox();
            jcb.setSelected(d.getIngredients().contains(ingredient));
            checkBoxes.add(jcb);
        }
    }

    @Override
    public Class getColumnClass(int c)
    {
        if (c == 0) return JCheckBox.class;
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
        if(c < 3 && l < checkBoxes.size())
        {
            if(c == 0) return checkBoxes.get(l);

            else return ingredients.get(l).getName();
        }

        return null;
    }

    @Override
    public void setValueAt(Object value,int l,int c) {
    }

    @Override
    public boolean isCellEditable(int row,int column) {
        return (row == 0);
    }

    public Ingredient getIngredientAt(int index) {
        return ingredients.get(index);
    }

    public Vector<Ingredient> getIngredients()
    {
        Vector<Ingredient> v = new Vector<>();
        for(int i = 0; i < ingredients.size(); i++) if(checkBoxes.get(i).isSelected()) v.add(ingredients.get(i));

        return v;
    }

    public void setIngredients(Vector<Ingredient> v)
    {
        for(int i = 0; i < ingredients.size(); i++) checkBoxes.get(i).setSelected(v.contains(ingredients.get(i)));
    }
}