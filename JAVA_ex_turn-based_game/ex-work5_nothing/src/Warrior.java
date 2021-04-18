public class Warrior extends Actor_Game1{
    Warrior(String na){
        super(na);
        setBlood(300);
        setDamage(30);
        setDefend(1.2);
    }

    @Override
    public void Attack(Actor aim) {
        if(aim.getClass().getName().equals(this.getClass().getName()))
            aim.AttackedBy(this,getDamage()*2);
        else
            aim.AttackedBy(this,getDamage());
    }

    @Override
    public void AttackedBy(Actor attacker, int damage) {
        if(getState_defend()){
            Attacked((int)(damage/(2*getDefend())));
            setStatus_defend(false);
        }
        else{
            Attacked((int)(damage/getDefend()));
        }
    }
}
