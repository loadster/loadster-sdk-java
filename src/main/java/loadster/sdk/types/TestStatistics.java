package loadster.sdk.types;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestStatistics {
    private List<String> urlsByTotalResponseTime;

    private Map<String, Integer> pageCount = new HashMap<String, Integer>();
    private Map<String, Double> totalResponseTimes = new HashMap<String, Double>();
    private Map<String, Double> maxResponseTimes = new HashMap<String, Double>();
    private Map<String, Double> avgResponseTimes = new HashMap<String, Double>();
    private Map<String, Double> minResponseTimes = new HashMap<String, Double>();
    private Map<String, HashMap<String, Integer>> pageErrorCount = new HashMap<String, HashMap<String, Integer>>();
    private Map<String, String> jsonDataByProvider = new HashMap<String, String>();
    private long totalHits;
    private long totalPages;
    private long totalErrors;
    private long totalIterations;
    private long totalBytesTransferred;
    private long totalBytesUploaded;
    private long totalBytesDownloaded;
    private double maxPagesPerSecond;
    private double avgPagesPerSecond;
    private double minPagesPerSecond;
    private double maxHitsPerSecond;
    private double avgHitsPerSecond;
    private double minHitsPerSecond;
    private double maxBytesPerSecond;
    private double avgBytesPerSecond;
    private double minBytesPerSecond;
    private double responseTimeAvg;
    private double responseTimeP90;
    private double responseTimeP80;

    public List<String> getUrlsByTotalResponseTime() {
        return urlsByTotalResponseTime;
    }

    public void setUrlsByTotalResponseTime(List<String> urlsByTotalResponseTime) {
        this.urlsByTotalResponseTime = urlsByTotalResponseTime;
    }

    public Map<String, Integer> getPageCount() {
        return pageCount;
    }

    public void setPageCount(Map<String, Integer> pageCount) {
        this.pageCount = pageCount;
    }

    public Map<String, Double> getTotalResponseTimes() {
        return totalResponseTimes;
    }

    public void setTotalResponseTimes(Map<String, Double> totalResponseTimes) {
        this.totalResponseTimes = totalResponseTimes;
    }

    public Map<String, Double> getMaxResponseTimes() {
        return maxResponseTimes;
    }

    public void setMaxResponseTimes(Map<String, Double> maxResponseTimes) {
        this.maxResponseTimes = maxResponseTimes;
    }

    public Map<String, Double> getAvgResponseTimes() {
        return avgResponseTimes;
    }

    public void setAvgResponseTimes(Map<String, Double> avgResponseTimes) {
        this.avgResponseTimes = avgResponseTimes;
    }

    public Map<String, Double> getMinResponseTimes() {
        return minResponseTimes;
    }

    public void setMinResponseTimes(Map<String, Double> minResponseTimes) {
        this.minResponseTimes = minResponseTimes;
    }

    public Map<String, HashMap<String, Integer>> getPageErrorCount() {
        return pageErrorCount;
    }

    public void setPageErrorCount(Map<String, HashMap<String, Integer>> pageErrorCount) {
        this.pageErrorCount = pageErrorCount;
    }

    public Map<String, String> getJsonDataByProvider() {
        return jsonDataByProvider;
    }

    public void setJsonDataByProvider(Map<String, String> jsonDataByProvider) {
        this.jsonDataByProvider = jsonDataByProvider;
    }

    public long getTotalHits() {
        return totalHits;
    }

    public void setTotalHits(long totalHits) {
        this.totalHits = totalHits;
    }

    public long getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(long totalPages) {
        this.totalPages = totalPages;
    }

    public long getTotalErrors() {
        return totalErrors;
    }

    public void setTotalErrors(long totalErrors) {
        this.totalErrors = totalErrors;
    }

    public long getTotalIterations() {
        return totalIterations;
    }

    public void setTotalIterations(long totalIterations) {
        this.totalIterations = totalIterations;
    }

    public long getTotalBytesTransferred() {
        return totalBytesTransferred;
    }

    public void setTotalBytesTransferred(long totalBytesTransferred) {
        this.totalBytesTransferred = totalBytesTransferred;
    }

    public long getTotalBytesUploaded() {
        return totalBytesUploaded;
    }

    public void setTotalBytesUploaded(long totalBytesUploaded) {
        this.totalBytesUploaded = totalBytesUploaded;
    }

    public long getTotalBytesDownloaded() {
        return totalBytesDownloaded;
    }

    public void setTotalBytesDownloaded(long totalBytesDownloaded) {
        this.totalBytesDownloaded = totalBytesDownloaded;
    }

    public double getMaxPagesPerSecond() {
        return maxPagesPerSecond;
    }

    public void setMaxPagesPerSecond(double maxPagesPerSecond) {
        this.maxPagesPerSecond = maxPagesPerSecond;
    }

    public double getAvgPagesPerSecond() {
        return avgPagesPerSecond;
    }

    public void setAvgPagesPerSecond(double avgPagesPerSecond) {
        this.avgPagesPerSecond = avgPagesPerSecond;
    }

    public double getMinPagesPerSecond() {
        return minPagesPerSecond;
    }

    public void setMinPagesPerSecond(double minPagesPerSecond) {
        this.minPagesPerSecond = minPagesPerSecond;
    }

    public double getMaxHitsPerSecond() {
        return maxHitsPerSecond;
    }

    public void setMaxHitsPerSecond(double maxHitsPerSecond) {
        this.maxHitsPerSecond = maxHitsPerSecond;
    }

    public double getAvgHitsPerSecond() {
        return avgHitsPerSecond;
    }

    public void setAvgHitsPerSecond(double avgHitsPerSecond) {
        this.avgHitsPerSecond = avgHitsPerSecond;
    }

    public double getMinHitsPerSecond() {
        return minHitsPerSecond;
    }

    public void setMinHitsPerSecond(double minHitsPerSecond) {
        this.minHitsPerSecond = minHitsPerSecond;
    }

    public double getMaxBytesPerSecond() {
        return maxBytesPerSecond;
    }

    public void setMaxBytesPerSecond(double maxBytesPerSecond) {
        this.maxBytesPerSecond = maxBytesPerSecond;
    }

    public double getAvgBytesPerSecond() {
        return avgBytesPerSecond;
    }

    public void setAvgBytesPerSecond(double avgBytesPerSecond) {
        this.avgBytesPerSecond = avgBytesPerSecond;
    }

    public double getMinBytesPerSecond() {
        return minBytesPerSecond;
    }

    public void setMinBytesPerSecond(double minBytesPerSecond) {
        this.minBytesPerSecond = minBytesPerSecond;
    }

    public double getResponseTimeAvg() {
        return responseTimeAvg;
    }

    public void setResponseTimeAvg(double responseTimeAvg) {
        this.responseTimeAvg = responseTimeAvg;
    }

    public double getResponseTimeP90() {
        return responseTimeP90;
    }

    public void setResponseTimeP90(double responseTimeP90) {
        this.responseTimeP90 = responseTimeP90;
    }

    public double getResponseTimeP80() {
        return responseTimeP80;
    }

    public void setResponseTimeP80(double responseTimeP80) {
        this.responseTimeP80 = responseTimeP80;
    }
}
