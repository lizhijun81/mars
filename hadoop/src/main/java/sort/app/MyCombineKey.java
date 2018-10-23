package sort.app;

import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class MyCombineKey implements WritableComparable<MyCombineKey> {

    private int year;

    private int temp;

    @Override
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeInt(year);
        dataOutput.writeInt(temp);
    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {
        year = dataInput.readInt();
        temp = dataInput.readInt();
    }

    public MyCombineKey() {
    }

    public MyCombineKey(int year, int temp) {
        this.year = year;
        this.temp = temp;
    }

    @Override
    public int compareTo(MyCombineKey o) {
        if (this.year != o.year) {
            return o.year - this.year;
        } else {
            return o.temp - this.temp;
        }
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getTemp() {
        return temp;
    }

    public void setTemp(int temp) {
        this.temp = temp;
    }

    @Override
    public String toString() {
        return "MyCombineKey{" +
                "year=" + year +
                ", temp=" + temp +
                '}';
    }
}
