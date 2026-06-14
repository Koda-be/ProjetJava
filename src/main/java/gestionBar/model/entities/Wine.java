package gestionBar.model.entities;

import gestionBar.model.exceptions.ESellPriceLowerThanBuyPrice;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Vector;

public class Wine extends Product implements Buyable, Sellable, Quantifiable
{
    private double sellPrice;
    private String cepage;
    private Colour colour;
    private int millesime;

    // Constructors
    public Wine(String n, ImageIcon img, double bp, double sp, int q, String cep, Colour col, int m)
    {
        super(n, img, bp, q);
        cepage = cep;
        colour = col;
        millesime = m;

        try
        {
            setSellPrice(sp);
        }
        catch(ESellPriceLowerThanBuyPrice e)
        {
            sellPrice = buyPrice;
            throw e;
        }
    }

    public Wine()
    {
        super();
        cepage = "Unknown";
        colour = Colour.Unknown;
        millesime = 1970;
    }

    public Wine(Wine w)
    {
        this(w.getName(), w.getImage(), w.getBuyPrice(), w.getSellPrice(), w.getQuantity(), w.getCepage(), w.getColour(), w.getMillesime());
    }

    // ======= Getters =======

    public double getSellPrice()
    { return sellPrice; }

    public String getCepage()
    { return cepage; }

    public Colour getColour()
    { return colour; }

    public int getMillesime()
    { return millesime; }

    // ======= Setters =======
    public void setBuyPrice(float newPrice)
    { buyPrice = newPrice; }

    public void setSellPrice(double newPrice) throws ESellPriceLowerThanBuyPrice
    {
        if(newPrice < buyPrice) throw new ESellPriceLowerThanBuyPrice(this, buyPrice, newPrice);
        sellPrice = newPrice;
    }

    public void setQuantity(int newQuantity)
    { quantity = newQuantity; }

    public void setCepage(String newCep)
    { cepage = newCep; }

    public void setColour(Colour newCol)
    { colour = newCol; }

    public void setMillesime(int newMil)
    { millesime = newMil; }


    public static int FieldAmount()
    { return Product.FieldAmount() + 4; }

    public static ArrayList<String> getFieldNames()
    {
        ArrayList<String> v = Product.getFieldNames();
        v.add(3,"Sell price");
        v.add("Cepage");
        v.add("Colour");
        v.add("Millesime");

        return v;
    }

    // Override
    @Override
    public String toString()
    { return super.toString() + ", buy price: " + buyPrice + ", sell price: " + sellPrice + ", quantity: " + quantity + ", cepage: " + cepage + ", colour: " + colour; }

    @Override
    public boolean equals(Object o)
    {
        if(!(o instanceof Wine)) return false;
        Wine w = (Wine) o;
        return super.equals(w) && buyPrice == w.getBuyPrice() && sellPrice == w.getSellPrice() && cepage.equals(w.getCepage()) && colour.equals(w.getColour()) && millesime == w.getMillesime();
    }

    @Override
    public Object getFieldAt(int i)
    {
        if (i < 3) return super.getFieldAt(i);

        switch (i)
        {
            case 3: return getSellPrice();
            case 4: return getQuantity();
            case 5: return getCepage();
            case 6: return getColour();
            case 7: return getMillesime();
            default: return null;
        }
    }
}