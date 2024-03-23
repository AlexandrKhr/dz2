package Units;

import Technical_Aspects.Ridable;
import Technical_Aspects.Shootable;

public class ArcherHorse extends Unit implements Shootable, Ridable {
    public ArcherHorse(String nameOfUnit, int healthPoint, int damage, int attackRange, int protection, int movement, int price) {
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
    @Override
    public void setMovementOfUnit(int movement) {
        setMovement(movement);
    }
}
