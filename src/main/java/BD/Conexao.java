package BD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
	
	public static Connection Conecta(String path, String user, String pass)
	{
		try
		{
			return DriverManager.getConnection(path, user, pass);
		} 
		catch (SQLException e)
		{
			throw new RuntimeException(e);
		}
	}

}
