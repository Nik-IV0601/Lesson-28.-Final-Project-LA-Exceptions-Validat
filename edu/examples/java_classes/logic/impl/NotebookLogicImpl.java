package edu.examples.java_classes.logic.impl;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import edu.examples.java_classes.dao.DaoException;
import edu.examples.java_classes.dao.DaoProvider;
import edu.examples.java_classes.dao.NoteBookDao;
import edu.examples.java_classes.entity.Note;
import edu.examples.java_classes.logic.LogicException;
import edu.examples.java_classes.logic.NotebookLogic;

public class NotebookLogicImpl implements NotebookLogic {
	private final DaoProvider provider = DaoProvider.getInstance();
	private final NoteBookDao dao = provider.getNoteBookDao();

	@Override
	public void update(Note n) throws LogicException, IOException {
		try {
			dao.update(n);
		} catch (DaoException e) {
			throw new LogicException(e);
		}
	}

	@Override
	public void delete(int id) throws LogicException, IOException {
		try {
			dao.delete(id);
		} catch (DaoException e) {
			throw new LogicException(e);
		}
	}

	public void add(Note n) throws LogicException, DaoException {
		try {
			dao.save(n);
		} catch (DaoException e) {
			throw new LogicException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Note> find(String text) throws LogicException {
		List<Note> result = new ArrayList<>();
		List<Note> myNotes;

		try {
			InputStreamReader reader = new InputStreamReader(new FileInputStream("resources/notes.txt"), "UTF-8");
			try (BufferedReader breader = new BufferedReader(reader)) {
				myNotes = dao.allNotes();
				for (Note n : myNotes) {
					String line = breader.readLine();
					if (isTextInNote(n, text)) {
						result.add(n);
						System.out.println(line);
					}
				}
			}
			return result;
		} catch (DaoException e) {
			throw new LogicException(e);
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

	}

	private boolean isTextInNote(Note n, String text) {
		return n.getTitle().contains(text) || n.getContent().contains(text);
	}

	public List<Note> allNotes() throws LogicException, DaoException{
		
		try {
			InputStreamReader reader = new InputStreamReader(new FileInputStream("resources/notes.txt"), "UTF-8");
			BufferedReader breader = new BufferedReader(reader);
			String line = null;
			while ((line = breader.readLine()) != null) {
				System.out.println(line);
			}
			reader.close();
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return dao.allNotes();
	}
	
//	public void allNotes() throws LogicException {
//		try {
//			InputStreamReader reader = new InputStreamReader(new FileInputStream("resources/notes.txt"), "UTF-8");
//			BufferedReader breader = new BufferedReader(reader);
//			String line = null;
//			while ((line = breader.readLine()) != null) {
//				System.out.println(line);
//			}
//			reader.close();
//		} catch (FileNotFoundException e) {
//			throw new RuntimeException(e);
//		} catch (UnsupportedEncodingException e) {
//			throw new RuntimeException(e);
//		} catch (IOException e) {
//			throw new RuntimeException(e);
//		}
//	}
}