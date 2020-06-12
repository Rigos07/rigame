import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import static org.lwjgl.opengl.GL11.*;

/*
 * TODO: Fix la texture
 * TODO: Gérer la vitesse via des vecteurs
 * TODO: Ajouter du terrain pour test un peu mieux
 * TODO: Gérer les collisions proprement
 * 
 * 
 * 
 */

public class Game {
	private static Player player;
	private int windowWidth;
	private int windowHeight;
	private GameState state;
	
	public Game(int x, int y) {
		this.windowWidth = x;
		this.windowHeight = y;
		this.state = GameState.INTRO;
	}
	
	public void run() {
		try {
			Display.setDisplayMode(new DisplayMode(windowWidth, windowHeight));
			Display.setTitle("Testeuh");
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
			Display.destroy();
			System.exit(1);
		}
		player = new Player("textures/sonocLarge.png", 10, 10);
		while(!Display.isCloseRequested()) {
			glMatrixMode(GL_PROJECTION);
			glLoadIdentity(); // Resets any previous projection matrices
			glOrtho(0, 640, 480, 0, 1, -1);
			glMatrixMode(GL_MODELVIEW);
			glClear(GL_COLOR_BUFFER_BIT);
			if(Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) {
				if(player.getXPos() + player.getWidth() + player.getSpeed() < this.windowWidth) player.moveRight();
			} else if(Keyboard.isKeyDown(Keyboard.KEY_LEFT)) {
				if(player.getXPos() - player.getSpeed() > 0) player.moveLeft();
			}else if(Keyboard.isKeyDown(Keyboard.KEY_UP)) {
				if(player.getYPos() - player.getSpeed() > 0) player.moveUp();
			} else if(Keyboard.isKeyDown(Keyboard.KEY_DOWN)) {
				if(player.getYPos() + player.getHeight() + player.getSpeed() < this.windowHeight) player.moveDown();
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
			
			player.draw();
			Display.update();
			Display.sync(60);
		}
		Display.destroy();
		System.exit(0);
	}
}