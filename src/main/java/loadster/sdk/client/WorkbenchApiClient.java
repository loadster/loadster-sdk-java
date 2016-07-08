package loadster.sdk.client;

import feign.*;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import loadster.sdk.exceptions.ApiException;
import loadster.sdk.types.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

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
     * Lists all projects.
     */
    public List<Reference> listProjects() throws ApiException, IOException {
        return jsonApi.listProjects();
    }

    /**
     * Gets details on a project.
     */
    public Project getProject(String id) throws ApiException, IOException {
        return jsonApi.getProject(id);
    }

    /**
     * Gets details on a scenario.
     */
    public Scenario getScenario(String projectId, String scenarioId) throws ApiException, IOException {
        return jsonApi.getScenario(projectId, scenarioId);
    }

    /**
     * Gets details on a script.
     */
    public Script getScript(String projectId, String scriptId) throws ApiException, IOException {
        return jsonApi.getScript(projectId, scriptId);
    }

    /**
     * Gets details on a data set.
     */
    public DataSet getDataSet(String projectId, String dataSetId) throws ApiException, IOException {
        return jsonApi.getDataSet(projectId, dataSetId);
    }

    /**
     * Gets details on a test.
     */
    public Test getTest(String projectId, String scenarioId, String testId) throws ApiException, IOException {
        return jsonApi.getTest(projectId, scenarioId, testId);
    }

    /**
     * Attempts to start a test for a scenario.
     */
    public Test startTest(Scenario scenario) throws ApiException, IOException {
        Test test = jsonApi.startTest(scenario.getProjectId(), scenario.getId());

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
        @RequestLine("GET /projects")
        @Headers("Accept: application/json")
        List<Reference> listProjects();

        @RequestLine("GET /projects/{projectId}")
        @Headers("Accept: application/json")
        Project getProject(@Param("projectId") String projectId);

        @RequestLine("GET /projects/{projectId}/scenarios/{scenarioId}")
        @Headers("Accept: application/json")
        Scenario getScenario(@Param("projectId") String projectId, @Param("scenarioId") String scenarioId);

        @RequestLine("GET /projects/{projectId}/scripts/{scriptId}")
        @Headers("Accept: application/json")
        Script getScript(@Param("projectId") String projectId, @Param("scriptId") String scriptId);

        @RequestLine("GET /projects/{projectId}/datasets/{dataSetId}")
        @Headers("Accept: application/json")
        DataSet getDataSet(@Param("projectId") String projectId, @Param("dataSetId") String dataSetId);

        @RequestLine("GET /projects/{projectId}/scenarios/{scenarioId}/tests/{testId}")
        @Headers("Accept: application/json")
        Test getTest(@Param("projectId") String projectId, @Param("scenarioId") String scenarioId, @Param("testId") String testId);

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
