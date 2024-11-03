package com.lwjgl.window;

import java.nio.*;

import org.lwjgl.*;
import org.lwjgl.glfw.*;
import org.lwjgl.system.*;

import com.lwjgl.math.Color;

import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryStack.*;
import static org.lwjgl.system.MemoryUtil.NULL;
import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;


public class Window {
	// The window handle
	private long glfwWindow;
	private int width = 800, height = 600;
	private Color bg_color = Color.white();
	private String title = "LWJGL Window";

	public Window(String title, int width, int height, Color bg_color) {
		this.width = width;
		this.height = height;
		this.bg_color = bg_color;
		this.title = title;
	}
	public Window(String title, int width, int height) {
		this.width = width;
		this.height = height;
		this.title = title;
	}
	public Window(String title) {
		this.title = title;
	}
	public Window(Color bg_color) {
		this.bg_color = bg_color;
	}
	
	public Window(int width, int height) {
		this.width = width;
		this.height = height;
	}
	
	public Window() {
		init();
	}

	public void setBg_color(Color bg_color) {
		this.bg_color = bg_color;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setWidth(int width) {
		this.width = width;
	}

	public void run() {
		System.out.println("Hello LWJGL " + Version.getVersion() + "!");
		init();
		loop();

		// Free the window callbacks and destroy the window
		glfwFreeCallbacks(glfwWindow);
		glfwDestroyWindow(glfwWindow);
		
		// Terminate GLFW and free the error callback
		glfwTerminate();
		glfwSetErrorCallback(null).free();
	}

	private void init() {
		// Setup an error callback. The default implementation
		// will print the error message in System.err.
		GLFWErrorCallback.createPrint(System.err).set();

		// Initialize GLFW. Most GLFW functions will not work before doing this.
		if ( !glfwInit() )
			throw new IllegalStateException("Unable to initialize GLFW");

		// Configure GLFW
		glfwDefaultWindowHints(); // optional, the current window hints are already the default
		glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE); // the window will stay hidden after creation
		glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE); // the window will be resizable
		
		// Create the window
		glfwWindow = glfwCreateWindow(
			this.width, this.height, 
			this.title, 
			NULL, NULL
		);
		if ( glfwWindow == NULL ) {
			throw new RuntimeException("Failed to create the GLFW window");
		}

		glfwSetKeyCallback(glfwWindow, KeyInput::keyCallback);
		glfwSetMouseButtonCallback(glfwWindow, MouseInput::mouseButtonCallback);
		glfwSetCursorPosCallback(glfwWindow, MouseInput::mousePosCallback);
		glfwSetScrollCallback(glfwWindow, MouseInput::mouseScrollCallback);
		

		// Get the thread stack and push a new frame
		try ( MemoryStack stack = stackPush() ) {
			IntBuffer pWidth = stack.mallocInt(1); // int*
			IntBuffer pHeight = stack.mallocInt(1); // int*

			// Get the window size passed to glfwCreateWindow
			glfwGetWindowSize(glfwWindow, pWidth, pHeight);

			// Get the resolution of the primary monitor
			GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());

			// Center the window
			glfwSetWindowPos(
				glfwWindow,
				(vidmode.width() - pWidth.get(0)) / 2,
				(vidmode.height() - pHeight.get(0)) / 2
			);
		} // the stack frame is popped automatically

		// Make the OpenGL context current
		glfwMakeContextCurrent(glfwWindow);
		// Enable v-sync
		glfwSwapInterval(1);

		// Make the window visible
		glfwShowWindow(glfwWindow);

		// This line is critical for LWJGL's interoperation with GLFW's
		// OpenGL context, or any context that is managed externally.
		// LWJGL detects the context that is current in the current thread,
		// creates the GLCapabilities instance and makes the OpenGL
		// bindings available for use.
		GL.createCapabilities();

		// Set the clear color
		setClearColor(bg_color);
	}

	private void setClearColor(Color color) {
		glClearColor(
			this.bg_color.getRed(),
			this.bg_color.getBlue(), 
			this.bg_color.getGreen(),
			this.bg_color.getAlpha()
		);
	}

	private void render() {

	}

	private void loop() {
		// Run the rendering loop until the user has attempted to close
		// the window or has pressed the ESCAPE key.
		while ( !glfwWindowShouldClose(glfwWindow) ) {
			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the framebuffer
			// Poll for window events. The key callback above will only be
			// invoked during this call.
			glfwPollEvents();

			if (KeyInput.isKeyPressed(GLFW_KEY_ESCAPE)) {
				glfwSetWindowShouldClose(glfwWindow, true);
			}

			if (KeyInput.isKeyPressed(GLFW_KEY_E)) {
				System.out.println("Hello world!");
			}
			
			
			render();
			
			glfwSwapBuffers(glfwWindow); // swap the color buffers
		}
	}
}
