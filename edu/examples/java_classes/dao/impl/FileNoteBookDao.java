package edu.examples.java_classes.dao.impl;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import edu.examples.java_classes.dao.DaoException;
import edu.examples.java_classes.dao.NoteBookDao;
import edu.examples.java_classes.entity.Note;

public class FileNoteBookDao implements NoteBookDao {

	@Override
	public void save(Note n) throws DaoException, IOException {
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yy/HH-mm");
		BufferedWriter bufWriter = null;
		try {
			bufWriter = new BufferedWriter(new FileWriter("resources/notes.txt", true));

			bufWriter.write(n.getId() + " " + format.format(n.getDate()) + " " + n.getTitle() + " " + n.getContent());
			bufWriter.newLine();
			bufWriter.close();
		} catch (IOException e) {
			System.err.println("Error in file");
		}
	}

	@Override
	public List<Note> allNotes() throws DaoException {

		List<Note> listNote = new ArrayList<>();
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yy/HH-mm");
		try {
			InputStreamReader reader = new InputStreamReader(new FileInputStream("resources/notes.txt"), "UTF-8");
			BufferedReader breader = new BufferedReader(reader);
			String line = null;

			while ((line = breader.readLine()) != null) {

				String[] part = line.split(" ", 4);
				int id = Integer.parseInt(part[0]);
				Date date = format.parse(part[1]);
				listNote.add(new Note(id, part[2], part[3], date));

			}
			reader.close();
		} catch (IOException e) {
			System.err.println("File not found");
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}

		return listNote;
	}

	@Override
	public void delete(int id) throws IOException, NumberFormatException, DaoException {

		String currentNote = "resources/notes.txt";
		File tempNote = new File("temp.txt");

		BufferedReader reader = null;
		BufferedWriter writer = null;

		try {
			reader = new BufferedReader(new FileReader(currentNote));
			writer = new BufferedWriter(new FileWriter(tempNote));
			String line;
			while ((line = reader.readLine()) != null) {
				if (!line.startsWith(String.valueOf(id))) {
					writer.write(line + System.getProperty("line.separator"));
				}
			}
		} catch (IOException e) {
			System.err.println("Error while deleting");
		} finally {
			reader.close();
			writer.close();
			File oldFile = new File(currentNote);
			oldFile.delete();
			tempNote.renameTo(oldFile);
		}
	}

	@Override
	public void update(Note n) throws IOException, NumberFormatException, DaoException {
		String currentFile = "resources/notes.txt";
		File tempFile = new File("temp.txt");
		int lineToDelete = n.getId();

		BufferedReader reader = null;
		BufferedWriter writer = null;
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yy/HH-mm");
		String requiredLine;

		try {
			reader = new BufferedReader(new FileReader(currentFile));
			writer = new BufferedWriter(new FileWriter(tempFile));

			String line;
			requiredLine = n.getId() + " " + format.format(n.getDate()) + " " + n.getTitle() + " " + n.getContent();

			while ((line = reader.readLine()) != null) {
				if (!line.startsWith(String.valueOf(lineToDelete))) {
					writer.write(line + System.getProperty("line.separator"));
				} else {
					writer.write(requiredLine + System.getProperty("line.separator"));
				}
			}
		} catch (IOException e) {
			System.err.println("Error while processing file");
		} finally {
			reader.close();
			writer.close();
			File oldFile = new File(currentFile);
			oldFile.delete();
			tempFile.renameTo(oldFile);
		}
	}
}