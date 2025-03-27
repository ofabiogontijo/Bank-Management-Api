package com.fabiogontijo.bank_management_api.templates;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import com.fabiogontijo.bank_management_api.account.Account;
import com.fabiogontijo.bank_management_api.transaction.Transaction;
import com.fabiogontijo.bank_management_api.transaction.TransactionPaymentMethodEnum;

import java.math.BigDecimal;

import static com.fabiogontijo.bank_management_api.core.IdGenerator.generateId;
import static com.fabiogontijo.bank_management_api.support.FixtureTemplates.VALID;

public class TransactionTemplateLoader implements br.com.six2six.fixturefactory.loader.TemplateLoader {

	@Override
	public void load() {
		Fixture.of(Transaction.class).addTemplate(VALID.name(), new Rule() {
			{
				add("id", generateId());
				add("account", one(Account.class, VALID.name()));
				add("paymentMethod", TransactionPaymentMethodEnum.C);
				add("amount", random(BigDecimal.class, new BigDecimal("100.00"), new BigDecimal("1000.00")));
			}
		});
	}

}
