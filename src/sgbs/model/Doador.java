package sgbs.model;

import java.util.ArrayList;
import sgbs.dao.DoadorDAO;
import sgbs.dao.ExceptionDAO;


public class Doador {
    private Integer idDoador;
    private String nome;
    private String sobrenome;
    private String sexo;
    private String DtNasc;
    private String CPF;
    private String tipoSanguineo;
    private Integer idUsuario;
    private Usuario usuario;
    private Endereco endereco;
    
    private ArrayList<Telefone> telefones = new ArrayList<Telefone>();
    
    // CONSTRUTOR
    public Doador(){};
    public Doador(String CPF, String nome, String sobrenome, String sexo, String DtNasc, String tipoSanguineo, Integer idUsuario, Endereco endereco, ArrayList<Telefone> telefones ) {
        this.CPF = CPF;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.sexo = sexo;
        this.DtNasc = DtNasc;
        this.tipoSanguineo = tipoSanguineo;
        this.idUsuario = idUsuario;
        this.endereco = endereco;
        this.telefones = telefones;
    }
    
    public Integer getIdDoador() {
        return idDoador;
    }
    
    public  boolean verificarDoador(String CPF) throws ExceptionDAO {
        // VERIFICAR SE O DOADOR ESTA CADASTRADO
        DoadorDAO doadorDAO = new DoadorDAO();
        boolean cadastrado = doadorDAO.ehDoadorCadastrado(CPF);
        
        return cadastrado;
    }
    
    public  boolean verificarDoador(int idDoador) throws ExceptionDAO {
        // VERIFICAR SE O DOADOR ESTA CADASTRADO
        DoadorDAO doadorDAO = new DoadorDAO();
        boolean cadastrado = doadorDAO.ehDoadorCadastrado(idDoador);
        
        return cadastrado;
    }
    
    public void cadastrarDoador(Doador doador) throws ExceptionDAO {
        // CRIANDO OBJETO DA CLASSE USUARIO DATA ACCESS MODEL 
        DoadorDAO doadorDAO = new DoadorDAO();
        doadorDAO.cadastraDoador(doador);
    };
    
    public String obterNomeCompletoDoador(int idDoador) throws ExceptionDAO {
        Doador doador = DoadorDAO.carregarDadosDoador(idDoador);
        
        return doador.getNome() + " " + doador.getSobrenome();
    }

    // GETTERS E SETTERS
    public void setIdDoador(Integer idDoador) {
        this.idDoador = idDoador;
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

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
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

    public  String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public String getTipoSanguineo() {
        return tipoSanguineo;
    }

    public void setTipoSanguineo(String tipoSanguineo) {
        this.tipoSanguineo = tipoSanguineo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public ArrayList<Telefone> getTelefone() {
        return telefones;
    }

    public void setTelefone(ArrayList<Telefone> telefone) {
        this.telefones = telefone;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }
    
    
}
