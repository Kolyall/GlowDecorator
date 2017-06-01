package by.unick.glowdecorator;

import android.graphics.Color;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;

/**
 * Created by Nick Unuchek on 31.05.2017.
 */
public class GlowSettings {
    int margin;
    int glowRadius;
    @ColorInt int glowColor;
    int animationDuration;
    @DrawableRes int imageRes;

    private GlowSettings(int margin, int glowRadius, int glowColor, int animationDuration, int imageRes) {
        this.margin = margin;
        this.glowRadius = glowRadius;
        this.glowColor = glowColor;
        this.animationDuration = animationDuration;
        this.imageRes = imageRes;
    }

    public int getMargin() {
        return margin;
    }

    public int getGlowRadius() {
        return glowRadius;
    }

    public int getGlowColor() {
        return glowColor;
    }

    public int getAnimationDuration() {
        return animationDuration;
    }

    public int getImageRes() {
        return imageRes;
    }

    public static GlowSettingsBuilder builder() {
        return new GlowSettingsBuilder();
    }

    public static class GlowSettingsBuilder {
        private int margin = 24;
        private int glowRadius = 16;
        private int glowColor = Color.WHITE;
        private int animationDuration = 300;
        private int imageRes = 0;

        public GlowSettingsBuilder margin(int margin) {
            this.margin = margin;
            return this;
        }

        public GlowSettingsBuilder glowRadius(int glowRadius) {
            this.glowRadius = glowRadius;
            return this;
        }
        public GlowSettingsBuilder imageRes(int imageRes) {
            this.imageRes = imageRes;
            return this;
        }

        public GlowSettingsBuilder glowColor(int glowColor) {
            this.glowColor = glowColor;
            return this;
        }

        public GlowSettingsBuilder animationDuration(int animationDuration) {
            this.animationDuration = animationDuration;
            return this;
        }

        public GlowSettings build() {
            return new GlowSettings(margin, glowRadius, glowColor, animationDuration, imageRes);
        }
    }
}
