/**
Created on Tue Jan  3 14:25:24 2023

@author: nathanmitchell
**/
package com.enigma;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Enigma extends JFrame implements ActionListener {

    private final String[] e1rotors = {"I", "II", "III"};
    private final String[] m3rotors = {"I", "II", "III", "IV", "V"};
    private final String[] alphabet = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
    private int letterCount = 0;
    private final JComboBox r1, r2, r3, w1, w2, w3;
    private final JFrame f;
    private final JLabel rl1,rl2,rl3, wl1, wl2,wl3, errorLabel, enigmaLabel, settingsLabel, inputLabel, outputLabel,
                            rotor1Label,rotor2Label,rotor3Label, reflectorLabel, plugErrorLabel;
    private JPanel protor1, protor2, protor3;
    private EnigmaImpl enigma;
    private JMenuBar menuBar;
    private JMenu file;
    private JMenuItem enigma1, m3, exit;
    private JTextArea inputText,outputText;
    private JTextField plugInp;
    private RotorGraphics rg1,rg2,rg3,rg4;
    private final int width = 1500;
    private final int height = 900;

    public Enigma(String title) {
        f = new JFrame(title);
        f.setSize(width,height);
        f.setLayout(null);

        // Creating the menu input to select an enigma version
        menuBar = new JMenuBar();
        file = new JMenu("File");
        enigma1 = new JMenuItem("Enigma 1");
        m3 = new JMenuItem("M3");
        exit = new JMenuItem("Exit");
        file.add(enigma1);
        file.add(m3);
        file.add(exit);
        enigma1.addActionListener(this);
        m3.addActionListener(this);
        exit.addActionListener(this);
        menuBar.add(file);

        //Defining the panels for headers and rotor selection
        JPanel versionP = new JPanel();
        versionP.setBounds(650,0,width/2,25);
        JPanel pRotors = new JPanel();
        pRotors.setBounds(650,20,width/2,30);
        JPanel pSubmit = new JPanel();
        pSubmit.setBounds(650,50,width/2,40);

        // adding the labels for the rotor graphics
        JPanel pR1Label = new JPanel();
        pR1Label.setBounds(725,85,100,25);
        rotor1Label = new JLabel("Rotor 1");
        pR1Label.add(rotor1Label);
        JPanel pR2Label = new JPanel();
        pR2Label.setBounds(900,85,100,25);
        rotor2Label = new JLabel("Rotor 2");
        pR2Label.add(rotor2Label);
        JPanel pR3Label = new JPanel();
        pR3Label.setBounds(1075,85,100,25);
        rotor3Label = new JLabel("Rotor 3");
        pR3Label.add(rotor3Label);
        JPanel pReflectorLabel = new JPanel();
        pReflectorLabel.setBounds(1250,85,100,25);
        reflectorLabel = new JLabel("Reflector");
        pReflectorLabel.add(reflectorLabel);

        //Defining the panels for the rotor interface
        rg1 = new RotorGraphics();
        protor1 = rg1.getPanel();
        protor1.setBounds(725,110,100,650);
        rg2 = new RotorGraphics();
        protor2 = rg2.getPanel();
        protor2.setBounds(900,110,100,650);
        rg3 = new RotorGraphics();
        protor3 = rg3.getPanel();
        protor3.setBounds(1075,110,100,650);
        rg4 = new RotorGraphics();
        JPanel pReflector = rg4.getPanel();
        pReflector.setBounds(1250,110,100,650);


        enigmaLabel = new JLabel("Version: Enigma 1");
        versionP.add(enigmaLabel);

        //creating the drop lists for rotor and rotor wheel selection
        r1 = new JComboBox(e1rotors);
        r1.setSelectedIndex(0);
        r2 = new JComboBox(e1rotors);
        r2.setSelectedIndex(1);
        r3 = new JComboBox(e1rotors);
        r3.setSelectedIndex(2);
        rl1 = new JLabel("Rotor:");
        rl2 = new JLabel("Rotor:");
        rl3 = new JLabel("Rotor:");

        w1 = new JComboBox(alphabet);
        w2 = new JComboBox(alphabet);
        w3 = new JComboBox(alphabet);
        wl1 = new JLabel("Wheel:");
        wl2 = new JLabel("Wheel:");
        wl3 = new JLabel("Wheel:");
        errorLabel = new JLabel("");
        errorLabel.setForeground(Color.RED);

        pRotors.add(rl1);
        pRotors.add(r1);
        pRotors.add(wl1);
        pRotors.add(w1);
        pRotors.add(rl2);
        pRotors.add(r2);
        pRotors.add(wl2);
        pRotors.add(w2);
        pRotors.add(rl3);
        pRotors.add(r3);
        pRotors.add(wl3);
        pRotors.add(w3);

        pSubmit.add(errorLabel);
        JButton submit = new JButton("Submit");
        submit.addActionListener(this);
        pSubmit.add(submit);

        JPanel pButtonHeader = new JPanel();
        pButtonHeader.setBounds(0,0, 650,25);
        JLabel keyboardLabel = new JLabel("Keyboard");
        pButtonHeader.add(keyboardLabel);

        //Adding the buttons for the interface
        Keyboard keyboard = new Keyboard();
        JPanel pKeyboard = keyboard.getPanel();
        JButton[] buttons = keyboard.getButtons();
        for (JButton button:buttons) {
            button.addActionListener(this);
        }
        pKeyboard.setBounds(0,25,650,300);

        //Headers of the plugboard
        JPanel pPlugHead = new JPanel();
        JLabel plugHead = new JLabel("Plugboard");
        pPlugHead.setBounds(0,325,650,25);
        pPlugHead.add(plugHead);

        JPanel pPlugboard = new JPanel();
        JLabel plugLabel = new JLabel("Plugboard settings: ");
        plugInp = new JTextField(30);
        pPlugboard.add(plugLabel);
        pPlugboard.add(plugInp);
        pPlugboard.setBounds(0,350,650,50);

        JPanel pPlugApply = new JPanel();
        JButton pApply = new JButton("Apply");
        pPlugApply.add(pApply);
        pPlugApply.setBounds(0,425,650,50);
        pApply.addActionListener(this);

        JPanel pPlugSettings = new JPanel();
        settingsLabel = new JLabel("");
        pPlugSettings.add(settingsLabel);
        pPlugSettings.setBounds(0,475,650,50);

        JPanel pPlugError = new JPanel();
        plugErrorLabel = new JLabel("");
        plugErrorLabel.setForeground(Color.red);
        pPlugError.add(plugErrorLabel);
        pPlugError.setBounds(0,390,650,50);

        //panels for the text area labels
        JPanel pInputTextLabel = new JPanel();
        pInputTextLabel.setBounds(0, 600, 325,25);
        inputLabel = new JLabel("Input");
        pInputTextLabel.add(inputLabel);
        JPanel pOutputTextLabel = new JPanel();
        pOutputTextLabel.setBounds(325, 600, 325,25);
        outputLabel = new JLabel("Output");
        pOutputTextLabel.add(outputLabel);

        //Panels for the text areas (input and output)
        JPanel pText = new JPanel();
        pText.setBounds(0,625,650,100);
        inputText = new JTextArea(20,25);
        outputText = new JTextArea(20,25);
        inputText.setEditable(false);
        outputText.setEditable(false);
        pText.add(inputText);
        pText.add(outputText);

        //Panel for a clear button
        JPanel pClear = new JPanel();
        pClear.setBounds(0, 725,650,30);
        JButton clear = new JButton("Clear");
        clear.addActionListener(this);
        pClear.add(clear);

        //Adding all the panels to the frame
        f.setJMenuBar(menuBar);
        f.add(versionP);
        f.add(pRotors);
        f.add(pSubmit);
        f.add(pR1Label);
        f.add(pR2Label);
        f.add(pR3Label);
        f.add(pReflectorLabel);
        f.add(protor1);
        f.add(protor2);
        f.add(protor3);
        f.add(pReflector);
        f.add(pButtonHeader);
        f.add(pKeyboard);
        f.add(pPlugHead);
        f.add(pPlugboard);
        f.add(pPlugApply);
        f.add(pPlugError);
        f.add(pPlugSettings);
        f.add(pInputTextLabel);
        f.add(pOutputTextLabel);
        f.add(pText);
        f.add(pClear);

        f.setVisible(true);

        enigma = new EnigmaImpl();
    }

    public static void main(String[] args) {
        Enigma e = new Enigma("Enigma");
    }

    // Resetting the input and output message boxes
    private void clear() {
        inputText.setText("");
        outputText.setText("");
        letterCount = 0;
        resetLabels();
    }

    // Applying the plugboard settings
    private void apply() {
        String inputText = plugInp.getText();
        char[] inpChar = inputText.toCharArray();
        if (filterString(inputText)) {
            String plugboardSettings = enigma.plugboard.changeArray(inpChar);
            settingsLabel.setText(plugboardSettings);
            plugErrorLabel.setText("");;
        } else {
            plugErrorLabel.setText("Invalid Plugboard input");
        }
    }

    // checking plugboard input string is valid
    private boolean filterString(String input) {
        if (input.length() > 20) {
            return false;
        }
        char[] strArray = input.toCharArray();
        for (int i=0; i<strArray.length; i++) {
            if (!Character.isAlphabetic(strArray[i])) {
                return false;
            }
            for (int j=i+1; j<strArray.length;j++) {
                if(strArray[i] == strArray[j]) {
                    return false;
                }
            }
        }
        return true;
    }

    // updating the graphics after the rotation of the machine, to mimic the rotor rotating in real life
    private void rotateGraphics() {
        rg1.rotateGraphic(enigma.rotors[0].getRotorPosition());
        rg2.rotateGraphic(enigma.rotors[1].getRotorPosition());
        rg3.rotateGraphic(enigma.rotors[2].getRotorPosition());
        protor1 = rg1.getPanel();
        protor2 = rg2.getPanel();
        protor3 = rg3.getPanel();
    }

    // encrypting a character and updating the GUI given an input from the keyboard
    private void keyboardPressed(String pressed) {
        resetLabels();
        inputText.append(pressed);
        String encryption = enigma.encrypt(pressed);
        char[] encChar = encryption.toCharArray();
        outputText.append(Character.toString(encChar[encChar.length-1]));
        letterCount++;
        checkCount();
        setComboBox();
        rotateGraphics();
        lightLabels(encChar);
    }

    // Performing the action following any button being pressed in the interface
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equalsIgnoreCase("Submit")) {
            if (r1.getSelectedIndex()==r2.getSelectedIndex() || r1.getSelectedIndex()==r3.getSelectedIndex() ||
                r2.getSelectedIndex()==r3.getSelectedIndex()) {
                errorLabel.setText("Cannot select the same rotor for two positions!");
            } else {
                int[] rotorPositions = {r1.getSelectedIndex(), r2.getSelectedIndex(), r3.getSelectedIndex()};
                enigma.setRotors(rotorPositions);
                int[] wheelPositions = {w1.getSelectedIndex(), w2.getSelectedIndex(), w3.getSelectedIndex()};
                enigma.setRotorPositions(wheelPositions);
                rotateGraphics();
                errorLabel.setText("");
            }
        }
        else if (e.getActionCommand().equalsIgnoreCase("Enigma 1")) {
            enigmaLabel.setText("Version: Enigma 1");
            r1.setModel(new DefaultComboBoxModel(e1rotors));
            r2.setModel(new DefaultComboBoxModel(e1rotors));
            r3.setModel(new DefaultComboBoxModel(e1rotors));
            resetRotors();
            errorLabel.setText("");
        }
        else if (e.getActionCommand().equalsIgnoreCase("M3")) {
            enigmaLabel.setText("Version: M3");
            r1.setModel(new DefaultComboBoxModel(m3rotors));
            r2.setModel(new DefaultComboBoxModel(m3rotors));
            r3.setModel(new DefaultComboBoxModel(m3rotors));
            resetRotors();
            errorLabel.setText("");
        }
        else if (e.getActionCommand().equalsIgnoreCase("Exit")) {
            System.exit(0);
        }
        else if (e.getActionCommand().equalsIgnoreCase("Clear")) {
            clear();
        } else if (e.getActionCommand().equalsIgnoreCase("Apply")) {
            apply();

        }
        //All button action for the keyboard
        else if (e.getActionCommand().equalsIgnoreCase("A")) {
            keyboardPressed("A");
        } else if (e.getActionCommand().equalsIgnoreCase("B")) {
            keyboardPressed("B");
        } else if (e.getActionCommand().equalsIgnoreCase("C")) {
            keyboardPressed("C");
        } else if (e.getActionCommand().equalsIgnoreCase("D")) {
            keyboardPressed("D");
        } else if (e.getActionCommand().equalsIgnoreCase("E")) {
            keyboardPressed("E");
        } else if (e.getActionCommand().equalsIgnoreCase("F")) {
            keyboardPressed("F");
        } else if (e.getActionCommand().equalsIgnoreCase("G")) {
            keyboardPressed("G");
        } else if (e.getActionCommand().equalsIgnoreCase("H")) {
            keyboardPressed("H");
        } else if (e.getActionCommand().equalsIgnoreCase("I")) {
            keyboardPressed("I");
        } else if (e.getActionCommand().equalsIgnoreCase("J")) {
            keyboardPressed("J");
        } else if (e.getActionCommand().equalsIgnoreCase("K")) {
            keyboardPressed("K");
        } else if (e.getActionCommand().equalsIgnoreCase("L")) {
            keyboardPressed("L");
        } else if (e.getActionCommand().equalsIgnoreCase("M")) {
            keyboardPressed("M");
        } else if (e.getActionCommand().equalsIgnoreCase("N")) {
            keyboardPressed("N");
        } else if (e.getActionCommand().equalsIgnoreCase("O")) {
            keyboardPressed("O");
        } else if (e.getActionCommand().equalsIgnoreCase("P")) {
            keyboardPressed("P");
        } else if (e.getActionCommand().equalsIgnoreCase("Q")) {
            keyboardPressed("Q");
        } else if (e.getActionCommand().equalsIgnoreCase("R")) {
            keyboardPressed("R");
        } else if (e.getActionCommand().equalsIgnoreCase("S")) {
            keyboardPressed("S");
        } else if (e.getActionCommand().equalsIgnoreCase("T")) {
            keyboardPressed("T");
        } else if (e.getActionCommand().equalsIgnoreCase("U")) {
            keyboardPressed("U");
        } else if (e.getActionCommand().equalsIgnoreCase("V")) {
            keyboardPressed("V");
        } else if (e.getActionCommand().equalsIgnoreCase("W")) {
            keyboardPressed("W");
        } else if (e.getActionCommand().equalsIgnoreCase("X")) {
            keyboardPressed("X");
        } else if (e.getActionCommand().equalsIgnoreCase("Y")) {
            keyboardPressed("Y");
        } else if (e.getActionCommand().equalsIgnoreCase("Z")) {
            keyboardPressed("Z");
        }
    }

    //Lighting the labels that are involved in the encryption
    private void lightLabels(final char[] encryption) {
        int p1 = enigma.rotors[0].getRotorPosition();
        int p2 = enigma.rotors[1].getRotorPosition();
        int p3 = enigma.rotors[2].getRotorPosition();
        rg1.lightForwardLabel(Position.valueOf(Character.toString(encryption[2])).ordinal()+p1);
        rg2.lightForwardLabel(Position.valueOf(Character.toString(encryption[3])).ordinal()+p2);
        rg3.lightForwardLabel(Position.valueOf(Character.toString(encryption[4])).ordinal()+p3);
        rg4.lightForwardLabel(Position.valueOf(Character.toString(encryption[5])).ordinal());
        rg3.lightBackwardLabel(Position.valueOf(Character.toString(encryption[6])).ordinal()+p3);
        rg2.lightBackwardLabel(Position.valueOf(Character.toString(encryption[7])).ordinal()+p2);
        rg1.lightBackwardLabel(Position.valueOf(Character.toString(encryption[8])).ordinal()+p1);
    }

    //Checking how long the input is to add a space as per the enigma machine
    private void checkCount() {
        if (letterCount%5 == 0) {
            inputText.append(" ");
            outputText.append(" ");
        }
        if (letterCount%30 == 0) {
            inputText.append("\n");
            outputText.append("\n");
        }
    }

    //Resetting labels to original colour before colouring again
    private void resetLabels() {
        rg1.resetLabels();
        rg2.resetLabels();
        rg3.resetLabels();
        rg4.resetLabels();
    }

    //Setting the combo boxes to their new values after rotation
    private void setComboBox() {
        int[] rotorPositions = enigma.getRotorPositions();
        w1.setSelectedIndex(rotorPositions[0]);
        w2.setSelectedIndex(rotorPositions[1]);
        w3.setSelectedIndex(rotorPositions[2]);
    }

    //Resetting the rotors to I, II, III
    private void resetRotors() {
        r1.setSelectedIndex(0);
        r2.setSelectedIndex(1);
        r3.setSelectedIndex(2);
    }
}