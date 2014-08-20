/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.lang.*;
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
public class TeacherListModelTest {
    
    private TeacherListModel instance;
    private TeacherListItem temp = null;
            
    public TeacherListModelTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        instance = TeacherListModel.getInstance();
        instance.addItem(temp);//In the list we have two items at the moment
        instance.addItem(temp);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of initTempTeacherList method, of class TeacherListModel.
     */
    @Test(expected=NullPointerException.class)
    public void testInitTempTeacherList() throws Exception{
        System.out.println("initTempTeacherList");
        //Can init tempTeacherList but selectedItems does not have any real Teacher object to add yet -> NullPointerException
        instance.initTempTeacherList();
    }
    
    /**
     * Test of addItem method, of class TeacherListModel.
     */
    @Test
    public void testAddItem() {
        System.out.println("addItem");
        int size = instance.getSelectedItem().size();
        instance.addItem(temp);
        instance.addItem(temp);
        assertEquals(size + 2, instance.getSelectedItem().size());
    }

    /**
     * Test of removeItem method, of class TeacherListModel.
     */
    @Test
    public void testRemoveItem() {
        System.out.println("removeItem");
        int size = instance.getSelectedItem().size();
        instance.removeItem(temp);
        instance.removeItem(temp);
        assertEquals(size - 2, instance.getSelectedItem().size());
    }

}