import java.awt.Rectangle;
import java.awt.Graphics;
import java.util.ArrayList;

/**
 * This class represents an Herbivore (abstractly)
 *
 * @author Graham Wright
 * @version 1.0
 */
public abstract class Herbivore extends Fish {

    /**
     * The Herbivore constructor
     *
     * @param x The x coordinate of the top left of the fish's location
     * @param y The y coordinate of the top left of the fish's location
     * @param bounds The Rectangle bounding the Ocean
     */
    public Herbivore(int x, int y, Rectangle bounds) {
        super(x, y, bounds);
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
    public boolean canEatFish(Fish other) {
        return false;
    }

    /**
     * Moves the Fish and eats seaweed if needed
     */
    public void move() {
        super.move();
        if (getHealth() < 10) {
            eatSeaweed();
        }
    }

    /**
     * Replenishes the health of the Herbivore from eating seaweed
     */
    public void eatSeaweed() {
        replenishHealth();
    }

    /**
     * Draws the Fish to the screen
     *
     * @param g The Graphics object for the program
     */
    public abstract void draw(Graphics g);
}
