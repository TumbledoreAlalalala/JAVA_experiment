package com.ex.lootery;

public class Twod extends Lottery3D{
    public Twod(){
        super.setGameRules("请输入确定的两个位置上的数字，其他位输入*，例如，如果确定个位数为2、百位数为7，请输入7*2");
        System.out.println("Twod 完成构造");
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
            if(markforStar!=1) {
                return false;
            }
            else
            {
                return super.setUserNumber(userInput);
            }
        }
    }

    @Override
    public Integer getBonus() {
        int tempcount=0;
        for(int i=0;i<3;++i){
            if(super.getUserNumber()[i]==-1)
                continue;
            if(super.getUserNumber()[i].equals(super.getWinnerNumber()[i]))
                tempcount++;
        }
        if(tempcount==2)
            return 104;
        else
            return 0;
    }
}
