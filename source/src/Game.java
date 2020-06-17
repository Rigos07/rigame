import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import static org.lwjgl.opengl.GL11.*;

import java.util.ArrayList;

/*
 * TODO: Mieux gérer les sauts et collisions
 * TODO: Génération du terrain (mieux qu'un for)
 * TODO: Menus
 * TODO: Gérer les sprites/animations
 * 
 * 
 */

public class Game {
	private static Player player;
	private int windowWidth;
	private int windowHeight;
	private GameState state;
	private Background background;
	private ArrayList<Bloc> blocs;
	private boolean jump;
	
	public Game(int x, int y) {
		this.windowWidth = x;
		this.windowHeight = y;
		this.state = GameState.INTRO;
	}
	
	public void controls4Directions() {
		if(Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) {
			player.addForce(new Vector(1,0));
		} if(Keyboard.isKeyDown(Keyboard.KEY_LEFT)) {
			player.addForce(new Vector(-1,0));
		} if(Keyboard.isKeyDown(Keyboard.KEY_UP)) {
			player.addForce(new Vector(0,-1));
		} if(Keyboard.isKeyDown(Keyboard.KEY_DOWN)) {
			player.addForce(new Vector(0,1));
		}
	}
	
	public void controls2Directions() {
		if(Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) {
			player.addForce(new Vector(1,0));
		} if(Keyboard.isKeyDown(Keyboard.KEY_LEFT)) {
			player.addForce(new Vector(-1,0));
		} if(Keyboard.isKeyDown(Keyboard.KEY_UP)) {
			if(!this.jump && player.isOnGround()) {
				player.addForce(new Vector(0,-15));
				this.jump = true;
			}
		} if(Keyboard.isKeyDown(Keyboard.KEY_DOWN)) {
			player.addForce(new Vector(0,1));
		}
	}
	
	public void drawGame() {
		background.draw();
		for(Bloc bloc : blocs) bloc.draw();		
		player.draw();
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
        
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA); // enable alpha blending
        
        glViewport(0,0,windowWidth,windowHeight);
        glHint(GL_PERSPECTIVE_CORRECTION_HINT, GL_NICEST);
		glHint(GL_LINE_SMOOTH_HINT, GL_NICEST);
        glMatrixMode(GL_MODELVIEW);
 
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity(); // Resets any previous projection matrices
        glOrtho(0, windowWidth, windowHeight, 0, 1, -1);
        glMatrixMode(GL_MODELVIEW);
        
        this.state = GameState.INTRO;
		this.blocs = new ArrayList<Bloc>();
		//player = new Player("textures/sonocLarge.png", 150, 118);
        player = new Player("textures/player.png", 64, 64, 128, 128);
        //entities.add(player);
		background = new Background("textures/background.png", windowWidth, windowHeight, windowWidth, windowHeight);
		//jump = false;
	}
	
	public void run() {
		this.init();
		
		/*Bloc bloc = new Bloc("textures/bloc.png", 64, 64);
		entities.add(bloc);
		bloc.setPos(400, 250);*/
		/*for(int i = 0 ; i < 8 ; i++) {
			Bloc b = new Bloc("textures/bloc.png", 64, 64, 64, 64);
			b.setPos(i*64, 200);
			blocs.add(b);
			if(i == 4 || i == 7) {
				Bloc b2 = new Bloc("textures/bloc.png", 64, 64, 64, 64);
				b2.setPos(i*64, 136);
				blocs.add(b2);
			}
		}
		
		for(int i = 2 ; i < 10 ; i++) {
			Bloc b = new Bloc("textures/bloc.png", 64, 64, 64, 64);
			b.setPos((i)*64, 350);
			blocs.add(b);
		}*/
		
		while(!Display.isCloseRequested()) {
			switch(this.state) {
				case INTRO:
					background.setTexture("textures/background_intro.png", 957, 738);
					background.setSize(640, 480);
					background.draw();
					while (Keyboard.next()) {
						if(Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
							this.state = GameState.MENU;
						}
					}
					break;
				case MENU:
					background.setTexture("textures/background.png", 640, 480);
					background.setSize(640, 480);
					background.draw();
					
					while (Keyboard.next()) {
						if(Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
							this.state = GameState.GAME;
						}
					}
					break;
				case GAME:
					//while (Keyboard.next()) {
					this.controls2Directions();
					if(Keyboard.isKeyDown(Keyboard.KEY_R)) {
						player.setPos(10, 10);
						player.setForce(0,0);
					}
				//}


				/*for(Bloc bloc : blocs) {
					if(player.isColliding(bloc)) {
						bloc.setTexture("./textures/bloc2.png", 64, 64);
						player.addCollision(bloc);
						//player.setOnGround(true);
						jump = false;
						player.jump = false;
						//inAir = false;
					} else {
						bloc.setTexture("./textures/bloc.png", 64, 64);
					}
				}*/
				
				/*if(inAir) {
					player.setOnGround(false);
				}*/
				
				/*if(!jump) {
					player.addForce(new Vector(500,500));
					jump = true;
				}*/
				
				// Keep player inside the map
				double nextX = player.getXPos() + player.getdx(), nextY = player.getYPos() + player.getdy();
				if(nextX < 0 || nextX + player.getWidth() > background.getWidth()) player.setForce(-player.getdx(), player.getdy());
				if(nextY < 0 || nextY + player.getHeight() > background.getHeight()) {
					if(nextY + player.getHeight() > background.getHeight()) {
						this.jump = false;
						player.setOnGround(true);
					}
					
					player.setForce(player.getdx(), -player.getdy());
				} 
				
				if(nextY + player.getHeight() < background.getHeight()) player.setOnGround(false);
	
				
				player.update(background);
				
				
				
				//System.out.println(player.getdx() + " , " + player.getdy() + " / jump = " + player.jump + "/ ground = " + player.isOnGround());
				
				this.drawGame();
				break;
			}
			
			
			
			Display.update();
			Display.sync(60);
		}
		Display.destroy();
		System.exit(0);
	}
}