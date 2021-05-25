/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sgbs.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import sgbs.dao.ExceptionDAO;
import sgbs.model.Triagem;

/**
 *
 * @author gabriel
 */
public class TriagemController {
    boolean resultadoTriagem = false;
    String motivo, resultado;
    
    //DATA
    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    Date date = new Date();
    String data = dateFormat.format(date);

    
    
    // METODO PARA VERIFICAR RESULTADO DE TRIAGEM
    public boolean resultadoTriagem(Integer codUsuario, Integer codDoador, 
        boolean teveHepatite, boolean hepatiteBouC, boolean evidenciaAids, 
        boolean drogasIlicitas, boolean doencaChagas, boolean teveCancer, 
        boolean teveMalaria, boolean transplateOrgaos, 
        boolean problemasPulmaoCoracaoRinsFig, boolean diabetico, 
        boolean tratDentario, boolean cirOdontologica) throws ExceptionDAO 
    {
        // ATRIBUTOS
        boolean passouNosRequistosfuncionais = verificaRequisitosEssenciais(teveHepatite, hepatiteBouC, 
                evidenciaAids, drogasIlicitas, doencaChagas, teveCancer, 
                teveMalaria, transplateOrgaos, problemasPulmaoCoracaoRinsFig, 
                diabetico);

        
        // DOADOR NAO ATENDE OS REQUISTIOS OBRIGATORIOS PARA REALIZAR A DOACAO
        if (!passouNosRequistosfuncionais)
        {   
            resultado = "REPROVADO";
            motivo = "O doador não atendeu os requisitos básicos";
            resultadoTriagem =  false;
            Triagem triagemReprovada  = new Triagem(codUsuario, codDoador, resultado, motivo, data);
            triagemReprovada.salvarTriagem(triagemReprovada);
        
        } // PODE FAZER DOCAO APÓS UM PERIODO DE SETE DIAS 
        else if (passouNosRequistosfuncionais && (tratDentario || cirOdontologica)){
            resultado = "REPROVADO*";
            motivo = "O doador não atendeu todos os requisitos porém pode realizar uma doação após 7 dias";
            resultadoTriagem =  false;
            Triagem triagemReprovadaObs  = new Triagem(codUsuario, codDoador, resultado, motivo, data);
            triagemReprovadaObs.salvarTriagem(triagemReprovadaObs);
        } 
        // APROVADO
        else if (passouNosRequistosfuncionais && !tratDentario && !cirOdontologica) {
             resultado = "APROVADO";
            motivo = "Doador aprovado sem apontamentos";
            resultadoTriagem =  true;
            Triagem triagemaprovada  = new Triagem(codUsuario, codDoador, resultado, motivo, data);
            triagemaprovada.salvarTriagem(triagemaprovada);
        }
        
        return resultadoTriagem;
    }
    
    
    private boolean verificaRequisitosEssenciais(boolean teveHepatite, 
        boolean hepatiteBouC, boolean evidenciaAids, 
        boolean drogasIlicitas, boolean doencaChagas, boolean teveCancer, 
        boolean teveMalaria, boolean transplateOrgaos, 
        boolean problemasPulmaoCoracaoRinsFig, boolean diabetico) 
    {
        if (teveHepatite || hepatiteBouC || evidenciaAids || drogasIlicitas ||
            doencaChagas || teveCancer || teveMalaria || transplateOrgaos ||
            problemasPulmaoCoracaoRinsFig || diabetico
            ) {
            return false;
        }
        return true;
   
    }
    
    // RETORNA ARRAY DE  OBJETOS TRIAGEM
    public ArrayList<Triagem> obterTriagens(Integer id) throws ExceptionDAO {
        Triagem triagem = new Triagem();
        ArrayList<Triagem> triagens =  triagem.obterTriagens(id);
        
        
        return triagens;
    }
    
    // GETTERS E SETTERS

    public boolean isResultadoTriagem() {
        return resultadoTriagem;
    }

    public String getMotivo() {
        return motivo;
    }

    public String getResultado() {
        return resultado;
    }
    
    
}
