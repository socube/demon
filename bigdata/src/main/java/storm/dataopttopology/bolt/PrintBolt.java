package storm.dataopttopology.bolt;

import org.apache.storm.topology.BasicOutputCollector;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseBasicBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;

/**
 * @Description
 * @Author xuedong.wang
 * @Date 17/5/19.
 */
public class PrintBolt  extends BaseBasicBolt {

    public static void main(String[] args) {

    }

    @Override
    public void execute(Tuple input, BasicOutputCollector collector) {
        try {
            String mesg = input.getString(0);
            if (mesg != null)
                // 打印数据
                System.out.println("Tuple: " + mesg);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declare(new Fields("mesg"));

    }
}
