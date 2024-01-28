import java.io.Serializable;

public class QuestionOne implements Serializable {
    private String[][] array2D;
    private String[] array1D;

    public QuestionOne(String[][] array2D, String[] array1D) {
        this.array2D = array2D;
        this.array1D = array1D;
    }

    // Getter methods for accessing the arrays
    public String[][] getArray2D() {
        return array2D;
    }

    public String[] getArray1D() {
        return array1D;
    }
}
