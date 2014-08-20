package Controller;

import Model.*;
import View.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class StudentListController implements MouseListener {

    @Override
    public void mouseClicked(MouseEvent e) {

        // if source of event is from a JButton
        if (e.getSource() instanceof JButton) {
            JButton button = (JButton) e.getSource();
            if (button.getText().equalsIgnoreCase("enroll")) {
                System.out.println("enrolling student");
                // send a list of enrolled student(s) to classForm
                StudentListModel.getInstance().notifyClassForm();
                StudentListView.getInstance().dispose();
            } else if (button.getText().equalsIgnoreCase("cancel")) {
                System.out.println("cancelling");
                StudentListModel.getInstance().getSelectedItem().clear();
                StudentListView.getInstance().dispose();
            } else if (button.getText().equalsIgnoreCase("search")) {
                System.out.println("searching");
                String query = StudentListView.getInstance().getSearchField().getText();
                if (query.equals("")) {
                    StudentListItem[] items = new StudentListItem[Data.studentList.size()];
                    for (int i = 0; i < Data.studentList.size(); i++) {
                        items[i] = new StudentListItem(Data.studentList.get(i));
                    }
                    StudentListView.getInstance().getList().setListData(items);
                    StudentListView.getInstance().getList().setBackground(new Color(176, 224, 230));
                } else {
                    int count = 0;
                    for (int i = 0; i < Data.studentList.size(); i++) {
                        StudentListItem temp = new StudentListItem(Data.studentList.get(i));
                        if (temp.getName().contains(query)) {
                            count += 1;
                        }
                    }
                    StudentListItem[] items = new StudentListItem[count];
                    for (int i = 0, j = 0; i < Data.studentList.size(); i++) {
                        StudentListItem temp = new StudentListItem(Data.studentList.get(i));
                        if (temp.getName().contains(query)) {
                            items[j] = new StudentListItem(Data.studentList.get(i));
                            j++;
                        }
                    }
                    StudentListView.getInstance().getList().setListData(items);
                    StudentListView.getInstance().getList().setBackground(new Color(176, 224, 230));
                }
            }
        }

        // if source of event is from JList
        if (e.getSource() instanceof JList) {
            JList list = (JList) e.getSource();
            int index = list.locationToIndex(e.getPoint());
            StudentListItem ci = (StudentListItem) list.getModel().getElementAt(index);
            ci.setSelected(!ci.isSelected());
            list.repaint(list.getCellBounds(index, index));

            if (ci.isSelected()) {
                StudentListModel.getInstance().addItem(ci);
                System.out.println("----------Adding----------");
                StudentListModel.getInstance().listItems();
            } else if (!ci.isSelected()) {
                StudentListModel.getInstance().removeItem(ci);
                System.out.println("----------Removing----------");
                StudentListModel.getInstance().listItems();
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
