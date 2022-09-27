package com.elective.commad;

import java.util.HashMap;
import java.util.Map;

public class CommandContainer {

    private CommandContainer(){
    }

    private static final Map<String, Command> commands;

    static {
        commands = new HashMap<>();
        commands.put("login", new LoginCommand());
        commands.put("registr", new RegistrCommand());
    }
    public static Command getCommand(String commandName) {
        return commands.get(commandName);
    }
}
