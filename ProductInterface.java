/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.ac.tut.bl;

import java.sql.*;
import java.util.*;

/**
 *
 * @author MNCEDISI
 */
public interface ProductInterface<T> 
{
    public boolean addProduct(T t) throws SQLException;
    
    public boolean updateProduct(T t) throws SQLException;
    
    public boolean deleteProduct(Integer idNumber) throws SQLException;
    
    public T getProduct(Integer idNumber) throws SQLException;
    
    public List<T> getAllProduct() throws SQLException;
}
