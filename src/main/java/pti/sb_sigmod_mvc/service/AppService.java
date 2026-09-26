package pti.sb_sigmod_mvc.service;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pti.sb_sigmod_mvc.dto.AuthorDTO;
import pti.sb_sigmod_mvc.model.Author;
import pti.sb_sigmod_mvc.repository.AuthorRepository;
import pti.sb_sigmod_mvc.xml.XmlReader;

@Service
public class AppService {

	public XmlReader reader;
	private AuthorRepository repository;

	@Autowired
	public AppService(XmlReader reader, AuthorRepository repository) {
		super();
		this.reader = reader;
		this.repository = repository;
	}

	public List<AuthorDTO> getAuthors(String path, String search) {

		List<AuthorDTO> authorDTOList = new ArrayList<>();

		Map<String, Integer> authorCounts = reader.getAuthors(path, search);
		for (Map.Entry<String, Integer> author : authorCounts.entrySet()) {

			AuthorDTO authorDTO = new AuthorDTO(author.getKey(), author.getValue());
			authorDTOList.add(authorDTO);
		}
		return authorDTOList;
	}

	public void exportToDatabase(String path) {

		Map<String, Integer> authorCounts = reader.getAuthors(path, "");

		for (Map.Entry<String, Integer> author : authorCounts.entrySet()) {

			Author newAuthor = new Author(null, author.getKey(), author.getValue());

			repository.save(newAuthor);
		}

	}

	public void exportToXml(String path) {
		reader.exportToXml(path);
	}

	public List<AuthorDTO> getAuthorsFromDatabase(String search) {
		List<AuthorDTO> authorDTOList = new ArrayList<>();

		List<Author> authors = repository.findByNameContainingIgnoreCase(search);
		System.out.println(authors.size());

		if (!authors.isEmpty()) {
			for (Author author : authors) {
				AuthorDTO authorDTO = new AuthorDTO(author.getName(), author.getCounter());
				authorDTOList.add(authorDTO);
			}
		} else {
			// nincs adat
		}

		return authorDTOList;
	}

}
