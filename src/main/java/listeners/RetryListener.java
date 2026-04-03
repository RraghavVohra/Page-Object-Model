package listeners;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;

import util.RetryAnalyzer;

public class RetryListener implements IAnnotationTransformer {

	@Override
	public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
		// Automatically apply RetryAnalyzer to every @Test method
		// This avoids adding retryAnalyzer = RetryAnalyzer.class to each individual test
		annotation.setRetryAnalyzer(RetryAnalyzer.class);
	}
}
