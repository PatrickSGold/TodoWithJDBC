package com.todo.todo;
import com.todo.todo.controller.Controller;


public class TodoApplication {

	public static void main(String[] args) throws Exception {
		Controller controller = new Controller();

		controller.run();
	}
}