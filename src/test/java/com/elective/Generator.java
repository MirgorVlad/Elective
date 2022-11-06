package com.elective;

import com.elective.db.dao.UserDAO;
import com.elective.db.entity.Course;
import com.elective.db.entity.Journal;
import com.elective.db.entity.User;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Generator {
    Random rand = new Random();

    public User createRandomUser(String role) {
        User user = new User();
        user.setId(rand.nextInt(100));
        user.setFirstName(generateString(10));
        user.setLastName(generateString(10));
        user.setPassword(generateString(10));
        user.setEmail(user.getFirstName() + "@mail.com");
        user.setRole(role != null ? role : rand.nextBoolean() ? UserDAO.STUDENT_ROLE : UserDAO.TEACHER_ROLE);

        return user;
    }

    public Course createRandomCourse(User teacher) {
        Course course = new Course();
        course.setId(rand.nextInt(100));
        course.setName(generateString(10));
        course.setDescription(generateString(20));
        course.setTopic("topic" + rand.nextInt(10));
        course.setStartDate(new Date(rand.nextLong(12345)));
        course.setFinishDate(new Date(course.getStartDate().getTime() + rand.nextLong(12345)));

        if(teacher != null)
            course.setTeacher(teacher);
        else
            course.setTeacher(createRandomUser(UserDAO.TEACHER_ROLE));

        return course;
    }

    public Course createRandomCourse(User teacher, Date start, Date finish) {
        Course course = new Course();
        course.setId(rand.nextInt(100));
        course.setName(generateString(10));
        course.setDescription(generateString(20));
        course.setTopic("topic" + rand.nextInt(10));
        course.setStartDate(start);
        course.setFinishDate(finish);

        if(teacher != null)
            course.setTeacher(teacher);
        else
            course.setTeacher(createRandomUser(UserDAO.TEACHER_ROLE));

        return course;
    }

    public Journal createRandomJournal(){
        Journal journal = new Journal();

        journal.setCourse(createRandomCourse(null));
        journal.setStudent(createRandomUser(UserDAO.STUDENT_ROLE));
        journal.setGrade(rand.nextInt(10));
        journal.setDate(new Date(rand.nextLong(12345)));

        return journal;
    }

    public List<Course> generateCourseList(int length){
        List<Course> courseList = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            courseList.add(createRandomCourse(null));
        }
        return courseList;
    }

    public List<User> generateUserList(int length){
        List<User> userList = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            userList.add(createRandomUser(null));
        }
        return userList;
    }

    public List<User> generateUserList(int length, String role){
        List<User> userList = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            userList.add(createRandomUser(role));
        }
        return userList;
    }


    public String generateString(int targetStringLength) {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        Random random = new Random();

        return random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }
}
