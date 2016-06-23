package loadster.sdk.client;

import feign.*;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import loadster.sdk.exceptions.ApiException;
import loadster.sdk.types.Scenario;
import loadster.sdk.types.Test;
import loadster.sdk.types.TestStatistics;
import loadster.sdk.types.TestStatus;

import java.io.IOException;
import java.io.InputStream;

/**
 * Simplified client interface for the Workbench API.
 */
public class WorkbenchApiClient {
    private WorkbenchApi jsonApi;

    public WorkbenchApiClient(final String host, final int port, final String apiKey) {
        jsonApi = Feign.builder().decoder(new JacksonDecoder()).encoder(new JacksonEncoder()).requestInterceptor(new RequestInterceptor() {
            @Override
            public void apply(RequestTemplate requestTemplate) {
                requestTemplate.query("apiKey", apiKey);
            }
        }).target(WorkbenchApi.class, "http://" + host + ":" + port);
    }

    /**
     * Attempts to start a test for a scenario.
     */
    public Test startTest(Scenario scenario) throws ApiException, IOException {
        Test test = jsonApi.startTest(scenario.getProjectId(), scenario.getId());

        // TODO - the API should be including these fields in the response
        test.setProjectId(scenario.getProjectId());
        test.setScenarioId(scenario.getId());

        return test;
    }

    /**
     * Gets up-to-date status on a test.
     */
    public TestStatus getTestStatus(Test test) throws ApiException, IOException {
        return jsonApi.getTestStatus(test.getProjectId(), test.getScenarioId(), test.getId());
    }

    /**
     * Fetches an HTML test report for a test. The test has to be finished or this will fail.
     */
    public InputStream getTestReport(Test test) throws ApiException, IOException {
        return jsonApi.getTestReport(test.getProjectId(), test.getScenarioId(), test.getId()).body().asInputStream();
    }

    /**
     * Fetches statistics for a test. The test has to be finished or this will fail.
     */
    public TestStatistics getTestStatistics(Test test) throws ApiException, IOException {
        return jsonApi.getTestStatistics(test.getProjectId(), test.getScenarioId(), test.getId());
    }

    private interface WorkbenchApi {
        @RequestLine("POST /projects/{projectId}/scenarios/{scenarioId}/tests")
        @Headers("Accept: application/json")
        Test startTest(@Param("projectId") String projectId, @Param("scenarioId") String scenarioId);

        @RequestLine("GET /projects/{projectId}/scenarios/{scenarioId}/tests/{testId}/status")
        @Headers("Accept: application/json")
        TestStatus getTestStatus(@Param("projectId") String projectId, @Param("scenarioId") String scenarioId, @Param("testId") String testId);

        @RequestLine("GET /projects/{projectId}/scenarios/{scenarioId}/tests/{testId}/report")
        @Headers("Accept: application/json")
        TestStatistics getTestStatistics(@Param("projectId") String projectId, @Param("scenarioId") String scenarioId, @Param("testId") String testId);

        @RequestLine("GET /projects/{projectId}/scenarios/{scenarioId}/tests/{testId}/report")
        @Headers("Accept: text/html")
        Response getTestReport(@Param("projectId") String projectId, @Param("scenarioId") String scenarioId, @Param("testId") String testId);
    }
}
