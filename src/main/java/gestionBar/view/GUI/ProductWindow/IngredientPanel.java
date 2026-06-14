package gestionBar.view.GUI.ProductWindow;

import gestionBar.model.entities.Ingredient;
import gestionBar.model.entities.Product;
import gestionBar.model.exceptions.EWrongTypeUsed;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.Month;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

public class IngredientPanel implements ProductEditor
{
    private JTextField nameField;
    private JSpinner quantitySpinner;
    private JSpinner buyPriceSpinner;
    private JSpinner daySpinner;
    private JComboBox monthComboBox;
    private JSpinner yearSpinner;
    private JPanel panel1;

    public IngredientPanel()
    {
        buyPriceSpinner.setModel(new SpinnerNumberModel(10f, 0f, null, 1f));
        quantitySpinner.setModel(new SpinnerNumberModel(0, 0, null, 1));

        LocalDate nowDate = LocalDate.now();
        LocalDate defaultDate = LocalDate.now().plusMonths(2);

        daySpinner.setModel(new SpinnerNumberModel(defaultDate.getDayOfMonth(), 1, defaultDate.lengthOfMonth(), 1));
        monthComboBox.setModel(new DefaultComboBoxModel<>(Month.values()));
        monthComboBox.addItemListener(new ItemListener()
        {
            @Override
            public void itemStateChanged(ItemEvent itemEvent)
            {
                if(monthComboBox.getSelectedItem() == null) daySpinner.setModel(new SpinnerNumberModel((Number) daySpinner.getValue(), 1, LocalDate.of(1, (Month) monthComboBox.getSelectedItem(), (Integer) yearSpinner.getValue()).lengthOfMonth(),1));
            }
        });

        yearSpinner.setModel(new SpinnerNumberModel(nowDate.getYear(), nowDate.getYear(), nowDate.plusYears(10).getYear(), 1));
    }

    public JPanel getPanel()
    { return panel1; }

    @Override
    public Product getProduct()
    {
        try
        {
            buyPriceSpinner.commitEdit();
            quantitySpinner.commitEdit();
        }
        catch (ParseException e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Parse error", JOptionPane.ERROR_MESSAGE);
        }

        if(buyPriceSpinner.getValue().equals(0) || monthComboBox.getSelectedItem() == null ) return null;

        return new Ingredient(nameField.getText(), null, (Float) buyPriceSpinner.getValue(), (Integer) quantitySpinner.getValue(), LocalDate.of((Integer) yearSpinner.getValue(), (Month) monthComboBox.getSelectedItem(), (Integer) daySpinner.getValue()));
    }

    @Override
    public void setProduct(Product p)
    {
        if(!(p instanceof Ingredient))
        {
            Vector<Type> v = new Vector<>();
            v.add(Ingredient.class);
            throw new EWrongTypeUsed("WinePanel.setProduct", p.getClass(), v);
        }

        Ingredient i = (Ingredient) p;

        nameField.setText(i.getName());
        buyPriceSpinner.setValue(i.getBuyPrice());
        quantitySpinner.setValue(i.getQuantity());

        LocalDate date = i.getExpirationDate();
        daySpinner.setValue(date.getDayOfMonth());
        monthComboBox.setSelectedItem(date.getMonth());
        yearSpinner.setValue(date.getYear());

    }
}
