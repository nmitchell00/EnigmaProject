/**
Created on Tue Jan  3 14:25:24 2023

@author: nathanmitchell
**/
package com.enigma;

public class RotorImpl implements Rotor {

    private Position [] forward;
    private Position [] reverse;
    private final Position turnover;
    private Position rotorPosition = Position.A;
    private final int ENUM_LENGTH = Position.values().length;

    //initialising the rotor with the forward and reverse mappings
    public RotorImpl (char[] forwardMapping, Position turn) {
        turnover = turn;
        setPlugBoard(forwardMapping);
    }

    public void setRotorPosition(Position pos) {
        rotorPosition = pos;
    }
    
    public boolean rotate() {
        int index = rotorPosition.ordinal();
        int nextIndex = (index+1)%ENUM_LENGTH;
        rotorPosition = Position.values()[nextIndex];
        if (nextIndex == turnover.ordinal()) {
            return true;
        } else {
            return false;
        }
    }

    public Position encryptForward(Position letter) {
        int rotorIndex = rotorPosition.ordinal();
        int characterIndex = letter.ordinal();
        int totalIndex = (rotorIndex + characterIndex) % ENUM_LENGTH;
        int firstEncPos = forward[totalIndex].ordinal();
        if (firstEncPos-rotorIndex<0) {
            return Position.values()[(firstEncPos-rotorIndex)+26];
        } else {
            return Position.values()[(firstEncPos-rotorIndex)];
        }
    }

    public Position encryptReverse(Position letter) {
        int rotorIndex = rotorPosition.ordinal();
        int characterIndex = (letter.ordinal() + rotorIndex)%26;
        int reverseIndex = reverse[characterIndex].ordinal();
        int finalPosition =  (reverseIndex - rotorIndex);
        if (finalPosition < 0) {
            finalPosition += ENUM_LENGTH;
        }
        return Position.values()[finalPosition];
    }

    public void setPlugBoard(char[] forwardMapping) {
        forward = new Position[forwardMapping.length];
        reverse = new Position[forwardMapping.length];
        Position to, from;
        int i = 0;
        for (char character: forwardMapping) {
            from = Position.values()[i];
            to = Position.valueOf(Character.toString(character));
            forward[i] = to;
            reverse[to.ordinal()] = from;
            i++;
        }
    }

    //Changing the plugboard array given the new input changes
    public String changeArray(char[] plugSettings) {
        String allPlugs = "";
        char[] newMapping = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
        for (int i=0; i<plugSettings.length-1; i+=2) {
            char input1 = Character.toUpperCase(plugSettings[i]);
            if (!Character.isAlphabetic(input1)){
                break;
            }
            char input2 = Character.toUpperCase(plugSettings[i+1]);
            if (!Character.isAlphabetic(input2)) {
                break;
            }
            Position posInp1 = Position.valueOf(Character.toString(input1));
            Position posInp2 = Position.valueOf(Character.toString(input2));
            newMapping[posInp1.ordinal()] = input2;
            newMapping[posInp2.ordinal()] = input1;
            allPlugs += "[" + input1 + input2 + "] ";
        }
        setPlugBoard(newMapping);
        System.out.println(forward);
        System.out.println(reverse);
        return allPlugs;
    }

    private Position[] getForward() { return forward; }

    public int getRotorPosition() {
        return rotorPosition.ordinal();
    }
}