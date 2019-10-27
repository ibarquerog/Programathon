package Concretos;

class Classroom {
    int id;
    int classYear;
    String section;
    String status;
    int gradeId;
    int teacherId;

    public Classroom(int id, int classYear, String section, String status, int gradeId, int teacherId) {
        this.id = id;
        this.classYear = classYear;
        this.section = section;
        this.status = status;
        this.gradeId = gradeId;
        this.teacherId = teacherId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClassYear() {
        return classYear;
    }

    public void setClassYear(int classYear) {
        this.classYear = classYear;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getGradeId() {
        return gradeId;
    }

    public void setGradeId(int gradeId) {
        this.gradeId = gradeId;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }
}
