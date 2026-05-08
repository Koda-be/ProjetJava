package gestionBar.view.GUI.MainWindow;

import gestionBar.model.entities.*;
import gestionBar.model.exceptions.EWrongTypeUsed;
import gestionBar.model.utilities.TypedVector;
import gestionBar.view.ViewModelLayer;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Vector;

public class MainWindow extends JFrame
{
    private ViewModelLayer vm;

    private JMenuItem menuItemLogin;
    private JMenuItem menuItemExit;

    private JTabbedPane tabbedPane1;
    private JPanel mainPanel;
    private JPanel wineTab;
    private JPanel dishTab;
    private JPanel ingTab;
    private JTable wineTable;
    private JTable dishTable;
    private JTable ingTable;
    private JButton addButton;
    private JButton modifyButton;
    private JButton deleteButton;

    private JTable table;

    public MainWindow(ViewModelLayer v)
    {
        super();
        setTitle("Gestion Bar");
        setMinimumSize(new Dimension(600, 500));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        vm = v;

        menuItemLogin = new JMenuItem("Login");
        menuItemLogin.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent actionEvent)
            {
                vm.PromptForLogin();
            }
        });
        menuItemExit = new JMenuItem("Exit");
        menuItemExit.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent actionEvent)
            {
                vm.quit();
            }
        });

        JMenu m = new JMenu("User");
        m.add(menuItemLogin);
        m.add(menuItemExit);

        JMenuBar mb = new JMenuBar();
        mb.add(m);

        setJMenuBar(mb);

        table = new JTable(new ProductTableModel(new TypedVector<Wine>()), new ProductTableColumnModel(Wine.class));

        wineTable = table;
        dishTable = table;
        ingTable = table;

        setContentPane(mainPanel);
    }

    public static void main(String[] args)
    {
        Vector<Wine> vw = new Vector<>();

        vw.add(new Wine("Wine1", new ImageIcon("src/ressources/images/Default.png"), 10, 20, 3, "Cepage1", Colour.Red, 1970));
        vw.add(new Wine("Wine2", new ImageIcon("src/ressources/images/Default.png"), 20, 21, 4, "Cepage2", Colour.White, 1980));

        MainWindow w = new MainWindow(null);

        w.setVisible(true);
    }

    public void setTable(TypedVector<? extends Product> v)
    {
        table.setModel(new ProductTableModel(v));
        table.setColumnModel(new ProductTableColumnModel(v.getType()));
    }
}

class ProductTableModel extends AbstractTableModel
{
    private TypedVector<? extends Product> products;

    public ProductTableModel(TypedVector<? extends Product> v)
    {
        products = v;
    }

    @Override
    public int getRowCount()
    {
        return products.size();
    }

    @Override
    public int getColumnCount()
    {
        Type t = products.getType();

        if (t == Wine.class) return Wine.FieldAmmount();
        if (t == Dish.class) return Dish.FieldAmmount();
        if (t == Ingredient.class) return Ingredient.FieldAmmount();

        Vector<Type> v = new Vector<>();
        v.add(Wine.class);
        v.add(Dish.class);
        v.add(Ingredient.class);
        throw new EWrongTypeUsed("ProductTableModel.getColumnCount()", products.getType(), v);
    }

    @Override
    public Object getValueAt(int i, int i1)
    {
        return products.get(i).getFieldAt(i1);
    }
}

class ProductTableColumnModel extends DefaultTableColumnModel
{
    public ProductTableColumnModel(Type t)
    {
        super();
        String[] colName;

        if(t == Wine.class) colName = Arrays.copyOf(Wine.getFieldNames().toArray(), Wine.FieldAmmount(), String[].class);
        else if(t == Dish.class) colName = Arrays.copyOf(Dish.getFieldNames().toArray(), Dish.FieldAmmount(), String[].class);
        else if(t == Ingredient.class) colName = Arrays.copyOf(Ingredient.getFieldNames().toArray(), Ingredient.FieldAmmount(), String[].class);

        else
        {
            Vector<Type> ev = new Vector<>();
            ev.add(Wine.class);
            ev.add(Dish.class);
            ev.add(Ingredient.class);
            throw new EWrongTypeUsed("ProductTableModel.getColumnCount()", t, ev);
        }

        for (int i=0 ; i<colName.length ; i++)
        {
            TableColumn c = new TableColumn(i);
            c.setHeaderValue(colName[i]);
            addColumn(c);
        }
    }
}