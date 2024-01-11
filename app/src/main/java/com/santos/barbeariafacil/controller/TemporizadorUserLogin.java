package com.santos.barbeariafacil.controller;

import android.os.Handler;
import android.os.Looper;

public class TemporizadorUserLogin {

    private static final long TEMPO_INATIVIDADE_MS = 15* 1000;
    private Handler handler;
    private Runnable onTimeoutListener;

    public TemporizadorUserLogin(Runnable onTimeoutListener) {
        this.handler = new Handler(Looper.getMainLooper());
        this.onTimeoutListener = onTimeoutListener;
    }

    public void iniciarContagemRegressiva() {
        handler.removeCallbacks(onTimeoutListener);
        handler.postDelayed(onTimeoutListener, TEMPO_INATIVIDADE_MS);
    }

    public void reiniciarContagemRegressiva() {
        handler.removeCallbacks(onTimeoutListener);
        iniciarContagemRegressiva();
    }

    public void pararContagemRegressiva() {
        handler.removeCallbacks(onTimeoutListener);
    }
}
