/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2015-2021 Elior "Mallowigi" Boukhobza
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 *
 */

/*
 * Created by JFormDesigner on Fri Aug 13 16:21:48 IDT 2021
 */

package com.mallowigi.config.select;

import com.intellij.openapi.Disposable;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.ui.SearchTextField;
import com.intellij.util.ui.ColumnInfo;
import com.mallowigi.config.AtomSettingsBundle;
import com.mallowigi.config.associations.ui.columns.*;
import com.mallowigi.config.associations.ui.internal.AssociationsTableItemEditor;
import com.mallowigi.config.associations.ui.internal.AssociationsTableModelEditor;
import com.mallowigi.config.ui.SettingsFormUI;
import com.mallowigi.icons.associations.RegexAssociation;
import com.mallowigi.icons.associations.SelectedAssociations;
import com.mallowigi.models.IconType;
import net.miginfocom.swing.MigLayout;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.event.ActionEvent;
import java.util.ResourceBundle;

@SuppressWarnings({"FieldCanBeLocal",
  "DuplicateStringLiteralInspection",
  "StringConcatenation",
  "UndesirableClassUsage",
  "InstanceVariableMayNotBeInitialized",
  "TransientFieldNotInitialized",
  "ClassWithTooManyFields"})
public final class AtomSelectForm extends JPanel implements SettingsFormUI, Disposable {

  private final transient ColumnInfo[] fileColumns = {
    new EnabledColumnInfo(),
    new NameEditableColumnInfo(this, false),
    new PatternEditableColumnInfo(this, true),
    new FileIconEditableColumnInfo(this, true),
    new PriorityColumnInfo(this, true),
    new TouchedColumnInfo(),
  };

  private final transient ColumnInfo[] folderColumns = {
    new EnabledColumnInfo(),
    new NameEditableColumnInfo(this, false),
    new PatternEditableColumnInfo(this, true),
    new FolderIconEditableColumnInfo(this, true),
    new PriorityColumnInfo(this, true),
    new TouchedColumnInfo(),
  };
  // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
  // Generated using JFormDesigner non-commercial license
  private JLabel explanation;
  private JTabbedPane tabbedPane;
  private JPanel fileAssociationsPanel;
  private SearchTextField fileSearch;
  private JPanel filePanelTable;
  private JPanel folderAssociationsPanel;
  private SearchTextField folderSearch;
  private JPanel folderPanelTable;
  private JButton resetButton;
  // JFormDesigner - End of variables declaration  //GEN-END:variables
  private JComponent fileIconsTable;
  private JComponent folderIconsTable;
  private @Nullable AssociationsTableModelEditor<RegexAssociation> fileAssociationsEditor;
  private @Nullable AssociationsTableModelEditor<RegexAssociation> folderAssociationsEditor;

  @Override
  public void init() {
    initComponents();
    createTables();
  }

  @Override
  public JComponent getContent() {
    return this;
  }

  @Override
  public void afterStateSet() {
    // add after state set
  }

  @Override
  public void dispose() {
    fileAssociationsEditor = null;
    folderAssociationsEditor = null;
  }

  public void setFormState(final AtomSelectConfig config) {
    ApplicationManager.getApplication().invokeLater(() -> {
      if (fileAssociationsEditor != null) {
        fileAssociationsEditor.reset(config.getSelectedFileAssociations().getTheAssociations());
      }
      if (folderAssociationsEditor != null) {
        folderAssociationsEditor.reset(config.getSelectedFolderAssociations().getTheAssociations());
      }
      afterStateSet();
    });
  }

  @SuppressWarnings({"SimplifiableIfStatement",
    "DuplicatedCode"})
  public boolean isModified(final AtomSelectConfig config) {
    boolean modified = false;
    if (fileAssociationsEditor != null) {
      modified = config.isFileIconsModified(fileAssociationsEditor.getModel().getItems());
    }
    if (folderAssociationsEditor != null) {
      modified = modified || config.isFolderIconsModified(folderAssociationsEditor.getModel().getItems());
    }
    return modified;
  }

  public SelectedAssociations getFileAssociations() {
    assert fileAssociationsEditor != null;
    return new SelectedAssociations(IconType.FILE, fileAssociationsEditor.getModel().getAllItems());
  }

  public SelectedAssociations getFolderAssociations() {
    assert folderAssociationsEditor != null;
    return new SelectedAssociations(IconType.FOLDER, folderAssociationsEditor.getModel().getAllItems());
  }

  @SuppressWarnings("ConfusingFloatingPointLiteral")
  private void initComponents() {
    // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
    // Generated using JFormDesigner non-commercial license
    final ResourceBundle bundle = ResourceBundle.getBundle("messages.AtomFileIconsBundle");
    explanation = new JLabel();
    tabbedPane = new JTabbedPane();
    fileAssociationsPanel = new JPanel();
    fileSearch = new SearchTextField();
    filePanelTable = new JPanel();
    folderAssociationsPanel = new JPanel();
    folderSearch = new SearchTextField();
    folderPanelTable = new JPanel();
    resetButton = new JButton();

    //======== this ========
    setBorder(new TitledBorder(null, "Associations Editor", TitledBorder.CENTER, TitledBorder.TOP));
    setLayout(new MigLayout(
      "hidemode 3",
      // columns
      "[369,grow,fill]",
      // rows
      "[]" +
        "[grow,fill]" +
        "[fill]"));

    //---- explanation ----
    explanation.setText(bundle.getString("SelectForm.explanation.text"));
    explanation.setFont(explanation.getFont().deriveFont(explanation.getFont().getSize() - 1f));
    explanation.setForeground(UIManager.getColor("inactiveCaptionText"));
    add(explanation, "cell 0 0");

    //======== tabbedPane ========
    {

      //======== fileAssociationsPanel ========
      {
        fileAssociationsPanel.setLayout(new MigLayout(
          "hidemode 3",
          // columns
          "0[grow,fill]0",
          // rows
          "[]0" +
            "[grow,fill]rel" +
            "[]"));
        fileAssociationsPanel.add(fileSearch, "cell 0 0");

        //======== filePanelTable ========
        {
          filePanelTable.setLayout(new MigLayout(
            "insets 0,hidemode 3,gap 0 0",
            // columns
            "[grow,fill]",
            // rows
            "[grow,fill]"));
        }
        fileAssociationsPanel.add(filePanelTable, "cell 0 1");
      }
      tabbedPane.addTab(bundle.getString("SelectForm.fileAssociationsPanel.tab.title"), fileAssociationsPanel);

      //======== folderAssociationsPanel ========
      {
        folderAssociationsPanel.setLayout(new MigLayout(
          "hidemode 3",
          // columns
          "0[grow,fill]0",
          // rows
          "0[fill]0" +
            "[grow]"));
        folderAssociationsPanel.add(folderSearch, "cell 0 0");

        //======== folderPanelTable ========
        {
          folderPanelTable.setLayout(new MigLayout(
            "insets 0,hidemode 3,gap 0 0",
            // columns
            "[grow,fill]",
            // rows
            "[grow,fill]"));
        }
        folderAssociationsPanel.add(folderPanelTable, "cell 0 1");
      }
      tabbedPane.addTab(bundle.getString("SelectForm.folderAssociationsPanel.tab.title"), folderAssociationsPanel);
    }
    add(tabbedPane, "cell 0 1");

    //---- resetButton ----
    resetButton.setText(bundle.getString("SelectForm.resetButton.text"));
    add(resetButton, "cell 0 2,alignx right,growx 0");
    // JFormDesigner - End of component initialization  //GEN-END:initComponents
    resetButton.addActionListener(this::resetButtonActionPerformed);
  }

  @SuppressWarnings("FeatureEnvy")
  private void resetButtonActionPerformed(final ActionEvent e) {
    final AtomSelectConfig config = AtomSelectConfig.getInstance();

    config.reset();
    ApplicationManager.getApplication().invokeLater(() -> {
      if (fileAssociationsEditor != null) {
        fileAssociationsEditor.reset(config.getSelectedFileAssociations().getTheAssociations());
      }
      if (folderAssociationsEditor != null) {
        folderAssociationsEditor.reset(config.getSelectedFolderAssociations().getTheAssociations());
      }
      afterStateSet();
    });
  }

  private void createTables() {
    createFileIconsTable();
    createFolderIconsTable();
  }

  /**
   * Create the file icons
   */
  private void createFileIconsTable() {
    final AssociationsTableItemEditor itemEditor = new AssociationsTableItemEditor();
    fileAssociationsEditor = new AssociationsTableModelEditor<>(fileColumns,
      itemEditor,
      AtomSettingsBundle.message("no.file.associations"),
      fileSearch);
    fileIconsTable = fileAssociationsEditor.createComponent();
    filePanelTable.add(fileIconsTable, "cell 0 0"); //NON-NLS

  }

  /**
   * Create the folder icons
   */
  private void createFolderIconsTable() {
    final AssociationsTableItemEditor itemEditor = new AssociationsTableItemEditor();
    folderAssociationsEditor = new AssociationsTableModelEditor<>(folderColumns,
      itemEditor,
      AtomSettingsBundle.message("no.folder.associations"),
      folderSearch);
    folderIconsTable = folderAssociationsEditor.createComponent();
    folderPanelTable.add(folderIconsTable, "cell 0 0"); //NON-NLS
  }
}
