package com.example.dadm_21_22_parejaa_p3.engine;

public abstract class GameObject {

    public abstract void startGame();

    public abstract void onUpdate(long elapsedMillis, GameEngine gameEngine);

    public abstract void onDraw();

    public final Runnable onAddedRunnable = new Runnable() {
        @Override
        public void run() {
            onAddedToGameUiThread();
        }
    };

    public void onAddedToGameUiThread(){
    }

    public final Runnable onRemovedRunnable = new Runnable() {
        @Override
        public void run() {
            onRemovedFromGameUiThread();
        }
    };

    public void onRemovedFromGameUiThread(){
    }

}
