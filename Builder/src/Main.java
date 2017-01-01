public class Main {
    public static void main(String[] args) {
        Hero hero = new Hero.Builder()
                .name("Мегапихарь228")
                .level(6)
                .sex('g')
                .hp(100)
                .mp(40)
                .armor(5)
                .build();
    
        System.out.println("HERO ***" + hero.getName() + "*** (" +
                (hero.getSex() == 'm' ? "Мужик" : (hero.getSex() == 'f' ? "Тёлка" : "Гермафродит")) + ") connected... \n" +
                "Stats: \n" +
                "\t LEVEL: " + hero.getLevel() + "\n" +
                "\t HP: " + hero.getHp() + "\n" +
                "\t MP: " + hero.getMp() + "\n" +
                "\t ATTACK_DAMAGE: " + hero.getAttack_damage() + "\n" +
                "\t ATTACK_SPEED: " + hero.getAttack_speed() + "\n" +
                "\t MOVEMENT_SPEED: " + hero.getMovement_speed() + "\n" +
                "\t ARMOR: " + hero.getArmor() + "\n"
        );
    }
}
