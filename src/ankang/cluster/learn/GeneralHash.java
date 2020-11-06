package ankang.cluster.learn;

/**
 * 普通hash算法的实现
 *
 * @author: ankang
 * @email: dreedisgood@qq.com
 * @create: 2020-10-26
 */
public class GeneralHash {

    public static void main(String[] args) {
        // 定义客户端ip
        final String[] clients = {"10.78.12.3" , "113.25.63.1" , "126.12.3.8"};

        // 定义服务器数量
        final int serviceCount = 5;

        // 路由计算
        for (String client : clients) {
            final int index = Math.abs(client.hashCode()) % serviceCount;
            System.out.println(String.format("Client %s connect to %s" , client , index));
        }

    }

}
