import java.util.ArrayList;
import java.util.List;

import javafx.scene.image.ImageView;

/**
 *Actor is an abstract base class for general sprites in an arcade style game. Because Actor extends ImageView, you have access to all the ImageView commands such as:
 <p> - getX(), getY(), setX(), setY()
 <p> - setImage()
 <p> - getFitHeight(), getFitWidth()
 */
public abstract class Actor extends ImageView {
	/**This method is called every frame once start has been called on the world
	 * 
	 * @param now the time since the timer has been started
	 */
	public abstract void act(long now);
	/** Creates a default Actor and is added to a World from outside this class.
	 */
	public Actor() {
		
	}
	
	/**Moves this actor by the given dx and dy.
	 * 
	 * @param dx the distance moved in the x-direction
	 * @param dy the distance moved in the y-direction
	 */
	public void move(double dx, double dy) {
		this.setX(this.getX() + dx);
		this.setY(this.getY() + dy);
	}
	
	/**Returns the World this actor is in, or null if it is not in a world.
	 * 
	 * @return The world that this actor belongs to
	 */
	public World getWorld() {
		return (World) getParent();
	}

	/**Returns the width of the current image of this actor
	 * 
	 * @return the width of the current image of this actor
	 */
	public double getWidth() {
		return getBoundsInParent().getWidth();
	}

	/**Returns the height of the current image of this actor
	 * 
	 * @return the height of the current image of this actor
	 */
	public double getHeight() {
		return getBoundsInParent().getHeight();
	}
	
	/**
	 * Returns a list of all the actors of a given type intersecting this actor.
	 * 
	 * @param cls given class
	 * @return a list of all the actors of a given type intersecting this actor.
	 */
	public <A extends Actor> List<A> getIntersectingObjects(Class<A> cls) {
		if (getWorld() == null) {
			return null;
		}
		ArrayList<A> classObj = (ArrayList<A>) getWorld().getObjects(cls);
		ArrayList<A> intersectingObj = new ArrayList<A>();
		for (A act : classObj) {
			if (act.intersects(this.getBoundsInLocal())) {
				intersectingObj.add(act);
			}
		}
		return intersectingObj;
	}
	/**Returns one actor of the given class that is intersecting this actor.
	 * 
	 * @param cls given class
	 * @return one actor of the given class that is intersecting this actor.
	 */
	public <A extends Actor> A getOneIntersectingObject(Class<A> cls) {
		return getIntersectingObjects(cls).get(0);
	}
}
