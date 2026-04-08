package gestionBar.model.entities;

import java.time.LocalDate;
import java.time.Period;

public class Ingredient extends Product implements Buyable, Quantifiable
{
    protected float buyPrice;
    protected int quantity;
    private LocalDate expirationDate;

    // ======= Constructors =======
    public Ingredient(String n, String img, float bp, int q, LocalDate expDate)
    {
        super(n, img);
        buyPrice = bp;
        quantity = q;
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
    public float getBuyPrice()
    { return buyPrice; }

    public int getQuantity()
    { return quantity; }

    public LocalDate getExpirationDate()
    { return expirationDate; }

    // ======= Setters =======
    public void setBuyPrice(float newPrice)
    { buyPrice = newPrice; }

    public void setQuantity(int newQuantity)
    { quantity = newQuantity; }

    public void setExpirationDate(LocalDate newDate)
    { expirationDate = newDate; }

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
}
