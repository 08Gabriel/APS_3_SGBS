/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sgbs.dao;

/**
 *
 * @author gabriel
 */
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import sgbs.model.Usuario;

public class UsuarioDAO {
    public void cadastrarUsuario (Usuario usuario) throws ExceptionDAO{
        String sqlInstrucao = "insert into Usuario (administrador, acessoLab, nome, sobrenome, dtNasc, nomeUsuario, senha, CPF, numeroFuncional) value(?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement pStatement = null;
        Connection conexao = null;
        
        try {
            conexao = new connectionSGBS().getConnection();
            pStatement = conexao.prepareStatement(sqlInstrucao);
            
            pStatement.setString(1, usuario.getAdmin());
            pStatement.setString(2, usuario.getAcessoLab());
            pStatement.setString(3, usuario.getNome());
            pStatement.setString(4, usuario.getSobrenome());
            pStatement.setString(5, usuario.getDtNasc());
            pStatement.setString(6, usuario.getNomeDeUsuario());
            pStatement.setString(7, usuario.getSenha());
            pStatement.setString(8, usuario.getCPF());
            pStatement.setString(9, usuario.getNumeroFuncional());
            pStatement.execute();
        
        } catch (SQLException e) {
            throw  new ExceptionDAO("Ocorreu um erro inesperado ao cadastrar o usuário, tente novamente mais tarde\nERRO: " + e);
        } finally {
            connectionSGBS.closeConnection(conexao, pStatement);
        }
    }
    
    public boolean validarUsuario(String nomeUsuario, String senha ) throws ExceptionDAO {
        String sqlInstrucao = "SELECT * from Usuario where nomeUsuario = ? and senha = ?";
        PreparedStatement pStatement = null;
        Connection conexao = null;
        ResultSet rs = null;
        boolean validado = false;
        
        try {
            conexao = new connectionSGBS().getConnection();
            pStatement = conexao.prepareStatement(sqlInstrucao);
            
            pStatement.setString(1, nomeUsuario);
            pStatement.setString(2, senha);
            
            rs = pStatement.executeQuery();
            
            if (rs.next()) {
                validado = true;
            }        
            
            
        } catch (SQLException e) {
            throw  new ExceptionDAO("Ocorreu um erro inesperado \nERRO: " + e);
        } finally {
            connectionSGBS.closeConnection(conexao, pStatement, rs);
        }
        
        return validado;
    }
    public boolean validarUsuarioAdmin(String nomeUsuario, String senha ) throws ExceptionDAO {
        String sqlInstrucao = "SELECT * from Usuario where nomeUsuario = ? and senha = ? and upper(administrador) = 'SIM' limit 1";
        PreparedStatement pStatement = null;
        Connection conexao = null;
        ResultSet rs = null;
        boolean validado = false;
        
        try {
            conexao = new connectionSGBS().getConnection();
            pStatement = conexao.prepareStatement(sqlInstrucao);
            
            pStatement.setString(1, nomeUsuario);
            pStatement.setString(2, senha);
            
            rs = pStatement.executeQuery();
            
            if (rs.next()) {
                validado = true;
            }        
            
            
        } catch (SQLException e) {
            throw  new ExceptionDAO("Ocorreu um erro inesperado \nERRO: " + e);
        } finally {
            connectionSGBS.closeConnection(conexao, pStatement, rs);
        }
        
        return validado;
    }
    
    public static Usuario carregarDadosUsuario(String nomeUsuario) throws ExceptionDAO {
        Usuario usuario = new Usuario();
        String sqlInstrucao = "SELECT * from Usuario where nomeUsuario = ? limit 1";
        PreparedStatement pStatement = null;
        Connection conexao = null;
        ResultSet rs = null;
        
        
        try {
            conexao = new connectionSGBS().getConnection();
            pStatement = conexao.prepareStatement(sqlInstrucao);
            
            pStatement.setString(1, nomeUsuario);
            
            rs = pStatement.executeQuery();
            if (rs.next()) {
                usuario.setIdUsuario(rs.getInt("idUsuario"));
                usuario.setAdmin(rs.getString("administrador"));
                usuario.setAcessoLab(rs.getString("acessoLab"));
                usuario.setNome(rs.getString("nome"));
                usuario.setSobrenome(rs.getString("sobrenome"));
                usuario.setDtNasc(rs.getString("dtNasc"));
                usuario.setNomeDeUsuario(rs.getString("nomeUsuario"));
                usuario.setSenha(rs.getString("senha"));
                usuario.setCPF(rs.getString("CPF"));
                usuario.setNumeroFuncional(rs.getString("numeroFuncional"));
                return usuario;
            }
            
        
        } catch(SQLException e) {
            throw  new ExceptionDAO("Ocorreu um erro ao carregar dados do usuário \nERRO: " + e);
        
        } finally {
            connectionSGBS.closeConnection(conexao, pStatement, rs);
        }
        return usuario;
    
    } 
    
    public boolean nomeDeUsuarioJaExiste(String nomeUsuario) throws ExceptionDAO {
        boolean encontrouNomeIgual = false;
        
        String sqlInstrucao = "SELECT nomeUsuario from Usuario";
        PreparedStatement pStatement = null;
        Connection conexao = null;
        ResultSet rs = null;
        
        
        try {
            conexao = new connectionSGBS().getConnection();
            pStatement = conexao.prepareStatement(sqlInstrucao);
            rs = pStatement.executeQuery();
            
            while (rs.next()) {
                if (rs.getString("nomeUsuario").equals(nomeUsuario)) {
                    encontrouNomeIgual = true;
                    break;
                }
            }
            
            
        
        } catch(SQLException e) {
            throw  new ExceptionDAO("Ocorreu um erro ao carregar dados do usuário \nERRO: " + e);
        
        } finally {
            connectionSGBS.closeConnection(conexao, pStatement, rs);
        }
    
        return encontrouNomeIgual;
   
    }
    
    public void AtualizarUsuario (Usuario usuario) throws ExceptionDAO{
        String sqlInstrucao = "update Usuario set  administrador = ?, acessoLab = ?,  nome = ?, sobrenome = ?,"
                + "dtNasc = ?, nomeUsuario = ?, senha = ?, CPF = ?, numeroFuncional = ? where idUsuario = ? limit 1";
        PreparedStatement pStatement = null;
        Connection conexao = null;
        
        try {
            conexao = new connectionSGBS().getConnection();
            pStatement = conexao.prepareStatement(sqlInstrucao);
            
            pStatement.setString(1, usuario.getAdmin());
            pStatement.setString(2, usuario.getAcessoLab());
            pStatement.setString(3, usuario.getNome());
            pStatement.setString(4, usuario.getSobrenome());
            pStatement.setString(5, usuario.getDtNasc());
            pStatement.setString(6, usuario.getNomeDeUsuario());
            pStatement.setString(7, usuario.getSenha());
            pStatement.setString(8, usuario.getCPF());
            pStatement.setString(9, usuario.getNumeroFuncional());
            pStatement.setInt(10, usuario.getIdUsuario());
            pStatement.execute();
        
        } catch (SQLException e) {
            throw  new ExceptionDAO("Ocorreu um erro inesperado ao atualizar o usuário, tente novamente mais tarde\nERRO: " + e);
        } finally {
            connectionSGBS.closeConnection(conexao, pStatement);
        }
    }
}
