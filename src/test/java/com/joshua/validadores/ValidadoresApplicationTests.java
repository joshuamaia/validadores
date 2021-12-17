package com.joshua.validadores;

import static org.assertj.core.api.Assertions.assertThat;

import static br.com.fluentvalidator.predicate.LogicalPredicate.not;
import static br.com.fluentvalidator.predicate.StringPredicate.stringEmptyOrNull;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.joshua.validadores.domain.Pessoa;
import com.joshua.validadores.validators.ValidatorPessoa;

import br.com.fluentvalidator.context.ValidationResult;

@SpringBootTest
class ValidadoresApplicationTests {

	@Test
	void contextLoads() {
		final Pessoa pessoa = new Pessoa();

		pessoa.setIdade(-1);
		pessoa.setNome("Ana");

		ValidatorPessoa validatorPessoa = new ValidatorPessoa();
	    final ValidationResult result = validatorPessoa.validate(pessoa);

	    assertFalse(result.isValid());
	    assertThat(result.getErrors(), not(empty()));
	    assertThat(result.getErrors(), hasSize(3));

	    assertThat(result.getErrors(), hasItem(hasProperty("field", containsString("idade"))));
	    assertThat(result.getErrors(), hasItem(hasProperty("attemptedValue", equalTo(pessoa.getIdade()))));
	    assertThat(result.getErrors(), hasItem(hasProperty("message", containsString("Idade tem que ser maior que 0 (ZERO)"))));
	}

}
