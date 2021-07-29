package Registration;

import Helper.Conversion;
import Models.User;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "Delete", value = "/Delete")
public class Delete extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String Email=request.getParameter("Email");



        try{
            DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder domBuilder = null;
            domBuilder = domFactory.newDocumentBuilder();
            String rootPath = getServletContext().getRealPath("");
            String xmlFilePath = rootPath.replace("target\\Registration-1.0-SNAPSHOT\\","") + "src\\main\\resources\\data.xml";
            Document document = domBuilder.parse(xmlFilePath);
            //
            NodeList usersEmailElements=document.getElementsByTagName("email");
            int emailListLength=usersEmailElements.getLength();
            for(int i=0; i<emailListLength; i++){
                if(usersEmailElements.item(i).getTextContent().equals(Email)){
                    Node userElement = usersEmailElements.item(i).getParentNode();
                    Element userCountryNodeElement = (Element) userElement.getParentNode();
                    int userCountryElementsLength=userCountryNodeElement.getElementsByTagName("user").getLength();
                    if(userCountryElementsLength==1)
                    {
                        Element userContinentNodeElement= (Element) userCountryNodeElement.getParentNode();
                        int userContinentElementsLength=userContinentNodeElement.getElementsByTagName("country").getLength();
                        if(userContinentElementsLength==1){
                            Element DataTemp= (Element) userContinentNodeElement.getParentNode();
                            DataTemp.removeChild(userContinentNodeElement);
                            break;
                        }
                        else{
                            Element ContinentTemp= (Element) userCountryNodeElement.getParentNode();
                            ContinentTemp.removeChild(userCountryNodeElement);
                            break;
                        }
                    }
                    else{
                        Element CountryTemp= (Element) userElement.getParentNode();
                        CountryTemp.removeChild(userElement);
                        break;
                    }
                }
            }
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(new File(xmlFilePath));
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(source, result);

            List<User> userList = Conversion.XmlToUserList(document);
            request.setAttribute("userList", userList);
            request.getRequestDispatcher("index.jsp").forward(request, response); //saljemo na index jsp kontrolu koja se dalje prosledjuje browseru


        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }

    }
}
