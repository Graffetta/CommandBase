/*
 * Copyright (c) Graffetta 2019.
 * CommandBase is distributed under the MIT license and is available for download over at https://github.com/Graffetta/CommandBase
 */

package io.github.graffetta.commandbase.command;

import io.github.graffetta.commandbase.CommandBase;

@CommandInfo(
        name = "command", description = "uninitialized command"
)
public abstract class Command {

    private String name;
    private String description;

    public String getName() { return this.name; }
    public String getDescription() { return this.description; }

    public abstract CommandResult execute(final String[] input, final CommandBase commandBase, Object... args) throws IndexOutOfBoundsException;

    public Command() {
        CommandInfo commandInfoAnnotation = this.getClass().getAnnotation(CommandInfo.class);
        this.name = commandInfoAnnotation.name();
        this.description = commandInfoAnnotation.description();
    }

}
