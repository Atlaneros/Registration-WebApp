package Helper;

import Models.User;
import org.w3c.dom.*;


import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class XMLOperations {


    public XMLOperations() throws TransformerConfigurationException {
    }

    public static void saveUserToXML(User user, Document document,String xmlFilePath) throws TransformerException {
        Map<String,String> countryContinentMap = new HashMap<String, String>();
        boolean added=false;
        countryContinentMap.put("China","Asia");
        countryContinentMap.put("United States", "North America");
        countryContinentMap.put("United Kingdom", "Europe");
        countryContinentMap.put("Germany", "Europe");
        countryContinentMap.put("Japan", "Asia");
        countryContinentMap.put("Canada", "North America");
        countryContinentMap.put("France", "Europe");


        Element userElement=document.createElement("user");

        Element firstNameElement=document.createElement("first_name");
        firstNameElement.appendChild(document.createTextNode(user.getFirstName()));
        userElement.appendChild(firstNameElement);

        Element lastNameElement=document.createElement("last_name");
        lastNameElement.appendChild(document.createTextNode(user.getLastName()));
        userElement.appendChild(lastNameElement);

        Element addressElement=document.createElement("address");
        addressElement.appendChild(document.createTextNode(user.getAddress()));
        userElement.appendChild(addressElement);

        Element cityElement=document.createElement("city");
        cityElement.appendChild(document.createTextNode(user.getCity()));
        userElement.appendChild(cityElement);


        Element emailElement=document.createElement("email");
        emailElement.appendChild(document.createTextNode(user.getEmail()));
        userElement.appendChild(emailElement);

        Element passwordElement=document.createElement("password");
        passwordElement.appendChild(document.createTextNode(user.getPassword()));
        userElement.appendChild(passwordElement);


        Comment comment=document.createComment("Users");
        NodeList continents = document.getElementsByTagName("continent");
        int continentsLength = continents.getLength();
        String keycontinent = countryContinentMap.get(user.getCountry());
        for(int i=0; i<continentsLength ;i++){
            if (keycontinent.equals(continents.item(i).getAttributes().getNamedItem("name").getNodeValue())){

                Element continentElement=(Element) continents.item(i);
                NodeList countries=continentElement.getElementsByTagName("country");
                int countresLength = countries.getLength();
                for(int j=0; j<continentsLength; j++){
                    if(user.getCountry().equals(countries.item(j).getAttributes().getNamedItem("name").getNodeValue())){
                     //dodaj usera na listu
                     countries.item(j).appendChild(userElement);
                     added=true;
                     break;
                    }
                }
                if(added==false) {
                    //make country node and add user on it
                    Element userCountryElement=document.createElement("country");
                    userCountryElement.appendChild(comment);
                    userCountryElement.setAttribute("name",user.getCountry());
                    userCountryElement.appendChild(userElement);
                    continents.item(i).appendChild(userCountryElement);

                    added=true;
                    break;
                }
            }
        }
        if(added==false){
            //make continent node and country and add user

            NodeList root=document.getElementsByTagName("data");
            Element userContinentElement=document.createElement("continent");
            userContinentElement.setAttribute("name", keycontinent );

            Element userCountryElement=document.createElement("country");
            userCountryElement.appendChild(comment);
            userCountryElement.setAttribute("name",user.getCountry());

            root.item(0).appendChild(userContinentElement).appendChild(userCountryElement).appendChild(userElement);
            added=true;

        }
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(document);
        StreamResult result = new StreamResult(new File(xmlFilePath));
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.transform(source, result);
    }


}
