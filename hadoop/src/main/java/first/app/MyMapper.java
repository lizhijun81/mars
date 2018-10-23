package first.app;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import java.io.IOException;

public class MyMapper extends Mapper<LongWritable, Text, IntWritable, IntWritable> {
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

        String val = value.toString();
        String[] strs = val.split("\t");

        int year = Integer.valueOf(strs[0]);

        int temp = Integer.valueOf(strs[1]);

        context.write(new IntWritable(year), new IntWritable(temp));
    }
}
