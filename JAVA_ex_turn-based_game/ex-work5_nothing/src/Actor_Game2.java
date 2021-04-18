import java.util.ArrayList;

public class Actor_Game2 extends Actor{
    private final String name;
    private final int id;
    private int spellCost=0;
    private Integer damage=0;
    private Double defend=1.0;
    private Integer energy=0;
    private int medicine_blood=0;
    private int medicine_energy=0;
    private int maxBlood=0;
    private int maxEnergy=0;
    private double cureRate=0;
    private boolean state_injury=false;
    private ArrayList<String> ActionList;
    private boolean state_active=true;
    private boolean awaken=false;

    public boolean isAwaken(){return awaken;}
    public void setAwaken(boolean awaken){this.awaken=awaken;}
    public boolean isState_active(){return state_active;}
    public void setState_active(boolean state_active){this.state_active=state_active;}
    public boolean isState_injury() { return state_injury; }
    public void setState_injury(boolean injury){this.state_injury=injury;}
    public ArrayList<String> getActionList(){return ActionList;}
    public void setSpellCost(int spellCost){this.spellCost=spellCost;}
    public int getSpellCost(){return spellCost;}
    //public double getCureRate(){return cureRate;}
    public void setCureRate(double cureRate){this.cureRate=cureRate;}
    public int getMaxBlood(){return maxBlood;}
    public void setMaxBlood(int maxBlood){this.maxBlood=maxBlood;setBlood(maxBlood);}
   // public int getMaxEnergy(){return maxEnergy;}
    public void setMaxEnergy(int maxEnergy){this.maxEnergy=maxEnergy;energy=maxEnergy;}
    public void setEnergy(int energy){ this.energy =energy;}
    public int  getEnergy(){return energy;}
    public void setDamage(int da){damage=da;}
    public int getDamage(){return damage;}
    public void setDefend(double de){defend=de;}
    public double getDefend(){return defend;}
    public void setMedicine_blood(int mb){medicine_blood=mb;}
    public int getMedicine_blood(){return medicine_blood;}
    public void setMedicine_energy(int me){medicine_energy=me;}
    public int getMedicine_energy(){return medicine_energy;}
    public void useMedicineBlood(){
        if(maxBlood<cureRate*maxBlood+getBlood())
            super.setBlood(maxBlood);
        else
            super.AddBlood((int)(maxBlood*cureRate));
        medicine_blood--;
        if(medicine_blood==0)
            ActionList.remove("useMedicine_Blood");
    }
    public void useMedicineEnergy(){
        if(maxEnergy<maxEnergy*0.3+energy)
            energy=maxEnergy;
        else
            energy+=(int)(maxEnergy*0.3);
        medicine_energy--;
        if(medicine_energy==0)
            ActionList.remove("useMedicine_energy");
    }
    public String getName(){return name+id;}
    public Actor_Game2(){name="无名";this.id=-1;}
    public Actor_Game2(String name,int id){
        this.name=name;
        this.id=id;
        ActionList=new ArrayList<>();
        ActionList.add("Attack");
        ActionList.add("Defend");
        ActionList.add("CastSpell");
        ActionList.add("useMedicine_Blood");
        ActionList.add("useMedicine_energy");
    }

    public void CastSpell(){}
    public int updateState(){return 0;}
    public void Attack(Actor_Game2 aim){System.out.println("Actor_Game2‘s Attack被调用");}
}
