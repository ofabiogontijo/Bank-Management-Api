package com.fabiogontijo.bank_management_api.support;

import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import com.fabiogontijo.bank_management_api.core.BeanUtil;
import com.fabiogontijo.bank_management_api.transaction.TransactionFeeCalculatorService;
import lombok.Getter;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public abstract class TestSupportWithPublisher extends TestSupport {

	@Mock
	@Getter
	private TransactionFeeCalculatorService transactionFeeCalculatorService;

	@Getter
	private static MockedStatic<BeanUtil> beanUtilMock;

	@BeforeAll
	public static void setUp() {
		FixtureFactoryLoader.loadTemplates("com.fabiogontijo.bank_management_api.templates");
	}

	@BeforeEach
	public void setUpTest() {
		Mockito.clearAllCaches();

		beanUtilMock = Mockito.mockStatic(BeanUtil.class);

		MockitoAnnotations.openMocks(this);

		beanUtilMock.when(() -> BeanUtil.getBean(TransactionFeeCalculatorService.class))
				.thenReturn(transactionFeeCalculatorService);

		this.init();
	}

}
