import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Game_2 extends Game{
    private ArrayList<Actor_Game2> Defend_ActionList;
    private ArrayList<Actor_Game2> Spell_ActionList1;
    private ArrayList<Actor_Game2> Spell_ActionList2;
    private ArrayList<Actor_Game2> UseMedicineBloodList;
    private ArrayList<Actor_Game2> UseMedicineEnergyList;
    private ArrayList<Actor_Game2> Attack_ActionList;
    private ArrayList<Actor_Game2> AimList;

    private ArrayList<Actor_Game2> ActiveActorList;
    private ArrayList<Actor_Game2> kuntList;
    private Actor_Game2 User;
    private int round=0;
    private int NPCNumber=0;

    @Override
    public void Play() {
        Scanner in=new Scanner(System.in);
        Random random=new Random();
        while(true){
            round=0;
            ActiveActorList=new ArrayList<>();
            kuntList=new ArrayList<>();
            //用户角色选择
            while(true){
                System.out.println("请选择角色\nMinister   Werewolf   Vampire   Witcher");
                String userInput=in.next().toUpperCase();
                in.nextLine();
                userInput=userInput.charAt(0)+userInput.substring(1).toLowerCase();
                if(userInput.startsWith("Minister")){
                    User=new Minister("User",0);
                }
                else if(userInput.startsWith("Werewolf")){
                    User=new Werewolf("User",0);
                }
                else if(userInput.startsWith("Vampire")){
                    User=new Vampire("User",0);
                }
                else if(userInput.startsWith("Witcher")){
                    User=new Witcher("User",0);
                }
                else{
                    System.out.println("角色选择输入有误!");
                    continue;
                }
                ActiveActorList.add(User);
                break;
            }
            //用户选择NPC数量
            while(true){
                System.out.println("请输入NPC数量\n1-20之间");
                Pattern pattern=Pattern.compile("[0-9]*$");
                String userInput=in.next();
                in.nextLine();
                if(pattern.matcher(userInput).matches()){
                    if(userInput.length()>2){
                        System.out.println("请输入1-20的正整数!!");
                    }
                    else{
                        NPCNumber=Integer.parseInt(userInput);
                        if(NPCNumber>20||NPCNumber<1){
                            System.out.println("请输入1-20的正整数!!");
                            NPCNumber=0;
                        }
                        else{
                            break;
                        }
                    }
                }
                else{
                    System.out.println("请输入正整数！！\n1-20之间");
                }
            }
            //生成NPC
            for(int i=0;i<NPCNumber;++i){
                int tempRandNumber=random.nextInt(4);
                if(tempRandNumber==0){
                    ActiveActorList.add(new Minister("NPC",i+1));
                }
                else if(tempRandNumber==1){
                    ActiveActorList.add(new Werewolf("NPC",i+1));
                }
                else if(tempRandNumber==2){
                    ActiveActorList.add(new Vampire("NPC",i+1));
                }
                else{
                    ActiveActorList.add(new Witcher("NPC",i+1));
                }
            }
            System.out.println("初始场上角色及状态信息:");
            for(var e:ActiveActorList)
                System.out.println(e.getName()+": "+e.getClass().getName()+"  Blood:"+e.getBlood()+"  Energy:"+e.getEnergy()+"  Medicine_Blood:"+e.getMedicine_blood()+"  Medicine_Energy:"+e.getMedicine_energy()+"  SpellCost:"+e.getSpellCost());

            //斗殴流程
            while(true){
                round++;
                System.out.println("round:"+round);
                Defend_ActionList=new ArrayList<>();
                Spell_ActionList1=new ArrayList<>();
                Spell_ActionList2=new ArrayList<>();
                Attack_ActionList =new ArrayList<>();
                AimList=new ArrayList<>();
                UseMedicineEnergyList=new ArrayList<>();
                UseMedicineBloodList =new ArrayList<>();
                //用户选择操作
                if(User.isState_active()){
                    if(!User.isState_die())
                        while(true){
                        System.out.println("请选择操作");
                        for(var e:User.getActionList())
                            System.out.print(e+" ");
                        System.out.println();
                        String userInput=in.next().toUpperCase();
                        in.nextLine();
                        String tempactive=" ";
                        for(var e:User.getActionList()){
                            if(userInput.startsWith(e.toUpperCase())){
                                tempactive=e.toUpperCase();
                                break;
                            }
                        }
                        if(tempactive.equals(" ")){
                            System.out.println("操作选择输入有误，请选择操作：");
                        }
                        else{
                            if(tempactive.equals("DEFEND")){
                                Defend_ActionList.add(User);
                            }
                            else if(tempactive.equals("CASTSPELL")){
                                if(User.getClass().getName().equals("Witcher"))
                                    Spell_ActionList1.add(User);
                                else
                                    Spell_ActionList2.add(User);
                            }
                            else if(tempactive.equals("USEMEDICINE_BLOOD"))
                                UseMedicineBloodList.add(User);
                            else if(tempactive.equals("USEMEDICINE_ENERGY"))
                                UseMedicineEnergyList.add(User);
                            else{
                                Attack_ActionList.add(User);
                                while(ActiveActorList.size()>1&&true){
                                    int tempChoose=random.nextInt(ActiveActorList.size());
                                    if(!ActiveActorList.get(tempChoose).getName().startsWith("User")){
                                        AimList.add(ActiveActorList.get(tempChoose));
                                        break;
                                    }
                                }
                            }
                            break;
                        }
                    }
                    else
                        System.out.println("你已经无了，看着吧");
                }
                else{
                    System.out.println("你的角色在愉快的玩耍昆特牌，无心参与战斗。");
                }
                //电脑选择操作
                for(var e:ActiveActorList){
                    if(!e.getName().startsWith("User")) {
                        String tempAction=e.getActionList().get(random.nextInt(e.getActionList().size()));
                        if(tempAction.equals("Attack")){
                            Attack_ActionList.add(e);
                            while(true){
                                int tempChoose=random.nextInt(ActiveActorList.size());
                                if(!ActiveActorList.get(tempChoose).getName().equals(e.getName())){
                                    AimList.add(ActiveActorList.get(tempChoose));
                                    break;
                                }
                            }
                        }
                        else if(tempAction.equals("Defend"))
                            Defend_ActionList.add(e);
                        else if(tempAction.equals("useMedicine_Blood"))
                            UseMedicineBloodList.add(e);
                        else if(tempAction.equals("useMedicine_energy"))
                            UseMedicineEnergyList.add(e);
                        else if(tempAction.equals("CastSpell")){
                            if(e.getClass().getName().equals("Witcher"))
                                Spell_ActionList1.add(e);
                            else
                                Spell_ActionList2.add(e);
                        }
                    }
                    if(e.getClass().getName().equals("Werewolf")){
                        if(e.isAwaken()){
                            System.out.println(e.getName()+": Werewolf 使用了觉醒力量，额外选择了一个攻击目标");
                            Attack_ActionList.add(e);
                            while(true){
                                int tempChoose=random.nextInt(ActiveActorList.size());
                                if(!ActiveActorList.get(tempChoose).getName().equals(e.getName())){
                                    AimList.add(ActiveActorList.get(tempChoose));
                                    break;
                                }
                            }
                        }
                    }
                }
                for(var e:kuntList)
                    if(e.getName().startsWith("NPC"))
                         System.out.println(e.getName()+": "+e.getClass().getName()+"  正在玩昆特牌，无心参与战斗");
                //执行操作
                for(var e:Defend_ActionList){
                    e.Defend();
                }
                for(var e:UseMedicineBloodList){
                    e.useMedicineBlood();
                }
                for(var e:UseMedicineEnergyList){
                    e.useMedicineEnergy();
                }
                for(var e:Spell_ActionList1){
                    e.CastSpell();
                }
                for(var e:Spell_ActionList2){
                    e.CastSpell();
                }
                for(int i=0;i<Attack_ActionList.size();++i){
                    if(AimList.isEmpty()){
                        System.out.println(ActiveActorList.get(i).getName()+"本想攻击一个witcher，可目标在隔壁打昆特牌");
                    }
                    else
                        Attack_ActionList.get(i).Attack(AimList.get(i));
                }
                //更新角色状态
                ArrayList<Actor_Game2> tempAddList=new ArrayList<>();
                while(!kuntList.isEmpty()){
                    kuntList.get(0).updateState();
                    tempAddList.add(kuntList.remove(0));
                }

                for(int i=0;i<ActiveActorList.size();++i){
                    int updataResult=ActiveActorList.get(i).updateState();
                    if(updataResult==-1){
                        ActiveActorList.remove(i);
                        i--;
                    }
                    else if(updataResult==1){
                        kuntList.add(ActiveActorList.remove(i));
                        i--;
                    }
                }
                ActiveActorList.addAll(tempAddList);
                //判断斗殴是否结束
                System.out.println("round:"+round+"结束");
                if(User.isState_die())
                    System.out.println("玩家角色已死亡");
                if(!kuntList.isEmpty()||ActiveActorList.size()!=1){
                    System.out.println("对局仍未结束");
                    System.out.println("场上存活角色数量："+ActiveActorList.size()+"   还有"+kuntList.size()+"个场外摸鱼的杰骆驼");
                    for(var e:kuntList)
                        System.out.println(e.getName()+": "+e.getClass().getName()+"   Blood:"+e.getBlood()+"  Energy:"+e.getEnergy());
                    for(var e:ActiveActorList)
                        System.out.println(e.getName()+": "+e.getClass().getName()+"   Blood:"+e.getBlood()+"  Energy:"+e.getEnergy());
                    System.out.println("\n");
                }
                else{
                    System.out.println("对局结束!");
                    if(ActiveActorList.isEmpty())
                        System.out.println("无人生还！！");
                    else{
                        if(ActiveActorList.get(0).getName().startsWith("User"))
                            System.out.println("你赢了！");
                        else{
                            System.out.println("你输了");
                            System.out.println("Winner: "+ActiveActorList.get(0).getName());
                        }
                    }
                    break;
                }
            }
            //更新局势信息
            System.out.println("再整一把？ Y/N");
            String tempInput=in.next().toUpperCase();
            if(!tempInput.startsWith("Y")) {
                setGameState_over(true);
                break;
            }
            else{
                setGameState_over(false);
            }
        }
    }
}
