public class Witcher extends Actor_Game2{
    private int kuntCount=0;
    private boolean state_spell=false;
    private int comboCount=0;
    public boolean isState_spell() { return state_spell; }
    public Witcher(String name, int id){
        super(name,id);
        setDamage(80);
        setDefend(0.9);
        setMaxBlood(200);
        setMaxEnergy(2);
        setMedicine_blood(2);
        setMedicine_energy(0);
        setSpellCost(1);
        setCureRate(0.25);
        getActionList().remove("useMedicine_energy");
    }

    @Override
    public void Defend() {
        if(state_spell)
            kuntCount--;
        else{
            comboCount=0;
            System.out.println(getName()+": Witcher   防守！");
            super.Defend();
        }
    }

    @Override
    public void Attack(Actor_Game2 aim) {
        if(state_spell)
            kuntCount--;
        else{
            System.out.println(getName()+": Witcher   攻击了 "+aim.getName()+": "+aim.getClass().getName());
            aim.AttackedBy(this,getDamage()+comboCount*15);
            comboCount++;
        }
    }

    @Override
    public void useMedicineBlood() {
        System.out.println(getName()+": Witcher   使用燕子药水，恢复了生命值！");
        super.useMedicineBlood();
    }

    @Override
    public void AttackedBy(Actor attacker, int damage) {
        if(!state_spell){
            if(getState_defend()){
                Attacked((int)(damage/(2.2*getDefend())));
            }
            else
                Attacked((int)(damage/getDefend()));
        }
    }

    @Override
    public void CastSpell() {
        kuntCount++;
        setEnergy(getEnergy()-getSpellCost());
        if(kuntCount==2){
            state_spell=true;
            setState_active(false);
            System.out.println(getName()+": Witcher   已经凑齐了昆特牌组，决定先暂时离开游戏来一局昆特牌");
        }
        else{
            System.out.println(getName()+": Witcher   收集了部分昆特牌组，但是目前并没有什么用");
        }
        if(getEnergy()<getSpellCost())
            getActionList().remove("CastSpell");
    }

    @Override
    public int updateState() {
        if(isState_die())
            return -1;
        if(state_spell){
            kuntCount--;
            if(kuntCount==0) {
                state_spell=false;
                setState_active(true);
                System.out.println(getName()+": Witcher   玩昆特牌输光了低保，又回到了游戏中");
                return 2;
            }
            else{
                System.out.println(getName()+": Witcher   现在还在愉快的玩昆特牌");
                return 1;
            }
        }
        return 0;
    }
}
