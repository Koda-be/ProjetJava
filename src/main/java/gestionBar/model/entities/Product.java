package gestionBar.model.entities;

import javax.swing.*;
import java.util.Vector;

public abstract class Product
{
    protected String name;
    protected ImageIcon image;
    protected double buyPrice;
    protected int quantity;

    // Constructors
    public Product(String n, ImageIcon img, double bp, int q)
    {
        name = n;
        image = (img == null ? new ImageIcon("src/ressources/images/Default.png") : img);
        buyPrice = bp;
        quantity = q;
    }

    public Product()
    {
        this("Unknown", new ImageIcon("Default.png"), 0, 0);
    }

    // ======= Getters =======
    public String getName()
    { return name; }

    public ImageIcon getImage()
    { return image; }

    public double getBuyPrice()
    { return buyPrice; }

    public int getQuantity()
    { return quantity; }

    // ======= Setters =======
    public void setName(String newName)
    { name = newName; }

    public void setImage(ImageIcon newImage)
    { image = newImage; }



    // ======= Other methods =======

    public static int FieldAmmount()
    { return 4; }

    public static Vector<String> getFieldNames()
    {
        Vector<String> v = new Vector<>();

        v.add("Name");
        v.add("Image path");
        v.add("Buy price");
        v.add("Quantity");

        return v;
    }

    public Object getFieldAt(int i)
    {
        switch(i)
        {
            case 0: return getName();
            case 1: return getImage().toString();
            case 2: return getBuyPrice();
            case 3: return getQuantity();
            default: return null;
        }
    }

    // ======= Override =======
    @Override
    public String toString()
    {
        return "Name: " + name;
    }

    @Override
    public boolean equals(Object o)
    {
        if(!(o instanceof Product)) return false;
        Product p = (Product) o;
        return name.equals(p.getName());
    }
}
