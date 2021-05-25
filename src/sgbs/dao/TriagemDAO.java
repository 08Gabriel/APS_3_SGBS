/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sgbs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import sgbs.model.Triagem;

/**
 *
 * @author gabriel
 */
public class TriagemDAO {
    public void cadastrarTriagem(Triagem triagem) throws ExceptionDAO{        
        String sqlInstrucao = "insert into Triagem (codUsuario, codDoador, resultado, dataTriagem, motivo) value(?, ?, ?, ?, ?)";
        PreparedStatement pStatement = null;
        Connection conexao = null;
        
        try {
            conexao = new connectionSGBS().getConnection();
            pStatement = conexao.prepareStatement(sqlInstrucao);
            
            // DATA PARA MYSQL
            String dia = triagem.getDataTriagem().substring(0, 2);
            String mes = triagem.getDataTriagem().substring(3, 5);
            String ano = triagem.getDataTriagem().substring(6, 10);
            
            String dataMySQL = ano + "-" + mes + "-" + dia;
            
            pStatement.setInt(1, triagem.getCodUsuario());
            pStatement.setInt(2, triagem.getCodDoador());
            pStatement.setString(3, triagem.getResultado());
            pStatement.setString(4, dataMySQL);
            pStatement.setString(5, triagem.getMotivo());
            pStatement.execute();
        
        } catch (SQLException e) {
            throw  new ExceptionDAO("Ocorreu um erro interno inesperado ao cadastrar a TRIAGEM - TriagemDAO, tente novamente mais tarde\nERRO: " + e);
        } finally {
            connectionSGBS.closeConnection(conexao, pStatement);
        }
    
    }  
    
    public ArrayList<Triagem>  obterTriagens(Integer id) throws ExceptionDAO {
        String sqlInstrucao = "select * from Triagem where codDoador = ?";
        PreparedStatement pStatement = null;
        ResultSet rs = null;
        Connection conexao = null;
        ArrayList<Triagem> triagensArray;     
        triagensArray = new ArrayList<>();
        
        try {            
            conexao = new connectionSGBS().getConnection();
            pStatement = conexao.prepareStatement(sqlInstrucao);
            pStatement.setInt(1, id);

            rs = pStatement.executeQuery();
            
            // ADICIONANDO VALORES NOS OBJETO
            while (rs.next()) {
                Triagem triagem = new Triagem();
                
                triagem.setIdTriagem(rs.getInt("idTriagem"));
                triagem.setDataTriagem(rs.getString("dataTriagem"));
                triagem.setResultado(rs.getString("resultado"));
                triagem.setMotivo(rs.getString("motivo"));
                triagensArray.add(triagem); // -> adiciona no array
            } 

        } catch (SQLException e) {
            throw  new ExceptionDAO("DADOS NÃO ENCONTRADOS!");
        } finally {
            connectionSGBS.closeConnection(conexao, pStatement, rs);
        }
        return triagensArray;
    }
}
