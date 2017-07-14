/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilities;

import MyMathsLibrary.Vector3;

/**
 *
 * @author DI
 */
public class MyUtility
{
   public Vector3 ReturnVector(String text)
   {
      String[] coordinates = text.split(",");
      
      Vector3 vector = new Vector3(Float.parseFloat(coordinates[0]),Float.parseFloat(coordinates[1]),Float.parseFloat(coordinates[2]));
      
      return vector;
   }
   
   public float ReturnFloat(String text)
   {
      return Float.parseFloat(text);
   }
   
}
