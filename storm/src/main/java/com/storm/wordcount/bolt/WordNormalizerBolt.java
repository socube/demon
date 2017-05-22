package com.storm.wordcount.bolt;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.IRichBolt;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;

import java.util.Map;

/**
 * @Description 消息标准化处理
 * @Author xuedong.wang
 * @Date 17/5/22.
 */

//将消息标准化，
@SuppressWarnings("serial")
public class WordNormalizerBolt implements IRichBolt {


    private OutputCollector outputCollector;

    //bolt初始化方法
    @SuppressWarnings("rawtypes")
    public void prepare(Map stormConf, TopologyContext context,
                        OutputCollector collector) {
        outputCollector = collector;
    }

    //执行订阅的Tuple逻辑过程的方法
    public void execute(Tuple input) {

        String sentence = input.getString(0);
        String[] words = sentence.split(" ");

        for (String word : words) {
            outputCollector.emit(new Values(word));
        }

    }

    public void cleanup() {
    }

    //字段声明
    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declare(new Fields("word"));
    }

    public Map<String, Object> getComponentConfiguration() {
        return null;
    }

}
