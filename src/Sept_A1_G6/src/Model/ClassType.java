package Model;

import java.io.Serializable;

public class ClassType implements Serializable {

    private String name;
    private String feePerLesson;
    private String type;
    private String remarks;
    private String lessonPerWeek;

    public ClassType(String name, String fee, String type, String remarks, String lesson) {
        this.name = name;
        this.feePerLesson = fee;
        this.type = type;
        this.remarks = remarks;
        this.lessonPerWeek = lesson;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getFeePerLesson() {
        return feePerLesson;
    }

    public void setFeePerLesson(String feePerLesson) {
        this.feePerLesson = feePerLesson;
    }

    public String getLessonPerWeek() {
        return lessonPerWeek;
    }

    public void setLessonPerWeek(String lessonPerWeek) {
        this.lessonPerWeek = lessonPerWeek;
    }
}
