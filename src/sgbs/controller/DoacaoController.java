package sgbs.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import sgbs.dao.ExceptionDAO;
import sgbs.model.Doacao;

/**
 * @author gabriel
 */
public class DoacaoController {
    
    public boolean cadastrarDoacao(Float qtdColetada, String dataColeta, String status, Integer idUsuario, String CPFDoador, Integer idDoador, String tipoSanguineo) throws ExceptionDAO {
        if (qtdColetada != null && dataColeta != null && status != null && idUsuario != null && CPFDoador != null && idDoador != null && tipoSanguineo != null) {
            Doacao doacao = new Doacao(qtdColetada, dataColeta, status, idUsuario, CPFDoador, idDoador, tipoSanguineo);
            doacao.cadastrarDoacao(doacao);
            return true;
        }
        return false;
    }
    
    public ArrayList<Doacao> leituraDoacao(String status) throws ExceptionDAO {
        Doacao doacao = new Doacao();
        ArrayList<Doacao> doacoes = doacao.leituraDoacao(status);
        
        return doacoes;
    }
    public ArrayList<Doacao> leituraDoacaoEspecifica(String paramBusca, int busca) throws ExceptionDAO {
        Doacao doacao = new Doacao();
        ArrayList<Doacao> doacoes = doacao.leituraDoacaoEspecifica(paramBusca, busca);
        
        return doacoes;
    }
    
     public Doacao carregarDadosDoacao(Integer idDoacao) throws ExceptionDAO {
        Doacao doacao = new Doacao();
        doacao.carregarDadosDoacao(idDoacao);
        
        return doacao;
    }
     
     public ArrayList<Doacao> obterDoacoes(Integer id) throws ExceptionDAO, SQLException {
         Doacao doacao = new Doacao();
        ArrayList<Doacao> doacoes =  doacao.obterDoacoes(id);
        
        
        return doacoes;
    }
}
