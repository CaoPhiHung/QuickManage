/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import View.ClassForm;
import java.util.ArrayList;
import java.util.Date;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Kelly
 */
public class ClassesTest {
    
    public ClassesTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of enrollStudent method, of class Classes.
     */
    @Test
    public void testEnrollStudent() {
        System.out.println("enrollStudent");
        ArrayList<Student> students = new ArrayList<>();
        Student student = null;
        students.add(student);
        Classes instance = new Classes();
        instance.enrollStudent(students);
    }

}