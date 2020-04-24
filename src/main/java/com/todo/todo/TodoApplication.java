package com.todo.todo;


import com.todo.todo.controller.Controller;
import com.todo.todo.repository.ServiceRepository;
import com.todo.todo.repository.ServiceRepositoryImpl;

public class TodoApplication {

	public static void main(String[] args) throws Exception {
		Controller controller = new Controller();
		ServiceRepository repo = new ServiceRepositoryImpl();
		controller.run();
		//repo.displayUndoneTasks();
	}
}