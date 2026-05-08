package gestionBar.model.entities;

import javax.swing.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.Vector;

public class Ingredient extends Product implements Buyable, Quantifiable
{
    private LocalDate expirationDate;

    // ======= Constructors =======
    public Ingredient(String n, ImageIcon img, double bp, int q, LocalDate expDate)
    {
        super(n, img, bp, q);
        expirationDate = expDate;
    }

    public Ingredient()
    {
        super();
        buyPrice = 0;
        quantity = 0;
        expirationDate = LocalDate.now().plus(Period.of(0,2,0));
    }

    // ======= Getters =======

    public LocalDate getExpirationDate()
    { return expirationDate; }

    // ======= Setters =======
    public void setBuyPrice(float newPrice)
    { buyPrice = newPrice; }

    public void setQuantity(int newQuantity)
    { quantity = newQuantity; }

    public void setExpirationDate(LocalDate newDate)
    { expirationDate = newDate; }

    public static int FieldAmmount()
    { return Product.FieldAmmount() + 1; }

    public static Vector<String> getFieldNames()
    {
        Vector<String> v = Product.getFieldNames();
        v.add("Expiration date");

        return v;
    }

    // ======= Override =======

    @Override
    public String toString()
    {
        return super.toString() + ", buy price: " + buyPrice + ", quantity: " + quantity + ", exp date: " + expirationDate;
    }

    @Override
    public boolean equals(Object o)
    {
        if(!(o instanceof Ingredient)) return false;
        Ingredient i = (Ingredient) o;
        return super.equals(i) && buyPrice == i.getBuyPrice() && expirationDate.equals(i.getExpirationDate());
    }

    @Override
    public Object getFieldAt(int i)
    {
        if (i < 4) return super.getFieldAt(i);

        if (i == 4) return getExpirationDate();
        return null;
    }
}
