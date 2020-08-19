package edu.fiuba.algo3.interfazGrafica;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.LayoutStyle;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("GroupLayout");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container contentPane = frame.getContentPane();

        GroupLayout groupLayout = new GroupLayout(contentPane);

        contentPane.setLayout(groupLayout);

        JLabel label = new JLabel("Label");
        JButton b2 = new JButton("Second Button");

        groupLayout.setHorizontalGroup(groupLayout.createSequentialGroup()
                .addComponent(label).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(b2));

        groupLayout.setVerticalGroup(groupLayout
                .createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(label)
                .addComponent(b2));

        frame.pack();
        frame.setVisible(true);
    }
}