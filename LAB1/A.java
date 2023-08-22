import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class A{

    public static void main(String[] args) {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();

            Document document = builder.parse(new File("employee.xml"));

            document.getDocumentElement().normalize();

            NodeList laptopList = document.getElementsByTagName("emp");

            String tag = "<html><body><table border=\"1\"><tr><th>Name</th><th>Roll</th><th>Phone</th><th>Dept</th></tr>";
            for (int i = 0; i < laptopList.getLength(); i++) {
                Node laptop = laptopList.item(i);
                if (laptop.getNodeType() == Node.ELEMENT_NODE) {

                    Element laptopElement = (Element) laptop;
                    tag += "<tr><td>" + laptopElement.getAttribute("name") + "</td>";

                    NodeList laptopDetails = laptop.getChildNodes();
                    for (int j = 0; j < laptopDetails.getLength(); j++) {
                        Node detail = laptopDetails.item(j);
                        if (detail.getNodeType() == Node.ELEMENT_NODE) {
                            Element detailElement = (Element) detail;
                            tag += "<td>" + detailElement.getAttribute("value") + "</td>";
                        }
                    }
                    tag += "</tr>";
                }
            }

            tag += "</table></body></html>";

            try {
                FileWriter myWriter = new FileWriter("index.html");
                myWriter.write(tag);
                myWriter.close();

            } catch (IOException e) {
                System.out.println("An error occurred.");
            }

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}