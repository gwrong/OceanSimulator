import java.awt.Rectangle;
import java.awt.Graphics;
import java.util.ArrayList;

/**
 * This class represents a Carnivore (abstractly)
 *
 * @author Graham Wright
 * @version 1.0
 */
public abstract class Carnivore extends Fish {

    /**
     * The Carnivore constructor
     *
     * @param x The x coordinate of the top left of the fish's location
     * @param y The y coordinate of the top left of the fish's location
     * @param bounds The Rectangle bounding the Ocean
     */
    public Carnivore(int x, int y, Rectangle bounds) {
        super(x, y, bounds);
    }

    /**
     * The Carnivore constructor
     *
     * @param x The x coordinate of the top left of the fish's location
     * @param y The y coordinate of the top left of the fish's location
     * @param bounds The Rectangle bounding the Ocean
     */
    public Carnivore(int x, int y, int vecX, int vecY, Rectangle bounds) {
        super(x, y, vecX, vecY, bounds);
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
     * Eats the other Fish
     *
     * @param other The other Fish to eat
     */
    public void eatFish(Fish other) {

        replenishHealth();
        other.declareDead();
    }

    /**
     * Draws the Fish to the screen
     *
     * @param g The Graphics object for the program
     */
    public abstract void draw(Graphics g);
}
