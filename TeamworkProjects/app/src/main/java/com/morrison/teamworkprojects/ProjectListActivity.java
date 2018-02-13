package com.morrison.teamworkprojects;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.morrison.teamworkprojects.model.Project;
import com.morrison.teamworkprojects.resource.AppConstants;
import com.morrison.teamworkprojects.resource.JSONProjectsParser;
import com.morrison.teamworkprojects.resource.ProjectsProvider;
import com.morrison.teamworkprojects.ui.ProjectsList;

import org.json.JSONException;

import java.io.IOException;
import java.net.URL;
import java.util.List;

/**
 * Jim Morrison
 */
public class ProjectListActivity extends AppCompatActivity
{
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        setContentView(R.layout.activity_project_list);

        final Handler handler = new Handler();

        new Thread(new Runnable()
        {
            public void run()
            {
                try
                {
                    final List<Project> projects = getProjects();
                    handler.post(new Runnable()
                    {
                        public void run()
                        {
                            ProjectsProvider.INSTANCE.setAllProjects(projects);
                            updateListForProjects();
                        }
                    });
                }
                catch (final Exception e)
                {
                    e.printStackTrace();
                    handler.post(new Runnable()
                    {
                        public void run()
                        {
                            handleFatalException(e);
                        }
                    });
                }
            }
        }).start();

        listView = (ListView) findViewById(R.id.listView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                Project project = (Project) parent.getItemAtPosition(position);

                ProjectsProvider.INSTANCE.setSelectedProject(project);

                startActivity(new Intent(ProjectListActivity.this, ProjectDetailActivity.class));
            }
        });

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#82C882")));
        String title = AppConstants.APP_NAME;
        SpannableString string = new SpannableString(title);
        string.setSpan(new ForegroundColorSpan(Color.BLACK), 0, title.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        getSupportActionBar().setTitle(string);
    }

    // should show a nicer, more informative error message and maybe give the user a chance to retry.
    private void handleFatalException(Exception ex)
    {
        Toast toast = Toast.makeText(getApplicationContext(), "Error: App will close", Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();

        // close the app after 5 seconds
        new Handler().postDelayed(new Runnable()
        {
            public void run()
            {
                System.exit(0);
            }
        }, 5000);
    }

    private void updateListForProjects()
    {
        listView.setAdapter(new ProjectsList(ProjectListActivity.this, ProjectsProvider.INSTANCE.getAllProjects()));
    }

    private List<Project> getProjects() throws IOException, JSONException
    {
        return new JSONProjectsParser().readProjects(new URL(AppConstants.PROJECT_DATA_URL_STRING), getAssets());
    }

}
