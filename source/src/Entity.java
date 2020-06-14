import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glVertex2i;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

public abstract class Entity {
	private Texture texture;
	protected int xPos;
	protected int yPos;
	private int textureWidth;
	private int textureHeight;
	
	public Entity(String texturePath, int x, int y) {
		this.xPos = x;
		this.yPos = y;
		try {
			this.texture = TextureLoader.getTexture("PNG", new FileInputStream(new File(texturePath)));
			this.textureWidth = texture.getImageWidth();
			this.textureHeight = texture.getImageHeight();
			System.out.println(textureWidth);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public int getXPos() {
		return this.xPos;
	}
	
	public int getYPos() {
		return this.yPos;
	}
	
	public int getWidth() {
		return this.textureWidth;
	}
	
	public int getHeight() {
		return this.textureHeight;
	}
	
	public void setPos(int x, int y) {
		this.xPos = x;
		this.yPos = y;
	}
	
	public void move(int x, int y) {
		this.xPos += x;
		this.yPos += y;
	}
	
	public void draw() {
		glEnable(GL_TEXTURE_2D);
		glBegin(GL_QUADS);
		 
		glTexCoord2f(0, 0);
		glVertex2i(xPos, yPos);
		glTexCoord2f(1, 0);
		glVertex2i(xPos + textureWidth, yPos);
		glTexCoord2f(1, 1);
		glVertex2i(xPos + textureWidth, yPos + textureHeight);
		glTexCoord2f(0, 1);
		glVertex2i(xPos, yPos + textureHeight);
		
		glEnd();
		glDisable(GL_TEXTURE_2D);
	}
}
