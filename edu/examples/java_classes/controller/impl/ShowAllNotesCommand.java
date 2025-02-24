package edu.examples.java_classes.controller.impl;

import edu.examples.java_classes.controller.Command;
import edu.examples.java_classes.dao.DaoException;
import edu.examples.java_classes.logic.LogicException;
import edu.examples.java_classes.logic.LogicProvider;
import edu.examples.java_classes.logic.NotebookLogic;

public class ShowAllNotesCommand implements Command {
	private final LogicProvider logicProvider = LogicProvider.getInstance();
	private final NotebookLogic logic = logicProvider.getNotebookLogic();

	@Override
	public String execute(String request) throws DaoException {
		String response = null;

		try {
			logic.allNotes();
			response = "Все записи выведены на экран.";
		} catch (NumberFormatException | LogicException e) {
			response = "Что-то пошло не так.";
		} 

		return response.toString();
	}
}