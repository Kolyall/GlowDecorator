package by.unick.glowdecorator;

import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.DimenRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.IntRange;
import android.view.View;

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

    public GlowCreator glowRadiusDimenRes(@DimenRes int glowRadiusDimenRes) {
        mGlowSettingsBuilder.glowRadiusDimenRes(glowRadiusDimenRes);
        return this;
    }

    public GlowCreator margin(int margin) {
        mGlowSettingsBuilder.margin(margin);
        return this;
    }
    public GlowCreator marginDimenRes(@DimenRes int marginDimenRes) {
        mGlowSettingsBuilder.marginDimenRes(marginDimenRes);
        return this;
    }

    public GlowCreator glowColor(@ColorInt int glowColor) {
        mGlowSettingsBuilder.glowColor(glowColor);
        return this;
    }
    public GlowCreator glowColorRes(@ColorRes int glowColorRes) {
        mGlowSettingsBuilder.glowColorRes(glowColorRes);
        return this;
    }
    public GlowCreator animationDuration(@IntRange(from = 1) int animationDuration) {
        mGlowSettingsBuilder.animationDuration(animationDuration);
        return this;
    }
    public GlowCreator imageRes(@DrawableRes int imageRes) {
        mGlowSettingsBuilder.imageRes(imageRes);
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
