package sort.app;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class MySortGroupComparator extends WritableComparator {

    protected MySortGroupComparator() {
        super(MyCombineKey.class, true);
    }

    @Override
    public int compare(WritableComparable a, WritableComparable b) {
        MyCombineKey a1 = (MyCombineKey) a;
        MyCombineKey b1 = (MyCombineKey) b;
        return a1.getYear() - b1.getYear();
    }
}
