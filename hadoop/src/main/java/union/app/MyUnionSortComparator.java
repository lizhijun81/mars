package union.app;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class MyUnionSortComparator extends WritableComparator {

    protected MyUnionSortComparator() {
        super(MyUnionCombineKey.class, true);
    }

    @Override
    public int compare(WritableComparable a, WritableComparable b) {
        MyUnionCombineKey a1 = (MyUnionCombineKey) a;
        MyUnionCombineKey b1 = (MyUnionCombineKey) b;
        int compare = a1.getStationId() - b1.getStationId();

        if (compare != 0) {
            return compare;
        }

        return a1.getFlag() - b1.getFlag();
    }
}
