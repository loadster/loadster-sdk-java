package loadster.sdk.types;

/**
 * Specialized playback options for running a Loadster script or test.
 */
public class PlaybackOptions {
    private long connectionBps;
    private String httpUserAgent;
    private long httpTotalResourceTimeout;
    private long httpIndividualResourceTimeout;
    private int httpResourceThreads;
    private boolean skipWaitTimes;

    public long getConnectionBps() {
        return connectionBps;
    }

    public void setConnectionBps(long connectionBps) {
        this.connectionBps = connectionBps;
    }

    public String getHttpUserAgent() {
        return httpUserAgent;
    }

    public void setHttpUserAgent(String httpUserAgent) {
        this.httpUserAgent = httpUserAgent;
    }

    public long getHttpTotalResourceTimeout() {
        return httpTotalResourceTimeout;
    }

    public void setHttpTotalResourceTimeout(long httpTotalResourceTimeout) {
        this.httpTotalResourceTimeout = httpTotalResourceTimeout;
    }

    public long getHttpIndividualResourceTimeout() {
        return httpIndividualResourceTimeout;
    }

    public void setHttpIndividualResourceTimeout(long httpIndividualResourceTimeout) {
        this.httpIndividualResourceTimeout = httpIndividualResourceTimeout;
    }

    public int getHttpResourceThreads() {
        return httpResourceThreads;
    }

    public void setHttpResourceThreads(int httpResourceThreads) {
        this.httpResourceThreads = httpResourceThreads;
    }

    public boolean isSkipWaitTimes() {
        return skipWaitTimes;
    }

    public void setSkipWaitTimes(boolean skipWaitTimes) {
        this.skipWaitTimes = skipWaitTimes;
    }
}
