package sort.app;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

/**
 * 全排序
 */
public class MySortApp {
    public static void main(String[] args) throws Exception {

        Configuration configuration = new Configuration();

        Job job = Job.getInstance(configuration);
        job.setJarByClass(MySortApp.class);
        job.setJobName("sort job");

        job.setMapperClass(MySortMapper.class);
        job.setReducerClass(MySortReducer.class);

        job.setPartitionerClass(MyPartitioner.class);

        job.setMapOutputKeyClass(MyCombineKey.class);
        job.setMapOutputValueClass(NullWritable.class);

        job.setOutputKeyClass(IntWritable.class);
        job.setOutputValueClass(IntWritable.class);

        FileInputFormat.addInputPath(job, new Path("hdfs://localhost:9000/user/lizhijun/data/"));
        FileOutputFormat.setOutputPath(job, new Path("hdfs://localhost:9000/user/lizhijun/data/output_5/"));

        job.setNumReduceTasks(2);

        System.exit(job.waitForCompletion(true) ? 1 : 0);
    }
}