import java.util.ArrayList;

import javafx.scene.image.Image;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

/**
 A MovableFighter is an Actor that can move, and is able to hit enemy MovableFighters. Different MovableFighters have different strengths, and each MovableFighter has a health bar. 
 */
public class MovableFighter extends Actor {

	Rectangle healthBar = new Rectangle();
	int maxHealth;
	int currentHealth;
	int energyCost;
	double dx;
	double dy;
	boolean playerTeam;
	boolean onField;
	int moveCounter;
	boolean isDead;
	boolean isTower;
	ArrayList<Image> walkImage = new ArrayList<Image>();
	ArrayList<Image> fightImage = new ArrayList<Image>();
	/**Creates a MovableFighter that is able to survive a certain number of hits (based on strength parameter) and requires a certain amount of energy to make (based on strength). Based on value of pTeam and isT, adds particular images to the walkImage and fightImage ArrayLists. Sets the color of the healthBar based on the value of pTeam.
	 * 
	 * @param strength the strength of the movable fighter, determining how its cost and health
	 * @param pTeam a value of true means player team, whereas a value of false means enemy team
	 * @param isT a value of true means tower whereas false means not a tower. If this is true, the value of strength is irrelevant.
	 */

	MovableFighter(int strength, boolean pTeam, boolean isT) {
		if (isT) {
			onField = true;
			setFitHeight(55);
			maxHealth = 200;

			if (pTeam) {
				this.setImage(new Image("right up blue.png"));
				walkImage.add(new Image("right up blue.png"));
				walkImage.add(new Image("left up blue.png"));
				fightImage.add(new Image("right up blue.png"));
				fightImage.add(new Image("down blue.png"));
			} else {
				this.setImage(new Image("right up red.png"));
				walkImage.add(new Image("right up red.png"));
				walkImage.add(new Image("left up red.png"));
				fightImage.add(new Image("right up red.png"));
				fightImage.add(new Image("down red.png"));
			}
		} else {
			setFitHeight(35);
		}
		if (strength == 1) {
			energyCost = 2;
			maxHealth = 10;
			if (pTeam) {
				this.setImage(new Image("s o 3.png"));
				walkImage.add(new Image("s o 3.png"));
				walkImage.add(new Image("s o 4.png"));
				fightImage.add(new Image("s o 1.png"));
				fightImage.add(new Image("s o 2.png"));
			} else {
				this.setImage(new Image("s p 3.png"));
				walkImage.add(new Image("s p 3.png"));
				walkImage.add(new Image("s p 4.png"));
				fightImage.add(new Image("s p 1.png"));
				fightImage.add(new Image("s p 2.png"));
			}
		} else if (strength == 2) {
			energyCost = 4;
			maxHealth = 30;
			if (pTeam) {
				this.setImage(new Image("med o 3.png"));
				walkImage.add(new Image("med o 3.png"));
				walkImage.add(new Image("med o 4.png"));
				fightImage.add(new Image("med o 1.png"));
				fightImage.add(new Image("med o 2.png"));
			} else {
				this.setImage(new Image("med p 3.png"));
				walkImage.add(new Image("med p 3.png"));
				walkImage.add(new Image("med p 4.png"));
				fightImage.add(new Image("med p 1.png"));
				fightImage.add(new Image("med p 2.png"));
			}
		} else if (strength == 3) {
			energyCost = 6;
			maxHealth = 90;
			if (pTeam) {
				this.setImage(new Image("big o 3.png"));
				walkImage.add(new Image("big o 3.png"));
				walkImage.add(new Image("big o 4.png"));
				fightImage.add(new Image("big o 1.png"));
				fightImage.add(new Image("big o 2.png"));
			} else {
				this.setImage(new Image("big p 3.png"));
				walkImage.add(new Image("big p 3.png"));
				walkImage.add(new Image("big p 4.png"));
				fightImage.add(new Image("big p 1.png"));
				fightImage.add(new Image("big p 2.png"));
			}
		}

		healthBar.setX(this.getX());
		healthBar.setY(this.getY() - 10);

		healthBar.setWidth(50);
		healthBar.setHeight(5);

		if (pTeam) {
			healthBar.setFill(Paint.valueOf("BLUE"));
		} else {
			healthBar.setFill(Paint.valueOf("RED"));
		}

		playerTeam = pTeam;
		dx = 0;
		if (playerTeam) {
			dy = -0.3;
		} else {
			dy = 0.3;
		}
		if (isT) {
			dx = 0;
			dy = 0;
		}
		setPreserveRatio(true);
		currentHealth = maxHealth;
		isTower = isT;
	}
	
	/**
	 * Returns the maximum number of hits that the fighter can take. 
	 * @return the maximum health of this fighter
	 */
	public int getNumOfHits() {
		return maxHealth;
	}

	/**
	 * Returns the value of playerTeam. 
	 * @return the truth value of this MovableFighter being on the player team.
	 */
	public boolean getPlayerTeam() {
		return playerTeam;
	}
	/**
	 * Returns the energy cost of this MovableFighter
	 * @return the energy cost of this Movable Fighter
	 */
	public int getEnergyCost() {
		return energyCost;
	}

	
	/**
	 * If it is not on the field, it will do nothing. Else, it will have a health bar, die based on its x and y positions, or die based on its current health. It will move towards the closest enemy and hit any enemies it is intersecting. 
	 */
	public void act(long now) {
		if (!onField) {

		} else {
			if (healthBar.getParent() == null && getWorld() != null) {
				getWorld().add(healthBar);
				healthBar.setX(this.getX());
				healthBar.setY(this.getY() - 10);
			} else {
				if (getX() == 0) {
					die();
				}
				if (getY() == 0) {
					die();
				}
				if (getX() + getWidth() == getWorld().getWidth()) {
					die();
				}
				if (getY() + getHeight() == getWorld().getHeight()) {
					die();
				}
				if (currentHealth <= 0) {
					die();
				}
				if (getIntersectingLiveEnemies() == null) {

				} else if (getIntersectingLiveEnemies().size() == 0 && getIntersectingLiveEnemies() != null) {
					// find new dx and dy
					if (isTower) {
					} else {
						try {
						double xVector = getNearestLivingEnemy().getX() - getX();
						double yVector = getNearestLivingEnemy().getY() - getY();
						dx = xVector / (Math.sqrt(xVector * xVector + yVector * yVector)) / 5;
						dy = yVector / (Math.sqrt(xVector * xVector + yVector * yVector)) / 5;
						}
						catch(NullPointerException e) {

						}
					}
					move(dx, dy);
				} else {
					moveCounter++;
					if (moveCounter % 20 == 0) {
						hit(this.getIntersectingLiveEnemies().get(0));
					}
				}
			}
		}
	}
	
	/**Reduces the health of the MovableFighter passed in by 1.
	 * 
	 * @param f the MovableFighter that will have its health reduced.
	 */
	public void hit(MovableFighter f) {
		f.setCurrentHealth(f.currentHealth - 1);
		if (getImage().equals(fightImage.get(0))) {
			setImage(fightImage.get(1));
		} else {
			setImage(fightImage.get(0));
		}
	}
	
	/** Returns the horizontal velocity of this MovableFighter
	 * 
	 * @return the horizontal velocity of this MovableFighter
	 */
	public double getDx() {
		return dx;
	}
	/** Returns the vertical velocity of this MovableFighter
	 * 
	 * @return the vertical velocity of this MovableFighter
	 */
	public double getDy() {
		return dy;
	}

	public void setDx(double changeX) {
		dx = changeX;
	}
	
	/** Sets the vertical velocity of this MovableFighter
	 * 
	 * @param changeY the new vertical velocity of this MovableFighter
	 */
	public void setDy(double changeY) {
		dy = changeY;
	}
	
	/**Removes this MovableFighter from its World
	 * 
	 */
	public void die() {
		isDead = true;
		getWorld().remove(this);
	}

	/**Will move to a location based on dx and dy. Will switch images on a periodic basis to make the fighter look like its walking. 
	 * 
	 */
	public void move(double dx, double dy) {
		super.move(dx, dy);
		moveCounter++;
		if (moveCounter % 10 == 0) {
			if (getImage().equals(walkImage.get(0))) {
				setImage(walkImage.get(1));
			} else {
				setImage(walkImage.get(0));
			}
		}
		healthBar.setX(this.getX());
		healthBar.setY(this.getY() - 10);

	}
	
	/** Will change the value of currentHealth and adjust the width of healthBar depending on the value of num. 
	 * 
	 * @param num the new currentHealth of this MovableFighter
	 */
	public void setCurrentHealth(int num) {
		currentHealth = num;
		double width = ((double) currentHealth / maxHealth) * 50;
		healthBar.setWidth(width);

	}
	
	/** Returns a list of all living enemies this MovableFighter intersects.
	 * 
	 * @return a list of all living enemies this MovableFighter intersects.
	 */
	public ArrayList<MovableFighter> getIntersectingLiveEnemies() {
		@SuppressWarnings("unchecked")
		ArrayList<MovableFighter> intersectingFighters = (ArrayList<MovableFighter>) this
				.getIntersectingObjects(this.getClass());
		if (intersectingFighters == null) {
			return null;
		}
		ArrayList<MovableFighter> enemies = new ArrayList<MovableFighter>();
		for (int i = 0; i < intersectingFighters.size(); i++) {
			if (intersectingFighters.get(i).playerTeam != this.playerTeam && !intersectingFighters.get(i).isDead) {
				enemies.add(intersectingFighters.get(i));
			}
		}
		return enemies;
	}
	
	/**Returns the rectangle representing the Health Bar.
	 * 
	 * @return the rectangle representing the Health Bar.
	 */
	public Rectangle getHealthBar() {
		return healthBar;
	}
	
	/** Returns the closest living enemy that is on the field.
	 * 
	 * @return closest living enemy that is on the field.
	 */
	public MovableFighter getNearestLivingEnemy() {
		@SuppressWarnings("unchecked")
		ArrayList<MovableFighter> allFighters = (ArrayList<MovableFighter>) getWorld().getObjects(this.getClass());
		ArrayList<MovableFighter> allEnemies = new ArrayList<MovableFighter>();
		for (MovableFighter m : allFighters) {
			if (m.getPlayerTeam() != this.getPlayerTeam() && !m.isDead && m.onField) {
				allEnemies.add(m);
			}
		}
		if (allEnemies.size() == 0) {
			return null;
		}
		MovableFighter closestEnemy = allEnemies.get(0);
		for (MovableFighter m : allEnemies) {
			if (Math.sqrt((this.getX() - m.getX()) * (this.getX() - m.getX())
					+ (this.getY() - m.getY()) * (this.getY() - m.getY())) <= Math
							.sqrt((this.getX() - closestEnemy.getX()) * (this.getX() - closestEnemy.getX())
									+ (this.getY() - closestEnemy.getY()) * (this.getY() - closestEnemy.getY()))) {
				closestEnemy = m;
			}
		}
		return closestEnemy;
	}
	
	/** Returns whether this movable fighter is on the field and in action
	 * 
	 * @return whether this movable fighter is on the field and in action
	 */
	public boolean getOnField() {
		return onField;
	}
	
	/** Set whether this movable fighter is on the field and in action
	 * 
	 * @param onF the new truth value of whether this movable fighter is on the field.
	 */
	public void setOnField(boolean onF) {
		onField = onF;
	}
}
