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
		
		public HeadOfficeController() {
			super();
			try {
				mondao = new MongoDAO();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
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
				return "list_offices";
			} catch (MongoWriteException e) {
				FacesMessage message = new FacesMessage("Error: Store ID "+off._id+" already exist");
				FacesContext.getCurrentInstance().addMessage(null, message);
				return null;
			} catch (Exception e) {
				return null;
			}

		}

		
		public ArrayList<HeadOffice> getOffices() {
			return offices;
		}

		public void setOffices(ArrayList<HeadOffice> offices) {
			this.offices = offices;
		}
		
		

}
