import java.awt.image.BufferedImage;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.ArrayList;

/**
 * This class represents a ParrotFish which is an Herbivore, reproduces often
 * and has a max of 1 offspring at a time
 *
 * @author Graham Wright
 * @version 1.0
 */
public class ParrotFish extends Herbivore {

    private final double reproductionProbability = 0.02;
    private final int maxOffspring = 1;
    private final String img = "resources/parrotFish.png";

    /**
     * The ParrotFish constructor
     *
     * @param x The x coordinate of the top left of the fish's location
     * @param y The y coordinate of the top left of the fish's location
     * @param bounds The Rectangle bounding the Ocean
     */
    public ParrotFish(int x, int y, Rectangle bounds) {
        super(x, y, bounds);
    }

    /**
     * Determines if this Fish can reproduce with another
     *
     * @param other The other Fish to check reproduction capabilities with
     * @return boolean Whether or not the Fish can reproduce with the other
     */
    public boolean canReproduceWithFish(Fish other) {
        return other instanceof ParrotFish && getAge()
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
            offspring.add(new ParrotFish(getX(), getY(), getBounds()));
        }
        return offspring;
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
