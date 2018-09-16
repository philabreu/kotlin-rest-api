package com.ingresse.ingresse.model;

import javax.persistence.Embeddable;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Embeddable
public class Endereco {

	@Getter
	@Setter
	@Size(max = 30)
	private String bairro;

	@Getter
	@Setter
	@Size(max = 30)
	private String cidade;

	@Getter
	@Setter
	@Size(max = 30)
	private String estado;

	
	
}
