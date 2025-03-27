package com.fabiogontijo.bank_management_api.transaction.web;

import com.fabiogontijo.bank_management_api.transaction.TransactionPaymentMethodEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class TransactionInput {

	@JsonProperty("forma_pagamento")
	private TransactionPaymentMethodEnum paymentMethod;

	@JsonProperty("numero_conta")
	private Integer account;

	@JsonProperty("valor")
	private BigDecimal amount;

}
