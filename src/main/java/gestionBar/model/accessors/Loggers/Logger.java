package gestionBar.model.accessors.Loggers;

import gestionBar.model.accessors.authenticator.Authenticator;

public class Logger
{
    private String userFile; // Change this when you know how to use files
    private boolean loginState;
    private Authenticator authenticator;

    public Logger(String uf, Authenticator a)
    {
        userFile = uf;
        loginState = false;
        authenticator = a;
    }

    public boolean AttemptLogin(String username, String password)
    {
        if(loginState = authenticator.authenticate(username, password))
        {
            userFile=username; // here too
            return true;
        }
        return false;
    }

    public String getUserFile()
    {
        return userFile;
    }

    public boolean getLoginState()
    {
        return loginState;
    }
    public void setLoginState(boolean ls) { loginState = ls; }
}
