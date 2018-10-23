package union.app;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class MyUnionGroupComparator extends WritableComparator {

    protected MyUnionGroupComparator() {
        super(MyUnionCombineKey.class, true);
    }

    @Override
    public int compare(WritableComparable a, WritableComparable b) {
        MyUnionCombineKey a1 = (MyUnionCombineKey) a;
        MyUnionCombineKey b1 = (MyUnionCombineKey) b;
        return a1.getStationId() - b1.getStationId();
    }
}
