package loadster.sdk.types;

/**
 * A reference to an API resource.
 */
public class Reference {
    private String id;
    private String href;

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
}
