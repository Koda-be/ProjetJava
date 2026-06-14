package gestionBar.view.GUI.ProductWindow;

import gestionBar.model.entities.Colour;
import gestionBar.model.entities.Product;
import gestionBar.model.entities.Wine;
import gestionBar.model.exceptions.EWrongTypeUsed;

import javax.swing.*;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.util.Vector;

public class WinePanel implements ProductEditor
{
    private JPanel panel1;
    private JTextField nameField;
    private JSpinner quantitySpinner;
    private JSpinner buyPriceSpinner;
    private JSpinner sellPriceSpinner;
    private JTextField cepageField;
    private JComboBox colourComboBox;
    private JSpinner millesimeSpinner;

    public WinePanel()
    {
        buyPriceSpinner.setModel(new SpinnerNumberModel(10.f, 0, 1000000, 1f));
        sellPriceSpinner.setModel(new SpinnerNumberModel(20f, 0, 1000000, 1));
        quantitySpinner.setModel(new SpinnerNumberModel(1, 0, 1000000, 1));
        millesimeSpinner.setModel(new SpinnerNumberModel(2000, 1910, 2026, 1));

        colourComboBox.setModel(new DefaultComboBoxModel<>(Colour.values()));
    }

    public JPanel getPanel()
    {
        return panel1;
    }

    @Override
    public Product getProduct()
    {
        try
        {
            buyPriceSpinner.commitEdit();
            sellPriceSpinner.commitEdit();
            quantitySpinner.commitEdit();
            millesimeSpinner.commitEdit();
        }
        catch (ParseException e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Parse error", JOptionPane.ERROR_MESSAGE);
        }

        if ((Double) sellPriceSpinner.getValue() < (Double) buyPriceSpinner.getValue())
        {
            JOptionPane.showMessageDialog(null, "Sell price cannot be lower than buy price", "Wrong input", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        return new Wine(nameField.getText(), null, (Double) buyPriceSpinner.getValue(), (Double) sellPriceSpinner.getValue(), (Integer) quantitySpinner.getValue(), cepageField.getText(), (Colour) colourComboBox.getSelectedItem(), (Integer) millesimeSpinner.getValue());
    }

    @Override
    public void setProduct(Product p)
    {
        if(!(p instanceof Wine))
        {
            Vector<Type> v = new Vector<>();
            v.add(Wine.class);
            throw new EWrongTypeUsed("WinePanel.setProduct", p.getClass(), v);
        }

        Wine w = (Wine) p;

        nameField.setText(w.getName());
        quantitySpinner.setValue(w.getQuantity());
        buyPriceSpinner.setValue(w.getBuyPrice());
        sellPriceSpinner.setValue(w.getSellPrice());
        cepageField.setText(w.getCepage());
        colourComboBox.setSelectedItem(w.getColour());
        millesimeSpinner.setValue(w.getMillesime());
    }
}
