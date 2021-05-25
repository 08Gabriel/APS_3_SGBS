package sgbs.controller;

import java.util.ArrayList;
import sgbs.dao.ExceptionDAO;
import sgbs.model.Doador;
import sgbs.model.Endereco;
import sgbs.model.Telefone;

/**
 * @author gabriel
 */
public class DoadorController {
    // VERIFICANDO SE POSSUI ALGUM VALOR NULO
    public boolean cadastrarDoador(String nome, String sobrenome, String sexo, String DtNasc, String CPF, String tipoSanguineo, Integer idUsuario, Endereco endereco, ArrayList<Telefone> telefones) throws ExceptionDAO {
        
        if (nome != null && nome.length() > 0 && sobrenome != null && sobrenome.length() > 0 && !sexo.equals("Selecione") && DtNasc != null && !tipoSanguineo.equals("Selecione")  && idUsuario != null) 
        {   
            // VERIFICANDO ENDEREÇO
            if(endereco.getRua() != null && endereco.getNumero() != null && endereco.getCEP() != null && endereco.getBairro() != null && endereco.getCidade() != null && !endereco.getUF().equals("Selecione")) 
            {
                // CRIANDO OBJETO DOADOR
                Doador doador = new Doador(CPF, nome, sobrenome,  sexo,  DtNasc,  tipoSanguineo,  idUsuario, endereco, telefones);
                doador.cadastrarDoador(doador);
                return true;
            }
        }
        return false;

    }
    
    public boolean verificarDoador(String CPF) throws ExceptionDAO {
        boolean cadastrado = new Doador().verificarDoador(CPF);
        
        return cadastrado;
    }
    
    public boolean verificarDoador(int idDoador) throws ExceptionDAO {
        boolean cadastrado = new Doador().verificarDoador(idDoador);
        
        return cadastrado;
    }
    
    public String obterNomeCompletoDoador(int idDoador) throws ExceptionDAO {
        String nome = new Doador().obterNomeCompletoDoador(idDoador);
        
        return nome;
    }
   
  
}
