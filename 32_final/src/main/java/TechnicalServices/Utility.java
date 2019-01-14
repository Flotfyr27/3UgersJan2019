package TechnicalServices;

public class Utility {

    private int getObjectIndex(Object obj, Object[] objArray){
        for(int n = 0; n < objArray.length; n++){
            if(objArray[n].equals(obj)){
                return n;
            }
        }
        throw new IllegalArgumentException("The object was not found in the array");
    }
}
