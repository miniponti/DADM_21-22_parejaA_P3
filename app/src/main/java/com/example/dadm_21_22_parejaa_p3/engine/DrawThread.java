package com.example.dadm_21_22_parejaa_p3.engine;

import java.util.Timer;
import java.util.TimerTask;

public class DrawThread {

    // VARIABLES
    private static int EXPECTED_FPS = 30;
    private static final long TIME_BETWEEN_DRAWS = 1000 / EXPECTED_FPS;

    private final GameEngine gE_drawThread;
    private Timer timer;

    // CONSTRUCTOR
    public DrawThread(GameEngine gameEngine) {
        gE_drawThread = gameEngine;
    }

    // EMPIEZA EL CONTADOR
    public void startTimer() {
        stopTimer();
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                gE_drawThread.onDraw();
            }
        }, 0, TIME_BETWEEN_DRAWS);
    }

    // DETIENE EL TIMER
    public void stopTimer() {
        if (timer != null) {
            timer.cancel();
            // quita las tareas canceladas de la cola
            timer.purge();
        }
    }

//    public void resumeGame() {
//        startTimer();
//    }
}
