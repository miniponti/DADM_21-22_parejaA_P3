package com.example.dadm_21_22_parejaa_p3.engine;

public class UpdateThread extends Thread {

    // VARIABLES
    private final GameEngine theGameEngine;
    private boolean isGameRunning = true;
    private boolean isGamePaused = false;

    private Object synchroLock = new Object();

    // CONSTRUCTOR
    public UpdateThread(GameEngine gameEngine) {
        theGameEngine = gameEngine;
    }

    public void ut_start() {
        isGameRunning = true;
        isGamePaused = false;
        super.start();
    }

    public void ut_stop() {
        isGameRunning = false;
        ut_resume();
    }

    @Override
    public void run() {
        long previousTimeMillis;
        long currentTimeMillis;
        long elapsedMillis;
        previousTimeMillis = System.currentTimeMillis();

        while (isGameRunning) {
            currentTimeMillis = System.currentTimeMillis();
            elapsedMillis = currentTimeMillis - previousTimeMillis;
            if (isGamePaused) {
                while (isGamePaused) {
                    try {
                        synchronized (synchroLock) {
                            synchroLock.wait();
                        }
                    } catch (InterruptedException e) {
                        // We stay on the loop
                    }
                }
                currentTimeMillis = System.currentTimeMillis();
            }
            theGameEngine.onUpdate(elapsedMillis);
            previousTimeMillis = currentTimeMillis;
        }
    }

    // DEVUELVE EL ESTADO DE JUEGO COMO PAUSADO
    public void ut_pause() {
        isGamePaused = true;
    }

    // DEVUELVE EL ESTADO DE JUEGO COMO REANUDADO
    public void ut_resume() {
        if (isGamePaused == true) {
            isGamePaused = false;
//            synchronized (synchroLock) {
//                synchroLock.notify();
//            }
        }
    }

    public boolean isGameRunning() {
        return isGameRunning;
    }

    public boolean isGamePaused() {
        return isGamePaused;
    }
}
