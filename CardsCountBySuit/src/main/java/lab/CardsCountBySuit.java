package lab;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.partition.HashPartitioner;
import org.apache.hadoop.mapreduce.lib.reduce.LongSumReducer;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class CardsCountBySuit extends Configured implements Tool {

	@Override
	public int run(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		Job job = Job.getInstance(getConf());
		job.setJarByClass(getClass());
		
		FileInputFormat.setInputPaths(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		
		job.setMapperClass(CardsMapper.class);
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(LongWritable.class);
		
		job.setPartitionerClass(HashPartitioner.class);
		job.setNumReduceTasks(2);
		
		job.setReducerClass(LongSumReducer.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(LongWritable.class);
		
		return job.waitForCompletion(true) ? 0 : 1;
		
	}

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		System.exit(ToolRunner.run(new CardsCountBySuit(), args));
	}
	
	private static class CardsMapper extends Mapper<LongWritable, Text, Text, LongWritable>{

		public void map(LongWritable offSet, Text suit, Context context) throws IOException, InterruptedException {
			context.write(new Text(suit.toString().split("\\|")[1]), new LongWritable(1));
		}
		
	}

}
