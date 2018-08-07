/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import entities.Tecnicos;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author kami_
 */
@Stateless
public class TecnicosFacade extends AbstractFacade<Tecnicos> {
    @PersistenceContext(unitName = "SOEInventarioPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TecnicosFacade() {
        super(Tecnicos.class);
    }
    
}
