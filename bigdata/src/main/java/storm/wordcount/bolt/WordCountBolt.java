package storm.wordcount.bolt;

import storm.wordcount.util.MapSort;
import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.IRichBolt;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description
 * @Author xuedong.wang
 * @Date 17/5/22.
 */
@SuppressWarnings("serial")
public class WordCountBolt implements IRichBolt {


    Map<String, Integer> counters;
    private OutputCollector outputCollector;

    @SuppressWarnings("rawtypes")
    public void prepare(Map stormConf, TopologyContext context,
                        OutputCollector collector) {
        outputCollector = collector;
        counters = new HashMap<String, Integer>();
    }

    public void execute(Tuple input) {
        String str = input.getString(0);

        if (!counters.containsKey(str)) {
            counters.put(str, 1);
        } else {
            Integer c = counters.get(str) + 1;
            counters.put(str, c);
        }

        //我们只取词频最大的前十个
        int num = 8;
        int length = 0;

        //使用工具类MapSort对map进行排序
        counters = MapSort.sortByValue(counters);

        if (num < counters.keySet().size()) {
            length = num;
        } else {
            length = counters.keySet().size();
        }

        String word = null;

        //增量统计
        int count = 0;
        for (String key : counters.keySet()) {

            //获取前N个，推出循环
            if (count >= length) {
                break;
            }

            if (count == 0) {
                word = "[" + key + ":" + counters.get(key) + "]";
            } else {
                word = word + ", [" + key + ":" + counters.get(key) + "]";
            }
            count++;
        }

        word = "The first " + num + ": " + word;
        outputCollector.emit(new Values(word));

    }

    public void cleanup() {

    }

    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declare(new Fields("word"));
    }

    public Map<String, Object> getComponentConfiguration() {
        return null;
    }

}
