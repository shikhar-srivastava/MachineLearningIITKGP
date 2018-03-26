import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;
import java.nio.file.Files;
import java.util.*;
import java.util.stream.Collectors;


//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ Servlet to save Uploaded Images to ##FILE SYSTEM## and return a String of '+' seperated Image file names ~~~~~~~~~~~~~~~~~~~~~~~~~~~~

@WebServlet("/getImages")
@MultipartConfig

public class getImagesServlet extends HttpServlet {


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    	response.setContentType("text/html");
        List<Part> fileParts = request.getParts().stream().filter(part -> "file".equals(part.getName())).collect(Collectors.toList());
        System.out.println(fileParts);
        File uploads = new File(getServletContext().getInitParameter("upload.location"));  // ~~~~~~~~~~~upload.location is added to web.xml for reference~~~~~~~~~~
        String fileName;
        String type;
        System.out.println("Right before filePart loop begins..");
        String newURL="";
        for (Part filePart : fileParts) {
            fileName = filePart.getSubmittedFileName();
            if(fileName.indexOf('.')!=-1)
            {
                type=fileName.substring(fileName.indexOf('.'));
                //System.out.println(type);
            }
            else type=".png";
            String newFileName= UUID.randomUUID().toString(); //~~~~~~~Generating Random string to (almost always as its psuedo-random) avoid invariable File Overwriting~~~~~~
            newURL+=newFileName+type+"||";
              System.out.println("File name decided: "+newFileName+type);
            InputStream fileContent = filePart.getInputStream();
            File file = new File(uploads, newFileName+type);
            try (InputStream input = filePart.getInputStream()) {
                Files.copy(input, file.toPath());
            }
        	//System.out.println("After file writing..");
        }

        newURL=newURL.substring(0,newURL.length()-2);
        response.sendRedirect(request.getContextPath()+"/input.html?"+newURL);
       
       	//~~~~~~~~~~~~Redirects to input.html page with appended Image String~~~~~~~~~~~~~~~~~~~~
	}

}




