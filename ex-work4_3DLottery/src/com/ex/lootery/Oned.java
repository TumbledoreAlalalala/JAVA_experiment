package com.ex.lootery;

public class Oned extends Lottery3D{
    public Oned(){
        super.setGameRules("请输入确定位置的一个数字，其他位输入*，例如，如果确定个位数为2，请输入**2");
        System.out.println("Oned 完成构造");
    }

    @Override
    public Boolean setUserNumber(String userInput) {
        if(userInput.length()!=3){
            return false;
        }
        else{
            int markforStar=0;
            for(int i=0;i<3;++i){
               if(Character.isDigit(userInput.charAt(i))) {
                   continue;
               }
               if(userInput.charAt(i)=='*')
                   markforStar++;
               else{
                   return false;
               }
            }
            if(markforStar!=2) {
                return false;
            }
            else
            {
                System.out.println("用户输入检查无异常  传入基类 的构造函数");
                return super.setUserNumber(userInput);
            }

        }
    }

    @Override
    public Integer getBonus() {
        for(int i=0;i<3;++i){
            if(super.getUserNumber()[i]==-1)
                continue;
            if(super.getUserNumber()[i].equals(super.getWinnerNumber()[i]))
                return 10;
        }
        return 0;
    }
}
