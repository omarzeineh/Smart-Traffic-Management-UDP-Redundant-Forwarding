package smarttraffic.q1;
public class Sensor {
    private int ID;
    private int vCount;
    private float total;
    private float avg;
    private boolean status;
    
    public Sensor(int ID) {
        this.ID = ID;
        this.vCount = 0;
        this.avg = 0;
        this.total = 0;
        this.status = true;
    }

    public int getID() {
        return ID;
    }

    public int getvCount() {
        return vCount;
    }

    public float getAvg() {
        return avg;
    }

    public boolean isStatus() {
        return status;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setvCount(int vCount) {
        this.vCount = vCount;
    }

    public void setAvg(float avg) {
        this.avg = avg;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    
    public float incCount() {
        this.vCount = this.vCount+5;
        this.total = this.total+(float)(5*(Math.random()*40+40));
        this.avg = this.total/(float)this.vCount;
        this.status = !this.status;
        return this.avg;
    }

    @Override
    public String toString() {
        if(status == true)
            return "Sensor{" + "ID=" + ID + ", vCount=" + vCount + "Cars, avg=" + avg + "Km/h, status=Green" + '}';
        else
            return "Sensor{" + "ID=" + ID + ", vCount=" + vCount + "Cars, avg=" + avg + "Km/h, status=Red" + '}';
    }
    
    
}
