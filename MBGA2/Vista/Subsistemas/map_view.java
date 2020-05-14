package Vista.Subsistemas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import Utils.ImageViewport;
import Vista.MainView;

public class map_view {

	private JPanel mainInfo;

	public map_view() {
		this.setMainInfo(new JPanel());
		mainInfo.setLayout(new BorderLayout());
		Object[][] data = new Object[14][20];
        for (int row = 0; row < 14; row++) {
            for (int col = 0; col < 20; col++) {
                data[row][col] = "";
            }
        }
        ImageIcon data1 = new ImageIcon( new ImageIcon(MainView.class.getResource("/Iconos/avion.png")).getImage().getScaledInstance( 20, 20,  java.awt.Image.SCALE_SMOOTH ));
        JTable table = new JTable();
        table.setRowHeight(50);
        table.setForeground(Color.WHITE);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.setModel(new javax.swing.table.DefaultTableModel(
        		
                        data,
                        new String[]{
                            "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""
                        }) {
            /**
							 * 
							 */
							private static final long serialVersionUID = 1L;

			@Override
            public Class<?> getColumnClass(int column) {
                switch(column) {
                    case 0:
                    case 1: return Integer.class;
                    case 2: return ImageIcon.class;
                    default: return Object.class;
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setPreferredSize(new Dimension(1400, 750));
        table.setOpaque(false);
        table.setBackground(new Color(255, 255, 255, 0));
        scrollPane.setViewport(new ImageViewport());
        scrollPane.setViewportView(table);
        mainInfo.add(scrollPane,BorderLayout.NORTH);
	}
	public JPanel getMainInfo() {
		return mainInfo;
	}

	public void setMainInfo(JPanel mainInfo) {
		this.mainInfo = mainInfo;
	}

	}

