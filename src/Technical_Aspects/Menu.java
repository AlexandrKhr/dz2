package Technical_Aspects;

import Main_Aspects.Game;

import java.util.Scanner;
public class Menu {

    public static void openMenu(){

        Scanner scanner = new Scanner(System.in);

        String commandInput;

        do {

            System.out.println("Welcome to battlegrounds of Baumanka\n " +
                    "1. Start new game\n " +
                    "2. Options \n " +
                    "3. Exit");

            commandInput = scanner.nextLine();

            switch (commandInput){

                case "1":
                    Game game = new Game();
                    game.fillFieldWithPlainSquare();
                    game.startGame();
                    break;
                case "2":
                    SettingsMenu.openSettingsMenu();
                    break;

                case "3":
                    System.out.println("See you next time<3");
                    break;

                default:
                    System.out.println("Please, choose one of the suggested numbers");

            }

        }while(!commandInput.equals("3"));

    }

}
