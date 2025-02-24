package edu.examples.java_classes.start;

import java.io.IOException;

import edu.examples.java_classes.controller.Controller;
import edu.examples.java_classes.dao.DaoException;

public class Main {
	public static void main(String[] args) throws IOException, DaoException {
		Controller controller = new Controller();

		String request;
		String response;

//		request = "ADD" 
//		        + "\ntitle=кеервая запись\n" 
//		        + "соntent=Это моя новая запись в блокноте";
//		response = controller.doAction(request);
//		System.out.println(response);

        request = "UPDATE\nid=11\ntitle=кеервая запись\ncontent=Это моя новая запись в блокноте\ndate=07-12-2023/15-15";
        response = controller.doAction(request);
        System.out.println(response);
//
//        request = "DELETE_NOTE\nid=1";
//        response = controller.doAction(request);
//        System.out.println(response);

//		request = "FIND_BY_TITLE\ntitle=Новая запись";
//		response = controller.doAction(request);
//		System.out.println(response);

//        request = "SHOW_ALL\
//		          + ntitle";
//        response = controller.doAction(request);
//        System.out.println(response);
	}
}