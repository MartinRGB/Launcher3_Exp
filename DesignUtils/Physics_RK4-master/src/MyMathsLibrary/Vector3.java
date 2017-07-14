/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MyMathsLibrary;

import Application.DrawApplication;

/**
 *
 * @author DI
 */
public class Vector3
{
   private float X, Y, Z;

   public float GetX()
   {
      return this.X;
   }

   public void SetX(float X)
   {
      this.X = X;
   }

   public float GetY()
   {
      return this.Y;
   }

   public void SetY(float Y)
   {
      this.Y = Y;
   }

   public float GetZ()
   {
      return this.Z;
   }

   public void SetZ(float Z)
   {
      this.Z = Z;
   }

   //default constructor
   public Vector3()
   {
      this.X = 0;
      this.Y = 0;
      this.Z = 0;
   }

   public Vector3(float X, float Y, float Z)
   {
      this.X = X;
      this.Y = Y;
      this.Z = Z;
   }

   public Vector3 Normalize()
   {
      double length;

      length = Math.pow(this.X, 2) + Math.pow(this.Y, 2) + Math.pow(this.Z, 2);

      length = Math.sqrt(length);

      return new Vector3((float) (this.X / length), (float) (this.Y / length), (float) (this.Z / length));
   }

   public String printVector()
   {
      if (DrawApplication.calculate2D)
      {
         return "X: " + this.X + ", Y: " + this.Y;
      }
      else if (DrawApplication.calculate3D)
      {
         return "X: " + this.X + ", Y: " + this.Y + ", Z: " + this.Z;
      }

      return "X: " + this.X + ", Y: " + this.Y + ", Z: " + this.Z;
   }
}
