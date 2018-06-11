/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

/**
 *
 * @author kylecieskiewicz
 */
public class Item {

    private String title;
    private String releaseDate;
    private String MPAA_Rating;
    private String directorName;
    private String studio;
    private String userNote;

    public Item(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getMPAA_Rating() {
        return MPAA_Rating;
    }

    public void setMPAA_Rating(String MPAA_Rating) {
        this.MPAA_Rating = MPAA_Rating;
    }

    public String getDirectorName() {
        return directorName;
    }

    public void setDirectorName(String directorName) {
        this.directorName = directorName;
    }

    public String getStudio() {
        return studio;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }

    public String getUserNote() {
        if (userNote.equals("")) {
            userNote = "Nothing entered";
        }
        return userNote;
    }

    public void setUserNote(String userNote) {
        if (userNote.equals("")) {
            userNote = "Nothing entered";
        }
        this.userNote = userNote;
    }

}
