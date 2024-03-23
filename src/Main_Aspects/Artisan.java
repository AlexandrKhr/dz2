package Main_Aspects;

import Technical_Aspects.IFieldArray;

public class Artisan implements IFieldArray {

    private int coordinateX;
    private int coordinateY;

    public Artisan(int coordinateX, int coordinateY) {
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
    }


    @Override
    public String getSymbol() {
        return " § ";
    }

    public int getCoordinateX() {
        return coordinateX;
    }

    public int getCoordinateY() {
        return coordinateY;
    }

    public void setCoordinateX(int coordinateX) {
        this.coordinateX = coordinateX;
    }

    public void setCoordinateY(int coordinateY) {
        this.coordinateY = coordinateY;
    }
}
