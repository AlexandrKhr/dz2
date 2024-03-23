package Main_Aspects;

import Design.PlainSquare;
import Main_Aspects.Enemy;
import Main_Aspects.Game;
import Penalties.Obstacle;
import Technical_Aspects.Closable;
import Technical_Aspects.Field;
import Technical_Aspects.IFieldArray;
import Technical_Aspects.SettingsMenu;
import Units.*;

import java.util.Scanner;

public class Player implements IFieldArray {

    private int amountOfColumnsYIndex;
    private int amountOfRowsXIndex;
    private int amountOfPlayers;
    private int typeOfPlayer;
    private int amountOfGold = SettingsMenu.getAmountOfGold();
    private Unit unit;
    private int playerIndex;
    private int penaltyForObstacle;
    private int newSizeX;
    private int newSizeY;
    private boolean isCycleStarted = false;
    private boolean isCycleStarted1 = false;
    private boolean isRunOutOfMoney = false;
    private boolean isStepOnObstacle = false;
    private boolean isNeededToRedraw = false;
    private char playerSymbol;
    private Field field;
    private Game game;
    private String typeOfObstacle;
    private String typeOfObstacleForDrawing;
    private String typeOfUnit;
    private static final String MOVE_LEFT = "a";
    private static final String MOVE_RIGHT = "d";
    private static final String MOVE_UP = "w";
    private static final String MOVE_DOWN = "s";
    private static final String NO_MOVE = "f";

    Swordsman swordsman = new Swordsman("Swordsman",50, 5, 1, 8, 3, 10);
    Spearman spearman = new Spearman("Spearman",35,3,1,4,6,15);
    Axeman axeman = new Axeman("Axeman",45,9,1,3,4,20);
    ArcherLong archerLong = new ArcherLong("ArcherLong", 30,6,5,8,2,15);
    ArcherShort archerShort = new ArcherShort("ArcherShort", 25,3,3,4,4,19);
    Crossbowman crossbowman = new Crossbowman("Crossbowman",40,7,6,3,2,23);
    Knight knight = new Knight("Knight",30,5,1,3,6,20);
    Cuirassier cuirassier = new Cuirassier("Cuirassier",50,2,1,7,5,23);
    ArcherHorse archerHorse = new ArcherHorse("ArcherHorse",25,3,3,2,5,25);
    ArcherShort superArcher = new ArcherShort("SuperArcher", 1000, 1000,1000,1000,10,0);

    @Override
    public String getSymbol() {
        return " " + String.valueOf(playerSymbol) + " ";
    }


    public Player(int sizeXIndex, int sizeYIndex, Game game, int playerIndex, String typeOfUnit){
        this.isCycleStarted = false;
        this.amountOfColumnsYIndex = sizeYIndex;
        this.amountOfRowsXIndex = sizeXIndex;
        this.playerIndex = playerIndex + 97;
        this.playerSymbol = (char) this.playerIndex;
        unitTypeDefinition(typeOfUnit);
        this.game = game;
        this.field = game.getFieldFromGame();
        field.setFieldArray(sizeXIndex, sizeYIndex, this);


    }

    public void setPlayerSymbol(char playerSymbol) {
        this.playerSymbol = playerSymbol;
    }

    public int getSizeXIndex() {
        return amountOfRowsXIndex;
    }

    public int getSizeYIndex() {
        return amountOfColumnsYIndex;
    }

    public void moveTo(String command){

        if (isCycleStarted1)
        {
            Scanner scanner = new Scanner(System.in);
            command = scanner.nextLine();
        }

        switch (command){

            case MOVE_LEFT:
                movePlayer(0, -1);
                break;

            case MOVE_RIGHT:
                movePlayer(0,1);
                break;

            case MOVE_UP:
                movePlayer(-1,0);
                break;

            case MOVE_DOWN:
                movePlayer(1,0);
                break;

            case NO_MOVE:
                System.out.println("The unit remained in it's position");
                break;

            default:
                System.out.println("You pressed the wrong button!");
                isCycleStarted1 = true;
                moveTo(command);
                break;

        }
        isCycleStarted1 = false;

    }

    private void movePlayer(int changeX, int changeY){

        int newSizeOfColumnsYIndex = amountOfColumnsYIndex + changeY;
        int newSizeOfRowsXIndex = amountOfRowsXIndex + changeX;

        if ((newSizeOfColumnsYIndex >= 0) && (newSizeOfColumnsYIndex < SettingsMenu.getSizeY()) && (newSizeOfRowsXIndex >= 0)
                && (newSizeOfRowsXIndex < SettingsMenu.getSizeX()) &&
                !((field.getFieldArray(newSizeOfRowsXIndex, newSizeOfColumnsYIndex)) instanceof Enemy)
        && !((field.getFieldArray(newSizeOfRowsXIndex, newSizeOfColumnsYIndex)) instanceof Player)){

            if ((field.getFieldArray(newSizeOfRowsXIndex, newSizeOfColumnsYIndex) instanceof PlainSquare) ||
                    (field.getFieldArray(newSizeOfRowsXIndex, newSizeOfColumnsYIndex) instanceof Obstacle)){

                relocatePlayer(newSizeOfRowsXIndex, newSizeOfColumnsYIndex);
                if(isStepOnObstacle){
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
                    this.isNeededToRedraw = true;
                    isStepOnObstacle = false;
                }
            }

        }

    }

    private void relocatePlayer(int newSizeXIndex, int newSizeYIndex){
        field.setFieldArray(newSizeXIndex, newSizeYIndex, this);
        if(isNeededToRedraw){
            field.setFieldArray(amountOfRowsXIndex, amountOfColumnsYIndex, new Obstacle(typeOfObstacleForDrawing));
            isNeededToRedraw = false;
        }
        else
            field.setFieldArray(amountOfRowsXIndex, amountOfColumnsYIndex, new PlainSquare());
        amountOfRowsXIndex = newSizeXIndex;
        amountOfColumnsYIndex = newSizeYIndex;
    }

    public String penaltyForObstacle(String command){

        switch (command) {

            case MOVE_LEFT:
                newSizeX = 0;
                newSizeY = -1;
                break;

            case MOVE_RIGHT:
                newSizeX = 0;
                newSizeY = 1;
                break;

            case MOVE_UP:
                newSizeX = -1;
                newSizeY = 0;
                break;

            case MOVE_DOWN:
                newSizeX = 1;
                newSizeY = 0;
                break;

            default:
                break;
        }
        int newSizeOfColumnYIndex = amountOfColumnsYIndex + newSizeY;
        int newSizeOfRowXIndex = amountOfRowsXIndex + newSizeX;

        if ((newSizeOfColumnYIndex >= 0) && (newSizeOfColumnYIndex < SettingsMenu.getSizeY()) && (newSizeOfRowXIndex >= 0)
                && (newSizeOfRowXIndex < SettingsMenu.getSizeX()) &&
                (field.getFieldArray(newSizeOfRowXIndex, newSizeOfColumnYIndex) instanceof Obstacle)){
            this.typeOfObstacle = field.getFieldArray(newSizeOfRowXIndex, newSizeOfColumnYIndex).getSymbol();
            return typeOfObstacle;
        }
        else
         return "null";
    }

    public void unitTypeDefinition(String typeOfPlayer){
        if(isCycleStarted) {
            Scanner scanner = new Scanner(System.in);
            typeOfPlayer = scanner.nextLine();
        }
        switch (typeOfPlayer){

            case "1":
                this.unit = swordsman;
                this.typeOfUnit = "foots";
                break;

            case "2":
                this.unit = spearman;
                this.typeOfUnit = "foots";
                break;

            case "3":
                this.unit = axeman;
                this.typeOfUnit = "foots";
                break;

            case "4":
                this.unit = archerLong;
                this.typeOfUnit = "archers";
                break;

            case "5":
                this.unit = archerShort;
                this.typeOfUnit = "archers";
                break;

            case "6":
                this.unit = crossbowman;
                this.typeOfUnit = "archers";
                break;

            case "7":
                this.unit = knight;
                this.typeOfUnit = "riders";
                break;

            case "8":
                this.unit = cuirassier;
                this.typeOfUnit = "riders";
                break;

            case "9":
                this.unit = archerHorse;
                this.typeOfUnit = "riders";
                break;

            case "10":
                this.unit = superArcher;
                this.typeOfUnit = "archers";
                break;

            case "404":
                System.out.println("It was necessary to use gold more rationally!");
                this.isRunOutOfMoney = true;
                break;

            default:
                System.out.println("There is no such unit in our list! ");
                isCycleStarted = true;
                unitTypeDefinition(typeOfPlayer);
                break;

        }
        if ((this.amountOfGold - this.unit.getPrice() < 0) && !isRunOutOfMoney){
            System.out.println("You don't have enough gold to buy this unit, please repeat. \n" +
                    "If you no longer have gold, then write '404' ");
            isCycleStarted = true;
            unitTypeDefinition(typeOfPlayer);
        }
        else
            SettingsMenu.setAmountOfGold(amountOfGold-this.unit.getPrice());

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
    public boolean isCycleStarted() {
        return isCycleStarted;
    }
    public boolean isRunOutOfMoney() {
        return isRunOutOfMoney;
    }
    public void setHealthPoint(int healthPoint){
        unit.setHealthPoint(healthPoint);
    }
    public void setProtection(int protection){
        unit.setProtection(protection);
    }
    public String getTypeOfUnit() {
        return typeOfUnit;
    }
    public boolean isStepOnObstacle() {
        return isStepOnObstacle;
    }
    public void setIsStepOnObstacleTrue(){
        this.isStepOnObstacle = true;
    }
    public void setIsStepOnObstacleFalse(){
        this.isStepOnObstacle = false;
    }
    public void upgradeShootableRange(){
        archerHorse.setAttackRangeOfUnit(8);
        crossbowman.setAttackRangeOfUnit(11);
        archerShort.setAttackRangeOfUnit(8);
        archerLong.setAttackRangeOfUnit(10);
    }
    public void upgradeShootableDamage(){
        archerHorse.setDamageOfUnit(9);
        crossbowman.setDamageOfUnit(13);
        archerShort.setDamageOfUnit(9);
        archerLong.setDamageOfUnit(12);
    }
    public void upgradeClosableRange(){
        swordsman.setAttackRangeOfUnit(3);
        spearman.setAttackRangeOfUnit(3);
        axeman.setAttackRangeOfUnit(3);
        knight.setAttackRangeOfUnit(3);
        cuirassier.setAttackRangeOfUnit(3);
    }
    public void upgradeCloasbleDamage(){
        swordsman.setDamageOfUnit(15);
        spearman.setDamageOfUnit(13);
        axeman.setDamageOfUnit(19);
        knight.setDamageOfUnit(15);
        cuirassier.setDamageOfUnit(12);
    }
    public void upgradeRidableMovement(){
        knight.setMovementOfUnit(12);
        cuirassier.setMovementOfUnit(11);
        archerHorse.setMovementOfUnit(11);
    }
    public void cancelUpgradeShootableRange(){
        archerHorse.setAttackRangeOfUnit(3);
        crossbowman.setAttackRangeOfUnit(6);
        archerShort.setAttackRangeOfUnit(3);
        archerLong.setAttackRangeOfUnit(5);
    }
    public void cancelUpgradeShootableDamage(){
        archerHorse.setDamageOfUnit(3);
        crossbowman.setDamageOfUnit(7);
        archerShort.setDamageOfUnit(3);
        archerLong.setDamageOfUnit(6);
    }
    public void cancelUpgradeClosableRange(){
        swordsman.setAttackRangeOfUnit(1);
        spearman.setAttackRangeOfUnit(1);
        axeman.setAttackRangeOfUnit(1);
        knight.setAttackRangeOfUnit(1);
        cuirassier.setAttackRangeOfUnit(1);
    }
    public void cancelUpgradeCloasbleDamage(){
        swordsman.setDamageOfUnit(5);
        spearman.setDamageOfUnit(3);
        axeman.setDamageOfUnit(9);
        knight.setDamageOfUnit(5);
        cuirassier.setDamageOfUnit(2);
    }
    public void cancelUpgradeRidableMovement(){
        knight.setMovementOfUnit(6);
        cuirassier.setMovementOfUnit(5);
        archerHorse.setMovementOfUnit(5);
    }
}
