package com.enigma;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class RotorGraphics extends JPanel {

    JLabel[] labels = new JLabel[26];
    JLabel a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z;
    private JPanel panel;

    public RotorGraphics() {
        panel = new JPanel();
        panel.setLayout(new GridLayout(26,1));

        a = new JLabel("A",SwingConstants.CENTER);
        b = new JLabel("B",SwingConstants.CENTER);
        c = new JLabel("C",SwingConstants.CENTER);
        d = new JLabel("D",SwingConstants.CENTER);
        e = new JLabel("E",SwingConstants.CENTER);
        f = new JLabel("F",SwingConstants.CENTER);
        g = new JLabel("G",SwingConstants.CENTER);
        h = new JLabel("H",SwingConstants.CENTER);
        i = new JLabel("I",SwingConstants.CENTER);
        j = new JLabel("J",SwingConstants.CENTER);
        k = new JLabel("K",SwingConstants.CENTER);
        l = new JLabel("L",SwingConstants.CENTER);
        m = new JLabel("M",SwingConstants.CENTER);
        n = new JLabel("N",SwingConstants.CENTER);
        o = new JLabel("O",SwingConstants.CENTER);
        p = new JLabel("P",SwingConstants.CENTER);
        q = new JLabel("Q",SwingConstants.CENTER);
        r = new JLabel("R",SwingConstants.CENTER);
        s = new JLabel("S",SwingConstants.CENTER);
        t = new JLabel("T",SwingConstants.CENTER);
        u = new JLabel("U",SwingConstants.CENTER);
        v = new JLabel("V",SwingConstants.CENTER);
        w = new JLabel("W",SwingConstants.CENTER);
        x = new JLabel("X",SwingConstants.CENTER);
        y = new JLabel("Y",SwingConstants.CENTER);
        z = new JLabel("Z",SwingConstants.CENTER);

        rotateGraphic(0);
    }

    public JPanel getPanel() {
        return panel;
    }

    public void resetLabels() {
        for (JLabel label:labels) {
            label.setBackground(Color.white);
        }
    }

    public void rotateGraphic(int instep) {
        labels[instep%26] = a;
        labels[(1+instep)%26] = b;
        labels[(2+instep)%26] = c;
        labels[(3+instep)%26] = d;
        labels[(4+instep)%26] = e;
        labels[(5+instep)%26] = f;
        labels[(6+instep)%26] = g;
        labels[(7+instep)%26] = h;
        labels[(8+instep)%26] = i;
        labels[(9+instep)%26] = j;
        labels[(10+instep)%26] = k;
        labels[(11+instep)%26] = l;
        labels[(12+instep)%26] = m;
        labels[(13+instep)%26] = n;
        labels[(14+instep)%26] = o;
        labels[(15+instep)%26] = p;
        labels[(16+instep)%26] = q;
        labels[(17+instep)%26] = r;
        labels[(18+instep)%26] = s;
        labels[(19+instep)%26] = t;
        labels[(20+instep)%26] = u;
        labels[(21+instep)%26] = v;
        labels[(22+instep)%26] = w;
        labels[(23+instep)%26] = x;
        labels[(24+instep)%26] = y;
        labels[(25+instep)%26] = z;

        Border border = BorderFactory.createLineBorder(Color.BLACK,2);
        for (int index=0;index<labels.length;index++)  {
            labels[index].setBounds(0,(650/26)*index,100,650/26);
            labels[index].setBorder(border);
            labels[index].setVerticalAlignment(SwingConstants.CENTER);
            labels[index].setOpaque(true);
            labels[index].setBackground(Color.white);
            panel.add(labels[index]);
        }
    }

    public void lightForwardLabel(int index) {
        labels[index%26].setBackground(Color.yellow);
    }

    public void lightBackwardLabel(int index) {
        labels[index%26].setBackground(Color.cyan);
    }
}
