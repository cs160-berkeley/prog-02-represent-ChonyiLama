package com.cs160.joleary.catnip;

/**
 * Created by chonyi on 3/11/16.
 */
public class Legislator {
    public String first_name, last_name;
    public String title, party;
    public String website, email;
    //public boolean in_office;

    public Legislator(String firstname,String lastname,String tit,String part,String web,String emaill) {
        first_name = firstname;
        last_name = lastname;
        title = tit;
        party = part;
        website = web;
        email = emaill;
    }
    public void setFirst_name(String name) {
        first_name = name;
    }
    public void setLast_name(String name) {
        last_name = name;
    }
    public void setTitle(String ttitle) {
        title = ttitle;
    }
    public void setParty(String pparty) {
        party = pparty;
    }
    public void setWebsite(String wwebsite) {
        website = wwebsite;
    }
    public void setEmail(String eemail) {
        email = eemail;
    }


    public String getFirstName() {
        return first_name;
    }
    public String getLastName() {
        return last_name;
    }
    public String getWebsite() {
        return website;
    }
    public String getParty() {
        return party;
    }
    public String getEmail() {
        return email;
    }
    public String getTitle() {
        String title = this.title;
        if (title.equals("Del"))
            return "Delegate";
        else if (title.equals("Com"))
            return "Resident Commissioner";
        else if (title.equals("Sen"))
            return "Senator";
        else // "Rep"
            return "Representative";
    }


    public static String partyName(String party) {
        if (party.equals("D"))
            return "Democrat";
        if (party.equals("R"))
            return "Republican";
        if (party.equals("I"))
            return "Independent";
        else
            return "";
    }
/**
    public String getPosition(String stateName) {
        String district = this.district;

        String position = "";

        if (district.equals("Senior Seat"))
            position = "Senior Senator from " + stateName;
        else if (district.equals("Junior Seat"))
            position = "Junior Senator from " + stateName;
        else if (district.equals("0")) {
            if (title.equals("Rep"))
                position = "Representative for " + stateName + " At-Large";
            else
                position = fullTitle() + " for " + stateName;
        } else
            position = "Representative for " + stateName + "-" + district;

        return "(" + party + ") " + position;
    }
  **/

}
