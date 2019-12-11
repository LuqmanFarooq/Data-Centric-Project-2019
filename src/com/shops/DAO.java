package com.shops;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;




/**
 * Mysql Data Access Object (DAO) which connects and communicates with Mysql Database.
 * Responsible for all the functionality of database related methods.
 * @author Muhammad Luqman
 *
 */
public class DAO {

	 DataSource mysqlDS;

	
	/**
	 * Constructor setting up connection to the Mysql database.
	 * @throws NamingException If database error occurs.
	 */
	public DAO() throws Exception {
		Context context = new InitialContext();
		String jndiName = "java:comp/env/shops";
		mysqlDS = (DataSource) context.lookup(jndiName);
	}
	
public ArrayList<Store> loadStores() throws Exception {
		
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		myConn = mysqlDS.getConnection();

		String sql = "select * from store";

		myStmt = myConn.createStatement();

		myRs = myStmt.executeQuery(sql);
		
		ArrayList<Store> stores = new ArrayList<Store>();

		// process result set
		while (myRs.next()) {
			Store s = new Store();
			s.setsID(myRs.getInt("id"));
			s.setsName(myRs.getString("name"));
			s.setFounded(myRs.getString("Founded"));
			stores.add(s);
		}
		return stores;
	}

	public ArrayList<Product> manageProducts() throws SQLException {
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		myConn = mysqlDS.getConnection();

		String sql = "select * from product";

		myStmt = myConn.createStatement();

		myRs = myStmt.executeQuery(sql);
		
		ArrayList<Product> products = new ArrayList<Product>();

		// process result set
		while (myRs.next()) {
			Product p = new Product();
			p.setSid(myRs.getInt("sid"));
			p.setPid(myRs.getInt("pid"));
			p.setName(myRs.getString("prodName"));
			p.setPrice(myRs.getDouble("price"));
			products.add(p);
		}
		return products;
	}


	public void addStore(Store s) throws SQLException {
		int sID = s.getsID();
		String sName = s.getsName();
		String founded = s.getFounded();

		Connection conn = mysqlDS.getConnection();
		String query = "insert into store values(?, ?, ?);";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setInt(1, sID);
		statement.setString(2, sName);
		statement.setString(3, founded);
		statement.executeUpdate();
		
		conn.close();
	} // addStore()
	
	public ArrayList<Product> getProducts(Store store) throws SQLException {
		Connection conn = mysqlDS.getConnection();
		String query = "select * from product where sID=?;";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setInt(1, store.getsID());
		ResultSet rs = statement.executeQuery();
		ArrayList<Product> products = new ArrayList<Product>();

		while (rs.next()) {
			int sid = rs.getInt("sid");
			int pid = rs.getInt("pid");
			String name = rs.getString("prodName");
			double price = rs.getDouble("price");

			Product p = new Product(sid,pid , name,price, store);
			products.add(p);
		} // while
		
		conn.close();
		return products;
	} // getproducts()
	
	public ArrayList<Product> loadProducts() throws SQLException {
		Connection conn = mysqlDS.getConnection();
		String query = "select * from product join store on product.pid = store.sID;";
		PreparedStatement statement = conn.prepareStatement(query);
		ResultSet rs = statement.executeQuery();
		ArrayList<Product> products = new ArrayList<Product>();

		while (rs.next()) {
			int sid = rs.getInt("sid");
			int pid = rs.getInt("pid");
			String name = rs.getString("name");
			double price = rs.getDouble("price");
			int storeID = rs.getInt("sID");
			String storeName = rs.getString("name");
			String founded = rs.getString("founded");
			Store s = new Store(storeID, storeName, founded);
			Product p = new Product(sid,pid,name,price,s);
			products.add(p);
		} // while

		conn.close();
		return products;
	} // loadProducts()
	
	public ArrayList<Product> getProduct(Product product) {
		ArrayList<Product> products = new ArrayList<Product>();
		products.add(product);
		
		return products;
	} // getProduct()	
	
	//DELETE STORE METHOD
		public void deleteStore(Store store) throws Exception {
			Connection myConn = null;
			java.sql.PreparedStatement myStmt = null;


			myConn = mysqlDS.getConnection();
			String sql = "DELETE from store where id = ?;";
			myStmt = myConn.prepareStatement(sql);
			myStmt.setInt(1, store.getsID());
			myStmt.executeUpdate();
			
			myConn.close();
			
		}
		
		//DELETE PRODUCT METHOD
		public void deleteProduct(Product product) throws Exception {
			Connection myConn = null;
			java.sql.PreparedStatement myStmt = null;


			myConn = mysqlDS.getConnection();
			String sql = "DELETE from product where pid = ?;";
			myStmt = myConn.prepareStatement(sql);
			myStmt.setInt(1, product.getPid());
			myStmt.executeUpdate();
			
			myConn.close();
		} 
		
		
}
