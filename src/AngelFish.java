import java.awt.image.BufferedImage;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.ArrayList;
import java.util.Random;

/**
 * This class represents an AngelFish which is a Carnivore, reproduces often
 * and has a maximum of 2 offspring at a time. An angelfish moves by
 * teleporting to a random spot inside the Ocean every once in a while due
 * to its magical nature
 *
 * @author Graham Wright
 * @version 1.0
 */
public class AngelFish extends Carnivore {

    private final double reproductionProbability = 0.02;
    private final int maxOffspring = 2;
    private final String img = "resources/angelFish.png";
    private int numMovesStationary;

    /**
     * The AngelFish constructor
     *
     * @param x The x coordinate of the top left of the fish's location
     * @param y The y coordinate of the top left of the fish's location
     * @param bounds The Rectangle bounding the Ocean
     */
    public AngelFish(int x, int y, Rectangle bounds) {
        super(x, y, bounds);
        numMovesStationary = 0;
    }

    /**
     * Moves the AngelFish to a random spot in the Ocean with its magical
     * powers
     */
    public void move() {
        if (numMovesStationary >= 50) {
            Random rand = new Random();
            int posX = rand.nextInt((int) getBounds().getWidth() - FISH_WIDTH
                                                                        + 1);
            int posY = rand.nextInt((int) getBounds().getHeight() - FISH_HEIGHT
                                                                        + 1);
            setX(posX);
            setY(posY);
            numMovesStationary = 0;
        } else {
            numMovesStationary++;
        }

        decrementHealth();
        incrementAge();
    }

    /**
     * Determines if this Fish can reproduce with another
     *
     * @param other The other Fish to check reproduction capabilities with
     * @return boolean Whether or not the Fish can reproduce with the other
     */
    public boolean canReproduceWithFish(Fish other) {
        return other instanceof AngelFish && getAge()
            > Fish.MIN_REPRODUCTION_AGE && other.getAge()
            > Fish.MIN_REPRODUCTION_AGE
            && getAge() < Fish.MAX_REPRODUCTION_AGE && other.getAge()
            < Fish.MAX_REPRODUCTION_AGE;
    }

    /**
     * Returns the Fish formed from reproduction between this Fish and other
     *
     * @param other The other Fish to mate with
     * @return ArrayList<Fish> The ArrayList of Fish produced
     */
    public ArrayList<Fish> reproduceWithFish(Fish other) {
        int numOffspring = Fish.calculateNumberOfOffspring(
                                    reproductionProbability, maxOffspring);
        ArrayList<Fish> offspring = new ArrayList<Fish>(numOffspring);
        for (int i = 0; i < numOffspring; i++) {
            offspring.add(new AngelFish(getX(), getY(), getBounds()));
        }
        return offspring;
    }

    /**
     * Determines if this Fish can eat the other. AngelFish cannot eat other
     * Fish so returns false
     *
     * @param other The other Fish to potentially eat
     * @return boolean Whether or not the Fish eat the other
     */
    public boolean canEatFish(Fish other) {
        return false;
    }

    /**
     * Draws the Fish to the screen
     *
     * @param g The Graphics object for the program
     */
    public void draw(Graphics g) {
        BufferedImage buffImg = null;
        try {
            buffImg = ImageIO.read(new File(img));
        } catch (IOException e) {
            System.exit(0);
        }
        g.drawImage(buffImg, getX(), getY(), null);
    }
}