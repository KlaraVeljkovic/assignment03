package assignment03;

import java.time.LocalTime;
import java.util.List;

public class Tester {
    public static void main(String[] args) {
        // Create a Person
        Person person = new Person("John Doe", "1990-01-01");
        System.out.println("Testing Person class:");
        System.out.println("Name: " + person.getName());
        System.out.println("Date of Birth: " + person.getDateOfBirth());
        person.setStreetAddress("123 Main St");
        person.setCity("Springfield");
        person.setStateAndZip("IL 62701");
        person.setCountry("USA");
        System.out.println("Address: " + person.getStreetAddress() + ", " + person.getCity() + ", " + person.getStateAndZip() + ", " + person.getCountry());

        // Create a ConcreteStudent
        AbstractStudent student = new ConcreteStudent(person);
        System.out.println("\nTesting ConcreteStudent class:");
        student.setUniversity("Example University");
        student.setMajor("Computer Science");
        System.out.println("University: " + student.getUniversity());
        System.out.println("Major: " + student.getMajor());

        // Add a CS542 course decorator to the student
        System.out.println("\nAdding CS542 CourseDecorator:");
        student = new CS542(student);

        // Test getCourses method from CourseDecorator
        System.out.println("Courses enrolled (getCourses):");
        System.out.println(student.getCourses());

        // Test getCourseList method from CourseDecorator
        System.out.println("Courses enrolled (getCourseList):");
        List<String> courses = student.getCourseList();
        for (String course : courses) {
            System.out.println(course);
        }

        // Test timeConflict method in CourseDecorator
        System.out.println("\nTesting timeConflict method:");
        LocalTime testTime = LocalTime.of(13, 15); // During CS542 course
        System.out.println("Time conflict at 13:15: " + student.timeConflict(testTime));

        LocalTime anotherTestTime = LocalTime.of(15, 0); // Outside CS542 time
        System.out.println("Time conflict at 15:00: " + student.timeConflict(anotherTestTime));

        // Test betweenStartAndEnd method in CourseDecorator directly (assume we have access)
        CourseDecorator cs542Decorator = (CourseDecorator) student;
        System.out.println("\nTesting betweenStartAndEnd method directly:");
        System.out.println("Is 13:15 between 1:00pm-2:10pm? " + cs542Decorator.betweenStartAndEnd(testTime));
        System.out.println("Is 15:00 between 1:00pm-2:10pm? " + cs542Decorator.betweenStartAndEnd(anotherTestTime));

        // Test AbstractStudent delegation methods in CourseDecorator
        System.out.println("\nTesting delegation in CourseDecorator:");
        System.out.println("University (from CourseDecorator): " + student.getUniversity());
        System.out.println("Major (from CourseDecorator): " + student.getMajor());

        // Test Person class again through CourseDecorator/ConcreteStudent
        System.out.println("\nTesting Person class through CourseDecorator/ConcreteStudent:");
        System.out.println("Name (through CourseDecorator): " + student.getInnerSelf().getName());
        System.out.println("Date of Birth (through CourseDecorator): " + student.getInnerSelf().getDateOfBirth());
    }
}
