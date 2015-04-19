package loadster.sdk.types;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Project {
    private String id;
    private String name;
    private Date createdDate;
    private Date modifiedDate;
    private List<Reference> scenarios = new ArrayList<Reference>();
    private List<Reference> scripts = new ArrayList<Reference>();
    private List<Reference> datasets = new ArrayList<Reference>();

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

    public List<Reference> getScenarios() {
        return scenarios;
    }

    public void setScenarios(List<Reference> scenarios) {
        this.scenarios = scenarios;
    }

    public List<Reference> getScripts() {
        return scripts;
    }

    public void setScripts(List<Reference> scripts) {
        this.scripts = scripts;
    }

    public List<Reference> getDatasets() {
        return datasets;
    }

    public void setDatasets(List<Reference> datasets) {
        this.datasets = datasets;
    }
}
