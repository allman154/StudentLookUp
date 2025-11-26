public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Populate a list of students, with their majorsvand their courses. ");

        // Create the Student class and populate the full list of students and their
        // details.
        Students students = new Students();
        students.populateStudents();

        // LOOP through the students and view their details - loop through child lists
        // to display those as well.
        System.out.println("---- STUDENT LIST ----\n");
        for (Student st : students.getStudentList()) {
            System.out.println("\n-STUDENT-");
            System.out.println("Name: " + st.getFirstName() + " " + st.getLastName());
            System.out.println("Student ID: " + st.getIdNumber());
            System.out.println("User ID: " + st.getUserId());
            System.out.println("-MAJORS-");
            for (Major mj : st.getMajorList()) {
                System.out.println("  Title: " + mj.getMajorDesc());
            }
            System.out.println("-COURSES-");
            int cnum = 1;
            for (Course crs : st.getCourseList()) {
                System.out.println("Course " + cnum);
                System.out.println("  Title: " + crs.getCourseTitle());
                System.out.println("  Number: " + crs.getCourseNum());
                System.out.println("  Type: " + crs.getCourseType());
                cnum++;
            }
        }
    }
}
