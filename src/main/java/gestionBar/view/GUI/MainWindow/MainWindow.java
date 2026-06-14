package gestionBar.view.GUI.MainWindow;

import gestionBar.model.entities.*;
import gestionBar.model.exceptions.EWrongTypeUsed;
import gestionBar.model.utilities.TypedVector;
import gestionBar.view.ViewModelLayer;

import javax.lang.model.type.UnionType;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Type;
import java.util.ArrayList;
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

        table = new JTable(new ProductTableModel(new ArrayList<Wine>()), new ProductTableColumnModel(Wine.class));

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

    public void setTable(ArrayList<? extends Product> a)
    {
        table.setModel(new ProductTableModel(a));
        table.setColumnModel(new ProductTableColumnModel( !a.isEmpty() ? a.get(0).getClass() : null));
    }
}

class ProductTableModel extends AbstractTableModel
{
    private ArrayList<? extends Product> products;

    public ProductTableModel(ArrayList<? extends Product> v)
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
        Product p = products != null && !products.isEmpty() ? products.get(0) : null;

        if(p != null)
        {
            if(p instanceof Wine) return Wine.FieldAmount();
            if(p instanceof Dish) return Dish.FieldAmount();
            if(p instanceof Ingredient) return Ingredient.FieldAmount();
        }
        return 0;
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

        if(t == Wine.class) colName = Wine.getFieldNames().toArray(new String[0]); //Arrays.copyOf(Wine.getFieldNames().toArray(), Wine.FieldAmount(), String[].class);
        else if(t == Dish.class) colName = Dish.getFieldNames().toArray(new String[0]); //Arrays.copyOf(Dish.getFieldNames().toArray(), Dish.FieldAmount(), String[].class);
        else if(t == Ingredient.class) colName = Ingredient.getFieldNames().toArray(new String[0]); //Arrays.copyOf(Ingredient.getFieldNames().toArray(), Ingredient.FieldAmount(), String[].class);
        else if(t == null) colName = new String[0];

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