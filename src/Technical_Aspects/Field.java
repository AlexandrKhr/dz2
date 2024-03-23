package Technical_Aspects;

import Main_Aspects.Player;

public class Field {

    private int amountOfColumnsX;
    private int amountOfRowsY;
    private Player player;
    private IFieldArray[][] field;
    public Field(int sizeX, int sizeY){
        this.amountOfColumnsX = sizeX;
        this.amountOfRowsY = sizeY;
        field = new IFieldArray[amountOfColumnsX][sizeY];
    }

    public void setFieldArray(int x, int y, IFieldArray battleField){
        field[x][y] = battleField;
    }

    public IFieldArray getFieldArray(int x, int y){
        return field[x][y];
    }

    public void showField(){
        System.out.println();
        for (int i = 0; i < amountOfColumnsX; i++){
            System.out.println();
            for (int j = 0; j < amountOfRowsY; j++){
                System.out.print(field[i][j].getSymbol());
            }
        }
        System.out.println();
    }

}
