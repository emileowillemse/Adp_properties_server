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
public class Rental {
    private int code;
    private int commission;
    private int rentPrice;

    public Rental(int code, int commission, int rentPrice) {
        this.code = code;
        this.commission = commission;
        this.rentPrice = rentPrice;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getCommission() {
        return commission;
    }

    public void setCommission(int commission) {
        this.commission = commission;
    }

    public int getRentPrice() {
        return rentPrice;
    }

    public void setRentPrice(int rentPrice) {
        this.rentPrice = rentPrice;
    }

    @Override
    public String toString() {
        return "Rental{" + "code=" + code + ", commission=" + commission + ", rentPrice=" + rentPrice + '}';
    }
    
}
