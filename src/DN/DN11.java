package DN;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DN11 {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Velike črke");
        frame.setSize(900, 500);
        frame.setLocation(300, 200);

        JTextArea originalTA = new JTextArea();
        JTextArea copyTA = new JTextArea();
        JButton convertB = new JButton("--> pretvori");

        convertB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                copyTA.setText(originalTA.getText().toUpperCase());
            }
        });

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc;

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 2.0 / 4;
        gbc.weighty = 1;
        gbc.insets = new Insets(10, 10, 10, 10);
        panel.add(originalTA, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.insets = new Insets(10, 10, 10, 10);
        panel.add(convertB, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 2.0 / 4;
        gbc.weighty = 1;
        gbc.insets = new Insets(10, 10, 10, 10);
        panel.add(copyTA, gbc);

        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

}
