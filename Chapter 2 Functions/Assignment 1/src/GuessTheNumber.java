import java.util.Random;
import java.util.Scanner;

public class GuessTheNumber {

    private final int numberToGuess;
    private int guessCount;
    private final Scanner scanner;

    public GuessTheNumber() {
        this.numberToGuess = generateRandomNumber();
        this.guessCount = 0;
        this.scanner = new Scanner(System.in);
    }

    private int generateRandomNumber() {
        return new Random().nextInt(101);
    }

    private int getUserGuess() {
        while (true) {
            System.out.print("Guess a number between 1 and 100: ");
            String userInput = scanner.nextLine();
            if (isValidGuess(userInput)) {
                return Integer.parseInt(userInput);
            } else {
                System.out.println("Your input is Invalid. Enter a number between 1 and 100.");
            }
        }
    }

    private boolean isValidGuess(String userInput) {
        int number = Integer.parseInt(userInput);
        return number >= 1 && number <= 100;
    }

    private void evaluateGuess(int userGuess) {
        if (userGuess < numberToGuess) {
            System.out.println("Too low! Try again.");
        } else if (userGuess > numberToGuess) {
            System.out.println("Too high! Try again.");
        } else {
            System.out.println("Your guess is correct. guess count = " + guessCount);
        }
    }

    public void GuessNumber() {
        boolean isCorrectGuess = false;
        while (!isCorrectGuess) {
            int userGuess = getUserGuess();
            guessCount++;
            if (userGuess == numberToGuess) {
                isCorrectGuess = true;
            }
            evaluateGuess(userGuess);
        }
        scanner.close();
    }

    public static void main(String[] args) {
        GuessTheNumber game = new GuessTheNumber();
        game.GuessNumber();
    }
}