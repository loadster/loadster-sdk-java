package loadster.sdk.client;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import loadster.sdk.exceptions.ApiException;
import loadster.sdk.types.ErrorDetail;
import loadster.sdk.types.Reference;
import loadster.sdk.types.TestStatistics;
import loadster.sdk.types.TestStatus;
import org.apache.http.HttpResponse;
import org.apache.http.ProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

/**
 * Simplified client interface for the Workbench API.
 */
public class WorkbenchApiClient {
    private String baseUrl;
    private String apiKey;

    private DefaultHttpClient httpClient = new DefaultHttpClient();
    private Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'hh:mm:ss.SSS'Z'").create();

    public WorkbenchApiClient(String host, int port, String apiKey) {
        this.baseUrl = "http://" + host + ":" + port;
        this.apiKey = apiKey;
    }

    /**
     * Attempts to start a test for a scenario, such as <code>/projects/1/scenarios/3</code>.
     */
    public Reference startTest(String scenarioUri) throws ApiException, IOException, ProtocolException {
        HttpPost post = new HttpPost(baseUrl + scenarioUri + "/tests?apiKey=" + apiKey);
        HttpResponse response = httpClient.execute(post);
        Reader reader = new InputStreamReader(response.getEntity().getContent(), "UTF-8");
        int status = response.getStatusLine().getStatusCode();

        if (status == 201) {
            return gson.fromJson(reader, Reference.class);
        } else if (status == 403) {
            EntityUtils.consumeQuietly(response.getEntity());

            throw new ApiException(gson.fromJson(reader, ErrorDetail.class));
        } else {
            EntityUtils.consumeQuietly(response.getEntity());

            throw new ApiException(response.getStatusLine().toString());
        }
    }

    /**
     * Gets up-to-date status on a test.
     */
    public TestStatus getTestStatus(Reference test) throws ApiException, IOException, ProtocolException {
        HttpGet request = new HttpGet(test.getHref() + "/status?apiKey=" + apiKey);
        HttpResponse response = httpClient.execute(request);
        Reader reader = new InputStreamReader(response.getEntity().getContent(), "UTF-8");
        int status = response.getStatusLine().getStatusCode();

        if (status == 200) {
            return gson.fromJson(reader, TestStatus.class);
        } else {
            EntityUtils.consumeQuietly(response.getEntity());

            throw new ApiException(response.getStatusLine().toString());
        }
    }

    /**
     * Fetches an HTML test report for a test. The test has to be finished or this will fail.
     */
    public InputStream getTestReport(Reference test) throws ApiException, IOException, ProtocolException {
        HttpGet request = new HttpGet(test.getHref() + "/report?apiKey=" + apiKey);
        HttpResponse response = httpClient.execute(request);
        int status = response.getStatusLine().getStatusCode();

        if (status == 200) {
            return response.getEntity().getContent();
        } else {
            EntityUtils.consumeQuietly(response.getEntity());

            throw new ApiException(response.getStatusLine().toString());
        }
    }

    /**
     * Fetches statistics for a test. The test has to be finished or this will fail.
     */
    public TestStatistics getTestStatistics(Reference test) throws ApiException, IOException, ProtocolException {
        HttpGet request = new HttpGet(test.getHref() + "/report?apiKey=" + apiKey);

        request.setHeader("Accept", "application/json");

        HttpResponse response = httpClient.execute(request);
        int status = response.getStatusLine().getStatusCode();

        if (status == 200) {
            return gson.fromJson(new InputStreamReader(response.getEntity().getContent()), TestStatistics.class);
        } else {
            EntityUtils.consumeQuietly(response.getEntity());

            throw new ApiException(response.getStatusLine().toString());
        }
    }
}
