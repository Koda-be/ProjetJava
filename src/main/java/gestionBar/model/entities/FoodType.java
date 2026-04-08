package gestionBar.model.entities;

public enum FoodType
{
    Meat("Meat"),
    Vegetable("Vegetable");


    private final String desc;

    private FoodType(String d)
    {
        desc = d;
    }

    @Override
    public String toString()
    { return desc; }
}
