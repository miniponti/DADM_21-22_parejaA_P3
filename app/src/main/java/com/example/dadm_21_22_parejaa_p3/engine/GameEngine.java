package com.example.dadm_21_22_parejaa_p3.engine;

import android.app.Activity;
import java.util.ArrayList;
import java.util.List;
import com.example.dadm_21_22_parejaa_p3.input.InputController;

public class GameEngine {

    // VARIABLES ---------------------------------------
    private List<GameObject> lista_gameObjects = new ArrayList<GameObject>();
    private List<GameObject> lista_objToAdd = new ArrayList<GameObject>();
    private List<GameObject> lista_objToRemove = new ArrayList<GameObject>();

    private UpdateThread ge_UpdateThread;
    private DrawThread ge_DrawThread;
    public InputController ge_inputController;

    private Activity mainActivity;
    // ------------------------------------------------

    private Runnable drawRunnable = new Runnable() {
        @Override
        public void run() {
            int numGameObjects = lista_gameObjects.size();

            for (int i = 0; i < numGameObjects; i++) {
                lista_gameObjects.get(i).onDraw();
            }
        }
    };

    // CONSTRUCTOR
    public GameEngine(Activity activity) {
        mainActivity = activity;
    }

    // SETTER DE INPUT CONTROLLER
    public void setGe_inputController(InputController inputController) {
        ge_inputController = inputController;
    }

    // METODOS -------------------------------------------------------------

    // FUNCION QUE INICIA EL JUEGO Y PONE EL THREAD EN FUNCIONAMIENTO
    public void ge_startGame() {
        // EN CASO DE QUE HAYA UN JUEGO FUNCIONANDO YA, LO PARA
        ge_stopGame();

        // INICIALIZA LOS GAMEOBJECTS
        int numGameObjects = lista_gameObjects.size();
        for (int i=0; i<numGameObjects; i++) {
            lista_gameObjects.get(i).startGame();
        }

        // INICIA EL UPDATE THREAD
        ge_UpdateThread = new UpdateThread(this);
        ge_UpdateThread.ut_start();

        // INICIA EL THREAD PRINCIPAL
        ge_DrawThread = new DrawThread(this);
        ge_DrawThread.startTimer();
    }

    // DETIENE LOS HILOS
    public void ge_stopGame() {
        // PRIMERO SE COMPRUEBA SI EXISTEN
        if (ge_UpdateThread != null) {
            ge_UpdateThread.ut_stop();
        }
        if (ge_DrawThread != null) {
            ge_DrawThread.stopTimer();
        }
    }

    // PAUSA EL HILO DE ACTUALIZACION Y DETIENE EL DE DIBUJO
    public void ge_pauseGame() {
        if (ge_UpdateThread != null) {
            ge_UpdateThread.ut_pause();
        }
        if (ge_DrawThread != null) {
            ge_DrawThread.stopTimer();
        }
    }

    // REANUDA EL JUEGO
    public void ge_resumeGame() {
        if (ge_UpdateThread != null) {
            ge_UpdateThread.ut_resume();
        }
        if (ge_DrawThread != null) {
            ge_DrawThread.startTimer();
        }
    }

    public void addGameObject(GameObject gameObject) {
        if (isRunning()){
            lista_objToAdd.add(gameObject);
        } else {
            lista_gameObjects.add(gameObject);
        }
        mainActivity.runOnUiThread(gameObject.onAddedRunnable);
    }

    public void removeGameObject(GameObject gameObject) {
        // SE AÑADE A LA LISTA DE OBJETOS QUE ELIMINAR
        lista_objToRemove.add(gameObject);
        mainActivity.runOnUiThread(gameObject.onRemovedRunnable);
    }

    public void onUpdate(long elapsedMillis) {
        int numGameObjects = lista_gameObjects.size();
        for (int i=0; i<numGameObjects; i++) {
            lista_gameObjects.get(i).onUpdate(elapsedMillis, this);
        }
        synchronized (lista_gameObjects) {
            while (!lista_objToRemove.isEmpty()) {
                lista_gameObjects.remove(lista_objToRemove.remove(0));
            }
            while (!lista_objToAdd.isEmpty()) {
                lista_gameObjects.add(lista_objToAdd.remove(0));
            }
        }
    }

    public void onDraw() {
        mainActivity.runOnUiThread(drawRunnable);
    }

    public boolean isRunning() {
        return ge_UpdateThread != null && ge_UpdateThread.isGameRunning();
    }

    public boolean isPaused() {
        return ge_UpdateThread != null && ge_UpdateThread.isGamePaused();
    }
}
