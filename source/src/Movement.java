import org.lwjgl.input.Keyboard;

public class Movement {

	public Movement() {
		// TODO Auto-generated constructor stub
	}
	public void controls4Directions(Player player) {
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
	
	public void controls2DirectionsGravity(Player player) {
		if(Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) {
			player.addForce(new Vector(1,0));
		} if(Keyboard.isKeyDown(Keyboard.KEY_LEFT)) {
			player.addForce(new Vector(-1,0));
		} if(Keyboard.isKeyDown(Keyboard.KEY_UP)) {
			player.addForce(new Vector(0,-4));
		} if(Keyboard.isKeyDown(Keyboard.KEY_DOWN)) {
			player.addForce(new Vector(0,1));
		}
	}
}
