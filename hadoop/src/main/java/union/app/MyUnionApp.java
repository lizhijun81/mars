package union.app;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.MultipleInputs;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class MyUnionApp {

    public static void main(String[] args) throws Exception {
        Configuration configuration = new Configuration();

        Job job = Job.getInstance(configuration);
        job.setJarByClass(MyUnionApp.class);
        job.setJobName("union job");

        MultipleInputs.addInputPath(job, new Path("hdfs://localhost:9000/user/lizhijun/data_1/t3.txt"), TextInputFormat.class, MyUnionTempMapper.class);
        MultipleInputs.addInputPath(job, new Path("hdfs://localhost:9000/user/lizhijun/data_1/t4.txt"), TextInputFormat.class, MyUnionTempMapper.class);
        MultipleInputs.addInputPath(job, new Path("hdfs://localhost:9000/user/lizhijun/data_1/s1.txt"), TextInputFormat.class, MyUnionStationMapper.class);

        FileOutputFormat.setOutputPath(job, new Path("hdfs://localhost:9000/user/lizhijun/data_2/out_11"));

        job.setPartitionerClass(MyUnionPartitioner.class);
        job.setSortComparatorClass(MyUnionSortComparator.class);
        job.setGroupingComparatorClass(MyUnionGroupComparator.class);
        job.setReducerClass(MyUnionReducer.class);

        job.setMapOutputKeyClass(MyUnionCombineKey.class);
        job.setMapOutputValueClass(Text.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);

        job.setNumReduceTasks(2);

        System.exit(job.waitForCompletion(true) ? 1 : 0);
    }

}
