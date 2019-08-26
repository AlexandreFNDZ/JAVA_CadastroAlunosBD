/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connection;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Classe de Conexão ao Banco de Dados
 * @author Alexandre
 */
public class Conexao {
    
    public Connection getConnection() throws SQLException, ClassNotFoundException {
        
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://143.106.241.1:3306/cl25080";
        String usuario = "cl25080";
        String senha = "cl*31031990";
        
        try { // Try-Catch para tratar o retorno do método 'getConnection' da classe DriverManager joga uma excessão
            Class.forName(driver);
            return (Connection) DriverManager.getConnection(url, usuario, senha);
        } catch (SQLException ex) { // Tipo de Excessão recebida pelo método 'getConnection'
            throw new RuntimeException("Erro de conexao", ex);
        }
    }
    
}
