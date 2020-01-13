package com.earlybird.kroygame;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class Utils {
	private Utils() {}
	
	public static Drawable getColoredDrawable(int width, int height, Color color) {
		Pixmap pixmap = new Pixmap(width, height, Format.RGBA8888);
		pixmap.setColor(color);
		pixmap.fill();
		
		TextureRegionDrawable drawable = new TextureRegionDrawable(new TextureRegion(new Texture(pixmap)));
		
		pixmap.dispose();
		
		return drawable;
	}
	
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
			if (point3.x > small.x & point3.x < big.x
					& point3.y > small.y & point3.y < big.y) {
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
