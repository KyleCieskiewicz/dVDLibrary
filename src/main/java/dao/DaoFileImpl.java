/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.Item;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author kylecieskiewicz
 */
public class DaoFileImpl implements Dao {

    public static final String INVENTORY_FILE = "inventory.txt";
    public static final String DELIMITER = "::";

    private Map<String, Item> items = new HashMap<>();

    @Override
    public Item addItem(String itemId, Item item)
            //        Item newItem = items.put(itemId, item);
            //        return newItem;
            throws DaoException {
        load();
        Item newItem = items.put(itemId, item);
        write();
        return newItem;
    }

    @Override
    public List<Item> getAllItems()
            throws DaoException {
        load();

        return new ArrayList<Item>(items.values());

    }

    @Override
    public Item getItem(String itemId)
            throws DaoException {
        load();
        return items.get(itemId);
    }

    @Override
    public Item editItem(String title, String changedItem, Item editedDvd)
            throws DaoException {
        Item itemEdited = items.put(title, editedDvd);
        if (!(title.equals(changedItem))) {
            itemEdited = items.remove(title);
            items.put(changedItem, itemEdited);
        }
        write();
        return itemEdited;
    }

    @Override
    public Item removeItem(String itemId)
            throws DaoException {
        Item removedItem = items.remove(itemId);
        write();
        return removedItem;
    }

    private void load() throws DaoException {
        Scanner scanner;

        try {
            // Create Scanner for reading the file
            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(INVENTORY_FILE)));
        } catch (FileNotFoundException e) {
            throw new DaoException(
                    "-_- Could not load data into memory.", e);
        }

        String currentLine;

        String[] currentTokens;

        while (scanner.hasNextLine()) {
            // get the next line in the file
            currentLine = scanner.nextLine();
            // break up the line into tokens
            currentTokens = currentLine.split(DELIMITER);

            Item currentItem = new Item(currentTokens[0]);

            currentItem.setReleaseDate(currentTokens[1]);
            currentItem.setMPAA_Rating(currentTokens[2]);
            currentItem.setDirectorName(currentTokens[3]);
            currentItem.setStudio(currentTokens[4]);
            currentItem.setUserNote(currentTokens[5]);

            items.put(currentItem.getTitle(), currentItem);
        }
        // close scanner
        scanner.close();
    }

    private void write() throws DaoException {

        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(INVENTORY_FILE));
        } catch (IOException e) {
            throw new DaoException(
                    "Could not save data.", e);
        }

        List<Item> itemList = this.getAllItems();
        for (Item currentItem : itemList) {

            out.println(currentItem.getTitle() + DELIMITER
                    + currentItem.getReleaseDate() + DELIMITER
                    + currentItem.getMPAA_Rating() + DELIMITER
                    + currentItem.getDirectorName() + DELIMITER
                    + currentItem.getStudio() + DELIMITER
                    + currentItem.getUserNote());
            // force PrintWriter to write line to the file
            out.flush();
        }
        // Clean up
        out.close();
    }

}
