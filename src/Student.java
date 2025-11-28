import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Student {
    private String firstName;
    private String lastName;
    private String userId;
    private int idNumber;
    private List<Major> majorList;
    private List<Course> courseList;

    public Student() {
        majorList = new ArrayList<Major>();
        courseList = new ArrayList<Course>();
    }

    /**
     * Populate the student's Majors and Courses.
     */
    public void populateAdditionalStudentInformation() {
        int returnCode = populateMajorsMock(idNumber);
        if (returnCode > 0) // failure on major populate - display error message.
        {
            System.err.println("There was a problem loading this student's Majors.");
        }

        returnCode = populateCoursesMock(idNumber);
        if (returnCode > 0) // failure on courses populate - display error message.
        {
            System.err.println("There was a problem loading this student's Courses.");
        }
    }

    /**
     * -- OJDBC METHOD STUB EXAMPLE
     * Populate the list of majors for the provided student ID
     * Return a 0 for success or 1 for failure.
     * 
     * @return int returnCode
     */
    private int demoPopulateStudentMajors() {
        int returnCode = 0;
        Connection conn = null;
        ResultSet rset = null;
        PreparedStatement pstmt = null;
        // make the db connection and set up the SQL statement and execute.
        try {
            conn = DriverManager.getConnection("URL", "USER", "PASSWORD");
            pstmt = conn.prepareStatement(
                    "   SELECT  major_desc,"
                            + "         major_code"
                            + " FROM    majors "
                            + " WHERE   major_code NOT IN ('KSC','MGT','ACC')"
                            + "         AND major_stu_id = ? ");
            pstmt.setInt(1, this.idNumber);
            rset = pstmt.executeQuery();
            while (rset.next()) {
                Major mj = new Major();
                mj.setMajorDesc(rset.getString("major_desc"));
                mj.setMajorCode(rset.getString("major_code"));
                majorList.add(mj);
            }
        } // Catch any errors, then close connections etc.
        catch (SQLException e) {
            returnCode = 1;
            e.printStackTrace();
        } finally {
            // Close the result set
            if (rset != null) {
                try {
                    rset.close();
                } catch (SQLException se) {
                    returnCode = 1;
                }
            }
            // close the preparred statement
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException se) {
                    returnCode = 1;
                }
            }
            // close the DB connection
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException se) {
                    returnCode = 1;
                }
            }
        }
        // return success or failure.
        return returnCode;
    }

    /**
     * Mock populate method for hardcoded major assignement results.
     */
    private int populateMajorsMock(int inIdNumber) {
        int returnCode = 0;

        Major mj;
        if (inIdNumber == 123456) {
            mj = new Major();
            mj.setMajorDesc("Computer Science");
            mj.setMajorCode("CSE");
            majorList.add(mj);
        }
        if (inIdNumber == 7891011) {
            mj = new Major();
            mj.setMajorDesc("Music");
            mj.setMajorCode("MUS");
            majorList.add(mj);

        }
        if (inIdNumber == 12131415) {
            mj = new Major();
            mj.setMajorDesc("Biology");
            mj.setMajorCode("BIO");
            majorList.add(mj);

        }
        if (inIdNumber == 16171819) {
            mj = new Major();
            mj.setMajorDesc("Education");
            mj.setMajorCode("EDU");
            majorList.add(mj);

        }
        if (inIdNumber == 20212223) {
            mj = new Major();
            mj.setMajorDesc("Business");
            mj.setMajorCode("BUS");
            majorList.add(mj);

        }
        return returnCode;
    }

    /**
     * -- OJDBC METHOD STUB EXAMPLE
     * Populate the list of courses for the provided student ID
     * Return a 0 for success or 1 for failure.
     * 
     * @return int returnCode
     */
    private int demoPopulateStudentCourses() {
        int returnCode = 0;
        Connection conn = null;
        ResultSet rset = null;
        PreparedStatement pstmt = null;
        // make the db connection and set up the SQL statement and execute.
        try {
            conn = DriverManager.getConnection("URL", "USER", "PASSWORD");
            pstmt = conn.prepareStatement(
                    "   SELECT  course_title,"
                            + "         course_num, "
                            + "         course_type"
                            + " FROM    courses "
                            + " WHERE   course_stu_id = ? ");
            pstmt.setInt(1, this.idNumber);
            rset = pstmt.executeQuery();
            while (rset.next()) {
                Course crs = new Course();
                crs.setCourseTitle(rset.getString("course_title"));
                crs.setCourseNum(rset.getString("course_num"));
                crs.setCourseType(rset.getString("course_type"));
                courseList.add(crs);
            }
        } // Catch any errors, then close connections etc.
        catch (SQLException e) {
            returnCode = 1;
            e.printStackTrace();
        } finally {
            // Close the result set
            if (rset != null) {
                try {
                    rset.close();
                } catch (SQLException se) {
                    returnCode = 1;
                }
            }
            // close the preparred statement
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException se) {
                    returnCode = 1;
                }
            }
            // close the DB connection
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException se) {
                    returnCode = 1;
                }
            }
        }
        // return success or failure.
        return returnCode;
    }

    /**
     * Mock populate method for hardcoded course assignement results.
     */
    private int populateCoursesMock(int inIdNumber) {
        int returnCode = 0;
        Course crs;
        if (inIdNumber == 123456) {

            crs = new Course();
            crs.setCourseTitle("Chemistry");
            crs.setCourseNum("131");
            crs.setCourseType("Course");
            courseList.add(crs);

            crs = new Course();
            crs.setCourseTitle("Chemistry Lab");
            crs.setCourseNum("131L");
            crs.setCourseType("Lab");
            courseList.add(crs);

            crs = new Course();
            crs.setCourseTitle("Programming I");
            crs.setCourseNum("140");
            crs.setCourseType("Course");
            courseList.add(crs);
        }
        if (inIdNumber == 7891011) {
            crs = new Course();
            crs.setCourseTitle("Programming I Lab");
            crs.setCourseNum("140L");
            crs.setCourseType("Lab");
            courseList.add(crs);

            crs = new Course();
            crs.setCourseTitle("Calculus I");
            crs.setCourseNum("150");
            crs.setCourseType("Course");
            courseList.add(crs);

            crs = new Course();
            crs.setCourseTitle("Music Theory I");
            crs.setCourseNum("160A");
            crs.setCourseType("Course");
            courseList.add(crs);
        }
        if (inIdNumber == 12131415) {
            crs = new Course();
            crs.setCourseTitle("Intro to Psychology");
            crs.setCourseNum("120");
            crs.setCourseType("Course");
            courseList.add(crs);
        }
        if (inIdNumber == 16171819) {
            crs = new Course();
            crs.setCourseTitle("Creative Writing");
            crs.setCourseNum("101A");
            crs.setCourseType("Course");
            courseList.add(crs);

            crs = new Course();
            crs.setCourseTitle("General Biology");
            crs.setCourseNum("110");
            crs.setCourseType("Course");
            courseList.add(crs);

            crs = new Course();
            crs.setCourseTitle("General Biology Lab");
            crs.setCourseNum("110L");
            crs.setCourseType("Lab");
            courseList.add(crs);

        }
        if (inIdNumber == 20212223) {
            crs = new Course();
            crs.setCourseTitle("U.S. History");
            crs.setCourseNum("210B");
            crs.setCourseType("Course");
            courseList.add(crs);

            crs = new Course();
            crs.setCourseTitle("Physics I");
            crs.setCourseNum("230");
            crs.setCourseType("Course");
            courseList.add(crs);

            crs = new Course();
            crs.setCourseTitle("Physics I Lab");
            crs.setCourseNum("230L");
            crs.setCourseType("Lab");
            courseList.add(crs);
        }
        return returnCode;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(int idNumber) {
        this.idNumber = idNumber;
    }

    public List<Major> getMajorList() {
        return majorList;
    }

    public void setMajorList(List<Major> majorList) {
        this.majorList = majorList;
    }

    public List<Course> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<Course> courseList) {
        this.courseList = courseList;
    }

}
