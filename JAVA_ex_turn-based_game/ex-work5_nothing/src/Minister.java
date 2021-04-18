public class Minister extends Actor_Game2{
    private boolean state_spell=false;
    public Minister(String name,int id){
        super(name,id);
        setDamage(45  );
        setDefend(0.8);
        setMaxBlood(150);
        setMaxEnergy(200);
        setMedicine_blood(3);
        setMedicine_energy(2);
        setSpellCost(130);
        setCureRate(0.5);
    }

    @Override
    public void Defend() {
        System.out.println(getName()+": Minister  防守!");
        super.Defend();
    }

    @Override
    public void useMedicineBlood() {
        if(state_spell) {
            setMedicine_blood(getMedicine_blood()+1);
            state_spell=false;
        }
        else
            System.out.println(getName()+": Minister  嘬了口圣水，恢复了生命值!");
        super.useMedicineBlood();
    }

    @Override
    public void useMedicineEnergy() {
        System.out.println(getName()+": Minister  进行了祷告，恢复了部分能量");
        super.useMedicineEnergy();
    }

    @Override
    public void CastSpell() {
        System.out.println(getName()+": Minister  使用了技能，奶了自己一口!");
        state_spell=true;
        useMedicineBlood();
        setEnergy(getEnergy()-getSpellCost());
        if(getEnergy()<getSpellCost())
            getActionList().remove("CastSpell");
    }

    @Override
    public void Attack(Actor_Game2 aim) {
        System.out.println(getName()+": Minister  攻击了 "+aim.getName()+":"+aim.getClass().getName());
        aim.AttackedBy(this,getDamage());
    }

    @Override
    public void AttackedBy(Actor attacker, int damage) {
        if(getState_defend()){
            Attacked((int)(damage/(1.2*getDefend())));
        }
        else
            Attacked((int)(damage/getDefend()));
    }

    @Override
    public int updateState() {
        if(isState_die())
            return -1;
        return 0;
    }
}

