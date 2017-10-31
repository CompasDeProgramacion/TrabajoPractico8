package com.example.a42100095.trabajopractico8;

import android.util.Log;

import org.cocos2d.layers.Layer;
import org.cocos2d.nodes.Director;
import org.cocos2d.nodes.Scene;
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
        Director.sharedDirector().attachInView(_VistaDelJuego);
    }
    private Escena()
    {
        Log.d("EscenaDelJuego","Comienza el armado de la escena del juego");

        Log.d("EscenaDelJuego","Declaro e instancio la escena en si");
        Scene EscenaADevolver;
        EscenaADevolver= Scene.node();

        Log.d("EscenaDelJuego","Declaro e instancio la capa que va a contener la imagen de fondo");
        CapaDeFondo MiCapaDeFondo;
        MiCapaDeFondo = new CapaDeFondo();

        Log.d("EscenaDelJuego","Declaro e instancio la capa que va a contener el jugador y los enemigos");
        CapaDeFrente MiCapaDeFrente;
        MiCapaDeFrente = new CapaDeFrente();

        Log.d("EscenaDelJuego","Agrego a la escena la capa del fondo mas atras, y la del frente mas adelante");
        EscenaADevolver.addChild(MiCapaDeFondo, -10);
        EscenaADevolver.addChild(MiCapaDeFrente, 10);

        Log.d("EscenaDelJuego","Devuelvo la escena ya armada");
        return EscenaADevolver;


    }
    class CapaDeFondo extends Layer {

    }
    class CapaDeFrente extends Layer {

    }
}
