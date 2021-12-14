package com.joshua.validadores.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joshua.validadores.domain.Pessoa;
import com.joshua.validadores.validators.ValidatorPessoa;

import br.com.fluentvalidator.context.ValidationResult;

@RestController
@RequestMapping("/pessoa")
public class PessoaController {
	
	@PostMapping
	public ResponseEntity<?> criar(@RequestBody Pessoa pessoa) {
		ValidatorPessoa validor = new ValidatorPessoa();
		
		ValidationResult resultValidator = validor.validate(pessoa);
		
		if (resultValidator.isValid()) {
			pessoa.setId(1L);
			return new ResponseEntity<Pessoa>(pessoa, HttpStatus.OK);
		} else {
			return new ResponseEntity<Object>(resultValidator.getErrors(), HttpStatus.BAD_REQUEST);
		}
	}

}
