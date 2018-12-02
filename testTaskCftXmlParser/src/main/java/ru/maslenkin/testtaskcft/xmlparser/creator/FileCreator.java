package ru.maslenkin.testtaskcft.xmlparser.creator;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

public class FileCreator {
    private String purposeFolder;

    public FileCreator(String purposeFolder) {
        this.purposeFolder = purposeFolder;
    }

    public Document writeFile(String fileName, List<Map<String, String>> tagFields) {
        Document document = createDocument(tagFields);
        try {
            String newFileName = purposeFolder + "/" + "dst_" + fileName;
            FileWriter fileWriter = new FileWriter(newFileName);
            document.write(fileWriter);
            fileWriter.close();
            System.out.println("New file: " + newFileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return document;
    }

    private Document createDocument(List<Map<String, String>> tagFields) {
        Document document = DocumentHelper.createDocument();
        Element rootElement = document.addElement("Data").addText("\r\n");
        for (Map<String, String> tagField : tagFields) {
            rootElement.addText("\t");
            Element newElement = rootElement.addElement(tagField.get("type"));
            for (Map.Entry<String, String> entry : tagField.entrySet()) {
                if (!entry.getKey().equals("type")) {
                    String value = entry.getValue();
                    if (entry.getKey().equals("digitOnly") || entry.getKey().equals("required") || entry.getKey().equals("readOnly")) {
                        switch (Integer.parseInt(entry.getValue())) {
                            case 1:
                                value = "true";
                                break;
                            case 0:
                                value = "false";
                                break;
                        }
                    }
                    if (newElement.getName().equals("Sum") && entry.getKey().equals("value")) {
                        value = new DecimalFormat("#0.00").format(Double.parseDouble(value)).replace(',', '.');
                    }
                    if (newElement.getName().equals("Address") && entry.getKey().equals("value")) {
                        String[] address = value.split(",");
                        newElement.addAttribute("street", address[0]);
                        newElement.addAttribute("house", address[1]);
                        newElement.addAttribute("flat", address[2]);
                    } else {
                        newElement.addAttribute(entry.getKey(), value);
                    }
                }
            }
            rootElement.addText("\r\n");
        }
        return document;
    }
}
