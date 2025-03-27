package com.fabiogontijo.bank_management_api.templates;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import com.fabiogontijo.bank_management_api.transaction.TransactionPaymentMethodEnum;
import com.fabiogontijo.bank_management_api.transaction.web.TransactionInput;

import java.math.BigDecimal;

import static com.fabiogontijo.bank_management_api.support.FixtureTemplates.VALID;

public class TransactionInputTemplateLoader implements br.com.six2six.fixturefactory.loader.TemplateLoader {

	@Override
	public void load() {
		Fixture.of(TransactionInput.class).addTemplate(VALID.name(), new Rule() {
			{
				add("paymentMethod", TransactionPaymentMethodEnum.C);
				add("account", random(Integer.class, 1000, 9999));
				add("amount", random(BigDecimal.class, new BigDecimal("100.00"), new BigDecimal("1000.00")));
			}
		});
	}

}
