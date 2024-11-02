package com.lwjgl.maven;

import com.lwjgl.Math.Color;
import com.lwjgl.Window.*;

public class Main {
	public static void main(String[] args) {
		Window window = new Window("Hello, LWJGL!",800, 600);
		window.setBg_color(Color.dark());
		window.run();
	}
}