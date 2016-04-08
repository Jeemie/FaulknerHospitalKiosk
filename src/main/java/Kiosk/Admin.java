package Kiosk;

import javafx.beans.property.*;


public class Admin {

    private StringProperty deptType = new SimpleStringProperty(this, "deptType", "");
    public String getDeptType() {
        return deptType.get();
    }
    public StringProperty deptTypeProperty() {
        return deptType;
    }
    public void setDeptType(String deptType) {
        this.deptType.set(deptType);
    }

    private StringProperty deptName = new SimpleStringProperty(this, "deptName", "");
    public String getDeptName() {return deptName.get();}
    public StringProperty deptNameProperty() {return deptName;}
    public void setDeptName(String deptName) {
        this.deptName.set(deptName);
    }

    private IntegerProperty floor = new SimpleIntegerProperty(this, "floor", 0);
    public Integer getFloor() {
        return floor.get();
    }
    public IntegerProperty floorProperty() {
        return floor;
    }
    public void setFloor(int floor) {
        this.floor.set(floor);
    }

    private DoubleProperty gradepoint = new SimpleDoubleProperty(this, "gradePoint", 0.0);
    public double getGradepoint() {
        return gradepoint.get();
    }
    public DoubleProperty gradepointProperty() {
        return gradepoint;
    }
    public void setGradepoint(double gradepoint) {
        this.gradepoint.set(gradepoint);
    }


    private DoubleProperty yCoordinate = new SimpleDoubleProperty(this, "yCoordinate", 0.0);
    public double getyCoordinate() {
        return yCoordinate.get();
    }
    public DoubleProperty yCoordinateProperty() {
        return yCoordinate;
    }
    public void setyCoordinate(double yCoordinate) {
        this.yCoordinate.set(yCoordinate);
    }

    private StringProperty dept = new SimpleStringProperty(this, "dept", "");
    public String getDept() {
        return dept.get();
    }
    public StringProperty deptProperty() {
        return dept;
    }
    public void setDept(String dept) {
        this.dept.set(dept);
    }


    //for console printing purposes
    public String toString() {

        return "Dept Type: " + getDeptType() + " | Dept name: " + getDeptName() +" | Floor: " + getFloor() + " | x: " + getyCoordinate() + " | y: " + getGradepoint() + " | Dept: " + getDept();
    }

    public String printDept(){
        return getDeptName();
    }

}
