package udemy.springdemo.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller // indicate this class is MVC controller
public class HomeController {

	@RequestMapping("/")
	public String showPage() {
		return "main-menu";
	}

}
