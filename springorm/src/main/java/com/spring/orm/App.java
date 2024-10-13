package com.spring.orm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.spring.orm.dao.StudentDao;
import com.spring.orm.entities.Student;

public class App {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("config.xml");
        StudentDao studentDao = context.getBean("studentDao", StudentDao.class);

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            System.out.println("PRESS 1 for add new student");
            System.out.println("PRESS 2 for display all students");
            System.out.println("PRESS 3 for get details of a single student");
            System.out.println("PRESS 4 for delete student");
            System.out.println("PRESS 5 for update studentg");
            System.out.println("PRESS 6 for exit");
            try {
                int input = Integer.parseInt(br.readLine());

                switch (input) {
                    case 1:
                        // Add new student
                        System.out.println("Enter user id:");
                        int uId = Integer.parseInt(br.readLine());

                        System.out.println("Enter User Name:");
                        String uName = br.readLine();

                        System.out.println("Enter user city:");
                        String uCity = br.readLine();

                        // Create student object and set values
                        Student s = new Student();
                        s.setStudentId(uId);
                        s.setStudentName(uName);
                        s.setStudentCity(uCity);

                        // Save student object to database
                        int r = studentDao.insert(s);
                        System.out.println(r + " student added");
                        System.out.println("*******************************************");
                        System.out.println();
                        break;

                    case 2:
                        // Display all students
                        List<Student> allStudents = studentDao.getAllstudent();
                        for (Student st : allStudents) {
                            System.out.println("id: " + st.getStudentId());
                            System.out.println("name: " + st.getStudentName());
                            System.out.println("city: " + st.getStudentCity());
                            System.out.println("------------------------------------------");
                        }
                        break;

                    case 3:
                        // Get details of a single student
                        System.out.println("Enter student id to get details:");
                        int studentId = Integer.parseInt(br.readLine());
                        Student student = studentDao.getStudent(studentId);
                        if (student != null) {
                            System.out.println("Student ID: " + student.getStudentId());
                            System.out.println("Student Name: " + student.getStudentName());
                            System.out.println("Student City: " + student.getStudentCity());
                        } else {
                            System.out.println("Student not found with ID: " + studentId);
                        }
                        break;

                    case 4:
                        // Delete student
                        System.out.println("Enter student id to delete:");
                        int deleteId = Integer.parseInt(br.readLine());
                        studentDao.deleteStudent(deleteId);
                        System.out.println("Student with ID " + deleteId + " deleted successfully.");
                        break;

                    case 5:
                        // Update student
                        System.out.println("Enter student id to update:");
                        int updateId = Integer.parseInt(br.readLine());

                        Student existingStudent = studentDao.getStudent(updateId);
                        if (existingStudent != null) {
                            System.out.println("Enter new student name:");
                            String newName = br.readLine();

                            System.out.println("Enter new student city:");
                            String newCity = br.readLine();

                            // Update the existing student details
                            existingStudent.setStudentName(newName);
                            existingStudent.setStudentCity(newCity);

                            // Save the updated student
                            studentDao.updateStudent(existingStudent);
                            System.out.println("Student with ID " + updateId + " updated successfully.");
                        } else {
                            System.out.println("Student not found with ID: " + updateId);
                        }
                        break;

                    case 6:
                        // Exit
                        System.out.println("Exiting...");
                        System.exit(0);
                        break;

                    default:
                        System.out.println("Invalid input! Please enter a valid option.");
                }

            } catch (Exception e) {
                System.out.println("Invalid input, please try again.");
                System.out.println(e.getMessage());
            }
        }
    }
}
