package com.fabiogontijo.bank_management_api.account.web;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

import static lombok.AccessLevel.PRIVATE;

@Getter
@EqualsAndHashCode
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor(access = PRIVATE)
public class AccountDTO {

	@JsonProperty("numero_conta")
	private Integer accountNumber;

	@JsonProperty("saldo")
	private BigDecimal balance;

}
