import java.io.Serializable;

public class Question implements Serializable {
    private static final long serialVersionUID = 1L;

    private String question;
    private String[] choices;
    private int correctAnswerIndex;

    public Question(String question, String[] choices, int correctAnswerIndex) {
        this.question = question;
        this.choices = choices;
        this.correctAnswerIndex = correctAnswerIndex;
    }

    public String getQuestion() {
        return question;
    }

    public String[] getChoices() {
        return choices;
    }

    public int getCorrectAnswerIndex() {
        return correctAnswerIndex;
    }
}
