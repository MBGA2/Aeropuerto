package Main;

import java.awt.EventQueue;
import java.io.IOException;
import java.net.UnknownHostException;
import java.sql.SQLException;

import Controladores.main_controller;
import Vista.MainView;


public class Main {

	
	public static void main(String[] args) throws ClassNotFoundException, SQLException, UnknownHostException, IOException {
		//c.desconectar();
		/*conexion c = new conexion();
		String sql = "set autocommit on";
		PreparedStatement ps = c.conectar().prepareStatement(sql);
		ps.execute();
		ps.close();
		c.desconectar();*/
		Aeropuerto aero = new Aeropuerto("MBGA");
		main_controller ctrl = new main_controller(aero);
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainView vista = new MainView(ctrl);
					aero.setVista(vista);
					aero.addObserver(ctrl);
					ctrl.init();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
