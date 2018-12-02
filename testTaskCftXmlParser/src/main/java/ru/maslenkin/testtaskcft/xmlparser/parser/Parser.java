package ru.maslenkin.testtaskcft.xmlparser.parser;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Parser {
    private static List<Map<String, String>> tagFields;

    private static void getTagValue(Document document, String xpath) {
        List<Node> nodeList = document.selectNodes(xpath);
        for (Node node : nodeList) {
            List<Node> nestedNodeList = node.selectNodes("*");
            Map<String, String> mapNodes = new HashMap<String, String>();
            for (Node innNode : nestedNodeList) {
                mapNodes.put(innNode.getName(), innNode.getStringValue());
            }
            tagFields.add(mapNodes);
        }
    }

    public static List<Map<String, String>> getParse(File inputFile) throws DocumentException {
        SAXReader saxReader = new SAXReader();
        tagFields = new ArrayList<Map<String, String>>();
        System.out.println("File: " + inputFile.getName());
        Document document = saxReader.read(inputFile);
        getTagValue(document, "/Form/Groups/Group/Fields/Field");
        getTagValue(document, "/Form/Groups/Group/Groups/Group/Fields/Field");
        return tagFields;
    }
}
