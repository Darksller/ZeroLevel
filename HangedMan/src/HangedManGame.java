import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public final class HangedManGame {
    private static final List<String> WORDS = Arrays.asList("SOME", "RANDOM", "WORDS");
    private static final int MAX_LIVES = 4;

    private final String wordToGuess;
    private final char[] currentGuess;
    private int livesLeft;
    private final Scanner scanner;

    public HangedManGame() {
        this.wordToGuess = getRandomWord();
        this.currentGuess = new char[wordToGuess.length()];
        Arrays.fill(currentGuess, '_');
        this.livesLeft = MAX_LIVES;
        this.scanner = new Scanner(System.in);
    }

    private String getRandomWord() {
        Random random = new Random();
        return WORDS.get(random.nextInt(WORDS.size()));
    }

    public void start() {
        System.out.println("Welcome to Hangman!");
        System.out.println("You have " + MAX_LIVES + " attempts to guess the word.");

        while (livesLeft > 0 && !isWordGuessed()) {
            displayGameState();
            playTurn();
        }

        displayGameResult();
        scanner.close();
    }

    private void displayGameState() {
        System.out.println("\nWord: " + String.valueOf(currentGuess));
        System.out.println("Lives remaining: " + livesLeft);

    }



    private void playTurn() {
        System.out.print("Enter a letter: ");
        String input = scanner.nextLine().toUpperCase();

        if (input.length() != 1) {
            System.out.println("Please enter only one letter!");
            return;
        }

        char letter = input.charAt(0);
        boolean found = false;

        for (int i = 0; i < wordToGuess.length(); i++) {
            if (wordToGuess.charAt(i) == letter && currentGuess[i] == '_') {
                currentGuess[i] = letter;
                found = true;
            }
        }

        if (!found) {
            livesLeft--;
            System.out.println("Wrong! This letter is not in the word.");
        } else {
            System.out.println("Correct! The letter is in the word.");
        }
    }

    private boolean isWordGuessed() {
        return String.valueOf(currentGuess).equals(wordToGuess);
    }

    private void displayGameResult() {
        if (isWordGuessed()) {
            System.out.println("\nCongratulations! You won!");
            System.out.println("The word was: " + wordToGuess);
        } else {
            System.out.println("\nGame Over! You lost.");
            System.out.println("The word was: " + wordToGuess);
        }
    }
}
