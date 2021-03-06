/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author kami_
 */
@Entity
@Table(name = "ordenentrega")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ordenentrega.findAll", query = "SELECT o FROM Ordenentrega o"),
    @NamedQuery(name = "Ordenentrega.findByCodigoOrden", query = "SELECT o FROM Ordenentrega o WHERE o.codigoOrden = :codigoOrden")})
public class Ordenentrega implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "codigoOrden")
    private Integer codigoOrden;
    @JoinColumn(name = "codigoTecnico", referencedColumnName = "codigo")
    @ManyToOne
    private Tecnicos codigoTecnico;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ordenentrega")
    private Collection<OrdenInsumos> ordenInsumosCollection;

    public Ordenentrega() {
    }

    public Ordenentrega(Integer codigoOrden) {
        this.codigoOrden = codigoOrden;
    }

    public Integer getCodigoOrden() {
        return codigoOrden;
    }

    public void setCodigoOrden(Integer codigoOrden) {
        this.codigoOrden = codigoOrden;
    }

    public Tecnicos getCodigoTecnico() {
        return codigoTecnico;
    }

    public void setCodigoTecnico(Tecnicos codigoTecnico) {
        this.codigoTecnico = codigoTecnico;
    }

    @XmlTransient
    public Collection<OrdenInsumos> getOrdenInsumosCollection() {
        return ordenInsumosCollection;
    }

    public void setOrdenInsumosCollection(Collection<OrdenInsumos> ordenInsumosCollection) {
        this.ordenInsumosCollection = ordenInsumosCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoOrden != null ? codigoOrden.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ordenentrega)) {
            return false;
        }
        Ordenentrega other = (Ordenentrega) object;
        if ((this.codigoOrden == null && other.codigoOrden != null) || (this.codigoOrden != null && !this.codigoOrden.equals(other.codigoOrden))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Ordenentrega[ codigoOrden=" + codigoOrden + " ]";
    }
    
}
