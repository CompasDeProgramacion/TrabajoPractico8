package com.example.a42100095.trabajopractico8;

import android.util.Log;

import org.cocos2d.opengl.CCGLSurfaceView;

/**
 * Created by 42100095 on 31/10/2017.
 */

public class clsJuego {
    CCGLSurfaceView _VistaDelJuego;
    public clsJuego (CCGLSurfaceView VistaDelJuego)
    {
        Log.d("Bob","Comienza el contructor de la nueva clase");
        _VistaDelJuego = VistaDelJuego;
    }
    public void ComenzarJuego()
    {
        Log.d("Comenzar","Comienza el juego");
    }
}
