import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class QuestionBank {
    private List<Question> easyQuestions = new ArrayList<>();
    private List<Question> mediumQuestions = new ArrayList<>();
    private List<Question> hardQuestions = new ArrayList<>();

    public QuestionBank() {
        addSampleQuestions();
    }

    private void addSampleQuestions() {
        easyQuestions.add(new Question("What is 2 + 2?", List.of("3", "4", "5", "6"), 1, 1));
        easyQuestions.add(new Question("What color are bananas?", List.of("Red", "Yellow", "Blue", "Green"), 1, 1));

        mediumQuestions.add(new Question("What is the capital of France?", List.of("Berlin", "Madrid", "Paris", "Rome"), 2, 2));
        mediumQuestions.add(new Question("Which planet is known as the Red Planet?", List.of("Earth", "Mars", "Jupiter", "Venus"), 1, 2));

        hardQuestions.add(new Question("What is the square root of 144?", List.of("10", "11", "12", "13"), 2, 3));
        hardQuestions.add(new Question("Who wrote 'Hamlet'?", List.of("Shakespeare", "Homer", "Dante", "Chaucer"), 0, 3));
    }

    public List<Question> getQuestionsByDifficulty(String difficulty, int number) {
        List<Question> selectedQuestions = switch (difficulty.toLowerCase()) {
            case "easy" -> new ArrayList<>(easyQuestions);
            case "medium" -> new ArrayList<>(mediumQuestions);
            case "hard" -> new ArrayList<>(hardQuestions);
            default -> new ArrayList<>();
        };

        Collections.shuffle(selectedQuestions);
        return selectedQuestions.subList(0, Math.min(number, selectedQuestions.size()));
    }
}

