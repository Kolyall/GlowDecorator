package by.unick.glowdecorator;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.DimenRes;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import java.lang.ref.WeakReference;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GlowDecorator {
    public static final String TAG = GlowDecorator.class.getSimpleName();

    private final WeakReference<Context> mWeakCtx;

    private Handler mHandler;
    private ExecutorService mExecutor;

    public static GlowCreator get(Context context) {
        GlowDecorator glowDecorator = new GlowDecorator(context);
        return new GlowCreator(glowDecorator);
    }

    private GlowDecorator(Context context) {
        mWeakCtx = new WeakReference<Context>(context);
        mHandler = new Handler(Looper.getMainLooper());
        mExecutor = Executors.newSingleThreadExecutor();
    }

    public void dropGlowBlur(final View aView, final GlowSettings glowSettings) {
        final int viewWidth = aView.getWidth();
        final int viewHeight = aView.getHeight();
        mExecutor.execute(new Runnable() {
            @Override
            public void run() {
                Bitmap bitmapCurrent = null;

                // An added margin to the initial image
                int margin = glowSettings.getMargin();
                @DimenRes int marginDimenRes = glowSettings.getMarginDimenRes();
                if (marginDimenRes != 0) {
                    margin = aView.getContext().getResources().getDimensionPixelSize(marginDimenRes);
                }
                int halfMargin = margin / 2;

                if (glowSettings.getImageRes()!=0){
                    Context ctx = mWeakCtx.get();
                    if (ctx == null) {
                        return;
                    }
                    bitmapCurrent = BitmapFactory.decodeResource(ctx.getResources(),glowSettings.getImageRes());
                    Log.e(TAG, "run: " );
                }else if (aView instanceof ImageView){
                    Drawable drawable = ((ImageView) aView).getDrawable();
                    BitmapDrawable bitmapDrawable = ((BitmapDrawable) drawable);
                    bitmapCurrent = bitmapDrawable .getBitmap();
                }else{
                    bitmapCurrent = convertToBitmap(aView.getBackground(), viewWidth, viewHeight);
                }

                if (bitmapCurrent==null && aView instanceof ImageView){
                    return;
                }

                if (bitmapCurrent==null) return;

                // the glow radius
                int glowRadius = glowSettings.getGlowRadius();
                @DimenRes int glowRadiusDimenRes = glowSettings.getGlowRadiusDimenRes();
                if (glowRadiusDimenRes != 0) {
                    glowRadius = aView.getContext().getResources().getDimensionPixelSize(glowRadiusDimenRes);
                }

                // The original image to use
                // extract the alpha from the source image
                Bitmap alpha = bitmapCurrent.extractAlpha();

                // The output bitmap (with the icon + glow)
                final Bitmap bmp = Bitmap.createBitmap(bitmapCurrent.getWidth() + margin,
                        bitmapCurrent.getHeight() + margin, Bitmap.Config.ARGB_8888);

                // The canvas to paint on the image
                Canvas canvas = new Canvas(bmp);

                Paint paint = new Paint();
                @ColorInt int glowColor = glowSettings.getGlowColor();
                @ColorRes int glowColorRes = glowSettings.getGlowColorRes();
                if (glowColorRes != 0) {
                    glowColor = ContextCompat.getColor(aView.getContext(),glowColorRes);
                }
                paint.setColor(glowColor);

                // outer glow
                paint.setMaskFilter(new BlurMaskFilter(glowRadius, BlurMaskFilter.Blur.OUTER));
                canvas.drawBitmap(alpha, halfMargin, halfMargin, paint);

                // original icon
                canvas.drawBitmap(bitmapCurrent, halfMargin, halfMargin, null);

                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        Context ctx = mWeakCtx.get();
                        if (ctx == null) {
                            return;
                        }

                        if (aView instanceof ImageView){
                            ((ImageView)aView).setImageDrawable(new BitmapDrawable(ctx.getResources(), bmp));
                        }else{
                            aView.setBackground(new BitmapDrawable(ctx.getResources(), bmp));
                        }

                        aView.animate()
                                .alpha(1f)
                                .setDuration(glowSettings.getAnimationDuration())
                                .setInterpolator(new LinearInterpolator())
                                .start();
                    }
                });
            }
        });
    }

    @Nullable
    public static Bitmap convertToBitmap(@Nullable Drawable drawable, int widthPixels, int heightPixels) {
        if (drawable == null || widthPixels == 0 || heightPixels == 0) return null;
        try {
            Bitmap bitmapRet = Bitmap.createBitmap(widthPixels, heightPixels, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmapRet);
            drawable.setBounds(0, 0, widthPixels, heightPixels);
            drawable.draw(canvas);
            return bitmapRet;
        }catch (Exception e){
            return null;
        }
    }
}
