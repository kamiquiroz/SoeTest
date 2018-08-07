/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import entities.Ordenentrega;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author kami_
 */
@Stateless
public class OrdenentregaFacade extends AbstractFacade<Ordenentrega> {
    @PersistenceContext(unitName = "SOEInventarioPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public OrdenentregaFacade() {
        super(Ordenentrega.class);
    }
    
}
