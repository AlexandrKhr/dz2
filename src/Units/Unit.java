package Units;

public class Unit {


    private String nameOfUnit;
    private int healthPoint;
    private int damage;
    private int attackRange;
    private int protection;
    private int movement;
    private int price;

    public int getHealthPoint() {
        return healthPoint;
    }

    public String getNameOfUnit() {
        return nameOfUnit;
    }

    public int getAttackRange() {
        return attackRange;
    }

    public int getDamage() {
        return damage;
    }

    public int getMovement() {
        return movement;
    }

    public int getPrice() {
        return price;
    }

    public int getProtection() {
        return protection;
    }

    public void setHealthPoint(int healthPoint) {
        this.healthPoint = healthPoint;
    }

    public void setProtection(int protection) {
        this.protection = protection;
    }

    public void setAttackRange(int attackRange) {
        this.attackRange = attackRange;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void setMovement(int movement) {
        this.movement = movement;
    }

    public Unit(String nameOfUnit, int healthPoint, int damage, int attackRange, int protection, int movement, int price){
        this.nameOfUnit = nameOfUnit;
        this.healthPoint = healthPoint;
        this.damage = damage;
        this.attackRange = attackRange;
        this.protection = protection;
        this.movement = movement;
        this.price = price;
    }


}
