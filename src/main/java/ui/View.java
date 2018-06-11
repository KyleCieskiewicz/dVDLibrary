/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import dto.Item;
import java.util.List;

/**
 *
 * @author kylecieskiewicz
 */
public class View {

//    UserIO io = new UserIOConsoleImpl();
    private UserIO io;

    public View(UserIO io) {
        this.io = io;
    }

    public Item getNewItemInfo() {
        String title = io.readString("Please enter DVD Title:");
        String releaseDate = io.readString("Please enter its Release Date:");
        String MPAA_Rating = io.readString("Please enter its MPAA Rating:");
        String directorName = io.readString("Please enter the Director Name:");
        String studio = io.readString("Please enter the Movie Studio:");
        String userNote = io.readString("Please enter any Additional Information Personal Notes about the DVD:");
        Item currentItem = new Item(title);
        currentItem.setTitle(title);
        currentItem.setReleaseDate(releaseDate);
        currentItem.setMPAA_Rating(MPAA_Rating);
        currentItem.setDirectorName(directorName);
        currentItem.setStudio(studio);
        currentItem.setUserNote(userNote);
        return currentItem;
    }

    public int printMenuAndGetSelection() {
        io.print("Main Menu");
        io.print("1. List");
        io.print("2. Create");
        io.print("3. View");
        io.print("4. Search");
        io.print("5. Edit");
        io.print("6. Remove");
        io.print("7. Exit");

        return io.readInt("Please select from the above choices.", 1, 7);
    }

    public void displayCreateItemBanner() {
        io.print("=== Create Item ===");
    }

    public void displayCreateSuccessBanner() {
        io.readString(
                "Item successfully created.  Please hit enter to continue");
    }

    public void displaySearch(List<Item> itemList) {
        io.print("");
        String searchFor = io.readString("Enter the Title or a word to search for a DVD:");
        io.print("");
        io.print("=== Display Search Results ===");

        String searchingFor;
        boolean doesNotContain = true;
        for (Item currentItem : itemList) {
            searchingFor = currentItem.getTitle();
            if (searchingFor.toLowerCase().contains(searchFor.toLowerCase())) {
                io.print(searchingFor);
                doesNotContain = false;
            }
        }
        if (doesNotContain) {
            io.print("Not Found");
        }
        io.print("");
        io.readString("Please hit enter to continue.");
    }

    public void displayItemList(List<Item> itemList) {
        for (Item currentItem : itemList) {
            io.print("");
            io.print(currentItem.getTitle() + ": "
                    + currentItem.getReleaseDate() + " "
                    + currentItem.getMPAA_Rating() + " "
                    + currentItem.getDirectorName() + " "
                    + currentItem.getStudio() + " "
                    + currentItem.getUserNote() + " ");
        }
        io.print("");
        io.readString("Please hit enter to continue.");
    }

    public void displayDisplayAllBanner() {
        io.print("=== Display All Items ===");
    }

    public void displayDisplayItemBanner() {
        io.print("=== Display Item ===");
    }

    public String getItemNameChoice() {
        return io.readString("Please enter the DVD Name.");
    }

    public void displayItem(Item item) {
        if (item != null) {
            io.print(item.getTitle());
            io.print(item.getReleaseDate());
            io.print(item.getMPAA_Rating());
            io.print(item.getDirectorName());
            io.print(item.getStudio());
            io.print(item.getUserNote());
            io.print("");
        } else {
            io.print("No such item.");
        }
        io.readString("Please hit enter to continue.");
    }

    public Item editItem(List<Item> itemList, Item item) {
        
        if (item != null) {
            io.print("");
            io.print("1. Title: " + item.getTitle());
            io.print("2. Release Date: " + item.getReleaseDate());
            io.print("3. MPAA Rating: " + item.getMPAA_Rating());
            io.print("4. Director: " + item.getDirectorName());
            io.print("5. Studio: " + item.getStudio());
            io.print("6. Additional Information: " + item.getUserNote());
            io.print("");
            
        }
        
        boolean invalid = false;
        String editAgain = "N";
        int dvdField = 0;

        if (item != null) {
            do {
                io.print("Select the field for " + item.getTitle() + " that you would like to edit.");
                dvdField = io.readInt("1=Title, 2=Release Date, 3=Rating, 4=Director, 5=Studio, 6=Additional Information", 1, 6);

                io.print("");

                switch (dvdField) {
                    case 1:
                        String newTitle = io.readString("Please enter the new Title:");
                        item.setTitle(newTitle);
                        break;
                    case 2:
                        String newReleaseDate = io.readString("Please enter the new Release Date:");
                        item.setReleaseDate(newReleaseDate);
                        break;
                    case 3:
                        String newRating = io.readString("Please enter the new MPAA Rating:");
                        item.setMPAA_Rating(newRating);
                        break;
                    case 4:
                        String newDirector = io.readString("Please enter the new Director:");
                        item.setDirectorName(newDirector);
                        break;
                    case 5:
                        String newStudio = io.readString("Please enter the new Movie Studio:");
                        item.setStudio(newStudio);
                        break;
                    case 6:
                        String newInfo = io.readString("Please enter the new Additional Information:");
                        if (newInfo.equals("")) {
                            newInfo = "Nothing entered";
                        }
                        item.setUserNote(newInfo);
                        break;
                    default:
                        io.print("You have entered an invalid DVD field.");
                        invalid = true;
                }

                io.print("");
                editAgain = io.readString("Would you like to edit another field for this DVD listing? (Y/N)");
                if(editAgain.equalsIgnoreCase("Y")){
                    invalid = true;
                } else {
                    invalid = false;
                }
            } while (invalid);
        }

        return item;
    }

    public void displayRemoveItemBanner() {
        io.print("=== Remove Item ===");
    }

    public void displayRemoveSuccessBanner() {
        io.readString("Item successfully removed. Please hit enter to continue.");
    }

    public void displayExitBanner() {
        io.print("Good Bye!!!");
    }

    public void displayUnknownCommandBanner() {
        io.print("Unknown Command!!!");
    }

    public void displayErrorMessage(String errorMsg) {
        io.print("=== ERROR ===");
        io.print(errorMsg);
    }

}
