package main;

import view.View;
import controller.Controller;
import model.Model;

public class Main {
	public static void main(String[] args) {
		View frame = new View();
		Model model = new Model();
		Controller controller = new Controller(frame, model);
	}
}

