package eu.quickgdx.game.utils;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.GdxRuntimeException;

/**
 * Created by chzellot on 02.05.17.
 */

public class AssetManager extends com.badlogic.gdx.assets.AssetManager {

    public Texture getTextureAsset(AssetConfig assetConfig) {
        if (!assetConfig.assetDescriptor.type.equals(Texture.class)) {
            throw new GdxRuntimeException("you can only load textures with this class");
        }
        return get(assetConfig.assetDescriptor.fileName);
    }

    public synchronized void load(AssetConfig assetConfig) {
        if (assetConfig.type.equals(AssetConfig.AssetConfigType.STATIC)) {
            load(assetConfig);
        } else {
            for (int i = 0; i < assetConfig.getNrFrames(); i++) {
                load(String.format(assetConfig.assetDescriptor.fileName, i), Texture.class);
            }
        }
    }
}
