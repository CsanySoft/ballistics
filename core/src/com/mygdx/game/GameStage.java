package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.GlobalClasses.Assets;
import com.mygdx.game.MyBaseClasses.DosActor;
import com.mygdx.game.MyBaseClasses.IBMActor;
import com.mygdx.game.MyBaseClasses.Scene2D.MyActor;
import com.mygdx.game.MyBaseClasses.Scene2D.MyStage;
import com.mygdx.game.MyBaseClasses.Scene2D.OneSpriteStaticActor;

/**
 * Created by tanulo on 2017. 10. 25..
 */

public class GameStage extends MyStage {

    Ballistics ballistics;

    public void setControlStage(ControlStage controlStage) {
        this.controlStage = controlStage;
    }

    ControlStage controlStage;
    IBMActor ibmActor;
    OneSpriteStaticActor bg;
    OsemberActor osemberActor;
    MyActor actor;
    float offsetX = 0.2f;
    float offsetY = 1.8f;
    float timeScale = 1;

    public void setTimeScale(float timeScale) {
        this.timeScale = timeScale;
    }

    public float getOffsetX() {
        return offsetX;
    }

    public float getOffsetY() {
        return offsetY;
    }

    public GameStage getGameStage() {
        return this;
    }

    public void setV0(float v0) {
        this.v0 = v0;
    }


        float v0 = 10;
        int i = 0;

    public float round(float f){
        return ((float)((int)(f*10f)))/10f;
    }

    public GameStage(final Batch batch, final MyGdxGame game) {
        super(new ExtendViewport(10.24f,7.68f), batch, game);
        bg = new OneSpriteStaticActor(Assets.manager.get(Assets.BACKGROUND_TEXTURE));
        bg.setSize(getViewport().getWorldWidth(), getViewport().getWorldHeight());
        addActor(bg);


        addActor( osemberActor = new OsemberActor(Assets.manager.get(Assets.OSEMBER_TEXTURE)));
        addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                for (Actor actor : getActors().toArray()) {
                    if(actor.toString().equals("IBMActor")) i++;
                }
                if(i > 3) System.out.println("Nem rakhatsz le többet!");
                else {
                    IBMActor ibmActor;

                    try {
                        InfoLabelActor infoLabelActor;
                        controlStage.addActor(infoLabelActor = new InfoLabelActor("Távolság: " + round(x) + " m\n Magasság: "+round(y)+" m \n Szög (1): "+round(new Ballistics(x - getOffsetX(), y - getOffsetY(), v0).getAnglesByDeg()[0])+"°°\n Szög (2): "+round(new Ballistics(x - getOffsetX(), y - getOffsetY(), v0).getAnglesByDeg()[1])+"°\n Sebesség: " + round(v0) +" m/s", x*100+50, y*100-100, game.getLabelStyle()));
                        ibmActor = new IBMActor(x, y, infoLabelActor, getGameStage());
                        addActor(new FloppyActor(new Ballistics(x - getOffsetX(), y - getOffsetY(), v0), 0, ibmActor, getGameStage()));
                        addActor(new FloppyActor(new Ballistics(x - getOffsetX(), y - getOffsetY(), v0), 1, ibmActor, getGameStage()));

                        addActor(ibmActor);
                    }
                    catch (Exception e)
                    {
                        System.out.println(e.toString());
                        ibmActor = null;
                        osemberActor.doAngry(getGameStage());
                    }

                }
                i=0;
            }
        });
        setCameraResetToLeftBottomOfScreen();

        //setDebugAll(true);
    }

    public Label.LabelStyle getLabelStyle() {
        Label.LabelStyle style;
        style = new com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle();
        style.font = Assets.manager.get(Assets.ARIAL_30_FONT);
        style.fontColor = Color.WHITE;
        return style;
    }

    @Override
    public void init() {
    }
}
