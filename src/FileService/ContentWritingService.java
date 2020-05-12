package FileService;

import java.io.IOException;
import java.io.Serializable;
import java.util.Collection;


class ContentWritingService<T extends Serializable> {

    private ContentWriter<T> contentWriter;
    private ContentGenerator<T> contentGenerator;


    public ContentWritingService(ContentWriter fw, ContentGenerator rig) {
        this.contentWriter = fw;
        this.contentGenerator = rig;
    }


    public void processFile(int nLines) throws IOException, CouldNotWrite2FileAlreadyExists {

        Collection<T> content = contentGenerator.generateContent(nLines);
        contentWriter.writeContent(content);
        System.out.print("File has been created successfully");

    }

}
