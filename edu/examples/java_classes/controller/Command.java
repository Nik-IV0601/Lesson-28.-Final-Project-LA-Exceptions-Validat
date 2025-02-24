package edu.examples.java_classes.controller;

import java.io.IOException;
import edu.examples.java_classes.dao.DaoException;

public interface Command {
    String execute(String request) throws IOException, DaoException;
}