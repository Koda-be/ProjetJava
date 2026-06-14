package gestionBar.model.entities;

import gestionBar.model.exceptions.ESellPriceLowerThanBuyPrice;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Vector;

public class Dish extends Product implements Sellable
{
    private double sellPrice;
    private Ingredient ingredient;

    public Dish(String n, ImageIcon img, double sp, Ingredient ing)
    {
        super(n, img, 0, 0);
        ingredient = ing;

        buyPrice = ingredient.getBuyPrice();
        quantity = ingredient.getQuantity();

        try
        {
            setSellPrice(sp);
        }
        catch (ESellPriceLowerThanBuyPrice e)
        {
            sellPrice = buyPrice;
            throw e;
        }
    }

    public Dish()
    {
        super();

        sellPrice = 0;
    }

    public double getBuyPrice()
    {
        return buyPrice;
    }

    public double getSellPrice()
    {
        return sellPrice;
    }

    public int getQuantity()
    {
        return quantity;
    }

    public Ingredient getIngredient()
    {
        return ingredient;
    }

    public void setSellPrice(double newPrice) throws ESellPriceLowerThanBuyPrice
    {
        if(newPrice < buyPrice) throw(new ESellPriceLowerThanBuyPrice(this, buyPrice, newPrice));

        sellPrice = newPrice;
    }

    public void setIngredient(Ingredient i)
    {
        ingredient = i;

        buyPrice = i.getBuyPrice();

        if(sellPrice < buyPrice) setSellPrice(buyPrice);
    }

    public static int FieldAmount()
    { return Product.FieldAmount() + 2; }

    public static ArrayList<String> getFieldNames()
    {
        ArrayList<String> v = Product.getFieldNames();
        v.add(3, "Sell price");
        v.add("Ingredients");

        return v;
    }

    @Override
    public String toString()
    {
        return super.toString() + ", buy price: " + getBuyPrice() + ", sell price: " + sellPrice + ", quantity: " + getQuantity() + ", ingredients: " + ingredient;
    }

    public Object getFieldAt(int i)
    {
        if (i < 3) return super.getFieldAt(i);

        switch (i)
        {
            case 3:
                return getSellPrice();
            case 4:
                return getQuantity();
            case 5:
                return getIngredient();
            default:
                return null;
        }
    }
}
