package com.morrison.teamworkprojects.model;

public class Project {

    private final String id;
    private final String name;
    private final String creator;
    private final String startDate;
    private final String endDate;
    private final String description;

    public Project(final String id, final String name, final String creator, final String startDate, final String endDate, final String description) {
        this.id = id;
        this.name = name;
        this.creator = creator;
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCreator() {
        return creator;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public String getDescription() {
        return description;
    }

}
