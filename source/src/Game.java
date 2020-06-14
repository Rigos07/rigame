import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import static org.lwjgl.opengl.GL11.*;

import java.util.ArrayList;

/*
 * TODO: Gérer la vitesse via des vecteurs
 * TODO: Génération du terrain
 * TODO: Gérer les collisions proprement
 * TODO: Menus
 * 
 * 
 */

public class Game {
	private static Player player;
	private int windowWidth;
	private int windowHeight;
	private GameState state;
	private Background background;
	private ArrayList<Entity> entities;
	
	public Game(int x, int y) {
		this.windowWidth = x;
		this.windowHeight = y;
		this.state = GameState.INTRO;
	}
	
	public void init() {
		try {
			Display.setDisplayMode(new DisplayMode(windowWidth, windowHeight));
			Display.setTitle("Testeuh");
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
			Display.destroy();
			System.exit(1);
		}
		glEnable(GL_TEXTURE_2D);               
        
        glClearColor(0.0f, 0.0f, 0.0f, 0.0f);          
        // enable alpha blending
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        
        glViewport(0,0,windowWidth,windowHeight);
        glHint(GL_PERSPECTIVE_CORRECTION_HINT, GL_NICEST);
		glHint(GL_LINE_SMOOTH_HINT, GL_NICEST);
        glMatrixMode(GL_MODELVIEW);
 
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity(); // Resets any previous projection matrices
        glOrtho(0, windowWidth, windowHeight, 0, 1, -1);
        glMatrixMode(GL_MODELVIEW);
        
		this.entities = new ArrayList<Entity>();
		//player = new Player("textures/sonocLarge.png", 150, 118);
        player = new Player("textures/player.png", 128, 128);
        entities.add(player);
		background = new Background("textures/background.png", windowWidth, windowHeight);
	}
	
	public void run() {
		this.init();
		Bloc bloc = new Bloc("textures/bloc.png", 64, 64);
		entities.add(bloc);
		bloc.setPos(400, 250);
		
		while(!Display.isCloseRequested()) {

			if(Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) {
				if(player.getXPos() + player.getWidth() + player.getSpeed() < this.windowWidth) player.moveRight();
			} else if(Keyboard.isKeyDown(Keyboard.KEY_LEFT)) {
				if(player.getXPos() - player.getSpeed() > 0) player.moveLeft();
			}else if(Keyboard.isKeyDown(Keyboard.KEY_UP)) {
				if(player.getYPos() - player.getSpeed() > 0) player.moveUp();
			} else if(Keyboard.isKeyDown(Keyboard.KEY_DOWN)) {
				if(player.getYPos() + player.getHeight() + (player.getSpeed()) < this.windowHeight) player.moveDown();
			}
			/*while (Keyboard.next()) {
				if(Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
					if(state == GameState.INTRO) state = GameState.MENU;
					else if(state == GameState.MENU) state = GameState.GAME;
					else if(state == GameState.GAME) state = GameState.INTRO;
				} else if(Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) {
					if(player.getXPos() + player.getWidth() < this.windowWidth && player.getYPos() < this.windowHeight) player.move(10, 0);
				} else if(Keyboard.isKeyDown(Keyboard.KEY_LEFT)) {
					if(player.getXPos() + player.getWidth() < this.windowWidth && player.getYPos() < this.windowHeight) player.move(-10, 0);
				}
			}*/
			
			if(player.isColliding(bloc)) {
				bloc.setTexture("./textures/bloc2.png");
			} else {
				bloc.setTexture("./textures/bloc.png");
			}
			
			background.draw();
			bloc.draw();
			player.draw();
			Display.update();
			Display.sync(60);
		}
		Display.destroy();
		System.exit(0);
	}
}