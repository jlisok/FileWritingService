package writing.service.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileNotEmptyAndExistController {

    public static boolean assertFileExistsAndNotEmpty(Path filePath) throws IOException {

        if (Files.exists(filePath) & Files.size(filePath) > 0) {
            return true;
        } else {
            return false;
        }
    }

}
