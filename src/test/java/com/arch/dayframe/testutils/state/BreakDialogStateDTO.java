package com.arch.dayframe.testutils.state;

import com.arch.dayframe.gui.dialog.BreakDialog;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.List;

public class BreakDialogStateDTO extends GUIStateDTO {

    public LayoutManager layout;
    public Color background;
    public Point location;
    public JButton defaultButton;
    public boolean modal;
    public boolean resizable;
    public boolean visible;
    public boolean postponeListEnabled;
    public boolean postponeButtonEnabled;
    public int defaultCloseOperation;
    public int preferredSizeWidth;
    public int preferredSizeHeight;
    public int componentsCount;
    public int postponeListItemCount;
    public int postponeListSelectedItem;
    public int timeLabelFontStyle;
    public int timeLabelFontSize;
    public int messageLabelFontStyle;
    public int messageLabelFontSize;
    public int postponeListFontStyle;
    public int postponeListFontSize;
    public int okButtonFontStyle;
    public int okButtonFontSize;
    public int postponeButtonFontStyle;
    public int postponeButtonFontSize;
    public String title;
    public String timeLabelFontFamily;
    public String messageLabelFontFamily;
    public String postponeListFontFamily;
    public String postponeButtonFontFamily;
    public String okButtonFontFamily;
    public String timeLabelText;
    public String messageLabelText;
    public String okButtonText;
    public String postponeButtonText;
    public Border timeLabelBorder;
    public Border messageLabelBorder;
    public Border postponeListBorder;
    public Border buttonPanelBorder;
    public GridBagConstraints timeLabelConstraints;
    public GridBagConstraints messageLabelConstraints;
    public GridBagConstraints postponeListConstraints;
    public GridBagConstraints buttonPanelConstraints;
    public List<Component> labels;
    public List<Integer> postponeListItems;

    public BreakDialogStateDTO(BreakDialog breakDialog) {
        layout = getLayoutManager(breakDialog.mainPanel);
        background = getBackground(breakDialog.mainPanel);
        location = getLocation(breakDialog);
        defaultButton = getDefaultButton(breakDialog);
        modal = isModal(breakDialog);
        resizable = isResizable(breakDialog);
        visible = isVisible(breakDialog);
        postponeListEnabled = isEnabled(breakDialog.postponeList);
        postponeButtonEnabled = isEnabled(breakDialog.postponeButton);
        defaultCloseOperation = getDefaultCloseOperation(breakDialog);
        preferredSizeWidth = getPreferredSizeWidth(breakDialog);
        preferredSizeHeight = getPreferredSizeHeight(breakDialog);
        componentsCount = getComponentCount(breakDialog.mainPanel);
        postponeListItemCount = getListItemCount(breakDialog.postponeList);
        postponeListSelectedItem = Integer.parseInt(getListSelectedItem(breakDialog.postponeList));
        timeLabelFontStyle = getFontStyle(breakDialog.timeLabel);
        timeLabelFontSize = getFontSize(breakDialog.timeLabel);
        messageLabelFontStyle = getFontStyle(breakDialog.messageLabel);
        messageLabelFontSize = getFontSize(breakDialog.messageLabel);
        postponeListFontStyle = getFontStyle(breakDialog.postponeList);
        postponeListFontSize = getFontSize(breakDialog.postponeList);
        okButtonFontStyle = getFontStyle(breakDialog.okButton);
        okButtonFontSize = getFontSize(breakDialog.okButton);
        postponeButtonFontStyle = getFontStyle(breakDialog.postponeButton);
        postponeButtonFontSize = getFontSize(breakDialog.postponeButton);
        title = getTitle(breakDialog);
        timeLabelFontFamily = getFontFamily(breakDialog.timeLabel);
        messageLabelFontFamily = getFontFamily(breakDialog.messageLabel);
        postponeListFontFamily = getFontFamily(breakDialog.postponeList);
        postponeButtonFontFamily = getFontFamily(breakDialog.postponeButton);
        okButtonFontFamily = getFontFamily(breakDialog.okButton);
        timeLabelText = getText(breakDialog.timeLabel);
        messageLabelText = getText(breakDialog.messageLabel);
        okButtonText = getText(breakDialog.okButton);
        postponeButtonText = getText(breakDialog.postponeButton);
        timeLabelBorder = getBorder(breakDialog.timeLabel);
        messageLabelBorder = getBorder(breakDialog.messageLabel);
        postponeListBorder = getBorder(breakDialog.postponeList);
        buttonPanelBorder = getBorder(breakDialog.buttonPanel);
        timeLabelConstraints = getGridBagConstraints(layout, breakDialog.timeLabel);
        messageLabelConstraints = getGridBagConstraints(layout, breakDialog.messageLabel);
        postponeListConstraints = getGridBagConstraints(layout, breakDialog.postponeList);
        buttonPanelConstraints = getGridBagConstraints(layout, breakDialog.buttonPanel);
        labels = getComponents(breakDialog.mainPanel);
        postponeListItems = getListItems(breakDialog.postponeList);
    }
}
