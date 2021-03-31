package com.ex.lootery;
import java.util.regex.Pattern;

public class Single extends Lottery3D{
    public Single(){
        super.setGameRules("请输入0-999之间的整数");
        System.out.println("Single构造完成");
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
    public Integer getBonus(){
        for(int i=0;i<3;++i){
            if(!getUserNumber()[i].equals(getWinnerNumber()[i])) {
                return 0;
            }
        }
        return 1040;
    }
}
