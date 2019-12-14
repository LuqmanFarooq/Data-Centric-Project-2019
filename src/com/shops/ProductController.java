package com.shops;

import java.sql.*;
import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.naming.NamingException;

/**
 * A manufacturer controller class between web pages and DAO. Responsible for
 * all methods about Products and catching all Exceptions thrown by DAO. It is a
 * SessionScoped ManagedBean.
 *
 */
@ManagedBean
@SessionScoped
public class ProductController {

	private DAO dao;
	private ArrayList<Product> products;

	/**
	 * Constructor initializing DAO and the list of products.
	 */

	public ProductController() {
		super();
		try {
			dao = new DAO();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// load and save all products from dao for manage products page

	public void manageProducts() {
		System.out.println("In manageproducts()");
		try {
			products = dao.manageProducts();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	// load and save all products from dao for show products page

	public void loadProducts() {
		System.out.println("In loadproducts()");
		try {
			products = dao.loadProducts();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Parse Store object and call show products method from Mysql DAO.
	 * 
	 * @param store The selected Store object to parse into DAO.
	 * @return "store_product.xhtml" Go to store products Details page or empty
	 *         string to stay on page.
	 */
	public String showProducts(Store store) {
		try {
			setProducts(dao.getProducts(store));
		} catch (SQLException e) {
			FacesMessage message = new FacesMessage("Error: Cannot connect to MySQL Database");
			FacesContext.getCurrentInstance().addMessage(null, message);
			e.printStackTrace();

			return "";
		}

		return "store_product.xhtml";
	} // showproducts()

	public String showProduct(Product product) {
		setProducts(dao.getProduct(product));

		return "store_products.xhtml";
	} // showproduct()

	public String deleteProduct(Product product) {
		try {
			dao.deleteProduct(product);
		} catch (SQLIntegrityConstraintViolationException e) {
			FacesMessage message = new FacesMessage("Error: Cannot delete Product: " + product.getPid());
			FacesContext.getCurrentInstance().addMessage(null, message);
			e.printStackTrace();
		} catch (Exception e) {
			FacesMessage message = new FacesMessage("Error: Cannot connect to MySQL Database");
			FacesContext.getCurrentInstance().addMessage(null, message);
			e.printStackTrace();
		}

		return "list_products.xhtml";
	} // deleteProduct()

	// getters and setters
	public ArrayList<Product> getProducts() {
		return products;
	}

	public void setProducts(ArrayList<Product> products) {
		this.products = products;
	}

}
