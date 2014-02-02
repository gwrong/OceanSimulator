import javax.swing.JFrame;
import java.awt.BorderLayout;

/**
 * This class controls the presentation of the Ocean
 *
 * @author CS 1331 TA, maybe?
 * @version 1.0
 */
public class Ocean {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Ocean");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ControlPanel control = new ControlPanel();
        frame.add(control, BorderLayout.WEST);
        frame.add(new OceanPanel(control)); //defaults to CENTER
        frame.pack();
        frame.setVisible(true);
    }
}
