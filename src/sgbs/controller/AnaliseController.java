/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sgbs.controller;

import sgbs.dao.ExceptionDAO;
import sgbs.model.Analise;

/**
 *
 * @author gabriel
 */
public class AnaliseController {
    Analise analise = null;
    public boolean cadastrarAnalise(Integer idDoacao, 
            String resultado, String motivoResultado, String DataAnalise) throws ExceptionDAO {
        // VERIFICANDO SE POSSUI ALGUM VALOR NULO
        if (idDoacao != null && resultado != null && !resultado.toUpperCase().equals("SELECIONE") && motivoResultado != null && motivoResultado.length() > 0) {
            analise = new Analise(idDoacao, resultado, motivoResultado, DataAnalise);
            analise.cadastrarAnalise(analise);
            return true;
        }
        return false;

    }
    
}
