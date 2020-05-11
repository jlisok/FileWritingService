import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.util.ArrayList;


class FileWriter {

    final private ArrayList<Integer> content;
    final private Path filePath;

    FileWriter(ArrayList<Integer> content, Path filePath) {
        this.content = content;
        this.filePath = filePath;
    }



    void write2File() throws IOException {

        assertDirsExist(filePath);


        try (BufferedWriter writer = Files.newBufferedWriter(filePath)) {

            for (Integer items : content) {

                writer.write(items.toString() + "\n");

            }

        } catch (IOException e) {
            System.out.print("Could not write to file");
            e.printStackTrace();
        }

    }


    void assertDirsExist(Path filePath) {
        try {
            Files.createDirectories(filePath.getParent());
        } catch (IOException e) {
            System.out.print("Could not create directories");
            e.printStackTrace();
        }
    }

}
