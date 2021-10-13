/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.ac.cput.Adp_properties_server.classes;

/**
 *
 * @author emile
 */
public class Houses {
    private int ID;  
    private int location;   
    private int rooms;
    private int rent;
    private boolean canRent;

    public Houses(int ID, int location, int rooms, int rent, boolean canRent) {
        this.ID = ID;
        this.location = location;
        this.rooms = rooms;
        this.rent = rent;
        this.canRent = canRent;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getLocation() {
        return location;
    }

    public void setLocation(int location) {
        this.location = location;
    }

    public int getRooms() {
        return rooms;
    }

    public void setRooms(int rooms) {
        this.rooms = rooms;
    }

    public int getRent() {
        return rent;
    }

    public void setRent(int rent) {
        this.rent = rent;
    }

    public boolean isCanRent() {
        return canRent;
    }

    public void setCanRent(boolean canRent) {
        this.canRent = canRent;
    }

    @Override
    public String toString() {
        return "Houses{" + "ID=" + ID + ", location=" + location + ", rooms=" + rooms + ", rent=" + rent + ", canRent=" + canRent + '}';
    }
    
}
