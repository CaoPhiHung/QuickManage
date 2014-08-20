/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Model.StudentListItem;
import Model.TeacherListItem;
import java.awt.Component;
import javax.swing.JCheckBox;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

/**
 *
 * @author minh
 */
public class TeacherListRenderer extends JCheckBox implements ListCellRenderer {
    @Override
    public Component getListCellRendererComponent(JList list, Object value,
            int index, boolean isSelected, boolean cellHasFocus) {
        setEnabled(list.isEnabled());
        setSelected(((TeacherListItem) value).isSelected());
        setFont(list.getFont());
        setBackground(list.getBackground());
        setForeground(list.getForeground());
        setText(((TeacherListItem) value).getName());
        return this;
    }
}
