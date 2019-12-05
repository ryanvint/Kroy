package com.earlybird.kroygame;

import com.earlybird.kroygame.Utils;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;

public class HealthBar extends ProgressBar{

	public HealthBar(int width, int height, float health) {
		super(0f,health,1.0f,false,new ProgressBarStyle());
		getStyle().background = Utils.getColoredDrawable(width, height, Color.RED);
		getStyle().knob = Utils.getColoredDrawable(0, height, Color.GREEN);
		getStyle().knobBefore = Utils.getColoredDrawable(width, height, Color.GREEN);
		
		setWidth(width);
		setHeight(height);
		
		setAnimateDuration(0.0f);
		setValue(1f);
		
		setAnimateDuration(0.25f);
	}
	
}
