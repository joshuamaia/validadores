package com.joshua.validadores;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.empty;
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
	void testComprovanteMilitarObrigatorio() {
		final Pessoa pessoa = new Pessoa();

		pessoa.setDataNascimento(LocalDate.parse("2003-12-18", DateTimeFormatter.ISO_LOCAL_DATE));
		pessoa.setNome("João");
		pessoa.setSexo(SexoEnum.MASCULINO);
		pessoa.setComprovanteMilitar(false);
		
		ValidatorPessoa validatorPessoa = new ValidatorPessoa();
	    final ValidationResult result = validatorPessoa.validate(pessoa);
	    
	    assertFalse(result.isValid());
	    assertThat(result.getErrors(), not(empty()));
	    assertThat(result.getErrors(), hasSize(1));

	    assertThat(result.getErrors(), hasItem(hasProperty("field", containsString("comprovanteMilitar"))));
	    assertThat(result.getErrors(), hasItem(hasProperty("message", containsString("Comprovante militar é obrigatório!"))));
	}
	
	@Test
	void testComprovanteMilitarFalso() {
		final Pessoa pessoa = new Pessoa();

		pessoa.setDataNascimento(LocalDate.parse("2003-12-18", DateTimeFormatter.ISO_LOCAL_DATE));
		pessoa.setNome("Maria");
		pessoa.setSexo(SexoEnum.FEMININO);
		pessoa.setComprovanteMilitar(true);
		
		ValidatorPessoa validatorPessoa = new ValidatorPessoa();
	    final ValidationResult result = validatorPessoa.validate(pessoa);
	    
	    assertFalse(result.isValid());
	    assertThat(result.getErrors(), not(empty()));
	    assertThat(result.getErrors(), hasSize(1));

	    assertThat(result.getErrors(), hasItem(hasProperty("field", containsString("comprovanteMilitar"))));
	    assertThat(result.getErrors(), hasItem(hasProperty("message", containsString("Comprovante militar tem que ser falso!"))));
	}
	
	@Test
	void testPessoaSemSexo() {
		final Pessoa pessoa = new Pessoa();

		pessoa.setDataNascimento(LocalDate.parse("2003-12-18", DateTimeFormatter.ISO_LOCAL_DATE));
		pessoa.setNome("Maria");
		pessoa.setComprovanteMilitar(true);
		
		ValidatorPessoa validatorPessoa = new ValidatorPessoa();
	    final ValidationResult result = validatorPessoa.validate(pessoa);
	    
	    assertFalse(result.isValid());
	    assertThat(result.getErrors(), not(empty()));
	    assertThat(result.getErrors(), hasSize(1));

	    assertThat(result.getErrors(), hasItem(hasProperty("field", containsString("sexo"))));
	    assertThat(result.getErrors(), hasItem(hasProperty("message", containsString("Sexo não pode ser nulo!"))));
	}
	
	@Test
	void testPessoaSemDataDeNascimento() {
		final Pessoa pessoa = new Pessoa();

		pessoa.setNome("Maria");
		pessoa.setSexo(SexoEnum.FEMININO);
		pessoa.setComprovanteMilitar(false);
		
		ValidatorPessoa validatorPessoa = new ValidatorPessoa();
	    final ValidationResult result = validatorPessoa.validate(pessoa);
	    
	    System.out.println(result.getErrors());
	    
	    assertFalse(result.isValid());
	    assertThat(result.getErrors(), not(empty()));
	    assertThat(result.getErrors(), hasSize(1));

	    assertThat(result.getErrors(), hasItem(hasProperty("field", containsString("dataNascimento"))));
	    assertThat(result.getErrors(), hasItem(hasProperty("message", containsString("Data de Nascimento não pode ser nula!"))));
	}
	
	@Test
	void testPessoaSemNome() {
		final Pessoa pessoa = new Pessoa();

		pessoa.setDataNascimento(LocalDate.parse("2003-12-18", DateTimeFormatter.ISO_LOCAL_DATE));
		pessoa.setSexo(SexoEnum.FEMININO);
		pessoa.setComprovanteMilitar(false);
		
		ValidatorPessoa validatorPessoa = new ValidatorPessoa();
	    final ValidationResult result = validatorPessoa.validate(pessoa);
	    
	    assertFalse(result.isValid());
	    assertThat(result.getErrors(), not(empty()));
	    assertThat(result.getErrors(), hasSize(1));

	    assertThat(result.getErrors(), hasItem(hasProperty("field", containsString("nome"))));
	    assertThat(result.getErrors(), hasItem(hasProperty("message", containsString("Nome não pode ser nulo"))));
	}

}
