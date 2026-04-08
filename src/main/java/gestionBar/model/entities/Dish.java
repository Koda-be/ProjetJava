package gestionBar.model.entities;

import java.util.Vector;

public class Dish extends Product implements Sellable
{
    private float sellPrice;
    private Vector<Ingredient> ingredients;

    public Dish(String n, String img, float sp, Vector<Ingredient> ing)
    {
        super(n, img);
        ingredients = ing;

        try
        {
            setSellPrice(sp);
        } catch (ESellPriceLowerThanBuyPrice error)
        {
            setSellPrice(getBuyPrice());

        }
    }

    public Dish()
    {
        super();

        sellPrice = 0;
        ingredients = new Vector<Ingredient>();
    }

    public float getBuyPrice()
    {
        float sum = 0;
        for (Ingredient ing : ingredients) sum += ing.getBuyPrice();

        return sum;
    }

    public float getSellPrice()
    {
        return sellPrice;
    }

    public int getQuantity()
    {
        int min = ingredients.get(0).getQuantity();
        for(Ingredient ing : ingredients) if(ing.getQuantity() < min) min = ing.getQuantity();

        return min;
    }

    public void setSellPrice(float newPrice)
    {
        if(newPrice < getBuyPrice()) throw(new ESellPriceLowerThanBuyPrice(this, getBuyPrice(), newPrice));

        sellPrice = newPrice;
    }

    @Override
    public String toString()
    {
        return super.toString() + ", buy price: " + getBuyPrice() + ", sell price: " + sellPrice + ", quantity: " + getQuantity() + ", ingredients: " + ingredients;
    }
}
