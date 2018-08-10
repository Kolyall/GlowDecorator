package by.unick.glowdecorator;

import android.graphics.Color;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.DimenRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.IntRange;

public class GlowSettings {
    private int margin;
    @DimenRes private int marginDimenRes;
    private int glowRadius;
    @DimenRes private int glowRadiusDimenRes;
    @ColorInt private int glowColor;
    @ColorRes private int glowColorRes;
    private int animationDuration;
    @DrawableRes private int imageRes;

    private GlowSettings(int margin,@DimenRes int marginDimenRes, int glowRadius, @DimenRes int glowRadiusDimenRes,@ColorInt int glowColor,@ColorRes int glowColorRes, int animationDuration, int imageRes) {
        this.margin = margin;
        this.marginDimenRes = marginDimenRes;
        this.glowRadius = glowRadius;
        this.glowRadiusDimenRes = glowRadiusDimenRes;
        this.glowColor = glowColor;
        this.glowColorRes = glowColorRes;
        this.animationDuration = animationDuration;
        this.imageRes = imageRes;
    }

    public int getMargin() {
        return margin;
    }

    @DimenRes
    public int getMarginDimenRes() {
        return marginDimenRes;
    }

    public int getGlowRadius() {
        return glowRadius;
    }

    @DimenRes
    public int getGlowRadiusDimenRes() {
        return glowRadiusDimenRes;
    }

    @ColorInt
    public int getGlowColor() {
        return glowColor;
    }

    @ColorRes
    public int getGlowColorRes() {
        return glowColorRes;
    }

    public int getAnimationDuration() {
        return animationDuration;
    }

    @DrawableRes
    public int getImageRes() {
        return imageRes;
    }

    public static GlowSettingsBuilder builder() {
        return new GlowSettingsBuilder();
    }

    public static class GlowSettingsBuilder {
        private int margin = 24;
        @DimenRes private int marginDimenRes = 0;
        private int glowRadius = 16;
        @DimenRes private int glowRadiusDimenRes = 0;
        @ColorInt private int glowColor = Color.WHITE;
        @ColorRes private int glowColorRes = 0;
        private int animationDuration = 300;
        @DrawableRes private int imageRes = 0;


        public GlowSettingsBuilder margin(int margin) {
            this.margin = margin;
            return this;
        }

        public GlowSettingsBuilder marginDimenRes(@DimenRes int marginDimenRes) {
            this.marginDimenRes = marginDimenRes;
            return this;
        }

        public GlowSettingsBuilder glowRadius(int glowRadius) {
            this.glowRadius = glowRadius;
            return this;
        }
        public GlowSettingsBuilder glowRadiusDimenRes(@DimenRes int glowRadiusDimenRes) {
            this.glowRadiusDimenRes = glowRadiusDimenRes;
            return this;
        }
        public GlowSettingsBuilder imageRes(@DrawableRes int imageRes) {
            this.imageRes = imageRes;
            return this;
        }

        public GlowSettingsBuilder glowColor(@ColorInt int glowColor) {
            this.glowColor = glowColor;
            return this;
        }

        public GlowSettingsBuilder glowColorRes(@ColorRes int glowColorRes) {
            this.glowColorRes = glowColorRes;
            return this;
        }

        public GlowSettingsBuilder animationDuration(@IntRange(from = 1) int animationDuration) {
            this.animationDuration = animationDuration;
            return this;
        }

        public GlowSettings build() {
            return new GlowSettings(margin,marginDimenRes, glowRadius, glowRadiusDimenRes, glowColor,glowColorRes, animationDuration, imageRes);
        }
    }
}
