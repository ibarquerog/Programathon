package Concretos;

public class Form {
    int id;
    String name;
    String instructions;
    String status;
    int minAgeMonths;
    int minAgeDays;
    int maxAgeMonths;
    int maxAgeDays;

    public Form(int id, String name, String instructions, String status, int minAgeMonths, int minAgeDays, int maxAgeMonths, int maxAgeDays) {
        this.id = id;
        this.name = name;
        this.instructions = instructions;
        this.status = status;
        this.minAgeMonths = minAgeMonths;
        this.minAgeDays = minAgeDays;
        this.maxAgeMonths = maxAgeMonths;
        this.maxAgeDays = maxAgeDays;
    }


}
