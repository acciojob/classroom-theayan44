package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class StudentRepository {
    Map<String, Student> studentDb = new HashMap<>();

    Map<String, Teacher> teacherDb = new HashMap<>();

    Map<String, List<String>> teacherStudentDb = new HashMap<>();

    public void addStudent(Student student) {
        studentDb.put(student.getName(), student);
    }

    public void addTeacher(Teacher teacher) {
        teacherDb.put(teacher.getName(), teacher);
    }

    public void addStudentTeacherPair(String student, String teacher) {
        List<String> studentsList = new ArrayList<>();
        if(teacherStudentDb.containsKey(teacher))
            studentsList = teacherStudentDb.get(teacher);
        if(!studentsList.contains(student))
            studentsList.add(student);
        teacherStudentDb.put(teacher, studentsList);
    }

    public Student getStudentByName(String name) {
        if(!studentDb.containsKey(name))
            return null;
        return studentDb.get(name);
    }

    public Teacher getTeacherByName(String name) {
        if(!teacherDb.containsKey(name))
            return null;
        return teacherDb.get(name);
    }

    public List<String> getStudentsByTeacherName(String teacher) {
        if(!teacherDb.containsKey(teacher) || !teacherStudentDb.containsKey(teacher))
            return null;
        return teacherStudentDb.get(teacher);
    }

    public List<String> getAllStudents() {
        return new ArrayList<>(studentDb.keySet());
    }

    public void deleteTeacherByName(String teacher) {
        teacherDb.remove(teacher);
        teacherStudentDb.remove(teacher);
    }

    public void deleteAllTeachers() {
        teacherStudentDb.clear();
        teacherDb.clear();
    }
}
