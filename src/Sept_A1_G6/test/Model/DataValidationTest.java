/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import com.toedter.calendar.JCalendar;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author minh
 */
public class DataValidationTest {
    
    public DataValidationTest() {
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

    @Test
    public void testCheckRegexUserName() {
        
        // case input is empty
        String testCase1 = "";
        // case input special chars
        String testCase2 = "#$%@^";
        // case input more than max length
        String testCase3 = "q3Rera1b2E3d4e5iefjncwerew";
        // case input less than min length
        String testCase4 = "a1cd";
        // case have right input
        String testCase5 = "abd21erRed";
        
        DataValidation instance = new DataValidation();
        boolean expResult1 = false;
        boolean expResult2 = false;
        boolean expResult3 = false;
        boolean expResult4 = false;
        boolean expResult5 = true;
        boolean result1 = instance.checkRegexUserName(testCase1);
        boolean result2 = instance.checkRegexUserName(testCase2);
        boolean result3 = instance.checkRegexUserName(testCase3);
        boolean result4 = instance.checkRegexUserName(testCase4);
        boolean result5 = instance.checkRegexUserName(testCase5);
        assertEquals(expResult1, result1);
        assertEquals(expResult2, result2);
        assertEquals(expResult3, result3);
        assertEquals(expResult4, result4);
        assertEquals(expResult5, result5);
    }

    @Test
    public void testCheckUsernameExistence() {
        
        String username = "SomeThingThatIsNotExistedYet";
        DataValidation instance = new DataValidation();
        boolean expResult = true;
        boolean result = instance.checkUsernameExistence(username);
        assertEquals(expResult, result);
    }

    @Test
    public void testCheckName() {
        
       // a name start with space
        String testCase1 = " ";
       // a name with nothing 
        String testCase2 = "";
        
        DataValidation instance = new DataValidation();
        
        boolean expResult1 = false;
        boolean expResult2 = false;
        boolean result1 = instance.checkName(testCase1);
        boolean result2 = instance.checkName(testCase2);
        assertEquals(expResult1, result1);
        assertEquals(expResult2, result2);
    }

    @Test
    public void testCheckHomePhoneNumber() {
        
        // phone number is empty
        String testCase1 = "";
        // phone number does not have - between
        String testCase2 = "1234567";
        // phone number contains less than 4 number at the first part
        String testCase3 = "123-1234";
        // phone number has less than 4 number in the second part
        String testCase4 = "1234-123";
        // phone number has more than 4 numbers in the first part
        String testCase5 = "12345-1234";
        // phone number has more than 4 numbers in the second part
        String testCase6 = "1234-12345";
        // phone number has less then 4 number in the first and 4 in second part
        String testCase7 = "123-123";
        // phone number has the right format
        String testCase8 = "1234-1234";
        
        DataValidation instance = new DataValidation();
        boolean expResult1 = false;
        boolean expResult2 = false;
        boolean expResult3 = false;
        boolean expResult4 = false;
        boolean expResult5 = false;
        boolean expResult6 = false;
        boolean expResult7 = false;
        boolean expResult8 = true;
        boolean result1 = instance.checkHomePhoneNumber(testCase1);
        boolean result2 = instance.checkHomePhoneNumber(testCase2);
        boolean result3 = instance.checkHomePhoneNumber(testCase3);
        boolean result4 = instance.checkHomePhoneNumber(testCase4);
        boolean result5 = instance.checkHomePhoneNumber(testCase5);
        boolean result6 = instance.checkHomePhoneNumber(testCase6);
        boolean result7 = instance.checkHomePhoneNumber(testCase7);
        boolean result8 = instance.checkHomePhoneNumber(testCase8);
        assertEquals(expResult1, result1);
        assertEquals(expResult2, result2);
        assertEquals(expResult3, result3);
        assertEquals(expResult4, result4);
        assertEquals(expResult5, result5);
        assertEquals(expResult6, result6);
        assertEquals(expResult7, result7);
        assertEquals(expResult8, result8);
    }

    @Test
    public void testCheckCellPhoneNumber() {
        
        // phone number is empty
        String testCase1 = "";
        // phone number does not have - between
        String testCase2 = "12345678";
        // phone number contains less than 4 number at the first part
        String testCase3 = "12-1234";
        // phone number has less than 4 number in the second part
        String testCase4 = "1234-123";
        // phone number has less then 4 number in the first and second part
        String testCase5 = "123-123";
        // phone number has more than 3 numbers in the first part
        String testCase6 = "1234-1234";
        // phone number has more than 4 numbers in the second part
        String testCase7 = "123-12345";
        // phone number has the right format
        String testCase8 = "123-1234";
        DataValidation instance = new DataValidation();
        boolean expResult1 = false;
        boolean expResult2 = false;
        boolean expResult3 = false;
        boolean expResult4 = false;
        boolean expResult5 = false;
        boolean expResult6 = false;
        boolean expResult7 = false;
        boolean expResult8 = true;
        boolean result1 = instance.checkCellPhoneNumber(testCase1);
        boolean result2 = instance.checkCellPhoneNumber(testCase2);
        boolean result3 = instance.checkCellPhoneNumber(testCase3);
        boolean result4 = instance.checkCellPhoneNumber(testCase4);
        boolean result5 = instance.checkCellPhoneNumber(testCase5);
        boolean result6 = instance.checkCellPhoneNumber(testCase6);
        boolean result7 = instance.checkCellPhoneNumber(testCase7);
        boolean result8 = instance.checkCellPhoneNumber(testCase8);
        assertEquals(expResult1, result1);
        assertEquals(expResult2, result2);
        assertEquals(expResult3, result3);
        assertEquals(expResult4, result4);
        assertEquals(expResult5, result5);
        assertEquals(expResult6, result6);
        assertEquals(expResult7, result7);
        assertEquals(expResult8, result8);
    }

    @Test
    public void testCheckEmail() {
        // email is empty
        String testCase1 = "";
        // email misses @
        String testCase2 = "meemail.com";
        // email misses .
        String testCase3 = "me@emailcom";
        // email misses @ and .
        String testCase4 = "meemailcom";
        // email in the right format
        String testCase5 = "me@email.com";
        // email in the right format
        String testCase6 = "me@email.edu.com";
        // email in the right format
        String testCase7 = "me@email.edu.com.vn";
        
        DataValidation instance = new DataValidation();
        boolean expResult1 = false;
        boolean expResult2 = false;
        boolean expResult3 = false;
        boolean expResult4 = false;
        boolean expResult5 = true;
        boolean expResult6 = true;
        boolean expResult7 = true;
        boolean result1 = instance.checkEmail(testCase1);
        boolean result2 = instance.checkEmail(testCase2);
        boolean result3 = instance.checkEmail(testCase3);
        boolean result4 = instance.checkEmail(testCase4);
        boolean result5 = instance.checkEmail(testCase5);
        boolean result6 = instance.checkEmail(testCase6);
        boolean result7 = instance.checkEmail(testCase7);
        assertEquals(expResult1, result1);
        assertEquals(expResult2, result2);
        assertEquals(expResult3, result3);
        assertEquals(expResult4, result4);
        assertEquals(expResult5, result5);
        assertEquals(expResult6, result6);
        assertEquals(expResult7, result7);
    }

    @Test
    public void testCheckAddress() {
        // address is empty
        String testCase1 = "";
        // address contain more than 76 chars
        String testCase2 = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
                + "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
        // address in the right format
        String testCase3 = "123 Abc Street, Bcd ward, Efg district, xyz City ";
        DataValidation instance = new DataValidation();
        boolean expResult1 = false;
        boolean expResult2 = false;
        boolean expResult3 = true;
        boolean result1 = instance.checkAddress(testCase1);
        boolean result2 = instance.checkAddress(testCase2);
        boolean result3 = instance.checkAddress(testCase3);
        assertEquals(expResult1, result1);
        assertEquals(expResult2, result2);
        assertEquals(expResult3, result3);
    }

    @Test
    public void testCheckPassword() {
        
        // password is null
        char[] testCase1 = null;
        // pasword is less than 8 chars
        char[] testCase2 = {'1','2','3','4','5','6','7',};
        // pasword contains only numbers
        char[] testCase3 = {'1','2','3','4','5','6','7','8'};
        // password contains only chars
        char[] testCase4 = {'a','b','c','d','e','f','g','h'};
        // password contains only special chars
        char[] testCase5 = {'!','@','#','$','%','^','&','*'};
        // password conatins only upper case chars
        char[] testCase6 = {'A','B','C','D','E','F','G','H',};
        // password does not have a number
        char[] testCase7 = {'a','B','c','#','e','F','g','H'};
        // password does not have a upper case char
        char[] testCase8 = {'1','b','c','&','e','f','#','h'};
        // password does not have a special char
        char[] testCase9 = {'A','B','c','1','e','3','g','h'};
        // password does not have a lower case char
        char[] testCase10 = {'A','B','$','D','*','3','1','H'};
        // password has right format
        char[] testCase11 = {'D','a','o','t','i','e','n','1','!'};
        
        DataValidation instance = new DataValidation();
        boolean expResult1 = false;
        boolean expResult2 = false;
        boolean expResult3 = false;
        boolean expResult4 = false;
        boolean expResult5 = false;
        boolean expResult6 = false;
        boolean expResult7 = false;
        boolean expResult8 = false;
        boolean expResult9 = false;
        boolean expResult10 = false;
        boolean expResult11 = true;
        boolean result1 = instance.checkPassword(testCase1);
        boolean result2 = instance.checkPassword(testCase2);
        boolean result3 = instance.checkPassword(testCase3);
        boolean result4 = instance.checkPassword(testCase4);
        boolean result5 = instance.checkPassword(testCase5);
        boolean result6 = instance.checkPassword(testCase6);
        boolean result7 = instance.checkPassword(testCase7);
        boolean result8 = instance.checkPassword(testCase8);
        boolean result9 = instance.checkPassword(testCase9);
        boolean result10 = instance.checkPassword(testCase10);
        boolean result11= instance.checkPassword(testCase11);
        assertEquals(expResult1, result1);
        assertEquals(expResult2, result2);
        assertEquals(expResult3, result3);
        assertEquals(expResult4, result4);
        assertEquals(expResult5, result5);
        assertEquals(expResult6, result6);
        assertEquals(expResult7, result7);
        assertEquals(expResult8, result8);
        assertEquals(expResult9, result9);
        assertEquals(expResult10, result10);
        assertEquals(expResult11, result11);
    }

    @Test
    public void testCheckRetypePassword() {
        // test case 1: 2 passwords do not match
        char[] p1 = {'D','a','o','t','i','e','n','1','!'};
        char[] p2 = {'D','a','o','t','i','e','n','2','@'};
        // test case 2: 2 passwords match
        char[] p3 = {'D','a','o','t','i','e','n','1','!'};
        char[] p4 = {'D','a','o','t','i','e','n','1','!'};
        DataValidation instance = new DataValidation();
        boolean expResult1 = false;
        boolean expResult2 = true;
        boolean result1 = instance.checkRetypePassword(p1, p2);
        boolean result2 = instance.checkRetypePassword(p3, p4);
        assertEquals(expResult1, result1);
        assertEquals(expResult2, result2);
    }

    @Test
    public void testCheckTuitionFee() {
        // tuition fee is empty
        String testCase1 = "";
        // tuition fee contains letters
        String testCase2 = "102074a8B";
        // tuition fee has the right format
        String testCase3 = "10000000";
        DataValidation instance = new DataValidation();
        boolean expResult1 = false;
        boolean expResult2 = false;
        boolean expResult3 = true;
        boolean result1 = instance.checkTuitionFee(testCase1);
        boolean result2 = instance.checkTuitionFee(testCase2);
        boolean result3 = instance.checkTuitionFee(testCase3);
        assertEquals(expResult1, result1);
        assertEquals(expResult2, result2);
        assertEquals(expResult3, result3);
    }

    @Test
    public void testProcessTimeString() {
        // time is at hh:00
        String testCase1 = "08:00";
        // time is at hh:30
        String testCase2 = "08:30";
        
        DataValidation instance = new DataValidation();
        double expResult1 = 8.0;
        double expResult2 = 8.5;
        double result1 = instance.processTimeString(testCase1);
        double result2 = instance.processTimeString(testCase2);
        assertEquals(expResult1, result1, 0.0);
        assertEquals(expResult2, result2, 0.0);
    }

    @Test
    public void testCheckClassTime() {
        // start time == end time
        String start1 = "08:00";
        String end1 = "08:00";
        //start time > end time
        String start2 = "10:00";
        String end2 = "09:00";
        //start time < end time
        String start3 = "10:00";
        String end3 = "11:00";
        // total time is less than 45 mins
        String start4 = "10:00";
        String end4 = "10:30";
        // total time is greater than 60 mins
        String start5 = "10:00";
        String end5 = "11:30";
        DataValidation instance = new DataValidation();
        boolean expResult1 = false;
        boolean expResult2 = false;
        boolean expResult3 = true;
        boolean expResult4 = false;
        boolean expResult5 = false;
        boolean result1 = instance.checkClassTime(start1, end1);
        boolean result2 = instance.checkClassTime(start2, end2);
        boolean result3 = instance.checkClassTime(start3, end3);
        boolean result4 = instance.checkClassTime(start4, end4);
        boolean result5 = instance.checkClassTime(start5, end5);
        assertEquals(expResult1, result1);
        assertEquals(expResult2, result2);
        assertEquals(expResult3, result3);
        assertEquals(expResult4, result4);
        assertEquals(expResult5, result5);
    }
    
    @Test
    public void testCheckClassCollision(){
        String day = "Mon";
        DataValidation instance = new DataValidation();
        Data.loadAllData();
        
        // base assume that base start time = 10:00 and end time = 11:00
        
        // start and end time is the same as base start time and end time
        String classStartTime1 = "10:00";
        String classEndTime1 = "11:00";
        
        // start and end time partly overlap base start and end time
        String classStartTime2 = "10:30";
        String classEndTime2 = "11:30";
        
        //base start and end tim partly over lap with start and end time
        String classStartTime3 = "09:30";
        String classEndTime3 = "10:30";
        
        // no overlapping
        String classStartTime4 = "12:00";
        String classEndTime4 = "13:00";
        
        
        boolean expResult1 = false;
        boolean expResult2 = false;
        boolean expResult3 = false;
        boolean expResult4 = true;
        
        boolean result1 = instance.checkClassCollision(day,classStartTime1,classEndTime1);
        boolean result2 = instance.checkClassCollision(day,classStartTime2,classEndTime2);
        boolean result3 = instance.checkClassCollision(day,classStartTime3,classEndTime3);
        boolean result4 = instance.checkClassCollision(day,classStartTime4,classEndTime4);
        
        assertEquals(expResult1, result1);
        assertEquals(expResult2, result2);
        assertEquals(expResult3, result3);
        assertEquals(expResult4, result4);
    }

    @Test
    public void testCheckEmpty() {
        
        // text is empty
        String testCase1 = "";
        // text if not empty
        String testCase2 = "afede";
        DataValidation instance = new DataValidation();
        boolean expResult1 = false;
        boolean expResult2 = true;
        boolean result1 = instance.checkEmpty(testCase1);
        boolean result2 = instance.checkEmpty(testCase2);
        assertEquals(expResult1, result1);
        assertEquals(expResult2, result2);
    }

    @Test
    public void testMyformatDate() {
        Date date = new Date();
        JCalendar dc = new JCalendar();
        date = dc.getDate();
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        DataValidation instance = new DataValidation();
        String expResult = df.format(date);
        String result = instance.myformatDate(date);
        assertEquals(expResult, result);
    }
}