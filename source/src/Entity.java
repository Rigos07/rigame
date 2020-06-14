import static org.lwjgl.opengl.GL11.*;

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
	private int width;
	private int height;
	private int textureWidth;
	private int textureHeight;
	
	public Entity(String texturePath, int sizeX, int sizeY) {
		this.xPos = 0;
		this.yPos = 0;
		this.width = sizeX;
		this.height = sizeY;
		try {
			this.texture = TextureLoader.getTexture("PNG", new FileInputStream(new File(texturePath)));
			this.textureWidth = texture.getImageWidth();
			this.textureHeight = texture.getImageHeight();
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
		return this.width;
	}
	
	public int getHeight() {
		return this.height;
	}
	
	public void setPos(int x, int y) {
		this.xPos = x;
		this.yPos = y;
	}
	
	public void setTexture(String texturePath) {
		try {
			this.texture = TextureLoader.getTexture("PNG", new FileInputStream(new File(texturePath)));
			this.textureWidth = texture.getImageWidth();
			this.textureHeight = texture.getImageHeight();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public boolean isColliding(Entity e) {
		boolean collision = false,
		        horizontalCollision = (this.xPos <= e.xPos && this.xPos + this.width >= e.xPos) || (e.xPos <= this.xPos && e.xPos + e.getWidth() >= this.xPos),
		        verticalCollision = (this.yPos <= e.yPos && this.yPos + this.height >= e.yPos) || (e.yPos <= this.yPos && e.yPos + e.getHeight() >= this.yPos);
		
		if (horizontalCollision && verticalCollision) {
			collision = true;
		}
		return collision;
	}
	
	public void move(int x, int y) {
		this.xPos += x;
		this.yPos += y;
	}
	
	public void draw() {
		
		glEnable(GL_TEXTURE_2D);
		glBindTexture(GL_TEXTURE_2D, texture.getTextureID());
		glBegin(GL_TRIANGLES);
		{
		glTexCoord2f((float) width/textureWidth, 0);
		glVertex2i(xPos + width, yPos);
		glTexCoord2f(0, 0);
		glVertex2i(xPos, yPos);
		glTexCoord2f(0, (float) height/textureHeight);
		glVertex2i(xPos, yPos + height);
		 
		glTexCoord2f(0, (float) height/textureHeight);
		glVertex2i(xPos, yPos + height);
		glTexCoord2f((float) width/textureWidth, (float) height/textureHeight);
		glVertex2i(xPos + width, yPos + height);
		glTexCoord2f((float) width/textureWidth, 0);
		glVertex2i(xPos + width, yPos);
		}
		glEnd();
		/*glBegin(GL_QUADS);
		{
		glTexCoord2f(0, 0);
		glVertex2i(xPos, yPos);
		glTexCoord2f(0, (float) height/textureHeight);
		glVertex2i(xPos + width, yPos);
		glTexCoord2f((float) width/textureWidth, (float) height/textureHeight);
		glVertex2i(xPos + width, yPos + height);
		glTexCoord2f((float) width/textureWidth, 0);
		glVertex2i(xPos, yPos + height);
		}
		glEnd();*/
		glBindTexture(GL_TEXTURE_2D, 0);
		glDisable(GL_TEXTURE_2D);
	}
}
