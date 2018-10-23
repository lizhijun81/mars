package union.app;

import org.apache.hadoop.io.WritableComparable;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class MyUnionCombineKey implements WritableComparable<MyUnionCombineKey> {
    private int stationId;

    private int flag;

    public MyUnionCombineKey() {
    }

    public MyUnionCombineKey(int stationId, int flag) {
        this.stationId = stationId;
        this.flag = flag;
    }

    public int getStationId() {
        return stationId;
    }

    public void setStationId(int stationId) {
        this.stationId = stationId;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    @Override
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeInt(this.stationId);
        dataOutput.writeInt(this.flag);
    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {
        dataInput.readInt();
        dataInput.readInt();
    }

    @Override
    public int compareTo(MyUnionCombineKey o) {
        int compare = this.stationId - o.stationId;
        if (compare == 0) {
            return this.flag - o.getFlag();
        }
        return compare;
    }
}
