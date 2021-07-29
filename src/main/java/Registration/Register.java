package Registration;

import Helper.Conversion;
import Helper.XMLOperations;
import Models.User;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.*;

@WebServlet(name = "Register", value = "/Register")
public class Register extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean emailExist = false;
        User user = new User();

        List<User> userList = new ArrayList<User>();
        user.setFirstName(request.getParameter("FirstName"));
        user.setLastName(request.getParameter("LastName"));
        user.setAddress(request.getParameter("Address"));
        user.setCity(request.getParameter("City"));
        user.setCountry(request.getParameter("Country"));
        user.setEmail(request.getParameter("Email"));
        user.setPassword(request.getParameter("Password"));


        try {
            DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder domBuilder = null;
            domBuilder = domFactory.newDocumentBuilder();
            String rootPath = getServletContext().getRealPath("");
            String xmlFilePath = rootPath.replace("target\\Registration-1.0-SNAPSHOT\\","") + "src\\main\\resources\\data.xml";
            Document document = domBuilder.parse(xmlFilePath);
            userList= Conversion.XmlToUserList(document);
            for (User userTemp : userList) {
                if (user.getEmail().equals(userTemp.getEmail())) {
                    emailExist = true;
                    break;
                }
            }

            if(emailExist==false){
                XMLOperations.saveUserToXML(user,document,xmlFilePath);
                userList.add(user);
            }
            request.setAttribute("userList", userList);
            request.getRequestDispatcher("index.jsp").forward(request, response);
        } catch (ParserConfigurationException | SAXException | TransformerException exception) {
            request.setAttribute("javax.servlet.error.exception",exception); //ovdje sam setovo a u exceptionsu cu getovati
            request.getRequestDispatcher("Exceptions").forward(request,response);

        }


    }
}