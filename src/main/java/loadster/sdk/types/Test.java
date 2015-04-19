package loadster.sdk.types;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Test {
    private String id;
    private String name;
    private Date createdDate;
    private Date modifiedDate;
    private Scenario playedScenario;
    private List<Script> playedScripts = new ArrayList<Script>();
    private Date startDate;
    private Date endDate;
    private boolean startedAutomatically = false;
    private boolean stoppedEarly = false;
    private String report;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public Scenario getPlayedScenario() {
        return playedScenario;
    }

    public void setPlayedScenario(Scenario playedScenario) {
        this.playedScenario = playedScenario;
    }

    public List<Script> getPlayedScripts() {
        return playedScripts;
    }

    public void setPlayedScripts(List<Script> playedScripts) {
        this.playedScripts = playedScripts;
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

    public boolean isStartedAutomatically() {
        return startedAutomatically;
    }

    public void setStartedAutomatically(boolean startedAutomatically) {
        this.startedAutomatically = startedAutomatically;
    }

    public boolean isStoppedEarly() {
        return stoppedEarly;
    }

    public void setStoppedEarly(boolean stoppedEarly) {
        this.stoppedEarly = stoppedEarly;
    }

    public String getReport() {
        return report;
    }

    public void setReport(String report) {
        this.report = report;
    }
}
