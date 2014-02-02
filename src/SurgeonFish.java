import java.awt.image.BufferedImage;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.ArrayList;

/**
 * This class represents a SurgeonFish that is an Herbivore, reproduces rarely,
 * and has a max offspring of 5
 *
 * @author Graham Wright
 * @version 1.0
 */
public class SurgeonFish extends Herbivore {

    private final double reproductionProbability = 0.01;
    private final int maxOffspring = 5;
    private final String img = "resources/surgeonFish.png";

    /**
     * The SurgeonFish constructor
     *
     * @param x The x coordinate of the top left of the fish's location
     * @param y The y coordinate of the top left of the fish's location
     * @param bounds The Rectangle bounding the Ocean
     */
    public SurgeonFish(int x, int y, Rectangle bounds) {
        super(x, y, bounds);
    }

    /**
     * Determines if this Fish can reproduce with another
     *
     * @param other The other Fish to check reproduction capabilities with
     * @return boolean Whether or not the Fish can reproduce with the other
     */
    public boolean canReproduceWithFish(Fish other) {
        return other instanceof SurgeonFish && getAge()
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
            offspring.add(new SurgeonFish(getX(), getY(), getBounds()));
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