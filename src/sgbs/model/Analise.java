/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sgbs.model;

import java.util.ArrayList;
import sgbs.dao.AnaliseDAO;
import sgbs.dao.ExceptionDAO;

/**
 *
 * @author gabriel
 */
public class Analise {
    private Integer idAnalise;
    private Integer idDoacao;
    private String resultado;
    private String motivoResultado;
    private String dataAnalise;
    private ArrayList<Doacao> doacao = new ArrayList<Doacao>();
    
    // CONSTRUTOR
    public Analise() {};
    public Analise(Integer idDoacao, String resultado, String motivoResultado, String dataAnalise) {
        this.idDoacao = idDoacao;
        this.resultado = resultado;
        this.motivoResultado = motivoResultado;
        this.dataAnalise = dataAnalise;
    }
    
    
    public void cadastrarAnalise(Analise analise) throws ExceptionDAO {
        AnaliseDAO analiseDao = new AnaliseDAO();
        analiseDao.cadastrarAnalise(analise);
    }
    
    
    // GETTERS E SETTERS

    public Integer getIdAnalise() {
        return idAnalise;
    }

    public void setIdAnalise(Integer idAnalise) {
        this.idAnalise = idAnalise;
    }
    public Integer getIdDoacao() {
        return idDoacao;
    }

    public void setIdDoacao(Integer idDoacao) {
        this.idDoacao = idDoacao;
    }
    

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public String getMotivoResultado() {
        return motivoResultado;
    }

    public void setMotivoResultado(String motivoresultado) {
        this.motivoResultado = motivoresultado;
    }

    public String getDataAnalise() {
        return dataAnalise;
    }

    public void setDataAnalise(String dataAnalise) {
        this.dataAnalise = dataAnalise;
    }

    public ArrayList<Doacao> getDoacao() {
        return doacao;
    }

    public void setDoacao(ArrayList<Doacao> doacao) {
        this.doacao = doacao;
    }
    
}
