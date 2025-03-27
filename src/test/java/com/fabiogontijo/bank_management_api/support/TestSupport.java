package com.fabiogontijo.bank_management_api.support;

import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InOrder;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.stubbing.OngoingStubbing;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;

public abstract class TestSupport {

	@BeforeAll
	public static void setUp() {
		FixtureFactoryLoader.loadTemplates("com.fabiogontijo.bank_management_api.templates");
	}

	@BeforeEach
	public void setUpTest() {
		MockitoAnnotations.openMocks(this);
		this.init();
	}

	public InOrder inOrder(Object... mocks) {
		return Mockito.inOrder(mocks);
	}

	public <T> OngoingStubbing<T> when(T methodCall) {
		return Mockito.when(methodCall);
	}

	public <T> T verify(T mock) {
		return Mockito.verify(mock);
	}

	public <T> T verify(T mock, int wantedNumberOfInvocations) {
		return Mockito.verify(mock, Mockito.times(wantedNumberOfInvocations));
	}

	public <T> boolean testPrivateConstructor(Class<T> clazz) {

		boolean hasPrivateConstructor = false;
		Constructor<?>[] declaredConstructors = clazz.getDeclaredConstructors();
		if (declaredConstructors != null && declaredConstructors[0] != null) {
			hasPrivateConstructor = Modifier.isPrivate(declaredConstructors[0].getModifiers());
		}

		return hasPrivateConstructor;
	}

	@AfterEach
	public void teardown() {
		Mockito.clearAllCaches();
	}

	public abstract void init();

}
