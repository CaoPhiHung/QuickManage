/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;
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
public class StudentListModelTest {
    
    private StudentListModel instance;
    private StudentListItem temp = null;
    
    public StudentListModelTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        instance = StudentListModel.getInstance();
        instance.addItem(temp);
        instance.addItem(temp);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of initTempStudentList method, of class StudentListModel.
     */
    @Test(expected=NullPointerException.class)
    public void testInitTempStudentList() {
        System.out.println("initTempStudentList");
        //Can init tempStudentList but selectedItems does not have any real Student object to add yet -> NullPointerException
        instance.initTempStudentList();
    }

    /**
     * Test of addItem method, of class StudentListModel.
     */
    @Test
    public void testAddItem() {
        System.out.println("addItem");
        int size = instance.getSelectedItem().size();
        instance.addItem(temp);
        assertEquals(size + 1, instance.getSelectedItem().size());
    }

    /**
     * Test of removeItem method, of class StudentListModel.
     */
    @Test
    public void testRemoveItem() {
        System.out.println("removeItem");
        int size = instance.getSelectedItem().size();
        instance.removeItem(temp);
        assertEquals(size - 1, instance.getSelectedItem().size());
    }

}