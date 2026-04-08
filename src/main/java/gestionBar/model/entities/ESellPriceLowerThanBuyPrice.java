package gestionBar.model.entities;

public class ESellPriceLowerThanBuyPrice extends RuntimeException
{
    private Product origin;
    private float buyPrice;
    private float attemptedSellPrice;

    public ESellPriceLowerThanBuyPrice(Product p, float bp, float asp)
    {
        super(p.getName() + ": new sell price cannot be lower than buy price (buy price: " + bp + ", attempted sell price: " + asp);

        origin = p;
        buyPrice = bp;
        attemptedSellPrice = asp;
    }


}
