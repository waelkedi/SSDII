package SS2.GUI;

import SS2.BSPTree.BSPTree;

import javax.swing.*;

/**
 * Created by dimitri on 24/01/16.
 */
public class  PrintFrame extends JFrame{


        public PrintFrame(BSPTree tree, String name){

                this.setTitle("BSP" + name);
                this.setSize(1200, 800);
                this.setLocationRelativeTo(null);
                this.setContentPane(new PaintPanel(tree));
                this.setVisible(true);
        }

        public PrintFrame(BSPTree tree){

                this.setTitle("BSP Tree");
                this.setSize(1200, 800);
                this.setLocationRelativeTo(null);
                this.setContentPane(new PaintPanel(tree));
                this.setVisible(true);
        }
}
