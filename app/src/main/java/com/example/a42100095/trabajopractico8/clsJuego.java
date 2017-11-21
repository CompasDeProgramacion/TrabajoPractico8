package com.example.a42100095.trabajopractico8;

import android.content.Context;
import android.media.MediaPlayer;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MotionEvent;

import org.cocos2d.actions.interval.Animate;
import org.cocos2d.actions.interval.MoveTo;
import org.cocos2d.actions.interval.ScaleBy;
import org.cocos2d.layers.Layer;
import org.cocos2d.menus.Menu;
import org.cocos2d.menus.MenuItemImage;
import org.cocos2d.nodes.Animation;
import org.cocos2d.nodes.Director;
import org.cocos2d.nodes.Label;
import org.cocos2d.nodes.Scene;
import org.cocos2d.nodes.Sprite;
import org.cocos2d.opengl.CCGLSurfaceView;
import org.cocos2d.types.CCPoint;
import org.cocos2d.types.CCSize;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by 42100095 on 31/10/2017.
 */

public class clsJuego {
    CCGLSurfaceView _VistaDelJuego;
    CCSize PantallaDelDispositivo;
    Sprite Pacman;
    Sprite Fantasma;
    Sprite FantasmaMalo;
    Sprite ImagenFondo;
    Label lblTituloJuego;
    int PuntajeActual;
    Context _ContextoDelJuego;

    ArrayList<Sprite> arrEnemigos;
    ArrayList<Sprite> arrEnemigosMalos;

        public clsJuego (CCGLSurfaceView VistaDelJuego)
        {
            Log.d("Bob","Comienza el contructor de la nueva clase");
            _VistaDelJuego = VistaDelJuego;
        }
        public void ComenzarJuego(CCGLSurfaceView vistaPrincipal, Context _ContextoDelJuego) {
            Log.d("Comenzar","Comienza el juego");
            Director.sharedDirector().attachInView(_VistaDelJuego);

            PantallaDelDispositivo=Director.sharedDirector().displaySize();
            Log.d("Comenzar", "Pantalla del dispositivo - Ancho: "+ PantallaDelDispositivo.width + " - Alto: "+ PantallaDelDispositivo.height);

            Log.d("Comenzar", "Comienza musica de fondo");
            Random GeneradorDeAzar;
            GeneradorDeAzar=new Random();
            int i = GeneradorDeAzar.nextInt(2) + 1;

            MediaPlayer mpMusicaDeFondo = null;
            if (i == 1)
            {
                mpMusicaDeFondo = MediaPlayer.create(_ContextoDelJuego, R.raw.rock);
            }
            if (i == 2)
            {
                mpMusicaDeFondo = MediaPlayer.create(_ContextoDelJuego, R.raw.sky);
            }
            mpMusicaDeFondo.start();
            mpMusicaDeFondo.setLooping(true);

            Log.d("Comenzar","Le digo al director que ejecute la escena de inicio");
            Director.sharedDirector().runWithScene(EscenaInicio());
        }
        private Scene EscenaInicio() {
            Log.d("EscenaInicio","Comienza el armado de la escena de inicio");

            Log.d("EscenaInicio","Declaro e instancio la escena en si");
            Scene EscenaADevolver;
            EscenaADevolver = Scene.node();

            Log.d("EscenaInicio","Declaro e instancio la capa que va a contener la imagen de fondo");
            CapaDeFondoInicio MiCapaDeFondoInicio;
            MiCapaDeFondoInicio = new CapaDeFondoInicio();

            Log.d("EscenaInicio","Declaro e instancio la capa que va a contener el jugador y los enemigos");
            CapaDeFrenteInicio MiCapaDeFrenteInicio;
            MiCapaDeFrenteInicio = new CapaDeFrenteInicio();

            Log.d("EscenaInicio","Agrego a la escena la capa del fondo mas atras, y la del frente mas adelante");
            EscenaADevolver.addChild(MiCapaDeFondoInicio, -10);
            EscenaADevolver.addChild(MiCapaDeFrenteInicio, 10);

            Log.d("EscenaInicio","Devuelvo la escena ya armada");
            return EscenaADevolver;
        }
        class CapaDeFondoInicio extends Layer {
            public CapaDeFondoInicio() {
                Log.d("CapaDelFondo","Comienza el constructor de la capa del fondo");

                Log.d("CapaDelFondo","Pongo la imagen del fondo del juego");
                PonerImagenFondoInicio();
            }
            private void PonerImagenFondoInicio() {
                Log.d("PonerImagenFondo","comienzo a poner la imagen del fondo");

                Log.d("PonerImagenFondo","Instancio el sprite");
                ImagenFondo=Sprite.sprite("FondoInicio.jpg");

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
        class CapaDeFrenteInicio extends Layer {
            public CapaDeFrenteInicio(){
                Log.d("CapaDeFrente", "Comienza el constructor de la capa del frente");

                Log.d("CapaDeFrente", "Pongo el jugador en su posicion inicial");

                Log.d("CapaDeFrente","Habilito el control del Touch");
                this.setIsTouchEnabled(true);

                Log.d("Boton","Declaro el objeto para el boton de jugar");
                MenuItemImage btnJugar;
                Log.d("Boton","Declaro el objeto para el boton de jugar");
                btnJugar = MenuItemImage.item("inicio.png", "inicio.png", this, "ApretoJugar");
                float PosicionBotonX,PosicionBotonY;
                PosicionBotonX = btnJugar.getWidth()/2;
                PosicionBotonY = btnJugar.getHeight()/2;

                Log.d("Boton","Lo ubico en X: " + PosicionBotonX + "-Y: " + PosicionBotonY);
                btnJugar.setPosition(PosicionBotonX,PosicionBotonY);

                Log.d("Boton","Declaro el objeto para el boton de como jugar");
                MenuItemImage btnCJugar;
                Log.d("Boton","Declaro el objeto para el boton de como jugar");
                btnCJugar = MenuItemImage.item("cjugar.png", "cjugar.png", this, "ApretoComoJugar");
                float PosicionBoton2Y;
                PosicionBoton2Y = btnCJugar.getHeight()/2 - PosicionBotonY - 80;

                Log.d("Boton","Lo ubico en X: " + PosicionBotonX + "-Y: " + PosicionBoton2Y);
                btnCJugar.setPosition(PosicionBotonX,PosicionBoton2Y);

                Log.d("Boton","Armo el menu con los botones que declare");
                Menu menu;
                menu=Menu.menu(btnJugar, btnCJugar);
                menu.setPosition(PantallaDelDispositivo.getWidth()/2 - PosicionBotonX,PantallaDelDispositivo.getHeight()/2);

                super.addChild(menu);
            }
            public void ApretoJugar()
            {
                Log.d("ApretoJugar","Se presiono jugar");
                Director.sharedDirector().replaceScene(EscenaDelJuego());
            }
            public void ApretoComoJugar()
            {
                Log.d("ApretoJugar","Se presiono cjugar");
                Director.sharedDirector().replaceScene(EscenaComoJugar());
            }
        }

        private Scene EscenaComoJugar() {
            Log.d("EscenaComoJugar","Comienza el armado de la escena de inicio");

            Log.d("EscenaComoJugar","Declaro e instancio la escena en si");
            Scene EscenaADevolver;
            EscenaADevolver = Scene.node();

            Log.d("EscenaComoJugar","Declaro e instancio la capa que va a contener la imagen de fondo");
            CapaDeFondoComo MiCapaDeFondoComo;
            MiCapaDeFondoComo = new CapaDeFondoComo();

            Log.d("EscenaComoJugar","Agrego a la escena la capa del fondo mas atras, y la del frente mas adelante");
            EscenaADevolver.addChild(MiCapaDeFondoComo, -10);

            Log.d("EscenaComoJugar","Devuelvo la escena ya armada");
            return EscenaADevolver;
        }
        class CapaDeFondoComo extends Layer {
            public CapaDeFondoComo() {
                Log.d("CapaDelFondo","Comienza el constructor de la capa del fondo");

                Log.d("CapaDelFondo","Pongo la imagen del fondo del juego");
                PonerImagenFondoComo();
            }
            private void PonerImagenFondoComo() {
                Log.d("PonerImagenFondoComo","comienzo a poner la imagen del fondo");

                Log.d("PonerImagenFondoComo","Instancio el sprite");
                ImagenFondo=Sprite.sprite("ComoJugar.jpg");

                Log.d("PonerImagenFondoComo","La ubico en el centro de la pantalla");
                ImagenFondo.setPosition(PantallaDelDispositivo.width/2, PantallaDelDispositivo.height/2);

                Log.d("PonerImagenFondoComo","Agrando la imagen al doble de su tamaño actual");
                Float FactoAncho, FactorAlto;
                FactoAncho=PantallaDelDispositivo.width / ImagenFondo.getWidth();
                FactorAlto=PantallaDelDispositivo.height / ImagenFondo.getHeight();
                ImagenFondo.runAction(ScaleBy.action(0.5f, FactoAncho, FactorAlto));

                Log.d("PonerImagenFondo","La agrego a la capa");
                super.addChild(ImagenFondo);
            }
        }
        class CapaDeFrenteComo extends Layer {
            public CapaDeFrenteComo() {
                super.schedule("ApretoJugar", (float)5.0f );
            }
            public void ApretoJugar() {
                Log.d("ApretoJugar", "Se presiono jugar");
                Director.sharedDirector().replaceScene(EscenaDelJuego());
            }
        }

        public Scene EscenaDelJuego() {
        Log.d("EscenaDelJuego", "Comienza el armado de la escena del juego");

        Log.d("EscenaDelJuego", "Declaro e instancio la escena en si");
        Scene EscenaADevolver;
        EscenaADevolver = Scene.node();

        Log.d("EscenaDelJuego", "Declaro e instancio la capa que va a contener la imagen de fondo");
        CapaDeFondo MiCapaDeFondo;
        MiCapaDeFondo = new CapaDeFondo();

        Log.d("EscenaDelJuego", "Declaro e instancio la capa que va a contener el jugador y los enemigos");
        CapaDeFrente MiCapaDeFrente;
        MiCapaDeFrente = new CapaDeFrente();

        Log.d("EscenaDelJuego", "Agrego a la escena la capa del fondo mas atras, y la del frente mas adelante");
        EscenaADevolver.addChild(MiCapaDeFondo, -10);
        EscenaADevolver.addChild(MiCapaDeFrente, 10);

        Log.d("EscenaDelJuego", "Devuelvo la escena ya armada");
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

                Log.d("CapaDeFrente","Habilito el control del Touch");
                this.setIsTouchEnabled(true);

                if (PuntajeActual > -1 && PuntajeActual < 50) {
                    super.schedule("PonerUnEnemigo", (float) 3.0f);
                    super.schedule("DetectarColisiones", (float) 0.3f);
                    super.schedule("PonerUnEnemigoMalo", (float) 5.0f);
                }
            }

            public void PonerPacmanEnPosicionInicial() {
                Log.d("PonerPacman", "Empiezo a pongo el jugador en su posicion inicial");

                Log.d("PonerPacman", "Inicio el spite");
                Pacman = Sprite.sprite("segunda.png");
                int Altura = (int) Pacman.getHeight();

                float PosicionInicialX, PosicionInicialY;
                Log.d("PonerPacman", "Obtengo la mitad del ancho de la pantalla");
                PosicionInicialX = PantallaDelDispositivo.width / 2;

                Log.d("PonerPacman", "Obtengo la mitad de la altura de la pantalla");
                PosicionInicialY = 0 + Altura / 2;

                Log.d("PonerPacman", "Lo unbico en X: " + PosicionInicialX + " - Y: " + PosicionInicialY);
                Pacman.setPosition(PosicionInicialX, PosicionInicialY);

                Log.d("PonerPacman", "Lo agrego a la capa");
                super.addChild(Pacman);
            }

            private void PonerTituloJuego() {
                Log.d("PonerTituloJuego", "Comienza a poner el titulo");
                lblTituloJuego = Label.label("Puntaje: ", "Verdana", 60);
                Log.d("Puntaje", "es:" + PuntajeActual);
                lblTituloJuego.setString("Puntaje: " + PuntajeActual);

                float AltoDeTitulo;
                AltoDeTitulo = lblTituloJuego.getHeight();

                lblTituloJuego.setPosition(PantallaDelDispositivo.width / 2, PantallaDelDispositivo.height - AltoDeTitulo / 2);
                super.addChild(lblTituloJuego);
            }

            public void PonerUnEnemigo(float DiferenciaTiempo) {

                arrEnemigos = new ArrayList<Sprite>();
                Log.d("PonerEnemigo", "Instancio el sprite del enemigo");
                Fantasma = Sprite.sprite("Fantasma.png");

                Log.d("PonerEnemigo", "Determino la posicion inicial");
                Float PosicionInicialX, PosicionInicialY;
                Float AlturaEnemigo;
                AlturaEnemigo = Fantasma.getHeight();
                float AnchoEnemigo = Fantasma.getWidth();
                PosicionInicialY = PantallaDelDispositivo.height + AlturaEnemigo / 2;

                Log.d("PonerEnemigo", "Determino la posicion X al azar");
                Random GeneradorDeAzar;
                GeneradorDeAzar = new Random();
                PosicionInicialX = Float.valueOf(GeneradorDeAzar.nextInt((int) (PantallaDelDispositivo.width - AnchoEnemigo)));
                PosicionInicialX += AnchoEnemigo / 2;

                Log.d("PonerEnemigo", "Ubico el sprite en la pantalla");
                Fantasma.setPosition(PosicionInicialX, PosicionInicialY);

                Log.d("PonerEnemigo", "Determino la posicion final");
                Float PosicionFinalX, PosicionFinalY;
                PosicionFinalX = PosicionInicialX;
                PosicionFinalY = -AlturaEnemigo / 2;

                Log.d("PonerEnemigo", "Doy la orden para que se mueva hasta la posicion final");
                Fantasma.runAction(MoveTo.action(3, PosicionFinalX, PosicionFinalY));

                Log.d("PonerUnEnemigo", "Agrego el enemigo al array");
                arrEnemigos.add(Fantasma);
                Log.d("PonerUnEnemigo", "Hay " + arrEnemigos.size() + " enemigos en vuelo");

                Log.d("PonerEnemigo", "Agrego el srpite a la capa");
                super.addChild(Fantasma);
            }

            public void PonerUnEnemigoMalo(float DiferenciaTiempo) {
                arrEnemigosMalos = new ArrayList<Sprite>();

                Log.d("PonerEnemigoMalo", "Instancio el sprite del enemigo");
                FantasmaMalo = Sprite.sprite("FantasmaMalo.png");

                Log.d("PonerEnemigoMalo", "Determino la posicion inicial");
                Float PosicionInicialX, PosicionInicialY;
                Float AlturaEnemigo;
                AlturaEnemigo = FantasmaMalo.getHeight();
                float AnchoEnemigo = FantasmaMalo.getWidth();
                PosicionInicialY = PantallaDelDispositivo.height + AlturaEnemigo / 2;

                Log.d("PonerEnemigoMalo", "Determino la posicion X al azar");
                Random GeneradorDeAzar;
                GeneradorDeAzar = new Random();
                PosicionInicialX = Float.valueOf(GeneradorDeAzar.nextInt((int) (PantallaDelDispositivo.width - AnchoEnemigo)));
                PosicionInicialX += AnchoEnemigo / 2;

                Log.d("PonerEnemigoMalo", "Ubico el sprite en la pantalla");
                FantasmaMalo.setPosition(PosicionInicialX, PosicionInicialY);

                Log.d("PonerEnemigoMalo", "Determino la posicion final");
                Float PosicionFinalX, PosicionFinalY;
                PosicionFinalX = PosicionInicialX;
                PosicionFinalY = -AlturaEnemigo / 2;

                Log.d("PonerEnemigoMalo", "Doy la orden para que se mueva hasta la posicion final");
                FantasmaMalo.runAction(MoveTo.action(3, PosicionFinalX, PosicionFinalY));

                Log.d("PonerEnemigoMalo", "Agrego el srpite a la capa");
                super.addChild(FantasmaMalo);

                Log.d("PonerEnemigoMalo", "Agrego el enemigo malo al array");
                arrEnemigosMalos.add(FantasmaMalo);
                Log.d("PonerEnemigoMalo", "Hay " + arrEnemigosMalos.size() + " enemigos malos en vuelo");
            }

            boolean InterseccionEntreSprites(Sprite Sprite1, Sprite Sprite2) {
                boolean Devolver;
                Log.d("InterseccionSprites", "Vuelve a false");
                Devolver = false;

                int Sprite1Izquierda, Sprite1Derecha, Sprite1Abajo, Sprite1Arriba;
                int Sprite2Izquierda, Sprite2Derecha, Sprite2Abajo, Sprite2Arriba;

                Sprite1Izquierda = (int) (Sprite1.getPositionX() - Sprite1.getWidth() / 2);
                Sprite1Derecha = (int) (Sprite1.getPositionX() + Sprite1.getWidth() / 2);
                Sprite1Abajo = (int) (Sprite1.getPositionY() - Sprite1.getHeight() / 2);
                Sprite1Arriba = (int) (Sprite1.getPositionY() + Sprite1.getHeight() / 2);

                Sprite2Izquierda = (int) (Sprite2.getPositionX() - Sprite2.getWidth() / 2);
                Sprite2Derecha = (int) (Sprite2.getPositionX() + Sprite2.getWidth() / 2);
                Sprite2Abajo = (int) (Sprite2.getPositionY() - Sprite2.getHeight() / 2);
                Sprite2Arriba = (int) (Sprite2.getPositionY() + Sprite2.getHeight() / 2);

                Log.d("Interseccion", "Sp1 - Izq: " + Sprite1Izquierda + " - Der: " + Sprite1Derecha + " - Aba: " + Sprite1Abajo + " - Arr: " + Sprite1Arriba);
                Log.d("Interseccion", "Sp2 - Izq: " + Sprite2Izquierda + " - Der: " + Sprite2Derecha + " - Aba: " + Sprite2Abajo + " - Arr: " + Sprite2Arriba);

                //Borde izq y borde inf de Sprite 1 está dentro de Sprite 2
                if (EstaEntre(Sprite1Izquierda, Sprite2Izquierda, Sprite2Derecha) &&
                        EstaEntre(Sprite1Abajo, Sprite2Abajo, Sprite2Arriba)) {
                    Log.d("Interseccion", "1");
                    Devolver = true;
                }
                //Borde izq y borde sup de Sprite 1 está dentro de Sprite 2
                if (EstaEntre(Sprite1Izquierda, Sprite2Izquierda, Sprite2Derecha) &&
                        EstaEntre(Sprite1Arriba, Sprite2Abajo, Sprite2Arriba)) {
                    Log.d("Interseccion", "2");
                    Devolver = true;
                }
                //Borde der y borde sup de Sprite 1 está dentro de Sprite 2
                if (EstaEntre(Sprite1Derecha, Sprite2Izquierda, Sprite2Derecha) &&
                        EstaEntre(Sprite1Arriba, Sprite2Abajo, Sprite2Arriba)) {
                    Log.d("Interseccion", "3");
                    Devolver = true;
                }
                //Borde der y borde inf de Sprite 1 está dentro de Sprite 2
                if (EstaEntre(Sprite1Derecha, Sprite2Izquierda, Sprite2Derecha) &&
                        EstaEntre(Sprite1Abajo, Sprite2Abajo, Sprite2Arriba)) {
                    Log.d("Interseccion", "4");
                    Devolver = true;
                }
                //Borde izq y borde inf de Sprite 2 está dentro de Sprite 1
                if (EstaEntre(Sprite2Izquierda, Sprite1Izquierda, Sprite1Derecha) &&
                        EstaEntre(Sprite2Abajo, Sprite1Abajo, Sprite1Arriba)) {
                    Log.d("Interseccion", "5");
                    Devolver = true;
                }
                //Borde izq y borde sup de Sprite 1 está dentro de Sprite 1
                if (EstaEntre(Sprite2Izquierda, Sprite1Izquierda, Sprite1Derecha) &&
                        EstaEntre(Sprite2Arriba, Sprite1Abajo, Sprite1Arriba)) {
                    Log.d("Interseccion", "6");
                    Devolver = true;
                }
                //Borde der y borde sup de Sprite 2 está dentro de Sprite 1
                if (EstaEntre(Sprite2Derecha, Sprite1Izquierda, Sprite1Derecha) &&
                        EstaEntre(Sprite2Arriba, Sprite1Abajo, Sprite1Arriba)) {
                    Log.d("Interseccion", "7");
                    Devolver = true;
                }
                //Borde der y borde inf de Sprite 2 está dentro de Sprite 1
                if (EstaEntre(Sprite2Derecha, Sprite1Izquierda, Sprite1Derecha) &&
                        EstaEntre(Sprite2Abajo, Sprite1Abajo, Sprite1Arriba)) {
                    Log.d("Interseccion", "8");
                    Devolver = true;
                }

                return Devolver;
            }

            boolean EstaEntre(int NumeroAComparar, int NumeroMenor, int NumeroMayor) {
                boolean Devolver;

                Log.d("EstaEntre", "NumeroMenor: " + NumeroMenor + " - NumeroMayor: " + NumeroMayor);

                if (NumeroMenor > NumeroMayor) {
                    Log.d("EstaEntre", "Me los mandaron invertidos los ordeno");
                    int Auxiliar;
                    Auxiliar = NumeroMayor;
                    NumeroMayor = NumeroMenor;
                    NumeroMenor = Auxiliar;
                }

                if (NumeroAComparar >= NumeroMenor && NumeroAComparar <= NumeroMayor) {
                    Log.d("EstaEntre", "Esta entre");
                    Devolver = true;
                } else {
                    Log.d("EstaEntre", "NO Esta entre");
                    Devolver = false;
                }
                return Devolver;
            }

            void DetectarColisiones(float DiferenciaTiempo) {
                Log.d("DetectarColisiones", "Voy a verificar los " + arrEnemigos.size() + " enemigos");
                for (Sprite EnemigoAVerificar : arrEnemigos) {
                    if (InterseccionEntreSprites(Pacman, EnemigoAVerificar)) {
                        Animation AnimacionPacman;
                        AnimacionPacman = new Animation("Anim", 0.1f, "primera.png", "segunda.png", "tercera.png");
                        Pacman.runAction(Animate.action(AnimacionPacman));

                        Log.d("DetectarColisiones", "Hubo colision");
                        super.removeChild(EnemigoAVerificar, false);
                        PuntajeActual++;
                        lblTituloJuego.setString("Puntaje: " + PuntajeActual);
                    }
                }
            }

            @Override
            public boolean ccTouchesBegan(MotionEvent event) {
                Log.d("Toque comienza ", "X: " + event.getX() + " - Y: " + event.getY());

                MoverPacman(event.getX());

                return true;
            }

            @Override
            public boolean ccTouchesMoved(MotionEvent event) {
                Log.d("Toque mueve ", "X: " + event.getX() + " - Y: " + event.getY());

                MoverPacman(event.getX());

                return true;
            }
            @Override
            public boolean ccTouchesEnded (MotionEvent event){
                Log.d("Toque termina ","X: " + event.getX() + " - Y: " + event.getY());

                MoverPacman (event.getX());

                return true;
            }

            public void MoverPacman(float DestinoX) {

                float MovimientoHorizontal, SuavizadorDeMovimiento;
                MovimientoHorizontal = DestinoX - PantallaDelDispositivo.getWidth() / 2;
                SuavizadorDeMovimiento = 10;
                MovimientoHorizontal = MovimientoHorizontal / SuavizadorDeMovimiento;

                Log.d("MoverJugador", "Obtengo la posicion final a la que deberia ir");
                float PosicionFinalX, PosicionFinalY;
                PosicionFinalX = Pacman.getPositionX() + MovimientoHorizontal;
                PosicionFinalY = Pacman.getHeight() / 2;

                Log.d("MoverJugador", "Me fijo si no se fue de los limites de la pantalla");
                if (PosicionFinalX < Pacman.getWidth() / 2) {
                    Log.d("MoverJugador", "Se fue por la izquierda");
                    PosicionFinalX = Pacman.getWidth() / 2;
                }
                if (PosicionFinalX > PantallaDelDispositivo.getWidth() - Pacman.getWidth() / 2) {
                    Log.d("MoverJugador", "Se fue por la derecha");
                    PosicionFinalX = PantallaDelDispositivo.getWidth() - Pacman.getWidth() / 2;
                }

                Pacman.setPosition(PosicionFinalX, PosicionFinalY);
            }
        }
    }
