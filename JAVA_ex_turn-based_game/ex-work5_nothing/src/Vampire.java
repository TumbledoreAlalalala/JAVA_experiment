public class Vampire extends Actor_Game2{
    private int roundCount=0;
    private boolean state_spell=false;
    public Vampire(String name,int id){
        super(name,id);
        setDamage(75);
        setDefend(1.1);
        setMaxBlood(230);
        setMaxEnergy(100);
        setMedicine_blood(1);
        setMedicine_energy(0);
        setSpellCost(50);
        setCureRate(0.4);
        getActionList().remove("useMedicine_energy");
    }

    @Override
    public void Defend() {
        System.out.println(getName()+": Vampire   防守!");
        super.Defend();
        if(roundCount<3)
            roundCount++;
    }

    @Override
    public void useMedicineBlood() {
        System.out.println(getName()+": Vampire   使用了新鲜血液，恢复了生命值!");
        super.useMedicineBlood();
    }

    @Override
    public void Attack(Actor_Game2 aim) {
        System.out.println(getName()+": Vampire  攻击了 "+aim.getName()+":"+aim.getClass().getName()+"   并回复了少量生命值");
        if(roundCount<3)
            roundCount++;
        if(aim.getClass().getName().startsWith("Werewolf")){
            double temp=1.0;
            if(isState_injury())
                temp=0.5;
            aim.AttackedBy(this,getDamage()+10);
            setBlood(getBlood()+(int)(getMaxBlood()*0.1*temp));
        }
        else{
            double temp=1.0;
            if(isState_injury())
                temp=0.5;
            aim.AttackedBy(this,getDamage());
            setBlood(getBlood()+(int)(getMaxBlood()*0.05*temp));
        }
    }

    @Override
    public void AttackedBy(Actor attacker, int damage) {
        if(!state_spell){
            if(getState_defend()){
                if(getState_defend()){
                    Attacked((int)(damage/(1.4*getDefend())));
                }
                else{
                    Attacked((int)(damage/getDefend()));
                    if(attacker.getClass().getName().startsWith("Werewolf")&&roundCount>2)
                        setState_injury(true);
                }
            }
        }
    }

    @Override
    public void CastSpell() {
        setEnergy(getEnergy()-getSpellCost());
        System.out.println(getName()+": Vampire   化身成灵体，抵抗了本回合所有攻击!");
        state_spell=true;
        if(getEnergy()<getSpellCost())
            getActionList().remove("CastSpell");
    }
    @Override
    public int updateState(){
        if(isState_die())
            return -1;
        state_spell=false;
        return 0;
    }
}
