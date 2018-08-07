/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entities.Insumos;
import entities.OrdenInsumos;
import entities.Ordenentrega;
import entities.Tecnicos;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author kami_
 */
@ManagedBean(name = "listadosController")
@SessionScoped
public class ListadosController {

    @EJB
    private ejb.OrdenInsumosFacade ejbOrdenInsumosFacade;
    @EJB
    private ejb.OrdenentregaFacade ejbOrdenentregaFacade;
    @EJB
    private ejb.InsumosFacade ejbInsumosFacade;
    @EJB
    private ejb.TecnicosFacade ejbTecnicosFacade;

    private List<Insumos> listadoInsumos;
    private List<Tecnicos> listadoTecnicos;
    private List<Ordenentrega> listadoOrdenes;
    private List<OrdenInsumos> listadoOrdenInsumos;

    /**
     * @return the listadoInsumos
     */
    public List<Insumos> getListadoInsumos() {
        try {
            listadoInsumos = ejbInsumosFacade.findAll();

        } catch (Exception ex) {
            Logger.getLogger(ListadosController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listadoInsumos;
    }

    /**
     * @param listadoInsumos the listadoInsumos to set
     */
    public void setListadoInsumos(List<Insumos> listadoInsumos) {
        this.listadoInsumos = listadoInsumos;
    }

    /**
     * @return the listadoTecnicos
     */
    public List<Tecnicos> getListadoTecnicos() {
        try {
            listadoTecnicos = ejbTecnicosFacade.findAll();

        } catch (Exception ex) {
            Logger.getLogger(ListadosController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listadoTecnicos;
    }

    /**
     * @param listadoTecnicos the listadoTecnicos to set
     */
    public void setListadoTecnicos(List<Tecnicos> listadoTecnicos) {
        this.listadoTecnicos = listadoTecnicos;
    }

    /**
     * @return the listadoOrdenes
     */
    public List<Ordenentrega> getListadoOrdenes() {
        try {
            listadoOrdenes = ejbOrdenentregaFacade.findAll();
        } catch (Exception ex) {
            Logger.getLogger(ListadosController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listadoOrdenes;
    }

    /**
     * @param listadoOrdenes the listadoOrdenes to set
     */
    public void setListadoOrdenes(List<Ordenentrega> listadoOrdenes) {
        this.listadoOrdenes = listadoOrdenes;
    }

    /**
     * @return the listadoOrdenInsumos
     */
    public List<OrdenInsumos> getListadoOrdenInsumos() {
         try {
            listadoOrdenInsumos = ejbOrdenInsumosFacade.findAll();
        } catch (Exception ex) {
            Logger.getLogger(ListadosController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listadoOrdenInsumos;
    }

    /**
     * @param listadoOrdenInsumos the listadoOrdenInsumos to set
     */
    public void setListadoOrdenInsumos(List<OrdenInsumos> listadoOrdenInsumos) {
        this.listadoOrdenInsumos = listadoOrdenInsumos;
    }
}
