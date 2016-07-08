package loadster.sdk.tasks.ant;

import loadster.sdk.tasks.RunTest;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;

import java.io.File;

/**
 * Ant task to run a test.
 */
public class RunTestAntTask extends Task {
    private String apiHost = "localhost";
    private int apiPort = 1999;
    private String apiKey = null;
    private String projectId;
    private String scenarioId = null;
    private File htmlOutput = null;
    private File jsonOutput = null;

    /**
     * The main execute method. If all the right attributes are set, this runs the test.
     */
    public void execute() {
        if (apiKey == null || apiKey.length() == 0) {
            throw new BuildException("Parameter 'apiKey' is required!");
        }

        if (projectId == null || projectId.length() == 0) {
            throw new BuildException("Parameter 'projectId' is required!");
        }

        if (scenarioId == null || scenarioId.length() == 0) {
            throw new BuildException("Parameter 'scenarioId' is required!");
        }

        if (htmlOutput == null) {
            throw new BuildException("Parameter 'htmlOutput' is required! This is the destination where the HTML test report should be saved.");
        }

        System.out.println("Preparing to run scenario " + scenarioId + " of project " + projectId + " on " + apiHost + " port " + apiPort + "...");

        new RunTest(apiHost, apiPort, apiKey, projectId, scenarioId, htmlOutput.getAbsolutePath(), jsonOutput != null ? jsonOutput.getAbsolutePath() : null).run();
    }

    public String getApiHost() {
        return apiHost;
    }

    public void setApiHost(String apiHost) {
        this.apiHost = apiHost;
    }

    public int getApiPort() {
        return apiPort;
    }

    public void setApiPort(int apiPort) {
        this.apiPort = apiPort;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getScenarioId() {
        return scenarioId;
    }

    public void setScenarioId(String scenarioId) {
        this.scenarioId = scenarioId;
    }

    public File getHtmlOutput() {
        return htmlOutput;
    }

    public void setHtmlOutput(File htmlOutput) {
        this.htmlOutput = htmlOutput;
    }

    public File getJsonOutput() {
        return jsonOutput;
    }

    public void setJsonOutput(File jsonOutput) {
        this.jsonOutput = jsonOutput;
    }
}
