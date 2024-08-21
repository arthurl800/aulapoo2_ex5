package jdbc;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entities.Aluno;

public class AlunoJDBC {
	
	String sql;
	PreparedStatement pst;
	
    String host;
	String user;
	String password;
	
	
	public void salvar(Aluno a, Connection con) throws IOException {
		
		try {
			
			sql = "INSERT INTO aluno (nome, sexo, dt_nasc) VALUES (?,  ?, ?)";
			
			pst = con.prepareStatement(sql);
			pst.setString(1, a.getNome());
			pst.setString(2, a.getSexo());
			
			Date dataSql = new Date(a.getDt_nasc().getTime());
			pst.setDate(3, dataSql);
			
			pst.executeUpdate();
			System.out.println("\nCadastro do aluno realizado com sucesso!");
			
		}
		catch (SQLException e) {
			
			System.out.println(e);
		}
		
	}
	
	public List<Aluno> listar() {
	    
		List<Aluno> alunos = new ArrayList<>();
	    String sql = "SELECT * FROM alunos";
	    
		try (Connection conn = DriverManager.getConnection(host, user, password);
	         Statement stmt = conn.createStatement();
	         ResultSet rs = stmt.executeQuery(sql)) {
	        
	        while (rs.next()) {
	            Aluno aluno = new Aluno();
	            aluno.setId(rs.getInt("id"));
	            aluno.setNome(rs.getString("nome"));
	            aluno.setSexo(rs.getString("sexo"));
	            aluno.setDt_nasc(rs.getDate("dt_nasc"));
	            alunos.add(aluno);
	        
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
	    return alunos;
	}
	
	public void apagar(int a) {
	    String sql = "DELETE FROM alunos WHERE id = ?";
	    
		try (Connection conn = DriverManager.getConnection(host, user, password);
	         PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        
	        pstmt.setInt(1, a);
	        pstmt.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	public void alterar(Aluno a) {
	    String sql = "UPDATE alunos SET nome = ?, idade = ? WHERE id = ?";
	    
	    try (Connection conn = DriverManager.getConnection(host, user, password);
	         PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        
	        pstmt.setInt(1, a.getId());
	        pstmt.setString(2, a.getNome());
	        pstmt.setString(3, a.getSexo());
	        pstmt.setDate(4, a.getDt_nasc());

	        pstmt.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
}

