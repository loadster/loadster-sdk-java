package loadster.sdk.types;

import java.util.Date;

/**
 * A reference to an API resource.
 */
public class Reference {
    private String id;
    private String name;
    private String href;
    private Date createdDate;
    private Date modifiedDate;

    public Reference() {
    }

    public Reference(String id, String href) {
        this.id = id;
        this.href = href;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
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
}
