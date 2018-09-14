package union.app;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;


/**
 * 气象站mapper
 */
public class MyUnionStationMapper extends Mapper<LongWritable, Text, MyUnionCombineKey, Text> {

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String val = value.toString();
        String[] strs = val.split("\t");

        int stationId = Integer.valueOf(strs[0]);

        context.write(new MyUnionCombineKey(stationId, 0), value);
    }
}
