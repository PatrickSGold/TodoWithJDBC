package com.todo.todo.repository;

import java.sql.*;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class ServiceRepositoryImpl implements ServiceRepository {
    Scanner input = new Scanner(System.in);

    @Override
    public void addTask() {

        try (Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/Todo", "root", "P4trickgold!");

             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO Todos(Id, Todo, done) " +
                             "VALUES(null, ?, 0)")) {

            preparedStatement.setString(1, askForTask());
            preparedStatement.executeUpdate();
        }
        catch (Exception e) {
            handleException(e);
            // reset method
        }

    }

    @Override
    public void modifyTask() {
        try (Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/Todo", "root", "P4trickgold!");
             PreparedStatement preparedStatement = connection.prepareStatement(
                "UPDATE Todos SET Todo = ? WHERE Id = ?");) {

            preparedStatement.setString(1, askForTask());
            preparedStatement.setInt(2, askForKey());
            preparedStatement.executeUpdate();
        }
        catch (Exception e) {
            handleException(e);
        }
    }

    @Override
    public void removeTask() {

        try (Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/Todo", "root", "P4trickgold!");
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "DELETE FROM Todos WHERE Id = ?")) {

            preparedStatement.setInt(1, askForKey());
            preparedStatement.executeUpdate();
        }
        catch (Exception e) {
            handleException(e);
        }
    }

    @Override
    public void markTaskAsDone() {

        try (Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/Todo", "root", "P4trickgold!");
             PreparedStatement preparedStatement = connection.prepareStatement
                     ("UPDATE Todos " +
                             "SET done = 1 " +
                             "WHERE Id = ?")) {

            preparedStatement.setInt(1, askForKey());
            preparedStatement.executeUpdate();
        }

        catch (Exception e) {
            handleException(e);
        }
    }

    @Override
    public void displayAllTasks() {
        try (Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/Todo", "root", "P4trickgold!");
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Todos");
             ResultSet resultSet = preparedStatement.executeQuery("select * from Todos;");) {

            while (resultSet.next()) {
                System.out.println(resultSet.getInt(1) + "  "
                        + resultSet.getString(2) + "  "
                        + resultSet.getBoolean(3));
            }

        } catch (Exception e) {
            handleException(e);
        }
    }

    @Override
    public void displayUndoneTasks() {

        try (Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/Todo", "root", "P4trickgold!");

             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT * FROM Todos WHERE done = 0");

             ResultSet resultSet = preparedStatement.executeQuery(
                     "select * from Todos WHERE done = 0")) {

            while (resultSet.next()) {
                System.out.println(resultSet.getInt(1) + "  "
                        + resultSet.getString(2) + "  "
                        + resultSet.getBoolean(3));
            }
                
        } catch (Exception e) {
            handleException(e);
        }

    }

    private static void handleException(Exception e) {
        if (e instanceof SQLException) {
            SQLException sqlException = (SQLException) e;
            System.out.println("Error code: " + sqlException.getErrorCode());
            System.out.println("SQLState: " + sqlException.getSQLState());
        }
        System.out.println("SQLException message: " + e.getMessage());
        System.out.println("Stacktrace: ");
        e.printStackTrace();
    }

    private int askForKey() {
        int key = 0;

        try {
            System.out.print("Please enter key number: ");
            key = input.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Wrong input. Try again.");
            // reset method
        }
        return key;
    }

    private String askForTask() {
        String task = null;

        try {
            System.out.print("Please enter the task: ");
            task = input.nextLine();
        }
        catch (NoSuchElementException e) {
            System.out.println("Wrong input. Try again.");
            // reset method
        }
        return task;
    }


}
