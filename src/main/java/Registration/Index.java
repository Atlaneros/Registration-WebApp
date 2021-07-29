package Registration;

//import com.sun.org.apache.xml.internal.serialize.XHTMLSerializer;

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
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "Index", value = "/Index")
public class Index extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Document document = null; //trazilo mi inicijalizaciju varijable
        List<User> userList = new ArrayList<User>();
        String rootPath = getServletContext().getRealPath(""); //
        String xmlFilePath = rootPath.replace("target\\Registration-1.0-SNAPSHOT\\","") + "src\\main\\resources\\data.xml";
        

    try {
        DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder domBuilder = domFactory.newDocumentBuilder();
        document = domBuilder.parse(xmlFilePath);
        userList = Conversion.XmlToUserList(document);

        response.setContentType("text/html");
        document.getDocumentElement().normalize();
        Element root = document.getDocumentElement(); //get root node

        request.setAttribute("userList", userList);
        request.getRequestDispatcher("index.jsp").forward(request, response);

    } catch(ParserConfigurationException | SAXException ex){
        request.setAttribute("javax.servlet.error.exception", ex);
        request.getRequestDispatcher("Exceptions").forward(request, response);
    }



    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
