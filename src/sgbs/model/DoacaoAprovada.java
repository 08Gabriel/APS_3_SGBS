/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sgbs.model;

import sgbs.dao.DoacaoAprovadaDAO;
import sgbs.dao.ExceptionDAO;

/**
 *
 * @author gabriel
 */
public class DoacaoAprovada {
    private Integer idDoacaoAprovada;
    private Integer idAnalise;
    private String tipoSanguíneo;
    
    
    // CONSTRUTORES 
    public DoacaoAprovada() {};
    
    public DoacaoAprovada(Integer idDoacaoAprovada, Integer idAnalise, String tipoSanguíneo) {
        this.idDoacaoAprovada = idDoacaoAprovada;
        this.idAnalise = idAnalise;
        this.tipoSanguíneo = tipoSanguíneo;
    }
    
    // ACESSA A CLASSE DAO E CADASTRA A DOACAO ****APROVADA****
    public void cadastrarDoacaoAprovada(DoacaoAprovada doacaoAP) throws ExceptionDAO {
        DoacaoAprovadaDAO doacaoApDAO = new DoacaoAprovadaDAO();
        doacaoApDAO.cadastrarDoacaoAprovada(doacaoAP);
    
    }
    // RETORNA UM INTEIRO DA QUANTIDADE DE DOACOES APROVADAS DISPONIVEIS NO ESTOQUE
    public int qtdDoacoesApTipoSanguineo(String tipoSanguineo) throws ExceptionDAO {
        int qtd = new DoacaoAprovadaDAO().qtdDoacoesApTipoSanguineo(tipoSanguineo);
        
        return qtd;
    }

    // GETTERS E SETTERS 

    public Integer getIdDoacaoAprovada() {
        return idDoacaoAprovada;
    }

    public void setIdDoacaoAprovada(Integer idDoacaoAprovada) {
        this.idDoacaoAprovada = idDoacaoAprovada;
    }

    public Integer getIdAnalise() {
        return idAnalise;
    }

    public void setIdAnalise(Integer idAnalise) {
        this.idAnalise = idAnalise;
    }

    public String getTipoSanguíneo() {
        return tipoSanguíneo;
    }

    public void setTipoSanguíneo(String tipoSanguíneo) {
        this.tipoSanguíneo = tipoSanguíneo;
    }
    
    
}
