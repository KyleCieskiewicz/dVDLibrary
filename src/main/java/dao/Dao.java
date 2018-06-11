/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.Item;
import java.util.List;

/**
 *
 * @author kylecieskiewicz
 */
public interface Dao {
    
    
	    Item addItem(String itemId, Item item)
                throws DaoException;
	    
	    
	    List<Item> getAllItems()
                    throws DaoException;
	    
	    
	    Item getItem(String itemId)
                    throws DaoException;
	    
	    
	    Item removeItem(String itemId)
                throws DaoException;
            
            Item editItem(String title, String changedItem, Item editedDvd)
           throws DaoException;
    
}
