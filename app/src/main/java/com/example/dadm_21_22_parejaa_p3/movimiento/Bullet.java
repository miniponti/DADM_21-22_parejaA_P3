package com.example.dadm_21_22_parejaa_p3.movimiento;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.example.dadm_21_22_parejaa_p3.R;
import com.example.dadm_21_22_parejaa_p3.engine.GameEngine;

public class Bullet extends Player {

    private final ImageView bulletImageView;
    private final double imageHeight;
    private final double imageWidth;

    private PlayerShip parent;

    // CONSTRUCTOR
    public Bullet(View parentView){//}, double pixelFactor) {
        super(parentView);

        // VELOCIDAD DE 300 UNIDADES POR SEGUNDO
        speedFactor = pixelFactor * -300d / 1000d;

        // SE CREA EL SPRITE DE LA BALA
        bulletImageView = new ImageView(parentView.getContext());
        Drawable bulletDrawable = parentView.getContext().getResources().getDrawable(R.drawable.bullet);
        imageWidth = bulletDrawable.getIntrinsicWidth() * pixelFactor;
        imageHeight = bulletDrawable.getIntrinsicHeight() * pixelFactor;
        bulletImageView.setLayoutParams(new ViewGroup.LayoutParams(
                (int) (imageWidth),
                (int) (imageHeight)));
        bulletImageView.setImageDrawable(bulletDrawable);
        bulletImageView.setVisibility(View.GONE);
        ((FrameLayout) parentView).addView(bulletImageView);

    }

    public void init(PlayerShip parentPlayer, double initPositionX, double initPositionY) {
        positionX = initPositionX - imageWidth/2;
        positionY = initPositionY - imageHeight/2;
        parent = parentPlayer;
    }

    @Override
    public void startGame() {}

    @Override
    public void onUpdate(long elapsedMillis, GameEngine gameEngine) {
        positionY += speedFactor * elapsedMillis;
        if (positionY < -imageHeight) {
            gameEngine.removeGameObject(this);
            // And return it to the pool
            parent.releaseBullet(this);
        }YA
    }

    @Override
    public void onRemovedFromGameUiThread() {
        bulletImageView.setVisibility(View.GONE);
    }

    @Override
    public void onAddedToGameUiThread() {
        bulletImageView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onDraw() {
        bulletImageView.animate().translationX((int) positionX).translationY((int) positionY)
                .setDuration(1)
                .start();
    }

}
