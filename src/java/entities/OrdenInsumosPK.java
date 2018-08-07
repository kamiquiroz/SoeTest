/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author kami_
 */
@Embeddable
public class OrdenInsumosPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "codigoOrden")
    private int codigoOrden;
    @Basic(optional = false)
    @NotNull
    @Column(name = "codigoInsumo")
    private int codigoInsumo;

    public OrdenInsumosPK() {
    }

    public OrdenInsumosPK(int codigoOrden, int codigoInsumo) {
        this.codigoOrden = codigoOrden;
        this.codigoInsumo = codigoInsumo;
    }

    public int getCodigoOrden() {
        return codigoOrden;
    }

    public void setCodigoOrden(int codigoOrden) {
        this.codigoOrden = codigoOrden;
    }

    public int getCodigoInsumo() {
        return codigoInsumo;
    }

    public void setCodigoInsumo(int codigoInsumo) {
        this.codigoInsumo = codigoInsumo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) codigoOrden;
        hash += (int) codigoInsumo;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OrdenInsumosPK)) {
            return false;
        }
        OrdenInsumosPK other = (OrdenInsumosPK) object;
        if (this.codigoOrden != other.codigoOrden) {
            return false;
        }
        if (this.codigoInsumo != other.codigoInsumo) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.OrdenInsumosPK[ codigoOrden=" + codigoOrden + ", codigoInsumo=" + codigoInsumo + " ]";
    }
    
}
