package gestionBar.view.Login;

import gestionBar.model.accessors.authenticator.Authenticator;

import javax.swing.*;
import java.awt.*;

public class LoginWindow extends JDialog
{
    private String username;
    private String password;

    private JPanel mainPanel;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton okButton;
    private JButton cancelButton;
    private JPanel buttonPanel;
    private JPanel fieldPanel;

    public LoginWindow(JFrame owner, String tit, boolean mod, Authenticator logger)
    {
        super(owner, tit, mod);
        setSize(250, 150);
        setResizable(false);
        setContentPane(mainPanel);
    }

    public LoginWindow()
    {
        super();
        setTitle("Essai affichage");
        setSize(250, 150);
        setResizable(false);
        setContentPane(mainPanel);
    }

    public static void main(String[] args)
    {
        LoginWindow window = new LoginWindow();

        window.setVisible(true);
    }
}
