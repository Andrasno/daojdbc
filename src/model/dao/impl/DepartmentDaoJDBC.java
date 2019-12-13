package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.DepartmentDao;
import model.entities.Department;

public class DepartmentDaoJDBC implements DepartmentDao {
	private Connection conn;
	
	public DepartmentDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	@Override
	public void insert(Department obj) {
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement("INSERT INTO department (Name)"
										+ "VALUES (?)", Statement.RETURN_GENERATED_KEYS);
			
			stmt.setString(1, obj.getName());
			
			stmt.executeUpdate();
			int rowsAffected = stmt.executeUpdate();
			
			if (rowsAffected > 0) {
				ResultSet rs = stmt.getGeneratedKeys();
				if (rs.next()) {
					int id = rs.getInt(1);
					obj.setId(id);
				}
			}
			else {
				throw new DbException("Unexpected error! No rows affected!");
			}
			}catch(SQLException e) {
			throw new DbException(e.getMessage());
			
		}finally {
			DB.closeStatement(stmt);
		}
		
	}

	@Override
	public void update(Department obj) {
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement("UPDATE department SET Name = ?"
										+ "WHERE Id = ?");
			stmt.setString(1, obj.getName());
			stmt.setInt(2, obj.getId());
			
			stmt.executeUpdate();
			
			stmt.executeUpdate();
		}catch(SQLException e) {
			throw new DbException(e.getMessage());
		}finally {
			DB.closeStatement(stmt);
		}
		
	}

	@Override
	public void deleteById(Integer id) {
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement("DELETE FROM department WHERE Id = ?");
			
			stmt.setInt(1, id);
		}catch(SQLException e) {
			throw new DbException(e.getMessage());
		}finally {
			DB.closeStatement(stmt);
		}
		
	}

	@Override
	public Department findById(Integer id) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.prepareStatement(
				"SELECT * FROM department WHERE Id = ?");
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			if (rs.next()) {
				Department obj = new Department();
				obj.setId(rs.getInt("Id"));
				obj.setName(rs.getString("Name"));
				return obj;
			}
			return null;
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(stmt);
			DB.closeResultSet(rs);
		}
	}

	@Override
	public List<Department> findAll() {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.prepareStatement(
				"SELECT * FROM department ORDER BY Name");
			rs = stmt.executeQuery();

			List<Department> list = new ArrayList<>();

			while (rs.next()) {
				Department obj = new Department();
				obj.setId(rs.getInt("Id"));
				obj.setName(rs.getString("Name"));
				list.add(obj);
			}
			return list;
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(stmt);
			DB.closeResultSet(rs);
		}
	}

}
