package gestionBar.model.entities;

public enum Colour
{
    White("White"),
    Red("Red"),
    Rose("Rose"),
    Unknown("Unknown");

    public final String desc;

    private Colour(String d)
    { desc = d; }

    @Override
    public String toString()
    { return desc; }
}
