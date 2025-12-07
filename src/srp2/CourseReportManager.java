package srp2;

import java.util.List;

// Responsible for loading data
class CourseDataLoader {
    public void loadData(String courseId) {
        System.out.println("Loading students and grades for course " + courseId + "...");
    }
    public List<String> getStudents() { return List.of("An", "Binh", "Chi"); }
    public List<Integer> getGrades() { return List.of(9, 7, 8); }
}

// Responsible for logic/calculations
class GradeCalculator {
    public double calculateAverage(List<Integer> grades) {
        double total = 0;
        for (int g : grades) total += g;
        return total / grades.size();
    }
}

// Responsible for formatting the output
class ReportFormatter {
    public String formatReport(String courseId, List<String> students, List<Integer> grades, double avg) {
        StringBuilder report = new StringBuilder();
        report.append("Course ID: ").append(courseId).append("\n");
        report.append("Students and grades:\n");
        for (int i = 0; i < students.size(); i++) {
            report.append("-").append(students.get(i)).append(": ").append(grades.get(i)).append("\n");
        }
        report.append("Average grade: ").append(avg).append("\n");
        return report.toString();
    }
}

// Responsible for Persistence
class ReportSaver {
    public void saveToFile(String reportContent) {
        System.out.println("Saving report.txt ...");
    }
}

// Responsible for Notifications
class TeacherNotifier {
    public void sendEmail(String reportContent) {
        System.out.println("Sending email to teacher with report attached...");
        System.out.println(reportContent);
    }
}

// Coordinator Class
public class CourseReportManager {
    public void generateReport(String courseId) {
        CourseDataLoader loader = new CourseDataLoader();
        loader.loadData(courseId);

        GradeCalculator calculator = new GradeCalculator();
        double avg = calculator.calculateAverage(loader.getGrades());

        ReportFormatter formatter = new ReportFormatter();
        String report = formatter.formatReport(courseId, loader.getStudents(), loader.getGrades(), avg);

        ReportSaver saver = new ReportSaver();
        saver.saveToFile(report);

        TeacherNotifier notifier = new TeacherNotifier();
        notifier.sendEmail(report);
    }

    public static void main(String[] args) {
        new CourseReportManager().generateReport("SE123");
        
    }
}