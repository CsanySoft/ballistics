package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.game.DemoLoading.LoadingScreen;
import com.mygdx.game.GlobalClasses.Assets;
import com.mygdx.game.MyBaseClasses.Game.MyGame;

public class MyGdxGame extends MyGame {

	public Slider.SliderStyle getSliderStyle(){
		Slider.SliderStyle sliderStyle = new Slider.SliderStyle();
		sliderStyle.knobDown = new TextureRegionDrawable(new TextureRegion(Assets.manager.get(Assets.KNOB_TEXTURE)));
		sliderStyle.knobOver = new TextureRegionDrawable(new TextureRegion(Assets.manager.get(Assets.KNOB_TEXTURE)));
		sliderStyle.knob = new TextureRegionDrawable(new TextureRegion(Assets.manager.get(Assets.KNOB_TEXTURE)));
		sliderStyle.background = new TextureRegionDrawable(new TextureRegion(Assets.manager.get(Assets.SLIDER_TEXTURE)));
		return sliderStyle;
	}

    public Label.LabelStyle getLabelStyle() {
        Label.LabelStyle style;
        style = new com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle();
        style.font = Assets.manager.get(Assets.ARIAL_30_FONT);
        style.fontColor = Color.RED;
        return style;
    }



	@Override
	public void create () {
        Assets.prepare();
		setScreen(new LoadingScreen(this));
	}

}