package sgbs.controller;
import sgbs.dao.ExceptionDAO;
import sgbs.model.Usuario;

/**
 * @author gabriel
 */
public class UsuarioController {
    
    
    public boolean cadastrarUsuario(String nome, String sobrenome, String DtNasc, 
            String nomeDeUsuario, String senha, String CPF, String numeroFuncional, String acessoLab, String admin) throws ExceptionDAO {
        Usuario usuario = null;
        // VERIFICANDO SE POSSUI ALGUM VALOR NULO
        if ( (nome != null && nome.length() > 0) && (sobrenome != null && sobrenome.length() > 0)) 
        {
            if ((DtNasc != null && DtNasc.length() > 0) && (nomeDeUsuario != null) && (senha != null && senha.length() > 0) ) 
            {
                if((CPF != null) && (numeroFuncional != null)) {
                    if((acessoLab != null) && (admin != null)) {
                        //CRIANDO USARIO
                        usuario = new Usuario(admin, acessoLab, nome, sobrenome, DtNasc, nomeDeUsuario,
                        senha, CPF, numeroFuncional);
                        usuario.cadastrar(usuario);
                        return true;
                    }
                }
            }
            
        }
        // ALGUM CAMPO NÃO FOI PREENCHIDO CORRETAMENTE
        return false;
    }
    
    public boolean validarUsuario(String nomeUsuario, String senha) throws ExceptionDAO {
        boolean sucesso = false;
        if (nomeUsuario != null && senha != null) {
            sucesso = Usuario.validarUsuario(nomeUsuario, senha);
            
            if (sucesso)
                return true;
        } 
        return false;
    }
    
    public boolean validarUsuarioAdmin(String nomeUsuario, String senha) throws ExceptionDAO {
        boolean sucesso = false;
        if (nomeUsuario != null && senha != null) {
            Usuario usr = new Usuario();
            sucesso = usr.validarUsuarioAdmin(nomeUsuario, senha);
            
            if (sucesso)
                return true;
        } 
        return false;
    }
    
    public boolean nomeDeUsuarioJaExiste(String nomeUsuario) throws ExceptionDAO {
        Usuario usuario = new Usuario();
        return usuario.nomeDeUsuarioJaExiste(nomeUsuario);
     }
    public void atulizarUsuario(Usuario usuario) throws ExceptionDAO {
        Usuario usuarioClasse = new Usuario();
        usuarioClasse.atulizarUsuario(usuario);
    }
    
}
