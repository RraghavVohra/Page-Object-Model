package util;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {

	private int retryCount = 0;
	// Retry up to 2 times before marking the test as a real failure
	private static final int MAX_RETRY = 2;

	@Override
	public boolean retry(ITestResult result) {
		if (retryCount < MAX_RETRY) {
			retryCount++;
			System.out.println("Retrying test [" + result.getName() + "] — attempt " + retryCount + " of " + MAX_RETRY);
			return true;
		}
		return false;
	}
}
