package edu.examples.java_classes.controller;

import java.util.HashMap;
import java.util.Map;

import edu.examples.java_classes.controller.impl.AddNoteCommand;
import edu.examples.java_classes.controller.impl.DeleteNoteCommand;
import edu.examples.java_classes.controller.impl.FindNoteByTitleCommand;
import edu.examples.java_classes.controller.impl.NoSuchCommand;
import edu.examples.java_classes.controller.impl.ShowAllNotesCommand;
import edu.examples.java_classes.controller.impl.UpdateNoteCommand;

public class CommandProvider {
    private final Map<CommandName, Command> repository = new HashMap<>();

    CommandProvider(){
        repository.put(CommandName.ADD, new AddNoteCommand());
        repository.put(CommandName.UPDATE, new UpdateNoteCommand());
        repository.put(CommandName.DELETE_NOTE, new DeleteNoteCommand());
        repository.put(CommandName.FIND_BY_TITLE, new FindNoteByTitleCommand());
        repository.put(CommandName.SHOW_ALL, new ShowAllNotesCommand());
        repository.put(CommandName.WRONG_REQUEST, new NoSuchCommand());
    }

    Command getCommand(String name){
        CommandName commandName =null;
        Command command = null;

        try{
            commandName = CommandName.valueOf(name.toUpperCase());
            command = repository.get(commandName);
        }catch(IllegalArgumentException | NullPointerException e){
            command = repository.get(CommandName.WRONG_REQUEST);
        }
        return command;
    }
}