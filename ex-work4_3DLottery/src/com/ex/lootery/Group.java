package com.ex.lootery;

import java.util.ArrayList;
import java.util.Random;
import java.util.regex.Pattern;

public class Group extends Lottery3D{
    public Group(){
        Integer[] tempInt=super.getWinnerNumber();
        while(tempInt[0].equals(tempInt[1])&&tempInt[1].equals(tempInt[2])){
            Random rand=new Random();
            setWinnerNumber(((Integer)rand.nextInt(1000)).toString());
            tempInt=super.getWinnerNumber();
        }
        super.setGameRules("请输入0-999之间的整数,且不能是相同的三位数字\neg:999、111、222均不符合规则\n22、55符合规则");
    }

    @Override
    public Boolean setUserNumber(String userInput) {
        if(userInput.length()>3)
            return false;
        else{
            Pattern pattern=Pattern.compile("[0-9]*$");
            if(pattern.matcher(userInput).matches()){
                if(userInput.length()==3&&userInput.charAt(0)==userInput.charAt(1)&&userInput.charAt(1)==userInput.charAt(2))
                    return false;
                else
                    return super.setUserNumber(userInput);
            }
            else
                return false;
        }
    }

    @Override
    public Integer getBonus() {
        ArrayList<Integer> userNList=new ArrayList<>(3);
        for(var e:super.getUserNumber())
            userNList.add(e);
        for(var e:super.getWinnerNumber()){
            for(int i=0;i<userNList.size();++i){
                if(e.equals(userNList.get(i))){
                    userNList.remove(i);
                    break;
                }
            }
        }
        if(userNList.size()!=0)
            return 0;
        Integer[] tempInteger=super.getWinnerNumber();
        if(tempInteger[0].equals(tempInteger[1])||
           tempInteger[1].equals(tempInteger[2])||
           tempInteger[0].equals(tempInteger[2])){
            return 346;
        }
        else{
            return 173;
        }
    }
}
