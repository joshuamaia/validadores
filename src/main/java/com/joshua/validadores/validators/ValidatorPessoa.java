package com.joshua.validadores.validators;

import static br.com.fluentvalidator.predicate.LogicalPredicate.isTrue;
import static br.com.fluentvalidator.predicate.LogicalPredicate.isFalse;
import static br.com.fluentvalidator.predicate.LogicalPredicate.not;
import static br.com.fluentvalidator.predicate.ObjectPredicate.nullValue;

import java.time.Duration;
import java.time.LocalDate;

import com.joshua.validadores.domain.Pessoa;
import com.joshua.validadores.enums.SexoEnum;

import br.com.fluentvalidator.AbstractValidator;

public class ValidatorPessoa extends AbstractValidator<Pessoa> {

	@Override
	public void rules() {

		ruleFor(Pessoa::getNome).must(not(nullValue())).withMessage("Nome não pode ser nulo").withFieldName("nome");

		ruleFor(Pessoa::getSexo).must(not(nullValue())).withMessage("Sexo não pode ser nulo!").withFieldName("sexo");

		ruleFor(Pessoa::getDataNascimento).must(not(nullValue())).withMessage("Data de Nascimento não pode ser nula!")
				.withFieldName("dataNascimento");

		ruleFor(Pessoa::getDataNascimento).must(not(p -> {
			if (p == null) {
				return false;
			}
			Duration diff = Duration.between(p.atStartOfDay(), LocalDate.now().atStartOfDay());
			return diff.toDays() < 0;
		})).withMessage("Data de Nascimento não pode ser menor que ZERO").withFieldName("dataNascimento");

		ruleFor(pessoa -> pessoa).must(isTrue(Pessoa::getComprovanteMilitar))
				.when(p -> {
					boolean daysTest = p.getDataNascimento() != null && Duration.between(p.getDataNascimento().atStartOfDay(), LocalDate.now().atStartOfDay()).toDays() >= 18;
					return daysTest && p.getSexo() != null && p.getSexo().equals(SexoEnum.MASCULINO);
				})				
				.withMessage("Comprovante militar é obrigatório!").withFieldName("comprovanteMilitar");

		ruleFor(pessoa -> pessoa).must(isFalse(Pessoa::getComprovanteMilitar))
				.when(p -> p.getSexo() != null && p.getSexo().equals(SexoEnum.FEMININO))
				.withMessage("Comprovante militar tem que ser falso!").withFieldName("comprovanteMilitar");

	}

	public static void main(String[] args) {

	}

}