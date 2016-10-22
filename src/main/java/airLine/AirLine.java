/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package airLine;

import java.util.Arrays;

/**
 *
 * @author lenovo
 */
public class AirLine {

    private String name;
    private AirPlane[] arrayOfPlanes;
    private int countOfPlanes;

    public AirLine(String name) {
        this.name = name;
        System.out.println("Авиалиния " + name + " создана");
    }

    /*
    Метод добавляет самолет в массив.
     */
    public void addPlane(AirPlane plane) {
        if (plane instanceof AirPlane) {
            checkArray(countOfPlanes);
            arrayOfPlanes[countOfPlanes++] = plane;
        } else {
            throw new ClassCastException();
        }
    }
    /*
    Метод удаляет определенный самолет
    */
    public AirPlane extractPlane(AirPlane plane) {
        while (true) {
            if (plane instanceof AirPlane) {
                boolean isFound = false;
                int indexOfPlane = 0;
                for (int i = 0; i < countOfPlanes; i++) {
                    if (plane.equals(arrayOfPlanes[i])) {
                        isFound = true;
                        indexOfPlane = i;
                    }
                }
                if (isFound) {
                    AirPlane[] temp = new AirPlane[arrayOfPlanes.length - 1];
                    System.arraycopy(arrayOfPlanes, 0, temp, 0, indexOfPlane);
                    System.arraycopy(arrayOfPlanes, indexOfPlane + 1, temp, indexOfPlane, arrayOfPlanes.length - indexOfPlane);
                    arrayOfPlanes = temp;
                    return plane;
                }
                else if(!isFound){
                    throw new IllegalArgumentException("Данный самолет не найден");
                }
            } else {
                throw new ClassCastException();
            }
        }
    }

    /*
    Метод проверяет массив и расширяет его если требуется
     */
    public void checkArray(int checkedNum) {
        if (arrayOfPlanes.length <= checkedNum) {
            Arrays.copyOf(arrayOfPlanes, (3 * arrayOfPlanes.length / 2) + 1);
        }
    }
    
    public void showAllPlanes(){
        for(int i = 0; i < countOfPlanes; i ++){
            System.out.println((i+1) + "." + arrayOfPlanes[i]);
        }
    }

    public int getCountOfPlanes() {
        return countOfPlanes;
    }

    public void setCountOfPlanes(int countOfPlanes) {
        this.countOfPlanes = countOfPlanes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AirPlane[] getArrayOfPlanes() {
        return arrayOfPlanes;
    }

    public void setArrayOfPlanes(AirPlane[] arrayOfPlanes) {
        this.arrayOfPlanes = arrayOfPlanes;
    }

}
