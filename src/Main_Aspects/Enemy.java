package Main_Aspects;

import Technical_Aspects.IFieldArray;
import Technical_Aspects.SettingsMenu;
import Units.*;

import java.security.Key;
import java.util.Scanner;

public class Enemy implements IFieldArray {

    public Enemy(int amountOfRowsXIndex, int amountOfColumnsYIndex, int Index, int typeOfEnemyUnit){
        this.amountOfColumnsYIndex = amountOfColumnsYIndex;
        this.amountOfRowsXIndex = amountOfRowsXIndex;
        this.enemyIndex = Index;
        this.typeOfUnit = typeOfEnemyUnit;
        enemyUnitTypeDefinition(typeOfUnit);
    }

    private int amountOfColumnsYIndex;
    private int amountOfRowsXIndex;
    private int enemyIndex;
    private char enemySymbol;
    private int typeOfUnit;
    private String classOfUnit;
    private String typeOfObstacleForDrawing;
    private Unit unit;
    private boolean isStepOnObstacle;

    public int getAmountOfColumnsYIndex() {
        return amountOfColumnsYIndex;
    }

    public int getAmountOfRowsXIndex() {
        return amountOfRowsXIndex;
    }

    public void setAmountOfColumnsYIndex(int amountOfColumnsYIndex) {
        this.amountOfColumnsYIndex = amountOfColumnsYIndex;
    }

    public void setAmountOfRowsXIndex(int amountOfRowsXIndex) {
        this.amountOfRowsXIndex = amountOfRowsXIndex;
    }

    @Override
    public String getSymbol() {
        return " " + String.valueOf(enemyIndex) + " ";
    }

    Swordsman swordsman = new Swordsman("Swordsman",50, 5, 1, 8, 3, 10);
    Spearman spearman = new Spearman("Spearman",35,3,1,4,6,15);
    Axeman axeman = new Axeman("Axeman",45,9,1,3,4,20);
    ArcherLong archerLong = new ArcherLong("ArcherLong", 30,6,5,8,2,15);
    ArcherShort archerShort = new ArcherShort("ArcherShort", 25,3,3,4,4,19);
    Crossbowman crossbowman = new Crossbowman("Crossbowman",40,7,6,3,2,23);
    Knight knight = new Knight("Knight",30,5,1,3,6,20);
    Cuirassier cuirassier = new Cuirassier("Cuirassier",50,2,1,7,5,23);
    ArcherHorse archerHorse = new ArcherHorse("ArcherHorse",25,3,3,2,5,25);

    private void enemyUnitTypeDefinition(int typeOfUnit){

        switch (typeOfUnit){

            case 1:
                this.unit = swordsman;
                this.classOfUnit = "foots";
                break;

            case 2:
                this.unit = spearman;
                this.classOfUnit = "foots";
                break;

            case 3:
                this.unit = axeman;
                this.classOfUnit = "foots";
                break;

            case 4:
                this.unit = archerLong;
                this.classOfUnit = "archers";
                break;

            case 5:
                this.unit = archerShort;
                this.classOfUnit = "archers";
                break;

            case 6:
                this.unit = crossbowman;
                this.classOfUnit = "archers";
                break;

            case 7:
                this.unit = knight;
                this.classOfUnit = "riders";
                break;

            case 8:
                this.unit = cuirassier;
                this.classOfUnit = "riders";
                break;

            case 9:
                this.unit = archerHorse;
                this.classOfUnit = "riders";
                break;

            default:
                System.out.println("Something went wrong with Enemy unit, please restart the game! ");
                break;
        }

    }

    public String getNameOfUnit(){
        return unit.getNameOfUnit();
    }
    public int getHealthPointOfUnit(){
        return unit.getHealthPoint();
    }
    public int getDamageOfUnit(){
        return unit.getDamage();
    }
    public int getAttackRangeOfUnit(){
        return unit.getAttackRange();
    }
    public int getProtectionOfUnit(){
        return unit.getProtection();
    }
    public int getMovementOfUnit(){
        return unit.getMovement();
    }
    public int getPriceOfUnit(){
        return unit.getPrice();
    }
    public void setHealthPoint(int healthPoint){
        unit.setHealthPoint(healthPoint);
    }
    public void setProtection(int protection){
        unit.setProtection(protection);
    }
    public String getClassOfUnit() {
        return classOfUnit;
    }
    public void setIsStepOnObstacleTrue(){
        this.isStepOnObstacle = true;
    }
    public void setIsStepOnObstacleFalse(){
        this.isStepOnObstacle = false;
    }
    public boolean isStepOnObstacle() {
        return isStepOnObstacle;
    }
    public void typeOfObstacle(String typeOfObstacle){

        switch (typeOfObstacle){

            case " ! ":
                this.typeOfObstacleForDrawing = "Tree";
                break;

            case " @ ":
                this.typeOfObstacleForDrawing = "Hill";
                break;

            case " # ":
                this.typeOfObstacleForDrawing = "Swamp";
                break;

            default:
                break;

        }

    }
    public String getTypeOfObstacleForDrawing() {
        return typeOfObstacleForDrawing;
    }
}
