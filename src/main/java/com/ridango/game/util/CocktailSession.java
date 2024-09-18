package com.ridango.game.util;

import com.ridango.game.model.api.Cocktail;

import java.util.Random;

public class CocktailSession {
    Cocktail cocktail;
    String[] drinkLetters;
    boolean[] letterHints;
    static final String allowedSymbols = ".-_/'’\"() ";

    public CocktailSession(Cocktail cocktail){
        this.cocktail = cocktail;
        drinkLetters = cocktail.name.split("");
        letterHints = markAllowedSymbols(drinkLetters);
    }

    public String toString(){
        String[] cocktailDisplay = drinkLetters.clone();


        for (int i = 0; i < drinkLetters.length; i++) {
            if(!letterHints[i]){
                cocktailDisplay[i] = "_";
            }
        }

        return String.join("", cocktailDisplay);
    }

    public static boolean[] markAllowedSymbols(String[] drinkLetters){
        boolean[] letterHinted = new boolean[drinkLetters.length];
        for (int i = 0; i < letterHinted.length; i++) {
            if(allowedSymbols.contains(drinkLetters[i])){
                letterHinted[i] = true;
            }
        }
        return letterHinted;
    }

    public void addHint(){
        if(drinkLetters.length == 0){
            return;
        }

        int hintableLetters = 0;
        int hints;

        for (boolean hinted : letterHints) {
            if(!hinted){
                hintableLetters++;
            }
        }

        if(hintableLetters <= 1){
            hints = 0;
        } else if (hintableLetters < 4){
            hints = 1;
        } else{
            hints = hintableLetters/4 + 1;
        }

        Random random = new Random();
        int randomIndex;

        for (int i = 0; i < hints; i++) {
            randomIndex = random.ints(0, letterHints.length).findFirst().getAsInt();
            if(!letterHints[randomIndex]){
                letterHints[randomIndex] = true;
            } else{
                i--;
            }
        }
    }

    public int countHintedLetters(){
        int hints = 0;
        for (boolean hint : letterHints) {
            if(hint){
                hints++;
            }
        }
        return hints;
    }
}
