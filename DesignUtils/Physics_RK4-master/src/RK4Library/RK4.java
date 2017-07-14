/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package RK4Library;

import Application.*;
import MyMathsLibrary.MyMathLib;
import MyMathsLibrary.Vector3;

/*
 * @author DI
 */
public class RK4
{
   MyMathLib lib = new MyMathLib();
   
   public boolean performCalculation = true;
   
   private int iterations;
   
   private float g;//mass
   private float m;//mass
   private float t;//time
   private float fn;
   private float mS;
   private float mK;
   
   private Vector3 v;
   private Vector3 p;//position   
   private Vector3 a;//acceleration
   private Vector3 Fg;//gravity
   private Vector3 Fgn;//gravity
   private Vector3 Fn;//gravity
   private Vector3 N;//gravity
   private Vector3 Fgp;
   private Vector3 Ffric;//gravity
   private Vector3 Fnet;//total forces

   public Vector3 Getp()
   {
      return this.p;
   }

   public Vector3 Getv()
   {
      return this.a;
   }

   public RK4()
   {
      this.t = 0;
   }

   public Vector3 Fg(float m, float g, float angle)
   {
      this.t += 1/60f;
      DrawApplication.RK4ResultList.addElement("t: " + this.t);
      DrawApplication.RK4ResultList.addElement("");
      
      this.m = m;
      this.g = g;

      if (DrawApplication.calculate2D)
      {
         Vector3 k = new Vector3(m, -m, 0);

         if (DrawApplication.gravityOnOffCheckBox.isSelected())
         {
            this.Fg = new Vector3((k.GetX() * g) * (float) Math.cos(Math.toRadians(angle)), (k.GetY() * g) * (float) Math.cos(Math.toRadians(angle)), k.GetZ() * g);
         }
         else
         {
            this.Fg = new Vector3();
         }
      }
      else if (DrawApplication.calculate3D)
      {
         Vector3 k = new Vector3(0, 0, -1);

         if (DrawApplication.gravityOnOffCheckBox.isSelected())
         {
            this.Fg = new Vector3(k.GetX(), k.GetY(), k.GetZ() * g * m);
         }
         else
         {
            this.Fg = new Vector3();
         }
      }

      if (DrawApplication.forcesWorkingsCheckBox.isSelected())
      {
         DrawApplication.RK4ResultList.addElement("Fg: " + this.Fg.printVector());
      }

      return this.Fg;
   }

   public Vector3 Fgn(Vector3 N)
   {
      this.N = N;

      this.fn = lib.GetDotProduct(this.Fg, N);

      this.Fgn = new Vector3(N.GetX() * this.fn, N.GetY() * this.fn, N.GetZ() * this.fn);

      if (DrawApplication.forcesWorkingsCheckBox.isSelected())
      {
         DrawApplication.RK4ResultList.addElement("Fgn: " + this.Fgn.printVector());
      }

      return new Vector3();
   }

   public Vector3 Fn()
   {
      if (DrawApplication.calculate2D)
      {
         this.Fn = new Vector3();
         this.Fn.SetY(this.Fg.GetY() * -1);

         if (DrawApplication.forcesWorkingsCheckBox.isSelected())
         {
            DrawApplication.RK4ResultList.addElement("Fn: " + this.Fn.printVector());
         }
      }
      else if (DrawApplication.calculate3D)
      {
         this.fn *= -1;
         if (DrawApplication.forcesWorkingsCheckBox.isSelected())
         {
            DrawApplication.RK4ResultList.addElement("Fn: " + this.fn);
         }
      }

      return this.Fn;
   }

   public Vector3 Fgp()
   {
      this.Fgp = lib.SubtractVectors(this.Fg, this.Fgn);
      if (DrawApplication.forcesWorkingsCheckBox.isSelected())
      {
         DrawApplication.RK4ResultList.addElement("Fgp: " + this.Fgp.printVector());
      }
      return this.Fgp;
   }

   public Vector3 Ffric(float mS, float mK, Vector3 v)
   {
      this.mS = mS;
      this.mK = mK;
      this.v = v;

      this.Ffric = new Vector3();

      if (DrawApplication.calculate2D)
      {
         float maxStatic = mS * this.Fn.GetY();
         float maxKinetic = mK * this.Fn.GetY();

         if (maxStatic < this.Fn.GetY())
         {
            this.Ffric.SetX(maxKinetic * -1);
         }
         else
         {
            this.Ffric.SetX(maxStatic * -1);
         }
      }
      else if (DrawApplication.calculate3D)
      {
         float maxStatic = mS * this.fn;
         //float maxKinetic = mK * this.fn;
         
         if (maxStatic < this.fn)
         {
            this.fn *= mK;
         }
         else
         {
            this.fn *= mS;
         }

         float lg = lib.GetVectorLenght(v);
         
         Vector3 vec = new Vector3((v.GetX() * this.fn) / lg * -1, (v.GetY() * this.fn) / lg * -1, (v.GetZ() * this.fn) / lg * -1);
         
         if(Float.isNaN(vec.GetX()))
         {
            vec.SetX(0);
         }
         if(Float.isNaN(vec.GetY()))
         {
            vec.SetY(0);
         }
         if(Float.isNaN(vec.GetZ()))
         {
            vec.SetZ(0);
         }
         
         this.Ffric = vec;
      }

      if (DrawApplication.forcesWorkingsCheckBox.isSelected())
      {
         DrawApplication.RK4ResultList.addElement("Ffric: " + this.Ffric.printVector());
      }

      return this.Ffric;
   }

   public Vector3 Fnet()
   {
      if (DrawApplication.calculate2D)
      {
         Vector3 Fnet1;
         Fnet1 = lib.AddVectors(Fg, Fn);
         Fnet1 = lib.AddVectors(Fnet1, Ffric);

         this.Fnet = Fnet1;
      }
      else if (DrawApplication.calculate3D)
      {
         this.Fnet = lib.AddVectors(this.Ffric, this.Fgp);
      }

      if (DrawApplication.forcesWorkingsCheckBox.isSelected())
      {
         DrawApplication.RK4ResultList.addElement("Fnet: " + this.Fnet.printVector());
      }

      return this.Fnet;
   }

   public Vector3 acceleration()
   {
      this.a = new Vector3();

      if (DrawApplication.calculate2D)
      {
         this.a.SetX(Fnet.GetX() * (1 / this.m));
      }
      else if (DrawApplication.calculate3D)
      {
         this.a = new Vector3(Fnet.GetX() * (1 / this.m), Fnet.GetY() * (1 / this.m), Fnet.GetZ() * (1 / this.m));
      }

      DrawApplication.RK4ResultList.addElement("a: " + this.a.printVector());
      DrawApplication.RK4ResultList.addElement("-------------------------------------------------------------------------------------");

      return this.a;
   }

   public void Reset()
   {
      this.performCalculation = true;
      this.t = 0;
      this.Fg = new Vector3();
      this.Fnet = new Vector3();
      this.a = new Vector3();
      this.p = new Vector3();
      this.m = 0;
   }

   public void RK4Calculation(Vector3 acceleration, Vector3 position, Vector3 velocity,int iterations, boolean printSteps)
   {
      float h = 1 / 60f;
      
      this.iterations = iterations;

      for (int i = 0; i < iterations; i++)
      {
         if (this.performCalculation)//calculations are performed for hte first run of the RK4Calculation
         {
            //this.t += h;

            RK rk1 = new RK(acceleration, position, velocity, h);
            RK rk2 = new RK(acceleration, position, velocity, h);
            RK rk3 = new RK(acceleration, position, velocity, h);
            RK rk4 = new RK(acceleration, position, velocity, h);
            RK rk = new RK(acceleration, position, velocity, h);

            rk1.K1(printSteps);
            rk2.K2(rk1, printSteps);
            rk3.K3(rk2, printSteps);
            rk4.K4(rk3, printSteps);
            rk.K(rk1, rk2, rk3, rk4, printSteps);

            Vector3 pos = lib.AddVectors(position, rk.GetP());
            Vector3 vel = lib.AddVectors(velocity, rk.GetA());

            this.p = pos;
            this.a = vel;

            //DrawApplication.RK4ResultList.addElement("t: " + this.t);
            DrawApplication.RK4ResultList.addElement("POS: " + pos.printVector());
            DrawApplication.RK4ResultList.addElement("VEL: " + vel.printVector());
            DrawApplication.RK4ResultList.addElement("********************************************************************");
            DrawApplication.RK4ResultList.addElement(" ");


            this.performCalculation = false;
         }
         else if (!this.performCalculation || this.iterations == 0)//When the physics is simulated then this steps are performed instead of the previous one
         {
            //this.t += h;

            Fg(this.m, this.g, 0);
            Fgn(this.N);
            Fn();
            Fgp();
            Ffric(this.mS, this.mK, this.a);
            Fnet();
            acceleration();
            

            RK rk1 = new RK(this.a, this.p, this.v, h);
            RK rk2 = new RK(this.a, this.p, this.v, h);
            RK rk3 = new RK(this.a, this.p, this.v, h);
            RK rk4 = new RK(this.a, this.p, this.v, h);
            RK rk = new RK(this.a, this.p, this.v, h);

            rk1.K1(printSteps);
            rk2.K2(rk1, printSteps);
            rk3.K3(rk2, printSteps);
            rk4.K4(rk3, printSteps);
            rk.K(rk1, rk2, rk3, rk4, printSteps);

            Vector3 pos = lib.AddVectors(this.p, rk.GetP());
            Vector3 vel = lib.AddVectors(this.v, rk.GetA());

            this.p = pos;
            this.a = vel;


            //DrawApplication.RK4ResultList.addElement("t: " + this.t);
            DrawApplication.RK4ResultList.addElement("POS: " + pos.printVector());
            DrawApplication.RK4ResultList.addElement("VEL: " + vel.printVector());
            DrawApplication.RK4ResultList.addElement("********************************************************************");
            DrawApplication.RK4ResultList.addElement(" ");
            
         }
      }
   }
}

class RK
{
   private Vector3 A;//acceleration
   private Vector3 P;//position
   private Vector3 V;//velocity
   float h;

   public Vector3 GetA()
   {
      return this.A;
   }

   public Vector3 GetP()
   {
      return this.P;
   }

   public RK(Vector3 a, Vector3 p, Vector3 v, float h)
   {
      this.A = a;
      this.P = p;
      this.V = v;
      this.h = h;
   }

   private Vector3 Multiply(Vector3 v, float m)
   {
      return new Vector3(v.GetX() * m, v.GetY() * m, v.GetZ() * m);
   }

   private Vector3 add(Vector3 v1, Vector3 v2)
   {
      return new Vector3(v1.GetX() + v2.GetX(), v1.GetY() + v2.GetY(), v1.GetZ() + v2.GetZ());
   }

   public void K1(boolean printSteps)
   {
      this.P = Multiply(this.V, this.h);
      this.A = Multiply(A, this.h);

      if (printSteps)
      {
         System.out.println("K1=hf(ti,{p,v}i)");
         System.out.println(printP());
         System.out.println(printV());
         DrawApplication.RK4ResultList.addElement("K1: " + printP());
         DrawApplication.RK4ResultList.addElement("    " + printV());
         DrawApplication.RK4ResultList.addElement(" ");
      }
   }

   public void K2(RK k1, boolean printSteps)
   {
      Vector3 vec = Multiply(k1.A, 0.5f);
      vec = add(vec, this.V);
      this.P = Multiply(vec, this.h);
      this.A = Multiply(this.A, this.h);

      if (printSteps)
      {
         System.out.println("K2=hf(ti+h/2,{p,v}+1/2(K1))");
         System.out.println(printP());
         System.out.println(printV());
         DrawApplication.RK4ResultList.addElement("K2: " + printP());
         DrawApplication.RK4ResultList.addElement("    " + printV());
         DrawApplication.RK4ResultList.addElement(" ");
      }
   }

   public void K3(RK k2, boolean printSteps)
   {
      K2(k2, false);

      if (printSteps)
      {
         System.out.println("K3=hf(ti+h/2,{p,v}+1/2(K2))");
         System.out.println(printP());
         System.out.println(printV());
         DrawApplication.RK4ResultList.addElement("K3: " + printP());
         DrawApplication.RK4ResultList.addElement("    " + printV());
         DrawApplication.RK4ResultList.addElement(" ");
      }
   }

   public void K4(RK k3, boolean printSteps)
   {
      Vector3 vec = add(this.V, k3.A);
      this.P = Multiply(vec, this.h);
      this.A = Multiply(this.A, this.h);

      if (printSteps)
      {
         System.out.println("K4=hf(ti+2,{p,v}+K3)");
         System.out.println(printP());
         System.out.println(printV());
         DrawApplication.RK4ResultList.addElement("K4: " + printP());
         DrawApplication.RK4ResultList.addElement("    " + printV());
         DrawApplication.RK4ResultList.addElement(" ");
      }
   }

   public void K(RK k1, RK k2, RK k3, RK k4, boolean printSteps)
   {
      Vector3 vecMp = add(k1.P, Multiply(k2.P, 2));
      vecMp = add(vecMp, Multiply(k3.P, 2));
      vecMp = add(vecMp, k4.P);
      this.P = Multiply(vecMp, 1 / 6f);

      Vector3 vecMa = add(k1.A, Multiply(k2.A, 2));
      vecMa = add(vecMa, Multiply(k3.A, 2));
      vecMa = add(vecMa, k4.A);
      this.A = Multiply(vecMa, 1 / 6f);

      if (printSteps)
      {
         System.out.println("K=1/6(K1+2K2+2K3+K4)");
         System.out.println("K:\nVelocity: " + this.P.printVector() + "\nAcceleration: " + this.A.printVector() + "\n");
         DrawApplication.RK4ResultList.addElement("K: " + printP());
         DrawApplication.RK4ResultList.addElement("   " + printV());
         DrawApplication.RK4ResultList.addElement(" ");
      }
   }

   public String printP()
   {
      return "\nPosition: " + this.P.printVector() + "\n";
   }

   public String printV()
   {
      return "\nVelocity: " + this.A.printVector() + "\n";
   }
}
