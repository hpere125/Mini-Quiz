import java.util.List;

class Question {
    private String questionText;
    private List<String> options;
    private int correctAnswerIndex;
    private int points;

    public Question(String questionText, List<String> options, int correctAnswerIndex, int points) {
        this.questionText = questionText;
        this.options = options;
        this.correctAnswerIndex = correctAnswerIndex;
        this.points = points;
    }

    public String getQuestionText() { return questionText; }
    public List<String> getOptions() { return options; }
    public int getCorrectAnswerIndex() { return correctAnswerIndex; }
    public int getPoints() { return points; }

    public boolean checkAnswer(int answerIndex) {
        return answerIndex == correctAnswerIndex;
    }
}

