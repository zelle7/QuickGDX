package eu.quickgdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import eu.quickgdx.game.QuickGdx;
import eu.quickgdx.game.ScreenManager;

/**
 * Created by Mathias Lux, mathias@juggle.at,  on 04.02.2016.
 */
public class CreditsScreen extends ScreenAdapter {
    private final SpriteBatch batch;
    private final OrthographicCamera cam;
    private QuickGdx parentGame;

    Texture backgroundImage, gradientTop, gradientBottom;
    BitmapFont creditsFont;

    String[] credits = ("GdxGameSkelet0n by Mathias Lux\n" +
            "All assets are public d0main\n" +
            "Cl0ne and adapt t0 y0ur will\n" +
            "\n" +
            "H0pe it helps ;)").split("\\n");
    private float moveY;


    public CreditsScreen(QuickGdx game) {
        this.parentGame = game;

        backgroundImage = parentGame.getAssetManager().get("menu/menu_background.jpg");
        gradientTop = parentGame.getAssetManager().get("credits/gradient_top.png");
        gradientBottom = parentGame.getAssetManager().get("credits/gradient_bottom.png");

        creditsFont = parentGame.getAssetManager().get("menu/Ravie_42.fnt");

        // Create camera that projects the game onto the actual screen size.
        cam = new OrthographicCamera(QuickGdx.GAME_WIDTH, QuickGdx.GAME_HEIGHT);

        cam.position.set(cam.viewportWidth / 2f, cam.viewportHeight / 2f, 0);
        cam.update();

        batch = new SpriteBatch();
    }

    @Override
    public void render(float delta) {
        moveY += delta*100;
        handleInput();
        // camera:
        cam.update();
        batch.setProjectionMatrix(cam.combined);


        Gdx.gl.glClearColor(0.3f, 0.3f, 0.3f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        // draw bgImage
        batch.draw(backgroundImage, 0, 0, QuickGdx.GAME_WIDTH, QuickGdx.GAME_HEIGHT);

        // draw moving text:
        for (int i = 0; i < credits.length; i++) {
            creditsFont.draw(batch, credits[i], QuickGdx.GAME_WIDTH/8, moveY - i*creditsFont.getLineHeight()*1.5f);
        }


        // draw gradient
        batch.draw(gradientBottom, 0, 0, QuickGdx.GAME_WIDTH, gradientBottom.getHeight());
        batch.draw(gradientTop, 0, QuickGdx.GAME_HEIGHT-gradientTop.getHeight(), QuickGdx.GAME_WIDTH, gradientTop.getHeight());

        batch.end();
    }

    private void handleInput() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.ANY_KEY) || Gdx.input.justTouched()) {
            parentGame.getScreenManager().setCurrentState(ScreenManager.ScreenState.Menu);
        }
    }


}
