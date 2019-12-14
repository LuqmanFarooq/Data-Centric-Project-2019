package com.shops;

	import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
	import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.mongodb.MongoWriteException;


	@ManagedBean
	@SessionScoped
	public class HeadOfficeController {
		
		private ArrayList<HeadOffice> offices;
		
		public ArrayList<HeadOffice> getProducts() {
			return offices;
		}
		
		private MongoDAO mondao;
		
		/**
		 * Constructor initializing mongoDAO and the list of offices.
		 */
		public HeadOfficeController() {
			super();
			try {
				mondao = new MongoDAO();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// load and save offices from mongodao
		public String loadOffices() {
			System.out.println("In loadOffices()");
			try {
				offices = mondao.loadOffices();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
		
		//Adding a New Head Office
		public String addHeadOffice(HeadOffice off) {
			System.out.println(off.getId()+" "+off.getLocation());
			
			try {
				mondao.addHeadOffice(off);
				return "list_office.xhtml";
			} catch (MongoWriteException e) {
				FacesMessage message = new FacesMessage("Error: Store ID "+off._id+" already has Location");
				FacesContext.getCurrentInstance().addMessage(null, message);
				return null;
			} catch (Exception e) {
				FacesMessage message = new FacesMessage("Error: Cannot connect to MySQL Database");
	            FacesContext.getCurrentInstance().addMessage(null, message);
	            e.printStackTrace();	
			}
			return null;
		}
		//Adding a New Head Office
				public String deleteHeadOffice(HeadOffice off) {
					System.out.println(off.getId()+" "+off.getLocation());
					try {
						mondao.deleteHeadOffice(off);
						return "list_office.xhtml";
					} 
					catch (Exception e) {
						FacesMessage message = new FacesMessage("Error: Cannot connect to MySQL Database");
			            FacesContext.getCurrentInstance().addMessage(null, message);
			            e.printStackTrace();	
					}
					return null;
				}

		// getters and setters
		public ArrayList<HeadOffice> getOffices() {
			return offices;
		}

		public void setOffices(ArrayList<HeadOffice> offices) {
			this.offices = offices;
		}
		
		

}
