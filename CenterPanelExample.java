import javax.swing.*;
import java.awt.*;

public class CenterPanelExample {
static String[][] AnsCell1 = {
	    {"அ","","","","","","","","",""},
	    {"ம்","","","","","","","","",""},
	    {"மா","ம்","ப","ழ","ம்","","ளி","","",""},
	    {"","","ம்","","ன","","ள்","","",""},
	    {"னி","ழ","ப","","லி","ல்","ப","","",""},
	    {"","","ர","க","ல்","லூ","ரி","","",""},
	    {"","","ம்","","வ","","","","",""},
	    {"","","","","","","","","",""},
	    {"","","","","","","","","",""},
	    {"","","","x","","","","","",""}
	};
    public static void main(String[] args) {
        int dimension[] = DimensionMaker(AnsCell1);
	System.out.println(dimension[0]);
	System.out.println(dimension[1]);
    }
private static int[] DimensionMaker(String check[][]) {
        int w = -1, x = -1, y = -1, z = -1;

    // Find minimum and maximum non-empty column and row indices
    for (int i = 0; i < check.length; i++) {
        for (int j = 0; j < check[i].length; j++) {
            if (!check[i][j].isEmpty()) {
                if (w == -1) {
                    w = j; // Minimum non-empty column index
                    x = j;
                }
                if (j > x) {
                    x = j; // Maximum non-empty column index
                }
                if (y == -1) {
                    y = i; // Minimum non-empty row index
                    z = i;
                }
                if (i > z) {
                    z = i; // Maximum non-empty row index
                }
            }
        }}
// Calculate differences and create the result array
    int a = x - w + 1; // Difference between maximum and minimum non-empty column indices
    int b = z - y + 1; // Difference between maximum and minimum non-empty row indices

    int[] result = {a, b};
    return result;

}
}
