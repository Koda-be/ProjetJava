package gestionBar.model.entities;

import gestionBar.model.exceptions.ESellPriceLowerThanBuyPrice;

import javax.swing.*;
import java.util.Vector;

public class Dish extends Product implements Sellable
{
    private double sellPrice;
    private Vector<Ingredient> ingredients;

    public Dish(String n, ImageIcon img, double sp, Vector<Ingredient> ing)
    {
        super(n, img, 0, 0);
        ingredients = ing;

        double bp = 0;
        int q = ingredients.get(0).getQuantity();
        for(Ingredient i : ingredients)
        {
            bp += i.getBuyPrice();

            if (q > i.getQuantity()) q = i.getQuantity();
        }

        buyPrice = bp;
        quantity = q;

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
        ingredients = new Vector<Ingredient>();
    }

    public double getBuyPrice()
    {
        double sum = 0;
        for (Ingredient ing : ingredients) sum += ing.getBuyPrice();

        return sum;
    }

    public double getSellPrice()
    {
        return sellPrice;
    }

    public int getQuantity()
    {
        int min = ingredients.get(0).getQuantity();
        for(Ingredient ing : ingredients) if(ing.getQuantity() < min) min = ing.getQuantity();

        return min;
    }

    public Vector<Ingredient> getIngredients()
    {
        return ingredients;
    }

    public void setSellPrice(double newPrice) throws ESellPriceLowerThanBuyPrice
    {
        if(newPrice < getBuyPrice()) throw(new ESellPriceLowerThanBuyPrice(this, getBuyPrice(), newPrice));

        sellPrice = newPrice;
    }

    public void setIngredients(Vector<Ingredient> i)
    {
        ingredients = i;

        buyPrice = getBuyPrice();

        if(sellPrice < buyPrice) setSellPrice(buyPrice);
    }

    public static int FieldAmmount()
    { return Product.FieldAmmount() + 2; }

    public static Vector<String> getFieldNames()
    {
        Vector<String> v = Product.getFieldNames();
        v.insertElementAt("Sell price", 3);
        v.add("Ingredients");

        return v;
    }

    @Override
    public String toString()
    {
        return super.toString() + ", buy price: " + getBuyPrice() + ", sell price: " + sellPrice + ", quantity: " + getQuantity() + ", ingredients: " + ingredients;
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
                return ingredients;
            default:
                return null;
        }
    }
}
