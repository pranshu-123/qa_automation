package com.qa.utils;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.*;

/**
 * @author Ankur Jaiswal
 * This calls contains all browser network tab related methods
 */
public class NetworkManager {
    //http://tnode46.unraveldata.com:3000/api/v1/clusters/default/resources/cpu/allocated

    /**
     * @param driver WebDriver instance
     * @return logEntries from network tab
     */
    public static LogEntries getNetworkLogEntries(WebDriver driver) {
        LogEntries les = driver.manage().logs().get(LogType.PERFORMANCE);
        return les;
    }

    /**
     * @param driver WebDriver instance
     * @param url Url which should be present in network tab
     * @return list of request headers which has the matching url
     */
    public static List<JsonObject> getLogBasedForURLMatch(WebDriver driver, String url) {
        LogEntries logEntries = getNetworkLogEntries(driver);
        List<JsonObject> requestHeaders = new ArrayList<JsonObject>();
        for (LogEntry logEntry : logEntries) {
            JsonParser parser = new JsonParser();
            JsonElement jsonTree = parser.parse(logEntry.getMessage());
            if (jsonTree.isJsonObject()) {
                JsonObject jsonObject = jsonTree.getAsJsonObject();
                try {
                    String urlRequest = jsonObject.get("message").getAsJsonObject().get("params")
                            .getAsJsonObject().get("request").getAsJsonObject()
                            .get("url").getAsString();
                    if (urlRequest.contains(url)) {
                        requestHeaders.add(jsonObject);
                    }
                } catch (NullPointerException npe) {
                }
            }
        }
        return requestHeaders;
    }

    /**
     * This method check whether the request is new request or old request
     * based on the timestamp of request
     * @param jsonObject - RequestObject of network tab
     * @param timestamp - Timestamp to which request timestamp should be compared
     * @return true or false based on request timestamp is old or new.
     */
    public static boolean isNewRequest(JsonObject jsonObject, long timestamp) {
        long requestTime = jsonObject.get("message").getAsJsonObject().get("params")
          .getAsJsonObject().get("wallTime").getAsLong() * 1000;
       if (DateUtils.compareEpochDates(requestTime, timestamp) > -1) {
           return true;
       } else {
           return false;
       }
    }

    /**
     * Method to parse the url request then find the parameter and its value and return the
     * value
     * @param request - Network Json request
     * @param param - Parameter field present in parameter
     * @return parameter value
     */
    public static String getParameterValueFrom(JsonObject request, String param) {
        String paramValue = "";
        String urlRequest = request.get("message").getAsJsonObject().get("params")
          .getAsJsonObject().get("request").getAsJsonObject()
          .get("url").getAsString();
        try {
            URL uri = new URL(urlRequest);
            Map<String, String> query_pairs = new LinkedHashMap<String, String>();
            String query = uri.getQuery();
            String[] pairs = query.split("&");
            for (String pair : pairs) {
                int idx = pair.indexOf("=");
                query_pairs.put(URLDecoder.decode(pair.substring(0, idx), "UTF-8"), URLDecoder.decode(pair.substring(idx + 1), "UTF-8"));
            }
            paramValue = query_pairs.get(param);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return paramValue;
    }
}
