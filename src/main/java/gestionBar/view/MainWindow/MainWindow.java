package gestionBar.view.MainWindow;

import gestionBar.model.accessors.authenticator.Authenticator;
import gestionBar.view.Login.LoginWindow;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow extends JFrame implements ActionListener
{
    private Authenticator logger;
    private String user;

    public MainWindow()
    {
        super("Gestion bar");

        login();
    }

    public static void main(String[] args)
    {
        MainWindow window = new MainWindow();
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent)
    {

    }

    public void login()
    {
        LoginWindow loginWindow = new LoginWindow(this, "Authenticator", true, logger);
        loginWindow.setVisible(true);
    }
}
