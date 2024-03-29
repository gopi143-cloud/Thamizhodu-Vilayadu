import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
public class QuestionSerialization implements  Serializable{

    public static void main(String[] args) {
        // Create instances of the Question class
        String[][] array2D1 = {
	    {"அ","","","","","","","","",""},
	    {"ம்","","","","","","","","",""},
	    {"மா","ம்","ப","ழ","ம்","","ளி","","",""},
	    {"","","ம்","","ன","","ள்","","",""},
	    {"னி","ழ","ப","","லி","ல்","ப","","",""},
	    {"","","ர","க","ல்","லூ","ரி","","",""},
	    {"","","ம்","","வ","","","","",""},
	 {"","","","","","","","","",""},
	 {"","","","","","","","","",""},
	 {"","","","","","","","","",""}
	};

        String[] array1D1 = {
            "1.முன்று எழுத்து கவிதை?",
            "2.மிகவும் தொன்மையான விளையாட்டு?",
            "3.'க்,ச்,ட்,த்,ப்,ற்' எனப்படுவது?",
            "4.கல்வி நிறுவனம்?",
            "5.சேலத்தில் மிகவும் பிரபலமானது?",
            "6.பள்ளி முடிந்ததும் மாணவர்கள் மேற்கட்ட படிப்புகளுக்கு செல்லும் இடம்?",
            "7.ஆறுபடையில் ஒன்று?",
            "8.'கௌரி'யின் மற்றொரு பெயர்?"
    };
        QuestionOne question1 = new QuestionOne(array2D1, array1D1);

        String[][] array2D2 = {
            {"இ", "றா", "ள்", "", "", "", "", "", "", ""},
            {"ந்", "", "ல", "", "", "", "", "", "", ""},
            {"தி", "ரு", "க்", "கு", "ற", "ள்", "", "", "", ""},
            {"யா", "", "னோ", "", "லை", "", "", "", "", ""},
            {"", "", "", "ரை", "ம", "தா", "", "", "", ""},
            {"", "னி", "", "", "கு", "", "", "", "", ""},
            {"", "க்", "ளே", "பி", "ட", "", "", "", "", ""},
            {"", "அ", "", "", "கு", "", "", "", "", ""},
            {"", "", "", "", "", "", "", "", "", ""},
            {"", "", "", "", "", "", "", "", "", ""}
    };
        String[] array1D2 = {
            "1.உலகின் மிகப்பெரிய ஜனநாயக நாடு எது?",
            "2.இந்தியாவில் இரும்புப்பாலம் முதன்முதலில் எங்கு அமமக்கப்பட்டது?",
            "3.இந்தியாவின் கண்டம் விட்டு கண்டம் பாயும் ஏவுகனையின் பஞயர் என்ன?",
            "4.கங்கை ஆற்றின் பிறப்பிடம்?",
            "5.தலையில் இதயத்தைக் கொண்டுள்ள உயிரினம் எது?",
            "6.சந்திர கிரகணம் பற்றிக் கூறும் பதினெண்கீழ்க்கணக்கு நூல் எது?",
            "7.எலியின் மூலம் பரவும் நோய்களில் மிகவும் முக்கியமானது எது?",
            "8.இந்தியாவின் தேசிய மலர் எது?"
    };
        QuestionOne question2 = new QuestionOne(array2D2, array1D2);

	String[][] array2D3 = {
            {"", "கை", "", "", "", "", "", "", "", ""},
            {"பெ", "ங்", "க", "ளூ", "ர்", "", "", "", "", ""},
            {"", "க", "மு", "", "", "", "", "", "", ""},
            {"", "கா", "ல்", "டு", "வெ", "ல்", "ச", "ஞ்", "எ", ""},
            {"", "", "லை", "", "", "", "தா", "", "", ""},
            {"னை", "மோ", "நி", "", "", "", "வ", "", "", ""},
            {"ன்", "", "ல", "", "", "", "தா", "", "", ""},
            {"செ", "", "ம்", "", "", "", "னி", "", "", ""},
            {"", "", "", "", "", "", "", "", "", ""},
            {"", "", "", "", "", "", "", "", "", ""}
    };
        String[] array1D3 = {
            "1.ஏறு தழுவுதல் எந்த நிலத்தில் நடைபெறும் வீர விளையாட்டு?",
            "2.ஒரே நேரத்தில் நூறு செ யல்களை நினைவில் வைத்து சொல்பவர்?",
            "3.உலக தமிழ் ஆராய்ச்சி நிறுவனம் அமைந்துள்ள இடம்?",
            "4.இந்தியாவின் மிகப்பெரிய நதி எது?",
            "5.திராவிட மொழிகளின் தாய் தமிழ் என,உலகிற்குப் பறைசாற்றியவர்?",
            "6.இந்தியாவின் வின்வெளி ஆராய்ச்சி நிறுவனத்தின் தலைமையகம் அமைந்துள்ள இடம்?",
            "7.முதல் எழுத்து ஒன்றி வருவது?",
            "8.உலகின் மிக நீளமான நீர்வீழ்ச்சி எது?"
    };
        QuestionOne question3 = new QuestionOne(array2D3, array1D3);

        // Serialize the list of questions
        List<QuestionOne> questionList = new ArrayList<>();
        questionList.add(question1);
        questionList.add(question2);
	questionList.add(question3);

        serializeQuestions(questionList, "questions.dat");
    }

    private static void serializeQuestions(List<QuestionOne> questions, String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(questions);
            System.out.println("Questions serialized successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
