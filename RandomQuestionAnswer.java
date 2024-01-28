import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.RoundRectangle2D;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.LineBorder;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.List;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.Vector;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.text.BreakIterator;
public class RandomQuestionAnswer {

    public static List<String[]> questionsAndAnswers = new ArrayList<>();
    public static final String FILE_NAME = "questions_answers.txt"; // Replace with the actual file pat

    public static void readQuestionsAndAnswersFromFile() {
    try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
        String line;
        while ((line = br.readLine()) != null) {
            String[] parts = line.split("#");
            if (parts.length == 2) {  // Check if there are at least two parts
                questionsAndAnswers.add(new String[]{parts[0].trim(), parts[1].trim()});
            } else {
                System.err.println("Invalid line: " + line);
            }
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
}

    public static String[] getRandomQuestionAndAnswer() {
        if (!questionsAndAnswers.isEmpty()) {
            Random random = new Random();
            return questionsAndAnswers.get(random.nextInt(questionsAndAnswers.size()));
        }
        return null;
    }
}