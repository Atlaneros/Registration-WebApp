package Helper;

import Models.User;
//import jdk.internal.org.xml.sax.SAXException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Conversion {

    public static List<User> XmlToUserList(Document document) throws ParserConfigurationException, IOException {
        List<User> userList = new ArrayList<User>();

       // NodeList continents = document.getElementsByTagName("continent");

        NodeList users = document.getElementsByTagName("user");
        int numberOfUsers= users.getLength();
        for(int i=0; i<numberOfUsers ; i++){
            Element userElement=(Element) users.item(i);



            User user = new User();

            user.setFirstName(userElement.getElementsByTagName("first_name").item(0).getTextContent());
            user.setLastName(userElement.getElementsByTagName("last_name").item(0).getTextContent());
            user.setAddress(userElement.getElementsByTagName("address").item(0).getTextContent());
            user.setCity(userElement.getElementsByTagName("city").item(0).getTextContent());
            user.setEmail(userElement.getElementsByTagName("email").item(0).getTextContent());
            user.setPassword(userElement.getElementsByTagName("password").item(0).getTextContent());
            user.setCountry(userElement.getParentNode().getAttributes().getNamedItem("name").getNodeValue());

            userList.add(user);


        }


        return userList;
    }

}
