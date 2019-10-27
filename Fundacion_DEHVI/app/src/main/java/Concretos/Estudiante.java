package Concretos;

public class Estudiante {
    String dob;
    String joinDate;
    int earlyBirthAmount;
    int classRoomId;
    Classroom ClassRoom;
    Form form;
    int id;
    int dni;
    String firstName;
    String lastName;
    String gender;
    int locationId;
    String status;

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(String joinDate) {
        this.joinDate = joinDate;
    }

    public int getEarlyBirthAmount() {
        return earlyBirthAmount;
    }

    public void setEarlyBirthAmount(int earlyBirthAmount) {
        this.earlyBirthAmount = earlyBirthAmount;
    }

    public int getClassRoomId() {
        return classRoomId;
    }

    public void setClassRoomId(int classRoomId) {
        this.classRoomId = classRoomId;
    }

    public Classroom getClassRoom() {
        return ClassRoom;
    }

    public void setClassRoom(Classroom classRoom) {
        ClassRoom = classRoom;
    }

    public Form getForm() {
        return form;
    }

    public void setForm(Form form) {
        this.form = form;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
