package com.ex.lootery;
import java.util.*;
public class Lottery3D {
    private Integer[] userNumber;
    private Integer[] winnerNumber;
    private String gameRules="";
    public Lottery3D(){
        userNumber=new Integer[3];
        winnerNumber=new Integer[3];
        Random winRand=new Random();
        setWinnerNumber(((Integer)winRand.nextInt(1000)).toString());
    }
    public Boolean setUserNumber(String userS){
        int tempLength=userS.length();
        for(int i=0;i<tempLength;++i){
            if(Character.isDigit(userS.charAt(tempLength-1-i)))
                userNumber[2-i]=Integer.parseInt(userS.substring(tempLength-1-i,tempLength-i));
            else
                userNumber[2-i]=Integer.parseInt("-1");
        }
        for(int i=0;i<3-tempLength;++i){
            userNumber[i]=0;
        }
        return true;
    };
    public void setWinnerNumber(String winS){
        int tempLength=winS.length();
        for(int i=0;i<tempLength;++i){
            winnerNumber[2-i]=Integer.parseInt(String.valueOf(winS.charAt(tempLength-1-i)));
        }
        for(int i=0;i<3-tempLength;++i){
            winnerNumber[i]=0;
        }
    }
    public void setGameRules(String reles){
        gameRules =reles;}
    public Integer[] getUserNumber(){return userNumber;}
    public Integer[] getWinnerNumber(){return winnerNumber;}
    public void printWinnerNumber(){
        for(var e:winnerNumber){
            System.out.print(e+" ");
        }
        System.out.println("");
    }
    public String getGameRules(){return gameRules;}
    public Integer getBonus(){System.out.println("这是基类的getBonus，请在子类中实现具体功能");return -1;}
}
