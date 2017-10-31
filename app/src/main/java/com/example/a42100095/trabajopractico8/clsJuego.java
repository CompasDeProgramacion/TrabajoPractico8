package com.example.a42100095.trabajopractico8;

import android.util.Log;

import org.cocos2d.layers.Layer;
import org.cocos2d.nodes.Director;
import org.cocos2d.nodes.Scene;
import org.cocos2d.nodes.Sprite;
import org.cocos2d.opengl.CCGLSurfaceView;
import org.cocos2d.types.CCSize;

/**
 * Created by 42100095 on 31/10/2017.
 */

public class clsJuego {
    CCGLSurfaceView _VistaDelJuego;
    CCSize PantallaDelDispositivo;
    Sprite Pacman;

    public clsJuego (CCGLSurfaceView VistaDelJuego)
    {
        Log.d("Bob","Comienza el contructor de la nueva clase");
        _VistaDelJuego = VistaDelJuego;
    }
    public void ComenzarJuego()
    {
        Log.d("Comenzar","Comienza el juego");
        Director.sharedDirector().attachInView(_VistaDelJuego);

        PantallaDelDispositivo=Director.sharedDirector().displaySize();
        Log.d("Comenzar", "Pantalla del dispositivo - Ancho: "+ PantallaDelDispositivo.width + " - Alto: "+ PantallaDelDispositivo.height);

        Log.d("Comenzar","Le digo al director que ejecute la escena");
        Director.sharedDirector().runWithScene(EscenaDelJuego());
    }
    private Scene EscenaDelJuego() {
        Log.d("EscenaDelJuego","Comienza el armado de la escena del juego");

        Log.d("EscenaDelJuego","Declaro e instancio la escena en si");
        Scene EscenaADevolver;
        EscenaADevolver = Scene.node();

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
        public CapaDeFrente(){
            Log.d("CapaDeFrente", "Comienza el constructor de la capa del frente");

            Log.d("CapaDeFrente", "Pongo el jugador en su posicion inicial");
            PonerPacmanEnPosicionInicial();
        }
        private void PonerPacmanEnPosicionInicial() {
            Log.d("PonerPacman", "Empiezo a pongo el jugador en su posicion inicial");

            Log.d("PonerPacman", "Inicio el spite");
            Pacman=Sprite.sprite("Pacman.png");

            Log.d("PonerPacman", "Lo pongo en una posicion cualquiera");
            Pacman.setPosition(100, 300);

            Log.d("PonerPacman", "Lo agrego a la capa");
            super.addChild(Pacman);
        }

    }
}
