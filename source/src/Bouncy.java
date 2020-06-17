import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import static org.lwjgl.opengl.GL11.*;

/*
 * TODO: Mieux gérer les sauts
 * TODO: Génération du terrain (mieux qu'un for)
 * TODO: Gérer les collisions + proprement
 * TODO: Menus
 * TODO: Gérer les sprites/animations
 * 
 * 
 */

public class Bouncy {
	private static Ball ball;
	private int windowWidth;
	private int windowHeight;
	private Background background;
	
	public Bouncy(int x, int y) {
		this.windowWidth = x;
		this.windowHeight = y;
	}
	
	public void controls4Directions() {
		if(Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) {
			ball.addForce(new Vector(1,0));
		} if(Keyboard.isKeyDown(Keyboard.KEY_LEFT)) {
			ball.addForce(new Vector(-1,0));
		} if(Keyboard.isKeyDown(Keyboard.KEY_UP)) {
			ball.addForce(new Vector(0,-1));
		} if(Keyboard.isKeyDown(Keyboard.KEY_DOWN)) {
			ball.addForce(new Vector(0,1));
		}
	}
	
	public void init() {
		try {
			Display.setDisplayMode(new DisplayMode(windowWidth, windowHeight));
			Display.setTitle("Boing");
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
        
		//player = new Player("textures/sonocLarge.png", 150, 118);
        ball = new Ball("textures/player.png", 64, 64, 128, 128);
        ball.setForce(new Vector(10,10));
		background = new Background("textures/background.png", windowWidth, windowHeight, windowWidth, windowHeight);
	}
	
	public void run() {
		this.init();
		
		while(!Display.isCloseRequested()) {
			this.controls4Directions();
			if(Keyboard.isKeyDown(Keyboard.KEY_R)) {
				ball.setPos(windowWidth/2 - ball.getCenterX(), windowHeight/2 - ball.getCenterY());
				ball.setForce(new Vector(100,1));
			}
			
			
			//if(!ball.isContainedBy(background)) {
				double nextX = ball.getXPos() + ball.getdx(), nextY = ball.getYPos() + ball.getdy();
				if(nextX < 0 || nextX + ball.getWidth() > background.getWidth()) ball.setForce(new Vector(-ball.getdx(), ball.getdy()));
				if(nextY < 0 || nextY + ball.getHeight() > background.getHeight()) ball.setForce(new Vector(ball.getdx(), -ball.getdy()));
			//}
			
			background.draw();
			ball.update();
			
			//System.out.println(ball.getdx() + " , " + ball.getdy());
			ball.draw();
			
			Display.update();
			Display.sync(60);
		}
		Display.destroy();
		System.exit(0);
	}
}