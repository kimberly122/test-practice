package Servlet;

import Bean.CloudantClientClass;
import Bean.TexttoSpeechConnector;
import Bean.DashDBConnector;
import Bean.LanguageTranslatorConnector;
import Bean.ObjectStorageConnector;
import com.ibm.watson.developer_cloud.language_translation.v2.LanguageTranslation;
import com.ibm.watson.developer_cloud.language_translation.v2.model.TranslationResult;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.ibm.watson.developer_cloud.text_to_speech.v1.TextToSpeech;
import java.io.InputStream;

import org.openstack4j.model.common.Payload;
import org.openstack4j.model.common.Payloads;

import org.json.simple.parser.JSONParser;
import org.json.simple.JSONObject;

@WebServlet(name = "TexttoSpeechServlet", urlPatterns = {"/TexttoSpeechServlet"})
  
public class TexttoSpeechServlet extends HttpServlet {
	
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
			
			TexttoSpeechConnector connector = new TexttoSpeechConnector();      
  			TextToSpeech service = new TextToSpeech();
  			service.setUsernameAndPassword(connector.getUsername(),connector.getPassword());
        
        		String text = request.getParameter("inputText");
        		String format = "audio/wav";

  				InputStream speech = service.synthesize(text, format);
				
				ObjectStorageConnector osConnector = new ObjectStorageConnector();
				osConnector.createContainer("containerName");
				 Payload upfile = null;
				 upfile = Payloads.create(speech);
				 osConnector.uploadFile("containerName", "texttospeech.wav", upfile);
				
			
			
	
			try{
				
				
				/*CloudantClientClass db = new CloudantClientClass();
				
				int addStat;
				addStat = db.addEntry(translatedText);
				
				DashDBConnector dashdb = new DashDBConnector();
				
				
				JSONParser jsonParser = new JSONParser();
				JSONObject jsonObject = (JSONObject) jsonParser.parse(translatedText);
				String product = (String) jsonObject.get("translation");
				
								
				dashdb.addWords(product);
				*/
				
				
			} catch (Exception e) {
				e.printStackTrace(System.err);
			}
			
			/*
			String test = connector.getUsername();
			request.setAttribute("outputText",test);
			*/
			
			response.setContentType("text/html");
			response.setStatus(200);
			request.getRequestDispatcher("langtransindex.jsp").forward(request,response);
        //processRequest(request, response);
    }
/*    
    public String test(){
    	LanguageTranslatorConnector connector = new LanguageTranslatorConnector();
    	LanguageTranslation languageTranslation = new LanguageTranslation();
    	languageTranslation.setUsernameAndPassword(connector.getUsername(),connector.getPassword());
    	TranslationResult translated = languageTranslation.translate("hello", "en", "es");
    	String translatedText = translated.toString();
    	
    	return translatedText;
    }
	*/
	
	

}
