package com.morrison.teamworkprojects.ui;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.morrison.teamworkprojects.R;
import com.morrison.teamworkprojects.model.Project;

import java.util.List;

public class ProjectsList extends ArrayAdapter<Project>
{
    private final Activity context;
    private final List<Project> projects;

    public ProjectsList(final Activity context, final List<Project> projects)
    {
        super(context, R.layout.list_item, projects);
        this.context = context;
        this.projects = projects;
    }

    @Override
    public View getView(final int position, final View view, final ViewGroup parent)
    {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.list_item, null, true);

        Project project = projects.get(position);

        TextView nameTextView = (TextView) rowView.findViewById(R.id.nameTextView);
        nameTextView.setText(project.getName());
        TextView creatorTextView = (TextView) rowView.findViewById(R.id.creatorTextView);
        creatorTextView.setText(project.getCreator());
        TextView descriptionTextView = (TextView) rowView.findViewById(R.id.descriptionTextView);
        descriptionTextView.setText(getTruncatedString(project.getDescription(), 120));

        return rowView;
    }

    private String getTruncatedString(final String string, final int length) {
        if(string.length() <= length) {
            return string;
        }
        String postfix = "...";
        String newString = string.substring(0, length - postfix.length());
        return newString + postfix;
    }

}