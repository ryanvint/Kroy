package com.earlybird.kroygame;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

/**
 * Contains utility functions that don't quite belong in any other class
 */
public class Utils {
	private Utils() {}
	
	/**
	 * Used in creating StatBars
	 * @param width
	 * @param height
	 * @param color
	 * @return
	 */
	public static Drawable getColoredDrawable(int width, int height, Color color) {
		Pixmap pixmap = new Pixmap(width, height, Format.RGBA8888);
		pixmap.setColor(color);
		pixmap.fill();
		
		TextureRegionDrawable drawable = new TextureRegionDrawable(new TextureRegion(new Texture(pixmap)));
		
		pixmap.dispose();
		
		return drawable;
	}
	
	/**
	 * Finds the direction between 2 points, used in changing texture directions in movement
	 * @param a
	 * @param b
	 * @return int representing the direction 
	 */
	public static int findDir(Vector2 a, Vector2 b) {
		if (b.y > a.y) {
			return 0;
		}
		if (b.x < a.x) {
			return 1;
		}
		if (b.y < a.y) {
			return 2;
		}
		if (b.x > a.x) {
			return 3;
		}
		return -1;
	}
	
	/**
	 * Checks if a point is between 2 others
	 * @param point1
	 * @param point2
	 * @param point3 The point to check if it is between point1 and point2
	 * @return True if point3 is between point1 and point2
	 */
	public static boolean isBetween(Vector2 point1, Vector2 point2, Vector2 point3) {
		if(point1!=null && point2!=null && point3!=null) {
			Vector2 big = new Vector2(0, 0);
			Vector2 small = new Vector2(0, 0);
			if (point1.x < point2.x) {
				big.x = point2.x;
				small.x = point1.x;
			}
			else {
				big.x = point1.x;
				small.x = point2.x;
			}
			if (point1.y < point2.y) {
				big.y = point2.y;
				small.y = point1.y;
			}
			else {
				big.y = point1.y;
				small.y = point2.y;
			}
			if (point3.x >= small.x & point3.x <= big.x
					& point3.y >= small.y & point3.y <= big.y) {
				return true;
				}
			else {
				return false;
			}
		}
		else {
			return false;
		}
		
	}
	
}
