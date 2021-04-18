public class Werewolf extends Actor_Game2{
    private int roundCount=0;
    public Werewolf(String name,int id){
        super(name,id);
        setDamage(75);
        setDefend(1.1);
        setMaxBlood(230);
        setMaxEnergy(0);
        setMedicine_blood(1);
        setMedicine_energy(0);
        setSpellCost(0);
        setCureRate(0.3);
        getActionList().remove("CastSpell");
        getActionList().remove("useMedicine_energy");
    }

    @Override
    public void Defend() {
        System.out.println(getName()+": Werewolf  防守!");
        super.Defend();
        if(roundCount<6)
            roundCount++;
    }

    @Override
    public void Attack(Actor_Game2 aim) {
        System.out.println(getName()+": Werewolf  攻击了 "+aim.getName()+":"+aim.getClass().getName());
        if(roundCount<6)
            roundCount++;
        if(aim.getClass().getName().startsWith("Vampire")&&roundCount>2)
            aim.AttackedBy(this,2*getDamage());
        else
            aim.AttackedBy(this,getDamage());
    }

    @Override
    public void useMedicineBlood() {
        if(roundCount<6)
            roundCount++;
        System.out.println(getName()+": Werewolf   使用了发青舌头拌饭，恢复了生命值");
        super.useMedicineBlood();
    }


    @Override
    public void AttackedBy(Actor attacker, int damage) {
        if(getState_defend()){
            Attacked((int)(damage/(1.4*getDefend())));
        }
        else
            Attacked((int)(damage/getDefend()));
    }

    @Override
    public int updateState() {
        if(isState_die())
            return -1;
        if(roundCount==5) {
            System.out.println(getName()+": Werewolf   受满月影响觉醒了!!");
            setAwaken(true);
        }

        return 0;
    }
}

