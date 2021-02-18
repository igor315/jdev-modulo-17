//Classe responsavel por fazer as operações do banco de dados

package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
	
	//método listar
			public List<Userposjava> listar() throws Exception {
				List<Userposjava> list = new ArrayList<Userposjava>();
				
				String sql = "select * from userposjava";
				
				PreparedStatement statement = connection.prepareStatement(sql);
				ResultSet resultado = statement.executeQuery();
				
				while(resultado.next()) {
					Userposjava userposjava = new Userposjava(); 
					userposjava.setId(resultado.getLong("Id"));
					userposjava.setNome(resultado.getString("nome"));
					userposjava.setEmail(resultado.getString("email"));
					
					list.add(userposjava);
				}
				
				return list;
			}
			
			public Userposjava buscar(Long id) throws Exception {
				Userposjava retorno = new Userposjava();
				
				String sql = "select * from userposjava where id = " + id;
				
				PreparedStatement statement = connection.prepareStatement(sql);
				ResultSet resultado = statement.executeQuery();
				
				while(resultado.next()) { //retorna apenas ou nenhum  
					retorno.setId(resultado.getLong("Id"));
					retorno.setNome(resultado.getString("nome"));
					retorno.setEmail(resultado.getString("email"));
					
					
				}
				
				return retorno;
			}
			
}
