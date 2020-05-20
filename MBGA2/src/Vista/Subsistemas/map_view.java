package Vista.Subsistemas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Controladores.map_controller;
import Vista.ImageViewport;

public class map_view {

	private JPanel mainInfo;
	private map_controller m;
	private DefaultTableModel model;

	public map_view(map_controller map_controller) {
		this.m = map_controller;
		this.setMainInfo(new JPanel());
		mainInfo.setLayout(new BorderLayout());
		Object[][] data = new Object[14][19];
        for (int row = 0; row < 14; row++) {
            for (int col = 0; col < 19; col++) {
                data[row][col] = "";
            }
        }
        
        JTable table = new JTable();
        table.setRowHeight(50);
        table.setForeground(Color.WHITE);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        model = new DefaultTableModel() {
			private static final long serialVersionUID = 1L;
		public boolean isCellEditable(int row, int column) {
   		       return false;
   		    }
   		@Override
        public Class<?> getColumnClass(int column) {
   			return ImageIcon.class;
        }
   		};
   		Object[] rowData = new Object[18];
   		for (int i = 0; i < 18; i++) {
   			model.addColumn("");
   			rowData[i] = null;
   		}
   		for (int i = 0; i < 14; i++) {
   		model.addRow(rowData);
   		}
   		table.setModel(model);
   		table.setTableHeader(null);
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setPreferredSize(new Dimension(1353, 703));
        table.setOpaque(false);
        table.setBackground(new Color(255, 255, 255, 0));
        scrollPane.setViewport(new ImageViewport());
        scrollPane.setViewportView(table);
        table.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				int row = table.rowAtPoint(evt.getPoint());
				int col = table.columnAtPoint(evt.getPoint());
				map_controller.infoCell(row,col);
			}
		});
        this.m.addTable(model);
        mainInfo.add(scrollPane,BorderLayout.NORTH);
	}
	public JPanel getMainInfo() {
		return mainInfo;
	}

	public void setMainInfo(JPanel mainInfo) {
		this.mainInfo = mainInfo;
	}

	}

