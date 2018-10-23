package sort.app;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Partitioner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyPartitioner extends Partitioner<MyCombineKey, NullWritable> {

    private Logger logger = LoggerFactory.getLogger(MyPartitioner.class);

    @Override
    public int getPartition(MyCombineKey myCombineKey, NullWritable nullWritable, int partitionsNum) {
        logger.info("MyPartitioner:{}", myCombineKey);
        return myCombineKey.getYear() % partitionsNum;
    }

}
