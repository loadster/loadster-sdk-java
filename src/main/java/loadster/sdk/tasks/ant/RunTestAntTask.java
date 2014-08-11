package loadster.sdk.tasks.ant;

import loadster.sdk.tasks.RunTest;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;

import java.io.File;

/**
 * Ant task to run a test.
 */
public class RunTestAntTask extends Task {
    private static final Logger log = Logger.getLogger(RunTestAntTask.class);

    private String apiHost = "localhost";
    private int apiPort = 1999;
    private String apiKey = null;
    private String scenarioPath = null;
    private File toFile = null;

    /**
     * The main execute method. If all the right attributes are set, this runs the test.
     */
    public void execute() {
        Logger.getRootLogger().getAppender("console").setLayout(new PatternLayout("%m%n"));
        Logger.getRootLogger().addAppender(new ConsoleAppender());

        if (apiKey == null || apiKey.length() == 0) {
            throw new BuildException("Parameter 'apikey' is required!");
        }

        if (scenarioPath == null || scenarioPath.length() == 0) {
            throw new BuildException("Parameter 'scenariopath' is required! Example /projects/00001/scenarios/00003");
        }

        if (toFile == null) {
            throw new BuildException("Parameter 'tofile' is required! This is the destination where the test report should be saved.");
        }

        log.info("Preparing to run scenario " + scenarioPath + " on " + apiHost + " port " + apiPort + "...");

        new RunTest(apiHost, apiPort, apiKey, scenarioPath, toFile.getAbsolutePath()).run();
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

    public String getScenarioPath() {
        return scenarioPath;
    }

    public void setScenarioPath(String scenarioPath) {
        this.scenarioPath = scenarioPath;
    }

    public File getToFile() {
        return toFile;
    }

    public void setToFile(File toFile) {
        this.toFile = toFile;
    }
}
