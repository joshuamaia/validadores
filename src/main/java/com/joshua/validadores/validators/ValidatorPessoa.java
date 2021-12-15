package com.joshua.validadores.validators;

import static br.com.fluentvalidator.predicate.ComparablePredicate.greaterThan;
import static br.com.fluentvalidator.predicate.LogicalPredicate.isTrue;
import static br.com.fluentvalidator.predicate.LogicalPredicate.not;
import static br.com.fluentvalidator.predicate.ObjectPredicate.nullValue;

import com.joshua.validadores.domain.Pessoa;
import com.joshua.validadores.enums.SexoEnum;

import br.com.fluentvalidator.AbstractValidator;

public class ValidatorPessoa extends AbstractValidator<Pessoa> {

	@Override
	public void rules() {

		ruleFor(Pessoa::getNome).must(not(nullValue())).withMessage("Nome não pode ser nulo").withFieldName("nome");
		
		ruleFor(Pessoa::getSexo).must(not(nullValue())).withMessage("Sexo não pode ser nulo").withFieldName("sexo");

		ruleFor(Pessoa::getIdade).must(greaterThan(0)).withMessage("Idade tem que ser maior que 0 (ZERO)")
				.withFieldName("idade");

		ruleFor(Pessoa::getComprovanteMilitar).must(isTrue())
				.withAttempedValue(p -> p.getIdade() >= 18 && p.getSexo() != null && p.getSexo().equals(SexoEnum.MASCULINO))
				.withMessage("Comprovante militar é obrigatório!").withFieldName("comprovanteMilitar");
	}

	public static void main(String[] args) {
		ValidatorPessoa validor = new ValidatorPessoa();

		Pessoa p = new Pessoa();

		System.out.println(validor.validate(p).getErrors());
	}

}