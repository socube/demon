package hadoop.wordcount;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @Description  Map 任务
 * @Author xuedong.wang
 * @Date 17/7/11.
 */
public class WordCountMapper extends Mapper<LongWritable,Text,Text,IntWritable>{

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        //等到输入的每一行数据
        String line = value.toString();
        //空格分割数据
        String[] words = line.split("\t");

        //遍历数据并输出
        // hello 1
        //  a    1
        for (String word : words){
            context.write(new Text(word),new IntWritable(1));
        }

    }
}
