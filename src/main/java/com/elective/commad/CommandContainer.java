package com.elective.commad;

import java.util.HashMap;
import java.util.Map;

public class CommandContainer {

    private CommandContainer(){
    }

    private static final Map<String, Command> commands;

    static {
        commands = new HashMap<>();
        commands.put("login", new LoginCommand());
        commands.put("registr", new RegistrCommand());
        commands.put("createCourse", new CreateCourseCommand());
        commands.put("manageCourses", new ViewAllCoursesCommand());
        commands.put("viewCoursesList", new ViewAllCoursesCommand());
        commands.put("deleteCourse", new DeleteCourseCommand());
        commands.put("viewCourse", new ViewCourseCommand());
        commands.put("updateCourse", new UpdateCourseCommand());
        commands.put("joinToCourse", new JoinToCourseCommand());
        commands.put("unfollowCourse", new UnfollowCourseCommand());
        commands.put("showJournal", new ShowJournalCommand());
        commands.put("viewStudentAvailableCourses", new ViewStudentAvailableCoursesCommand());
        commands.put("viewTeacherAvailableCourses", new ViewTeacherAvailableCoursesCommand());
        commands.put("showStudentsInCourse", new ShowStudentsInCourseCommand());
        commands.put("viewProfile", new ViewProfileCommand());
        commands.put("editJournal", new EditJournalCommand());
        commands.put("sortCourses", new SortCoursesCommand());
        commands.put("selectCourses", new SelectCoursesCommand());
    }
    public static Command getCommand(String commandName) {
        return commands.get(commandName);
    }
}
