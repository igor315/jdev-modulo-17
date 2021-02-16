//Classe responsavel por fazer as operações do banco de dados

package dao;

import java.sql.Connection;

import conexaojdbc.SingleConnection;

public class UserPosDao {

	private Connection connection;
	
	public UserPosDao() {
		connection = SingleConnection.getConnection();//Criamos um construtor e injetamos a nossa SingleConnection para dentro dele toda vez que iniciarmos esse objeto
	}
	
	
}
