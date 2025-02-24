package edu.examples.java_classes.controller.impl;

import java.io.IOException;
import java.util.List;

import edu.examples.java_classes.controller.Command;
import edu.examples.java_classes.dao.DaoException;
import edu.examples.java_classes.entity.Note;
import edu.examples.java_classes.logic.LogicException;
import edu.examples.java_classes.logic.LogicProvider;
import edu.examples.java_classes.logic.NotebookLogic;

public class FindNoteByTitleCommand implements Command {
	private final LogicProvider logicProvider = LogicProvider.getInstance();
	private final NotebookLogic logic = logicProvider.getNotebookLogic();

	@Override
	public String execute(String request) throws IOException, DaoException {
	    String response = null;
	    String[] params = request.split("\n");
	    List<Note> allNotes = null;

	    if (params.length < 2) {
	        response = "Некорректный запрос.";
	        return response;
	    }

	    try {
	        String paramValue = params[1].split("=")[1].trim();
	        allNotes = logic.find(paramValue); 
	    } catch (NumberFormatException | LogicException e) {
	        response = "Что-то пошло не так: " + e.getMessage();
	    }

	    if (allNotes != null) {
	        response = "Выбранные записи выведены на экран.";
	    } else {
	        response = "Записи не найдены.";
	    }

	    return response;
	}
}