package loadster.sdk.types;

import java.util.Date;

/**
 * High-level status of a test. This is used to convey whether the test is in progress, has finished, and some basic
 * information about the outcome if available.
 */
public class TestStatus {
    public static final String UNKNOWN = "unknown";
    public static final String STARTING = "starting";
    public static final String RUNNING = "running";
    public static final String STOPPING = "stopping";
    public static final String FINISHED = "finished";

    private String status = UNKNOWN;
    private Date startDate;
    private Date endDate;
    private int runningUsers = 0;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getRunningUsers() {
        return runningUsers;
    }

    public void setRunningUsers(int runningUsers) {
        this.runningUsers = runningUsers;
    }
}
