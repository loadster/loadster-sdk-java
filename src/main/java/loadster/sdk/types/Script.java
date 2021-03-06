package loadster.sdk.types;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * A Loadster script.
 */
public class Script {
    private String id;
    private String projectId;
    private String name;
    private Date createdDate;
    private Date modifiedDate;
    private List<Reference> results = new ArrayList<Reference>();

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

    public List<Reference> getResults() {
        return results;
    }

    public void setResults(List<Reference> results) {
        this.results = results;
    }
}
