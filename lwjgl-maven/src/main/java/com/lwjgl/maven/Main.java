package com.lwjgl.maven;

import com.lwjgl.math.Color;
import com.lwjgl.window.*;

public class Main {
	public static void main(String[] args) {
		Window window = new Window(Color.dark());
		window.run();
	}
}