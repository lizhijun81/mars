package first.app;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class MyApp {
    public static void main(String[] args) throws Exception {
        Job job = new Job();
        job.setJarByClass(MyApp.class);
        job.setJobName("first job");

        FileInputFormat.addInputPath(job, new Path("hdfs://localhost:9000/user/lizhijun/data/"));
        FileOutputFormat.setOutputPath(job, new Path("hdfs://localhost:9000/user/lizhijun/data_output_1"));

        job.setMapperClass(MyMapper.class);
        job.setReducerClass(MyReducer.class);

        job.setOutputKeyClass(IntWritable.class);
        job.setOutputValueClass(IntWritable.class);

        System.exit(job.waitForCompletion(true) ? 1 : 0);
    }
}