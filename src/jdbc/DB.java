package jdbc;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DB {

	private static Connection conexao;
	
	public static Properties getPropriedades() throws IOException {
		
		Properties propriedades = new Properties();
		FileInputStream arquivo = new FileInputStream("./propriedades/db.properties");
		propriedades.load(arquivo);
		return propriedades;
	}
	
	public static Connection getConexao() throws IOException, SQLException {
		
		String host;
		String user;
		String password;
		Properties propriedades = DB.getPropriedades();
		
		if (conexao == null) {
			
			Properties p = DB.getPropriedades();
		
			conexao = DriverManager.getConnection(	p.getProperty("host"),
													p.getProperty("user"),
													p.getProperty("password"));

			conexao = DriverManager.getConnection(host, user, password);
		}
		return conexao;
	}
	
	public static void fechaConexao() throws SQLException {
		if (conexao != null) {
			conexao.close();	
		}
	}
	
}
