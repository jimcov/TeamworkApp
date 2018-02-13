package com.morrison.teamworkprojects.resource;

import com.morrison.teamworkprojects.model.Project;

import java.util.List;

public enum ProjectsProvider
{
	INSTANCE;

	private List<Project> allProjects;
	private Project selectedProject;

	public List<Project> getAllProjects()
	{
		return allProjects;
	}

	public void setAllProjects(final List<Project> allProjects)
	{
		this.allProjects = allProjects;
	}

	public Project getSelectedProject()
	{
		return selectedProject;
	}

	public void setSelectedProject(final Project selectedProject)
	{
		this.selectedProject = selectedProject;
	}

}
