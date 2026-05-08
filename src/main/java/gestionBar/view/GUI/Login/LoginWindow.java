package gestionBar.view.GUI.Login;

import gestionBar.model.accessors.Loggers.Logger;
import gestionBar.model.accessors.authenticator.MapAuthenticator;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class LoginWindow extends JDialog implements ActionListener
{
    private Logger logger;

    private JPanel mainPanel;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton okButton;
    private JButton cancelButton;
    private JPanel buttonPanel;
    private JPanel fieldPanel;

    public LoginWindow(String tit, boolean mod, Logger log)
    {
        super((JFrame) null, tit, mod);
        setSize(250, 150);
        setResizable(false);
        setContentPane(mainPanel);
        logger = log;

        okButton.addActionListener(this);
        cancelButton.addActionListener(this);

        okButton.setMnemonic(KeyEvent.VK_ENTER);
    }

    public static void main(String[] args)
    {
        Logger logger = new Logger("userFile", new MapAuthenticator());
        LoginWindow testWindow = new LoginWindow("testing", true, logger);
        testWindow.setVisible(true);
        testWindow.dispose();

        if(logger.getLoginState())
            JOptionPane.showMessageDialog(null, "Success! User logged in: " + logger.getUserFile(), "Login success", JOptionPane.INFORMATION_MESSAGE);
        else
            JOptionPane.showMessageDialog(null, "Login cancelled", "Login cancelled", JOptionPane.ERROR_MESSAGE);
    }

    public void checkLogin()
    {
        if (logger.AttemptLogin(usernameField.getText(), String.valueOf(passwordField.getPassword())))
        {
            dispose();
        }
        else
        {
            JOptionPane.showMessageDialog(this, "Incorrect username or password", "Login error", JOptionPane.ERROR_MESSAGE);
            usernameField.setText("");
            passwordField.setText("");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == okButton) checkLogin();

        else
        {
            logger.setLoginState(false);
            dispose();
        }
    }
}
