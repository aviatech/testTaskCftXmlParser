import org.dom4j.Document;
import org.dom4j.DocumentException;
import ru.maslenkin.testtaskcft.xmlparser.creator.FileCreator;
import ru.maslenkin.testtaskcft.xmlparser.scanner.FileScanner;
import ru.maslenkin.testtaskcft.xmlparser.parser.Parser;
import ru.maslenkin.testtaskcft.xmlparser.sending.Sending;

import java.io.File;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws DocumentException {
        String findFolder = "src/main/resources";
        String purposeFolder = "src/main/resources";
        FileScanner fileScanner = new FileScanner(findFolder);
        FileCreator fileCreator = new FileCreator(purposeFolder);
        File parseFile = fileScanner.getNext();
        List<Map<String, String>> fields = Parser.getParse(parseFile);
        Document document = fileCreator.writeFile(parseFile.getName(), fields);
        Sending.send(document, "http://demo5418344.mockable.io");
    }
}
