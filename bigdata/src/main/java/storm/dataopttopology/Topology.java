package storm.dataopttopology;

import com.storm.dataopttopology.bolt.FilterBolt;
import com.storm.dataopttopology.bolt.MetaBolt;
import com.storm.dataopttopology.bolt.MysqlBolt;
import com.storm.dataopttopology.spout.MetaSpout;
import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.StormSubmitter;
import org.apache.storm.generated.AlreadyAliveException;
import org.apache.storm.generated.AuthorizationException;
import org.apache.storm.generated.InvalidTopologyException;
import org.apache.storm.topology.TopologyBuilder;

/**
 * @Description
 * @Author xuedong.wang
 * @Date 17/5/19.
 */
public class Topology {

    // 实例化TopologyBuilder类。
    private static TopologyBuilder builder = new TopologyBuilder();

    public static void main(String[] args) throws InterruptedException,
            AlreadyAliveException, InvalidTopologyException {
        Config config = new Config();

        //数据源-->读取log文件/从消息队列metaq中消费
//		builder.setSpout("spout", new ReadLogSpout(), 1);
        builder.setSpout("spout", new MetaSpout("MetaSpout.xml"), 1);

        // 创建filter过滤节点
        builder.setBolt("filter", new FilterBolt("FilterBolt.xml"), 1)
                .shuffleGrouping("spout");

        // 创建mysql数据存储节点
        builder.setBolt("mysql", new MysqlBolt("MysqlBolt.xml"), 1)
                .shuffleGrouping("filter");

        //创建metaq回写节点
        builder.setBolt("meta", new MetaBolt("MetaBolt.xml"), 1)
                .shuffleGrouping("filter");

        //创建print消息打印节点
//		builder.setBolt("print", new PrintBolt(), 1).shuffleGrouping("filter");

        config.setDebug(false);

        if (args != null && args.length > 0) {
            config.setNumWorkers(1);
            try {
                StormSubmitter.submitTopology(args[0], config,
                        builder.createTopology());
            } catch (AuthorizationException e) {
                e.printStackTrace();
            }
        } else {
            // 这里是本地模式下运行的启动代码。
            config.setMaxTaskParallelism(1);
            LocalCluster cluster = new LocalCluster();
            cluster.submitTopology("dataopttopology", config, builder.createTopology());
        }

    }
}
