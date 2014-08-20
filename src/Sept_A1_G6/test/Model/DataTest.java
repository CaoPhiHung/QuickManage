/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import static Model.Data.managerList;
import View.*;
import View.ControlPanelFormUtilities.*;
import View.UserForm;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Phi Hung
 */
public class DataTest {

    Data data = new Data();
    
    public DataTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        Data.loadAllData();
    }

    @After
    public void tearDown() {
    }

    /**
     * All the IOException have already been caught within the "Write objects"
     * method Therefore, these tests about failing to write objects are skipped.
     */
    /**
     * Test of loadAllData method, of class Data.
     */
    @Test
    public void testLoadAllData() {
        System.out.println("loadAllData");
        /**
         * Receive at least one or two objects after loading Note that all
         * exceptions have been caught within the reading method so "reading
         * objects" errors cannot occur.
         */
        assertTrue(Data.managerList.size() >= 1);
        assertTrue(Data.teacherList.size() >= 1);
        assertTrue(Data.studentList.size() >= 1);
        assertTrue(Data.staffList.size() >= 1);
        assertTrue(Data.classList.size() >= 1);
    }

    /**
     * Test of getCurrentDate method, of class Data.
     */
    @Test
    public void testGetCurrentDate() {
        System.out.println("getCurrentDate");
        String current = "";
        Calendar currentDate = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("dd,MMM yyyy");
        current = formatter.format(currentDate.getTime());
        String result = Data.getCurrentDate();
        assertEquals(current, result);
    }

    /**
     * Test of delete method.
     */
    @Test
    public void testDetele() {
        System.out.println("detele");
        int size = Data.staffList.size();
        ArrayList<String> ids = new ArrayList<String>();
        ids.add("T1");
        try {
            data.delete(ids);
        } catch (NullPointerException e) {
            System.out.println("Cannot show View");
        }
        assertTrue(Data.staffList.size() < size);

        size = Data.managerList.size();
        ids = new ArrayList<String>();
        ids.add("M1");
        try {
            data.delete(ids);
        } catch (NullPointerException e) {
            System.out.println("Cannot show View");
        }
        assertTrue(Data.managerList.size() < size);

        size = Data.classList.size();
        ids = new ArrayList<String>();
        ids.add("C1");
        try {
            data.delete(ids);
        } catch (NullPointerException e) {
            System.out.println("Cannot show View");
        }
        assertTrue(Data.classList.size() < size);

        size = Data.teacherList.size();
        ids = new ArrayList<String>();
        ids.add("1304003");
        try {
            data.delete(ids);
        } catch (NullPointerException e) {
            System.out.println("Cannot show View");
        }
        assertTrue(Data.teacherList.size() < size);

        size = Data.studentList.size();
        ids = new ArrayList<String>();
        ids.add("1304001");
        try {
            data.delete(ids);
        } catch (NullPointerException e) {
            System.out.println("Cannot show View");
        }
        assertTrue(Data.studentList.size() < size);
    }

    /**
     * Test of activateButton method.
     */
    @Test
    public void testActivateButton() {
        System.out.println("activateButton");
        String staffStatus = "";
        String managerStatus = "";
        String teacherStatus = "";
        String studentStatus = "";
        ArrayList<String> ids = new ArrayList<String>();
        ids.add("T1");
        for (int j = 0; j < Data.staffList.size(); j++) {
            if (ids.get(0).equals(Data.staffList.get(j).getID())) {
                staffStatus = Data.staffList.get(j).getStatus();
            }
        }
        try {
            data.activateButton(ids);
        } catch (NullPointerException e) {
            System.out.println("Cannot show View");
        }
        for (int j = 0; j < Data.staffList.size(); j++) {
            if (ids.get(0).equals(Data.staffList.get(j).getID())) {
                assertFalse(Data.staffList.get(j).getStatus().equalsIgnoreCase(staffStatus));
            }
        }
        ids = new ArrayList<String>();
        ids.add("M1");
        for (int j = 0; j < Data.managerList.size(); j++) {
            if (ids.get(0).equals(Data.managerList.get(j).getID())) {
                managerStatus = Data.managerList.get(j).getStatus();
            }
        }
        try {
            data.activateButton(ids);
        } catch (NullPointerException e) {
            System.out.println("Cannot show View");
        }
        for (int j = 0; j < Data.managerList.size(); j++) {
            if (ids.get(0).equals(Data.managerList.get(j).getID())) {
                assertFalse(Data.managerList.get(j).getStatus().equalsIgnoreCase(managerStatus));
            }
        }
        ids = new ArrayList<String>();
        ids.add("1304003");
        for (int j = 0; j < Data.teacherList.size(); j++) {
            if (ids.get(0).equals(Data.teacherList.get(j).getID())) {
                teacherStatus = Data.teacherList.get(j).getStatus();
            }
        }
        try {
            data.activateButton(ids);
        } catch (NullPointerException e) {
            System.out.println("Cannot show View");
        }
        for (int j = 0; j < Data.teacherList.size(); j++) {
            if (ids.get(0).equals(Data.teacherList.get(j).getID())) {
                assertFalse(Data.teacherList.get(j).getStatus().equalsIgnoreCase(teacherStatus));
            }
        }
        ids = new ArrayList<String>();
        ids.add("1304001");
        for (int j = 0; j < Data.studentList.size(); j++) {
            if (ids.get(0).equals(Data.studentList.get(j).getID())) {
                studentStatus = Data.studentList.get(j).getStatus();
            }
        }
        try {
            data.activateButton(ids);
        } catch (NullPointerException e) {
            System.out.println("Cannot show View");
        }
        for (int j = 0; j < Data.studentList.size(); j++) {
            if (ids.get(0).equals(Data.studentList.get(j).getID())) {
                assertFalse(Data.studentList.get(j).getStatus().equalsIgnoreCase(studentStatus));
            }
        }
    }

    /**
     * Test of copyClasses method.
     */
    @Test
    public void testCopyClasses() {
        System.out.println("copyClasses");
        int size = Data.classList.size();
        String id = "C1";
        try {
            data.copyClasses(id);
        } catch (NullPointerException e) {
            System.out.println("Cannot show View");
        }
        assertTrue(size < Data.classList.size());
        assertFalse(Data.classList.get(Data.classList.size() - 1).getId().equalsIgnoreCase(id));
    }

    /**
     * Test of autoGenerateID method.
     */
    @Test
    public void testAutoGenerateID() {
        System.out.println("autoGenerateID");
        TeacherForm uf = TeacherForm.getInstance();
        uf.teacherInitialize();
        data.autoGenerateID(uf);
        if(Integer.parseInt(Data.teacherList.get(Data.teacherList.size() - 1).getID()) > Integer.parseInt(Data.studentList.get(Data.studentList.size() - 1).getID())) {
            assertEquals(Integer.parseInt(Data.teacherList.get(Data.teacherList.size() - 1).getID()) + 1,
                Integer.parseInt(TeacherForm.getInstance().getIdR().getText()));
        } else {
            assertEquals(Integer.parseInt(Data.studentList.get(Data.studentList.size() - 1).getID()) + 1,
                Integer.parseInt(TeacherForm.getInstance().getIdR().getText()));
        }
        
        StudentForm us = StudentForm.getInstance();
        us.studentInitialize();
        data.autoGenerateID(us);
        if(Integer.parseInt(Data.teacherList.get(Data.teacherList.size() - 1).getID()) > Integer.parseInt(Data.studentList.get(Data.studentList.size() - 1).getID())) {
            assertEquals(Integer.parseInt(Data.teacherList.get(Data.teacherList.size() - 1).getID()) + 1,
                Integer.parseInt(StudentForm.getInstance().getIdR().getText()));
        } else {
            assertEquals(Integer.parseInt(Data.studentList.get(Data.studentList.size() - 1).getID()) + 1,
                Integer.parseInt(StudentForm.getInstance().getIdR().getText()));
        }
    }
    
    /**
     * Test of autoGenerateInvoiceID method.
     */
    @Test
    public void testAutoGenerateInvoiceID() {
        DateFormat dateFormat = new SimpleDateFormat("yyMMdd");
        Date date = new Date();
        Data.setCurrentStudentEnroll(Data.studentList.get(0));
        String invoice = Data.autoInvoiceGenerateId();
        assertEquals(invoice, Data.studentList.get(0).getID() +  "-" + dateFormat.format(date).toString());
    }
}