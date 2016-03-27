package SS2.GUI;

import javax.swing.*;
import java.awt.*;

/**
 * Created by dimitri on 3/26/16.
 */
public class ControleFrame extends JFrame{

    public ControleFrame() {

        this.setTitle("SSD2 - BSP Tree");
        this.setSize(200, 230);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());
        this.add(new ControlPanel());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
