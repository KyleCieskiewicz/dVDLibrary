/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.Dao;
import dao.DaoException;
import dao.DaoFileImpl;
import dto.Item;
import java.util.List;
import ui.UserIO;
import ui.UserIOConsoleImpl;
import ui.View;

/**
 *
 * @author kylecieskiewicz
 */
public class Controller {

    private View view;
    private Dao dao;
//    private UserIO io = (UserIO) new UserIOConsoleImpl();

    public Controller(Dao dao, View view) {
        this.dao = dao;
        this.view = view;
    }

    public void run() throws Exception {
        boolean keepGoing = true;
        int menuSelection = 0;
        try {
        while (keepGoing) {

            menuSelection = getMenuSelection();

//	            io.print("Main Menu");
//	            io.print("1. List");
//	            io.print("2. Create New");
//	            io.print("3. View");
//	            io.print("4. Remove");
//	            io.print("5. Exit");
//	            
//	            menuSelection = io.readInt("Please select from the"
//	                    + " above choices.", 1, 5);
            switch (menuSelection) {
                case 1:
                    listItems();
                    break;
                case 2:
                    createItem();
                    break;
                case 3:
                    viewItem();
                    break;
                case 4:
                    listSearchResults();
                    break;
                case 5:
                    editItem();
                    break;
                case 6:
                    removeItem();
                    break;
                case 7:
                    keepGoing = false;
                    break;
                default:
                    unknownCommand();
            }

        }
        exitMessage();
    } catch (DaoException e) {
	        view.displayErrorMessage(e.getMessage());
	    }
    }

    private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }

    private void createItem() throws DaoException {
        view.displayCreateItemBanner();
        Item newItem = view.getNewItemInfo();
        dao.addItem(newItem.getTitle(), newItem);
        view.displayCreateSuccessBanner();
    }
    
    private void listSearchResults() throws DaoException {
       List<Item> itemList = dao.getAllItems();
       view.displaySearch(itemList);
   }

    private void listItems() throws DaoException {
        view.displayDisplayAllBanner();
        List<Item> itemList = dao.getAllItems();
        view.displayItemList(itemList);
    }

    private void viewItem() throws DaoException {
        view.displayDisplayItemBanner();
        String itemName = view.getItemNameChoice();
        Item item = dao.getItem(itemName);
        view.displayItem(item);
    }
    
    private void editItem() throws Exception {
       String title = view.getItemNameChoice();
       Item item = dao.getItem(title);
       List<Item> itemList = dao.getAllItems();
       Item editedItem = view.editItem(itemList, item);
       
       if (editedItem != null) {
           String itemEdited = editedItem.getTitle();
           dao.editItem(title, itemEdited, editedItem);
       }
       
    }

    private void removeItem() throws DaoException {
        view.displayRemoveItemBanner();
        String itemId = view.getItemNameChoice();
        dao.removeItem(itemId);
        view.displayRemoveSuccessBanner();
    }

    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }

    private void exitMessage() {
        view.displayExitBanner();
    }

}
