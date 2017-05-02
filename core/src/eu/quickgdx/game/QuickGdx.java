package eu.quickgdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import eu.quickgdx.game.utils.AssetManager;
import eu.quickgdx.game.utils.SoundManager;

/**
 * Main entry point in our game. Asset loading should be done here.
 * provides you with the basic manager classes (assets, screen, sound) and the animator helper class
 */
public class QuickGdx extends ApplicationAdapter {
    private SpriteBatch batch;
    private AssetManager assMan;
    private eu.quickgdx.game.utils.ScreenManager screenManager;
    private eu.quickgdx.game.utils.SoundManager soundManager;
    private Animator animator;

    // gives the original size for all screen working with the scaling orthographic camera
    // set in DesktopLauncher to any resolution and it will be scaled automatically.
    public static final int GAME_WIDTH = 1366;
    public static final int GAME_HEIGHT = 768;


    @Override
    public void create() {
        screenManager = new eu.quickgdx.game.utils.ScreenManager(this);
        soundManager = new eu.quickgdx.game.utils.SoundManager(this);
        assMan = new AssetManager();
        //TODO do not load here all assets do that with an intelligent loading mechanism
        animator = new Animator(assMan);
    }

    @Override
    public void render() {
        screenManager.getCurrentScreen().render(Gdx.graphics.getDeltaTime());
    }

    public AssetManager getAssetManager() {
        return assMan;
    }

    public eu.quickgdx.game.utils.ScreenManager getScreenManager() {
        return screenManager;
    }

    public SoundManager getSoundManager() {
        return soundManager;
    }

    public Animator getAnimator() {
        return animator;
    }
}
