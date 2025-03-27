package com.fabiogontijo.bank_management_api.account.web;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import com.fabiogontijo.bank_management_api.account.Account;
import com.fabiogontijo.bank_management_api.account.AccountCommand;
import com.fabiogontijo.bank_management_api.account.AccountQuery;
import com.fabiogontijo.bank_management_api.support.FixtureTemplates;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.setup.StandaloneMockMvcBuilder;

import static java.util.Collections.singletonList;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = { AccountWebBase.Config.class })
public class AccountWebBase {

	@MockBean
	private AccountCommand command;

	@MockBean
	private AccountQuery query;

	@BeforeAll
	public static void setUp() {
		FixtureFactoryLoader.loadTemplates("com.fabiogontijo.bank_management_api");
	}

	@BeforeEach
	public void setup() {
		Account account = Fixture.from(Account.class).gimme(FixtureTemplates.VALID.name());

		when(command.create(any())).thenReturn(account);
		when(query.findById(any())).thenReturn(account);

		StandaloneMockMvcBuilder standaloneMockMvcBuilder = MockMvcBuilders
				.standaloneSetup(new AccountRestService(command, query)).setMessageConverters(hal())
				.setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver());

		RestAssuredMockMvc.standaloneSetup(standaloneMockMvcBuilder);
	}

	private MappingJackson2HttpMessageConverter hal() {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JavaTimeModule());
		objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
		MappingJackson2HttpMessageConverter halConverter = new MappingJackson2HttpMessageConverter();
		halConverter.setObjectMapper(objectMapper);
		halConverter.setSupportedMediaTypes(singletonList(MediaType.APPLICATION_JSON));
		return halConverter;
	}

	@Configuration
	static class Config {

	}

}