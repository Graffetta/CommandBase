/*
 * Copyright (c) Graffetta 2019.
 * CommandBase is distributed under the MIT license and is available for download over at https://github.com/Graffetta/CommandBase
 */

package io.github.graffetta.commandbase.utils;

import java.lang.reflect.Field;

public class ObjectUtils {

    public static Object getValue(Object object, String fieldName)
        throws NoSuchFieldException, IllegalAccessException {
        Field valueField = object.getClass().getField(fieldName);
        valueField.setAccessible(true);
        return valueField.get(object);
    }

    public static void setValue(Object object, Object value, String fieldName)
        throws NoSuchFieldException, IllegalAccessException {
        Field field = object.getClass().getField(fieldName);
        field.setAccessible(true);
        if(value.getClass() == field.get(object).getClass())
            field.set(object, value);
    }

}
