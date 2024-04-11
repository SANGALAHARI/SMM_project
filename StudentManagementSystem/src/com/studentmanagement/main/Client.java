package com.studentmanagement.main;

import com.studentmanagement.dao.StudentDao;
import com.studentmanagement.dao.StudentDaoInterface;
import com.studentmanagement.model.Student;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        StudentDaoInterface dao = new StudentDao();
        System.out.println("Welcome to Student Management application");
        while (true) {
            System.out.println("\n1.Add Student" +
                    "\n2.Show All Students" +
                    "\n3.Get student based on roll number" +
                    "\n4.Delete Student" +
                    "\n5.Update Student" +
                    "\n6.Exit");
            System.out.println("Enter choice:");
            int ch = sc.nextInt();
            sc.nextLine(); // Consume newline character after nextInt()

            switch (ch) {
                case 1:
                    System.out.println("Add Student");
                    System.out.println("Enter student name:");
                    String name = sc.nextLine();
                    System.out.println("Enter student college name:");
                    String clgName = sc.nextLine();
                    System.out.println("Enter city:");
                    String city = sc.nextLine();
                    double percentage = 0.0;

                    boolean validInput = false;
                    while (!validInput) {
                        try {
                            System.out.println("Enter Percentage:");
                            percentage = sc.nextDouble();
                            validInput = true;
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid input! Please enter a valid percentage.");
                            sc.next(); // Consume invalid input
                        }
                    }

                    Student st = new Student(name, clgName, city, percentage);
                    boolean ans = dao.insertStudent(st);
                    if (ans)
                        System.out.println("Record inserted Successfully!!!");
                    else
                        System.out.println("Something went wrong, please try again");

                    break;
                case 2:
                    System.out.println("Show all students");
                    dao.showAllStudent();
                    break;
                case 3:
                    System.out.println("Get student based on roll number");
                    System.out.println("Enter roll number:");
                    int roll = sc.nextInt();
                    sc.nextLine(); // Consume newline character after nextInt()
                    boolean f = dao.showStudentById(roll);
                    if (!f)
                        System.out.println("Student with this id is not available in our system");
                    break;
                case 4:
                    System.out.println("Delete Student");
                    System.out.println("Enter roll number to delete:");
                    int rollnum = sc.nextInt();
                    sc.nextLine(); // Consume newline character after nextInt()
                    boolean ff = dao.delete(rollnum);
                    if (ff)
                        System.out.println("Record deleted successfully...");
                    else
                        System.out.println("Something went wrong");
                    break;
                case 5:
                    System.out.println("Update the student");
                    System.out.println("\n1.Update name\n2.Update college name");
                    System.out.println("Enter your choice:");
                    int choice = sc.nextInt();
                    sc.nextLine(); // Consume newline character after nextInt()
                    if (choice == 1) {
                        System.out.println("Enter roll number:");
                        int rnum = sc.nextInt();
                        sc.nextLine(); // Consume newline character after nextInt()
                        System.out.println("Enter new name:");
                        String sname = sc.nextLine();
                        Student std = new Student();
                        std.setName(sname);
                        boolean flag = dao.update(rnum, sname, choice, std);
                        if (flag)
                            System.out.println("Name updated successfully");
                        else
                            System.out.println("Something went wrong...");
                    }
                    break;
                case 6:
                    System.out.println("Thank You for using Student Management application!");
                    System.exit(0);
                default:
                    System.out.println("Please enter a valid choice.");
            }
        }
    }
}
