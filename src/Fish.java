import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Random;
import java.util.ArrayList;

/**
 * This class represents a Fish (abstractly)
 *
 * A probability of 0.01-0.03 seems too high for the Fish to reproduce, but
 * I have set it to that because I was told to
 *
 * @author Graham Wright
 * @version 1.0
 */
public abstract class Fish {

    public static final int FISH_HEIGHT = 75;
    public static final int FISH_WIDTH = 75;
    public static final int MAX_HEALTH = 1000;
    public static final int MAX_AGE = 700;
    public static final int MIN_REPRODUCTION_AGE = 500;
    public static final int MAX_REPRODUCTION_AGE = 550;
    public static final int MIN_EAT_HEALTH = 500;

    private int health, age;
    private int x, y;
    private Point vector;
    private Rectangle bounds;

    /**
     * The Fish constructor
     *
     * @param x The x coordinate of the top left of the fish's location
     * @param y The y coordinate of the top left of the fish's location
     * @param bounds The Rectangle bounding the Ocean
     */
    public Fish(int x, int y, Rectangle bounds) {
        this.health = MAX_HEALTH;
        this.age = 0;
        this.x = x;
        this.y = y;
        this.bounds = bounds;
        Random rand = new Random();
        this.vector = new Point(rand.nextInt(10) - 5, rand.nextInt(10) - 5);
        if (vector.getX() == 0) {
            vector = new Point(1, (int) vector.getY());
        }
    }

    /**
     * The Fish constructor with velocities
     *
     * @param x The x coordinate of the top left of the fish's location
     * @param y The y coordinate of the top left of the fish's location
     * @param vecX The x component of velocity
     * @param vecY The y component of velocity
     * @param bounds The Rectangle bounding the Ocean
     */
    public Fish(int x, int y, int vecX, int vecY, Rectangle bounds) {
        this.health = MAX_HEALTH;
        this.age = 0;
        this.x = x;
        this.y = y;
        this.bounds = bounds;
        this.vector = new Point(vecX, vecY);
    }

    private void maintainWallCollision() {
        Rectangle curFishBox = new Rectangle(this.x , this.y, FISH_WIDTH,
                                                                FISH_HEIGHT);
        if (curFishBox.getY() < 0) {
            this.y = 0;
            vector = new Point((int) vector.getX(), -1 * (int) vector.getY());
        } else if (curFishBox.getY() + FISH_HEIGHT > (int) bounds.getHeight()) {
            this.y = (int) bounds.getHeight() - FISH_HEIGHT;
            vector = new Point((int) vector.getX(), -1 * (int) vector.getY());
        }

        if (curFishBox.getX() + FISH_WIDTH > (int) bounds.getWidth()) {
            this.x = (int) bounds.getWidth() - FISH_WIDTH;
            vector = new Point(-1 * (int) vector.getX(), (int) vector.getY());

        } else if (curFishBox.getX() < 0) {
            this.x = 0;
            vector = new Point(-1 * (int) vector.getX(), (int) vector.getY());
        }
    }

    /**
     * This method is called on a Fish when a non Jellyfish collides with a
     * Jellyfish, and it hurts the Fish
     */
    public void zap() {
        health -= 25;
        vector = new Point(-1 * (int) vector.getX(), -1 * (int) vector.getY());
    }

    /**
     * This method decides whether or not this fish and another fish collide
     * If there is a collision with a Jellyfish, the Fish reflects in movement
     *
     * @param other The Fish that this Fish is checking collision for
     * @return boolean Whether or not this fish collides with the other fish
     */
    public boolean collidesWithFish(Fish other) {
        Rectangle curFishBox = new Rectangle(this.x, this.y, FISH_WIDTH,
                                                                FISH_HEIGHT);
        Rectangle otherFishBox = new Rectangle(other.x, other.y, FISH_WIDTH,
                                                                FISH_HEIGHT);
        return curFishBox.intersects(otherFishBox);
    }

    /**
     * Decides whether or not the Fish is dead
     *
     * @return boolean Whether or not the fish has a health of 0 or below
     */
    public boolean isDead() {
        return health <= 0;
    }

    /**
     * Decides whether or not the Fish is old enough to die
     *
     * @return boolean Whether or not the fish is too old and should die
     */
    public boolean isTooOld() {
        return age >= MAX_AGE;
    }

    /**
     * Moves the Fish and deducts health points and increases age
     */
    public void move() {
        maintainWallCollision();
        this.x += vector.getX();
        this.y += vector.getY();

        decrementHealth();
        incrementAge();
    }

    /**
     * Sets the Fish's health to 0
     */
    public void declareDead() {
        health = 0;
    }

    /**
     * Returns the health of the fish
     *
     * @return health The Fish's health
     */
    public int getHealth() {
        return health;
    }

    /**
     * Returns the age of the Fish
     *
     * @return age The Fish's health
     */
    public int getAge() {
        return age;
    }

    /**
     * Returns the x coordinate of the fish
     *
     * @return x The Fish's x coordinate
     */
    public int getX() {
        return x;
    }

    /**
     * Returns the y coordinate of the fish
     *
     * @return y The Fish's y coordinate
     */
    public int getY() {
        return y;
    }

    /**
     * Returns the Rectangle bounds
     *
     * @return bounds The ocean Rectangle bounds
     */
    public Rectangle getBounds() {
        return bounds;
    }

    /**
     * Replenishes all health of the fish
     *
     * @return health The Fish's health
     */
    public void replenishHealth() {
        health = MAX_HEALTH;
    }

    /**
     * Increases the age by 1
     */
    public void incrementAge() {
        age++;
    }

    /**
     * Decreases the health by 1
     */
    public void decrementHealth() {
        health--;
    }

    /**
     * Sets the x coordinate of the Fish to x1
     *
     * @param x1 The new x coordinate
     */
    public void setX(int x1) {
        x = x1;
    }

    /**
     * Sets the y coordinate of the Fish to y1
     *
     * @param y1 The new y coordinate
     */
    public void setY(int y1) {
        y = y1;
    }

    /**
     * This method calculates the number of offspring that two Fish produce when
     * reproducing based off of each Fish's probability and maximum allowed
     * offspring. If reproducing, there is a 50% of chance of having an
     * additional baby
     *
     * @param probability The probability of a type of Fish to reproduces
     * @param maxOffspring The maximum number of offspring allowed by the Fish
     *        in one reproduction
     * @return int The number of offspring produced
     */
    public static int calculateNumberOfOffspring(double probability,
                                                            int maxOffspring) {
        int maxRandom = (int) (probability * 1000);
        Random rand1 = new Random();
        int randInt = rand1.nextInt(100) + 1;
        if (randInt > maxRandom) {
            return 0;
        }
        int offspring = 1;
        while (offspring < maxOffspring) {
            Random rand2 = new Random();
            int bit = rand2.nextInt(2);
            if (1 == bit) {
                return offspring;
            } else {
                offspring++;
            }
        }
        return offspring;
    }

    /**
     * Determines if this Fish can reproduce with another
     *
     * @param other The other Fish to check reproduction capabilities with
     * @return boolean Whether or not the Fish can reproduce with the other
     */
    public abstract boolean canReproduceWithFish(Fish other);

    /**
     * Returns the Fish formed from reproduction between this Fish and other
     *
     * @param other The other Fish to mate with
     * @return ArrayList<Fish> The ArrayList of Fish produced
     */
    public abstract ArrayList<Fish> reproduceWithFish(Fish other);

    /**
     * Determines if this Fish can eat the other
     *
     * @param other The other Fish to potentially eat
     * @return boolean Whether or not the Fish eat the other
     */
    public abstract boolean canEatFish(Fish other);

    /**
     * Draws the Fish to the screen
     *
     * @param g The Graphics object for the program
     */
    public abstract void draw(Graphics g);
}
