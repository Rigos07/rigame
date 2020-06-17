import static org.lwjgl.opengl.GL11.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

public abstract class Entity {
	private Texture texture;
	protected double xPos;
	protected double yPos;
	private int width;
	private int height;
	private int textureWidth;
	private int textureHeight;
	private int textureTotalWidth;
	private int textureTotalHeight;
	
	public Entity(String texturePath, int sizeX, int sizeY, int textureX, int textureY) {
		this.xPos = 0;
		this.yPos = 0;
		this.width = sizeX;
		this.height = sizeY;
		this.textureWidth = textureX;
		this.textureHeight = textureY;
		try {
			this.texture = TextureLoader.getTexture("PNG", new FileInputStream(new File(texturePath)));
			this.textureTotalWidth = texture.getImageWidth();
			this.textureTotalHeight = texture.getImageHeight();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public double getXPos() {
		return this.xPos;
	}
	
	public double getYPos() {
		return this.yPos;
	}
	
	public int getWidth() {
		return this.width;
	}
	
	public int getHeight() {
		return this.height;
	}
	
	public int getCenterX() {
		return this.width/2;
	}
	
	public int getCenterY() {
		return this.height/2;
	}
	
	public void setPos(double x, double y) {
		this.xPos = x;
		this.yPos = y;
	}
	
	public void setSize(int x, int y) {
		this.width = x;
		this.height = y;
	}
	
	public void setTexture(String texturePath, int sizeX, int sizeY) {
		try {
			Texture newTexture = TextureLoader.getTexture("PNG", new FileInputStream(new File(texturePath)));
			this.texture.release();
			this.texture = newTexture;
			this.textureWidth = sizeX;
			this.textureHeight = sizeY;
			this.textureTotalWidth = texture.getImageWidth();
			this.textureTotalHeight = texture.getImageHeight();
			
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
	
	public boolean isContainedBy(Entity e) {
		boolean contained = false,
		        horizontalContained = (this.xPos >= e.xPos && e.xPos + e.getWidth() >= this.xPos + this.width),
		        verticalContained = (this.yPos >= e.xPos && e.yPos + e.getHeight() >= this.yPos + this.height);
		
		if (horizontalContained && verticalContained) {
			contained = true;
		}
		return contained;
	}
	
	public void move(int x, int y) {
		this.xPos += x;
		this.yPos += y;
	}
	
	public void draw() {
		
		glEnable(GL_TEXTURE_2D);
		glBindTexture(GL_TEXTURE_2D, texture.getTextureID());
		/*glBegin(GL_TRIANGLES);
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
		glEnd();*/
		glBegin(GL_QUADS);
		double xratio = ((double) this.textureWidth)/ ((double) this.textureTotalWidth), yratio = ((double) this.textureHeight)/((double) this.textureTotalHeight);
		{
		glTexCoord2d(xratio, 0);
		glVertex2d(xPos + width, yPos);
		glTexCoord2d(xratio, yratio);
		glVertex2d(xPos + width, yPos + height);
		glTexCoord2d(0, yratio);
		glVertex2d(xPos, yPos + height);
		glTexCoord2d(0, 0);
		glVertex2d(xPos, yPos);
		}
		glEnd();
		glBindTexture(GL_TEXTURE_2D, 0);
		glDisable(GL_TEXTURE_2D);
	}
}
