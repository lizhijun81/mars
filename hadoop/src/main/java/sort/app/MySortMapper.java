package sort.app;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class MySortMapper extends Mapper<LongWritable, Text, MyCombineKey, NullWritable> {

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String val = value.toString();
        String[] strs = val.split("\t");

        int year = Integer.valueOf(strs[0]);
        int temp = Integer.valueOf(strs[1]);

        context.write(new MyCombineKey(year, temp), NullWritable.get());
    }

}
