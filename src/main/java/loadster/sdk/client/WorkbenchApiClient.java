package loadster.sdk.client;

import com.google.gson.*;
import loadster.sdk.types.ErrorDetail;
import loadster.sdk.types.Reference;
import loadster.sdk.types.TestStatus;
import loadster.sdk.exceptions.ApiException;
import org.apache.http.HttpResponse;
import org.apache.http.ProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.Date;

/**
 * Simplified client interface for the Workbench API.
 */
public class WorkbenchApiClient {
    private String baseUrl;
    private String apiKey;

    private DefaultHttpClient httpClient = new DefaultHttpClient();
    private Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'kk:mm:ss.S'Z'").registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
        public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            return new Date(json.getAsJsonPrimitive().getAsLong()); 
        } 
    }).create();

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
            throw new ApiException(gson.fromJson(reader, ErrorDetail.class));
        } else {
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
            throw new ApiException(response.getStatusLine().toString());
        }
    }
}
