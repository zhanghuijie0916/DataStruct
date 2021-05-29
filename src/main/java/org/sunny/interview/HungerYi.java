package org.sunny.interview;

import java.util.*;

/**
 *
 设f(x)=4x+3,g(x)=8x+7。
 计算可以得到以下两个规律：
 （1）  g(f(x))=f(g(x))   即f和g的执行顺序没有影响。

 （2）  f(f(f(x)))=g(g(x))    即做3次f变换等价于做2次g变换

     由于规律（1） 对于一个可行方案，我们可以调整其变换的顺序。如ffggfggff，我们可以转化成 fffffgggg。
     由于规律（2），并且为了使执行次数最少，每3个f我们可以转化成2个g，如方案fffffgggg，可以转化成ffgggggg。
     因此一个最优的策略，f的执行次数只能为0,1,2。对于输入的x，我们只需要求x，4x+3,4*(4x+3)+3的最小g执行次数就可以了。

 */
public class HungerYi {

    public static void main(String[] args) {
        Deque<Long> deque = new LinkedList<>();
        Scanner scan = new Scanner(System.in);
        final long step = 1_000_000_007;
        long x0 = scan.nextLong();
        deque.add(x0);
        Map<Long,Integer> map = new HashMap<>();
        map.put(x0,0);

        Long path1 = 4*x0+3;
        Long path2 = 4*path1+3;
        deque.add(path1);
        deque.add(path2);
        map.put(x0,0);
        map.put(path1,1);
        map.put(path2,2);


        while (!deque.isEmpty()){
            long x = deque.remove();
            if (map.get(x)>100_000){
                System.out.println(-1);
                break;
            }
            long xx = 8*x+7;
            System.out.println(xx);

            if (xx%step == 0 ){
                System.out.println(map.get(x)+1);
                break;
            }
            else{
                xx = xx%step;
                map.put(xx,map.get(x)+1);
                //System.out.println(xx+"====="+map.get(x)+1);
                deque.add(xx);
            }

        }

    }


}
