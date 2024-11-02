package com.lwjgl.Math;

public class Color {
	private float red, green, blue, alpha;
	public float getAlpha() {
		return alpha;
	}
	public float getBlue() {
		return blue;
	}
	public float getGreen() {
		return green;
	}
	public float getRed() {
		return red;
	}
	public void setAlpha(float alpha) {
		this.alpha = alpha;
	}
	public void setBlue(float blue) {
		this.blue = blue;
	}
	public void setGreen(float green) {
		this.green = green;
	}
	public void setRed(float red) {
		this.red = red;
	}
	public Color(float r, float g, float b, float a) {
		this.red = r;
		this.green = g;		
		this.blue = b;
		this.alpha = a;
	}
	public Color(float r, float g, float b) {
		this.red = r;
		this.green = g;		
		this.blue = b;
		this.alpha = 1;
	}
	
	public static Color white() {
		return new Color(1.0f, 1.0f, 1.0f, 1.0f);
	}
	
	public static Color dark() {
		return new Color(0.0f, 0.0f, 0.0f, 1.0f);
	}

}
