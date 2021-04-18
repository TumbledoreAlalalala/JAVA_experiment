public class Actor_Game1 extends Actor{
    private  String name;
    private Integer damage=0;
    private Double defend=1.0;
    public void setDamage(int damage){this.damage=damage;}
    public void setDefend(Double defend1){defend=defend1;}
    public Integer getDamage(){return damage;}
    public  Double getDefend(){return defend;}
    public Actor_Game1(String na){name=na;}
    public String getName(){return name;}
}
