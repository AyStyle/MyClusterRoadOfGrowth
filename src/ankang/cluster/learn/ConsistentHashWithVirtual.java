package ankang.cluster.learn;

import java.util.SortedMap;
import java.util.TreeMap;

/**
 * @author: ankang
 * @email: dreedisgood@qq.com
 * @create: 2020-10-27
 */
public class ConsistentHashWithVirtual {

    public static void main(String[] args) {
        // 定义初始化虚拟节点数量
        final int virtualCount = 3;

        // 初始化：把服务器节点IP的hash值对应到hash环上
        final String[] services = {"10.123.111.0" , "192.168.62.1" , "172.135.42.2" , "139.46.74.3"};

        // 存储hash值与ip的对应关系
        final SortedMap<Integer, String> hashToIp = new TreeMap<>();
        for (String service : services) {
            final int serviceHash = Math.abs(service.hashCode());
            hashToIp.put(serviceHash , service);

            // 添加虚拟节点对应服务器
            for (int i = 0 ; i < virtualCount ; i++) {
                final String virtualService = service + "#@!" + i;
                final int virtualHash = Math.abs(virtualService.hashCode());
                hashToIp.put(virtualHash , String.format("virtual %d %s" , i , service));
            }
        }

        // 针对客户端求出hash值
        // 定义客户端ip
        final String[] clients = {"10.78.12.3" , "113.25.63.1" , "126.12.3.8"};
        for (String client : clients) {
            final int clientHash = Math.abs(client.hashCode());
            // 针对客户端找到能够处理客户端请求的服务器（hash环上顺时针最近）

            // 根据客户端ip的hash值去找出哪一个服务器节点能够处理
            final SortedMap<Integer, String> tailMap = hashToIp.tailMap(clientHash);

            // 处理请求服务器的key，如果tailMap为空，则：取头部的服务器处理
            final Integer firstKey = tailMap.isEmpty() ? hashToIp.firstKey() : tailMap.firstKey();

            System.out.println(String.format("Client %s connect to %s." , client , hashToIp.get(firstKey)));
        }

    }

}
