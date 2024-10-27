import java.util.Scanner;

public class QuizApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Quiz Game!");
        String difficulty;
        int numberOfQuestions;

        // Difficulty Selection with Validation
        while (true) {
            System.out.print("Choose a difficulty level (easy, medium, hard): ");
            difficulty = scanner.nextLine().toLowerCase();

            switch (difficulty) {
                case "easy" -> {
                    numberOfQuestions = 3;
                    break;
                }
                case "medium" -> {
                    numberOfQuestions = 5;
                    break;
                }
                case "hard" -> {
                    numberOfQuestions = 7;
                    break;
                }
                default -> {
                    System.out.println("Invalid choice. Please enter 'easy', 'medium', or 'hard'.");
                    continue;
                }
            }
            break;
        }

        System.out.print("Enter the time limit per question (seconds): ");
        int timeLimitPerQuestion = scanner.nextInt();

        QuestionBank questionBank = new QuestionBank();
        Quiz quiz = new Quiz(questionBank, timeLimitPerQuestion);

        quiz.startQuiz(difficulty, numberOfQuestions);

        System.out.println("\nQuiz Over!");
        System.out.println("Your Final Score: " + quiz.getScore());
        scanner.close();
    }
}