import java.util.ArrayList;
import java.util.Random;

class FileContentGenerator {

    int nLines;
    Random newNumber = new Random();

    FileContentGenerator(int nLines) {
        this.nLines = nLines;
    }

    ArrayList<Integer> generateRandomContent() {

        ArrayList<Integer> randomContent = new ArrayList<>(nLines);

        for(int i = 0; i < nLines; i++) {
            randomContent.add(newNumber.nextInt());
        }

        return randomContent;
    }

}
