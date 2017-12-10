package android.chigoin.com.hotonepiece;

/**
 * Created by chigoin on 2017/12/4.
 */


/**
 * Created by 李进熙 on 2017/11/20.
 */

public class Player {
    /** 玩家名 */
    private String name;
    /** 玩家的ID */
    private int pid;


    /**
     * 玩家构造函数
     * @param name 玩家姓名
     * @param pid 玩家ID
     */
    public Player(String name, int pid){
        this.name = name;
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }
}

