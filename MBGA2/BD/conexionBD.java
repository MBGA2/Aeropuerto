package BD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class conexionBD {
	Connection c;
	public Connection conectar() throws ClassNotFoundException, SQLException {
		String url = "jdbc:oracle:thin:@147.96.85.46:1521:ABDi";
        String user = "BDC0650";
        String password = "47447279";
		Class.forName("oracle.jdbc.OracleDriver");
		c = DriverManager.getConnection(url, user, password);
		return c;
	}
	
	public void desconectar() {
		try {
			c.close();
			//System.out.println("Te has desonectado de la Base de Datos");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("No se pudo realizar la desconexion");
		}
	}
}
