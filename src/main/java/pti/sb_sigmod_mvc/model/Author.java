package pti.sb_sigmod_mvc.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("authors")
public class Author {

	@Id
	private Integer id;

	@Column("name")
	private String name;

	@Column("counter")
	private Integer counter;

	public Author() {
		super();
	}

	public Author(Integer id, String name, Integer counter) {
		super();
		this.id = id;
		this.name = name;
		this.counter = counter;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getCounter() {
		return counter;
	}

	public void setCounter(Integer counter) {
		this.counter = counter;
	}
}
