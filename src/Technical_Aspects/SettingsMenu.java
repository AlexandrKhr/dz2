package Technical_Aspects;

import java.util.Scanner;

public class SettingsMenu {

    private static int sizeX = 8;
    private static int sizeY = 8;
    private static int amountOfTrees = 3;
    private static int amountOfSwamps = 3;
    private static int amountOfHills = 3;
    private static int amountOfPlayers = 3;
    private static int amountOfEnemies = 3;
    private static int amountOfGold = 60;
    private static int rewardForDefeatingFoots = 10;
    private static int rewardForDefeatingArchers = 15;
    private static int rewardForDefeatingRiders = 20;

    public static int getSizeX() {
        return sizeX;
    }

    public static int getSizeY() {
        return sizeY;
    }

    public static int getAmountOfHills() {
        return amountOfHills;
    }

    public static int getAmountOfSwamps() {
        return amountOfSwamps;
    }

    public static int getAmountOfTrees() {
        return amountOfTrees;
    }

    public static int getAmountOfPlayers() {
        return amountOfPlayers;
    }

    public static int getAmountOfEnemies() {
        return amountOfEnemies;
    }

    public static int getAmountOfGold() {
        return amountOfGold;
    }
    public static int getRewardForDefeatingFoots() {
        return rewardForDefeatingFoots;
    }
    public static int getRewardForDefeatingArchers() {
        return rewardForDefeatingArchers;
    }
    public static int getRewardForDefeatingRiders() {
        return rewardForDefeatingRiders;
    }

    public static void setAmountOfGold(int amountOfGold) {
        SettingsMenu.amountOfGold = amountOfGold;
    }
    public static void setAmountOfEnemies(int amountOfEnemies) {
        SettingsMenu.amountOfEnemies = amountOfEnemies;
    }
    public static void setAmountOfPlayers(int amountOfPlayers) {
        SettingsMenu.amountOfPlayers = amountOfPlayers;
    }

    public static int changeSettings(int value){
        Scanner scanner = new Scanner(System.in);

        do{
            value = scanner.nextInt();
            if (value % 1 != 0)
                System.out.println("Please, enter integer, not float");
        }while (value % 1 != 0);
        return value;
    }

    public static void openSettingsMenu(){
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        Scanner scanner = new Scanner(System.in);
        String commandInput;
        do {
            System.out.println("Settings \n" +
                    "1. Show current settings \n" +
                    "2. Change settings \n" +
                    "3. Exit\n");
            commandInput = scanner.nextLine();

            switch (commandInput){
                case "1" :
                    System.out.println("Current settings: \n" +
                            "lines: " + sizeY + "\n" +
                            "columns: " + sizeX + "\n" +
                            "amount of trees: " + amountOfTrees + "\n" +
                            "amount of swamps: " + amountOfSwamps + "\n" +
                            "amount of hills: " + amountOfHills + "\n" +
                            "amount of players: " + amountOfPlayers + "\n" +
                            "amount of enemies: " + amountOfEnemies + "\n" +
                            "amount of gold: " + amountOfGold + "\n" +
                            "reward for defeating foots: " + rewardForDefeatingFoots + "\n" +
                            "reward for defeating archers: " + rewardForDefeatingArchers + "\n" +
                            "reward for defeating riders: " + rewardForDefeatingRiders + "\n");
                    break;
                case "2" :
                    System.out.println("Change settings \n" +
                            "1. lines \n" +
                            "2. columns \n" +
                            "3. amount of trees \n" +
                            "4. amount of swamps \n" +
                            "5. amount of hills \n" +
                            "6. amount of players \n" +
                            "7. amount of enemies \n" +
                            "8. amount of gold \n" +
                            "9. reward for defeating foots \n" +
                            "10. reward for defeating archers \n" +
                            "11. reward for defeating riders \n");
                    commandInput = scanner.nextLine();
                    switch (commandInput) {
                        case "1":
                            System.out.println("Enter a new value for lines: ");
                            int newValue = changeSettings(sizeY);
                            sizeY = newValue;
                            break;

                        case "2":
                            System.out.println("Enter a new value for columns: ");
                            newValue = changeSettings(sizeX);
                            sizeX = newValue;
                            break;

                        case "3":
                            System.out.println("Enter a new number of trees: ");
                            newValue = changeSettings(amountOfTrees);
                            amountOfTrees = newValue;
                            break;

                        case "4":
                            System.out.println("Enter a new number of swamps: ");
                            newValue = changeSettings(amountOfSwamps);
                            amountOfSwamps = newValue;
                            break;

                        case "5":
                            System.out.println("Enter a new number of hills: ");
                            newValue = changeSettings(amountOfHills);
                            amountOfHills = newValue;
                            break;

                        case "6":
                            System.out.println("Enter a new number of players: ");
                            newValue = changeSettings(amountOfPlayers);
                            amountOfPlayers = newValue;
                            break;

                        case "7":
                            System.out.println("Enter a new number of enemies: ");
                            newValue = changeSettings(amountOfEnemies);
                            amountOfEnemies = newValue;
                            break;

                        case "8":
                            System.out.println("Enter a new number of gold: ");
                            newValue = changeSettings(amountOfGold);
                            amountOfGold = newValue;
                            break;

                        case "9":
                            System.out.println("Enter a new number of reward for gold");
                            newValue = changeSettings(rewardForDefeatingFoots);
                            rewardForDefeatingFoots = newValue;
                            break;

                        case "10":
                            System.out.println("Enter a new number of reward for gold");
                            newValue = changeSettings(rewardForDefeatingArchers);
                            rewardForDefeatingArchers = newValue;
                            break;

                        case "11":
                            System.out.println("Enter a new number of reward for gold");
                            newValue = changeSettings(rewardForDefeatingRiders);
                            rewardForDefeatingRiders= newValue;
                            break;

                        default:
                            System.out.println("Error");
                            break;
                    }
                case "3" :
                    break;
                default:
                    System.out.println("Please, choose one of the suggested numbers");
                    break;
            }

        }while(!commandInput.equals("3"));

    }

}
