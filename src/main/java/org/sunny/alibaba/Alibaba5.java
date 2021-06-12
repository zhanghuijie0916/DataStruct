package org.sunny.alibaba;



import java.util.Scanner;

public class Alibaba5 {

    public static int look(int[][]map,int xnow,int ynow,String so,int xend,int yend,String eo){
        int s1 = 0;
        int s2 = 0;
        int xdiff;
        int ydiff;
        String nextX;
        String nextY;

        if (map[xnow][ynow]==1){
            return 65536;
        }

        if (xnow==xend && ynow==yend && map[xnow][ynow]==0){
            return 1;
        }

        //设定下一步的方向
        else{
            if (xnow>=xend || ynow>=yend) {
                xdiff = -1;
                ydiff = -1;
            }
            else if (xend>=xnow || yend<=xend){
                xdiff = 1;
                ydiff = -1;
            }
            else if (xend<=xnow || yend>=xend){
                xdiff = -1;
                ydiff = 1;
            }
            // else if (xend>=xnow || yend>=xend)
            else{
                xdiff = 1;
                ydiff = 1;
            }

            if (xdiff<1){
                nextX = "111WEST";
            }
            else{
                nextX = "111EAST";
            }
            if (ydiff<1){
                nextY = "SOUTH";
            }
            else{
                nextY = "NORTH";
            }


            if (so.equals(nextX)){
                s1 += look(map,xnow+xdiff,ynow,nextX,xend,yend,eo);
            }
            else if (so.equals("NORTH") || so.equals("SOURTH")){
                s1 = s1+1+look(map,xnow+xdiff,ynow,nextX,xend,yend,eo);
            }
            //EAST
            else {
                s1 = s1+2+look(map,xnow+xdiff,ynow,nextX,xend,yend,eo);
            }


            if (so.equals(nextY)){
                s2 += look(map,xnow,ynow+ydiff,nextY,xend,yend,eo);
            }
            else if (so.equals("WEST") || so.equals("EAST")){
                s2 = s2+1+look(map,xnow,ynow+ydiff,nextY,xend,yend,eo);
            }
            //SOURTH
            else {
                s2 = s2+2+look(map,xnow,ynow+ydiff,nextY,xend,yend,eo);
            }

            return (s1>s2)?s2:s1;

        }
    }

    public static void main(String[] args){
        //起始
        Scanner scanner = new Scanner(System.in);
        int xStart = scanner.nextInt();
        int ystart = scanner.nextInt();
        String startOrien = scanner.next();  //方向

        //终止
        int xend = scanner.nextInt();
        int yend = scanner.nextInt();
        String endOrien = scanner.next();  //方向

        //地图
        int rowlen = scanner.nextInt();
        int collen = scanner.nextInt();
        int[][] map = new int[rowlen][collen];
        for (int i=0;i<rowlen;i++){
            for (int j=0;j<collen;j++){
                map[i][j] = scanner.nextInt();
            }
        }


        int time = look(map,xStart,ystart,startOrien,xend,yend,endOrien);
        System.out.println(time);
    }
}
