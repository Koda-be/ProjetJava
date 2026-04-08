package gestionBar.model.accessors.authenticator;

import java.util.HashMap;
import java.util.Map;

public class MapAuthenticator extends Authenticator
{
    final private Map<String, String> mapData = new HashMap<>();

    public MapAuthenticator()
    {
        mapData.put("Lio","Lio123");
        mapData.put("Deb", "Deb456");
        mapData.put("Mario", "Yahoo");
    }

    @Override
    protected boolean isLoginExists(String username)
    { return mapData.containsKey(username); }

    @Override
    protected String getPassword(String username)
    {
        return mapData.get(username);
    }
}
