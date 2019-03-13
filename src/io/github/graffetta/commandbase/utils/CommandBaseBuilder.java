/*
 * Copyright (c) Graffetta 2019.
 * CommandBase is distributed under the MIT license and is available for download over at https://github.com/Graffetta/CommandBase
 */

package io.github.graffetta.commandbase.utils;

import io.github.graffetta.commandbase.CommandBase;

import java.lang.reflect.InvocationTargetException;

public class CommandBaseBuilder {

    public static CommandBase newInstance()
            throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        return CommandBase.class.getDeclaredConstructor().newInstance();
    }

}
