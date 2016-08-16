package loadster.sdk.tasks;

import loadster.sdk.client.WorkbenchApiClient;
import loadster.sdk.exceptions.ApiException;
import loadster.sdk.types.Scenario;
import loadster.sdk.types.Test;
import loadster.sdk.types.TestStatus;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Simple task to kick off a test via the Loadster Workbench web service, monitor it while it runs, and automatically
 * download a test report once it is finished.
 */
public class RunTest implements Runnable {
    private static final int MAX_POLL_FAILS = 3;

    private WorkbenchApiClient client;
    private String projectId;
    private String scenarioId;
    private String testReportOutputFile;
    private String testJsonOutputFile;

    public RunTest(String apiHost, int apiPort, String apiKey, String projectId, String scenarioId, String testReportOutputFile, String testJsonOutputFile) {
        this.client = new WorkbenchApiClient(apiHost, apiPort, apiKey);
        this.projectId = projectId;
        this.scenarioId = scenarioId;
        this.testReportOutputFile = testReportOutputFile;
        this.testJsonOutputFile = testJsonOutputFile;
    }

    public void run() {
        try {
            Scenario scenario = new Scenario(projectId, scenarioId);
            Test test = client.startTest(scenario);

            log("Started test " + test.getId() + " for scenario " + scenarioId + " of project " + projectId);

            waitForTestToStart(test);
            pollWhileTestIsRunning(test);

            if (testReportOutputFile != null) {
                log("Attempting to fetch the HTML test report...");

                copy(waitForTestReport(test, true), new FileOutputStream(testReportOutputFile));

                log("Saved HTML test report to " + testReportOutputFile);
            }

            if (testJsonOutputFile != null) {
                log("Attempting to fetch the JSON test report...");

                copy(waitForTestReport(test, false), new FileOutputStream(testJsonOutputFile));

                log("Saved JSON test report to " + testJsonOutputFile);
            }
        } catch (Exception e) {
            log(e.getMessage());

            System.exit(2);
        }

        System.exit(0);
    }

    private void waitForTestToStart(Test test) {
        int failures = 0;

        for (int i = 0; i < 60 && failures < MAX_POLL_FAILS; i++) {
            try {
                Thread.sleep(10000);

                TestStatus status = client.getTestStatus(test);

                if (status.getStatus().equals(TestStatus.RUNNING)) {
                    log("Test is up and running!");

                    break;
                } else {
                    log("Waiting for test to start...");
                }
            } catch (Exception e) {
                log(e.getMessage());

                failures++;
            }
        }

        if (failures == MAX_POLL_FAILS) {
            log("Exiting because of too many polling failures! Loadster may be unreachable.");
        }
    }

    private void pollWhileTestIsRunning(Test test) {
        int failures = 0;

        while (failures < MAX_POLL_FAILS) {
            try {
                TestStatus status = client.getTestStatus(test);

                if (status.getStatus().equals(TestStatus.RUNNING)) {
                    log("Test is running (v-users=" + status.getRunningUsers() + ")");

                    Thread.sleep(10000);
                } else {
                    break;
                }
            } catch (Exception e) {
                log(e.getMessage());

                failures++;
            }
        }

        if (failures == MAX_POLL_FAILS) {
            log("Exiting because of too many polling failures! Loadster may be unreachable.");
        } else {
            log("Test is finished!");
        }
    }

    private InputStream waitForTestReport(Test test, boolean html) throws ApiException {
        for (int i = 0; i < 60; i++) {
            try {
                Thread.sleep(3000);

                if (html) {
                    return client.getHtmlTestReport(test);
                } else {
                    return client.getJsonTestReport(test);
                }
            } catch (Exception e) {
                log("Waiting for the test report to become available...");
            }
        }

        throw new ApiException("Failed to load the test report!");
    }

    private static void log(String message) {
        System.out.println(message);
    }

    private static void copy(InputStream source, OutputStream destination) throws IOException {
        byte[] buf = new byte[2048];
        int x;

        while ((x = source.read(buf)) != -1) {
            destination.write(buf, 0, x);
        }

        source.close();
        destination.close();
    }

    public static void main(String[] args) {
        String apiKey = System.getProperty("loadster.api.key");
        String apiHost = System.getProperty("loadster.api.host");
        int apiPort = Integer.parseInt(System.getProperty("loadster.api.port", "1999"));

        if (apiKey == null || apiKey.length() == 0) {
            System.err.println("API key not set! Use -Dloadster.api.key=changeme");
            System.exit(1);
        } else if (apiHost == null || apiHost.length() == 0) {
            System.err.println("API host not set! Use -Dloadster.api.host=10.0.0.1");
            System.exit(1);
        } else if (args.length < 3) {
            System.err.println("Usage: " + RunTest.class.getName() + " <projectId> <scenarioId> <htmlOutputFile> <jsonOutputFile>");
            System.exit(1);
        } else if (args.length == 3) {
            new RunTest(apiHost, apiPort, apiKey, args[0], args[1], args[2], null).run();
        } else {
            new RunTest(apiHost, apiPort, apiKey, args[0], args[1], args[2], args[3]).run();
        }
    }
}
