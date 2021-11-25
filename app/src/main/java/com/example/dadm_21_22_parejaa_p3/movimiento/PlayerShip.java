package com.example.dadm_21_22_parejaa_p3.movimiento;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.example.dadm_21_22_parejaa_p3.R;
import com.example.dadm_21_22_parejaa_p3.engine.GameEngine;
import com.example.dadm_21_22_parejaa_p3.input.InputController;
import java.util.ArrayList;
import java.util.List;

public class PlayerShip extends Player{

    private final ImageView lapiz;  // SPRITE LAPIZ

    // LISTA DE BALAS
    private static final int INITIAL_BULLET_POOL_AMOUNT = 6;
    List<Bullet> bullets = new ArrayList<Bullet>();

    // TIEMPO ENTRE DISPAROS
    private static final long TIME_BETWEEN_BULLETS = 250;
    private long timeSinceLastFire;

    // CONSTRUCTOR
    public PlayerShip(final View view) {
        super(view);

        lapiz = new ImageView(view.getContext());
        Drawable lapizDrawable = view.getContext().getResources().getDrawable(R.drawable.lapiz);
        lapiz.setLayoutParams(new ViewGroup.LayoutParams(
                (int) (lapizDrawable.getIntrinsicWidth() * pixelFactor),
                (int) (lapizDrawable.getIntrinsicHeight() * pixelFactor)));
        lapiz.setImageDrawable(lapizDrawable);
        ((FrameLayout) view).addView(lapiz);

        maxX -= (lapizDrawable.getIntrinsicWidth() * pixelFactor);
        maxY -= (lapizDrawable.getIntrinsicHeight() * pixelFactor);

        // SE LLENA LA LISTA DE BALAS
        for (int i=0; i<INITIAL_BULLET_POOL_AMOUNT; i++) {
            bullets.add(new Bullet(view));
        }
    }

    // SI LA LISTA DE BALAS ESTÁ VACÍA, NO DISPARA Y DEVUELVE NULL
    private Bullet getBullet() {
        if (bullets.isEmpty()) {
            return null;
        }
        return bullets.remove(0);
    }

    void releaseBullet(Bullet bullet) {
        bullets.add(bullet);
    }


    @Override
    public void onUpdate(long elapsedMillis, GameEngine gameEngine) {
        // Get the info from the inputController
        updatePosition(elapsedMillis, gameEngine.ge_inputController);
        checkFiring(elapsedMillis, gameEngine);
    }

    // PARA ACTUALIZAR LA POSICION DEL LAPIZ
    private void updatePosition(long elapsedMillis, InputController inputController) {
        positionX += speedFactor * inputController.horizontalFactor * elapsedMillis;
        if (positionX < 0) {
            positionX = 0;
        }
        if (positionX > maxX) {
            positionX = maxX;
        }
        positionY += speedFactor * inputController.verticalFactor * elapsedMillis;
        if (positionY < 0) {
            positionY = 0;
        }
        if (positionY > maxY) {
            positionY = maxY;
        }
    }

    // SE VERIFICA SI SE PUEDE DISPARAR
    private void checkFiring(long elapsedMillis, GameEngine gameEngine) {
        // SI ESTÁ DISPARANDO Y HA PASADO EL TIEMPO NECESARIO...
        if (gameEngine.ge_inputController.isFiring && timeSinceLastFire > TIME_BETWEEN_BULLETS) {
            Bullet bullet = getBullet();
            if (bullet == null) {
                return;
            }
            // SE CREA LA BALA EN LA POSICION DEL JUGADOR
            bullet.init(this, positionX + lapiz.getWidth()/2, positionY);
            gameEngine.addGameObject(bullet);
            timeSinceLastFire = 0;
        }
        else {
            // SE SUMA EL TIEMPO QUE HA PASADO
            timeSinceLastFire += elapsedMillis;
        }
    }

    @Override
    public void onDraw() {
        lapiz.setTranslationX((int)positionX);
        lapiz.setTranslationY((int)positionY);
    }
}
