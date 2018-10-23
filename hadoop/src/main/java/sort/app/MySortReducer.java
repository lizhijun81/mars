package sort.app;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class MySortReducer extends Reducer<MyCombineKey, NullWritable, IntWritable, IntWritable> {

    @Override
    protected void reduce(MyCombineKey key, Iterable<NullWritable> values, Context context) throws IOException, InterruptedException {

        System.out.println(key.getYear() + "=====" + key.getTemp());

        context.write(new IntWritable(key.getYear()), new IntWritable(key.getTemp()));
    }
}
