package by.unick.glowdecorator;

import android.view.View;

/**
 * Created by Nick Unuchek on 31.05.2017.
 */

public class GlowCreator {
    private final GlowDecorator mGlowDecorator;
    private final GlowSettings.GlowSettingsBuilder mGlowSettingsBuilder;

    public GlowCreator(GlowDecorator glowDecorator) {
        this.mGlowDecorator = glowDecorator;
        mGlowSettingsBuilder = GlowSettings.builder();
    }

    public GlowCreator glowRadius(int glowRadius) {
        mGlowSettingsBuilder.glowRadius(glowRadius);
        return this;
    }

    public GlowCreator margin(int margin) {
        mGlowSettingsBuilder.margin(margin);
        return this;
    }

    public GlowCreator glowColor(int glowColor) {
        mGlowSettingsBuilder.glowColor(glowColor);
        return this;
    }

    public void applyTo(final View targetView) {
        targetView.setAlpha(0f);
        targetView.post(new Runnable() {
            @Override
            public void run() {
                mGlowDecorator.dropGlowBlur(targetView, mGlowSettingsBuilder.build());
            }
        });
    }

}
