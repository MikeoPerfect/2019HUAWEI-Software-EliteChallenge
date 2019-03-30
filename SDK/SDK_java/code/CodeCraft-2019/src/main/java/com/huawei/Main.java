package com.huawei;

import org.apache.log4j.Logger;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    private static final Logger logger = Logger.getLogger(Main.class);

    public static void main(String[] args) throws IOException {
        /*if (args.length != 4) {
            logger.error("please input args: inputFilePath, resultFilePath");
            return;
        }*/

        logger.info("Start...");

        /*String carPath = args[0];
        String roadPath = args[1];
        String crossPath = args[2];
        String answerPath = args[3];*/

        String carPath = "C:\\Users\\HFUBoy\\Desktop\\HuaWeiSoftware\\SDK\\SDK_java\\code\\CodeCraft-2019\\car.txt";
        String  crossPath= "C:\\Users\\HFUBoy\\Desktop\\HuaWeiSoftware\\SDK\\SDK_java\\code\\CodeCraft-2019\\cross.txt";
        String roadPath = "C:\\Users\\HFUBoy\\Desktop\\HuaWeiSoftware\\SDK\\SDK_java\\code\\CodeCraft-2019\\road.txt";
        String answerPath = "C:\\Users\\HFUBoy\\Desktop\\HuaWeiSoftware\\SDK\\SDK_java\\code\\CodeCraft-2019\\answer.txt";

        logger.info("carPath = " + carPath + " roadPath = " + roadPath + " crossPath = " + crossPath + " and answerPath = " + answerPath);


        Car car = null;
        Cross cross=null;
        Road road=null;
        Answer answer=null;

        car= readCarTxt(carPath);           //读取CarTxt
        cross=readCrossTxt(crossPath);      //读取CrossTxt
        road=readRoadTxt(roadPath);         //读取RoadTxt



        int modelSize= 1995;


        int [][]FromToRoadId=new int[modelSize][modelSize];//路径Id,  x=crossIdfrom  y=crossIdTo, roadId
        int[][]modelMatrix=new int[modelSize][modelSize]; //图 x=crossIdFrom y=crossIdTo, roadPassTime

        boolean flag[]=new boolean[modelSize];
        int []distance=new int[modelSize];
        int PATH[]=new int[modelSize];





        answer=calcAnswer(car,road,PATH,FromToRoadId,modelMatrix,distance,flag);  //计算Answer
        writeAnswerTxt(answerPath,answer);  //写入AnswerTxt

        logger.info("End.....");
    }


    /**
     * 获取CarTxt的信息
     * @param carPath
     * @return
     * @throws IOException
     */
    static Car readCarTxt(String carPath) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new FileReader(carPath));
        Car car = new Car();
        bufferedReader.readLine();   //读取没用的第一行

        int lineNum[] = new int[5];    //一行中存储数值的中间变量数组
        String lineStr = null;    //文件中的每一行
        int lineCount=0,i=0;     //行数&中间变量数组的下标

        //读取文本并提取赋值
        while ((lineStr = bufferedReader.readLine()) != null) {
            //  car.carId[i]= Integer.parseInt(String.format(line, "(%d,)"));
            String regEx="[0-9]+";
            Pattern pattern=Pattern.compile(regEx);
            Matcher m=pattern.matcher(lineStr);
            i=0;
            while (m.find()){
                lineNum[i++]=Integer.parseInt(m.group());
                if(i==5)
                    break;
            }
            //将一行中的数字赋值给car对象对应的属性
            {
                car.carPlanTime[lineCount]=lineNum[--i];
                car.carSpeed[lineCount]=lineNum[--i];
                car.carCrossIdTo[lineCount]=lineNum[--i];
                car.carCrossIdFrom[lineCount]=lineNum[--i];
                car.carId[lineCount]=lineNum[--i];
            }
            lineCount++; //行数加一
        }
        bufferedReader.close();
        car.carTxtLineCount=lineCount;
        logger.info("read CarTxt over...");

        return car;
    }

    /**
     * 获取CrossTxt的信息
     * @param crossPath
     * @return
     * @throws IOException
     */
    static Cross readCrossTxt(String crossPath) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(crossPath));
        Cross cross=new Cross();
        bufferedReader.readLine();   //读取没用的第一行

        int lineNum[] = new int[5];    //一行中存储数值的中间变量数组
        String lineStr = null;    //文件中的每一行
        int lineCount=0,i=0,NUM=5;     //行数&中间变量数组的下标

        //读取文本并提取赋值
        while ((lineStr = bufferedReader.readLine()) != null) {
            String regEx="[0-9]+";
            Pattern pattern=Pattern.compile(regEx);
            Matcher m=pattern.matcher(lineStr);
            i=0;
            while (m.find()){
                lineNum[i++]=Integer.parseInt(m.group());
                if(i==NUM)
                    break;
            }
            //将一行中的数字赋值给cross对象对应的属性
            {
                cross.crossRoadIdFour[lineCount]=lineNum[--i];
                cross.crossRoadIdThree[lineCount]=lineNum[--i];
                cross.crossRoadIdTwo[lineCount]=lineNum[--i];
                cross.crossRoadIdOne[lineCount]=lineNum[--i];
                cross.crossId[lineCount]=lineNum[--i];
            }
            lineCount++; //行数加一
        }
        bufferedReader.close();
        cross.crossTxtLineCount=lineCount;
        logger.info("read CrossTxt over...");
        return cross;
    }

    /**
     * 获取RoadTxt的信息
     * @param roadPath
     * @return
     * @throws IOException
     */
    static Road readRoadTxt(String roadPath) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(roadPath));
        Road road=new Road();
        bufferedReader.readLine();   //读取没用的第一行
        int NUM=7;
        int lineNum[] = new int[NUM];    //一行中存储数值的中间变量数组
        String lineStr = null;    //文件中的每一行
        int lineCount=0,i=0;     //行数&中间变量数组的下标

        //读取文本并提取赋值
        while ((lineStr = bufferedReader.readLine()) != null) {
            String reg="[0-9]+";
            Pattern pattern=Pattern.compile(reg);
            Matcher m=pattern.matcher(lineStr);
            i=0;
            while (m.find()){
                lineNum[i++]=Integer.parseInt(m.group());
                if(i==7)
                    break;
            }
            //将一行中的数字赋值给road对象对应的属性
            {
                road.roadIsDuplex[lineCount]=lineNum[--i];
                road.roadCrossIdTo[lineCount]=lineNum[--i];
                road.roadCrossIdFrom[lineCount]=lineNum[--i];
                road.roadChannel[lineCount]=lineNum[--i];
                road.roadMaxSpeed[lineCount]=lineNum[--i];
                road.roadLength[lineCount]=lineNum[--i];
                road.roadId[lineCount]=lineNum[--i];
            }
            lineCount++; //行数加一
        }
        bufferedReader.close();
        road.roadTxtLineCount=lineCount;
        logger.info("read RoadTxt over...");
        return road;
    }

    /**
     * 道路初始化, FromToRoadId[road.roadCrossIdFrom][road.roadCrossIdTo]=road.roadId和
     *            modelMatrix[road.roadCrossIdFrom][road.roadCrossIdTo]=road.roadLength/speed
     *
     * @param carSpeed
     * @param road
     * @param FromToRoadId
     * @param modelMatrix
     */
    static void buildModel( int carSpeed,Road road, int [][]FromToRoadId, int[][]modelMatrix){
        int modelSize= modelMatrix.length;

        for(int i=0;i<modelSize;i++){
            Arrays.fill(modelMatrix[i],Constant.unLimitedMax);
            Arrays.fill(FromToRoadId[i],0);
            modelMatrix[i][i]=0;
        }

        for(int i=0;i<road.roadTxtLineCount;i++){
            int from = road.roadCrossIdFrom[i];
            int to = road.roadCrossIdTo[i];
            int roadLen=road.roadLength[i];
            int roadId=road.roadId[i];
            int roadPassTime=roadLen/(Math.min(carSpeed,road.roadMaxSpeed[i]));
            modelMatrix[from][to]=roadPassTime;
            FromToRoadId[from][to]=roadId;

            if(road.roadIsDuplex[i]==1){
                modelMatrix[to][from]=roadPassTime;
                FromToRoadId[to][from]=roadId;
            }
        }
    }

    /**
     * Dijkstra算法，
     * @param start
     * @param modelMatrix
     * @param PATH
     * @param distance
     */
    static void Dijkstra(int start,int [][]modelMatrix,int []PATH,int []distance,boolean[]flag){
        int modelSize= modelMatrix.length;
        Arrays.fill(PATH,-1);
        for(int i=1;i<modelSize;i++){
            flag[i]=false;
            distance[i] = modelMatrix[start][i];
            if(distance[i]!=Constant.unLimitedMax){
                PATH[i]=start;
            }else{
                PATH[i]=-1;
            }
        }
        flag[start]=true; distance[start]=0;
        for(int i=1;i<modelSize;i++){
            int t=start,minn=Constant.unLimitedMax;
            for(int j=1;j<modelSize;j++){
                if((!flag[j])&&(distance[j]<minn)){
                    t=j;
                    minn=distance[j];
                }
            }
        if(t==start){
            PATH[start]=-1;
            return;
        }
        flag[t]=true;

        for(int j=1;j<modelSize;j++){
            if((modelMatrix[t][j]<Constant.unLimitedMax)&&(!flag[j])){
                if(distance[j]>(distance[t]+modelMatrix[t][j])){
                    distance[j]=distance[t]+modelMatrix[t][j];
                    PATH[j]=t;
                    }
                }
             }
        }
    }

    static ArrayList<Integer> getPATH(int n,Car car,int crossIdFrom,int crossIdTo,int[]PATH,int[][]FromToRoadId,int[][]modelMatrix){
        int modelSize=PATH.length;
        ArrayList<Integer>res=new ArrayList<>();
        ArrayList<Integer>pathFromTo=new ArrayList<>();
        Stack<Integer>stack=new Stack<>();

        int p=0;
        for(int i=1;i<modelSize;i++) {
            if (crossIdFrom == i)
                continue;
            if (i == crossIdTo) {
                p = PATH[i];
           //     System.out.print(crossIdFrom + "---->" + i + "=====");
                while (p != -1) {
                    stack.push(p);
                    p = PATH[p];
                }
                while (!stack.isEmpty()) {
                    pathFromTo.add(stack.pop());
             //       System.out.print("-->" + stack.pop());
                }
                pathFromTo.add(i);
//                System.out.println("-->" + i + "  ");
            }
        }

        int sum=0;
        //获取路径ID信息
        for(int i=0;i<pathFromTo.size()-1;i++){
            sum+=modelMatrix[pathFromTo.get(i)][pathFromTo.get(i+1)];
            int num=FromToRoadId[pathFromTo.get(i)][pathFromTo.get(i+1)];
            if(num!=0)
                res.add(num);
        }
        car.carSumPassTime[n]=sum+car.carPlanTime[n];
        return res;
    }

    /**
     * 利用CarSumPassTime进行排序
     * @param car
     */
    static Car carPassTimeSort(Car car){
        int size= (int) car.carTxtLineCount;
        int tempCarId[]=new int[size];
        int tempCrossIdFrom[]=new int[size];
        int tempCrossIdTo[]=new int[size];
        int tempCarSpeed[]=new int[size];
        int tempCarPalinTime[]=new int[size];
        int tempCarSumPassTime[][]=new int[size][2];
        for(int i=0;i<size;i++){
            tempCarId[i]=car.carId[i];
            tempCrossIdFrom[i]=car.carCrossIdFrom[i];
            tempCrossIdTo[i]=car.carCrossIdTo[i];
            tempCarSpeed[i]=car.carSpeed[i];
            tempCarPalinTime[i]=car.carPlanTime[i];
            tempCarSumPassTime[i][0]=car.carSumPassTime[i];
            tempCarSumPassTime[i][1]=i;
        }
        int tempNum[][]=new int[size][2];
        for(int i=0;i<size;i++){
            boolean Flag=true;
            for(int j=0;j<size-i-1;j++){
                if(tempCarSumPassTime[j][0]>tempCarSumPassTime[j+1][0]){
                    int tempOne=tempCarSumPassTime[j][0];
                    int tempTwo=tempCarSumPassTime[j][1];
                    tempCarSumPassTime[j][0]=tempCarSumPassTime[j+1][0];
                    tempCarSumPassTime[j][1]=tempCarSumPassTime[j+1][1];
                    tempCarSumPassTime[j+1][0]=tempOne;
                    tempCarSumPassTime[j+1][1]=tempTwo;
                    Flag=false;
                }
            }
            if(Flag)
                break;
        }
        for(int i=0;i<size;i++){
            int one=tempCarSumPassTime[i][0];
            int two=tempCarSumPassTime[i][1];
            car.carId[i]=tempCarId[two];
            car.carCrossIdFrom[i]=tempCrossIdFrom[two];
            car.carCrossIdTo[i]=tempCrossIdTo[two];
            car.carSpeed[i]=tempCarSpeed[two];
            car.carPlanTime[i]=tempCarPalinTime[two];
            car.carSumPassTime[i]=one;
            car.subMark[i]=two;
        }
        return car;
    }



    /**
     * 计算出答案
     * @param car
     * @param PATH
     * @param FromToRoadId
     * @param modelMatrix
     * @param distance
     * @param flag
     * @return
     */
    static Answer calcAnswer(Car car,Road road,int PATH[],int[][]FromToRoadId,int[][]modelMatrix,int[]distance,boolean[]flag) throws IOException {
        logger.info("start to calc Answer...");
        Answer answer=new Answer();
        answer.answerTxtLineCount=car.carTxtLineCount;

        for(int i=0;i<answer.answerTxtLineCount;i++){
            answer.answerCarId[i]=car.carId[i];

            ArrayList<Integer>listPath=new ArrayList<>();

            buildModel(car.carSpeed[i],road,FromToRoadId,modelMatrix); //道路建模
            Dijkstra(car.carCrossIdFrom[i],modelMatrix,PATH,distance,flag);
            listPath=getPATH(i,car,car.carCrossIdFrom[i],car.carCrossIdTo[i],PATH,FromToRoadId,modelMatrix);



            int temp[]=new int[listPath.size()];
            for(int j=0;j<listPath.size();j++){
                temp[j]=listPath.get(j);
            }
            answer.answerRoadId[i]=temp;
//            System.out.println(Arrays.toString(answer.answerRoadId[i]));
        }


        calcAnswerTime(car,answer);


        logger.info(" calc Answer over...");


        return answer;
    }

    /**
     * 计算Answer的StartTime
     * @param car
     * @param answer
     */
    static void calcAnswerTime(Car car,Answer answer) throws IOException {
        logger.info("start to calc Answer Time...");
        car=carPassTimeSort(car);

        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("C:\\Users\\HFUBoy\\Desktop\\one.csv"));

        int carIdSum=0,left=0,right=0;
        for(int i=0;i<car.carTxtLineCount;i++){
            int j=car.subMark[i];
            int num=car.carSumPassTime[i];
           /* if(num<30){
                left=1; right=10;
            }else if(num>=30&&num<50){
                left=0; right=500;
            }else if(num>=50&&num<80){
                left=500; right=3500;
            }else if(num>=80&&num<120){
                left=3500; right=4000;
            }else if(num>=120&&num<150){
                left=4000; right=4500;
            }else if(num>=150&&num<200){
                left=4200; right=4500;
            }
            answer.answerStartTime[j]=num+(left+(int)(Math.random()*(right-left+1)));
*/
            answer.answerStartTime[j]=29;
            System.out.println(car.carId[i]+"   "+car.carPlanTime[i]+"  "+car.carSumPassTime[i]+"    "+answer.answerStartTime[j]);


            //test
            bufferedWriter.write(car.carId[i]+","+answer.answerStartTime[j]);bufferedWriter.newLine();
        }
        bufferedWriter.flush();
        bufferedWriter.close();
        logger.info(" calc Answer over...");
    }


    /**
     * 写入答案
     * @param answerPath
     * @return
     * @throws IOException
     */
    static void  writeAnswerTxt(String answerPath,Answer answer)throws IOException{
        BufferedWriter bufferedWriter=new BufferedWriter(new FileWriter(answerPath));
        int max=Integer.MIN_VALUE;
        int min=Integer.MAX_VALUE;
        try {
            for(int i=0;i<answer.answerTxtLineCount;i++){
                bufferedWriter.write("("+answer.answerCarId[i]);
                bufferedWriter.write(","+answer.answerStartTime[i]);
                max=Math.max(max,answer.answerStartTime[i]); //test
                min=Math.min(min,answer.answerStartTime[i]); //test
                for(int j=0;j<answer.answerRoadId[i].length;j++) {
                    bufferedWriter.write("," + answer.answerRoadId[i][j]);
                }
                bufferedWriter.write(")"); bufferedWriter.newLine();

            }
            System.out.println("max carPassTime:="+max+"min carPassTime:="+min);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            bufferedWriter.flush();
            bufferedWriter.close();
        }

        logger.info("write AnswerTxt over...");
    }
}
