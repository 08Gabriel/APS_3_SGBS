/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sgbs.controller;


import sgbs.dao.ExceptionDAO;
import sgbs.model.DoacaoAprovada;

/**
 *
 * @author gabriel
 */
public class DoacaoAprovadaController {
    public void cadastrarDoacaoAprovada(DoacaoAprovada doacaoAP) throws ExceptionDAO {
        DoacaoAprovada doacaoAp = new DoacaoAprovada();
        doacaoAp.cadastrarDoacaoAprovada(doacaoAP);
    }
    // RETORNA UM INTEIRO DA QUANTIDADE DE DOACOES APROVADAS DISPONIVEIS NO ESTOQUE
    public int qtdDoacoesApTipoSanguineo(String tipoSanguineo) throws ExceptionDAO {
        int qtd = new DoacaoAprovada().qtdDoacoesApTipoSanguineo(tipoSanguineo);
        
        return qtd;
    }
}
