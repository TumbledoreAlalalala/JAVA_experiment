import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String GameChoose;
        Game ga;
        Scanner userIn=new Scanner(System.in);
        while(true){
            System.out.println("玩哪个？  1、经典1V1回合制(输入1) 2、魔改大乱斗(输入2)");
            GameChoose=userIn.next();
            userIn.nextLine();
            if(GameChoose.startsWith("1")){
                ga=new Game_1();
                break;
            }
            else if(GameChoose.startsWith("2")){
                ga=new Game_2();
                break;
            }
            else{
                System.out.println("请输出 1   或者   2    进行选择！！！！");
            }
        }
        ga.Play();
    }
}
