package com.shops;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 * A manufacturer controller class between web pages and DAO. Responsible for
 * all methods about Stores and catching all Exceptions thrown by DAO. It is a
 * SessionScoped ManagedBean.
 *
 */
@ManagedBean
@SessionScoped

public class StoreController {

	private DAO dao;
	private ArrayList<Store> stores;

	/**
	 * Constructor initializing DAO and the list of Stores.
	 */

	public StoreController() {
		super();
		try {
			dao = new DAO();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// load and save stores from dao
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

	/**
	 * Parse Store object and call add store method from DAO.
	 * 
	 * @param store The selected Store object to parse into DAO.
	 * @return "list_stores.xhtml" Go to Manage stores page or empty string to stay
	 *         on page.
	 */
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
	} // add store()

	/**
	 * Parse Store object and call delete store method from DAO.
	 * 
	 * @param store The selected Store object to parse into DAO.
	 * @return "list_stores.xhtml" Refresh the page.
	 */
	public String deleteStore(Store store) {
		try {
			dao.deleteStore(store);
		} catch (SQLIntegrityConstraintViolationException e) {
			FacesMessage message = new FacesMessage(
					"Error: Store " + store.getsName() + " has not been deleted from MySQL DB,it contains products");
			FacesContext.getCurrentInstance().addMessage(null, message);
			e.printStackTrace();
		} catch (Exception e) {
			FacesMessage message = new FacesMessage("Error: Cannot connect to MySQL Database");
			FacesContext.getCurrentInstance().addMessage(null, message);
			e.printStackTrace();
		}

		return "list_stores.xhtml";
	} // deleteStore()

	// === Getters and setters ===
	public ArrayList<Store> getStores() {
		return stores;
	}

}// class
