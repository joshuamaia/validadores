package com.joshua.validadores.domain;

import java.io.Serializable;

import com.joshua.validadores.enums.SexoEnum;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Pessoa implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	
	private String nome;
	
	private Integer idade;
	
	private Boolean comprovanteMilitar;
	
	private SexoEnum sexo;
	
}
