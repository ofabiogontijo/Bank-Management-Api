package com.fabiogontijo.bank_management_api.templates;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import com.fabiogontijo.bank_management_api.transaction.web.TransactionDTO;

import java.math.BigDecimal;

import static com.fabiogontijo.bank_management_api.support.FixtureTemplates.VALID;

public class TransactionDTOTemplateLoader implements TemplateLoader {

	@Override
	public void load() {
		Fixture.of(TransactionDTO.class).addTemplate(VALID.name(), new Rule() {
			{
				add("accountNumber", random(Integer.class, range(100000, 999999)));
				add("balance", random(BigDecimal.class, range(100.00, 150.00)));
			}
		});
	}

}
