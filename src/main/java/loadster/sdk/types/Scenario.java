package loadster.sdk.types;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * A Loadster test scenario.
 */
public class Scenario {
    private String id;
    private String projectId;
    private String name;
    private Date createdDate;
    private Date modifiedDate;
    private List<Population> populations = new ArrayList<Population>();
    private List<Reference> tests = new ArrayList<Reference>();

    public Scenario() {
    }

    public Scenario(String projectId, String scenarioId) {
        this.projectId = projectId;
        this.id = scenarioId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
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

    public List<Population> getPopulations() {
        return populations;
    }

    public void setPopulations(List<Population> populations) {
        this.populations = populations;
    }

    public List<Reference> getTests() {
        return tests;
    }

    public void setTests(List<Reference> tests) {
        this.tests = tests;
    }
}
