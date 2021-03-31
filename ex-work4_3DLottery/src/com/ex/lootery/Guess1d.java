package com.ex.lootery;

public class Guess1d extends Lottery3D{
    public Guess1d(){
        super.setGameRules("请输入0-9之间的数字");
        System.out.println("Guess1d 构造完成");
    }

    @Override
    public Boolean setUserNumber(String userS) {
        if(userS.length()!=1){
            System.out.println("用户输入超过一位");
            return false;
        }
        else if(!Character.isDigit(userS.charAt(0))){
            System.out.println("用户输入不是数字");
            return false;
        }
        else
            return super.setUserNumber(userS+"**");
    }

    @Override
    public Integer getBonus() {
        Integer[] winnerNber=super.getWinnerNumber();
        Integer userNber=super.getUserNumber()[0];
        int level_Bonus=0;
        for(var e:winnerNber){
            if(e==userNber)
                level_Bonus++;
        }
        if(level_Bonus==1)
            return 2;
        if(level_Bonus==2)
            return 12;
        if(level_Bonus==3)
            return 230;
        return 0;
    }
}
