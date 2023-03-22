package com.favtuts.mavenwebapp.web;

// bdd and concat function
import com.mongodb.client.MongoClient;
import config.MongoConnectionManager;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

// standards
import java.util.List;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.io.IOException;
import java.io.PrintWriter;
import com.google.gson.Gson;

// server
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.RequestDispatcher;


public class SimpleServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		System.out.println("------hhh--------");
		String name = "eeeeeeeee";
    	String age = "15";
    
		// set the result as an attribute on the request
		List<String> texts = Arrays.asList("Hello world", "world");
		request.setAttribute("texts", texts);
		request.setAttribute("message", "hello !");
		request.setAttribute("name", name);
		request.setAttribute("age", age);


		request.getRequestDispatcher("index.jsp").forward(request, response);
		response.getWriter().println(" SimpleServlet Executed");
	}

	// // send json response
	// @Override
    // protected void doPost(HttpServletRequest request, HttpServletResponse response)
    //         throws ServletException, IOException {
    //     response.setContentType("text/plain");
    //     response.setStatus(HttpServletResponse.SC_OK);
		
    //     response.getWriter().format("Name: %s%n", request.getParameter("name"));
    //     response.getWriter().format("Age: %s%n", request.getParameter("age"));
    // }

	// send json response
	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.setStatus(HttpServletResponse.SC_OK);

		// Get the list of texts from the request parameters
		String yyy = request.getParameterNames().toString();
		System.out.println("yyy");
		System.out.println(yyy);
		Gson gson = new Gson();
		String[] textArray = gson.fromJson(request.getParameter("texts"), String[].class);
		System.out.println("textArray");
		System.out.println(textArray);
		if (textArray == null) {
			Map<String, Object> dict = new HashMap<>();
			dict.put("err", "no text sent");
			
			// Convert the list of texts to a JSON string using Gson
			String json = gson.toJson(dict);

			// Write the JSON string to the response
			response.getWriter().write(json);
            return;
        }

		// compute result
		List<String> texts = Arrays.asList(textArray);
		//String[] texts = ["eee","rrr"];
		//List<String> texts = Arrays.asList("foo", "bar", "baz");

		System.out.println(textArray);
		System.out.println(texts);
		System.out.println(texts.get(0));
		System.out.println(texts.get(1));

		int length = texts.size();
		String result = TextReconstructor.reconstructText(texts.get(0), texts.get(1));

		Map<String, Object> dict = new HashMap<>();

        dict.put("texts", texts);
		dict.put("nb_texts", length);
        dict.put("result", result);
		
		// Convert the list of texts to a JSON string using Gson
		String json = gson.toJson(dict);

		// Write the JSON string to the response
		response.getWriter().write(json);
    }

	// @Override
    // protected void doPost_(HttpServletRequest request, HttpServletResponse response)
    //         throws ServletException, IOException {
    //     response.setContentType("text/plain");
    //     response.setStatus(HttpServletResponse.SC_OK);

	// 	// set the result as an attribute on the request
	// 	request.setAttribute("name", "eeee");
	// 	request.setAttribute("result", "oups");

	// 	// forward the request to the JSP page
	// 	RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
	// 	dispatcher.forward(request, response);

    // }
}
