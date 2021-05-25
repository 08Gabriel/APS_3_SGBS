/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sgbs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import sgbs.model.DoacaoAprovada;
import java.sql.ResultSet;


/**
 *
 * @author gabriel
 */
public class DoacaoAprovadaDAO {
    // CADASTRA DOACAO APROVADA -> ESTOQUE
    public void cadastrarDoacaoAprovada(DoacaoAprovada doacaoAP) throws ExceptionDAO {
        String sqlInstrucao = "insert into Doacoes_Aprovadas values (?, ?, ?)";
        PreparedStatement pStatement = null;
        Connection conexao = null;
        
        
        try {
            conexao = new connectionSGBS().getConnection();
            pStatement = conexao.prepareStatement(sqlInstrucao);
            
            pStatement.setInt(1, doacaoAP.getIdDoacaoAprovada());
            pStatement.setInt(2, doacaoAP.getIdAnalise());
            pStatement.setString(3, doacaoAP.getTipoSanguíneo());
            pStatement.execute();

        
        } catch (SQLException e) {
            throw  new ExceptionDAO("Ocorreu um erro inesperado uma doacão aprovada, "
                    + "CLASSE: DoacaoAprovadaDAO \ntente novamente mais tarde\nERRO: " + e);
        } finally {
            connectionSGBS.closeConnection(conexao, pStatement);
        }
    }
    
    // RETORNA UM INTEIRO DA QUANTIDADE DE DOACOES APROVADAS DISPONIVEIS NO ESTOQUE
    public int qtdDoacoesApTipoSanguineo(String tipoSanguineo) throws ExceptionDAO {
        Integer qtdRegistros = null;
        
        String sqlInstrucao = "select count(*) from Doacoes_Aprovadas where upper(tipoSanguineo) = ?";
        PreparedStatement pStatement = null;
        Connection conexao = null;
        ResultSet rs = null;
        
        try {
            conexao = new connectionSGBS().getConnection();
            pStatement = conexao.prepareStatement(sqlInstrucao);
            pStatement.setString(1, tipoSanguineo);
            rs = pStatement.executeQuery();
            
            if (rs.next()) {
                qtdRegistros = rs.getInt(1);
            }
            
            return qtdRegistros;
        
        } catch (SQLException e) {
            throw  new ExceptionDAO("Ocorreu um erro inesperado ao contar os registros de doações Aprovadas (qtdDoacoesApTipoSanguineo)\nERRO: " + e);
        } finally {
            connectionSGBS.closeConnection(conexao, pStatement, rs);
        } 
    }
    
}
