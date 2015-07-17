package game.views;

import game.controllers.MyGame;
import game.util.Constants;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class DialogUI {

	public static void quitGameConfirm(Stage stage,final MyGame game) {

		TextureAtlas atlas = MyGame.manager.get("ui/ui.pack",TextureAtlas.class);
	    Skin skinDialog = new Skin(Gdx.files.internal("data/uiskin.json"));

		TextureRegion redButton = new TextureRegion(atlas.findRegion("red_button"));
		
	    LabelStyle style = new LabelStyle();
	    Label label1 = new Label("Are you sure that you want to exit?", style);
	    label1.setAlignment(Align.center);
	    //style.font.setScale(1, -1);
	    style.fontColor = Color.WHITE;

	    Skin tileSkin = new Skin();
	    Texture tex = redButton.getTexture();
	    tex.setFilter(TextureFilter.Linear, TextureFilter.Linear);
	    tileSkin.add("white", tex);
	    tileSkin.add("default", new BitmapFont());

	    TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
	    textButtonStyle.up = tileSkin.newDrawable("white");
	    textButtonStyle.down = tileSkin.newDrawable("white", Color.DARK_GRAY);
	    textButtonStyle.checked = tileSkin.newDrawable("white",
	            Color.LIGHT_GRAY);
	    textButtonStyle.over = tileSkin.newDrawable("white", Color.LIGHT_GRAY);
	    textButtonStyle.font = skinDialog.getFont("default-font");
	    textButtonStyle.font.setScale(1, -1);
	    textButtonStyle.fontColor = Color.WHITE;
	    tileSkin.add("default", textButtonStyle);

	    TextButton btnYes = new TextButton("Exit", tileSkin);
	    TextButton btnNo = new TextButton("Cancel", tileSkin);

	    // /////////////////
	    final Dialog dialog = new Dialog("", skinDialog) {
	        @Override
	        public float getPrefWidth() {
	            // force dialog width
	            // return Gdx.graphics.getWidth() / 2;
	            return 700f;
	        }

	        @Override
	        public float getPrefHeight() {
	            // force dialog height
	            // return Gdx.graphics.getWidth() / 2;
	            return 400f;
	        }
	    };
	    dialog.setModal(true);
	    dialog.setMovable(false);
	    dialog.setResizable(false);

	    btnYes.addListener(new InputListener() {
	        @Override
	        public boolean touchDown(InputEvent event, float x, float y,
	                int pointer, int button) {

	            // Do whatever here for exit button

	    		game.changeScreen(Constants.MENUSCREEN);

	            dialog.hide();
	            dialog.cancel();
	            dialog.remove();                

	            return true;
	        }

	    });

	    btnNo.addListener(new InputListener() {
	        @Override
	        public boolean touchDown(InputEvent event, float x, float y,
	                int pointer, int button) {

	            //Do whatever here for cancel

	            dialog.cancel();
	            dialog.hide();

	            return true;
	        }

	    });

	    TextureRegion myTex = new TextureRegion(atlas.findRegion("dialog"));
	    myTex.flip(false, true);
	    myTex.getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
	    Drawable drawable = new TextureRegionDrawable(myTex);
	    dialog.setBackground(drawable);

	    float btnSize = 80f;
	    Table t = new Table();
	    // t.debug();

	    dialog.getContentTable().add(label1).padTop(40f);

	    t.add(btnYes).width(btnSize).height(btnSize);
	    t.add(btnNo).width(btnSize).height(btnSize);

	    dialog.getButtonTable().add(t).center().padBottom(80f);
	    dialog.show(stage).setPosition(
	            (Constants.VIEWPORT_GUI_WIDTH / 2) - (720 / 2),
	            (Constants.VIEWPORT_GUI_HEIGHT) - (Constants.VIEWPORT_GUI_HEIGHT - 40));

	    dialog.setName("quitDialog");
	    stage.addActor(dialog);

	}
}
