/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sgbs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import sgbs.controller.DoacaoAprovadaController;
import sgbs.model.Analise;
import sgbs.model.Doacao;
import sgbs.model.DoacaoAprovada;

/**
 *
 * @author gabriel
 */
public class AnaliseDAO {
    public void cadastrarAnalise(Analise analise) throws ExceptionDAO {
        // GERANDO ID DA ANALISE = (ANO + COUNT + 1)
        int idAnalise = getQuantidadeRegistros() + 1;
        
        String sqlInstrucao = "insert into Analise values (?, ?, ?, ?, ?)";
        PreparedStatement pStatement = null;
        Connection conexao = null;
        
        // DATA PARA MYSQL
        String dia = analise.getDataAnalise().substring(0, 2);
        String mes = analise.getDataAnalise().substring(3, 5);
        String ano = analise.getDataAnalise().substring(6, 10);
        
        String dataMySQL = ano + "-" + mes + "-" + dia;
        
        try {
            conexao = new connectionSGBS().getConnection();
            pStatement = conexao.prepareStatement(sqlInstrucao);
            
            pStatement.setInt(1, idAnalise);
            pStatement.setInt(2, analise.getIdDoacao());
            pStatement.setString(3, analise.getResultado());
            pStatement.setString(4, analise.getMotivoResultado());
            alteraStatusDoacao(analise.getResultado(), analise.getIdDoacao());
            pStatement.setString(5, dataMySQL);
            pStatement.execute();
            
            
            // SE DOACAO FOI APROVADA É ADICIONA NA TABELA DE DOACOES APROVADAS(ESTOQUE) 
            if (analise.getResultado().equals("APROVADA")) {
                // OBJETO PARA PEGAR TIPO SANGUINEO DA DOACAO
                Doacao doacao = new Doacao().carregarDadosDoacao(analise.getIdDoacao());
                DoacaoAprovadaController doacaoApController = new DoacaoAprovadaController();
                
                DoacaoAprovada doacaoAP = new DoacaoAprovada(analise.getIdDoacao(), idAnalise, doacao.getTipoSanguineo());
                doacaoApController.cadastrarDoacaoAprovada(doacaoAP);
            }
        
        } catch (SQLException e) {
            throw  new ExceptionDAO("Ocorreu um erro inesperado ao cadastrar a análise, "
                    + "CLASSE: analiseDAO\ntente novamente mais tarde\nERRO: " + e);
        } finally {
            connectionSGBS.closeConnection(conexao, pStatement);
        }
    
    
    }
    
    private int getQuantidadeRegistros() throws ExceptionDAO {
        Integer ano = LocalDate.now().getYear();
        Integer qtdRegistros = null;
        Integer anoQtdRegistros = null;
        String concatena = null;
        
        String sqlInstrucao = "select count(*) from Analise";
        PreparedStatement pStatement = null;
        Connection conexao = null;
        ResultSet rs = null;
        
        try {
            conexao = new connectionSGBS().getConnection();
            pStatement = conexao.prepareStatement(sqlInstrucao);
            rs = pStatement.executeQuery();
            
            if (rs.next()) {
                qtdRegistros = rs.getInt(1);
                concatena = String.valueOf(ano) + String.valueOf(qtdRegistros);
                anoQtdRegistros = Integer.parseInt(concatena);
            }
            
            return anoQtdRegistros;
        
        } catch (SQLException e) {
            throw  new ExceptionDAO("Ocorreu um erro inesperado ao contar os registros de analises (getQuantidadeRegistros)\nERRO: " + e);
        } finally {
            connectionSGBS.closeConnection(conexao, pStatement, rs);
        }
    }
    
    // ALTERA O STATUS DA DOACAO NA TABELA DOACAO DE ACORDO COM O RESUTADO DA TRIAGEM
    private void alteraStatusDoacao(String resultado, Integer idDoacao) throws ExceptionDAO {
        String sqlInstrucao = "update Doacao set statusDoacao = ? where idDoacao = ? limit 1";
        PreparedStatement pStatement = null;
        Connection conexao = null;
        
        try {
            conexao = new connectionSGBS().getConnection();
            pStatement = conexao.prepareStatement(sqlInstrucao);
            
            pStatement.setString(1, resultado);
            pStatement.setInt(2, idDoacao);
            pStatement.execute();
        
        } catch (SQLException e) {
            throw  new ExceptionDAO("Ocorreu um erro interno ao alterar status doacao - AnaliseDAO (alteraStatusDoacao), tente novamente mais tarde\nERRO: " + e);
        } finally {
            connectionSGBS.closeConnection(conexao, pStatement);
    
        }
    }
    
    public Analise carregarDadosAnalise(int idDoacao) throws ExceptionDAO {
        Analise analise = new Analise();
        String sqlInstrucao = "SELECT * from Analise where idDoacao = ? limit 1";
        PreparedStatement pStatement = null;
        Connection conexao = null;
        ResultSet rs = null;
        
        
        try {
            conexao = new connectionSGBS().getConnection();
            pStatement = conexao.prepareStatement(sqlInstrucao);
            
            pStatement.setInt(1, idDoacao);
            
            rs = pStatement.executeQuery();
            if (rs.next()) {
                analise.setIdAnalise(rs.getInt("idAnalise"));
                analise.setIdDoacao(rs.getInt("idDoacao"));
                analise.setResultado(rs.getString("resultado"));
                analise.setMotivoResultado(rs.getString("motivoResultado"));
                analise.setDataAnalise(rs.getString("dataAnalise"));
        
            }

        } catch(SQLException e) {
            throw  new ExceptionDAO("Ocorreu um erro ao carregar dados do análise \nERRO: " + e);
        
        } finally {
            connectionSGBS.closeConnection(conexao, pStatement, rs);
        }
        return analise;
    }
}
