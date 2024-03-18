package com.example.faculties;

public class Faculty {
    private String name;
    private String contactDetails;
    private String department;

    // Constructor
    public Faculty(String name, String contactDetails, String department) {
        this.name = name;
        this.contactDetails = contactDetails;
        this.department = department;
    }

    // Getters and setters
    // (You can generate them automatically in many IDEs)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactDetails() {
        return contactDetails;
    }

    public void setContactDetails(String contactDetails) {
        this.contactDetails = contactDetails;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
