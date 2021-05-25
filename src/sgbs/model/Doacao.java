package sgbs.model;


import java.sql.SQLException;
import java.util.ArrayList;
import sgbs.dao.DoacaoDAO;
import sgbs.dao.ExceptionDAO;


public class Doacao {
    private Integer Id_Doacao;
    private Integer Id_Doador;
    private Float qtdColetada;
    private String dataColeta;
    private String status;
    private Integer IdUsuario;
    private String CpfDoador;
    private String tipoSanguineo;
    private Analise analise;
    
    public Doacao() {}
    
    public Doacao(Float qtdColetada, String dataColeta, String status, Integer IdUsuario, String CpfDoador, Integer idDoador, String tipoSanguineo) {
        this.qtdColetada = qtdColetada;
        this.dataColeta = dataColeta;
        this.status = status;
        this.IdUsuario = IdUsuario;
        this.CpfDoador = CpfDoador;
        this.Id_Doador = idDoador;
        this.tipoSanguineo = tipoSanguineo;
    }
    
    public void cadastrarDoacao(Doacao doacao) throws ExceptionDAO {
        DoacaoDAO doacaoDao = new DoacaoDAO();
        doacaoDao.cadastrarDoacao(doacao);
    }
    
    public ArrayList<Doacao> leituraDoacao(String status) throws ExceptionDAO {
        DoacaoDAO doacaoDao = new DoacaoDAO();
        ArrayList<Doacao> doacoes = doacaoDao.leituraDoacao(status);
        
        return doacoes;
    }
    
    public ArrayList<Doacao> leituraDoacaoEspecifica(String paramBusca, int busca) throws ExceptionDAO {
        DoacaoDAO doacaoDao = new DoacaoDAO();
        ArrayList<Doacao> doacoes = doacaoDao.leituraDoacaoEspecifica(paramBusca, busca);
        
        return doacoes;
    }
    
    public Doacao carregarDadosDoacao(Integer idDoacao) throws ExceptionDAO {
        DoacaoDAO doacaoDao = new DoacaoDAO();
        Doacao doacao = doacaoDao.carregarDadosDoacao(idDoacao);
        
        return doacao;
    }
    
    public ArrayList<Doacao> obterDoacoes(Integer id) throws ExceptionDAO, SQLException {
        DoacaoDAO doacaoDAO = new DoacaoDAO();
        ArrayList<Doacao> doacoes =  doacaoDAO.obterDoacoes(id);
        
        return doacoes;
    }
  
    
    
    
    // GETTERS E SETTERS
    public  Integer getId_Doacao() {
        return Id_Doacao;
    }

    public void setId_Doacao(Integer Id_Doacao) {
        this.Id_Doacao = Id_Doacao;
    }

    public Integer getId_Doador() {
        return Id_Doador;
    }

    public void setId_Doador(Integer Id_Doador) {
        this.Id_Doador = Id_Doador;
    }
    
    
    public Float getQtdColetada() {
        return qtdColetada;
    }

    public void setQtdColetada(Float qtdColetada) {
        this.qtdColetada = qtdColetada;
    }

    public String getDataColeta() {
        return dataColeta;
    }

    public void setDataColeta(String dataColeta) {
        this.dataColeta = dataColeta;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getIdUsuario() {
        return IdUsuario;
    }

    public void setIdUsuario(Integer IdUsuario) {
        this.IdUsuario = IdUsuario;
    }

    public String getCpfDoador() {
        return CpfDoador;
    }

    public void setCpfDoador(String CpfDoador) {
        this.CpfDoador = CpfDoador;
    }

    public String getTipoSanguineo() {
        return tipoSanguineo;
    }

    public void setTipoSanguineo(String tipoSanguineo) {
        this.tipoSanguineo = tipoSanguineo;
    }
    
    
    
    public Analise getAnalise() {
        return analise;
    }


    public void setAnalise(Analise analise) {
        this.analise = analise;
    }
 
}
