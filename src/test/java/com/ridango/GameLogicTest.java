package com.ridango;

import com.ridango.game.model.api.Cocktail;
import com.ridango.game.util.CocktailSession;
import com.ridango.game.util.Core;
import com.ridango.game.util.UiHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GameLogicTest {

    @Test
    public void TestSpecialSymbolsAreInstantlyRevealed(){
        // Check for spaces not being concealed.
        Cocktail cocktail = new Cocktail();
        cocktail.setName("Space key");
        CocktailSession session = new CocktailSession(cocktail);
        String unknownCocktail = session.toString();

        Assertions.assertEquals(unknownCocktail, "_____ ___");

        // Dashes, underscores and other special symbols also should not be concealed.
        cocktail.setName("The-rarest known spe'cial c(ok)tail in the worl’d");
        CocktailSession session2 = new CocktailSession(cocktail);
        unknownCocktail = session2.toString();

        Assertions.assertEquals(unknownCocktail, "___-______ _____ ___'____ _(__)____ __ ___ ____’_");
    }

    @Test
    public void hintsWillNotRevealAllLetters(){
        // After 4 attempts at least one letter should not be revealed.
        // Special symbols should be taken into account to not accidentally reveal all non-special symbols.

        Cocktail cocktail = new Cocktail();
        cocktail.setName("B-.- .-.");
        CocktailSession session2 = new CocktailSession(cocktail);

        session2.addHint();
        session2.addHint();
        session2.addHint();
        session2.addHint();

        String unknownCocktail = session2.toString();

        // B is the only letter, so it should not be revealed.
        Assertions.assertEquals(unknownCocktail, "_-.- .-.");
    }

    @Test
    public void theMoreLettersInCocktailTheMoreHintsPerAttempt(){
        Cocktail cocktail = new Cocktail();
        cocktail.setName("The longest cocktail known to man in modern history");
        CocktailSession session1 = new CocktailSession(cocktail);

        Cocktail cocktail2 = new Cocktail();
        cocktail2.setName("Shorty");
        CocktailSession session2 = new CocktailSession(cocktail2);

        int cocktail1BeforeAttempt = session1.countHintedLetters();
        int cocktail2BeforeAttempt = session2.countHintedLetters();

        session1.addHint();
        session2.addHint();

        int session1Difference = session1.countHintedLetters()-cocktail1BeforeAttempt;
        int session2Difference = session2.countHintedLetters()-cocktail2BeforeAttempt;

        Assertions.assertTrue( session1Difference > session2Difference);
    }
}
