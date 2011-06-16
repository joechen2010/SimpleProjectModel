package com;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.model.DataModel;

import org.primefaces.model.DualListModel;

import com.pojo.DataSource;
import com.pojo.LocalDataModel;
import com.pojo.UserInfo;
import com.pojo.UserInfoDAO;

@ManagedBean(name="mBean")
@RequestScoped
public class MBean extends DataSource{
	
	private String password ="555555555555555555";
	private double rating;
	private DualListModel cities; 
	private Date date;
	private TimeZone timeZone;
	private UserInfoDAO dao = new UserInfoDAO();
	
	
	public MBean() {   
		
		super("name");
        //Players   
		List citiesSource = new ArrayList();   
        List citiesTarget = new ArrayList();   
  
        citiesSource.add("Istanbul");   
        citiesSource.add("Ankara");   
        citiesSource.add("Izmir");   
        citiesSource.add("Antalya");   
        citiesSource.add("Bursa");   
  
        cities = new DualListModel(citiesSource, citiesTarget);  
        
       
  
    }   

	public String test(){
		List list = dao.findAll();
		System.out.println(list.size()+">>>>>>>>>>>");
		return null;
	}
	

	@SuppressWarnings("unchecked")
	public DataModel getMyPagedDataModel() {
		if (onePageDataModel == null) {
            onePageDataModel = new LocalDataModel(dao, pageSize, sortColumnName, true);
        }
        return onePageDataModel;
	}      
	
	
	public String handleCommand(String command, String[] params) {
		test();
        if(command.equals("greet"))   
            return "Hello " + params[0];   
        else if(command.equals("date"))   
            return new Date().toString();   
        else  
            return command + " not found";   
    }   
	

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}


	public DualListModel getCities() {
		return cities;
	}


	public void setCities(DualListModel cities) {
		this.cities = cities;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	 public TimeZone getTimeZone() {
	        return java.util.TimeZone.getDefault();
	}

	public UserInfoDAO getDao() {
		return dao;
	}

	public void setDao(UserInfoDAO dao) {
		this.dao = dao;
	}

	 protected boolean isDefaultAscending(String sortColumn) {
	        return true;
	 }
	
}
