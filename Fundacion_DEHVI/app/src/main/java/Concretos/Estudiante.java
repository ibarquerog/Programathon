package Concretos;

public class Estudiante {
    private String dob;
    private String joinDate;
    private int earlyBirthAmount;
    private int classRoomId;
    private Classroom ClassRoom;
    private Form form;
    private int id;
    private int dni;
    private String firstName;
    private String lastName;
    private  String gender;
    private int locationId;
    private String status;

    public Estudiante(String name){
        this.firstName=name;
    }

    public String getFormattedDate() {
        String result = "";
        for(int i=0;i<dob.length();i++){
            if(Character.toString(dob.charAt(i)).matches("[A-Z?]")||Character.toString(dob.charAt(i)).matches("[a-z?]")){
                break;
            }
            if(Character.toString(dob.charAt(i)).matches("-")){
                result+="/";
            }
            else {
                result+=Character.toString(dob.charAt(i));
            }
        }

        return result;
    }
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
