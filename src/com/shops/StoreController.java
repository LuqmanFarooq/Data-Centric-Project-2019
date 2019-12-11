package com.shops;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;



@ManagedBean
@SessionScoped

public class StoreController {

	private DAO dao;
	private ArrayList<Store> stores;
	
	public StoreController() {
		super();
		try {
			dao = new DAO();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String loadStores() {
		System.out.println("In loadStores()");
		try {
			stores = dao.loadStores();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public String addStore(Store store) {
		try {
			dao.addStore(store);
		} catch (SQLIntegrityConstraintViolationException e) {
			FacesMessage message = new FacesMessage("Error: Store ID " + store.getsID() + " already exists");
			FacesContext.getCurrentInstance().addMessage(null, message);
			e.printStackTrace();
			
			return "";
		} catch (Exception e) {
			FacesMessage message = new FacesMessage("Error: Cannot connect to MySQL Database");
			FacesContext.getCurrentInstance().addMessage(null, message);
			e.printStackTrace();
			
			return "";
		}
		
		return "list_stores.xhtml";
	} // addCourse()

	public String deleteStore(Store store){
        try {
            dao.deleteStore(store);
        } catch (SQLIntegrityConstraintViolationException e) {
            FacesMessage message = new FacesMessage("Error: Store "+ store.getsName() + " has not been deleted from MySQL DB,it contains products");
            FacesContext.getCurrentInstance().addMessage(null, message);
            e.printStackTrace();
        }
        catch (Exception e) {
            FacesMessage message = new FacesMessage("Error: Cannot connect to MySQL Database");
            FacesContext.getCurrentInstance().addMessage(null, message);
            e.printStackTrace();
        }

        return "list_stores.xhtml";
    } // deleteStore()
	
	public ArrayList<Store> getStores() {
		return stores;
	}
	
	
}
	
