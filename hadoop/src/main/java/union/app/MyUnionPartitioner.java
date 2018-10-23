package union.app;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;


public class MyUnionPartitioner extends Partitioner<MyUnionCombineKey, Text> {

    @Override
    public int getPartition(MyUnionCombineKey myUnionCombineKey, Text text, int i) {
        return myUnionCombineKey.getStationId() % i ;
    }

}
