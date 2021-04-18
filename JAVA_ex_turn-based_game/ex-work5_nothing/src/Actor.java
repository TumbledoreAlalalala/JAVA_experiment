public class Actor {
    private Integer blood=0;
    private boolean status_die =false;
    private boolean status_defend=false;
    public void Attack(Actor aim){
        System.out.println("Actor's Attack()被调用");
    }
    public void Attacked(int damage){
        if(damage>=blood){
          blood=0;
            status_die =true;
        }
        else{
            blood-=damage;
        }
    }
    public void AttackedBy(Actor attacker, int damage){
        System.out.println("Actor's AttackedBy()被调用");
    }
    public void Defend(){
        status_defend=true;
    }
    public void AddBlood(int adb){
        if(adb>0)
            blood+=adb;
    }

    public void setBlood(int blood){this.blood=blood;}
    public void setStatus_die(boolean die){
        status_die =die;}
    public void setStatus_defend(boolean status_defend) {
        this.status_defend = status_defend;
    }

    public Integer getBlood(){return blood;}
    public boolean isState_die(){return status_die;}
    public boolean getState_defend(){return status_defend;}
}
