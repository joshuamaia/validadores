package com.joshua.validadores.domain;

import java.io.Serializable;
import java.time.LocalDate;

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

	private LocalDate dataNascimento;

	private Boolean comprovanteMilitar;

	private SexoEnum sexo;

	@Override
	public String toString() {
		return "Pessoa [id=" + id + ", nome=" + nome + ", dataNascimento=" + dataNascimento + ", comprovanteMilitar="
				+ comprovanteMilitar + ", sexo=" + sexo + "]";
	}

}
