package nz.ac.vuw.ecs.group16.openrocketextension;

import javax.swing.JFrame;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.plaf.basic.BasicArrowButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

/**
 * Unused GUI for OR extension.
 * 
 * @author David
 *
 */
public class ORExtensionGUI extends JFrame {

  /**
   * ORExtensionGUI GUI builder using Swing components.
   **/

  public ORExtensionGUI() {
    setTitle("Group 16 OpenRocket Simulation Extension");
    setSize(900, 600);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    GridBagLayout gridBagLayout = new GridBagLayout();
    gridBagLayout.columnWidths = new int[] {683, 157, 0};
    gridBagLayout.rowHeights = new int[] {553, 0, 0};
    gridBagLayout.columnWeights = new double[] {0.0, 1.0, Double.MIN_VALUE};
    gridBagLayout.rowWeights = new double[] {0.0, 0.0, Double.MIN_VALUE};
    getContentPane().setLayout(gridBagLayout);

    JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
    tabbedPane.setFont(new Font("Tahoma", Font.PLAIN, 20));
    GridBagConstraints gbc_tabbedPane = new GridBagConstraints();
    gbc_tabbedPane.fill = GridBagConstraints.BOTH;
    gbc_tabbedPane.insets = new Insets(0, 0, 5, 5);
    gbc_tabbedPane.gridx = 0;
    gbc_tabbedPane.gridy = 0;
    getContentPane().add(tabbedPane, gbc_tabbedPane);

    JPanel mapPanel = new JPanel();
    tabbedPane.addTab("Map", null, mapPanel, null);
    mapPanel.setLayout(null);

    JButton plusButton = new JButton("+");
    plusButton.setBounds(12, 455, 41, 41);
    mapPanel.add(plusButton);

    JButton minusButton = new JButton("-");
    minusButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {}
    });
    minusButton.setBounds(72, 455, 41, 41);
    mapPanel.add(minusButton);

    BasicArrowButton leftButton = new BasicArrowButton(BasicArrowButton.WEST);
    BasicArrowButton rightButton = new BasicArrowButton(BasicArrowButton.EAST);
    BasicArrowButton upButton = new BasicArrowButton(BasicArrowButton.NORTH);
    BasicArrowButton downButton = new BasicArrowButton(BasicArrowButton.SOUTH);
    leftButton.setBounds(573, 442, 30, 30);
    rightButton.setBounds(631, 442, 30, 30);
    upButton.setBounds(602, 413, 30, 30);
    downButton.setBounds(602, 471, 30, 30);
    mapPanel.add(leftButton);
    mapPanel.add(rightButton);
    mapPanel.add(upButton);
    mapPanel.add(downButton);

    JButton generateSimulationButton = new JButton("Generate Simulation");
    generateSimulationButton.setBounds(468, 13, 170, 30);
    mapPanel.add(generateSimulationButton);
    JPanel graphPanel = new JPanel();
    tabbedPane.addTab("Graph", null, graphPanel, null);
    graphPanel.setLayout(null);

    JButton generateSimulationButton_1 = new JButton("Generate Simulation");
    generateSimulationButton_1.setBounds(468, 13, 170, 30);
    graphPanel.add(generateSimulationButton_1);

    JPanel dataPanel = new JPanel();
    GridBagConstraints gbc_dataPanel = new GridBagConstraints();
    gbc_dataPanel.insets = new Insets(0, 0, 5, 0);
    gbc_dataPanel.fill = GridBagConstraints.BOTH;
    gbc_dataPanel.gridx = 1;
    gbc_dataPanel.gridy = 0;
    getContentPane().add(dataPanel, gbc_dataPanel);
    GridBagLayout gbl_dataPanel = new GridBagLayout();
    gbl_dataPanel.columnWidths = new int[] {199, 0};
    gbl_dataPanel.rowHeights = new int[] {51, 30, 454, 0};
    gbl_dataPanel.columnWeights = new double[] {0.0, Double.MIN_VALUE};
    gbl_dataPanel.rowWeights = new double[] {0.0, 0.0, 0.0, Double.MIN_VALUE};
    dataPanel.setLayout(gbl_dataPanel);

    JLabel dataLabel = new JLabel("Data");
    dataLabel.setFont(new Font("Tahoma", Font.PLAIN, 26));
    dataLabel.setSize(100, 40);
    dataLabel.setHorizontalAlignment(SwingConstants.CENTER);
    GridBagConstraints gbc_dataLabel = new GridBagConstraints();
    gbc_dataLabel.anchor = GridBagConstraints.NORTH;
    gbc_dataLabel.fill = GridBagConstraints.HORIZONTAL;
    gbc_dataLabel.insets = new Insets(0, 0, 5, 0);
    gbc_dataLabel.gridx = 0;
    gbc_dataLabel.gridy = 0;
    dataPanel.add(dataLabel, gbc_dataLabel);

    JButton displayAllButton = new JButton("Display all");
    GridBagConstraints gbc_displayAllButton = new GridBagConstraints();
    gbc_displayAllButton.fill = GridBagConstraints.BOTH;
    gbc_displayAllButton.insets = new Insets(0, 0, 5, 0);
    gbc_displayAllButton.gridx = 0;
    gbc_displayAllButton.gridy = 1;
    dataPanel.add(displayAllButton, gbc_displayAllButton);

    JScrollPane dataTable = new JScrollPane();
    GridBagConstraints gbc_dataTable = new GridBagConstraints();
    gbc_dataTable.anchor = GridBagConstraints.NORTH;
    gbc_dataTable.fill = GridBagConstraints.HORIZONTAL;
    gbc_dataTable.gridx = 0;
    gbc_dataTable.gridy = 2;
    dataPanel.add(dataTable, gbc_dataTable);

    // JLabel lblData = new JLabel("Simulation Data");
    // scrollPane.setColumnHeaderView(lblData);

    setVisible(true);
  }
}

