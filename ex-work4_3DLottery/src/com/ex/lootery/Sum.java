package com.ex.lootery;

import java.util.regex.Pattern;

public class Sum extends Lottery3D{
    private int sum_userInput=0;
    public Sum(){
        super.setGameRules("请输入0-27的任一整数");
        System.out.println("Sum构造完成");
    }

    @Override
    public Boolean setUserNumber(String userInput) {
        if(userInput.length()>2)
            return false;
        else{
            Pattern pattern=Pattern.compile("[0-9]*$");
            if(pattern.matcher(userInput).matches()){
                sum_userInput=Integer.parseInt(userInput);
                if(sum_userInput<28)
                    return true;
            }
            return false;
        }
    }

    @Override
    public Integer getBonus() {
        Integer[] winnerNber=super.getWinnerNumber();
        if(sum_userInput==winnerNber[0]+winnerNber[1]+winnerNber[2]){
            switch(sum_userInput){
                case 0:
                case 27:return 1040;
                case 1:
                case 26:return 345;
                case 2:
                case 25:return 172;
                case 3:
                case 24:return 104;
                case 4:
                case 23:return 69;
                case 5:
                case 22:return 49;
                case 6:
                case 21:return 37;
                case 7:
                case 20:return 29;
                case 8:
                case 19:return 23;
                case 9:
                case 18:return 19;
                case 10:
                case 17:return 16;
                case 11:
                case 12:
                case 15:
                case 16:return 15;
                case 13:
                case 14:return 14;
            }
        }
        return 0;
    }
}
