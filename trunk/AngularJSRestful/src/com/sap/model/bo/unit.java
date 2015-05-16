package com.sap.model.bo;

import java.io.ObjectInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;

public class unit {
    private static unit instance;

   public unit() {
        if(unit.instance != null) {
            throw new RuntimeException(
                    this.getClass().getName()
                            + "is designed to be a Singleton, the instance already exist:"
                            + unit.instance);
        }
       unit.instance = this;
   }

   public static unit getInstance() {
       return instance;
   }
   public static Object createObjectByByteArray(byte[] theDataBytes)throws Exception{
     ByteArrayInputStream theBStream = new ByteArrayInputStream(theDataBytes);
     ObjectInputStream theOStream = new ObjectInputStream(theBStream);
     return theOStream.readObject();
   }
   public static byte[] createObjectByteArray(Object theData)throws Exception{
     ByteArrayOutputStream theBStream = new ByteArrayOutputStream();
     ObjectOutputStream theOStream = new ObjectOutputStream(theBStream);
     theOStream.writeObject(theData);
   return theBStream.toByteArray();
 }

}
