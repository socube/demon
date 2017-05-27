package storm.wordcount.spout;

import org.apache.storm.spout.SpoutOutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichSpout;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Values;
import org.apache.storm.utils.Utils;


import java.util.Map;
import java.util.Random;
/**
 * @Description
 * @Author xuedong.wang
 * @Date 17/5/22.
 */
public class RandomSentenceSpout extends BaseRichSpout {

    SpoutOutputCollector spoutOutputCollector;
    Random random;

    // 进行spout的一些初始化工作，包括参数传递
    @SuppressWarnings("rawtypes")
    public void open(Map conf, TopologyContext context,
                     SpoutOutputCollector collector) {
        spoutOutputCollector = collector;
        random = new Random();
    }

    // 进行Tuple处理的主要方法
    public void nextTuple() {
        Utils.sleep(2000);
        String[] sentences = new String[]{
                "jikexueyuan is a good school",
                "And if the golden sun",
                "four score and seven years ago",
                "storm hadoop spark hbase",
                "blogchong is a good man",
                "Would make my whole world bright",
                "blogchong is a good website",
                "storm would have to be with you",
                "Pipe to subprocess seems to be broken No output read",
                " You make me feel so happy",
                "For the moon never beams without bringing me dreams Of the beautiful Annalbel Lee",
                "Who love jikexueyuan and blogchong",
                "blogchong.com is Magic sites",
                "Ko blogchong swayed my leaves and flowers in the sun",
                "You love blogchong.com", "Now I may wither into the truth",
                "That the wind came out of the cloud",
                "at backtype storm utils ShellProcess",
                "Of those who were older than we"};
        // 从sentences数组中，随机获取一条语句，作为这次spout发送的消息
        String sentence = sentences[random.nextInt(sentences.length)];
        // 使用emit方法进行Tuple发布，参数用Values申明
        spoutOutputCollector.emit(new Values(sentence.trim().toLowerCase()));
    }

    // 消息保证机制中的ack确认方法
    public void ack(Object id) {
    }

    // 消息保证机制中的fail确认方法
    public void fail(Object id) {
    }

    // 声明字段
    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declare(new Fields("word"));
    }
}
