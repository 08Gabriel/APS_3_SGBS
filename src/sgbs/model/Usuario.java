
package sgbs.model;

import sgbs.dao.ExceptionDAO;
import sgbs.dao.UsuarioDAO;


public class Usuario {
    private Integer idUsuario;
    private String admin;
    private String acessoLab;
    private String nome;
    private String sobrenome;
    private String DtNasc;
    private String nomeDeUsuario;
    private String senha;
    private String CPF;
    private String numeroFuncional;
    private Endereco endereco;
    

    public Usuario(String admin, String acessoLab, String nome, String sobrenome, String DtNasc, String nomeDeUsuario, String senha, String CPF, String numeroFuncional) {
        this.admin = admin;
        this.acessoLab = acessoLab;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.DtNasc = DtNasc;
        this.nomeDeUsuario = nomeDeUsuario;
        this.senha = senha;
        this.CPF = CPF;
        this.numeroFuncional = numeroFuncional;
    }
    public Usuario() {}
    
    public void cadastrar(Usuario usuario) throws ExceptionDAO{
        // CRIANDO OBJETO DA CLASSE USUARIO DATA ACCESS MODEL 
        new UsuarioDAO().cadastrarUsuario(usuario);
    };
    
    public static boolean validarUsuario(String nomeUsuario, String senha ) throws ExceptionDAO {
        UsuarioDAO usuario = new UsuarioDAO();
        
        return usuario.validarUsuario(nomeUsuario, senha);    
    }
    
    public boolean validarUsuarioAdmin(String nomeUsuario, String senha ) throws ExceptionDAO {
        UsuarioDAO usuario = new UsuarioDAO();
        
        return usuario.validarUsuarioAdmin(nomeUsuario, senha);    
    }
    
    public boolean nomeDeUsuarioJaExiste(String nomeUsuario) throws ExceptionDAO {
         UsuarioDAO usuarioDAO = new UsuarioDAO();
         return usuarioDAO.nomeDeUsuarioJaExiste(nomeUsuario);
    }
    public void atulizarUsuario(Usuario usuario) throws ExceptionDAO {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        usuarioDAO.AtualizarUsuario(usuario);
    }
    
    // GETTERS E SETTERS
    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }

    public String getAcessoLab() {
        return acessoLab;
    }

    public void setAcessoLab(String acessoLab) {
        this.acessoLab = acessoLab;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getDtNasc() {
        return DtNasc;
    }

    public void setDtNasc(String DtNasc) {
        this.DtNasc = DtNasc;
    }

    public  String getNomeDeUsuario() {
        return nomeDeUsuario;
    }

    public void setNomeDeUsuario(String nomeDeUsuario) {
        this.nomeDeUsuario = nomeDeUsuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public String getNumeroFuncional() {
        return numeroFuncional;
    }

    public void setNumeroFuncional(String numeroFuncional) {
        this.numeroFuncional = numeroFuncional;
    }

    public  Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }
    
}
