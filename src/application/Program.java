package application;

import java.util.*;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		
		SellerDao sellerDao = DaoFactory.createSellerDao();
		
		Seller seller = sellerDao.findById(3);
		System.out.println("###TESTING###");
		System.out.println(seller);
		
		System.out.println("\n###################");	
		Department dep = new Department(2, null);			
		List<Seller> list = sellerDao.findByDepartment(dep);
			for(Seller obj: list) {
			System.out.println(obj);
			
			System.out.println("\n###################");	
			list = sellerDao.findAll();
				for(Seller all: list) {
				System.out.println(all);	
		}
		

	}
	}
	}
