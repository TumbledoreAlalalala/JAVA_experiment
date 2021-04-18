import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Game_1 extends Game{
    private ArrayList<Actor_Game1> Defend_ActionList;
    private ArrayList<Actor_Game1> Attack_ActionList;
    private Actor_Game1 NPC;
    private Actor_Game1 User;
    private String nameActor_NPC;
    private String nameActor_User;
    private int round=0;
    @Override
    public void Play() {
        Scanner in=new Scanner(System.in);
        Random random=new Random();
        while (true) {
            //用户选择角色
            while(true){
                System.out.println("请选择角色：Warrior或者Master");
                String userInput=in.next().toUpperCase();
                in.nextLine();
                userInput=userInput.charAt(0)+userInput.substring(1).toLowerCase();
                if(userInput.startsWith("Master")){
                    User=new Master("User");
                    nameActor_User="Master";
                    break;
                }
                else if(userInput.startsWith("Warrior")){
                    User=new Warrior("User");
                    nameActor_User="Warrior";
                    break;
                }
                else{
                    System.out.println("角色选择输入有误。");
                }
            }
            System.out.println("Player Actor: "+nameActor_User);

            //生成电脑角色
            int tempChoose=random.nextInt(2);
            if(tempChoose==0)
                NPC=new Warrior("NPC");
            else
                NPC=new Master("NPC");
            nameActor_NPC=NPC.getClass().getName();
            System.out.println("NPC Actor: "+nameActor_NPC);
            //斗殴流程
            while(!getGameState_over()){
                Defend_ActionList=new ArrayList<>();
                Attack_ActionList=new ArrayList<>();
                round++;
                //用户选择操作
                while(true){
                    System.out.println("请选择操作：Defend或者Attack");
                    String userInput=in.next().toUpperCase();
                    in.nextLine();
                    userInput=userInput.charAt(0)+userInput.substring(1).toLowerCase();
                    if(userInput.startsWith("Defend")){
                        Defend_ActionList.add(User);
                        break;
                    }
                    else if(userInput.startsWith("Attack")){
                        Attack_ActionList.add(User);
                        break;
                    }
                    else{
                        System.out.println("操作选择输入有误!");
                    }
                }
                //电脑角色选择操作
                tempChoose=random.nextInt(2);
                if(tempChoose==0)
                    Defend_ActionList.add(NPC);
                else
                    Attack_ActionList.add(NPC);

                //执行角色的操作
                System.out.println("Round "+round);
                for(var e:Defend_ActionList){
                    System.out.println(e.getName()+"：Defend!");
                    e.Defend();
                }
                for(var e:Attack_ActionList){
                    System.out.println(e.getName()+"：Attack!");
                    if(e.getName().equals("NPC")){
                        e.Attack(User);
                    }
                    else{
                        e.Attack(NPC);
                    }
                }
                if(NPC.isState_die()||User.isState_die()){
                    setGameState_over(true);
                    if(NPC.isState_die()&&User.isState_die()){
                        System.out.println("Draw!");
                    }
                    else if(NPC.isState_die()){
                        System.out.println("You win!");
                    }
                    else{
                        System.out.println("You lost!");
                    }
                    System.out.println("Player:"+nameActor_User+"  Blood:"+User.getBlood());
                    System.out.println("NPC: "+nameActor_NPC+" Blood:"+NPC.getBlood());
                    break;
                }
                System.out.println("Player:"+nameActor_User+"  Blood:"+User.getBlood());
                System.out.println("NPC: "+nameActor_NPC+" Blood:"+NPC.getBlood());
                System.out.println();
            }
            System.out.println("再整一把？ Y/N");
            String tempInput=in.next().toUpperCase();
            if(!tempInput.startsWith("Y"))
                break;
            else{
                setGameState_over(false);
            }
        }
    }

}
