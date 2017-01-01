public class Hero {
    private String name;
    private char sex;
    private int level;
    private int hp;
    private int mp;
    private int attack_damage;
    private int attack_speed;
    private int movement_speed;
    private int armor;

    private Hero(Builder builder){
        this.name = builder.name;
        this.sex = builder.sex;
        this.level = builder.level;
        this.hp = builder.hp;
        this.mp = builder.mp;
        this.attack_damage = builder.attack_damage;
        this.attack_speed = builder.attack_speed;
        this.movement_speed = builder.movement_speed;
        this.armor = builder.armor;
    }

    public static class Builder{
        String name;
        char sex;
        int level;
        int hp;
        int mp;
        int attack_damage;
        int attack_speed;
        int movement_speed;
        int armor;
        
        public Builder name(String name){
            this.name = name;
            return this;
        }
    
        public Builder sex(char sex){
            this.sex = sex;
            return this;
        }
    
        public Builder level(int level){
            this.level = level;
            return this;
        }
    
        public Builder hp(int hp){
            this.hp = hp;
            return this;
        }
    
        public Builder mp(int mp){
            this.mp = mp;
            return this;
        }
    
        public Builder attack_damage(int attack_damage){
            this.attack_damage = attack_damage;
            return this;
        }
    
        public Builder attack_speed(int attack_speed){
            this.attack_speed = attack_speed;
            return this;
        }
    
        public Builder movement_speed(int movement_speed){
            this.movement_speed = movement_speed;
            return this;
        }
    
        public Builder armor(int armor){
            this.armor = armor;
            return this;
        }
        
        public Hero build(){
            return new Hero(this);
        }
    }

    public String getName(){
        return name;
    }
    
    public char getSex() {
        return sex;
    }
    
    public int getMp() {
        return mp;
    }
    
    public int getArmor() {
        return armor;
    }
    
    public int getMovement_speed() {
    
        return movement_speed;
    }
    
    public int getAttack_speed() {
    
        return attack_speed;
    }
    
    public int getAttack_damage() {
    
        return attack_damage;
    }
    
    public int getLevel() {
    
        return level;
    }
    
    public int getHp() {
    
        return hp;
    }
}
