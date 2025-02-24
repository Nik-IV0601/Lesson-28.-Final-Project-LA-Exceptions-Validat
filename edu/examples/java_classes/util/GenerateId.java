package edu.examples.java_classes.util;

import java.io.*;

//public final class GenerateId {
//
//	private final LogicProvider logicProvider = LogicProvider.getInstance();
//	private final NotebookLogic logic = logicProvider.getNotebookLogic();
//
//	public int nextId() {
//
//		List<Note> notes;
//		int maxId;
//
//		try {
//			notes = logic.allNotes();
//			if (notes == null || notes.isEmpty()) {// Проверяем, есть ли в списке записи
//				return 1; // Если список пуст, начинаем с 1
//			}
//
//		} catch (LogicException | DaoException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		maxId = notes.stream()// Находим максимальный id в списке
//				.mapToInt(Note::getId) // Извлекаем ID из каждого объекта
//				.max() // Находим максимальное значение
//				.orElse(0);// Если список пуст, возвращаем 0
//
//		return maxId + 1;
//	}
//}

public final class GenerateId {

	private GenerateId() {
	}

	public static int nextId() throws IOException {
		int maxId = 0;
		try {
			InputStreamReader reader = new InputStreamReader(new FileInputStream("resources/notes.txt"), "UTF-8");
			BufferedReader breader = new BufferedReader(reader);
			String line = null;

			while ((line = breader.readLine()) != null) {

				String[] part = line.split(" ", 4);
				int id = Integer.parseInt(part[0]);
				if (id > maxId) {
					maxId = id;
				}
			}
			reader.close();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return maxId + 1;
	}
}