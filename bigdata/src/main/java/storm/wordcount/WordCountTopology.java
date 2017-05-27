package storm.wordcount;

import com.storm.dataopttopology.bolt.PrintBolt;
import com.storm.wordcount.bolt.WordCountBolt;
import com.storm.wordcount.bolt.WordNormalizerBolt;
import com.storm.wordcount.spout.RandomSentenceSpout;
import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.StormSubmitter;
import org.apache.storm.shade.org.joda.time.chrono.AssembledChronology;
import org.apache.storm.topology.TopologyBuilder;
import org.apache.storm.tuple.Fields;

/**
 * @Description
 * @Author xuedong.wang
 * @Date 17/5/22.
 */
public class WordCountTopology {


    private static TopologyBuilder builder = new TopologyBuilder();

    public static void main(String[] args) {

        Config config = new Config();

        builder.setSpout("RandomSentence", new RandomSentenceSpout(), 2);
        builder.setBolt("WordNormalizer", new WordNormalizerBolt(), 2).shuffleGrouping(
                "RandomSentence");
        builder.setBolt("WordCount", new WordCountBolt(), 2).fieldsGrouping("WordNormalizer",
                new Fields("word"));// new AssembledChronology.Fields("word")

        builder.setBolt("Print", new PrintBolt(), 1).shuffleGrouping(
                "WordCount");

        config.setDebug(false);

        //通过是否有参数来控制是否启动集群，或者本地模式执行
        if (args != null && args.length > 0) {
            try {
                config.setNumWorkers(1);
                StormSubmitter.submitTopology(args[0], config,
                        builder.createTopology());
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            config.setMaxTaskParallelism(1);
            LocalCluster cluster = new LocalCluster();
            cluster.submitTopology("wordcount", config, builder.createTopology());
        }
    }

}
