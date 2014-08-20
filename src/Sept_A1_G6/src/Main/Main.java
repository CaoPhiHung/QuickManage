package Main;

import Model.*;
import View.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {

                Data.loadAllData();

                //Create the Login Form and start the program
                LoginForm login = LoginForm.getInstance();
                login.initialize();
                login.getUsernameF().setText("manager");
                login.getPasswordF().setText("Manager1*");

            }
        });
    }
}
