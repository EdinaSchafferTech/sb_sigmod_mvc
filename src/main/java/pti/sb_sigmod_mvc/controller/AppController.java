package pti.sb_sigmod_mvc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pti.sb_sigmod_mvc.dto.AuthorDTO;
import pti.sb_sigmod_mvc.service.AppService;

@Controller
public class AppController {

	private AppService service;

	@Autowired
	public AppController(AppService service) {
		super();
		this.service = service;
	}

	@GetMapping("/")
	public String home(Model model) {
		return "authors.html";
	}

	@GetMapping("/authors")
	public String getAuthors(@RequestParam String source, @RequestParam String path, @RequestParam String search,
			Model model) {

		if (source.equals("database")) {
			List<AuthorDTO> authors = service.getAuthorsFromDatabase(search);
			model.addAttribute("authors", authors);

		} else {
			List<AuthorDTO> authors = service.getAuthors(path, search);
			model.addAttribute("authors", authors);
		}
		model.addAttribute("source", source);
		model.addAttribute("path", path);

		return "authors.html";
	}

	@GetMapping("/export")
	public String export(@RequestParam String type, @RequestParam String path, Model model) {

		if (type.equals("database")) {
			service.exportToDatabase(path);
		} else if (type.equals("xml")) {
			service.reader.exportToXml(path);
		}
		List<AuthorDTO> authors = service.getAuthors(path, "");

		model.addAttribute("authors", authors);

		return "authors.html";
	}

	@GetMapping("/read-database")
	public String readDatabase(Model model) {
		List<AuthorDTO> authors = service.getAuthorsFromDatabase("");

		if (authors.isEmpty()) {
			model.addAttribute("error", "The database is empty.");
			return "authors.html";
		}
		model.addAttribute("authors", authors);
		model.addAttribute("source", "database");

		return "authors.html";
	}

	@PostMapping("/read-xml")
	public String readXml(@RequestParam String path, Model model) {

		List<AuthorDTO> authors = service.getAuthors(path, "");

		if (authors.isEmpty()) {
			model.addAttribute("error", "The XML file could not be read.");
			return "authors.html";
		}
		model.addAttribute("authors", authors);
		model.addAttribute("source", "xml");
		model.addAttribute("path", path);

		return "authors.html";
	}

}
