package zk.zkLock;

/**
 * @Description
 * @Author xuedong.wang
 * @Date 17/1/8.
 */
public class LockNode {

    private String name;

    public LockNode(String name){
        this.name = name;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
