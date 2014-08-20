package Controller;

import Model.*;
import View.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TeacherListController implements MouseListener {

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() instanceof JButton) {
            JButton button = (JButton) e.getSource();
            if (button.getText().equalsIgnoreCase("Assign")) {
                System.out.println("Assigning");
                if (TeacherListModel.getInstance().getCount() == 0) {
                    JOptionPane.showMessageDialog(null, "No teacher selected");
                } else if (TeacherListModel.getInstance().getSelectedItem().size() > 1) {
                    JOptionPane.showMessageDialog(null, "Cannot select more than"
                            + " one teacher for a class");
                } else {
                    TeacherListModel.getInstance().notifyClassForm();
                    TeacherListView.getInstance().dispose();
                }

            } else if (button.getText().equalsIgnoreCase("Cancel")) {
                TeacherListModel.getInstance().getSelectedItem().clear();
                if (TeacherListModel.getInstance().getSelectedItem().isEmpty()) {
                    System.out.println("Teacher list is empty ");
                }
                TeacherListView.getInstance().dispose();
            } else if (button.getText().equalsIgnoreCase("Search")) {
                String query = TeacherListView.getInstance().getSearchField().getText();
                if (query.equals("")) {
                    TeacherListItem[] items = new TeacherListItem[Data.teacherList.size()];
                    for (int i = 0; i < Data.teacherList.size(); i++) {
                        items[i] = new TeacherListItem(Data.teacherList.get(i));
                    }
                    TeacherListView.getInstance().getList().setListData(items);
                    TeacherListView.getInstance().getList().setBackground(new Color(176, 224, 230));
                } else {
                    int count = 0;
                    for (int i = 0; i < Data.teacherList.size(); i++) {
                        TeacherListItem temp = new TeacherListItem(Data.teacherList.get(i));
                        if (temp.getName().contains(query)) {
                            count += 1;
                        }
                    }
                    TeacherListItem[] items = new TeacherListItem[count];
                    for (int i = 0, j = 0; i < Data.teacherList.size(); i++) {
                        TeacherListItem temp = new TeacherListItem(Data.teacherList.get(i));
                        if (temp.getName().contains(query)) {
                            items[j] = new TeacherListItem(Data.teacherList.get(i));
                            j++;
                        }
                    }
                    TeacherListView.getInstance().getList().setListData(items);
                    TeacherListView.getInstance().getList().setBackground(new Color(176, 224, 230));
                }
            }
        }
        // if source of event is from JList
        if (e.getSource() instanceof JList) {
            JList list = (JList) e.getSource();
            int index = list.locationToIndex(e.getPoint());
            TeacherListItem ci = (TeacherListItem) list.getModel().getElementAt(index);
            ci.setSelected(!ci.isSelected());
            list.repaint(list.getCellBounds(index, index));

            if (ci.isSelected()) {
                TeacherListModel.getInstance().addItem(ci);
                System.out.println("----------Adding----------");
                TeacherListModel.getInstance().listItems();
            } else if (!ci.isSelected()) {
                TeacherListModel.getInstance().removeItem(ci);
                System.out.println("----------Removing----------");
                TeacherListModel.getInstance().listItems();
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
