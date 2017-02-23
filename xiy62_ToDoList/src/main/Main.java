package main;

import view.View;
import controller.Controller;
import model.Model;

public class Main {
	public static void main(String[] args) {
		Model model = new Model();
		View frame = new View(model);
		Controller controller = new Controller(frame, model);
	}
}

