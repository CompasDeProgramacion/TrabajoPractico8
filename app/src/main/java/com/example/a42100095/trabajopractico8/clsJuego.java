package com.example.a42100095.trabajopractico8;

import android.util.Log;

import org.cocos2d.actions.interval.MoveTo;
import org.cocos2d.actions.interval.ScaleBy;
import org.cocos2d.layers.Layer;
import org.cocos2d.nodes.Director;
import org.cocos2d.nodes.Label;
import org.cocos2d.nodes.Scene;
import org.cocos2d.nodes.Sprite;
import org.cocos2d.opengl.CCGLSurfaceView;
import org.cocos2d.types.CCPoint;
import org.cocos2d.types.CCSize;

/**
 * Created by 42100095 on 31/10/2017.
 */

public class clsJuego {
    CCGLSurfaceView _VistaDelJuego;
    CCSize PantallaDelDispositivo;
    Sprite Pacman;
    Sprite Fantasma;
    Sprite ImagenFondo;
    Label lblTituloJuego;
    int PuntajeActual;

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
        public CapaDeFondo() {
            Log.d("CapaDelFondo","Comienza el constructor de la capa del fondo");

            Log.d("CapaDelFondo","Pongo la imagen del fondo del juego");
            PonerImagenFondo();
        }
        private void PonerImagenFondo() {
            Log.d("PonerImagenFondo","comienzo a poner la imagen del fondo");

            Log.d("PonerImagenFondo","Instancio el sprite");
            ImagenFondo=Sprite.sprite("Fondo.jpg");

            Log.d("PonerImagenFondo","La ubico en el centro de la pantalla");
            ImagenFondo.setPosition(PantallaDelDispositivo.width/2, PantallaDelDispositivo.height/2);

            Log.d("PonerImagenFondo","Agrando la imagen al doble de su tamaño actual");
            Float FactoAncho, FactorAlto;
            FactoAncho=PantallaDelDispositivo.width / ImagenFondo.getWidth();
            FactorAlto=PantallaDelDispositivo.height / ImagenFondo.getHeight();
            ImagenFondo.runAction(ScaleBy.action(0.5f, FactoAncho, FactorAlto));

            Log.d("PonerImagenFondo","La agrego a la capa");
            super.addChild(ImagenFondo);
        }
    }
    class CapaDeFrente extends Layer {
        public CapaDeFrente(){
            Log.d("CapaDeFrente", "Comienza el constructor de la capa del frente");

            Log.d("CapaDeFrente", "Pongo el jugador en su posicion inicial");
            PonerPacmanEnPosicionInicial();
            PonerTituloJuego();
        }
        private void PonerPacmanEnPosicionInicial() {
            Log.d("PonerPacman", "Empiezo a pongo el jugador en su posicion inicial");

            Log.d("PonerPacman", "Inicio el spite");
            Pacman=Sprite.sprite("Pacman.png");

            float PosicionInicialX, PosicionInicialY;
            Log.d("PonerPacman","Obtengo la mitad del ancho de la pantalla");
            PosicionInicialX=PantallaDelDispositivo.width/2;

            Log.d("PonerPacman", "Obtengo la mitad de la altura de la pantalla");
            PosicionInicialY=PantallaDelDispositivo.height/2;

            Log.d("PonerPacman", "Lo unbico en X: " + PosicionInicialX + " - Y: " + PosicionInicialY);
            Pacman.setPosition(PosicionInicialX, PosicionInicialY);

            Log.d("PonerPacman", "Lo agrego a la capa");
            super.addChild(Pacman);
        }
        private void PonerTituloJuego(){
            Log.d("PonerTituloJuego","Comienza a poner el titulo");
            lblTituloJuego=Label.label("Puntaje: ", "Verdana", 40);
            lblTituloJuego.setString("Puntaje: " + PuntajeActual);

            float AltoDeTitulo;
            AltoDeTitulo=lblTituloJuego.getHeight();

            lblTituloJuego.setPosition(PantallaDelDispositivo.width/2, PantallaDelDispositivo.height - AltoDeTitulo/2);
            super.addChild(lblTituloJuego);
        }
        void PonerUnEnemigo(){
            Log.d("PonerEnemigo", "Instancio el sprite del enemigo");
            Fantasma = Sprite.sprite("FantasmaMalo.png");

            Log.d("PonerEnemigo", "Determino la posicion inicial");
            Float PosicionInicialX, PosicionInicialY;
            Float AlturaEnemigo;
            AlturaEnemigo = Fantasma.getHeight();
            PosicionInicialX = PantallaDelDispositivo.height + AlturaEnemigo/2;
            PosicionInicialY = PantallaDelDispositivo.width/2;

            Log.d("PonerEnemigo", "Ubico el sprite en la pantalla");
            Fantasma.setPosition(PosicionInicialX, PosicionInicialY);

            Log.d("PonerEnemigo", "Determino la posicion final");
            Float PosicionFinalX, PosicionFinalY;
            PosicionFinalX=PosicionInicialX;
            PosicionFinalY= - AlturaEnemigo/2;

            Log.d("PonerEnemigo", "Doy la orden para que se mueva hasta la posicion final");
            Fantasma.runAction(MoveTo.action(3, PosicionFinalX, PosicionFinalY));

            Log.d("PonerEnemigo", "Agrego el srpite a la capa");
            super.addChild(Fantasma);
        }
    }
}
