package edu.examples.java_classes.controller.impl;

import java.io.IOException;
import java.util.Date;

import edu.examples.java_classes.controller.Command;
import edu.examples.java_classes.dao.DaoException;
import edu.examples.java_classes.entity.Note;
import edu.examples.java_classes.logic.LogicProvider;
import edu.examples.java_classes.logic.NotebookLogic;
import edu.examples.java_classes.util.GenerateId;

public class AddNoteCommand implements Command {

	private final LogicProvider logicProvider = LogicProvider.getInstance();
	private final NotebookLogic logic = logicProvider.getNotebookLogic();

	@Override
	public String execute(String request) throws IOException, DaoException {
		String response = null;
		String[] params = request.split("\n");
		Note newNote = new Note();

		newNote.setTitle(params[1].split("=")[1]);
		newNote.setContent(params[2].split("=")[1]);
		newNote.setId(GenerateId.nextId());
		newNote.setDate(new Date());

		try {
			logic.add(newNote);
		} catch (Exception e) {
			response = "Что-то пошло не так.";
		}
		response = "Запись сохранена успешно.";
		return response;
	}
}