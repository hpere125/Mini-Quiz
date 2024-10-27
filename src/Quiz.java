import java.util.List;
import java.util.Scanner;
import java.util.concurrent.*;

class Quiz {
    private QuestionBank questionBank;
    private int score;
    private int timeLimitPerQuestion; // in seconds

    public Quiz(QuestionBank questionBank, int timeLimitPerQuestion) {
        this.questionBank = questionBank;
        this.score = 0;
        this.timeLimitPerQuestion = timeLimitPerQuestion;
    }

    public void startQuiz(String difficulty, int numberOfQuestions) {
        List<Question> questions = questionBank.getQuestionsByDifficulty(difficulty, numberOfQuestions);
        Scanner scanner = new Scanner(System.in);

        for (int i = 0; i < questions.size(); i++) {
            Question question = questions.get(i);
            System.out.println("\nQuestion " + (i + 1) + ": " + question.getQuestionText());

            List<String> options = question.getOptions();
            for (int j = 0; j < options.size(); j++) {
                System.out.println((j + 1) + ". " + options.get(j));
            }

            System.out.println("You have " + timeLimitPerQuestion + " seconds to answer.");
            boolean answered = askWithTimeout(scanner, question);

            if (!answered) {
                System.out.println("Time’s up! The correct answer was: " + options.get(question.getCorrectAnswerIndex()));
            }
        }
        scanner.close();
    }

    private boolean askWithTimeout(Scanner scanner, Question question) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<Boolean> future = executor.submit(() -> {
            int answerIndex;
            try {
                System.out.print("Your answer (1-4): ");
                answerIndex = scanner.nextInt() - 1;
                if (question.checkAnswer(answerIndex)) {
                    System.out.println("Correct! You earned " + question.getPoints() + " points.");
                    score += question.getPoints();
                } else {
                    System.out.println("Wrong answer. The correct answer was: " + question.getOptions().get(question.getCorrectAnswerIndex()));
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Moving to the next question.");
                scanner.next(); // clear invalid input
                return false;
            }
            return true;
        });

        try {
            return future.get(timeLimitPerQuestion, TimeUnit.SECONDS);
        } catch (TimeoutException e) {
            System.out.println("\nTime's up!");
            future.cancel(true);  // Cancel the task if it times out
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            executor.shutdownNow();
        }
    }

    public int getScore() { return score; }
}