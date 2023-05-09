/**
Created on Tue Jan  3 14:25:24 2023

@author: nathanmitchell
**/
package com.enigma;

public class RotorImpl implements Rotor {

    private Position [] forward;
    private Position [] reverse;
    private final Position turnover;
    private Position ringPosition = Position.A;
    private Position rotorPosition = Position.A;
    private final int ENUM_LENGTH = Position.values().length;

    //initialising the rotor with the forward and reverse mappings
    public RotorImpl (char[] forwardMapping, Position turn) {
        turnover = turn;
        setMapping(forwardMapping);
    }

    public void setRotorPosition(Position pos) {
        rotorPosition = pos;
    }

    public void setRingPosition(Position pos) { ringPosition = pos; }
    
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
        int ringIndex = ringPosition.ordinal();
        int totalIndex = (rotorIndex + characterIndex-ringIndex);
        if (totalIndex<0) {
            totalIndex = totalIndex+ENUM_LENGTH;
        } else {
            totalIndex = totalIndex % ENUM_LENGTH;
        }
        int firstEncPos = forward[totalIndex].ordinal();
        int finalPos = firstEncPos-rotorIndex+ringIndex;
        if (finalPos<0) {
            return Position.values()[firstEncPos-rotorIndex+ringIndex+ENUM_LENGTH];
        } if (finalPos>25) {
            return Position.values()[finalPos-26];
        } else {
            return Position.values()[finalPos];
        }
    }

    public Position encryptReverse(Position letter) {
        int ringIndex = ringPosition.ordinal();
        int rotorIndex = rotorPosition.ordinal();
        int characterIndex = letter.ordinal() - ringIndex + rotorIndex;
        if (characterIndex<0) {
            characterIndex = characterIndex + ENUM_LENGTH;
        } else {
            characterIndex = characterIndex % ENUM_LENGTH;
        }
        int reverseIndex = reverse[characterIndex].ordinal();
        int finalPosition =  (reverseIndex - rotorIndex + ringIndex);
        if (finalPosition < 0) {
            finalPosition += ENUM_LENGTH;
        } if (finalPosition > 25) {
            finalPosition = finalPosition % ENUM_LENGTH;
        }
        return Position.values()[finalPosition];
    }

    public void setMapping (char[] forwardMapping) {
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
        setMapping(newMapping);
        return allPlugs;
    }

    private Position[] getForward() { return forward; }

    public int getRotorPosition() {
        return rotorPosition.ordinal();
    }
}