package gestionBar.model.exceptions;

import java.lang.reflect.Type;
import java.util.Vector;

public class EWrongTypeUsed extends RuntimeException
{
    public EWrongTypeUsed(String place, Type attemptedClass, Vector<Type> typeArray)
    {
        super(" In " + place + ": Use of type " + attemptedClass + " is not allowed in this context (allowed classes are: " + typeArray + ")");
    }
}
