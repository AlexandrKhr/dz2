package Main_Aspects;

import Design.DeadUnit;
import Design.PlainSquare;
import Penalties.Obstacle;
import Technical_Aspects.Field;
import Technical_Aspects.SettingsMenu;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.SortedMap;

public class Game {

    private Object[][] array;
    private int amountOfColumnsX;
    private int amountOfRowsY;
    private int amountOfTrees;
    private int amountOfSwamps;
    private int amountOfHills;
    private int amountOfPlayers;
    private int amountOfEnemies;
    private int amountOfGold;
    private int commandInput;
    private int penaltyForObstaclePlayer;
    private int penaltyForObstacleEnemy;
    private int goldForDefeating = 200;
    private int meaningBefore;
    private int meaningAfter;
    private int timerOnUpgradesOfShootableDamage;
    private int timerOnUpgradesOfShootableRange;
    private int timerOnUpgradesOfClosableDamage;
    private int timerOnUpgradesOfClosableRange;
    private int timerOnUpgradeOfRidableMovement;

    private Field field;
    private boolean isGameEnd = false;
    private boolean isCycleStarted = false;
    private boolean isStart = false;
    private boolean isHealthPointOfPlayerOver = true;
    private boolean isHealthPointOfEnemyOver = true;
    private boolean isAnyUpgrades = false;
    private String typeOfObstacle;
    private String typeOfUnit;
    private String typeOfObstacleForDrawing;
    private Random random = new Random();
    private Player player;
    private Enemy enemy;
    private Scanner scanner = new Scanner(System.in);
    private ArrayList<Enemy> enemyArrayList = new ArrayList<Enemy>();
    private ArrayList<Player> playerArrayList = new ArrayList<Player>();
    private Artisan artisan;
    public Game(){
        this.amountOfColumnsX = SettingsMenu.getSizeX();
        this.amountOfRowsY = SettingsMenu.getSizeY();
        this.amountOfTrees = SettingsMenu.getAmountOfTrees();
        this.amountOfHills = SettingsMenu.getAmountOfHills();
        this.amountOfSwamps = SettingsMenu.getAmountOfSwamps();
        this.amountOfPlayers = SettingsMenu.getAmountOfPlayers();
        this.amountOfEnemies = SettingsMenu.getAmountOfEnemies();
        this.amountOfGold = SettingsMenu.getAmountOfGold();
        field = new Field(amountOfColumnsX, amountOfRowsY);
    }

    public void fillFieldWithPlainSquare(){
        for (int i = 0; i < amountOfColumnsX; i++)
            for (int j = 0; j < amountOfRowsY; j++)
                field.setFieldArray(i, j, new PlainSquare());
    }

    public Field getFieldFromGame(){
        return this.field;
    }

    public void startGame(){
        System.out.println("Welcome to the battlefields of Bauman! \n\n" +
                "You are the king of some settlement , let's call it the Village of IU. But unfortunately, " +
                "your village is being attacked by wild barbarians right now.\nThere is no time to think, " +
                "it's time to hire units and join the battle to protect the IU settlement. Let the great " +
                "battlefields of Bauman begin!\n");
        System.out.println("Please, select units from this list: \n" +
                "№  Name           HP    Damage   Range   Armor   Move    Cost \n" +
                "1. Swordsman      50    5        1       8       3       10   \n" +
                "2. Spearman       35    3        1       4       6       15   \n" +
                "3. Axeman         45    9        1       3       4       20   \n" +
                "4. ArcherLong     30    6        5       8       2       15   \n" +
                "5. ArcherShort    25    3        3       4       4       19   \n" +
                "6. Crossbowman    40    7        6       3       2       23   \n" +
                "7. Knight         30    5        1       3       6       20   \n" +
                "8. Cuirassier     50    2        1       7       5       23   \n" +
                "9. ArcherHorse    25    3        3       2       5       25   \n" + "\n");
        System.out.println("For your information, such units don't like special places in nature: \n" +
                "Type of unit      Obstacle       Penalties for movement \n" +
                "Foots             Swamp          2 \n" +
                "Foots             Hill           3 \n" +
                "Foots             Tree           1 \n" +
                "Archers           Swamp          1 \n" +
                "Archers           Hill           2 \n" +
                "Archers           Tree           3 \n" +
                "Riders            Swamp          3 \n" +
                "Riders            Hill           1 \n" +
                "Riders            Tree           2 \n" );
        arrangePlayer();
        arrangeEnemy();
        arrangeObstacles();
        arrangeArtisan();
        while(!isGameEnd){

            field.showField();
            //showCharacteristics();
            playerTurn();
            checkIfGameFinished();
            enemyTurn();
            checkIfGameFinished();
            artisanTurn();
            if (isAnyUpgrades)
                checkWhenUpgradeLeft();

        }

    }
    private void showCharacteristics(){

        System.out.println("Currently characteristics is: \n" +
                " №   Name           HP    Damage   Range   Armor   Move ");
        for (int i = 0; i < amountOfPlayers; i++)
            System.out.println(playerArrayList.get(i).getSymbol() + "  " + playerArrayList.get(i).getNameOfUnit() +
                    "       " + playerArrayList.get(i).getHealthPointOfUnit() + "    " + playerArrayList.get(i).getDamageOfUnit() +
                    "        " + playerArrayList.get(i).getAttackRangeOfUnit() + "       " + playerArrayList.get(i).getProtectionOfUnit() +
                    "       " + playerArrayList.get(i).getMovementOfUnit());
        System.out.println("---------------------------------------------------------------");
        for (int i = 0; i < amountOfEnemies; i ++)
            System.out.println(enemyArrayList.get(i).getSymbol() + "  " + enemyArrayList.get(i).getNameOfUnit() +
                    "       " + enemyArrayList.get(i).getHealthPointOfUnit() + "    " + enemyArrayList.get(i).getDamageOfUnit() +
                    "        " + enemyArrayList.get(i).getAttackRangeOfUnit() + "       " + enemyArrayList.get(i).getProtectionOfUnit() +
                    "       " + enemyArrayList.get(i).getMovementOfUnit());
        System.out.println("Amount of gold: " + goldForDefeating);

    }
    private void playerTurn(){

        for(int i = 0; i < amountOfPlayers;){
            char j = (char) ((char) i + 97);
            System.out.println("Makes a turn unit called " + j + " " + playerArrayList.get(i).getNameOfUnit());
            System.out.println("So what are you going to do? \n" +
                    "1. I want to make a move for them! \n" +
                    "2. I want to fight for them! \n" +
                    "3. I want to buy an upgrade from a artisan! \n" +
                    "4. I want to skip this turn! \n" +
                    "5. I want to end this game!  \n");
            playerTurnRealisation(i);
            i++;
        }

    }
    private void playerTurnRealisation(int i){

        Scanner scanner1 = new Scanner(System.in);
        String commandInput = scanner1.nextLine();
        penaltyForObstaclePlayer = 0;
        switch (commandInput) {

            case "1":
                for (int j = 0; j < (player.getMovementOfUnit() - penaltyForObstaclePlayer); j++){
                    System.out.println("Choose the right direction!");
                    player = playerArrayList.get(i);
                    System.out.println("You have " + (player.getMovementOfUnit() - j - penaltyForObstaclePlayer) + " moves left for this unit!");
                    String commandToMove = scanner.nextLine();
                    typeOfObstacle = player.penaltyForObstacle(commandToMove);
                    if (!typeOfObstacle.equals("null")) {
                        typeOfUnit = playerArrayList.get(i).getTypeOfUnit();
                        penaltyForObstaclePlayer += typeOfPenalty(typeOfObstacle, typeOfUnit);
                        System.out.println("There is an obstacle ahead, the penalty for your unit is " +
                                penaltyForObstaclePlayer + ".");
                        playerArrayList.get(i).setIsStepOnObstacleTrue();
                    }
                    if (j <= (player.getMovementOfUnit() - penaltyForObstaclePlayer)) {
                        player.moveTo(commandToMove);
                        field.showField();
                    }
                    else
                        System.out.println("Your unit was tired and couldn't get through the obstacle...");
                }
                playerArrayList.get(i).setIsStepOnObstacleFalse();
                break;

            case "2":
                System.out.println("Which enemy do you want to beat? \n");
                dealDamagePlayer(i);
                break;

            case "3":
                buyAnUpgrades(i);
                break;

            case "4":
                System.out.println("Maybe this is a wise decision...\n" );
                break;

            case "5":
                System.out.println("Well, see the next time!");
                System.exit(0);

            default:
                System.out.println("I'm sorry, but i didn't understand you");
                playerTurnRealisation(i);
                break;
        }

    }
    private void enemyTurn(){

        enemyMove();

    }
    private void enemyMove(){

        int columnsYIndex = 0;
        int rowXIndex = 0;
        int newColumnsYIndex = 0;
        int newRowXIndex = 0;
        int j = 0;
        for(Enemy enemy : enemyArrayList){

            isCycleStarted = false;
            penaltyForObstacleEnemy = 0;
            rowXIndex = enemy.getAmountOfRowsXIndex();
            columnsYIndex = enemy.getAmountOfColumnsYIndex();

            newColumnsYIndex = columnsYIndex;
            newRowXIndex = rowXIndex + 1;

            for (int i = 0; i < amountOfPlayers; i++){

                int coordinateXOfEnemy = enemyArrayList.get(j).getAmountOfColumnsYIndex();
                int coordinateYOfEnemy = enemyArrayList.get(j).getAmountOfRowsXIndex();
                int coordinateXOfPlayer = playerArrayList.get(i).getSizeYIndex();
                int coordinateYOfPlayer = playerArrayList.get(i).getSizeXIndex();
                int differenceXPlayerAndEnemy = Math.abs(coordinateXOfEnemy - coordinateXOfPlayer);
                int differenceYPlayerAndEnemy = Math.abs(coordinateYOfEnemy - coordinateYOfPlayer);
                int rangeOfAttackEnemy = enemyArrayList.get(j).getAttackRangeOfUnit();
                if (((differenceXPlayerAndEnemy + differenceYPlayerAndEnemy) <= rangeOfAttackEnemy) && !(isCycleStarted)){
                    char nameOfUnitPlayer = (char) ((char) i + 97);
                    int healthPointOfPlayer = playerArrayList.get(i).getHealthPointOfUnit();
                    int armorOfPlayer = playerArrayList.get(i).getProtectionOfUnit();
                    int damageOfEnemy = enemyArrayList.get(j).getDamageOfUnit();
                    if ((armorOfPlayer > 0) && (armorOfPlayer > damageOfEnemy)){
                        playerArrayList.get(i).setProtection(armorOfPlayer - damageOfEnemy);
                        isCycleStarted = true;
                        System.out.println("Enemy unit number " + (j + 1) + " attack your unit whose name is " +
                                nameOfUnitPlayer + " and caused damage to his armor. Now the amount of armor" +
                                "of your unit is equal to " + (armorOfPlayer - damageOfEnemy));
                    }
                    else if ((armorOfPlayer > 0) && (armorOfPlayer < damageOfEnemy)){
                        int differenceArmorAndDamage = damageOfEnemy - armorOfPlayer;
                        playerArrayList.get(i).setProtection(0);
                        playerArrayList.get(i).setHealthPoint(healthPointOfPlayer - differenceArmorAndDamage);
                        isCycleStarted = true;
                        System.out.println("Unfortunately, the enemy unit number " + (j + 1) + " attacked your unit " +
                                "whose name is " + nameOfUnitPlayer + " and broke all his armor. " +
                                "He also dealt " + differenceArmorAndDamage + " damage to him. \n Now the " +
                                "health point of this unit is equal to " + (healthPointOfPlayer - differenceArmorAndDamage));
                    }
                    else if (armorOfPlayer > 0){
                        playerArrayList.get(i).setProtection(0);
                        isCycleStarted = true;
                        System.out.println("Unfortunately, the enemy unit number " + (j + 1)+ " attacked your unit " +
                                "whose name is " + nameOfUnitPlayer + " and broke all his armor. \n" +
                                "Fortunately, he didn't reach health point and the unit still feels good. ");
                    }
                    else {
                        playerArrayList.get(i).setHealthPoint(healthPointOfPlayer - damageOfEnemy);
                        isCycleStarted = true;
                        System.out.println("The enemy unit number " + (j + 1) + " attacked your unit " +
                                "whoose name is " + nameOfUnitPlayer + " and dealt him " + damageOfEnemy + " damage.");
                        if (playerArrayList.get(i).getHealthPointOfUnit() <= 0){
                            coordinateYOfPlayer = playerArrayList.get(i).getSizeYIndex();
                            coordinateXOfPlayer = playerArrayList.get(i).getSizeXIndex();
                            field.setFieldArray(coordinateXOfPlayer, coordinateYOfPlayer, new DeadUnit());
                            playerArrayList.remove(i);
                            this.amountOfPlayers = amountOfPlayers - 1;
                            SettingsMenu.setAmountOfPlayers(this.amountOfPlayers);
                            System.out.println("Unfortunately, the enemy unit defeated your whose name was" +
                                     nameOfUnitPlayer + ". My condolences...");
                            i--;
                        }

                    }
                }

            }
            for (int i = 0; i < (enemyArrayList.get(j).getMovementOfUnit() - penaltyForObstacleEnemy); i++) {
                if ((newRowXIndex >= 0) && (newColumnsYIndex >= 0) && (newColumnsYIndex < SettingsMenu.getSizeY())
                        && (newRowXIndex < SettingsMenu.getSizeX()) && !((field.getFieldArray(newRowXIndex, newColumnsYIndex)) instanceof Player) &&
                        !((field.getFieldArray(newRowXIndex, newColumnsYIndex)) instanceof Enemy) && !(isCycleStarted)
                        && !((field.getFieldArray(newRowXIndex,newColumnsYIndex)) instanceof Artisan)) {

                    if (field.getFieldArray(newRowXIndex, newColumnsYIndex) instanceof PlainSquare) {
                        relocateEnemy(newRowXIndex, newColumnsYIndex, rowXIndex, columnsYIndex, j);
                    } else if (field.getFieldArray(newRowXIndex, newColumnsYIndex) instanceof Obstacle) {
                        typeOfUnit = enemyArrayList.get(j).getClassOfUnit();
                        typeOfObstacle = field.getFieldArray(newRowXIndex, newColumnsYIndex).getSymbol();
                        penaltyForObstacleEnemy += typeOfPenalty(typeOfObstacle, typeOfUnit);
                        if (i <= (enemyArrayList.get(j).getMovementOfUnit() - penaltyForObstacleEnemy)){
                            relocateEnemy(newRowXIndex, newColumnsYIndex, rowXIndex, columnsYIndex, j);
                            enemyArrayList.get(j).typeOfObstacle(typeOfObstacle);
                            enemyArrayList.get(j).setIsStepOnObstacleTrue();
                        }
                    }
                }
                else
                    break;
                newRowXIndex++;
                rowXIndex++;
            }
            j++;
        }

    }

    private void relocateEnemy(int newSizeXIndex, int newSizeYIndex, int sizeXIndex, int sizeYIndex, int j){
        field.setFieldArray(newSizeXIndex, newSizeYIndex, enemyArrayList.get(j));
        if (enemyArrayList.get(j).isStepOnObstacle()){
            field.setFieldArray(sizeXIndex, sizeYIndex, new Obstacle(enemyArrayList.get(j).getTypeOfObstacleForDrawing()));
            enemyArrayList.get(j).setIsStepOnObstacleFalse();
        }
        else
            field.setFieldArray(sizeXIndex, sizeYIndex, new PlainSquare());
        enemyArrayList.get(j).setAmountOfRowsXIndex(newSizeXIndex);
        enemyArrayList.get(j).setAmountOfColumnsYIndex(newSizeYIndex);
    }


    private void checkIfGameFinished(){
        if (enemyArrayList.isEmpty()) {
            System.out.println("You defeat all the enemies! Now the village of IU isn't danger. Thank you great king!" +
                    " See you later!");
            isGameEnd = true;
        }
        if (playerArrayList.isEmpty()) {
            System.out.println("Oh no, all your units are defeated. Now the dark times of enslavement have come for the village IU." +
                    "\n Goodbye, worst king of our village.");
            isGameEnd = true;
        }


    }
    private void arrangePlayer(){
        int j = 0;
        for(int i = amountOfPlayers - playerArrayList.size(); i > 0;){

            int playerPositionX = amountOfColumnsX - 1;
            int playerPositionY = random.nextInt(amountOfRowsY);
            if(field.getFieldArray(playerPositionX, playerPositionY) instanceof  PlainSquare) {
                System.out.println("You have " + amountOfGold + " gold right now! \n\n" +
                        "Your choice for unit № " + (j + 1) + " is... ");
                scanner = new Scanner(System.in);
                String typeOfPlayer = scanner.nextLine();
                player = new Player(playerPositionX, playerPositionY, this, j, typeOfPlayer);
                if(player.isRunOutOfMoney()) {
                    i = 0;
                    this.amountOfPlayers--;
                    field.setFieldArray(playerPositionX, playerPositionY, new PlainSquare());
                }
                else {
                    playerArrayList.add(player);
                    amountOfGold -= playerArrayList.get(j).getPriceOfUnit();
                    i--;
                    j++;
                }
            }
        }
    }
    private void arrangeEnemy(){
        int j = 1;
        for (int i = amountOfEnemies - enemyArrayList.size(); i > 0; ){
            int enemyPositionX = 0;
            int enemyPositionY = random.nextInt(amountOfRowsY);

            if(field.getFieldArray(enemyPositionX, enemyPositionY) instanceof PlainSquare) {
                int typeOfEnemyUnit = random.nextInt(9) + 1;
                enemy = new Enemy(enemyPositionX, enemyPositionY, j, typeOfEnemyUnit);
                field.setFieldArray(enemyPositionX, enemyPositionY, enemy);
                enemyArrayList.add(enemy);
                i--;
                j++;
            }

        }

    }
    private void arrangeObstacles(){
        String caseOfObstacle = "Tree";
        arrangeOneOfTheObstacles(amountOfTrees, caseOfObstacle);
        caseOfObstacle = "Hill";
        arrangeOneOfTheObstacles(amountOfHills, caseOfObstacle);
        caseOfObstacle = "Swamp";
        arrangeOneOfTheObstacles(amountOfSwamps, caseOfObstacle);
    }
    private void arrangeOneOfTheObstacles(int amountOfObstacles, String caseOfObstacle){
        String caseOfObstacles = caseOfObstacle;
        for(int i = amountOfObstacles; i > 0;){

            int obstaclePositionY = random.nextInt(amountOfRowsY);
            int obstaclePositionX = random.nextInt(amountOfColumnsX);

            if (field.getFieldArray(obstaclePositionX, obstaclePositionY) instanceof PlainSquare){
                field.setFieldArray(obstaclePositionX, obstaclePositionY, new Obstacle(caseOfObstacles));
                i--;
            }

        }

    }
    private void dealDamagePlayer(int i){
        this.commandInput = scanner.nextInt();
        if ((commandInput > (enemyArrayList.size())) || (commandInput <= 0)) {
            System.out.println("There is no such enemy, make sure you choose him! ");
            commandInput++;
            dealDamagePlayer(i);
        }
        int indexOfUnits = commandInput - 1;
        int coordinateXOfEnemy = enemyArrayList.get(indexOfUnits).getAmountOfColumnsYIndex();
        int coordinateYOfEnemy = enemyArrayList.get(indexOfUnits).getAmountOfRowsXIndex();
        int coordinateXOfPlayer = playerArrayList.get(i).getSizeYIndex();
        int coordinateYOfPlayer = playerArrayList.get(i).getSizeXIndex();
        int differenceXPlayerAndEnemy = Math.abs(coordinateXOfEnemy - coordinateXOfPlayer);
        int differenceYPlayerAndEnemy = Math.abs(coordinateYOfEnemy - coordinateYOfPlayer);
        int rangeOfAttackPlayer = playerArrayList.get(i).getAttackRangeOfUnit();
        if ((differenceXPlayerAndEnemy + differenceYPlayerAndEnemy) <= rangeOfAttackPlayer){
            int healthPointOfEnemy = enemyArrayList.get(indexOfUnits).getHealthPointOfUnit();
            int armorOfEnemy = enemyArrayList.get(indexOfUnits).getProtectionOfUnit();
            int damageOfPlayer = playerArrayList.get(i).getDamageOfUnit();
            if ((armorOfEnemy > 0) && (armorOfEnemy > damageOfPlayer)){
                enemyArrayList.get(indexOfUnits).setProtection(armorOfEnemy - damageOfPlayer);
                System.out.println("The enemy unit number " + (indexOfUnits + 1) + " has armor! " +
                        "Your unit hit him and the amount of armor became " +
                        "equal to " + enemyArrayList.get(indexOfUnits).getProtectionOfUnit());
            }
            else if ((armorOfEnemy > 0) && (armorOfEnemy < damageOfPlayer)){
                int differenceArmorAndDamage = damageOfPlayer - armorOfEnemy;
                enemyArrayList.get(indexOfUnits).setProtection(0);
                enemyArrayList.get(indexOfUnits).setHealthPoint(healthPointOfEnemy - differenceArmorAndDamage);
                System.out.println("With a strong blow your unit broke the armor of enemy unit number " +
                        (indexOfUnits + 1) + ". After that, your unit dealt " + differenceArmorAndDamage + " damage to him.");
            }
            else if (armorOfEnemy > 0){
                enemyArrayList.get(indexOfUnits).setProtection(0);
                System.out.println("With a strong blow your unit broke the armor of enemy unit number " +
                        (indexOfUnits + 1) + ". But he was tired so he did no damage.");
            }
            else {
                enemyArrayList.get(indexOfUnits).setHealthPoint(healthPointOfEnemy - damageOfPlayer);
                System.out.println("Your unit deal " + damageOfPlayer + " damage to an enemy unit number " + (indexOfUnits + 1));
            }
                if (enemyArrayList.get(indexOfUnits).getHealthPointOfUnit() <= 0){
                    coordinateYOfEnemy = enemyArrayList.get(indexOfUnits).getAmountOfColumnsYIndex();
                    coordinateXOfEnemy = enemyArrayList.get(indexOfUnits).getAmountOfRowsXIndex();
                    field.setFieldArray(coordinateXOfEnemy, coordinateYOfEnemy, new DeadUnit());
                    switch (enemyArrayList.get(indexOfUnits).getClassOfUnit()){

                        case "foots":
                            goldForDefeating += SettingsMenu.getRewardForDefeatingFoots();
                            break;

                        case "archers":
                            goldForDefeating += SettingsMenu.getRewardForDefeatingArchers();
                            break;

                        case "riders":
                            goldForDefeating += SettingsMenu.getRewardForDefeatingRiders();
                            break;

                        default:
                            System.out.println("Something get wrong with gold for defeating. Please, restart the game.");
                            break;
                    }
                    enemyArrayList.remove(indexOfUnits);
                    SettingsMenu.setAmountOfEnemies(amountOfEnemies - 1);
                    System.out.println("You defeated an enemy unit number" + (indexOfUnits + 1) + ". Congratulations!");
            }
        }
        else
            System.out.println("Unfortunately, you miscalculated and your unit didn't reach the enemy with it's attack!");
    }

    private int typeOfPenalty(String typeOfObstacle, String typeOfUnit){
        int penaltyForObstacle = 0;
        switch (typeOfUnit){

            case "foots":
                switch (typeOfObstacle){

                    case " ! ":
                        penaltyForObstacle = 1;
                        break;

                    case " @ ":
                        penaltyForObstacle = 3;
                        break;

                    case " # ":
                        penaltyForObstacle = 2;
                        break;

                    default:
                        System.out.println("Something went wrong with penalty for obstacles, please restart the game! ");
                        break;
                }
                break;

            case "archers":
                switch (typeOfObstacle){

                    case " ! ":
                        penaltyForObstacle = 3;
                        break;

                    case " @ ":
                        penaltyForObstacle = 2;
                        break;

                    case " # ":
                        penaltyForObstacle = 1;
                        break;

                    default:
                        System.out.println("Something went wrong with penalty for obstacles, please restart the game! ");
                        break;
                }
                break;

            case "riders":
                switch (typeOfObstacle){

                    case " ! ":
                        penaltyForObstacle = 2;
                        break;

                    case " @ ":
                        penaltyForObstacle = 1;
                        break;

                    case " # ":
                        penaltyForObstacle = 3;
                        break;

                    default:
                        System.out.println("Something went wrong with penalty for obstacles, please restart the game! ");
                        break;
                }
                break;

            default:
                System.out.println("Something went wrong with penalty for obstacles, please restart the game! ");
                break;

        }
        return penaltyForObstacle;
    }

    private void arrangeArtisan(){
        int artisanPositionX = random.nextInt(amountOfColumnsX);
        int artisanPositionY = random.nextInt(amountOfColumnsX);
        if (field.getFieldArray(artisanPositionX,artisanPositionY) instanceof PlainSquare){
            artisan = new Artisan(artisanPositionX, artisanPositionY);
            field.setFieldArray(artisanPositionX,artisanPositionY, artisan);
        }
        else
            arrangeArtisan();
    }

    private void artisanTurn(){
        int rowXIndex = artisan.getCoordinateX();
        int columnYIndex = artisan.getCoordinateY();
        int deltaX = random.nextInt(3) - 1;
        int deltaY = random.nextInt(3) - 1;
        int newRowXIndex = rowXIndex + deltaX;
        int newColumnsYIndex = columnYIndex + deltaY;
        if ((newRowXIndex >= 0) && (newColumnsYIndex >= 0) && (newColumnsYIndex < SettingsMenu.getSizeY())
                && (newRowXIndex < SettingsMenu.getSizeX()) && !((field.getFieldArray(newRowXIndex, newColumnsYIndex)) instanceof Player) &&
                !((field.getFieldArray(newRowXIndex, newColumnsYIndex)) instanceof Enemy) &&
                !((field.getFieldArray(newRowXIndex, newColumnsYIndex)) instanceof Obstacle)){
            field.setFieldArray(newRowXIndex, newColumnsYIndex, artisan);
            if (deltaX != 0 || deltaY != 0)
                field.setFieldArray(rowXIndex, columnYIndex, new PlainSquare());
            artisan.setCoordinateX(newRowXIndex);
            artisan.setCoordinateY(newColumnsYIndex);
        }
        else artisanTurn();
    }

    private void buyAnUpgrades(int i){

        System.out.println(goldForDefeating);

        int coordinatePlayerX = playerArrayList.get(i).getSizeXIndex();
        int coordinatePlayerY = playerArrayList.get(i).getSizeYIndex();
        int coordinateArtisanX = artisan.getCoordinateX();
        int coordinateArtisanY = artisan.getCoordinateY();
        int differenceXPlayerAndArtisan = Math.abs(coordinatePlayerX - coordinateArtisanX);
        int differenceYPlayerAndArtisan = Math.abs(coordinatePlayerY - coordinateArtisanY);

        if ((differenceXPlayerAndArtisan + differenceYPlayerAndArtisan) >= 0 && ((coordinatePlayerX != coordinateArtisanX) ||
                coordinatePlayerY != coordinateArtisanY)){
            isAnyUpgrades = true;
            System.out.println("What do you want to buy from me? \n" +
                    "This is a list of what i have: \n" +
                    "1. Archer upgrade: plus attack range by 5 for 6 turns. 15 gold \n" +
                    "2. Archer upgrade: plus damage by 6 for 5 turns. 10 gold \n" +
                    "3. Close attacker upgrade: plus attack range by 2 for 5 turns. 20 gold \n" +
                    "4. Close attacker upgrade: plus damage by 10 for 4 turns. 17 gold \n" +
                    "5. Riders upgrade: plus movement by 6 for 7 turns. 8 gold  \n" +
                    "6. Nothing \n");
            commandInput = scanner.nextInt();

            switch (commandInput){

                case 1:
                    if (goldForDefeating >= 15) {
                        for (int j = 0; j < amountOfPlayers; j++) {
                            meaningBefore = playerArrayList.get(j).getAttackRangeOfUnit();
                            playerArrayList.get(j).upgradeShootableRange();
                            meaningAfter = playerArrayList.get(j).getAttackRangeOfUnit();
                            if (meaningBefore != meaningAfter) {
                                char symbol = (char) (j + 65);
                                playerArrayList.get(j).setPlayerSymbol(symbol);
                            }
                        }
                        goldForDefeating -= 15;
                        timerOnUpgradesOfShootableRange = 6;
                    }
                    else
                        System.out.println("Unfortunately, you don't have that much gold ");
                    break;

                case 2:
                    if (goldForDefeating >= 10) {
                        for (int j = 0; j < amountOfPlayers; j++) {
                            meaningBefore = playerArrayList.get(j).getDamageOfUnit();
                            playerArrayList.get(j).upgradeShootableDamage();
                            meaningAfter = playerArrayList.get(j).getDamageOfUnit();
                            if (meaningBefore != meaningAfter) {
                                char symbol = (char) (j + 65);
                                playerArrayList.get(j).setPlayerSymbol(symbol);
                            }
                        }
                        timerOnUpgradesOfShootableDamage = 5;
                        goldForDefeating -= 10;
                    }
                    else
                        System.out.println("Unfortunately, you don't have that much gold ");
                    break;

                case 3:
                    if (goldForDefeating >= 20) {
                        for (int j = 0; j < amountOfPlayers; j++) {
                            meaningBefore = playerArrayList.get(j).getAttackRangeOfUnit();
                            playerArrayList.get(j).upgradeClosableRange();
                            meaningAfter = playerArrayList.get(j).getAttackRangeOfUnit();
                            if (meaningBefore != meaningAfter) {
                                System.out.println("  dfsdfss ");
                                char symbol = (char) (j + 65);
                                playerArrayList.get(j).setPlayerSymbol(symbol);
                            }
                        }
                        timerOnUpgradesOfShootableRange = 5;
                        goldForDefeating -= 20;
                    }
                    else
                        System.out.println("Unfortunately, you don't have that much gold ");
                    break;

                case 4:
                    if (goldForDefeating >= 17) {
                        for (int j = 0; j < amountOfPlayers; j++) {
                            meaningBefore = playerArrayList.get(j).getDamageOfUnit();
                            playerArrayList.get(j).upgradeCloasbleDamage();
                            meaningAfter = playerArrayList.get(j).getDamageOfUnit();
                            if (meaningBefore != meaningAfter) {
                                char symbol = (char) (j + 65);
                                playerArrayList.get(j).setPlayerSymbol(symbol);
                            }
                        }
                        timerOnUpgradesOfShootableDamage = 4;
                        goldForDefeating -= 17;
                    }
                    else
                        System.out.println("Unfortunately, you don't have that much gold ");
                    break;

                case 5:
                    if (goldForDefeating >= 8){
                        for (int j = 0; j < amountOfPlayers; j++) {
                            meaningBefore = playerArrayList.get(j).getMovementOfUnit();
                            playerArrayList.get(j).upgradeRidableMovement();
                            meaningAfter = playerArrayList.get(j).getMovementOfUnit();
                            if (meaningBefore != meaningAfter) {
                                char symbol = (char) (j + 65);
                                playerArrayList.get(j).setPlayerSymbol(symbol);
                            }
                        }
                        timerOnUpgradeOfRidableMovement = 7;
                        goldForDefeating -= 8;
                        }
                    else
                        System.out.println("Unfortunately, you don't have that much gold ");
                    break;

                case 6:
                    System.out.println("Well, it's your decision");
                    break;

                default:
                    System.out.println("I'm sorry, but i didn't understand you");
                    buyAnUpgrades(i);
                    break;}
            System.out.println("You have " + goldForDefeating + " gold left!");
            System.out.println("Maybe you want to buy something else? \n" +
                    "1. Yes \n" +
                    "2. No \n");
            commandInput = scanner.nextInt();
            if (commandInput == 1)
                buyAnUpgrades(i);
            else
                System.out.println("It was nice to meet you. Good luck!");
        }
        else
            System.out.println("Unfortunately, you miscalculated and your unit didn't catch up with the " +
                    "artisan and he left!");

    }

    private void checkWhenUpgradeLeft(){
        timerOnUpgradeOfRidableMovement--;
        timerOnUpgradesOfClosableDamage--;
        timerOnUpgradesOfShootableDamage--;
        timerOnUpgradesOfShootableRange--;
        timerOnUpgradesOfClosableRange--;

        if(timerOnUpgradesOfShootableRange < 0){
            for(int j = 0; j < amountOfPlayers; j++){
                meaningBefore = playerArrayList.get(j).getAttackRangeOfUnit();
                playerArrayList.get(j).cancelUpgradeShootableRange();
                meaningAfter = playerArrayList.get(j).getAttackRangeOfUnit();
                if (meaningBefore != meaningAfter)
                    playerArrayList.get(j).setPlayerSymbol((char)(j + 97));
            }
        }
        if (timerOnUpgradesOfShootableDamage < 0){
            for(int j = 0; j < amountOfPlayers; j++){
                meaningBefore = playerArrayList.get(j).getDamageOfUnit();
                playerArrayList.get(j).cancelUpgradeShootableDamage();
                meaningAfter = playerArrayList.get(j).getDamageOfUnit();
                if (meaningBefore != meaningAfter)
                    playerArrayList.get(j).setPlayerSymbol((char)(j + 97));
            }
        }
        if (timerOnUpgradesOfClosableRange < 0){
            for(int j = 0; j < amountOfPlayers; j++){
                meaningBefore = playerArrayList.get(j).getAttackRangeOfUnit();
                playerArrayList.get(j).cancelUpgradeClosableRange();
                meaningAfter = playerArrayList.get(j).getAttackRangeOfUnit();
                if (meaningBefore != meaningAfter)
                    playerArrayList.get(j).setPlayerSymbol((char)(j + 97));
            }
        }
        if (timerOnUpgradesOfClosableDamage < 0){
            for(int j = 0; j < amountOfPlayers; j++){
                meaningBefore = playerArrayList.get(j).getDamageOfUnit();
                playerArrayList.get(j).cancelUpgradeCloasbleDamage();
                meaningAfter = playerArrayList.get(j).getDamageOfUnit();
                if (meaningBefore != meaningAfter)
                    playerArrayList.get(j).setPlayerSymbol((char)(j + 97));
            }
        }
        if (timerOnUpgradeOfRidableMovement < 0){
            for(int j = 0; j < amountOfPlayers; j++){
                meaningBefore = playerArrayList.get(j).getMovementOfUnit();
                playerArrayList.get(j).cancelUpgradeRidableMovement();
                meaningAfter = playerArrayList.get(j).getMovementOfUnit();
                if (meaningBefore != meaningAfter)
                    playerArrayList.get(j).setPlayerSymbol((char)(j + 97));
            }
        }
        if ((timerOnUpgradeOfRidableMovement < 0) && (timerOnUpgradesOfClosableDamage < 0) && (timerOnUpgradesOfClosableRange < 0)
        && (timerOnUpgradesOfShootableDamage < 0) && (timerOnUpgradesOfShootableRange < 0))
            isAnyUpgrades = false;
    }
}
