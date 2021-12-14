package com.joshua.validadores.validators;

import static br.com.fluentvalidator.predicate.ComparablePredicate.greaterThan;
import static br.com.fluentvalidator.predicate.LogicalPredicate.not;
import static br.com.fluentvalidator.predicate.ObjectPredicate.nullValue;

import com.joshua.validadores.domain.Pessoa;

import br.com.fluentvalidator.AbstractValidator;

public class ValidatorPessoa extends AbstractValidator<Pessoa> {

	@Override
	public void rules() {

		ruleFor(Pessoa::getNome).must(not(nullValue())).withMessage("Nome não pode ser nulo").withFieldName("nome");

		ruleFor(Pessoa::getIdade).must(greaterThan(0)).withMessage("Idade tem que ser maior que 0 (ZERO)")
				.withFieldName("idade");
	}

	public static void main(String[] args) {
		ValidatorPessoa validor = new ValidatorPessoa();

		Pessoa p = new Pessoa();

		System.out.println(validor.validate(p).getErrors());
	}

}