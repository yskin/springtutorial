package udemy.springdemo.mvc;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloWorldController {

	// need a controller method to show the initial HTML form
	@RequestMapping("/showForm")
	public String showForm() {
		return "helloworld-form";
	}

	// need a controller method to process the HTML form
	@RequestMapping("/processFrom")
	public String processFrom() {
		return "helloworld";
	}

	// new a controller method to read form data and
	// add data to the model
	@RequestMapping("/processFormVersionTwo")
	public String letsShoutDude(HttpServletRequest request, Model model) {
		// read the request parameter from the HTML form
		String studentName = request.getParameter("studentName");
		// convert the data to all caps
		studentName = studentName.toUpperCase();
		// create the message
		String message = "Hey! " + studentName;
		// add message to the model
		model.addAttribute("message", message);
		return "helloworld";
	}

}
