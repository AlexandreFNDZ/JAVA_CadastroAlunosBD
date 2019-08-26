/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import connection.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.bean.Aluno;

/**
 *
 * @author Alexandre
 */
public class AlunoDAO {
    
    Connection con = null;
    
    public boolean inserir(Aluno al) throws SQLException, MySQLIntegrityConstraintViolationException {
        
        boolean inseriu = false;
        try {
            con = new Conexao().getConnection();
            String sql = "INSERT INTO Alunos (ra, nome, status) values (?,?,?)";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, al.getRa());
            stmt.setString(2, al.getNome());
            stmt.setString(3, al.getStatus());
            stmt.execute();
            stmt.close();
            inseriu = true;
        } catch (MySQLIntegrityConstraintViolationException ex) {
            JOptionPane.showMessageDialog(null, "Usuário já Cadastrado!");
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            con.close();
        }
        
        return inseriu;
    }
    
    public boolean excluir(Aluno al) throws SQLException {
        
        boolean excluiu = false;
        try {
            con = new Conexao().getConnection();
            String sql = "DELETE FROM Alunos WHERE ra = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, al.getRa());
            stmt.execute();
            stmt.close();
            excluiu = true;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            con.close();
        }
        
        return excluiu;
        
    }
    
    public ArrayList<Aluno> buscar(int busca, boolean alfab) throws SQLException {
        ResultSet rs = null;
        String sql = "";
        String ordemAlfab = "";
        ArrayList<Aluno> listaAlunos = new ArrayList<Aluno>();
        
        if (alfab) {
            ordemAlfab = " ORDER BY nome ASC";
        }
        
        if (busca == 0) {
            sql = "SELECT * FROM Alunos" + ordemAlfab;
        } else if (busca == 1) {
            sql = "SELECT * FROM Alunos WHERE status = 'Aprovado'" + ordemAlfab;
        } else if (busca == 2) {
            sql = "SELECT * FROM Alunos WHERE status = 'Reprovado'" + ordemAlfab;
        } else if (busca == 3) {
            sql = "SELECT * FROM Alunos WHERE status = 'Recuperação'" + ordemAlfab;
        }
        
        try {
            con = new Conexao().getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);
            
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                Aluno al = new Aluno();
                al.setRa(rs.getInt("ra"));
                al.setNome(rs.getString("nome"));
                al.setStatus(rs.getString("status"));
       
                listaAlunos.add(al);
            }
            
            stmt.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            con.close();
        }
        
        return listaAlunos;
    }
}
