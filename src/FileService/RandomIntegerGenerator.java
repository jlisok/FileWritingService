package FileService;

import java.util.ArrayList;
import java.util.Random;

class RandomIntegerGenerator implements ContentGenerator<Integer> {

    private Random newNumber;

    RandomIntegerGenerator(Random newNumber){
        this.newNumber = newNumber;
    }


    @Override
    public ArrayList<Integer> generateContent(int nLines) {

        ArrayList<Integer> randomContent = new ArrayList<>(nLines);

        for (int i = 0; i < nLines; i++) {
            randomContent.add(newNumber.nextInt());
        }

        return randomContent;
    }

}
