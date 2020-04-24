package com.todo.todo.repository;

public interface ServiceRepository {

    void addTask();

    void modifyTask();

    void removeTask();

     void markTaskAsDone();

     void displayAllTasks();

     void displayUndoneTasks();
}
