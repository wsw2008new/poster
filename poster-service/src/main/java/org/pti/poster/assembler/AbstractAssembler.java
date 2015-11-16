package org.pti.poster.assembler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractAssembler {

	private static final Logger LOGGER = LoggerFactory.getLogger(AbstractAssembler.class);

	protected static Object getNewInstanceFor(String className) {
		Object result = null;
		Class<?> clazz;

		try {
			clazz = Class.forName(className);
			Constructor<?> ctor = clazz.getConstructor(getConstructorArgumentClasses());
			result = ctor.newInstance(getConstructorArguments());
		} catch (Exception e) {
			LOGGER.warn("Assembler creation error", e);
		}

		return result;
	}


	protected static Object[] getConstructorArguments() {
		List<Object> args = new ArrayList<>();

		return args.toArray();
	}

	protected static Class[] getConstructorArgumentClasses() {
		List<Class> args = new ArrayList<>();

		return args.toArray(new Class[args.size()]);
	}

}
