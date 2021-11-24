package com.example.dadm_21_22_parejaa_p3.movimiento;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.example.dadm_21_22_parejaa_p3.R;

public class PlayerShip extends Player{

    private final ImageView lapiz;

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
    }

    @Override
    public void onDraw() {
        lapiz.setTranslationX((int)positionX);
        lapiz.setTranslationY((int)positionY);
    }
}
