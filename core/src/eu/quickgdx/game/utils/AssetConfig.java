package eu.quickgdx.game.utils;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.GdxRuntimeException;

import eu.quickgdx.game.screens.CreditsScreen;
import eu.quickgdx.game.screens.GameplayScreen;
import eu.quickgdx.game.screens.MenuScreen;

/**
 * Created by chzellot on 02.05.17.
 */

public enum AssetConfig {

    FONT_RAVIE_42(new AssetDescriptor<BitmapFont>("menu/Ravie_42.fnt", BitmapFont.class)),
    FONT_RAVIE_72(new AssetDescriptor<BitmapFont>("menu/Ravie_72.fnt", BitmapFont.class)),
    FONT_RABBID_HIGHWAY_SIGN_II(new AssetDescriptor<BitmapFont>("fonts/RabbidHighwaySignII.fnt", BitmapFont.class)),

    MENU_BACKGROUND(new AssetDescriptor<Texture>("menu/menu_background.jpg", Texture.class), new Class[]{MenuScreen.class, CreditsScreen.class}),
    CREDITS_GRADIENT_TOP(new AssetDescriptor<Texture>("credits/gradient_top.png", Texture.class), new Class[]{CreditsScreen.class}),
    CREDITS_GRADIENT_BOTTOM(new AssetDescriptor<Texture>("credits/gradient_bottom.png", Texture.class), new Class[]{CreditsScreen.class}),

    SOUND_BLIP(new AssetDescriptor<Sound>("sfx/blip.wav", Sound.class)),
    SOUND_EXPLOSION(new AssetDescriptor<Sound>("sfx/explosion.wav", Sound.class)),
    SOUND_HIT(new AssetDescriptor<Sound>("sfx/hit.wav", Sound.class)),
    SOUND_JUMP(new AssetDescriptor<Sound>("sfx/jump.wav", Sound.class)),
    SOUND_LASER(new AssetDescriptor<Sound>("sfx/laser.wav", Sound.class)),
    SOUND_PICKUP(new AssetDescriptor<Sound>("sfx/pickup.wav", Sound.class)),
    SOUND_POWERUP(new AssetDescriptor<Sound>("sfx/powerup.wav", Sound.class)),

    HUD_LIFE_SMALL(new AssetDescriptor<Texture>("hud/life_small.png", Texture.class), new Class[]{GameplayScreen.class}),
    SPRITESHEET(new AssetDescriptor<Texture>("gameplay/spritesheet.png", Texture.class), new Class[]{GameplayScreen.class}),
    CHARACTER_ANIMATION_DOWN(new AssetDescriptor<Texture>("gameplay/movingAnimation_Down.png", Texture.class), new Class[]{GameplayScreen.class}),

    CHARACTER_ANIMATION_UP("gameplay/animation_", 3, "png", new Class[]{GameplayScreen.class}),;

    public final Class[] onScreen;
    public final AssetDescriptor assetDescriptor;
    public final AssetConfigType type;
    protected int nrFrames;

    AssetConfig(String animationPath, int nrFrames, String filetype, Class<? extends ScreenAdapter> onScreen[]) {
        this.onScreen = onScreen;
        this.assetDescriptor = new AssetDescriptor<Texture>(animationPath + "_%s." + filetype, Texture.class);
        if (nrFrames == 0) {
            throw new GdxRuntimeException("animation with 0 frames not allowed");
        }
        this.nrFrames = 0;
        this.type = AssetConfigType.ANIMATION;

    }

    AssetConfig(AssetDescriptor assetDescriptor) {
        this(assetDescriptor, null);
    }

    AssetConfig(AssetDescriptor assetDescriptor, Class<? extends ScreenAdapter> onScreen[]) {
        this.onScreen = onScreen;
        this.assetDescriptor = assetDescriptor;
        this.type = AssetConfigType.STATIC;
    }


    public Array<AssetConfig> getByScreenClass(Class screenClass) {
        Array<AssetConfig> assetConfigs = new Array<AssetConfig>();
        for (AssetConfig assetConfig : values()) {
            for (Class aClass : assetConfig.onScreen) {
                if (aClass.equals(screenClass)) {
                    assetConfigs.add(assetConfig);
                }
            }
        }
        return assetConfigs;
    }

    public Array<AssetConfig> getByScreenClassAndAssetClass(Class screenClass, Class assetClass) {
        Array<AssetConfig> assetConfigs = new Array<AssetConfig>();
        for (AssetConfig assetConfig : values()) {
            boolean candidate = false;
            for (Class aClass : assetConfig.onScreen) {
                if (aClass == null || aClass.equals(screenClass)) { //null means on all
                    candidate = true;
                    break;
                }
            }
            if (candidate && assetClass.equals(assetConfig.assetDescriptor.type)) {
                assetConfigs.add(assetConfig);
            }
        }
        return assetConfigs;
    }


    public int getNrFrames() {
        return nrFrames;
    }

    public enum AssetConfigType {
        ANIMATION,
        STATIC
    }
}
