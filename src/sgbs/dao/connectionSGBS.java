/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sgbs.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author gabriel
 */
public class connectionSGBS {
    public Connection getConnection() {
        Connection conexao = null;
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        
        try {
            conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306/sgbs", "root", "2001");
        } catch(SQLException e) {
            e.printStackTrace();
        }
        
        return conexao;
    }
    
    public static void closeConnection(Connection con) throws ExceptionDAO {
        try {
            if (con != null) {
                con.close();
            }
        } catch (SQLException ex) {
           throw new ExceptionDAO("Ocorreu um erro interno ao fechar a conexão\nERRO: " + ex);
        }
    }

    public static void closeConnection(Connection con, PreparedStatement stmt) throws ExceptionDAO {

        closeConnection(con);

        try {

            if (stmt != null) {
                stmt.close();
            }

        } catch (SQLException ex) {
           throw new ExceptionDAO("Ocorreu um erro interno ao fechar a conexão\nERRO: " + ex);
        }
    }

    public static void closeConnection(Connection con, PreparedStatement stmt, ResultSet rs) throws ExceptionDAO {

        closeConnection(con, stmt);

        try {

            if (rs != null) {
                rs.close();
            }

        } catch (SQLException ex) {
            throw new ExceptionDAO("Ocorreu um erro interno ao fechar a conexão\nERRO: " + ex);
        }
    }
    
}
