package gestionBar.model.entities;

public class Wine extends Product implements Buyable, Sellable, Quantifiable
{
    protected float buyPrice;
    protected float sellPrice;
    protected int quantity;
    private String cepage;
    private Colour colour;
    private int millesime;

    // Constructors
    public Wine(String n, String img, float bp, float sp, int q, String cep, Colour col, int m)
    {
        super(n, img);
        buyPrice = bp;
        sellPrice = sp;
        quantity = q;
        cepage = cep;
        colour = col;
        millesime = m;
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
        this(w.getName(), w.getImageName(), w.getBuyPrice(), w.getSellPrice(), w.getQuantity(), w.getCepage(), w.getColour(), w.getMillesime());
    }

    // ======= Getters =======
    public float getBuyPrice()
    { return buyPrice; }

    public float getSellPrice()
    { return sellPrice; }

    public int getQuantity()
    { return quantity; }

    public String getCepage()
    { return cepage; }

    public Colour getColour()
    { return colour; }

    public int getMillesime()
    { return millesime; }

    // ======= Setters =======
    public void setBuyPrice(float newPrice)
    { buyPrice = newPrice; }

    public void setSellPrice(float newPrice)
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
}