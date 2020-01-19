package com.earlybird.kroygame;

import com.earlybird.kroygame.Utils;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;

/**
 * StatBar is a progressBar modified to be styled like a health or water bar
 */
public class StatBar extends ProgressBar{

	/**
	 * Creates a progress bar used for displaying health and water values
	 * @param width
	 * @param height
	 * @param health
	 * @param background
	 * @param knob
	 * @param knobBefore
	 */
	public StatBar(int width, int height, float health, Color background, Color knob, Color knobBefore) {
		super(0f,health,1.0f,false,new ProgressBarStyle());
		getStyle().background = Utils.getColoredDrawable(width, height, background);
		getStyle().knob = Utils.getColoredDrawable(0, height, knob);
		getStyle().knobBefore = Utils.getColoredDrawable(width, height, knobBefore);
		
		setWidth(width);
		setHeight(height);
		
		setAnimateDuration(0.0f);
		setValue(1f);
		
		setAnimateDuration(0.25f);
	}
	
}
