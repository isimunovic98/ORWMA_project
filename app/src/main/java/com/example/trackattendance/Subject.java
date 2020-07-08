package com.example.trackattendance;

public class Subject {
    private String name;
    private int attended;
    private int total;

    public Subject(String name, int attended, int total){
        this.name = name;
        this.attended = attended;
        this.total = total;
    }

    public Subject(String name){
        this(name, 0 , 0);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAttended() {
        return attended;
    }

    public void setAttended(int attended) {
        this.attended = attended;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public void incrementAttended(){
        this.attended++;
    }
    public void incrementTotal(){this.total++;}
}
