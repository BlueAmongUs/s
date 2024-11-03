package com.lwjgl.math;

public class Matrix2x2 {
	private Vector2 i, j;
	public Matrix2x2(Vector2 i, Vector2 j) {
		this.i = i;
		this.j = j;
	}
	public Matrix2x2(
		float ix, float iy, 
		float jx, float jy
	) {
		this.i = new Vector2(ix, iy);
		this.j = new Vector2(jx, jy);
	}
	public Vector2 getI() {
		return i;
	}
	public Vector2 getJ() {
		return j;
	}	
}
