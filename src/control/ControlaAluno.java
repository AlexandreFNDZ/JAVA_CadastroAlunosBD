/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.table.DefaultTableModel;
import model.bean.Aluno;
import model.dao.AlunoDAO;

/**
 *
 * @author Alexandre
 */
public class ControlaAluno {
    
    public boolean insereAluno(int ra, String nome, String status) throws SQLException {
        Aluno aln = new Aluno();
        AlunoDAO alnDAO = new AlunoDAO();
        
        aln.setRa(ra);
        aln.setNome(nome);
        aln.setStatus(status);
        
        boolean inseriu = alnDAO.inserir(aln);
        
        return inseriu;
    }
    
    public boolean excluiAluno(int ra) throws SQLException, MySQLIntegrityConstraintViolationException {
        Aluno aln = new Aluno();
        AlunoDAO alnDAO = new AlunoDAO();
        
        aln.setRa(ra);
        
        boolean excluiu = alnDAO.excluir(aln);
        
        return excluiu;
    }
    
    public ArrayList<Aluno> buscaAluno(int busca, boolean alfab) throws SQLException {
        AlunoDAO alnDAO = new AlunoDAO();
        ArrayList<Aluno> listaAlunos = new ArrayList<Aluno>();
        
        listaAlunos = alnDAO.buscar(busca, alfab);
        
        return listaAlunos;
    }
    
    public void preencheTabela(DefaultTableModel model, ArrayList lista) {
        model.setNumRows(0);
        
        Iterator it = lista.iterator();
            
            while(it.hasNext()) {
                Aluno al = (Aluno) it.next();
                model.addRow(new Object[]{al.getRa(),al.getNome(),al.getStatus()});
            }
    }
}
