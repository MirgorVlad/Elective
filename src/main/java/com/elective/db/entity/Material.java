package com.elective.db.entity;
/**
 * Material entity
 */
public class Material {
    public static final String LECTION = "lection";
    public static final String VIDEO = "video";
    public static final String ASSIGNMENT = "assignment";
    public static final String SOLUTION = "solution";

    private String name;
    private String description;
    private String type;
    private String path;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
