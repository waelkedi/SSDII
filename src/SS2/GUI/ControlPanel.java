package SS2.GUI;

import SS2.BSPTree.BSPTree;
import SS2.BSPTree.BSPTreeFactory;
import SS2.BSPTree.heuristic.Heuristic;
import SS2.BSPTree.heuristic.InOrder;
import SS2.BSPTree.heuristic.Ramdom;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;


/**
 * Created by dimitri on 24/01/16.
 */


public class ControlPanel extends JPanel implements ActionListener {

    final JLabel fileLabel;
    final JButton fileButton;
    final JFileChooser chooser = new JFileChooser();
    final JButton buttongo;
    private File file;
    final JComboBox comboBox;

    public ControlPanel(){


        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBorder(new EmptyBorder(15, 15, 15, 15));

        JLabel label = new JLabel("Select file:");
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        label.setBorder(new EmptyBorder(0,8,8,8));
        add(label);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

        fileLabel = new JLabel("Null");
        fileLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(fileLabel);

        fileButton = new JButton("...");
        fileButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        fileButton.addActionListener(this);
        panel.add(fileButton);

        panel.setBorder(new EmptyBorder(8,8,8,8));
        add(panel);

        JLabel label2 = new JLabel("Select tree constructor:");
        label2.setAlignmentX(Component.CENTER_ALIGNMENT);
        label2.setBorder(new EmptyBorder(8,8,8,8));
        add(label2);


        Heuristic[] heuristics = {new InOrder(),new Ramdom()};

        comboBox = new JComboBox(heuristics);
        comboBox.setAlignmentX(Component.CENTER_ALIGNMENT);
        comboBox.setBorder(new EmptyBorder(8,8,8,8));
        add(comboBox);

        buttongo = new JButton("Go");
        buttongo.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttongo.addActionListener(this);
        add(buttongo);

    }

    public void actionPerformed(ActionEvent e) {

        System.out.print("ll");

        if (e.getSource() == fileButton){
            int returnVal = chooser.showOpenDialog(this);
            if(returnVal == JFileChooser.APPROVE_OPTION) {
                        fileLabel.setText(chooser.getSelectedFile().getName());
                        file = chooser.getSelectedFile();
            }
        } else if (e.getSource() == buttongo){

            try {
                BSPTree tree = BSPTreeFactory.createTree(file, (Heuristic) comboBox.getSelectedItem());
                new PrintFrame(tree,"Ramdom " + file.getName());
            }catch (Exception exception){
                JOptionPane.showMessageDialog(this,
                        "Not good file",
                        "error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
