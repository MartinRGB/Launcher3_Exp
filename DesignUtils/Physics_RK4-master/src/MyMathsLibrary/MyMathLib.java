/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MyMathsLibrary;

import Application.DrawApplication;

/*@author DI*/
public class MyMathLib
{
   public Vector3 GetVector(Vector3 a, Vector3 b)
   {
      return new Vector3((b.GetX() - a.GetX()), (b.GetY() - a.GetY()), (b.GetZ() - a.GetZ()));
   }

   public Vector3 AddVectors(Vector3 v1, Vector3 v2)
   {
      return new Vector3(v1.GetX() + v2.GetX(), v1.GetY() + v2.GetY(), v1.GetZ() + v2.GetZ());
   }

   public Vector3 SubtractVectors(Vector3 v1, Vector3 v2)
   {
      return new Vector3(v1.GetX() - v2.GetX(), v1.GetY() - v2.GetY(), v1.GetZ() - v2.GetZ());
   }

   public float GetDotProduct(Vector3 a, Vector3 b)
   {
      if (!DrawApplication.calculate2D && !DrawApplication.calculate3D)
      {
         a = a.Normalize();
         b = b.Normalize();
      }

      return (float) ((a.GetX() * b.GetX()) + (a.GetY() * b.GetY()) + (a.GetZ() * b.GetZ()));
   }

   public float GetVectorLenght(Vector3 vector)
   {
      return (float) Math.sqrt((Math.pow(vector.GetX(), 2)) + (Math.pow(vector.GetY(), 2)) + (Math.pow(vector.GetZ(), 2)));
   }

   public Vector3 GetCrossProduct(Vector3 a, Vector3 b)
   {
      Vector3 dotProd = new Vector3((a.GetY() * b.GetZ()) - (a.GetZ() * b.GetY()),
              (a.GetZ() * b.GetX()) - (a.GetX() * b.GetZ()),
              (a.GetX() * b.GetY()) - (a.GetY() * b.GetX()));

      dotProd.Normalize();

      return dotProd;
   }

   public Vector3 MultiplyVectors(Vector3 a, Vector3 b)
   {
      return new Vector3((a.GetX() * b.GetX()), (a.GetY() * b.GetY()), (a.GetZ() * b.GetZ()));
   }

   public float[][] multiplyMatrices(float[][] matrix1, float[][] matrix2)
   {
      float[][] Matrix = new float[matrix1.length][matrix1.length];

      for (int i = 0; i < matrix1.length; i++)
      {
         for (int j = 0; j < matrix1.length; j++)
         {
            for (int k = 0; k < matrix1.length; k++)
            {
               Matrix[i][k] += matrix1[i][j] * matrix2[j][k];
            }
         }
      }

      return Matrix;
   }

   public Vector3 multiplyVector3WithMatrix4by4(Vector3 vector, float[][] matrix)
   {
      Vector3 vector1 = new Vector3();

      vector1.SetX((vector.GetX() * matrix[0][0])
              + (vector.GetY() * matrix[1][0])
              + (vector.GetZ() * matrix[2][0])
              + (matrix[3][0]));

      vector1.SetY((vector.GetX() * matrix[0][1])
              + (vector.GetY() * matrix[1][1])
              + (vector.GetZ() * matrix[2][1])
              + (matrix[3][1]));

      vector1.SetZ((vector.GetX() * matrix[0][2])
              + (vector.GetY() * matrix[1][2])
              + (vector.GetZ() * matrix[2][2])
              + (matrix[3][2]));

      return vector1;
   }

   public void printMatrix(float[][] Matrix)
   {
      for (int i = 0; i < 4; i++)
      {
         for (int j = 0; j < 4; j++)
         {
            System.out.print(Matrix[i][j] + ",");
         }
         System.out.println();
      }
   }

//---------------------------------2D TRANSFORMATIONS---------------------------------------------    
   private float[][] ScalingMatrixT(float scaleX, float scaleY, float cX, float cY)
   {
      float[][] ScalingMatrix = new float[3][3];

      ScalingMatrix[0][0] = scaleX;
      ScalingMatrix[0][1] = 0;
      ScalingMatrix[0][2] = 0;

      ScalingMatrix[1][0] = 0;
      ScalingMatrix[1][1] = scaleY;
      ScalingMatrix[1][2] = 0;

      ScalingMatrix[2][0] = (cX * (1 - scaleX));
      ScalingMatrix[2][1] = (cY * (1 - scaleY));
      ScalingMatrix[2][2] = 1;

      return ScalingMatrix;
   }

   private float[][] ScalingMatrix(float scaleX, float scaleY)
   {
      float[][] ScalingMatrix = new float[3][3];

      ScalingMatrix[0][0] = scaleX;
      ScalingMatrix[0][1] = 0;
      ScalingMatrix[0][2] = 0;

      ScalingMatrix[1][0] = 0;
      ScalingMatrix[1][1] = scaleY;
      ScalingMatrix[1][2] = 0;

      ScalingMatrix[2][0] = 0;
      ScalingMatrix[2][1] = 0;
      ScalingMatrix[2][2] = 1;

      return ScalingMatrix;
   }

   private float[][] Scale(Vector3 vector, float[][] ScalingMatrix)
   {
      float[][] scaledMatrix = new float[1][3];

      scaledMatrix[0][0] = (vector.GetX() * ScalingMatrix[0][0])
              + (vector.GetY() * ScalingMatrix[1][0])
              + (vector.GetZ() * ScalingMatrix[2][0]);

      scaledMatrix[0][1] = (vector.GetX() * ScalingMatrix[0][1])
              + (vector.GetY() * ScalingMatrix[1][1])
              + (vector.GetZ() * ScalingMatrix[2][1]);

      scaledMatrix[0][2] = (vector.GetX() * ScalingMatrix[0][2])
              + (vector.GetY() * ScalingMatrix[1][2])
              + (vector.GetZ() * ScalingMatrix[2][2]);

      return scaledMatrix;
   }

   private float[][] RotationMatrixT(float degree, float cX, float cY)
   {
      float[][] RotationMatrix = new float[3][3];

      RotationMatrix[0][0] = (float) Math.cos(Math.toRadians(degree));
      RotationMatrix[0][1] = (float) Math.sin(Math.toRadians(degree));
      RotationMatrix[0][2] = 0;

      RotationMatrix[1][0] = (float) Math.sin(Math.toRadians(-degree));
      RotationMatrix[1][1] = (float) Math.cos(Math.toRadians(degree));
      RotationMatrix[1][2] = 0;

      RotationMatrix[2][0] = (cX * (1 - (float) Math.cos(Math.toRadians(degree))))
              + (cY * ((float) Math.sin(Math.toRadians(degree))));
      RotationMatrix[2][1] = (cY * (1 - (float) Math.cos(Math.toRadians(degree))))
              - (cX * ((float) Math.sin(Math.toRadians(degree))));
      RotationMatrix[2][2] = 1;

      return RotationMatrix;
   }

   private float[][] RotationMatrix(float degree)
   {
      float[][] RotationMatrix = new float[3][3];

      RotationMatrix[0][0] = (float) Math.cos(Math.toRadians(degree));
      RotationMatrix[0][1] = (float) Math.sin(Math.toRadians(degree));
      RotationMatrix[0][2] = 0;

      RotationMatrix[1][0] = (float) Math.sin(Math.toRadians(-degree));
      RotationMatrix[1][1] = (float) Math.cos(Math.toRadians(degree));
      RotationMatrix[1][2] = 0;

      RotationMatrix[2][0] = 0;
      RotationMatrix[2][1] = 0;
      RotationMatrix[2][2] = 1;

      return RotationMatrix;
   }

   private float[][] Rotate(Vector3 vector, float[][] RotationMatrix)
   {
      float[][] rotatedMatrix = new float[1][3];
      rotatedMatrix[0][0] = (vector.GetX() * RotationMatrix[0][0])
              + (vector.GetY() * RotationMatrix[1][0])
              + (vector.GetZ() * RotationMatrix[2][0]);

      rotatedMatrix[0][1] = (vector.GetX() * RotationMatrix[0][1])
              + (vector.GetY() * RotationMatrix[1][1])
              + (vector.GetZ() * RotationMatrix[2][1]);

      rotatedMatrix[0][2] = (vector.GetX() * RotationMatrix[0][2])
              + (vector.GetY() * RotationMatrix[1][2])
              + (vector.GetZ() * RotationMatrix[2][2]);

      return rotatedMatrix;
   }

   private float[][] ScaleRotateMatrix(float sX, float sY, float degree, float cX, float cY)
   {
      float[][] matrix = new float[3][3];

      matrix[0][0] = sX * (float) Math.cos(Math.toRadians(degree));
      matrix[0][1] = 0;
      matrix[0][2] = 0;

      matrix[1][0] = 0;
      matrix[1][1] = sY * (float) Math.cos(Math.toRadians(degree));
      matrix[1][2] = 0;

      matrix[2][0] = (cX * (1 - sX * (float) Math.cos(Math.toRadians(degree))))
              + (-cY * sY * (float) Math.sin(Math.toRadians(-degree)));

      matrix[2][1] = (-cX * sX * (float) Math.sin(Math.toRadians(degree)))
              + (cY * (1 - sY * (float) Math.cos(Math.toRadians(degree))));

      matrix[2][2] = 1;

      return matrix;
   }

   private float[][] ScaleRotate(Vector3 vector, float[][] matrix)
   {
      float[][] ScaleRotateMatrix = new float[1][3];

      ScaleRotateMatrix[0][0] = (vector.GetX() * matrix[0][0])
              + (vector.GetY() * matrix[1][0])
              + (matrix[2][0]);

      ScaleRotateMatrix[0][1] = (vector.GetX() * matrix[0][1])
              + (vector.GetY() * matrix[1][1])
              + (matrix[2][1]);

      ScaleRotateMatrix[0][2] = (vector.GetX() * matrix[0][2])
              + (vector.GetY() * matrix[1][2])
              + (matrix[2][2]);

      return ScaleRotateMatrix;
   }

   public Vector3 Transform(Vector3 vector, float degree, float cX, float cY)
   {
      float[][] RotationMatrix = Rotate(vector, RotationMatrixT(degree, cX, cY));

      return new Vector3(RotationMatrix[0][0], RotationMatrix[0][1], RotationMatrix[0][2]);
   }

   public Vector3 Transform(Vector3 vector, float degree)
   {
      float[][] RotationMatrix = Rotate(vector, RotationMatrix(degree));

      return new Vector3(RotationMatrix[0][0], RotationMatrix[0][1], RotationMatrix[0][2]);
   }

   public Vector3 Transform(Vector3 vector, float scaleX, float scaleY, float cX, float cY)
   {
      float[][] ScalingMatrix = Scale(vector, ScalingMatrixT(scaleX, scaleY, cX, cY));

      return new Vector3(ScalingMatrix[0][0], ScalingMatrix[0][1], ScalingMatrix[0][2]);
   }

   public Vector3 Transform(Vector3 vector, float scaleX, float scaleY)
   {
      float[][] ScalingMatrix = Scale(vector, ScalingMatrix(scaleX, scaleY));

      return new Vector3(ScalingMatrix[0][0], ScalingMatrix[0][1], ScalingMatrix[0][2]);
   }

   public Vector3 Transform(Vector3 vector, float scaleX, float scaleY, float degree, float cX, float cY)
   {
      float[][] ScaleRotate = ScaleRotate(vector, ScaleRotateMatrix(scaleX, scaleY, degree, cX, cY));

      return new Vector3(ScaleRotate[0][0], ScaleRotate[0][1], ScaleRotate[0][2]);
   }

//-----------------------------------------3D TRANSFORMATIONS-----------------------------------------------------    
   private float[][] TranslationMatrix(float x, float y, float z)
   {
      float[][] matrix = new float[4][4];

      matrix[0][0] = 1;
      matrix[0][1] = 0;
      matrix[0][2] = 0;
      matrix[0][3] = 0;

      matrix[1][0] = 0;
      matrix[1][1] = 1;
      matrix[1][2] = 0;
      matrix[1][3] = 0;

      matrix[2][0] = 0;
      matrix[2][1] = 0;
      matrix[2][2] = 1;
      matrix[2][3] = 0;

      matrix[3][0] = x;
      matrix[3][1] = y;
      matrix[3][2] = z;
      matrix[3][3] = 1;

      return matrix;
   }

   public Vector3 Transalte(Vector3 v, float x, float y, float z)
   {
      Vector3 vector = multiplyVector3WithMatrix4by4(v, TranslationMatrix(x, y, z));

      return vector;
   }

   private float[][] ScalingMatrix3DT(float scaleX, float scaleY, float scaleZ, float cX, float cY, float cZ)
   {
      float[][] ScalingMatrix = new float[4][4];

      ScalingMatrix[0][0] = scaleX;
      ScalingMatrix[0][1] = 0;
      ScalingMatrix[0][2] = 0;
      ScalingMatrix[0][3] = 0;

      ScalingMatrix[1][0] = 0;
      ScalingMatrix[1][1] = scaleY;
      ScalingMatrix[1][2] = 0;
      ScalingMatrix[1][3] = 0;

      ScalingMatrix[2][0] = 0;
      ScalingMatrix[2][1] = 0;
      ScalingMatrix[2][2] = scaleZ;
      ScalingMatrix[2][3] = 0;

      ScalingMatrix[3][0] = (cX * (1 - scaleX));
      ScalingMatrix[3][1] = (cY * (1 - scaleY));
      ScalingMatrix[3][2] = (cZ * (1 - scaleZ));
      ScalingMatrix[3][3] = 1;

      return ScalingMatrix;
   }

   private float[][] ScalingMatrix3D(float scaleX, float scaleY, float scaleZ)
   {
      float[][] ScalingMatrix = new float[4][4];

      ScalingMatrix[0][0] = scaleX;
      ScalingMatrix[0][1] = 0;
      ScalingMatrix[0][2] = 0;
      ScalingMatrix[0][3] = 0;

      ScalingMatrix[1][0] = 0;
      ScalingMatrix[1][1] = scaleY;
      ScalingMatrix[1][2] = 0;
      ScalingMatrix[1][3] = 0;

      ScalingMatrix[2][0] = 0;
      ScalingMatrix[2][1] = 0;
      ScalingMatrix[2][2] = scaleZ;
      ScalingMatrix[2][3] = 0;

      ScalingMatrix[3][0] = 0;
      ScalingMatrix[3][1] = 0;
      ScalingMatrix[3][2] = 0;
      ScalingMatrix[3][3] = 1;

      return ScalingMatrix;
   }

   private float[][] Scale3D(Vector3 vector, float[][] ScalingMatrix)
   {
      float[][] scaledMatrix = new float[1][4];

      scaledMatrix[0][0] = (vector.GetX() * ScalingMatrix[0][0])
              + (vector.GetY() * ScalingMatrix[1][0])
              + (vector.GetZ() * ScalingMatrix[2][0])
              + (ScalingMatrix[3][0]);

      scaledMatrix[0][1] = (vector.GetX() * ScalingMatrix[0][1])
              + (vector.GetY() * ScalingMatrix[1][1])
              + (vector.GetZ() * ScalingMatrix[2][1])
              + (ScalingMatrix[3][1]);

      scaledMatrix[0][2] = (vector.GetX() * ScalingMatrix[0][2])
              + (vector.GetY() * ScalingMatrix[1][2])
              + (vector.GetZ() * ScalingMatrix[2][2])
              + (ScalingMatrix[3][2]);

      scaledMatrix[0][3] = (vector.GetX() * ScalingMatrix[0][3])
              + (vector.GetY() * ScalingMatrix[1][3])
              + (vector.GetZ() * ScalingMatrix[2][3])
              + (ScalingMatrix[3][3]);

      return scaledMatrix;
   }

   private float[][] RotationMatrix3DTX(float degree, float cX, float cY, float cZ)
   {
      float[][] RotationMatrix = new float[4][4];

      RotationMatrix[0][0] = 1;
      RotationMatrix[0][1] = 0;
      RotationMatrix[0][2] = 0;
      RotationMatrix[0][3] = 0;

      RotationMatrix[1][0] = 0;
      RotationMatrix[1][1] = (float) Math.cos(Math.toRadians(degree));
      RotationMatrix[1][2] = 0;
      RotationMatrix[1][3] = 0;

      RotationMatrix[2][0] = 0;
      RotationMatrix[2][1] = 0;
      RotationMatrix[2][2] = (float) Math.cos(Math.toRadians(degree));;
      RotationMatrix[2][3] = 0;

      RotationMatrix[3][0] = 0;
      RotationMatrix[3][1] = (cY * (1 - (float) Math.cos(Math.toRadians(degree))))
              + (-cZ * ((float) Math.sin(Math.toRadians(-degree))));
      RotationMatrix[3][2] = (-cY * (float) Math.sin(Math.toRadians(degree)))
              + (cZ * (1 - ((float) Math.cos(Math.toRadians(degree)))));
      RotationMatrix[3][3] = 1;

      return RotationMatrix;
   }

   private float[][] RotationMatrix3DX(float degree)
   {
      float[][] RotationMatrix = new float[4][4];

      RotationMatrix[0][0] = 1;
      RotationMatrix[0][1] = 0;
      RotationMatrix[0][2] = 0;
      RotationMatrix[0][3] = 0;

      RotationMatrix[1][0] = 0;
      RotationMatrix[1][1] = (float) Math.cos(Math.toRadians(degree));
      RotationMatrix[1][2] = (float) Math.sin(Math.toRadians(degree));;
      RotationMatrix[1][3] = 0;

      RotationMatrix[2][0] = 0;
      RotationMatrix[2][1] = (float) Math.sin(Math.toRadians(-degree));;
      RotationMatrix[2][2] = (float) Math.cos(Math.toRadians(degree));;
      RotationMatrix[2][3] = 0;

      RotationMatrix[3][0] = 0;
      RotationMatrix[3][1] = 0;
      RotationMatrix[3][2] = 0;
      RotationMatrix[3][3] = 1;

      return RotationMatrix;
   }

   private float[][] RotationMatrix3DTY(float degree, float cX, float cY, float cZ)
   {
      float[][] RotationMatrix = new float[4][4];

      RotationMatrix[0][0] = (float) Math.cos(Math.toRadians(degree));
      RotationMatrix[0][1] = 0;
      RotationMatrix[0][2] = 0;
      RotationMatrix[0][3] = 0;

      RotationMatrix[1][0] = 0;
      RotationMatrix[1][1] = 1;
      RotationMatrix[1][2] = 0;
      RotationMatrix[1][3] = 0;

      RotationMatrix[2][0] = 0;
      RotationMatrix[2][1] = 0;
      RotationMatrix[2][2] = (float) Math.cos(Math.toRadians(degree));;
      RotationMatrix[2][3] = 0;

      RotationMatrix[3][0] = (-cZ * (float) Math.sin(Math.toRadians(-degree)));
      RotationMatrix[3][1] = 0;
      RotationMatrix[3][2] = (-cX * (float) Math.sin(Math.toRadians(-degree)))
              + (cZ * (1 - ((float) Math.cos(Math.toRadians(degree)))));
      RotationMatrix[3][3] = 1;

      return RotationMatrix;
   }

   private float[][] RotationMatrix3DY(float degree)
   {
      float[][] RotationMatrix = new float[4][4];

      RotationMatrix[0][0] = (float) Math.cos(Math.toRadians(degree));
      RotationMatrix[0][1] = 0;
      RotationMatrix[0][2] = (float) Math.sin(Math.toRadians(-degree));
      RotationMatrix[0][3] = 0;

      RotationMatrix[1][0] = 0;
      RotationMatrix[1][1] = 1;
      RotationMatrix[1][2] = 0;
      RotationMatrix[1][3] = 0;

      RotationMatrix[2][0] = (float) Math.sin(Math.toRadians(degree));
      RotationMatrix[2][1] = 0;
      RotationMatrix[2][2] = (float) Math.cos(Math.toRadians(degree));
      RotationMatrix[2][3] = 0;

      RotationMatrix[3][0] = 0;
      RotationMatrix[3][1] = 0;
      RotationMatrix[3][2] = 0;
      RotationMatrix[3][3] = 1;

      return RotationMatrix;
   }

   private float[][] RotationMatrix3DTZ(float degree, float cX, float cY, float cZ)
   {
      float[][] RotationMatrix = new float[4][4];

      RotationMatrix[0][0] = (float) Math.cos(Math.toRadians(degree));
      RotationMatrix[0][1] = 0;
      RotationMatrix[0][2] = 0;
      RotationMatrix[0][3] = 0;

      RotationMatrix[1][0] = 0;
      RotationMatrix[1][1] = (float) Math.cos(Math.toRadians(degree));
      RotationMatrix[1][2] = 0;
      RotationMatrix[1][3] = 0;

      RotationMatrix[2][0] = 0;
      RotationMatrix[2][1] = 0;
      RotationMatrix[2][2] = 1;
      RotationMatrix[2][3] = 0;

      RotationMatrix[3][0] = (cX * (1 - (float) Math.cos(Math.toRadians(degree))))
              + (-cY * (float) Math.sin(Math.toRadians(-degree)));
      RotationMatrix[3][1] = (-cX * (float) Math.sin(Math.toRadians(degree)))
              + (cY * (1 - (float) Math.cos(Math.toRadians(degree))));
      RotationMatrix[3][2] = 0;
      RotationMatrix[3][3] = 1;

      return RotationMatrix;
   }

   private float[][] RotationMatrix3DZ(float degree)
   {
      float[][] RotationMatrix = new float[4][4];

      RotationMatrix[0][0] = (float) Math.cos(Math.toRadians(degree));
      RotationMatrix[0][1] = (float) Math.sin(Math.toRadians(degree));
      RotationMatrix[0][2] = 0;
      RotationMatrix[0][3] = 0;

      RotationMatrix[1][0] = (float) Math.sin(Math.toRadians(-degree));
      RotationMatrix[1][1] = (float) Math.cos(Math.toRadians(degree));
      RotationMatrix[1][2] = 0;
      RotationMatrix[1][3] = 0;

      RotationMatrix[2][0] = 0;
      RotationMatrix[2][1] = 0;
      RotationMatrix[2][2] = 1;
      RotationMatrix[2][3] = 0;

      RotationMatrix[3][0] = 0;
      RotationMatrix[3][1] = 0;
      RotationMatrix[3][2] = 0;
      RotationMatrix[3][3] = 1;

      return RotationMatrix;
   }

   private float[][] Rotate3D(Vector3 vector, float[][] RotationMatrix)
   {
      float[][] rotatedMatrix = new float[1][4];

      rotatedMatrix[0][0] = (vector.GetX() * RotationMatrix[0][0])
              + (vector.GetY() * RotationMatrix[1][0])
              + (vector.GetZ() * RotationMatrix[2][0])
              + (RotationMatrix[3][0]);

      rotatedMatrix[0][1] = (vector.GetX() * RotationMatrix[0][1])
              + (vector.GetY() * RotationMatrix[1][1])
              + (vector.GetZ() * RotationMatrix[2][1])
              + (RotationMatrix[3][1]);

      rotatedMatrix[0][2] = (vector.GetX() * RotationMatrix[0][2])
              + (vector.GetY() * RotationMatrix[1][2])
              + (vector.GetZ() * RotationMatrix[2][2])
              + (RotationMatrix[3][2]);

      rotatedMatrix[0][3] = (vector.GetX() * RotationMatrix[0][3])
              + (vector.GetY() * RotationMatrix[1][3])
              + (vector.GetZ() * RotationMatrix[2][3])
              + (RotationMatrix[3][3]);

      return rotatedMatrix;
   }

   public Vector3 Transform3DScale(Vector3 vector, float scaleX, float scaleY, float scaleZ, float cX, float cY, float cZ)
   {
      float[][] ScalingMatrix = Scale3D(vector, ScalingMatrix3DT(scaleX, scaleY, scaleZ, cX, cY, cZ));

      return new Vector3(ScalingMatrix[0][0], ScalingMatrix[0][1], ScalingMatrix[0][2]);
   }

   public Vector3 Transform3DScale(Vector3 vector, float scaleX, float scaleY, float scaleZ)
   {
      float[][] ScalingMatrix = Scale3D(vector, ScalingMatrix3D(scaleX, scaleY, scaleZ));

      return new Vector3(ScalingMatrix[0][0], ScalingMatrix[0][1], ScalingMatrix[0][2]);
   }

   public Vector3 Transform3DRotateX(Vector3 vector, float degree, float cX, float cY, float cZ)
   {
      float[][] RotationMatrix = Rotate3D(vector, RotationMatrix3DTX(degree, cX, cY, cZ));

      return new Vector3(RotationMatrix[0][0], RotationMatrix[0][1], RotationMatrix[0][2]);
   }

   public Vector3 Transform3DRotateX(Vector3 vector, float degree)
   {
      float[][] RotationMatrix = Rotate3D(vector, RotationMatrix3DX(degree));

      return new Vector3(RotationMatrix[0][0], RotationMatrix[0][1], RotationMatrix[0][2]);
   }

   public Vector3 Transform3DRotateY(Vector3 vector, float degree, float cX, float cY, float cZ)
   {
      float[][] RotationMatrix = Rotate3D(vector, RotationMatrix3DTY(degree, cX, cY, cZ));

      return new Vector3(RotationMatrix[0][0], RotationMatrix[0][1], RotationMatrix[0][2]);
   }

   public Vector3 Transform3DRotateY(Vector3 vector, float degree)
   {
      float[][] RotationMatrix = Rotate3D(vector, RotationMatrix3DY(degree));

      return new Vector3(RotationMatrix[0][0], RotationMatrix[0][1], RotationMatrix[0][2]);
   }

   public Vector3 Transform3DRotateZ(Vector3 vector, float degree, float cX, float cY, float cZ)
   {
      float[][] RotationMatrix = Rotate3D(vector, RotationMatrix3DTZ(degree, cX, cY, cZ));

      return new Vector3(RotationMatrix[0][0], RotationMatrix[0][1], RotationMatrix[0][2]);
   }

   public Vector3 Transform3DRotateZ(Vector3 vector, float degree)
   {
      float[][] RotationMatrix = Rotate3D(vector, RotationMatrix3DZ(degree));

      return new Vector3(RotationMatrix[0][0], RotationMatrix[0][1], RotationMatrix[0][2]);
   }

//------------------------ARBITRARY POINT-------------------------------    
   //Step1
   private float[][] TranslationArbitraryMatrix(Vector3 vector)
   {
      float[][] matrix = new float[4][4];

      matrix[0][0] = 1;
      matrix[0][1] = 0;
      matrix[0][2] = 0;
      matrix[0][3] = 0;

      matrix[1][0] = 0;
      matrix[1][1] = 1;
      matrix[1][2] = 0;
      matrix[1][3] = 0;

      matrix[2][0] = 0;
      matrix[2][1] = 0;
      matrix[2][2] = 1;
      matrix[2][3] = 0;

      matrix[3][0] = -vector.GetX();
      matrix[3][1] = -vector.GetY();
      matrix[3][2] = -vector.GetZ();
      matrix[3][3] = 1;

      return matrix;
   }

   //Step2
   private float[][] RotateByThetaAngleMatrix(float cosT, float sinT)
   {
      float[][] matrix = new float[4][4];

      matrix[0][0] = 1;
      matrix[0][1] = 0;
      matrix[0][2] = 0;
      matrix[0][3] = 0;

      matrix[1][0] = 0;
      matrix[1][1] = cosT;
      matrix[1][2] = sinT;
      matrix[1][3] = 0;

      matrix[2][0] = 0;
      matrix[2][1] = -sinT;
      matrix[2][2] = cosT;
      matrix[2][3] = 0;

      matrix[3][0] = 0;
      matrix[3][1] = 0;
      matrix[3][2] = 0;
      matrix[3][3] = 1;

      return matrix;
   }

   //Step3
   private float[][] RotateByBetaAngleMatrix(float cosB, float sinB)
   {
      float[][] matrix = new float[4][4];

      matrix[0][0] = cosB;
      matrix[0][1] = 0;
      matrix[0][2] = -sinB;
      matrix[0][3] = 0;

      matrix[1][0] = 0;
      matrix[1][1] = 1;
      matrix[1][2] = 0;
      matrix[1][3] = 0;

      matrix[2][0] = sinB;;
      matrix[2][1] = 0;
      matrix[2][2] = cosB;
      matrix[2][3] = 0;

      matrix[3][0] = 0;
      matrix[3][1] = 0;
      matrix[3][2] = 0;
      matrix[3][3] = 1;

      return matrix;
   }

   //Step4 Rotate by 3dx-axis
   //step5 roate by negative Beta about y-axis
   private float[][] RotateByNegativeBetaAngleMatrix(float cosB, float sinB)
   {
      float[][] matrix = new float[4][4];

      matrix[0][0] = cosB;
      matrix[0][1] = 0;
      matrix[0][2] = sinB;
      matrix[0][3] = 0;

      matrix[1][0] = 0;
      matrix[1][1] = 1;
      matrix[1][2] = 0;
      matrix[1][3] = 0;

      matrix[2][0] = -sinB;
      matrix[2][1] = 0;
      matrix[2][2] = cosB;
      matrix[2][3] = 0;

      matrix[3][0] = 0;
      matrix[3][1] = 0;
      matrix[3][2] = 0;
      matrix[3][3] = 1;

      return matrix;
   }

   //Step6 Rotate about negative Theta about x-axis
   private float[][] RotateByNegativeThetaAngleMatrix(float cosT, float sinT)
   {
      float[][] matrix = new float[4][4];

      matrix[0][0] = 1;
      matrix[0][1] = 0;
      matrix[0][2] = 0;
      matrix[0][3] = 0;

      matrix[1][0] = 0;
      matrix[1][1] = cosT;
      matrix[1][2] = -sinT;
      matrix[1][3] = 0;

      matrix[2][0] = 0;
      matrix[2][1] = sinT;
      matrix[2][2] = cosT;
      matrix[2][3] = 0;

      matrix[3][0] = 0;
      matrix[3][1] = 0;
      matrix[3][2] = 0;
      matrix[3][3] = 1;

      return matrix;
   }

   //Step 7 translae back
   private float[][] TranslationBackArbitraryMatrix(Vector3 vector)
   {
      float[][] matrix = new float[4][4];

      matrix[0][0] = 1;
      matrix[0][1] = 0;
      matrix[0][2] = 0;
      matrix[0][3] = 0;

      matrix[1][0] = 0;
      matrix[1][1] = 1;
      matrix[1][2] = 0;
      matrix[1][3] = 0;

      matrix[2][0] = 0;
      matrix[2][1] = 0;
      matrix[2][2] = 1;
      matrix[2][3] = 0;

      matrix[3][0] = vector.GetX();
      matrix[3][1] = vector.GetY();
      matrix[3][2] = vector.GetZ();
      matrix[3][3] = 1;

      return matrix;
   }

   //FINAL MATRIX
   public float[][] RotateAroundArbitraryAxis(Vector3 p1, Vector3 p2, float degrees, boolean printSteps)
   {
      float S1 = (float) Math.sqrt(Math.pow(p2.GetY(), 2) + Math.pow(p2.GetZ(), 2));
      float S2 = (float) Math.sqrt(Math.pow(p2.GetX(), 2) + Math.pow(p2.GetY(), 2) + Math.pow(p2.GetZ(), 2));

      float cosT = (p2.GetZ() / S1);
      float sinT = (p2.GetY() / S1);

      float cosB = (p2.GetX() / S2);
      float sinB = (S1 / S2);

      if (S1 == 0)
      {
         cosT = 1;
         sinT = 0;
      }

      //step 1 translate p1
      float[][] translatedP1 = TranslationArbitraryMatrix(p1);
      if (printSteps)
      {
         System.out.println("Step1:");
         printMatrix(translatedP1);
      }

      //step2 Rotate p2 by angle theta about x axis
      float[][] ThetaAngleMatrix = RotateByThetaAngleMatrix(cosT, sinT);
      if (printSteps)
      {
         System.out.println("\nStep2:");
         printMatrix(ThetaAngleMatrix);
      }

      //step 3 Rotate by angle beta about y axis
      float[][] BetaAngleMatrix = RotateByBetaAngleMatrix(cosB, sinB);
      if (printSteps)
      {
         System.out.println("\nStep3:");
         printMatrix(BetaAngleMatrix);
      }

      //step4 Rotate around x-axis
      float[][] rotationMatrixX = RotationMatrix3DX(degrees);
      if (printSteps)
      {
         System.out.println("\nStep4:");
         printMatrix(rotationMatrixX);
      }

      //step 5 Rotate by -beta angle around y axis
      float[][] NegativeBetaAngleMatrix = RotateByNegativeBetaAngleMatrix(cosB, sinB);
      if (printSteps)
      {
         System.out.println("\nStep5:");
         printMatrix(NegativeBetaAngleMatrix);
      }

      //step 6 Rotate by -theta angle about x axis
      float[][] NegativeThetaAngleMatrix = RotateByNegativeThetaAngleMatrix(cosT, sinT);
      if (printSteps)
      {
         System.out.println("\nStep6:");
         printMatrix(NegativeThetaAngleMatrix);
      }

      //step 7 translate back
      float[][] translatedP1Back = TranslationBackArbitraryMatrix(p1);
      if (printSteps)
      {
         System.out.println("\nStep7:");
         printMatrix(translatedP1Back);
      }

      //MULTIPLY ALL MATRICES 1 by 1
      float[][] finalMatrix = multiplyMatrices(translatedP1, ThetaAngleMatrix);

      finalMatrix = multiplyMatrices(finalMatrix, BetaAngleMatrix);

      finalMatrix = multiplyMatrices(finalMatrix, rotationMatrixX);

      finalMatrix = multiplyMatrices(finalMatrix, NegativeBetaAngleMatrix);

      finalMatrix = multiplyMatrices(finalMatrix, NegativeThetaAngleMatrix);

      finalMatrix = multiplyMatrices(finalMatrix, translatedP1Back);

      if (printSteps)
      {
         System.out.println("\n\nStep6:");
         printMatrix(finalMatrix);
      }

      return finalMatrix;
   }

   //--------------------------------- SPECULAR REFLECTION -------------------------------------
   public Vector3 GetReflectedVector(Vector3 IncidentPointLight, Vector3 PpointOnPolygon, Vector3 Normal, boolean printInfo)
   {
      Vector3 I = IncidentPointLight;
      Vector3 P = PpointOnPolygon;
      Vector3 N = Normal;

      Vector3 L = GetVector(I, P);

      L.Normalize();// = Normalize(L);

      if (printInfo)
      {
         System.out.println(L.printVector());
      }

      float vect = GetDotProduct(L, N);
      vect = vect * -2;

      N.SetX(vect * N.GetX());
      N.SetY(vect * N.GetY());
      N.SetZ(vect * N.GetZ());

      return new Vector3(L.GetX() + N.GetX(), L.GetY() + N.GetY(), L.GetZ() + N.GetZ());
   }

   public float SpecReflFindCosA(Vector3 LightSource, Vector3 v1, Vector3 v2, Vector3 v3, int point, boolean printInfo)
   {
      //STEP1 calculate the unit directional incident light vector L  
      Vector3 L = new Vector3(0, 0, 0);

      if (point == 3)
      {
         L = GetVector(LightSource, v3);
      }

      L.Normalize();// = Normalize(L);
      System.out.println("|L|   : " + L.printVector());

      //STEP2 Calculate the unit normal directional vector to the polygon
      Vector3 a = new Vector3(0, 0, 0);
      Vector3 b = new Vector3(0, 0, 0);

      if (point == 3)
      {
         a = GetVector(v2, v3);
         if (printInfo)
         {
            System.out.println("Vec a : " + a.printVector());
         }

         b = GetVector(v2, v1);
         if (printInfo)
         {
            System.out.println("Vec b : " + b.printVector());
         }
      }

      Vector3 N = GetCrossProduct(a, b);
      if (printInfo)
      {
         System.out.println("|N|   : " + N.printVector());
      }

      //STEP3 Claculate cos        
      float CosA = GetDotProduct(L, N);
      if (printInfo)
      {
         System.out.println("COS A : " + CosA);
      }

      return CosA;
   }

   public float SpecReflFindCosB(Vector3 LightSource, Vector3 ViewPoint, Vector3 v1, Vector3 v2, Vector3 v3, int point, boolean printInfo)
   {
      //STEP1 calculate the reflected vector
      Vector3 L = new Vector3(0, 0, 0);

      if (point == 3)
      {
         L = GetVector(LightSource, v3);
      }

      L.Normalize();// = Normalize(L);

      Vector3 a = new Vector3(0, 0, 0);
      Vector3 b = new Vector3(0, 0, 0);

      if (point == 3)
      {
         a = GetVector(v2, v3);

         b = GetVector(v2, v1);
      }

      Vector3 N = GetCrossProduct(a, b);

      Vector3 R = GetReflectedVector(LightSource, v3, N, false);

      if (printInfo)
      {
         System.out.println("|R|   : " + R.printVector());
      }

      //STEP2 Calculate the unit directional viewpoint vector

      Vector3 V = GetVector(ViewPoint, v3);
      V.Normalize();// = Normalize(V);
      if (printInfo)
      {
         System.out.println("|V|   : " + V.printVector());
      }

      //STEP3 Calculate Cos using dot product

      float CosB = GetDotProduct(V, R);
      if (printInfo)
      {
         System.out.println("COS B : " + CosB);
      }

      return CosB;
   }
}