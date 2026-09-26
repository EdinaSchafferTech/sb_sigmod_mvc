package pti.sb_sigmod_mvc.xml;

import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.Namespace;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import org.springframework.stereotype.Component;

@Component
public class XmlReader {

	public Map<String, Integer> getAuthors(String path, String search) {

		Map<String, Integer> authorMap = new TreeMap<>();

		try {
			File file = new File(path);

			if (!file.exists()|| !file.isFile()) {
				return authorMap;
			}

			SAXBuilder sb = new SAXBuilder();
			Document doc = sb.build(file);
			Element rootElement = doc.getRootElement();
			Namespace ns = rootElement.getNamespace();

			List<Element> issueList = rootElement.getChildren("issue", ns);
			for (Element issue : issueList) {
				Element articles = issue.getChild("articles", ns);

				List<Element> articleList = articles.getChildren("article", ns);
				for (Element article : articleList) {
					Element authors = article.getChild("authors", ns);

					List<Element> authorList = authors.getChildren("author", ns);
					boolean authorPos01Found = false;
					for (Element author : authorList) {

						if (author.getAttributeValue("position").equals("01")) {

							authorPos01Found = true;
							break;
						}
					}

					if (authorPos01Found == true) {
						for (Element author : authorList) {

							String authorName = author.getValue();

							if (authorName.toLowerCase().contains(search.toLowerCase())) {
								if (authorMap.containsKey(authorName)) {

									Integer counter = authorMap.get(authorName);
									counter++;
									authorMap.put(authorName, counter);
								} else {

									authorMap.put(authorName, 1);
								}
							}

						}
					}
				}
			}
		} catch (Exception e) {
			 e.printStackTrace();

		}

		return authorMap;
	}

	public void exportToXml(String path) {

		Map<String, Integer> authorCounts = getAuthors(path, "");

		try {
			FileWriter writer = new FileWriter("output.xml");

			XMLOutputter outputter = new XMLOutputter(Format.getPrettyFormat());

			Document doc = new Document();

			Element rootElement = new Element("authors");

			for (Map.Entry<String, Integer> author : authorCounts.entrySet()) {

				Element authorElement = new Element("author");

				authorElement.setText(author.getKey());
				authorElement.setAttribute("counter", author.getValue().toString());

				rootElement.addContent(authorElement);
			}

			doc.setRootElement(rootElement);

			outputter.output(doc, writer);
			writer.close();

		} catch (Exception e) {
			e.printStackTrace();

		}
	}

}
