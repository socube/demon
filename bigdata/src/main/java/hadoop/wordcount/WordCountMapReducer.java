package hadoop.wordcount;


import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.*;

import java.io.IOException;
import java.util.Iterator;
import java.util.StringTokenizer;


/**
 * @Description
 * @Author xuedong.wang
 * @Date 17/7/11.
 */
public class WordCountMapReducer {


    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {

//        Configuration conf = new Configuration();
//
//        Job job = Job.getInstance(conf, "wordCount");
//
//        job.setJarByClass(WordCountMapReducer.class);
//        job.setMapperClass(WordCountMapper.class);
//        job.setReducerClass(WordCountReducer.class);
//
//        //mapper key value
//        job.setMapOutputKeyClass(Text.class);
//        job.setMapOutputValueClass(IntWritable.class);
//
//        //reducer key value
//
//        job.setOutputKeyClass(Text.class);
//        job.setMapOutputValueClass(IntWritable.class);
//
//        //设置输入和输出的路径
//        //FileInputFormat.setInputPaths(conf1, new Path(args[0]));
//        //提交任务
//
//        boolean isSuccess = job.waitForCompletion(true);
//        if (!isSuccess) { //任务失败
//            System.out.print("This task is fialed");
//        }

        JobConf conf = new JobConf(WordCountMapReducer.class);
        conf.setJobName("wordcount");

        conf.setOutputKeyClass(Text.class);
        conf.setOutputValueClass(IntWritable.class);

        conf.setMapperClass(Map.class);
        conf.setCombinerClass(Reduce.class);
        conf.setReducerClass(Reduce.class);

        conf.setInputFormat(TextInputFormat.class);
        conf.setOutputFormat(TextOutputFormat.class);

        FileInputFormat.setInputPaths(conf, new Path("/Users/wangxuedong/Downloads/words"));//hdfs://192.168.201.228:9000/words
        FileOutputFormat.setOutputPath(conf, new Path("/Users/wangxuedong/Downloads/wordcount_out"));//hdfs://192.168.201.228:9000/wordcount_out

        JobClient.runJob(conf);
    }


    public static class Map extends MapReduceBase implements Mapper<LongWritable, Text, Text, IntWritable> {
        private final static IntWritable one = new IntWritable(1);
        private Text word = new Text();

        public void map(LongWritable key, Text value, OutputCollector<Text, IntWritable> output, Reporter reporter) throws IOException {
            String line = value.toString();
            StringTokenizer tokenizer = new StringTokenizer(line);
            while (tokenizer.hasMoreTokens()) {
                word.set(tokenizer.nextToken());
                output.collect(word, one);
            }
        }
    }

    public static class Reduce extends MapReduceBase implements Reducer<Text, IntWritable, Text, IntWritable> {
        public void reduce(Text key, Iterator<IntWritable> values, OutputCollector<Text, IntWritable> output, Reporter reporter) throws IOException {
            int sum = 0;
            while (values.hasNext()) {
                sum += values.next().get();
            }
            output.collect(key, new IntWritable(sum));
        }
    }
}
