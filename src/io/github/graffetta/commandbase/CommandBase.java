/*
 * Copyright (c) Graffetta 2019.
 * CommandBase is distributed under the MIT license and is available for download over at https://github.com/Graffetta/CommandBase
 */

package io.github.graffetta.commandbase;

import io.github.graffetta.commandbase.command.Command;
import io.github.graffetta.commandbase.command.CommandResult;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class CommandBase {

    private CommandBase instance;

    public CommandBase getInstance() { return this.instance; }
    public CommandBase setInstance(CommandBase instance) { return this.instance = instance; }

    public CommandBase() {
        this.instance = this;
        this.updateCommands();
    }

    private List<Command> commandList;
    private List<Command> registrationQueue = new ArrayList<>();

    public List<Command> getCommandList() { return this.commandList; }

    public Command getCommand(Class <? extends Command> command) {
        for(final Command currentCommand : this.commandList)
            if(currentCommand.getClass() == command)
                return currentCommand;
        return null;
    }

    public List<Command> getCommandsByName(String name) {
        return this.commandList.stream().filter(command -> command.getName().equalsIgnoreCase(name)).collect(Collectors.toList());
    }

    public void registerCommand(Class <? extends Command> commandClass)
        throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        this.registerCommand(commandClass.getDeclaredConstructor().newInstance());
    }

    public void registerCommand(Command command) {
        if(this.commandList == null)
            this.registrationQueue.add(command);
        else
            this.commandList.add(command);
    }

    private void updateCommands() {
        if(this.commandList == null)
            this.commandList = new ArrayList<>();
        Iterator<Command> iterator = this.registrationQueue.iterator();
        while(iterator.hasNext()) {
            this.commandList.add(iterator.next());
            iterator.remove();
        }
    }

    public CommandResult executeNew(String[] input, Class<? extends Command> command, Object... args)
        throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        return executeNew(input, command.getDeclaredConstructor().newInstance(), args);
    }
    public CommandResult executeNew(String[] input, Command command, Object... args) {
        return command.execute(input, this, args);
    }

    public CommandResult execute(String input, final CommandBase commandBase, Object... args) {

        if(input.length() == 0) {
            return CommandResult.EMPTY_INPUT;
        }

        CommandResult result = CommandResult.FAILED;

        String[] inputArgs = input.split(" ");

        for(final Command currentCommand : getCommandsByName(inputArgs[0])) {
            result = currentCommand.execute(inputArgs, this, args);
        }

        return result;
    }

    public CommandResult execute(String input, Object... args) {
        return this.execute(input, this, args);
    }

    public static void main(String[] args) {
        System.out.println("This program cannot be run in DOS mode.\nNot even as a standalone program, sorry!\n( https://github.com/Graffetta/CommandBase )");
    }

}
