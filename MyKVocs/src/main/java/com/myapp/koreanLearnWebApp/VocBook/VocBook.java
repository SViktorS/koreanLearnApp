package com.myapp.koreanLearnWebApp.VocBook;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
public class VocBook {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private int vocBookId;
	
	@NotBlank(message = "ups...don't forget this one")
	private String name;
	
	private String description;
	
	public VocBook() {}

	public VocBook(String name, String description) {
		super();
		this.name = name;
		this.description = description;
	}

	@Override
	public String toString() {
		return "VocBook [vocBookId=" + vocBookId + ", name=" + name + ", description=" + description + "]";
	}

	public int getVocBookId() {
		return vocBookId;
	}

	public void setVocBookId(int vocBookId) {
		this.vocBookId = vocBookId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
