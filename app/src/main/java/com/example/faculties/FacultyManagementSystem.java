package com.example.faculties;

import java.util.ArrayList;

public class FacultyManagementSystem {
    private ArrayList<Faculty> facultyList;

    public FacultyManagementSystem() {
        facultyList = new ArrayList<>();
    }

    public void addFaculty(Faculty faculty) {
        facultyList.add(faculty);
    }

    // You can add more methods for updating, deleting, or searching faculties
}
