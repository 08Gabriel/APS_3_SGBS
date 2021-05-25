/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sgbs.model;

import java.util.ArrayList;
import sgbs.dao.ExceptionDAO;
import sgbs.dao.TriagemDAO;

/**
 *
 * @author gabriel
 */


public class Triagem {
    private Integer idTriagem;
    private Integer codUsuario;
    private Integer codDoador;
    private String resultado;
    private String dataTriagem;
    private String motivo;
    
    public Triagem() {}; 
    
    public Triagem(Integer codUsuario, Integer codDoador, String resultado, String motivo, String dataTriagem) {
        this.codUsuario = codUsuario;
        this.codDoador = codDoador;
        this.resultado = resultado;
        this.motivo = motivo;
        this.dataTriagem = dataTriagem;
    }
    
    public void salvarTriagem(Triagem triagem) throws ExceptionDAO {
        TriagemDAO triagemDAO = new TriagemDAO();
        triagemDAO.cadastrarTriagem(triagem);
        
    }
    
    public ArrayList<Triagem> obterTriagens(Integer id) throws ExceptionDAO {
        TriagemDAO triagemDAO = new TriagemDAO();
        ArrayList<Triagem> triagens =  triagemDAO.obterTriagens(id);
        
        
        return triagens;
    }
    
    
    // GETTERS E SETTERS

    public Integer getIdTriagem() {
        return idTriagem;
    }

    public void setIdTriagem(Integer idTriagem) {
        this.idTriagem = idTriagem;
    }

    public Integer getCodUsuario() {
        return codUsuario;
    }

    public void setCodUsuario(Integer codUsuario) {
        this.codUsuario = codUsuario;
    }

    public Integer getCodDoador() {
        return codDoador;
    }

    public void setCodDoador(Integer codDoador) {
        this.codDoador = codDoador;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getDataTriagem() {
        return dataTriagem;
    }

    public void setDataTriagem(String dataTriagem) {
        this.dataTriagem = dataTriagem;
    }
    
    
    
    
}
