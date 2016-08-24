package eu.quickgdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import eu.quickgdx.game.Mechanics.World;
import eu.quickgdx.game.QuickGdx;

/**
 * Created by Mathias Lux, mathias@juggle.at,  on 04.02.2016.
 */
public class GameplayScreen extends ScreenAdapter {
    private final SpriteBatch batch;
    public final OrthographicCamera cam;
    public QuickGdx parentGame;

    Texture backgroundImage;
    BitmapFont menuFont;
    World world;
    String[] menuStrings = {"Play", "Credits", "Exit"};
    int currentMenuItem = 0;

    float offsetLeft = QuickGdx.GAME_WIDTH / 8, offsetTop = QuickGdx.GAME_WIDTH / 8, offsetY = QuickGdx.GAME_HEIGHT / 8;


    public GameplayScreen(QuickGdx game) {
        this.parentGame = game;
        this.world = new World(this);

        backgroundImage = parentGame.getAssetManager().get("menu/menu_background.jpg");
        menuFont = parentGame.getAssetManager().get("menu/Ravie_72.fnt");
        menuFont.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        // Create camera that projects the game onto the actual screen size.
        cam = new OrthographicCamera(QuickGdx.GAME_WIDTH, QuickGdx.GAME_HEIGHT);

        cam.position.set(cam.viewportWidth / 2f, cam.viewportHeight / 2f, 0);
        cam.update();

        batch = new SpriteBatch();
    }

    @Override
    public void render(float delta) {
        handleInput();
        // camera:
        cam.update();
        batch.setProjectionMatrix(cam.combined);


        Gdx.gl.glClearColor(0.3f, 0.3f, 0.3f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        world.update(delta);
        world.render(delta);

//        batch.begin();
//        // draw bgImage ...
//        batch.draw(backgroundImage, 0, 0, GdxGame.GAME_WIDTH, GdxGame.GAME_HEIGHT);
//        // draw Strings ...
//        for (int i = 0; i < menuStrings.length; i++) {
//            if (i == currentMenuItem) menuFont.setColor(0.2f, 1f, 0.2f, 1f);
//            else menuFont.setColor(0.2f, 0.2f, 1f, 1f);
//            menuFont.draw(batch, menuStrings[i], offsetLeft, GdxGame.GAME_HEIGHT - offsetTop - i * offsetY);
//        }
//        batch.end();
    }

    private void handleInput() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            System.out.println("ESCAPE PRESSED");
            parentGame.getSoundManager().playEvent("blip");
        }
    }

}