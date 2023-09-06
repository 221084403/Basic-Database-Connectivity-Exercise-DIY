/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.ac.tut.bl;

import java.sql.*;
import java.util.*;
import za.ac.tut.entity.*;

/**
 *
 * @author MNCEDISI
 */
public class ProductManager implements ProductInterface<Product>
{
    private Connection connection;
    
    public ProductManager(String dbURL , String userName , String password) throws SQLException 
    {
        this.connection = DriverManager.getConnection(dbURL, userName, password);
    }

    @Override
    public boolean addProduct(Product product) throws SQLException 
    {
        String sql = "INSERT INTO PRODUCT_TBL VALUES (? ,? , ? , ? , ?)";
        
        PreparedStatement ps = getConnection().prepareStatement(sql);
        
        ps.setInt(1, product.getIdNumber());
        ps.setString(2, product.getDescription());
        ps.setInt(3, product.getQuantity());
        ps.setDouble(4, product.getUnityPrice());
        ps.setTimestamp(5, product.getTimestamp());
        
        ps.executeUpdate();
        
        return true;
    }

    @Override
    public boolean updateProduct(Product product) throws SQLException 
    {
        String sql = "UPDATE PRODUCT_TBL "+
                     "SET Description = ? , Quantity  = ? , UnityPrice = ? , TimeStamp = ? "+
                     "WHERE IdNumber = ? ";
        
        PreparedStatement ps = getConnection().prepareStatement(sql);
        
        ps.setString(1, product.getDescription());
        ps.setInt(2, product.getQuantity());
        ps.setDouble(3, product.getUnityPrice());
        ps.setTimestamp(4, product.getTimestamp());
        ps.setInt(5, product.getIdNumber());
        
        ps.executeUpdate();
        
        return true;
        
    }

    @Override
    public boolean deleteProduct(Integer idNumber) throws SQLException 
    {
        String sql = "DELETE FROM PRODUCT_TBL "+
                     "WHERE IdNumber = ? ";
        
        PreparedStatement ps = getConnection().prepareStatement(sql);
        
        ps.setInt(1, idNumber);
        
        ps.executeUpdate();
        
        return true;
                
    }

    @Override
    public Product getProduct(Integer idNumber) throws SQLException 
    {
        String sql = "SELECT * FROM PRODUCT_TBL "+
                     "WHERE IdNumber = ? ";
        
        PreparedStatement ps = getConnection().prepareStatement(sql);
        
        ps.setInt(1, idNumber);
        
        ResultSet rs = ps.executeQuery();
        
        if(rs.next())
        {
            String description = rs.getString("Description");
            Integer quantity = rs.getInt("Quantity");
            Double unityPrice = rs.getDouble("UnityPrice");
            Timestamp timestamp = rs.getTimestamp("TimeStamp");
            
            rs.close();;
            return  new Product(idNumber, description, quantity, unityPrice, timestamp);
        }
        else
        {
            rs.close();
            return null;
        }
    }

    @Override
    public List<Product> getAllProduct() throws SQLException
    {
        String sql = "SELECT * FROM PRODUCT_TBL "+
                     "ORDER BY IdNumber ASC ";
        
        PreparedStatement ps = getConnection().prepareStatement(sql);
        
        ResultSet rs = ps.executeQuery();
        
        List<Product> theProduct = new ArrayList<>();
        
        while(rs.next())
        {
            Integer idNumber = rs.getInt("IdNumber");
            String description = rs.getString("Description");
            Integer quantity = rs.getInt("Quantity");
            Double unityPrice = rs.getDouble("UnityPrice");
            Timestamp timestamp = rs.getTimestamp("TimeStamp");
            
            Product product = new Product(idNumber, description, quantity, unityPrice, timestamp);
            
            theProduct.add(product);
        }
        
        rs.close();
        return theProduct;
        
    }
    
    public Connection getConnection() {
        return connection;
    }
}
