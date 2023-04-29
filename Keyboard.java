package com.enigma;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Keyboard extends JPanel implements ActionListener {

    private JPanel panel;
    private JButton[] buttons;

    public Keyboard() {
        panel = new JPanel();
        panel.setLayout(new GridLayout(9,3));

        JButton a = new JButton("A");
        JButton b = new JButton("B");
        JButton c = new JButton("C");
        JButton d = new JButton("D");
        JButton e = new JButton("E");
        JButton f = new JButton("F");
        JButton g = new JButton("G");
        JButton h = new JButton("H");
        JButton i = new JButton("I");
        JButton j = new JButton("J");
        JButton k = new JButton("K");
        JButton l = new JButton("L");
        JButton m = new JButton("M");
        JButton n = new JButton("N");
        JButton o = new JButton("O");
        JButton p = new JButton("P");
        JButton q = new JButton("Q");
        JButton r = new JButton("R");
        JButton s = new JButton("S");
        JButton t = new JButton("T");
        JButton u = new JButton("U");
        JButton v = new JButton("V");
        JButton w = new JButton("W");
        JButton x = new JButton("X");
        JButton y = new JButton("Y");
        JButton z = new JButton("Z");

        buttons = new JButton[26];
        buttons[0] = a;
        buttons[1] = b;
        buttons[2] = c;
        buttons[3] = d;
        buttons[4] = e;
        buttons[5] = f;
        buttons[6] = g;
        buttons[7] = h;
        buttons[8] = i;
        buttons[9] = j;
        buttons[10] = k;
        buttons[11] = l;
        buttons[12] = m;
        buttons[13] = n;
        buttons[14] = o;
        buttons[15] = p;
        buttons[16] = q;
        buttons[17] = r;
        buttons[18] = s;
        buttons[19] = t;
        buttons[20] = u;
        buttons[21] = v;
        buttons[22] = w;
        buttons[23] = x;
        buttons[24] = y;
        buttons[25] = z;

        for (int index=0; index< buttons.length; index++){
            panel.add(buttons[index]);
        }
    }

    public JPanel getPanel() {
        return panel;
    }

    public JButton[] getButtons() {
        return buttons;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equalsIgnoreCase("A")) {
            System.out.println("Test");
        }
    }
}
