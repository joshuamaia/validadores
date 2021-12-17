package com.joshua.validadores;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.not;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.joshua.validadores.domain.Pessoa;
import com.joshua.validadores.enums.SexoEnum;
import com.joshua.validadores.validators.ValidatorPessoa;

import br.com.fluentvalidator.context.ValidationResult;

@SpringBootTest
class ValidadoresApplicationTests {

	@Test
	void contextLoads() {
		final Pessoa pessoa = new Pessoa();

		pessoa.setDataNascimento(LocalDate.parse("2021-12-18", DateTimeFormatter.ISO_LOCAL_DATE));
		pessoa.setNome("Ana");
		pessoa.setSexo(SexoEnum.FEMININO);
		pessoa.setComprovanteMilitar(false);
		
		ValidatorPessoa validatorPessoa = new ValidatorPessoa();
	    final ValidationResult result = validatorPessoa.validate(pessoa);
	    
	    assertFalse(result.isValid());
	    assertThat(result.getErrors(), not(empty()));
	    assertThat(result.getErrors(), hasSize(1));

	    assertThat(result.getErrors(), hasItem(hasProperty("field", containsString("dataNascimento"))));
	    assertThat(result.getErrors(), hasItem(hasProperty("attemptedValue", equalTo(pessoa.getDataNascimento()))));
	    assertThat(result.getErrors(), hasItem(hasProperty("message", containsString("Data de Nascimento não pode ser menor que ZERO"))));
	}

}
