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
import sgbs.model.Doador;

/**
 *
 * @author gabriel
 */
public class DoadorDAO {
    public void cadastraDoador(Doador doador) throws ExceptionDAO {
        // GERANDO ID DO DOADOR = (ANO + COUNT + 1)
        int idDoador = getQuantidadeRegistros() + 1;
        
        String sqlInstrucao = "insert into Doador values(?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement pStatement = null;
        Connection conexao = null;
        
        try {
            conexao = new connectionSGBS().getConnection();
            pStatement = conexao.prepareStatement(sqlInstrucao);
            doador.setIdDoador(idDoador);
            
            pStatement.setInt(1, doador.getIdDoador());
            pStatement.setString(2, doador.getCPF());
            pStatement.setString(3, doador.getNome());
            pStatement.setString(4, doador.getSobrenome());
            pStatement.setString(5, doador.getDtNasc());
            pStatement.setString(6, doador.getSexo());
            pStatement.setString(7, doador.getTipoSanguineo());
            pStatement.setInt(8, doador.getIdUsuario());
            salvarEndereco(doador);
            pStatement.execute();
        
        } catch (SQLException e) {
            throw  new ExceptionDAO("Ocorreu um erro inesperado ao cadastrar o Doador, tente novamente mais tarde (cadastraDoador)\nERRO: " + e);
        } finally {
            connectionSGBS.closeConnection(conexao, pStatement);
        }

    }
    private void salvarEndereco(Doador doador) throws ExceptionDAO {
    String sqlInstrucao = "insert into Endereco (cod, rua, numero, complemento, bairro, CEP, cidade, UF) values(?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement pStatement = null;
        Connection conexao = null;
        
        try {
            conexao = new connectionSGBS().getConnection();
            pStatement = conexao.prepareStatement(sqlInstrucao);
            
            pStatement.setInt(1, doador.getIdDoador());
            pStatement.setString(2, doador.getEndereco().getRua());
            pStatement.setString(3, doador.getEndereco().getNumero());
            pStatement.setString(4, doador.getEndereco().getComplemento());
            pStatement.setString(5, doador.getEndereco().getBairro());
            pStatement.setString(6, doador.getEndereco().getCEP());
            pStatement.setString(7, doador.getEndereco().getCidade());
            pStatement.setString(8, doador.getEndereco().getUF());
            pStatement.execute();
        
        } catch (SQLException e) {
            throw  new ExceptionDAO("Ocorreu um erro inesperado ao cadastrar endereço do Doador, tente novamente mais tarde (cadastraDoador)\nERRO: " + e);
        } finally {
            connectionSGBS.closeConnection(conexao, pStatement);
        }
    
    
    }
    
    private int getQuantidadeRegistros() throws ExceptionDAO {
        Integer qtdRegistros = null;
        String sqlInstrucao = "select count(*) from Doador";
        PreparedStatement pStatement = null;
        Connection conexao = null;
        ResultSet rs = null;
        
        try {
            conexao = new connectionSGBS().getConnection();
            pStatement = conexao.prepareStatement(sqlInstrucao);
            rs = pStatement.executeQuery();
            
            if (rs.next()) {
                qtdRegistros = rs.getInt(1);
            }
            
            return qtdRegistros;
        
        } catch (SQLException e) {
            throw  new ExceptionDAO("Ocorreu um erro inesperado ao selecionar quantidade de registros, tente novamente mais tarde(getQuantidadeRegistros)\nERRO: " + e);
        } finally {
            connectionSGBS.closeConnection(conexao, pStatement, rs);
        }
    
    }
    
    public boolean ehDoadorCadastrado(String CPF) throws ExceptionDAO {
        String sqlInstrucao = "SELECT * from Doador where CPF = ?";
        PreparedStatement pStatement = null;
        Connection conexao = null;
        ResultSet rs = null;
        boolean cadastrado = false;
        
        try {
            conexao = new connectionSGBS().getConnection();
            pStatement = conexao.prepareStatement(sqlInstrucao);
            
            pStatement.setString(1, CPF);

            
            rs = pStatement.executeQuery();
            
            if (rs.next()) {
                cadastrado = true;
            }        
            
            
        } catch (SQLException e) {
            throw  new ExceptionDAO("Ocorreu um erro inesperado ao verificar o doador no BD \nERRO: " + e);
        } finally {
            connectionSGBS.closeConnection(conexao, pStatement, rs);
        }
        
        return cadastrado;
    }
    
    public boolean ehDoadorCadastrado(int idDoador) throws ExceptionDAO {
        String sqlInstrucao = "SELECT * from Doador where idDoador = ?";
        PreparedStatement pStatement = null;
        Connection conexao = null;
        ResultSet rs = null;
        boolean cadastrado = false;
        
        try {
            conexao = new connectionSGBS().getConnection();
            pStatement = conexao.prepareStatement(sqlInstrucao);
            
            pStatement.setInt(1, idDoador);

            
            rs = pStatement.executeQuery();
            
            if (rs.next()) {
                cadastrado = true;
            }        
            
            
        } catch (SQLException e) {
            throw  new ExceptionDAO("Ocorreu um erro inesperado ao verificar doador no BD: assinatura int \nERRO: " + e);
        } finally {
            connectionSGBS.closeConnection(conexao, pStatement, rs);
        }
        
        return cadastrado;
    }
    
    // CARREGA DADOS COM CPF
    public static Doador carregarDadosDoador(String CPF) throws ExceptionDAO {
        Doador doador = new Doador();
        String sqlInstrucao = "SELECT * from Doador where CPF = ? limit 1";
        PreparedStatement pStatement = null;
        Connection conexao = null;
        ResultSet rs = null;
        
        
        try {
            conexao = new connectionSGBS().getConnection();
            pStatement = conexao.prepareStatement(sqlInstrucao);
            
            pStatement.setString(1, CPF);
            
            rs = pStatement.executeQuery();
            if (rs.next()) {
                doador.setIdDoador(rs.getInt("idDoador"));
                doador.setCPF(rs.getString("CPF"));
                doador.setNome(rs.getString("nome"));
                doador.setSobrenome(rs.getString("sobrenome"));
                doador.setDtNasc(rs.getString("dtNasc"));
                doador.setSexo(rs.getString("sexo"));
                doador.setTipoSanguineo(rs.getString("tipoSanguineo"));
                doador.setIdUsuario(rs.getInt("idUsuario"));
                return doador;
            }
            
        
        } catch(SQLException e) {
            throw  new ExceptionDAO("Ocorreu um erro ao carregar dados do doador \nERRO: " + e);
        
        } finally {
            connectionSGBS.closeConnection(conexao, pStatement, rs);
        }
        return doador;
    
    } 
    
    // CARREGA DADOS COM ID
    public static Doador carregarDadosDoador(int idDoador) throws ExceptionDAO {
        Doador doador = new Doador();
        String sqlInstrucao = "SELECT * from Doador where idDoador = ? limit 1";
        PreparedStatement pStatement = null;
        Connection conexao = null;
        ResultSet rs = null;
        
        
        try {
            conexao = new connectionSGBS().getConnection();
            pStatement = conexao.prepareStatement(sqlInstrucao);
            
            pStatement.setInt(1, idDoador);
            
            rs = pStatement.executeQuery();
            if (rs.next()) {
                doador.setIdDoador(rs.getInt("idDoador"));
                doador.setCPF(rs.getString("CPF"));
                doador.setNome(rs.getString("nome"));
                doador.setSobrenome(rs.getString("sobrenome"));
                doador.setDtNasc(rs.getString("dtNasc"));
                doador.setSexo(rs.getString("sexo"));
                doador.setTipoSanguineo(rs.getString("tipoSanguineo"));
                doador.setIdUsuario(rs.getInt("idUsuario"));
                return doador;
            }
            
        
        } catch(SQLException e) {
            throw  new ExceptionDAO("Ocorreu um erro ao carregar dados do doador \nERRO: " + e);
        
        } finally {
            connectionSGBS.closeConnection(conexao, pStatement, rs);
        }
        return doador;
    
    } 
}
