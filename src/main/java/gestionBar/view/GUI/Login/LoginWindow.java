package gestionBar.view.GUI.Login;

import gestionBar.model.accessors.Loggers.Logger;
import gestionBar.model.accessors.authenticator.Authenticator;
import gestionBar.model.accessors.authenticator.MapAuthenticator;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class LoginWindow extends JDialog implements ActionListener
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

    public LoginWindow(String tit, boolean mod)
    {
        super((JFrame) null, tit, mod);
        setSize(250, 150);
        setResizable(false);
        setContentPane(mainPanel);

        okButton.addActionListener(this);
        cancelButton.addActionListener(this);

        okButton.setMnemonic(KeyEvent.VK_ENTER);
    }

    public static void main(String[] args)
    {
        Authenticator logger = new MapAuthenticator();
        LoginWindow testWindow = new LoginWindow("testing", true);
        testWindow.setVisible(true);
        String[] strs = testWindow.getCredentials();

        testWindow.dispose();


        if(logger.authenticate(strs[0], strs[1]))
            JOptionPane.showMessageDialog(null, "Success! User logged in: " + strs[0], "Login success", JOptionPane.INFORMATION_MESSAGE);
        else
            JOptionPane.showMessageDialog(null, "Login cancelled", "Login cancelled", JOptionPane.ERROR_MESSAGE);
    }

    public String[] getCredentials()
    {
        return new String[] {username, password};
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == okButton)
        {
            username =  usernameField.getText();
            password = new String(passwordField.getPassword());
        }
        else
        {
            username = null;
            password = null;
        }

        setVisible(false);
    }
}
