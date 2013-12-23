package loadster.sdk.tasks;

import loadster.sdk.client.WorkbenchApiClient;
import loadster.sdk.exceptions.ApiException;
import loadster.sdk.types.Reference;
import loadster.sdk.types.TestStatus;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.http.ProtocolException;
import org.apache.log4j.Logger;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Simple task to kick off a test via the Loadster Workbench web service, monitor it while it runs, and automatically
 * download a test report once it is finished.
 */
public class RunTest implements Runnable {
    private static final Logger log = Logger.getLogger(RunTest.class);

    private WorkbenchApiClient client;
    private String scenarioUri;
    private String testReportOutputFile;

    public RunTest(String apiHost, int apiPort, String apiKey, String scenarioUri, String testReportOutputFile) {
        this.client = new WorkbenchApiClient(apiHost, apiPort, apiKey);
        this.scenarioUri = scenarioUri;
        this.testReportOutputFile = testReportOutputFile;
    }

    public void run() {
        try {
            Reference ref = client.startTest(scenarioUri);

            log.info("Started test " + ref.getId() + " for scenario " + scenarioUri);

            waitForTestToStart(ref);
            pollWhileTestIsRunning(ref);

            log.info("Attempting to fetch the test report...");

            IOUtils.copy(client.getTestReport(ref), new FileOutputStream(testReportOutputFile));

            log.info("Saved test report to " + testReportOutputFile);
        } catch (Exception e) {
            log.error(e);

            System.exit(2);
        }

        System.exit(0);
    }

    private void waitForTestToStart(Reference ref) throws ApiException, InterruptedException, IOException, ProtocolException {
        for (int i = 0; i < 60; i++) {
            Thread.sleep(10000);

            TestStatus status = client.getTestStatus(ref);

            if (status.getStatus().equals(TestStatus.RUNNING)) {
                log.info("Test is up and running!");

                break;
            } else {
                log.debug("Waiting for test to start...");
            }
        }
    }

    private void pollWhileTestIsRunning(Reference ref) throws ApiException, InterruptedException, IOException, ProtocolException {
        for (TestStatus status = client.getTestStatus(ref); status.getStatus().equals(TestStatus.RUNNING); status = client.getTestStatus(ref)) {
            log.info("Test is running (v-users=" + status.getRunningUsers() + ")");

            Thread.sleep(10000);
        }

        log.info("Test is finished!");
    }

    public static void main(String[] args) {
        String apiKey = System.getProperty("loadster.api.key");
        String apiHost = System.getProperty("loadster.api.host");
        int apiPort = Integer.parseInt(System.getProperty("loadster.api.port", "1999"));

        if (StringUtils.isEmpty(apiKey)) {
            System.err.println("API key not set! Use -Dloadster.api.key=changeme");
            System.exit(1);
        } else if (StringUtils.isEmpty(apiHost)) {
            System.err.println("API host not set! Use -Dloadster.api.host=10.0.0.1");
            System.exit(1);
        } else if (args.length < 2) {
            System.err.println("Usage: " + RunTest.class.getName() + " /projects/1/scenario/1 report.html");
            System.exit(1);
        }

        new RunTest(apiHost, apiPort, apiKey, args[0], args[1]).run();
    }
}
