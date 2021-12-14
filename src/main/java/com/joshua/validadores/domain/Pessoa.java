package com.joshua.validadores.domain;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pessoa implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	
	private String nome;
	
	private Integer idade;
	
}
