package edu.examples.java_classes.controller.impl;

import java.io.IOException;

import edu.examples.java_classes.controller.Command;
import edu.examples.java_classes.logic.LogicException;
import edu.examples.java_classes.logic.LogicProvider;
import edu.examples.java_classes.logic.NotebookLogic;

public class DeleteNoteCommand implements Command {
    private final LogicProvider logicProvider = LogicProvider.getInstance();
    private final NotebookLogic logic = logicProvider.getNotebookLogic();
    
    @Override
    public String execute(String request)  {
        String response = null;
        String[] params = request.split("\n");

        try {
            logic.delete(Integer.parseInt(params[1].split("=")[1]));
        } catch (IOException | NumberFormatException | LogicException e) {
            response = "Что-то пошло не так.";
        } 
        response = "Запись удалена успешно.";

        return response;
    }
}