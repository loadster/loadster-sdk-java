package loadster.sdk.types;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * A Loadster data set (used for populating canned data in script variables).
 */
public class DataSet {
    private String id;
    private String name;
    private Date createdDate;
    private Date modifiedDate;
    private List<String[]> data = new ArrayList<String[]>();

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

    public List<String[]> getData() {
        return data;
    }

    public void setData(List<String[]> data) {
        this.data = data;
    }
}
