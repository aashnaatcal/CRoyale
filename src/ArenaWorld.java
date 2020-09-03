import java.util.ArrayList;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

/**An ArenaWorld is an extension of World that is more specific to this game. It allows for us to check when the game is over and has the logic that creates new MovableFighters of the opposite team.

Has a checkCounter attribute that occasionally checks if the game is over. It is only occasionally to ensure that the game does not run slowly. 

*/
public class ArenaWorld extends World {
	int checkCounter = 0;
	int difficulty;

	
	/**Increments the checkCounter. Occasionally checks the methods getNewBlue() and getNewRed() to find out how many towers are standing of each team. Depending on the results of both methods, it will create a “You Won” or “You Lost” alert. This method will also randomly create new fighters of the opposing team at a random strength at a random location, the amount of enemies determined by the level of difficulty.
	 * 
	 */
	@Override
	public void act(long now) {
		checkCounter++;
		if (checkCounter % 20 == 0) {
			if (getNewBlue() == 0) {
				stop();
				Alert a = new Alert(AlertType.INFORMATION, "You have lost!", ButtonType.OK);
				a.setOnShowing(new Game.OKButtonHandler());
				a.show();
			} else if (getNewRed() == 0) {
				stop();
				Alert a = new Alert(AlertType.INFORMATION, "You have won!", ButtonType.OK);
				a.setOnShowing(new Game.OKButtonHandler());
				a.show();
			}
		}
		if (difficulty == 1) {
			if (Math.random() < 0.003) {
				MovableFighter f = new MovableFighter((int) (Math.random() * 3) + 1, false, false);
				f.setX((Math.random() * 350) + 220);
				f.setY((Math.random() * 275) + 75);
				f.setOnField(true);
				add(f);
			}
		}
		else if (difficulty == 2) {
			if (Math.random() < 0.004) {
				MovableFighter f = new MovableFighter((int) (Math.random() * 3) + 1, false, false);
				f.setX((Math.random() * 350) + 220);
				f.setY((Math.random() * 275) + 75);
				f.setOnField(true);
				add(f);
			}
		}
		else {
			if (Math.random() < 0.006) {
				MovableFighter f = new MovableFighter((int) (Math.random() * 3) + 1, false, false);
				f.setX((Math.random() * 350) + 220);
				f.setY((Math.random() * 275) + 75);
				f.setOnField(true);
				add(f);
			}
		}
		
	}
	
	/**Sets the intensity of the game
	 * 
	 * @param num the intensity level of the game from 1-3
	 */
	public void setIntensity(int num) {
		difficulty = num;
	}
	/** Will return the number of blue towers still remaining. Very intensive method so we try not to run too often
	 * 
	 * @return the number of blue towers still remaining.
	 */
	private int getNewBlue() {
		@SuppressWarnings("unchecked")
		ArrayList<MovableFighter> allFighter = (ArrayList<MovableFighter>) this
				.getObjects(new MovableFighter(0, true, true).getClass());
		int counter = 0;
		for (MovableFighter f : allFighter) {
			if (f.isTower && f.playerTeam && !f.isDead) {
				counter++;
			}
		}
		return counter;
	}
	/** Will return the number of red towers still remaining. Very intensive method so we try not to run too often
	 * 
	 * @return the number of red towers still remaining.
	 */
	public int getNewRed() {
		@SuppressWarnings("unchecked")
		ArrayList<MovableFighter> allFighter = (ArrayList<MovableFighter>) this
				.getObjects(new MovableFighter(0, true, true).getClass());
		int counter = 0;
		for (MovableFighter f : allFighter) {
			if (f.isTower && !f.playerTeam && !f.isDead) {
				counter++;
			}
		}
		return counter;
	}

}
