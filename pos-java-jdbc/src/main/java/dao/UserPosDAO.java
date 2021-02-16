//Classe responsavel por fazer as operações do banco de dados

package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import conexaojdbc.SingleConnection;
import model.Userposjava;

public class UserPosDAO {

	private Connection connection;

	public UserPosDAO() {
		connection = SingleConnection.getConnection();// Criamos um construtor e injetamos a nossa SingleConnection para
														// dentro dele toda vez que iniciarmos esse objeto
	}

	public void salvar(Userposjava userposjava) {// Método salvar recebe a nossa Classe modelo(Objeto) "Userposjava"
		
		try {
			
			String sql = "insert into userposjava (id, nome, email) values (?,?,?)";
			PreparedStatement insert = connection.prepareStatement(sql) ; // PreparedStatement faz o nosso insert na tabela
			
			//agora passamos os parametros para a nossa tabela que no caso é id, nome, email
			insert.setLong(1, userposjava.getId()); 
			insert.setString(2, userposjava.getNome());
			insert.setString(3, userposjava.getEmail());
			insert.execute();
			connection.commit();//salva no banco
			
		} catch (Exception e) {
			try {
				connection.rollback(); //caso der algum problema
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		
			
		
	}
}
