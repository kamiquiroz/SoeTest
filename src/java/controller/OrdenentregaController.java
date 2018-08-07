package controller;

import entities.Ordenentrega;
import controller.util.JsfUtil;
import controller.util.PaginationHelper;
import ejb.OrdenentregaFacade;
import entities.Insumos;
import entities.OrdenInsumos;
import entities.Tecnicos;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;

@ManagedBean(name = "ordenentregaController")
@SessionScoped
public class OrdenentregaController implements Serializable {

    private Ordenentrega current;
    private DataModel items = null;
    @EJB
    private ejb.OrdenentregaFacade ejbFacade;
    @EJB
    private ejb.InsumosFacade ejbInsumosFacade;
    @EJB
    private ejb.TecnicosFacade ejbTecnicosFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;
    private Tecnicos currentTecnico;
    private List<OrdenInsumos> ordenInsumosTempo;
    private List<Insumos> listadoInsumosTemp;

    public OrdenentregaController() {
    }

    public Ordenentrega getSelected() {
        if (current == null) {
            current = new Ordenentrega();
            selectedItemIndex = -1;
        }
        return current;
    }

    private OrdenentregaFacade getFacade() {
        return ejbFacade;
    }
    
//    public void addTempInsumo(Insumos insumoTempo){
//        boolean sw = false;
//        int i = 0;
//        
//        
//        while (i<ordenInsumosTempo.size() && sw == false) {
//            OrdenInsumos l = ordenInsumosTempo.get(i);
//            if (insumoTempo.getCodigo().equals(l.getInsumos().getCodigo())) {
//                sw = true;               
//                this.ordenInsumosTempo.remove(l);
//            } else {
//                i++;
//            }
//        }
//        if (sw == false) {
//            OrdenInsumos insu = new OrdenInsumos();
//            insu.(insumoTempo.getCodigo());
//            insu.setDescripcion(insumoTempo.getDescripcion());
//           
//            this.listadoInsumosTemp.add(insu);
//        }
//        insumoTempo = null;
//    }


    public PaginationHelper getPagination() {
        if (pagination == null) {
            pagination = new PaginationHelper(10) {

                @Override
                public int getItemsCount() {
                    return getFacade().count();
                }

                @Override
                public DataModel createPageDataModel() {
                    return new ListDataModel(getFacade().findRange(new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}));
                }
            };
        }
        return pagination;
    }

    public String prepareList() {
        recreateModel();
        return "List";
    }

    public String prepareView() {
        current = (Ordenentrega) getItems().getRowData();
//        currentTecnico = (Tecnicos) (ejbTecnicosFacade.find(current.getCodigoTecnico()));
//        String nombre = (Tecnicos) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new Ordenentrega();
        selectedItemIndex = -1;
        return "Create";
    }

    public boolean validarExistencia() {
        Insumos insumo = ejbInsumosFacade.find(current.getOrdenInsumosCollection());

        Integer cant=current.getOrdenInsumosCollection().hashCode();
        if (insumo != null) {
            if (insumo.getExistencia() >= cant) {
                return true;
            }
        }
        return false;
    }

    public String create() {
        try {  
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("OrdenentregaCreated"));
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        current = (Ordenentrega) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("OrdenentregaUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (Ordenentrega) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        performDestroy();
        recreatePagination();
        recreateModel();
        return "List";
    }

    public String destroyAndView() {
        performDestroy();
        recreateModel();
        updateCurrentItem();
        if (selectedItemIndex >= 0) {
            return "View";
        } else {
            // all items were removed - go back to list
            recreateModel();
            return "List";
        }
    }

    private void performDestroy() {
        try {
            getFacade().remove(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("OrdenentregaDeleted"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
        }
    }

    private void updateCurrentItem() {
        int count = getFacade().count();
        if (selectedItemIndex >= count) {
            // selected index cannot be bigger than number of items:
            selectedItemIndex = count - 1;
            // go to previous page if last page disappeared:
            if (pagination.getPageFirstItem() >= count) {
                pagination.previousPage();
            }
        }
        if (selectedItemIndex >= 0) {
            current = getFacade().findRange(new int[]{selectedItemIndex, selectedItemIndex + 1}).get(0);
        }
    }

    public DataModel getItems() {
        if (items == null) {
            items = getPagination().createPageDataModel();
        }
        return items;
    }

    private void recreateModel() {
        items = null;
    }

    private void recreatePagination() {
        pagination = null;
    }

    public String next() {
        getPagination().nextPage();
        recreateModel();
        return "List";
    }

    public String previous() {
        getPagination().previousPage();
        recreateModel();
        return "List";
    }

    public SelectItem[] getItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), false);
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), true);
    }

    /**
     * @return the currentTecnico
     */
    public Tecnicos getCurrentTecnico() {
        return currentTecnico;
    }

    /**
     * @param currentTecnico the currentTecnico to set
     */
    public void setCurrentTecnico(Tecnicos currentTecnico) {
        this.currentTecnico = currentTecnico;
    }

    /**
     * @return the listadoInsumosTemp
     */
    public List<Insumos> getListadoInsumosTemp() {
        return listadoInsumosTemp;
    }

    /**
     * @param listadoInsumosTemp the listadoInsumosTemp to set
     */
    public void setListadoInsumosTemp(List<Insumos> listadoInsumosTemp) {
        this.listadoInsumosTemp = listadoInsumosTemp;
    }

    @FacesConverter(forClass = Ordenentrega.class)
    public static class OrdenentregaControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            OrdenentregaController controller = (OrdenentregaController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "ordenentregaController");
            return controller.ejbFacade.find(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Ordenentrega) {
                Ordenentrega o = (Ordenentrega) object;
                return getStringKey(o.getCodigoOrden());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Ordenentrega.class.getName());
            }
        }

    }

}
