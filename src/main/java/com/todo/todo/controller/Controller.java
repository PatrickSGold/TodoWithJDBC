package com.todo.todo.controller;


import com.todo.todo.repository.ServiceRepository;
import com.todo.todo.repository.ServiceRepositoryImpl;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Controller {
    ServiceRepository repo = new ServiceRepositoryImpl();

    private boolean exit = false;
    private int chosenAction = 0;

    public void run() {
        while (!exit) {
            action();
        }
    }

    private void action() {
        switch (askWhatUserWantsToDo()) {
            case 1:
                repo.addTask();
                break;
            case 2:
                repo.markTaskAsDone();
                break;
            case 3:
                repo.modifyTask();
                break;
            case 4:
                repo.displayAllTasks();
                break;
            case 5:
                repo.displayUndoneTasks();
            case 6:
                repo.removeTask();
            case 7:
                exit = true;
                break;
        }
    }

    private int askWhatUserWantsToDo() {
        Scanner input = new Scanner(System.in);

        System.out.println("What do you want to do?");
        System.out.println("1: Add task");
        System.out.println("2: Mark task as completed");
        System.out.println("3: Change a task");
        System.out.println("4: Display all tasks");
        System.out.println("5: Display uncompleted tasks");
        System.out.println("6: Remove a task");
        System.out.println("7: Exit");

        try {
            chosenAction = input.nextInt();
            checkInput();
        }
        catch (InputMismatchException e) {
            System.out.println("Wrong input. Try again.");
            askWhatUserWantsToDo();
        }
        return chosenAction;
    }

    private void checkInput() {
        if(chosenAction <= 0 || chosenAction >= 8) {
            System.out.println("Wrong input. Try again.");
            askWhatUserWantsToDo();
        }
    }



}
