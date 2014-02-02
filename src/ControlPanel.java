import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * This is the ControlPanel for the Ocean. It allows the
 * user to pick which Fish it would like to add next.
 *
 * @author Doug
 * @version 1.0
 */
public class ControlPanel extends JPanel {
    private JButton surgeonfish, parrotfish, shark, giantSquid, jellyFish,
                                                                    angelFish;
    private JLabel current;

    private String fishType;

    /**
     * The constructor for ControlPanel creates the JButtons and visual aspects
     */
    public ControlPanel() {
        setPreferredSize(new Dimension(150, OceanPanel.HEIGHT));

        surgeonfish = new JButton("SurgeonFish");
        surgeonfish.addActionListener(new ButtonListener("SurgeonFish"));
        // ***Make sure that the String you pass into ButtonListener matches the
        // exact class name of the object.
        add(surgeonfish);

        parrotfish = new JButton("ParrotFish");
        parrotfish.addActionListener(new ButtonListener("ParrotFish"));
        add(parrotfish);

        shark = new JButton("Shark");
        shark.addActionListener(new ButtonListener("Shark"));
        add(shark);

        giantSquid = new JButton("GiantSquid");
        giantSquid.addActionListener(new ButtonListener("GiantSquid"));
        add(giantSquid);

        jellyFish = new JButton("JellyFish");
        jellyFish.addActionListener(new ButtonListener("JellyFish"));
        add(jellyFish);

        angelFish = new JButton("AngelFish");
        angelFish.addActionListener(new ButtonListener("AngelFish"));
        add(angelFish);
        //You are going to need to add more buttons when you add more
        //types of fish.

        //default starting fish
        fishType = "SurgeonFish";
        add(new JLabel("Current Fish"));
        current = new JLabel("SurgeonFish");
        add(current);

        //implement timer speed control if you feel adventurous
    }

    /**
     * Invoked by OceanPanel to determine which Fish
     * was chosen.
     *
     * @return The currently selected Fish type
     */
    public String getFishType() {
        return fishType;
    }

    /**
     * The ButtonListener inner class is the ActionListener for the JButtons
     */
    public class ButtonListener implements ActionListener {
        private String name;

        /**
        * The ButtonListener constructor
        *
        * @param className The name of the class being called for
        */
        public ButtonListener(String className) {
            name = className;
        }

        /**
        * The required actionPerformed method
        *
        * @param e The ActionEvent happening that causes the change in current
        * Fish
        */
        public void actionPerformed(ActionEvent e) {
            fishType = name;
            current.setText(name);
        }
    }
}
