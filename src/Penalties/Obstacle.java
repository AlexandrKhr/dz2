package Penalties;

import Technical_Aspects.IFieldArray;

public class Obstacle implements IFieldArray {
    public Obstacle(String variationOfObstacle){
        this.nameOfObstacle = variationOfObstacle;
    }
    private String symbolOfHill;
    private String symbolOfTree;
    private String symbolOfSwamp;
    private String symbolOfObstacle;
    private String nameOfObstacle;
    private String checkTheObstacle(String variationOfObstacle){
        if (nameOfObstacle.equals("Tree"))
            this.symbolOfObstacle = " ! ";
        else if (nameOfObstacle.equals("Hill"))
            this.symbolOfObstacle = " @ ";
        else if (nameOfObstacle.equals("Swamp"))
            this.symbolOfObstacle = " # ";
        else
            System.out.println("Error entering obstacle symbol!!!");
        return symbolOfObstacle;
    }
    @Override
    public String getSymbol() {
        this.symbolOfObstacle = checkTheObstacle(this.nameOfObstacle);
        return symbolOfObstacle;
    }

}
