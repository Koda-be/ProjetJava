package gestionBar.model.entities;

import gestionBar.model.exceptions.ESellPriceLowerThanBuyPrice;

public interface Sellable
{
    double getSellPrice();
    void setSellPrice(double newPrice) throws ESellPriceLowerThanBuyPrice;
}
