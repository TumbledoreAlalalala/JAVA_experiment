public class Master extends Actor_Game1{
    Master(String na){
        super(na);
        setBlood(100);
        setDamage(90);
        setDefend(0.8);
    }
    @Override
    public void Attack(Actor aim) {
        if(!aim.getClass().getName().equals(this.getClass().getName()))
            aim.AttackedBy(this,getDamage()*2);
        else
            aim.AttackedBy(this,getDamage());
    }
    @Override
    public void AttackedBy(Actor attacker, int damage) {
        if(getState_defend()){
            Attacked((int)(damage/(1.5*getDefend())));
            setStatus_defend(false);
        }
        else{
            Attacked((int)(damage/getDefend()));
        }
    }
}
