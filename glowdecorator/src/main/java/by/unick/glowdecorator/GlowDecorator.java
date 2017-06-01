package by.unick.glowdecorator;

/**
 * Created by Nick Unuchek on 31.05.2017.
 */

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
import android.view.View;
import android.view.animation.LinearInterpolator;

import java.lang.ref.WeakReference;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GlowDecorator {

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

    /**
     * Drops a shadow around the passed in view using Box blur.
     *
     * @param aView        - view which to decorate with a shadow.
     * @param glowSettings
     * @return void.
     */
    public void dropGlowBlur(final View aView, final GlowSettings glowSettings) {
        final int viewWidth = aView.getWidth();
        final int viewHeight = aView.getHeight();
        mExecutor.execute(new Runnable() {
            @Override
            public void run() {
                Bitmap bitmapCurrent;

                // An added margin to the initial image
                int margin = glowSettings.getMargin();
                int halfMargin = margin / 2;

                // the glow radius
                int glowRadius = glowSettings.getGlowRadius();

                if (glowSettings.getImageRes()!=0){
                    Context ctx = mWeakCtx.get();
                    if (ctx == null) {
                        return;
                    }
                    bitmapCurrent = BitmapFactory.decodeResource(ctx.getResources(),glowSettings.getImageRes());
                }else{
                    bitmapCurrent = convertToBitmap(aView.getBackground(), viewWidth, viewHeight);
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
                paint.setColor(glowSettings.getGlowColor());

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

                        aView.setBackground(new BitmapDrawable(ctx.getResources(), bmp));
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


    /* Utils */
    public static Bitmap convertToBitmap(Drawable drawable, int widthPixels, int heightPixels) {
        Bitmap bitmapRet = Bitmap.createBitmap(widthPixels, heightPixels, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmapRet);
        drawable.setBounds(0, 0, widthPixels, heightPixels);
        drawable.draw(canvas);

        return bitmapRet;
    }
}
