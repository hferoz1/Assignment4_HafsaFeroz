package org.example;

import java.util.ArrayList;
import java.util.List;

public class CompositeCommand implements Command {
    private List<Command> commands;

    /**
     * creates new empty composite command
     */
    public CompositeCommand() {
        this.commands = new ArrayList<>();
    }

    /**
     * adds command to composite command
     * @param command command to add
     */
    public void addCommand(Command command) {
        commands.add(command);
    }

    /**
     * removes command from composite command
     * @param command command to remove
     */
    public void removeCommand(Command command) {
        commands.remove(command);
    }

    /**
     * executes all commands in composite command in order
     */
    @Override
    public void execute() {
        for (Command command : commands) {
            command.execute();
        }
    }

    /**
     * gets copy of list of commands in composite
     * @return new list containing all commands
     */
    public List<Command> getCommands() {
        return new ArrayList<>(commands);
    }
}