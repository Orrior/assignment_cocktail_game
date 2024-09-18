package com.ridango.game.util;

import com.ridango.game.model.api.Cocktail;
import com.ridango.game.model.db.Score;

import java.util.List;

public class UiHelper {

    public static String renderIntro(List<Score> scoreboard){
        //generate leaderboard
        String intro =
                """
                GUESS THE COCKTAIL!\s
                ------------------
                """
                +
                renderScoreBoard(scoreboard)
                +
                """
                ------------------\s
                Enter any key to start the game!
                """;
//TODO! Leaderboard
        return intro;
    }

    private static String renderScoreBoard(List<Score> scoreboard){
        StringBuilder leaderboard = new StringBuilder("Leaderboard:\n");

        for (Score score : scoreboard) {
            leaderboard.append(score.getName());
            leaderboard.append(":");
            leaderboard.append(score.getScore());
            leaderboard.append("\n");
        }

        return leaderboard.toString();
    }

    public static String renderHint(String[] letters, boolean[] hints){
        String[] cocktailDisplay = letters.clone();


        for (int i = 0; i < letters.length; i++) {
            if(!hints[i]){
                cocktailDisplay[i] = "_";
            }
        }

        return String.join("", cocktailDisplay);
    }

    public static void renderCocktailSession(int attempts, CocktailSession session){
        String attempt = String.format(
            "Attempts left: %s \nDrink name: %s \nCocktail instruction: \n%s \n",
                attempts, session.toString(), session.cocktail.instructions);


        if(attempts <= 4){
            attempt += "Cocktail contains alcohol?: " + session.cocktail.alcoholic + "\n";
        }

        if(attempts <= 3){
            attempt += "Cocktail Category: " + session.cocktail.category + "\n";
        }

        if(attempts <= 2){
            attempt += "Cocktail glass type: " + session.cocktail.glass + "\n";
        }
        attempt += "Guess Name:\n";
        System.out.println(attempt);
    }
}
