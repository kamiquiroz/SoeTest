/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author kami_
 */
@Entity
@Table(name = "orden_insumos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OrdenInsumos.findAll", query = "SELECT o FROM OrdenInsumos o"),
    @NamedQuery(name = "OrdenInsumos.findByCodigoOrden", query = "SELECT o FROM OrdenInsumos o WHERE o.ordenInsumosPK.codigoOrden = :codigoOrden"),
    @NamedQuery(name = "OrdenInsumos.findByCodigoInsumo", query = "SELECT o FROM OrdenInsumos o WHERE o.ordenInsumosPK.codigoInsumo = :codigoInsumo"),
    @NamedQuery(name = "OrdenInsumos.findByCantidad", query = "SELECT o FROM OrdenInsumos o WHERE o.cantidad = :cantidad")})
public class OrdenInsumos implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected OrdenInsumosPK ordenInsumosPK;
    @Column(name = "cantidad")
    private Integer cantidad;
    @JoinColumn(name = "codigoInsumo", referencedColumnName = "codigo", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Insumos insumos;
    @JoinColumn(name = "codigoOrden", referencedColumnName = "codigoOrden", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Ordenentrega ordenentrega;

    public OrdenInsumos() {
    }

    public OrdenInsumos(OrdenInsumosPK ordenInsumosPK) {
        this.ordenInsumosPK = ordenInsumosPK;
    }

    public OrdenInsumos(int codigoOrden, int codigoInsumo) {
        this.ordenInsumosPK = new OrdenInsumosPK(codigoOrden, codigoInsumo);
    }

    public OrdenInsumosPK getOrdenInsumosPK() {
        return ordenInsumosPK;
    }

    public void setOrdenInsumosPK(OrdenInsumosPK ordenInsumosPK) {
        this.ordenInsumosPK = ordenInsumosPK;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Insumos getInsumos() {
        return insumos;
    }

    public void setInsumos(Insumos insumos) {
        this.insumos = insumos;
    }

    public Ordenentrega getOrdenentrega() {
        return ordenentrega;
    }

    public void setOrdenentrega(Ordenentrega ordenentrega) {
        this.ordenentrega = ordenentrega;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ordenInsumosPK != null ? ordenInsumosPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OrdenInsumos)) {
            return false;
        }
        OrdenInsumos other = (OrdenInsumos) object;
        if ((this.ordenInsumosPK == null && other.ordenInsumosPK != null) || (this.ordenInsumosPK != null && !this.ordenInsumosPK.equals(other.ordenInsumosPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.OrdenInsumos[ ordenInsumosPK=" + ordenInsumosPK + " ]";
    }
    
}
