package com.ex.lootery;

import java.util.regex.Pattern;

public class General extends Lottery3D{
    public General(){
        super.setGameRules("请输入0-999之间的整数");
        System.out.println("General构造完成");
    }

    @Override
    public Boolean setUserNumber(String userInput) {
        if(userInput.length()>3)
            return false;
        else{
            Pattern pattern=Pattern.compile("[0-9]*$");
            if(pattern.matcher(userInput).matches()){
                return super.setUserNumber(userInput);
            }
            else
                return false;
        }
    }

    @Override
    public Integer getBonus() {
        Integer[] winnerNber=super.getWinnerNumber();
        Integer[] userNber=super.getUserNumber();
        int level_bonus=0;
        for(int i=0;i<3;++i){
            if(winnerNber[i]==userNber[i])
                level_bonus++;
        }
        if(level_bonus==3)
            return 470;
        if(level_bonus==2)
            return 21;
        return 0;
    }
}

