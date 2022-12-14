package com.elective.command;

import java.util.HashMap;
import java.util.Map;

/**
 * Container that stores command name and appropriate command implementation
 */

public class CommandContainer {

    private CommandContainer(){
    }

    private static final Map<String, Command> commands;

    static {
        commands = new HashMap<>();
        commands.put("login", new LoginCommand());
        commands.put("logout", new LogoutCommand());
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
        commands.put("viewAllUsers", new ViewAllUsersCommand());
        commands.put("finalTest", new FinalTestCommand());
        commands.put("showMaterials", new ShowMaterialsCommand());
        commands.put("addMaterials", new UploadMaterialToCourseCommand());
        commands.put("viewMaterial", new ViewMaterialCommand());
        commands.put("downloadMaterial", new DownloadMaterialCommand());
        commands.put("deleteMaterial", new DeleteMaterialCommand());
    }
    public static Command getCommand(String commandName) {
        return commands.get(commandName);
    }
}
