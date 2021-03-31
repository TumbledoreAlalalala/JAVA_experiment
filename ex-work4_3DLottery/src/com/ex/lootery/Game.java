package com.ex.lootery;

import java.util.Scanner;
import java.lang.reflect.*;

public class Game {
    private GameMode gamemode =null;
    public void Play() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException, ClassNotFoundException {
        while(true){
            //选取游戏模式
            gamemode=null;
            System.out.println("请输入投注方式：\nSINGLE,GROUP,ONED,TWOD,GUESS1D,SUM,GENERAL,PACKAGE,TRACTOR;\n//不区分大小写");
            while(true){
                Scanner in=new Scanner(System.in);
                String Input_gameMode=in.next().toUpperCase();
                GameMode[] modes=GameMode.values();
                for(var e:modes){
                    if(e.toString().equals(Input_gameMode)){
                        gamemode =e;
                        break;
                    }
                }
                in.nextLine();
                //游戏模式选取成功
                if(gamemode !=null){
                    break;
                }
                //游戏模式选取失败
                else{
                    System.out.println("投注方式不存在 请输入正确的投注方式：\nSINGLE,GROUP,ONED,GUESS1D,TWOD,SUM\n//不区分大小写");
                }
            }
            //游戏模式选取完成


            //构建相应的类的实例
            String tempName=gamemode.toString();
            Class gameClass=Class.forName("com.ex.lootery."+tempName.charAt(0)+tempName.substring(1).toLowerCase());
            System.out.println(gameClass.getName());
            Object game_obj=gameClass.getDeclaredConstructor().newInstance();
            //实例化完成


            //正确获取用户号码
            System.out.print("中奖号码为：");
            Method method_print_winNber=gameClass.getMethod("printWinnerNumber");
            method_print_winNber.invoke(game_obj);
            Method method_get_rules=gameClass.getMethod("getGameRules");
            System.out.println((String)method_get_rules.invoke(game_obj));
            String tempstrforname=gameClass.getName().toUpperCase();
            boolean isTractor=false;
            if(tempstrforname.length()>=7){
                tempstrforname=tempstrforname.substring(tempstrforname.length()-7);
                if(tempstrforname.equals("TRACTOR"))
                    isTractor=true;
            }
            if(isTractor==false)
                while(true){
                String Input_userNumber;
                Scanner in=new Scanner(System.in);
                Input_userNumber=in.next();
                Method method_set_userNber=gameClass.getMethod("setUserNumber",String.class);
                //获取用户号码成功
                if((Boolean)method_set_userNber.invoke(game_obj,Input_userNumber)==true)
                    break;
                //获取用户号码失败
                else{
                    in.nextLine();
                    System.out.println("投注内容不符合游戏规则");
                    System.out.println((String)method_get_rules.invoke(game_obj));
                }
            }
            //获取用户号码完成


            //获取奖金数额
            Method method_get_bonus=gameClass.getDeclaredMethod("getBonus");
            System.out.println("奖金数额为："+method_get_bonus.invoke(game_obj));

            //查询游戏是否继续
            System.out.println("\n\n退出游戏请输入:q");
            Scanner in=new Scanner(System.in);
            String overStr=in.next();
            in.nextLine();
            if(overStr.charAt(0)=='q'||overStr.charAt(0)=='Q')
                break;
        }
    }
}
