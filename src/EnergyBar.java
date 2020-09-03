import javafx.scene.image.Image;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
/**An energy bar keeps track of the amount of energy that the player has. It updates itself accordingly. Will have a Rectangle attribute.  
 * 
 * 
 *
 */
public class EnergyBar extends Actor {

	Rectangle energy;
	/**Creates an energy bar. Initializes the rectangle to a y position of 715, an x position of 280, a width of 200, and a height of 24. Sets the color of the rectangle to purple and sets the border image and makes it have a width of 275. 
	 * 
	 */
	EnergyBar() {
		energy = new Rectangle(280, 715, 200, 24);
		energy.setFill(Paint.valueOf("PURPLE"));
		this.setImage(new Image("energyBar.png"));
		setPreserveRatio(true);
		setFitWidth(275);
	}

	/**Will constantly increase the amount of energy when it’s not at its maximum capacity
	 * 
	 */
	public void act(long now) {
		if (energy.getWidth() < 262) {
			increaseEnergy();
		}

	}
	/**Will remove the energy if possible. 
	 * 
	 * @param num the amount of energy removed
	 */
	public void removeEnergy(int num) {
		if (num > 10 || num * 26.2 > energy.getWidth()) {
			// System.out.println("I cannot do this.");
		} else {
			energy.setWidth(energy.getWidth() - (26.2 * num));
		}
	}
	/**Returns the purple rectangle representing how much energy is left.
	 * 
	 * @return the purple rectangle representing how much energy is left
	 */
	public Rectangle getRect() {
		return energy;
	}
	/**Increase the width of the rectangle by 0.3
	 * 
	 */
	public void increaseEnergy() {
		energy.setWidth(energy.getWidth() + 0.3);
	}
	/**Returns the amount of energy that the player has
	 * 
	 * @return the amount of energy that the player has
	 */
	public double energyAmount() {
		return energy.getWidth() / 26.2;
	}

}
