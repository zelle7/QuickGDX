package eu.quickgdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import eu.quickgdx.game.utils.AssetConfig;
import eu.quickgdx.game.utils.AssetManager;
import eu.quickgdx.game.utils.ScreenManager;

/**
 * Created by Mathias Lux, mathias@juggle.at,  on 04.02.2016.
 */
public class CreditsScreen extends ScreenAdapter {
    private final SpriteBatch batch;
    private final OrthographicCamera cam;
    private AssetManager assetManager;

    Texture backgroundImage, gradientTop, gradientBottom;
    BitmapFont creditsFont;

    String[] credits = ("GdxGameSkelet0n by Mathias Lux\n" +
            "All assets are public d0main\n" +
            "Cl0ne and adapt t0 y0ur will\n" +
            "\n" +
            "H0pe it helps ;)").split("\\n");
    private float moveY;


    public CreditsScreen(AssetManager game) {
        this.assetManager = game;

        backgroundImage = assetManager.getTextureAsset(AssetConfig.MENU_BACKGROUND);
        gradientTop = assetManager.getTextureAsset(AssetConfig.CREDITS_GRADIENT_TOP);
        gradientBottom = assetManager.getTextureAsset(AssetConfig.CREDITS_GRADIENT_BOTTOM);

        creditsFont = assetManager.get(AssetConfig.FONT_RAVIE_42.assetDescriptor.fileName, BitmapFont.class);

        // Create camera that projects the game onto the actual screen size.
        cam = new OrthographicCamera(AssetManager.GAME_WIDTH, AssetManager.GAME_HEIGHT);

        cam.position.set(cam.viewportWidth / 2f, cam.viewportHeight / 2f, 0);
        cam.update();

        batch = new SpriteBatch();
    }

    @Override
    public void render(float delta) {
        moveY += delta * 100;
        handleInput();
        // camera:
        cam.update();
        batch.setProjectionMatrix(cam.combined);


        Gdx.gl.glClearColor(0.3f, 0.3f, 0.3f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        // draw bgImage
        batch.draw(backgroundImage, 0, 0, AssetManager.GAME_WIDTH, AssetManager.GAME_HEIGHT);

        // draw moving text:
        for (int i = 0; i < credits.length; i++) {
            creditsFont.draw(batch, credits[i], AssetManager.GAME_WIDTH / 8, moveY - i * creditsFont.getLineHeight() * 1.5f);
        }


        // draw gradient
        batch.draw(gradientBottom, 0, 0, AssetManager.GAME_WIDTH, gradientBottom.getHeight());
        batch.draw(gradientTop, 0, AssetManager.GAME_HEIGHT - gradientTop.getHeight(), AssetManager.GAME_WIDTH, gradientTop.getHeight());

        batch.end();
    }

    private void handleInput() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.ANY_KEY) || Gdx.input.justTouched()) {
            assetManager.getScreenManager().setCurrentState(ScreenManager.ScreenState.Menu);
        }
    }


}
