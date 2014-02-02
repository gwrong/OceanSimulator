import java.awt.image.BufferedImage;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.ArrayList;

/**
 * This class represents a JellyFish which is a Carnivore and cannot reproduce.
 * A JellyFish "zaps" any fish that touches it, pushing it away and taking
 * away some health.
 *
 * @author Graham Wright
 * @version 1.0
 */
public class JellyFish extends Carnivore {

    private final double reproductionProbability = 0;
    private final int maxOffspring = 0;
    private final String img = "resources/jellyFish.png";

    /**
     * The JellyFish constructor
     *
     * @param x The x coordinate of the top left of the fish's location
     * @param y The y coordinate of the top left of the fish's location
     * @param bounds The Rectangle bounding the Ocean
     */
    public JellyFish(int x, int y, Rectangle bounds) {
        super(x, y, 0, 0, bounds);
    }

    /**
     * Determines if this Fish can reproduce with another
     *
     * @param other The other Fish to check reproduction capabilities with
     * @return boolean Whether or not the Fish can reproduce with the other
     */
    public boolean canReproduceWithFish(Fish other) {
        return false;
    }

    /**
     * Returns the Fish formed from reproduction between this Fish and other
     *
     * @param other The other Fish to mate with
     * @return ArrayList<Fish> The ArrayList of Fish produced
     */
    public ArrayList<Fish> reproduceWithFish(Fish other) {
        return new ArrayList<Fish>(0);
    }

    /**
     * Determines if this Fish can eat the other
     *
     * @param other The other Fish to potentially eat
     * @return boolean Whether or not the Fish eat the other
     */
    public boolean canEatFish(Fish other) {
        return other instanceof JellyFish && other.getHealth()
                                                        < Fish.MIN_EAT_HEALTH;
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