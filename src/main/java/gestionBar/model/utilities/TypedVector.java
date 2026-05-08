package gestionBar.model.utilities;

import gestionBar.model.exceptions.EWrongTypeUsed;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Vector;

public class TypedVector<T> extends Vector<T>
{
    private final Type type;

    @SuppressWarnings("unchecked")
    public TypedVector()
    {
        ParameterizedType pt = (ParameterizedType) getClass().getGenericSuperclass();
        type = pt.getActualTypeArguments()[0];
    }

    public Type getType()
    {
        return type;
    }
}