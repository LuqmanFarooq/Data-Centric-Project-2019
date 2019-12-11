package com.shops;

import java.sql.*;
import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.naming.NamingException;


@ManagedBean
@SessionScoped
public class ProductController {

	private DAO dao;
	private ArrayList<Product> products;
	
	public ProductController() {
		super();
		try {
			dao = new DAO();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void manageProducts() {
		System.out.println("In manageproducts()");
		try {
			products = dao.manageProducts();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void loadProducts() {
		System.out.println("In loadproducts()");
		try {
			products = dao.loadProducts();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
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
	} // showStudents()

	public String showProduct(Product product) {
        setProducts(dao.getProduct(product));

        return "store_products.xhtml";
    } // showStudent()
	
	public String deleteProduct(Product product){
        try {
            dao.deleteProduct(product);
        } catch (SQLIntegrityConstraintViolationException e) {
            FacesMessage message = new FacesMessage("Error: Cannot delete Product: " + product.getPid() );
            FacesContext.getCurrentInstance().addMessage(null, message);
            e.printStackTrace();
        }
        catch (Exception e) {
            FacesMessage message = new FacesMessage("Error: Cannot connect to MySQL Database");
            FacesContext.getCurrentInstance().addMessage(null, message);
            e.printStackTrace();
        }

        return "list_products.xhtml";
    } // deleteStore()
	
	public ArrayList<Product> getProducts() {
		return products;
	}

	public void setProducts(ArrayList<Product> products) {
		this.products = products;
	}
	
}
