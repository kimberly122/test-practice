<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Language Translation</title>
		
    </head>
    <body>
        <div align="center">
            <form action ="LanguageTranslationServlet" method="post">
                <input type="text" name="inputText">
                <input type="SUBMIT" value="Submit" />
            </form>
			
			<%
				if (request.getAttribute("outputText") != null){
					out.println("<h1>"+request.getAttribute("outputText")+"</h1>");
				}
				
			%>
			<%
			if(request.getAttribute("charCount") != null){
					out.println("<h1>"+request.getAttribute("charCount")+"</h1>");
				}
				%>
			<%	
				if(request.getAttribute("trans") != null){
					out.println("<h1>"+request.getAttribute("trans")+"</h1>");
				}
				%>
				<%
				if(request.getAttribute("wordCount") != null){
					out.println("<h1>"+request.getAttribute("wordCount")+"</h1>");
				}
				%>
        </div>
    </body>
</html>
