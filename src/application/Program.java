package application;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

public class Program {

	public static void main(String[] args) {
		
		
				
				System.out.println("\n###################");	
				DepartmentDao dao = DaoFactory.createDepartmentDao();
				Department newDepartment = new Department(null, "Music");
				dao.insert(newDepartment);
				System.out.println("Inserted! New id: " + newDepartment.getId());
				
		}
		

	
	}
	
