package com.morrison.teamworkprojects.resource;

import android.content.res.AssetManager;

import com.morrison.teamworkprojects.model.Project;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class JSONProjectsParser {

    public List<Project> readProjects(final URL projectDataURL, final AssetManager assets) throws IOException, JSONException {
        List<Project> projectsList = new ArrayList<>();

        // Need to get the live data from the server...
        // JSONArray projectsArray = new JSONArray(new JSONObject(getStringFromURL(projectDataURL)).getString("projects"));

        // but ran out of time, so instead I read a local copy
        JSONArray projectsArray = new JSONArray(new JSONObject(getLocalJSONString(assets)).getString("projects"));

        for (int i = 0; i < projectsArray.length(); i++) {
            projectsList.add(readProject(projectsArray.getJSONObject(i)));
        }

        return projectsList;
    }

    private Project readProject(final JSONObject projectJSONObject) throws JSONException {
        String id = projectJSONObject.getString("id");
        String name = projectJSONObject.getString("name");
        String creator = projectJSONObject.getJSONObject("company").getString("name");
        String startDate = projectJSONObject.getString("startDate");
        String endDate = projectJSONObject.getString("endDate");
        String description = projectJSONObject.getString("description");

        return new Project(id, name, creator, startDate, endDate, description);
    }

    private String getStringFromURL(URL url) throws IOException {
        StringBuilder builder = new StringBuilder();

        URLConnection urlConnection = url.openConnection();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            builder.append(line + "\n");
        }
        bufferedReader.close();

        return builder.toString();
    }

    public String getLocalJSONString(final AssetManager assets) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(assets.open("projects.json")));
        StringBuilder builder = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null)
        {
            builder.append(line);
            builder.append('\n');
        }

        builder.append('\n');
        return builder.toString();
    }

}
