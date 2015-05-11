/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package distoleos;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author moises
 */
@Entity
@Table(name = "producao", catalog = "armazenamento", schema = "")
@NamedQueries({
    @NamedQuery(name = "Producao.findAll", query = "SELECT p FROM Producao p"),
    @NamedQuery(name = "Producao.findByCodigo", query = "SELECT p FROM Producao p WHERE p.codigo = :codigo"),
    @NamedQuery(name = "Producao.findByDescricao", query = "SELECT p FROM Producao p WHERE p.descricao = :descricao"),
    @NamedQuery(name = "Producao.findByPreco", query = "SELECT p FROM Producao p WHERE p.preco = :preco"),
    @NamedQuery(name = "Producao.findByImpostoFederal", query = "SELECT p FROM Producao p WHERE p.impostoFederal = :impostoFederal"),
    @NamedQuery(name = "Producao.findByImpostoEstadual", query = "SELECT p FROM Producao p WHERE p.impostoEstadual = :impostoEstadual"),
    @NamedQuery(name = "Producao.findByIpi", query = "SELECT p FROM Producao p WHERE p.ipi = :ipi"),
    @NamedQuery(name = "Producao.findByIcms", query = "SELECT p FROM Producao p WHERE p.icms = :icms"),
    @NamedQuery(name = "Producao.findByFrete", query = "SELECT p FROM Producao p WHERE p.frete = :frete"),
    @NamedQuery(name = "Producao.findByGanhoLivre", query = "SELECT p FROM Producao p WHERE p.ganhoLivre = :ganhoLivre"),
    @NamedQuery(name = "Producao.findByCustosGerais", query = "SELECT p FROM Producao p WHERE p.custosGerais = :custosGerais"),
    @NamedQuery(name = "Producao.findByPrecoVenda", query = "SELECT p FROM Producao p WHERE p.precoVenda = :precoVenda"),
    @NamedQuery(name = "Producao.findByCategoria", query = "SELECT p FROM Producao p WHERE p.categoria = :categoria")})
public class Producao implements Serializable {
    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo")
    private Integer codigo;
    @Column(name = "Descricao")
    private String descricao;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "Preco")
    private Float preco;
    @Column(name = "ImpostoFederal")
    private Float impostoFederal;
    @Column(name = "ImpostoEstadual")
    private Float impostoEstadual;
    @Column(name = "IPI")
    private Float ipi;
    @Column(name = "ICMS")
    private Float icms;
    @Column(name = "Frete")
    private Float frete;
    @Column(name = "GanhoLivre")
    private Float ganhoLivre;
    @Column(name = "CustosGerais")
    private Float custosGerais;
    @Column(name = "PrecoVenda")
    private Float precoVenda;
    @Column(name = "Categoria")
    private String categoria;

    public Producao() {
    }

    public Producao(Integer codigo) {
        this.codigo = codigo;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        Integer oldCodigo = this.codigo;
        this.codigo = codigo;
        changeSupport.firePropertyChange("codigo", oldCodigo, codigo);
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        String oldDescricao = this.descricao;
        this.descricao = descricao;
        changeSupport.firePropertyChange("descricao", oldDescricao, descricao);
    }

    public Float getPreco() {
        return preco;
    }

    public void setPreco(Float preco) {
        Float oldPreco = this.preco;
        this.preco = preco;
        changeSupport.firePropertyChange("preco", oldPreco, preco);
    }

    public Float getImpostoFederal() {
        return impostoFederal;
    }

    public void setImpostoFederal(Float impostoFederal) {
        Float oldImpostoFederal = this.impostoFederal;
        this.impostoFederal = impostoFederal;
        changeSupport.firePropertyChange("impostoFederal", oldImpostoFederal, impostoFederal);
    }

    public Float getImpostoEstadual() {
        return impostoEstadual;
    }

    public void setImpostoEstadual(Float impostoEstadual) {
        Float oldImpostoEstadual = this.impostoEstadual;
        this.impostoEstadual = impostoEstadual;
        changeSupport.firePropertyChange("impostoEstadual", oldImpostoEstadual, impostoEstadual);
    }

    public Float getIpi() {
        return ipi;
    }

    public void setIpi(Float ipi) {
        Float oldIpi = this.ipi;
        this.ipi = ipi;
        changeSupport.firePropertyChange("ipi", oldIpi, ipi);
    }

    public Float getIcms() {
        return icms;
    }

    public void setIcms(Float icms) {
        Float oldIcms = this.icms;
        this.icms = icms;
        changeSupport.firePropertyChange("icms", oldIcms, icms);
    }

    public Float getFrete() {
        return frete;
    }

    public void setFrete(Float frete) {
        Float oldFrete = this.frete;
        this.frete = frete;
        changeSupport.firePropertyChange("frete", oldFrete, frete);
    }

    public Float getGanhoLivre() {
        return ganhoLivre;
    }

    public void setGanhoLivre(Float ganhoLivre) {
        Float oldGanhoLivre = this.ganhoLivre;
        this.ganhoLivre = ganhoLivre;
        changeSupport.firePropertyChange("ganhoLivre", oldGanhoLivre, ganhoLivre);
    }

    public Float getCustosGerais() {
        return custosGerais;
    }

    public void setCustosGerais(Float custosGerais) {
        Float oldCustosGerais = this.custosGerais;
        this.custosGerais = custosGerais;
        changeSupport.firePropertyChange("custosGerais", oldCustosGerais, custosGerais);
    }

    public Float getPrecoVenda() {
        return precoVenda;
    }

    public void setPrecoVenda(Float precoVenda) {
        Float oldPrecoVenda = this.precoVenda;
        this.precoVenda = precoVenda;
        changeSupport.firePropertyChange("precoVenda", oldPrecoVenda, precoVenda);
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        String oldCategoria = this.categoria;
        this.categoria = categoria;
        changeSupport.firePropertyChange("categoria", oldCategoria, categoria);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigo != null ? codigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Producao)) {
            return false;
        }
        Producao other = (Producao) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "distoleos.Producao[ codigo=" + codigo + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
