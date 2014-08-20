package Model;

import java.io.Serializable;

public class Room implements Serializable {

    private String number;
    private String type;
    private double occupiedMinute = 0;

    public Room(String number, String type) {
        this.number = number;
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public double getOccupiedMinute() {
        return occupiedMinute;
    }

    public void setOccupiedMinute(double occupiedMinute) {
        this.occupiedMinute = occupiedMinute;
    }
}
