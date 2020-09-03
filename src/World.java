import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.HashSet;

import javafx.animation.AnimationTimer;
import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;


/**A World is an extension of a Pane that holds Actors (which are custom ImageView objects). Since a World is a Pane it already keeps track of its children Nodes and this list can be accessed via a call to the Pane's getChildren().
A World contains an AnimationTimer which runs its own act() method as well as the act() method for every Actor in the World.
*/
public abstract class World extends Pane {
	HashSet<KeyCode> downKeyCodes = new HashSet<KeyCode>();
	private AnimationTimer timer;

	/*Creates a default World with no Actors in it.
	 */
	public World() {
		timer = new AnimationTimer() {

			@Override
			public void handle(long now) {
				act(now);
				try {
					for (Node a : getChildren()) {
						if (a.getClass().equals(new Rectangle().getClass())) {
						} else {
							((Actor) a).act(now);
						}
					}
				} catch (ConcurrentModificationException e) {

				}

			}
		};
	}
	/**This method is called every frame once start has been called on.
	 * 
	 * @param now the time since start has been called
	 */
	public abstract void act(long now);
	
	/**Adds the given actor to the world.
	 * @param actor the actor you wish to add to the world
	 */
	public void add(Actor actor) {
		getChildren().add(actor);
	}

	/**Removes the given actor from the world.
	 * 
	 * @param actor the actor you wish to remove from the world
	 */
	public void remove(Actor actor) {
		getChildren().remove(actor);
	}

	/**Adds the given rectangle to the world
	 * @param rec the rectangle you wish to add to the world
	 */
	public void add(Rectangle rec) {
		getChildren().add(rec);
	}

	/**Starts the timer that calls the act method on the world and on each Actor in the world each frame.
	 * 
	 */
	public void start() {
		timer.start();
	}
	/**Stops the timer that calls the act method
	 * 
	 */
	public void stop() {
		timer.stop();
	}
	/**Returns a list of all the actors in the world of the given class.
	 * 
	 * @param cls the given class
	 * @return a list of all the actors in the given class
	 */
	public <A extends Actor> java.util.List<A> getObjects(java.lang.Class<A> cls) {
		ArrayList<A> ls = new ArrayList<A>();
		for (int i = 0; i < getChildren().size(); i++) {
			if (getChildren().get(i).getClass().equals(cls)) {
				ls.add(cls.cast(getChildren().get(i)));
			}
		}
		return ls;
	}

	
}
