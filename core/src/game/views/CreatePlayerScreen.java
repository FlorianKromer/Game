package game.views;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import game.controllers.AbstractScreen;
import game.controllers.MyGame;
import game.models.world.Character;
import game.util.Constants;
import game.util.GamePreferences;

public class CreatePlayerScreen extends AbstractScreen{


	protected Stage stage;
	private TextButton btnMenuPlay;
	private TextButton btnMenuOptions;
	private TextField tfPlayerName;
	private List<String> listClasses;

	public CreatePlayerScreen(MyGame game) {
		super(game);
		this.stage = new Stage(new StretchViewport(Constants.VIEWPORT_GUI_WIDTH, Constants.VIEWPORT_GUI_HEIGHT));

	}
	@Override
	public void show() {
		// on dit a l'appli d'ecouter ce stage quand la methode show est appelee
		Gdx.input.setInputProcessor(stage);

		stage.clear();
		// Background initialisation
		stage.addActor(buildBackgroundLayer());
		Stack stack = new Stack();
		stage.addActor(stack);
		buildBtnMenuPlay(true);
		stack.setSize(Constants.VIEWPORT_GUI_WIDTH, Constants.VIEWPORT_GUI_HEIGHT);
		//		stack.add(buildTitleLayer(atlas));

		stack.add(buildTfPlayerName());
		stack.add(buildCreateCharacterWindow());

		//		AudioManager.instance.play(Gdx.audio.newMusic(Gdx.files.internal("sound/CloudTopLoops.mp3")));

	};



	@Override
	public void render(float delta) {
		super.render(delta);

		stage.act(delta);
		stage.draw();


	}
	private Table buildCreateCharacterWindow(){
		Table t = new Table();

		Window winNewCharacter = new Window("La légende de Kikou",skin);
		winNewCharacter.setMovable(false);
		winNewCharacter.add(new Label("Choisir sa classe", skin));
		winNewCharacter.row();
		winNewCharacter.add(buildClasseSelection()).minWidth(100).expandX().fillX().colspan(4);
		winNewCharacter.row();
		winNewCharacter.pack();
		//		showNewCharacterWindow(true, true);
		t.add(winNewCharacter);
		t.top();
		t.left();
		t.pad(Constants.VIEWPORT_GUI_HEIGHT*0.1f,Constants.VIEWPORT_GUI_WIDTH*0.1f,0f,0f);
		t.pack();
		return t;
	}
	private Table buildTfPlayerName(){
		TextureAtlas atlas = MyGame.manager.get("ui/ui.pack",TextureAtlas.class);
	    Image imgTitle = new Image(atlas.findRegion("dialog"));
		
		Table t = new Table();
		t.setBackground(imgTitle.getDrawable());
		t.setFillParent(false);
		tfPlayerName = new TextField("", skin);
		tfPlayerName.setMessageText("Saisir un pseudo!");

		tfPlayerName.setTextFieldListener(new TextFieldListener() {
			public void keyTyped(TextField textField, char key) {
				if (key == '\n')
					textField.getOnscreenKeyboard().show(false);
				if(! tfPlayerName.getText().equals(""))
					btnMenuPlay = buildBtnMenuPlay(false);
			}
		});


		Label txt = new Label("Nom", skin);
		txt.setColor(Color.valueOf("3a2b2b"));
		t.add(txt);
		t.row();
		t.add(tfPlayerName);
		t.setBounds(0, 0, 200, 200);
		t.pack();
		
		Table a = new Table();
		a.add(t);
		return a;
	}

	/**
	 * 
	 * @return
	 */
	private ScrollPane buildClasseSelection(){
		// creation d'un tableau pour stocker les classes
		String[] tabPersonnage = { "Knight", "White Mage", "Black Mage","Monk","Red Mage","Thief" };
		// creation d'une select box (appele List ici) avec le tableau ci dessus
		listClasses = new List(skin);
		listClasses.setSize(100, 100);
		listClasses.setItems(tabPersonnage);
		listClasses.addListener(new ChangeListener() {

			@Override
			public void changed(ChangeEvent event, Actor actor) {
				GamePreferences.instance.load();
				String classType = (String) listClasses.getItems().get(listClasses.getSelectedIndex());
				classType = classType.toLowerCase().replaceAll(" ", "");
				game.player = new Character('f', tfPlayerName.getText(),classType);

				GamePreferences.instance.save();
				game.player.setName(tfPlayerName.getText());
				
				btnMenuPlay = buildBtnMenuPlay(false);
				//				stage.addActor(game.player);
				//				showClassDescWindow(true, true);		
			}
		});
		// ajout de la List dans un scrollPane, pour pouvoir derouler,
		// descendre, monter
		ScrollPane scrollPaneClasses = new ScrollPane(listClasses, skin);
		return scrollPaneClasses;
	}


	/**
	 * construit le bouton pour jour
	 * 
	 * @param style
	 * @return
	 */
	private TextButton buildBtnMenuPlay(boolean isDisabled) {
		stage.getActors().removeValue(btnMenuPlay, false);

		btnMenuPlay = new TextButton("Démarer la partie ", enablePlayButton(isDisabled));
		btnMenuPlay.padBottom(15);
		btnMenuPlay.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				game.changeScreen(Constants.MAPSCREEN);
			}
		});
		btnMenuPlay.setPosition((float) (Constants.VIEWPORT_GUI_WIDTH *0.5 - btnMenuPlay.getWidth() /2  ),
				Constants.VIEWPORT_GUI_HEIGHT / 6);
		btnMenuPlay.setDisabled(isDisabled);
		stage.addActor(btnMenuPlay);
		return btnMenuPlay;
	}
	/**
	 * creation de l'image du titre
	 * 
	 * @param atlas
	 * @return
	 */
	private Table buildTitleLayer(TextureAtlas atlas) {
		Table t = new Table();
		Image imgTitle = new Image(atlas.findRegion("logo"));
		imgTitle.setSize((float) (Constants.VIEWPORT_GUI_WIDTH*0.5), (float) (Constants.VIEWPORT_GUI_HEIGHT*0.35));
		imgTitle.setPosition(Constants.VIEWPORT_GUI_WIDTH / 3 - imgTitle.getWidth(), (float) (Constants.VIEWPORT_GUI_HEIGHT /2 - imgTitle.getHeight()));
		imgTitle.addAction(sequence(Actions.fadeOut(0.0001f),Actions.fadeIn(3f)));
		imgTitle.pack();
		t.add(imgTitle).width((float) (Constants.VIEWPORT_GUI_WIDTH*0.5)).height((float) (Constants.VIEWPORT_GUI_HEIGHT*0.35));
		t.top();
		t.pack();
		return t;
	}
	private Image buildBackgroundLayer() {
		TextureAtlas atlas = MyGame.manager.get("ui/scroll.pack",
				TextureAtlas.class);
		Image scrollingImage = new Image(atlas.findRegion("Scroll_neige"));
		scrollingImage.setHeight(Constants.VIEWPORT_GUI_HEIGHT);
		scrollingImage.setPosition(0, 0);


		return scrollingImage;
	}
	
	private TextButtonStyle enablePlayButton(boolean isDisabled){
		TextureAtlas atlas = MyGame.manager.get("ui/ui.pack",TextureAtlas.class);
		TextButtonStyle btntyle = new TextButtonStyle();
		btntyle.font = skin.getFont("default-font");


		if(! isDisabled){
			TextureRegion redButton = new TextureRegion(atlas.findRegion("red_button"));
			//creation style correspondant
			btntyle.up = new TextureRegionDrawable(redButton);
			
		}
		else {
			TextureRegion greyButton = new TextureRegion(atlas.findRegion("grey_button"));
			//creation style correspondant
			btntyle.up = new TextureRegionDrawable(greyButton);
			
		}
		return btntyle;


	}

	public void destroy() {
	};

	@Override
	public void dispose() {
		skin.dispose();
		batch.dispose();
		stage.dispose();
	};
}
