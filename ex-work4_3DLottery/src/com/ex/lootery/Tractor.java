package com.ex.lootery;

public class Tractor extends Lottery3D{
    public Tractor(){ }

    @Override
    public Integer getBonus() {
        Integer[] winnerNber=super.getWinnerNumber();
        if((winnerNber[1]-winnerNber[0]==1)&&(winnerNber[2]-winnerNber[1]==1))
            return 65;
        if((winnerNber[0]-winnerNber[1]==1)&&(winnerNber[1]-winnerNber[2]==1))
            return 65;
        return 0;
    }
}
