package loadster.sdk.types;

/**
 * A virtual user group in a scenario.
 */
public class Population {
    private String id;
    private String name;
    private String scriptId;
    private String loadEngineId = "00001";
    private String rampUpStrategy;
    private long rampUpDuration;
    private long peakDuration;
    private String rampDownStrategy;
    private long rampDownDuration;
    private int count;
    private int iterations;
    private PlaybackOptions playbackOptions;

    public PlaybackOptions getPlaybackOptions() {
        return playbackOptions;
    }

    public void setPlaybackOptions(PlaybackOptions playbackOptions) {
        this.playbackOptions = playbackOptions;
    }

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

    public String getScriptId() {
        return scriptId;
    }

    public void setScriptId(String scriptId) {
        this.scriptId = scriptId;
    }

    public String getLoadEngineId() {
        return loadEngineId;
    }

    public void setLoadEngineId(String loadEngineId) {
        this.loadEngineId = loadEngineId;
    }

    public String getRampUpStrategy() {
        return rampUpStrategy;
    }

    public void setRampUpStrategy(String rampUpStrategy) {
        this.rampUpStrategy = rampUpStrategy;
    }

    public long getRampUpDuration() {
        return rampUpDuration;
    }

    public void setRampUpDuration(long rampUpDuration) {
        this.rampUpDuration = rampUpDuration;
    }

    public String getRampDownStrategy() {
        return rampDownStrategy;
    }

    public void setRampDownStrategy(String rampDownStrategy) {
        this.rampDownStrategy = rampDownStrategy;
    }

    public long getRampDownDuration() {
        return rampDownDuration;
    }

    public void setRampDownDuration(long rampDownDuration) {
        this.rampDownDuration = rampDownDuration;
    }

    public long getPeakDuration() {
        return peakDuration;
    }

    public void setPeakDuration(long peakDuration) {
        this.peakDuration = peakDuration;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getIterations() {
        return iterations;
    }

    public void setIterations(int iterations) {
        this.iterations = iterations;
    }
}
