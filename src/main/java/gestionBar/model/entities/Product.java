package gestionBar.model.entities;

public abstract class Product
{
    protected String name;
    protected String imageName;

    // Constructors
    public Product(String n, String img)
    {
        name = n;
        imageName = img;
    }

    public Product()
    {
        this("Unknown", "Default.png");
    }

    public Product(Product p)
    {
        this(p.getName(), p.getImageName());
    }

    // ======= Getters =======
    public String getName()
    { return name; }

    public String getImageName()
    { return imageName; }

    // ======= Setters =======
    public void setName(String newName)
    { name = newName; }

    public void setImageName(String imgPath)
    { imageName = imgPath; }

    // Override
    @Override
    public String toString()
    {
        return "Name: " + name;
    }

    @Override
    public boolean equals(Object o)
    {
        if(!(o instanceof Product)) return false;
        Product p = (Product) o;
        return name.equals(p.getName());
    }
}
