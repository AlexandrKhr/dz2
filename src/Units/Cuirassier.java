package Units;

import Technical_Aspects.Closable;
import Technical_Aspects.Ridable;

public class Cuirassier extends Unit implements Closable, Ridable {
    public Cuirassier(String nameOfUnit, int healthPoint, int damage, int attackRange, int protection, int movement, int price) {
        super(nameOfUnit, healthPoint, damage, attackRange, protection, movement, price);
    }

    @Override
    public void setMovementOfUnit(int movement) {
        setMovement(movement);
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
