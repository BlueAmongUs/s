package com.lwjgl.window;

import static org.lwjgl.glfw.GLFW.GLFW_PRESS;
// import static org.lwjgl.glfw.GLFW.GLFW_RELEASE;

public class KeyInput {
	private KeyInput() {};
	private static KeyInput instance;
    private boolean keyPressed[] = new boolean[350];

	public static KeyInput get() {
        if (KeyInput.instance == null) {
            KeyInput.instance = new KeyInput();
        }
        return KeyInput.instance;
    }
    public static void keyCallback(
        long window, int key, 
        int scancode, int action, int mods
    ) {
		get().keyPressed[key] = action == GLFW_PRESS;
        // if (action == GLFW_PRESS) {
        //     get().keyPressed[key] = true;
        // } else if (action == GLFW_RELEASE) {
        //     get().keyPressed[key] = false;
        // }
    }
    public static boolean isKeyPressed(int keyCode) {
        return get().keyPressed[keyCode];
    }
}
