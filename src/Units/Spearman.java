package Units;

import Technical_Aspects.Closable;

public class Spearman extends Unit implements Closable {
    public Spearman(String nameOfUnit, int healthPoint, int damage, int attackRange, int protection, int movement, int price) {
        super(nameOfUnit, healthPoint, damage, attackRange, protection, movement, price);
    }

    @Override
    public void setDamageOfUnit(int damage) {
        setDamage(damage);
    }

    @Override
    public void setAttackRangeOfUnit(int range) {
        setAttackRange(range);
    }
}
