package com.cuber.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @author cuber
 * @create 2022/3/3 23:45
 */


public class Command {
    public static List<String> exeCmd(String commandStr) {
        List<Double> speeds=new ArrayList<>();
        List<String> message=new ArrayList<>();
        BufferedReader br = null;
        try {
            Process p = Runtime.getRuntime().exec(commandStr);
            br = new BufferedReader(new InputStreamReader(p.getInputStream()));

            StringBuilder sbError= new StringBuilder();
            String line = null;
            String lineError= null;

            while ((line = br.readLine()) != null) {
                String []linestr=line.split(" ");

                if ("car".equals(linestr[0])){
                    if (120>Math.abs(Double.parseDouble(linestr[2]))&&5<Math.abs(Double.parseDouble(linestr[2]))){
                        speeds.add(Math.abs(Double.parseDouble(linestr[2])));
                    }
                    message.add(line);
                }
                if("duration".equals(linestr[0])){
                    System.out.println(linestr[1]);
                    message.add(line);
                }
                System.out.println(line);
            }

            BufferedReader    isError = new BufferedReader(new InputStreamReader(p.getErrorStream(),"gbk"));
            while ((lineError= isError.readLine()) != null) {
                sbError.append(lineError);
                sbError.append("\n");
                System.out.println(lineError);
            }

            return message;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public static void main(String[] args) {
        String commandStr1 =
                "D:\\install\\python\\Anaconda3\\envs\\pytorch\\Scripts\\activate.bat && " +
                        "cd D:\\workspace\\graduationproject\\algorithm\\yolov5-deepsort && "+
                        "python D:\\workspace\\graduationproject\\algorithm\\yolov5-deepsort\\count_car.py 1 D:\\workspace\\graduationproject\\algorithm\\yolov5-deepsort\\video\\test_traffic.mp4";

        Command.exeCmd(commandStr1);
    }
}

