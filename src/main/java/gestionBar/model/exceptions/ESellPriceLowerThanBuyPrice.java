package gestionBar.model.exceptions;

import gestionBar.model.entities.Product;

public class ESellPriceLowerThanBuyPrice extends RuntimeException
{
    public ESellPriceLowerThanBuyPrice(Product p, double bp, double asp)
    {
        super(p.getName() + ": new sell price cannot be lower than buy price (buy price: " + bp + ", attempted sell price: " + asp);
    }
}
