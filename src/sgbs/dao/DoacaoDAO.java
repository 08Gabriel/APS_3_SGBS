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
import java.util.ArrayList;
import sgbs.model.Analise;
import sgbs.model.Doacao;


/**
 *
 * @author gabriel
 */
public class DoacaoDAO {
    public void cadastrarDoacao (Doacao doacao) throws ExceptionDAO{
        // GERANDO ID DA DOACAO = (ANO + COUNT + 1)
        int idDoacao = getQuantidadeRegistros() + 1;
        
        String sqlInstrucao = "insert into Doacao  value(?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement pStatement = null;
        Connection conexao = null;
        
        try {
            conexao = new connectionSGBS().getConnection();
            pStatement = conexao.prepareStatement(sqlInstrucao);
            
            // DATA PARA MYSQL
            String dia = doacao.getDataColeta().substring(0, 2);
            String mes = doacao.getDataColeta().substring(3, 5);
            String ano = doacao.getDataColeta().substring(6);
            
            String dataMySQL = ano + "-" + mes + "-" + dia;
            
            pStatement.setInt(1, idDoacao);
            pStatement.setFloat(2, doacao.getQtdColetada());
            pStatement.setInt(3, doacao.getId_Doador());
            pStatement.setInt(4, doacao.getIdUsuario());
            pStatement.setString(5, dataMySQL);
            pStatement.setString(6, doacao.getStatus());
            pStatement.setString(7, doacao.getTipoSanguineo());
            pStatement.execute();
        
        } catch (SQLException e) {
            throw  new ExceptionDAO("Ocorreu um erro inesperado ao cadastrar a Doação, tente novamente mais tarde\nERRO: " + e);
        } finally {
            connectionSGBS.closeConnection(conexao, pStatement);
        }
    }

    private int getQuantidadeRegistros() throws ExceptionDAO {
        Integer ano = LocalDate.now().getYear();
        Integer qtdRegistros = null;
        Integer anoQtdRegistros = null;
        String concatena = null;
        
        String sqlInstrucao = "select count(*) from Doacao";
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
            throw  new ExceptionDAO("Ocorreu um erro inesperado ao contar os registros de doações (getQuantidadeRegistros)\nERRO: " + e);
        } finally {
            connectionSGBS.closeConnection(conexao, pStatement, rs);
        }
    
    }
    
    public ArrayList<Doacao> leituraDoacao(String status) throws ExceptionDAO {
        String sqlInstrucao = "Select * from Doacao where upper(statusDoacao) = ?";
        PreparedStatement pStatement = null;
        ResultSet rs = null;
        Connection conexao = null;
        
        ArrayList<Doacao> doacoes = new ArrayList<Doacao>();
        
        
        try {
            
            conexao = new connectionSGBS().getConnection();
            pStatement = conexao.prepareStatement(sqlInstrucao);
            pStatement.setString(1, status);
            rs = pStatement.executeQuery();
            // ADICIONANDO VALORES NOS OBJETOS
            while (rs.next()) {
                Doacao doacao = new Doacao();
                
                doacao.setId_Doacao(rs.getInt("idDoacao"));
                doacao.setQtdColetada(rs.getFloat("qtdColetada"));
                doacao.setId_Doador(rs.getInt("idDoador"));
                doacao.setIdUsuario(rs.getInt("idUsuario"));
                doacao.setDataColeta(rs.getString("dataColeta"));
                doacao.setStatus(rs.getString("statusDoacao"));
                doacao.setTipoSanguineo(rs.getString("tipoSanguineo"));
                doacoes.add(doacao);
            }
            
        
        } catch (SQLException e) {
            throw  new ExceptionDAO("Ocorreu um erro inesperado ao carregar ler doações, tente novamente mais tarde\nERRO: " + e);
        } finally {
            connectionSGBS.closeConnection(conexao, pStatement, rs);
        }
        
        return doacoes;
    }
    
    // USADO PARA RETORNAR DOACOES COM ESTADO DE AGUARDANDO ANALISE ATRAVES DE UMA BUSCA
    public ArrayList<Doacao> leituraDoacaoEspecifica(String paramBusca, int busca) throws ExceptionDAO {
        String sqlInstrucao = "select * from Doacao";
        PreparedStatement pStatement = null;
        ResultSet rs = null;
        Connection conexao = null;
        ArrayList<Doacao> doacoes;

        try {
            String nomeColuna = "idUsuario";
            doacoes = new ArrayList<Doacao>();

            // DEFININDO POR QUAL COLUNA A DOACAO SERA PESQUISA
            switch (paramBusca.toUpperCase()) {
                case "ID DOAÇÃO":
                    sqlInstrucao = "Select * from Doacao where idDoacao = ? and statusDoacao = 'Aguardando Analise' ";
                    break;
                case "ID USUÁRIO":
                    sqlInstrucao = "Select * from Doacao where idUsuario = ? and statusDoacao = 'Aguardando Analise' ";
                    break;
                case "ID DOADOR":
                    sqlInstrucao = "Select * from Doacao where idDoador = ? and statusDoacao = 'Aguardando Analise' ";
                    break;
            }
            
            conexao = new connectionSGBS().getConnection();
            pStatement = conexao.prepareStatement(sqlInstrucao);
            
            pStatement.setInt(1 , busca);
            
            rs = pStatement.executeQuery();
            
            
            // ADICIONANDO VALORES NOS OBJETOS
            while (rs.next()){
                Doacao doacao = new Doacao();
                doacao.setId_Doacao(rs.getInt("idDoacao"));
                doacao.setQtdColetada(rs.getFloat("qtdColetada"));
                doacao.setId_Doador(rs.getInt("idDoador"));
                doacao.setIdUsuario(rs.getInt("idUsuario"));
                doacao.setDataColeta(rs.getString("dataColeta"));
                doacao.setStatus(rs.getString("statusDoacao"));
                doacao.setTipoSanguineo(rs.getString("tipoSanguineo"));
                doacoes.add(doacao);
            } 

        } catch (SQLException e) {
            throw  new ExceptionDAO("DADOS NÃO ENCONTRADOS!");
        } finally {
            connectionSGBS.closeConnection(conexao, pStatement, rs);
        }
        
        return doacoes;
    }
    // USADO PARA RETORNAR TODAS DOACOES  ANALISE ATRAVES DE UMA BUSCA
    public ArrayList<Doacao> obterDoacoes(Integer id) throws ExceptionDAO, SQLException {
        String sqlInstrucao = "select * from Doacao where idDoador = ?";
        PreparedStatement pStatement = null;
        ResultSet rs = null;
        Connection conexao = null;
        ArrayList<Doacao> doacoes;     
            
        
        try {            
            doacoes = new ArrayList<>();
  
            conexao = new connectionSGBS().getConnection();
            pStatement = conexao.prepareStatement(sqlInstrucao);
            pStatement.setInt(1, id);

            rs = pStatement.executeQuery();
            
            // ADICIONANDO VALORES NOS OBJETOS
            while (rs.next()){
                Doacao doacao = new Doacao();
                
                
                doacao.setId_Doacao(rs.getInt("idDoacao"));
                // CARREGANDO DADOS DA ANALISE
                Analise analise = new AnaliseDAO().carregarDadosAnalise(rs.getInt("idDoacao"));
                
                doacao.setId_Doador(rs.getInt("idDoador"));
                doacao.setDataColeta(rs.getString("dataColeta"));
                doacao.setQtdColetada(rs.getFloat("qtdColetada"));
                doacao.setStatus(rs.getString("statusDoacao"));
                doacao.setAnalise(analise);
                doacoes.add(doacao);
            } 

        } catch (SQLException e) {
            throw  new ExceptionDAO("DADOS NÃO ENCONTRADOS!");
        } finally {
            connectionSGBS.closeConnection(conexao, pStatement, rs);
        }
        
        return doacoes;
    }
    
    public Doacao carregarDadosDoacao(Integer idDoacao) throws ExceptionDAO {
        String sqlInstrucao = "select * from Doacao where idDoacao = ?";
        PreparedStatement pStatement = null;
        Connection conexao = null;
        ResultSet rs = null;
        
        Doacao doacao = new Doacao();
        
        try {
            conexao = new connectionSGBS().getConnection();
            pStatement = conexao.prepareStatement(sqlInstrucao);
            pStatement.setInt(1, idDoacao);
            rs = pStatement.executeQuery();
            
            if (rs.next()) {
                doacao.setId_Doacao(rs.getInt("idDoacao"));
                doacao.setQtdColetada(rs.getFloat("qtdColetada"));
                doacao.setId_Doador(rs.getInt("idDoador"));
                doacao.setIdUsuario(rs.getInt("idUsuario"));
                doacao.setDataColeta(rs.getString("dataColeta"));
                doacao.setStatus(rs.getString("statusDoacao"));
                doacao.setTipoSanguineo(rs.getString("tipoSanguineo"));
            }
            
            return doacao;
        
        } catch (SQLException e) {
            throw  new ExceptionDAO("Ocorreu um erro inesperado ao carregar doação (carregarDadosDoacao)\nERRO: " + e);
        } finally {
            connectionSGBS.closeConnection(conexao, pStatement, rs);
        }
    
    }
}
