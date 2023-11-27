package listeners;


import annotations.RetryAfterSeconds;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {
    private int count = 1;
    private static final int MAX_TRY = 3;

    @Override
    public boolean retry(ITestResult iTestResult) {
        if (!iTestResult.isSuccess()) {
            if (count++ < MAX_TRY) {
                waitIfNeeded(iTestResult);
                iTestResult.setStatus(ITestResult.FAILURE);
                return true;
            } else {
                iTestResult.setStatus(ITestResult.FAILURE);
            }
        } else {
            iTestResult.setStatus(ITestResult.SUCCESS);
        }
        return false;
    }

    private void waitIfNeeded(ITestResult iTestResult) {
        boolean waitNeeded = iTestResult.getMethod().getRealClass().isAnnotationPresent(RetryAfterSeconds.class);
        boolean testFailed = iTestResult.getStatus() == ITestResult.FAILURE;
        if (waitNeeded && testFailed) {
            count++;
            RetryAfterSeconds annotation = (RetryAfterSeconds) iTestResult.getMethod().getRealClass().getAnnotation(RetryAfterSeconds.class);
            int seconds = annotation.seconds();
            System.out.println("Ждём " + seconds + " сек");
            try {
                Thread.sleep(seconds * 1000L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
