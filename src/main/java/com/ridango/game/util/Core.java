package com.ridango.game.util;

import com.ridango.game.model.api.Cocktail;
import com.ridango.game.model.db.Score;
import com.ridango.game.repository.ScoreRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
@Component
public class Core {
    @Autowired
    ScoreRepo scoreRepo;
    List<Integer> cocktailsId;

    static Scanner scanner = new Scanner(System.in);

    public Core(){
    }

    public void startIntro(){
        while (true){
            System.out.println(UiHelper.renderIntro(scoreRepo.findTop10ByOrderByScoreDesc()));
            scanner.nextLine();
            startGame();
        }
    }

    public void startGame(){
        int localScore;
        int totalScore = 0;
        cocktailsId = new ArrayList<>();

        while (true){
            Cocktail cocktail = getNewCocktail();
            localScore = guessCocktail(cocktail);
            if(localScore < 1){
                System.out.println("Game Over! The cocktail name was: " + cocktail.name + ".\n");
                break;
            } else {
                System.out.println("Correct! You get " + localScore + " points." +
                        " You have " + totalScore +" Points. Next Cocktail!");
                totalScore += localScore;
            }
        }

        System.out.println("Your final score is " + totalScore + ".\n" +
                "Enter your name, leave blank if you don't want to record result.\n");
        String nickname = scanner.nextLine();
        if(!nickname.isEmpty()){
            saveNickname(nickname, totalScore);
            System.out.println("Result is recorded!");
        }
    }

    public void saveNickname(String nickname, int score){
        if(!nickname.isEmpty()){
            Score scoreRecord = new Score(nickname, score);
            scoreRepo.save(scoreRecord);
        }
    }

    public Cocktail getNewCocktail(){
        Cocktail newCocktail = ApiHandler.randomCocktail();

        //Check if such cocktail has already been in this session.
        while (cocktailsId.contains(newCocktail.id)){
            newCocktail = ApiHandler.randomCocktail();
        }
        return newCocktail;
    }

    public int guessCocktail(Cocktail cocktail){
        int attempts = 5;

        CocktailSession session = new CocktailSession(cocktail);

        while( attempts > 0){
            UiHelper.renderCocktailSession(attempts,session);
            if(scanner.nextLine().equalsIgnoreCase(cocktail.name)){
                return attempts;
            } else{
                attempts--;
                session.addHint();
            }
        }
        return 0;
    }
}
