import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Students {
    private List<Student> studentList;

    public Students() {
        studentList = new ArrayList<Student>();
    }

    /**
     * Public access to populate student list.
     */
    public void populateStudents() {
        int returnCode = populateStudentssMock();
        if (returnCode > 0) {
            System.err.println("There was a problem populating the list of students. ");
        }
    }

    /**
     * -- OJDBC METHOD STUB EXAMPLE
     * Populate the list of students from the database.
     * return 0 if success 1 if error.
     * 
     * @return returnCode
     */
    private int demoPopulateStudents() {
        int returnCode = 0;
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        Connection conn = null;

        // make the db connection and execute the query
        try {
            conn = DriverManager.getConnection("URL", "USER", "PASSWORD");
            pstmt = conn.prepareStatement(
                    "   SELECT  first_name, "
                            + "         last_name,  "
                            + "         id,         "
                            + "         userid      "
                            + " FROM    student     ");
            rset = pstmt.executeQuery();
            while (rset.next()) {
                Student stu = new Student();
                stu.setFirstName(rset.getString("first_name"));
                stu.setLastName(rset.getString("last_name"));
                stu.setIdNumber(rset.getInt("id"));
                stu.setUserId(rset.getString("userid"));
                stu.populateAdditionalStudentInformation();
                studentList.add(stu);
            }
        }
        // catch SQL errors
        catch (SQLException se) {
            returnCode = 1;
            se.printStackTrace();
        }
        // catch any errors - close the connections
        finally {
            // Close result set
            if (rset != null) {
                try {
                    rset.close();
                } catch (SQLException se) {
                    returnCode = 1;
                }
            }
            // Close prepared statement
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException se) {
                    returnCode = 1;
                }
            }
            // close connection
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException se) {
                    returnCode = 1;
                }
            }
        }

        return returnCode;
    }

    /**
     * Mock populate method for hardcoded results.
     */
    private int populateStudentssMock() {
        int returnCode = 0;
        studentList = new ArrayList<>();
        Student stu;

        stu = new Student();
        stu.setFirstName("Biff");
        stu.setLastName("Tannen");
        stu.setIdNumber(123456);
        stu.setUserId("tannenb");
        stu.populateAdditionalStudentInformation();
        studentList.add(stu);
        stu = new Student();
        stu.setFirstName("James");
        stu.setLastName("Kirk");
        stu.setIdNumber(7891011);
        stu.setUserId("kirkj");
        stu.populateAdditionalStudentInformation();
        studentList.add(stu);
        stu = new Student();
        stu.setFirstName("Marty");
        stu.setLastName("McFly");
        stu.setIdNumber(12131415);
        stu.setUserId("mcflym");
        stu.populateAdditionalStudentInformation();
        studentList.add(stu);
        stu = new Student();
        stu.setFirstName("Ellen");
        stu.setLastName("Ripley");
        stu.setIdNumber(16171819);
        stu.setUserId("ripleye");
        stu.populateAdditionalStudentInformation();
        studentList.add(stu);
        stu = new Student();
        stu.setFirstName("Peggy");
        stu.setLastName("Olson");
        stu.setIdNumber(20212223);
        stu.setUserId("olsonp");
        stu.populateAdditionalStudentInformation();
        studentList.add(stu);

        return returnCode;
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }
}
