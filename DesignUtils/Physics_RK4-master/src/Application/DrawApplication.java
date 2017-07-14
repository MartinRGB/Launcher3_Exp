package Application;

import RK4Library.RK4;
import Utilities.MyUtility;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import MyMathsLibrary.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.Date;

/**
 *
 * @author DI
 */
enum cubeSide
{
   backTopLeft, //0
   backTopRight, //1
   backBottomLeft, //2
   backBottomRight, //3    
   frontTopLeft, //4
   frontTopRight, //5
   frontBottomLeft, //6
   frontBottomRight  //7
};

public class DrawApplication extends javax.swing.JFrame
{
   private JFrame renderWindow = new JFrame("RenderWindow");
   private MyMathLib myMathLib = new MyMathLib();
   private MyUtility myUtility = new MyUtility();
   private boolean ScaleUp = false, ScaleDown = false;
   private boolean playPause = true;
   private boolean projectilePlayPause = false;
   //-------------------------COLORS--------------------------------------------
   private Color cubeColor, XYZPlane, Background;
   //--------------------- CUBE VECTORS-----------------------------------------    
   private Vector3 COMS = new Vector3(0, 0, 0);
   private Vector3 COM = COMS;
   private int pointLenght = 50;//length of the cube sides from COM
   private Vector3[] cubeVectors = AssignCubeVectors(pointLenght);
   //--------------------- ARBITRARY POINTS + TRANSLATION -------------------------------------    
   private Vector3 p1;
   private Vector3 p2;
   private float rotationDegree;
   private int translateX = 0, translateY = 0, translateZ = 0;
   //------------------------XYZ PLANE-----------------------------------------
   //mini plane
   private Vector3 X1CornerXYZPlane;// = new Vector3(50, 60, 1);
   private Vector3 X2CornerXYZPlane;// = new Vector3(150, 60, 1);
   private Vector3 Y1CornerXYZPlane;// = new Vector3(100, 20, 1);
   private Vector3 Y2CornerXYZPlane;// = new Vector3(100, 100, 1);
   //big centre plane
   private Vector3 x1CenterPlane;// = new Vector3(500, 0, 0);
   private Vector3 x2CenterPlane;// = new Vector3(500, 1000, 0);
   private Vector3 y1CenterPlane;// = new Vector3(0, 500, 0);
   private Vector3 y2CenterPlane;// = new Vector3(1000, 500, 0);
   //------------------------------RK4 PHYSICS------------------------------------------
   private RK4 rk4 = new RK4();// simulates physics
   RK4 rk41;
   static public boolean calculate2D = false, calculate3D = false;
   private float m, angle, g, mS, mK;
   private Vector3 N, v, p;
   private Vector3 RK4Point;

   /** Creates new form DrawApplication */
   public DrawApplication()
   {
      initComponents();

      renderWindow.addWindowListener(new WindowAdapter()
      {
         public void windowClosing(WindowEvent e)
         {
            System.exit(0);
         }
      });

      JApplet applet = new RenderingApplication();
      renderWindow.getContentPane().add("Center", applet);
      applet.init();
      renderWindow.pack();
      renderWindow.setSize(new Dimension(1000, 1000));
      renderWindow.setResizable(false);
      //renderWindow.show();

      this.cubeColor = Color.GREEN;
      this.XYZPlane = Color.LIGHT_GRAY;
      this.Background = Color.BLACK;

      X1CornerXYZPlane = new Vector3(-renderWindow.getSize().width / 2f + 50, -renderWindow.getSize().height / 2f + 60, 1);
      X2CornerXYZPlane = new Vector3(-renderWindow.getSize().width / 2f + 150, -renderWindow.getSize().height / 2f + 60, 1);
      Y1CornerXYZPlane = new Vector3(-renderWindow.getSize().width / 2f + 100, -renderWindow.getSize().height / 2f + 20, 1);
      Y2CornerXYZPlane = new Vector3(-renderWindow.getSize().width / 2f + 100, -renderWindow.getSize().height / 2f + 100, 1);

      this.x1CenterPlane = new Vector3(-renderWindow.getSize().width / 2f, 0, 0);
      this.x2CenterPlane = new Vector3(renderWindow.getSize().width / 2f, 0, 0);
      this.y1CenterPlane = new Vector3(0, -renderWindow.getSize().height / 2f, 0);
      this.y2CenterPlane = new Vector3(0, renderWindow.getSize().height / 2f, 0);

      //------------------------------RK4------------------------------------------


      this.g = Float.parseFloat(gravityTextField.getText());
      this.m = Float.parseFloat(objectMassTextField.getText());
      this.angle = Float.parseFloat(objectAngleTextField.getText());

      this.RK4Point = myUtility.ReturnVector(objectPositionTextField.getText());
      RK4ResultList.clear();
   }

   @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        RK4ResultList = new javax.swing.DefaultListModel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        RK4Panel = new javax.swing.JPanel();
        RK4CalculateButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        resetRK4 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jTabbedPane3 = new javax.swing.JTabbedPane();
        jPanel5 = new javax.swing.JPanel();
        jLabel30 = new javax.swing.JLabel();
        objectPositionTextField1 = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        objectVelocityTextField1 = new javax.swing.JTextField();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        objectStaticForceTextField1 = new javax.swing.JTextField();
        objectKineticForceTextField1 = new javax.swing.JTextField();
        jLabel37 = new javax.swing.JLabel();
        objectMassTextField1 = new javax.swing.JTextField();
        applyRK4Variables3DButton1 = new javax.swing.JButton();
        jLabel36 = new javax.swing.JLabel();
        planeNormalTextField = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        objectPositionTextField = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        objectVelocityTextField = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        objectStaticForceTextField = new javax.swing.JTextField();
        objectKineticForceTextField = new javax.swing.JTextField();
        objectAngleTextField = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        objectMassTextField = new javax.swing.JTextField();
        applyRK4Variables2DButton = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        gravityTextField = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        gravityOnOffCheckBox = new javax.swing.JCheckBox();
        jLabel24 = new javax.swing.JLabel();
        iterationsTextField = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        objectPresetComboBox = new javax.swing.JComboBox();
        jLabel32 = new javax.swing.JLabel();
        showRK4StepsCheckBox = new javax.swing.JCheckBox();
        forcesWorkingsCheckBox = new javax.swing.JCheckBox();
        jLabel33 = new javax.swing.JLabel();
        RK4NextStepButton = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        FileMenuBarItem = new javax.swing.JMenu();
        saveRK4ResultsButton = new javax.swing.JMenuItem();
        exitProgramButton = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("DrawAppProperties");
        setBackground(new java.awt.Color(0, 0, 0));
        setBounds(new java.awt.Rectangle(1000, 0, 0, 0));
        setName("MainFrame"); // NOI18N
        setResizable(false);

        jTabbedPane1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        RK4CalculateButton.setText("Calculate RK4");
        RK4CalculateButton.setEnabled(false);
        RK4CalculateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RK4CalculateButtonActionPerformed(evt);
            }
        });

        jList1.setModel(RK4ResultList);
        jScrollPane1.setViewportView(jList1);

        resetRK4.setText("Reset");
        resetRK4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetRK4ActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), "Propertis", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Object"));

        jLabel30.setText("Velocity(X,Y,Z)m/s");

        objectPositionTextField1.setText("3,-6,8");

        jLabel31.setText("Ms");

        objectVelocityTextField1.setText("-16,10,12");

        jLabel34.setText("Position(X,Y,Z)m");

        jLabel35.setText("Mk");

        objectStaticForceTextField1.setText("0.7");

        objectKineticForceTextField1.setText("0.5");

        jLabel37.setText("Mass");

        objectMassTextField1.setText("2");

        applyRK4Variables3DButton1.setText("Apply Propertis");
        applyRK4Variables3DButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                applyRK4Variables3DButton1ActionPerformed(evt);
            }
        });

        jLabel36.setText("N");

        planeNormalTextField.setText("0.6,0,0.8");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel30)
                            .addComponent(jLabel31)
                            .addComponent(jLabel35)
                            .addComponent(jLabel34)
                            .addComponent(jLabel37)
                            .addComponent(jLabel36))
                        .addGap(11, 11, 11)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(planeNormalTextField)
                            .addComponent(objectPositionTextField1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(objectKineticForceTextField1)
                            .addComponent(objectStaticForceTextField1)
                            .addComponent(objectVelocityTextField1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(objectMassTextField1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(applyRK4Variables3DButton1)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(objectMassTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel37))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel34)
                    .addComponent(objectPositionTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30)
                    .addComponent(objectVelocityTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel31)
                    .addComponent(objectStaticForceTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel35)
                    .addComponent(objectKineticForceTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel36)
                    .addComponent(planeNormalTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(applyRK4Variables3DButton1)
                .addContainerGap(92, Short.MAX_VALUE))
        );

        jTabbedPane3.addTab("3D", jPanel5);

        jLabel18.setText("Velocity(X,Y,Z)m/s");

        objectPositionTextField.setText("1,2,0");

        jLabel21.setText("Ms");

        objectVelocityTextField.setText("4,-10,0");

        jLabel19.setText("Position(X,Y,Z)m");

        jLabel22.setText("Mk");

        jLabel20.setText("Angle");

        objectStaticForceTextField.setText("0.7");

        objectKineticForceTextField.setText("0.6");

        objectAngleTextField.setText("45");

        jLabel17.setText("Mass");

        objectMassTextField.setText("2");

        applyRK4Variables2DButton.setText("Apply Propertis");
        applyRK4Variables2DButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                applyRK4Variables2DButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel18)
                    .addComponent(jLabel21)
                    .addComponent(jLabel22)
                    .addComponent(jLabel20)
                    .addComponent(jLabel19)
                    .addComponent(jLabel17))
                .addGap(33, 33, 33)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(objectPositionTextField, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(objectAngleTextField)
                    .addComponent(objectKineticForceTextField)
                    .addComponent(objectStaticForceTextField)
                    .addComponent(objectVelocityTextField, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(objectMassTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27))
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(applyRK4Variables2DButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(objectMassTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel19)
                    .addComponent(objectPositionTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(objectVelocityTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(objectStaticForceTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(objectKineticForceTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(objectAngleTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20))
                .addGap(18, 18, 18)
                .addComponent(applyRK4Variables2DButton)
                .addContainerGap(92, Short.MAX_VALUE))
        );

        jTabbedPane3.addTab("2D", jPanel8);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTabbedPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jTabbedPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 322, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Forces"));

        gravityTextField.setText("9.81");

        jLabel23.setText("Fg");

        jLabel29.setText("Fg ON/OFF");

        gravityOnOffCheckBox.setSelected(true);
        gravityOnOffCheckBox.setEnabled(false);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel23)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(gravityTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel29)
                        .addGap(20, 20, 20)
                        .addComponent(gravityOnOffCheckBox)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(gravityTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel23))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel29)
                    .addComponent(gravityOnOffCheckBox)))
        );

        jLabel24.setText("Iteraions");

        iterationsTextField.setText("1");

        jLabel25.setText("60 iter. = 1 second");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel24)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(iterationsTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel25))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(iterationsTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel25))
                .addGap(30, 30, 30))
        );

        jLabel26.setText("Objects Preset");

        objectPresetComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Example 1", "Example 2", "Example 3" }));
        objectPresetComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                objectPresetComboBoxActionPerformed(evt);
            }
        });

        jLabel32.setText("Show RK4 workings");

        showRK4StepsCheckBox.setSelected(true);

        forcesWorkingsCheckBox.setSelected(true);

        jLabel33.setText("Show F workings");

        RK4NextStepButton.setText("Next Step(# iter.)");
        RK4NextStepButton.setEnabled(false);
        RK4NextStepButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RK4NextStepButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout RK4PanelLayout = new javax.swing.GroupLayout(RK4Panel);
        RK4Panel.setLayout(RK4PanelLayout);
        RK4PanelLayout.setHorizontalGroup(
            RK4PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(RK4PanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(RK4PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 404, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(RK4PanelLayout.createSequentialGroup()
                        .addGroup(RK4PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel33)
                            .addComponent(jLabel32))
                        .addGap(12, 12, 12)
                        .addGroup(RK4PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(forcesWorkingsCheckBox)
                            .addComponent(showRK4StepsCheckBox))
                        .addGap(27, 27, 27)
                        .addGroup(RK4PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(RK4CalculateButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(RK4NextStepButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(RK4PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(RK4PanelLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel26)
                        .addGap(18, 18, 18)
                        .addComponent(objectPresetComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(RK4PanelLayout.createSequentialGroup()
                        .addGap(147, 147, 147)
                        .addComponent(resetRK4, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        RK4PanelLayout.setVerticalGroup(
            RK4PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, RK4PanelLayout.createSequentialGroup()
                .addGroup(RK4PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 566, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, RK4PanelLayout.createSequentialGroup()
                        .addGroup(RK4PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel26)
                            .addComponent(objectPresetComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(RK4PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(RK4PanelLayout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(resetRK4))
                    .addGroup(RK4PanelLayout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(RK4PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(RK4PanelLayout.createSequentialGroup()
                                .addGroup(RK4PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(RK4PanelLayout.createSequentialGroup()
                                        .addGroup(RK4PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel32)
                                            .addComponent(showRK4StepsCheckBox))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                                    .addComponent(RK4CalculateButton))
                                .addGroup(RK4PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(RK4NextStepButton)
                                    .addComponent(forcesWorkingsCheckBox)))
                            .addGroup(RK4PanelLayout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addComponent(jLabel33)))))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Sliding", RK4Panel);

        getContentPane().add(jTabbedPane1, java.awt.BorderLayout.CENTER);

        FileMenuBarItem.setText("File");

        saveRK4ResultsButton.setText("Save RK4 Results");
        saveRK4ResultsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveRK4ResultsButtonActionPerformed(evt);
            }
        });
        FileMenuBarItem.add(saveRK4ResultsButton);

        exitProgramButton.setText("Exit");
        exitProgramButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitProgramButtonActionPerformed(evt);
            }
        });
        FileMenuBarItem.add(exitProgramButton);

        jMenuBar1.add(FileMenuBarItem);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

//Java Automatically Generated Code
private void exitProgramButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitProgramButtonActionPerformed
// TODO add your handling code here:
   System.exit(0);
}//GEN-LAST:event_exitProgramButtonActionPerformed

//Java Automatically Generated Code
private void RK4CalculateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RK4CalculateButtonActionPerformed
// TODO add your handling code here:   
   RK4CalculateButton.setEnabled(false);
   RK4NextStepButton.setEnabled(true);

   RK4ResultList.clear();

   rk41 = new RK4();

   Vector3 Fg1 = rk41.Fg(m, g, angle);
   Vector3 Fgn1 = rk41.Fgn(this.N);
   Vector3 Fn1 = rk41.Fn();
   Vector3 Fgp1 = rk41.Fgp();
   Vector3 Ffric1 = rk41.Ffric(mS, mK, v);
   Vector3 Ffnet1 = rk41.Fnet();
   Vector3 a1 = rk41.acceleration();

   rk41.RK4Calculation(a1, this.p, this.v, Integer.parseInt(iterationsTextField.getText()), showRK4StepsCheckBox.isSelected());
   this.p = rk41.Getp();
   this.v = rk41.Getv();
}//GEN-LAST:event_RK4CalculateButtonActionPerformed

//Java Automatically Generated Code
private void resetRK4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetRK4ActionPerformed
// TODO add your handling code here:   
   rk4.Reset();
   RK4NextStepButton.setEnabled(false);
   /*
   this.Fg = rk4.FgSphere(r, density);
   this.Fd = rk4.FdSphere(fluidDensity, r, dragC, velocity, flowRate);
   this.Fm = rk4.FmSphere(spinning, r);
   this.Fnet = rk4.FnetSphere(Fg, Fd, Fm);
   this.a = rk4.acceleration(Fnet);
    * 
    */
   this.RK4Point = myUtility.ReturnVector(objectPositionTextField.getText());
   RK4ResultList.clear();

}//GEN-LAST:event_resetRK4ActionPerformed

//Java Automatically Generated Code
private void saveRK4ResultsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveRK4ResultsButtonActionPerformed
// TODO add your handling code here:   
   try
   {
      Object[] results = RK4ResultList.toArray();

      Writer output = null;
      File file = new File("Result Set.txt");
      output = new BufferedWriter(new FileWriter(file));

      output.write("Gravity: " + this.gravityTextField.getText() + "\r\n");
      output.write("Mass: " + this.objectMassTextField1.getText() + "\r\n");
      output.write("Position: " + this.objectPositionTextField1.getText() + "\r\n");
      output.write("Velocity: " + this.objectVelocityTextField1.getText() + "\r\n");
      output.write("Ms : " + this.objectStaticForceTextField1.getText() + "\r\n");
      output.write("Mk : " + this.objectKineticForceTextField1.getText() + "\r\n");
      output.write("Normal : " + this.planeNormalTextField.getText() + "\r\n");

      output.write("############################################################\r\n");
      output.write("############################################################\r\n");
      output.write("############################################################\r\n");


      for (Object o : results)
      {
         output.write(o.toString());
         output.write("\r\n");
      }

      output.close();
   } catch (Exception e)
   {//Catch exception if any
      System.err.println("Error: " + e.getMessage());
   }
}//GEN-LAST:event_saveRK4ResultsButtonActionPerformed

//Java Automatically Generated Code
private void objectPresetComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_objectPresetComboBoxActionPerformed
// TODO add your handling code here:
   int selected = objectPresetComboBox.getSelectedIndex();

   if (selected == 1)
   {
      objectMassTextField1.setText("1.2");
      objectPositionTextField1.setText("8,-11,13");
      objectVelocityTextField1.setText("-21,15,17");
      objectStaticForceTextField1.setText("0.3");
      objectKineticForceTextField1.setText("0.7");
      planeNormalTextField.setText("0.8,0,0.9");
   }
   else if (selected == 2)
   {
      objectMassTextField1.setText("2.3");
      objectPositionTextField1.setText("38,41,53");
      objectVelocityTextField1.setText("-37,9,23");
      objectStaticForceTextField1.setText("0.5");
      objectKineticForceTextField1.setText("0.8");
      planeNormalTextField.setText("0.4,0.8,0.4");
   }
   else if (selected == 3)
   {
      objectMassTextField1.setText("0.7");
      objectPositionTextField1.setText("20,18,9");
      objectVelocityTextField1.setText("-9,17,2");
      objectStaticForceTextField1.setText("0.2");
      objectKineticForceTextField1.setText("0.9");
      planeNormalTextField.setText("0.3,0.1,0.6");
   }
}//GEN-LAST:event_objectPresetComboBoxActionPerformed

private void applyRK4Variables2DButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_applyRK4Variables2DButtonActionPerformed
// TODO add your handling code here:
   RK4CalculateButton.setEnabled(true);
   RK4NextStepButton.setEnabled(false);

   RK4ResultList.clear();
   
   this.calculate2D = true;
   this.calculate3D = false;

   this.g = Float.parseFloat(gravityTextField.getText());
   this.m = Float.parseFloat(objectMassTextField.getText());
   this.angle = Float.parseFloat(objectAngleTextField.getText());
   this.mS = Float.parseFloat(objectStaticForceTextField.getText());
   this.mK = Float.parseFloat(objectKineticForceTextField.getText());
   this.v = myUtility.ReturnVector(objectVelocityTextField.getText());
   this.p = myUtility.ReturnVector(objectPositionTextField.getText());
   
   this.N = myUtility.ReturnVector(planeNormalTextField.getText());

   //this.RK4Point = myUtility.ReturnVector(objectPositionTextField.getText());

   //projectilePlayPauseButton.setEnabled(true);
}//GEN-LAST:event_applyRK4Variables2DButtonActionPerformed

private void applyRK4Variables3DButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_applyRK4Variables3DButton1ActionPerformed
// TODO add your handling code here:
   RK4CalculateButton.setEnabled(true);
   RK4NextStepButton.setEnabled(false);
   
   RK4ResultList.clear();

   this.calculate2D = false;
   this.calculate3D = true;

   this.g = Float.parseFloat(gravityTextField.getText());
   this.m = Float.parseFloat(objectMassTextField1.getText());
   this.mS = Float.parseFloat(objectStaticForceTextField1.getText());
   this.mK = Float.parseFloat(objectKineticForceTextField1.getText());

   this.v = myUtility.ReturnVector(objectVelocityTextField1.getText());
   this.p = myUtility.ReturnVector(objectPositionTextField1.getText());

   this.N = myUtility.ReturnVector(planeNormalTextField.getText());
}//GEN-LAST:event_applyRK4Variables3DButton1ActionPerformed

private void RK4NextStepButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RK4NextStepButtonActionPerformed
// TODO add your handling code here:
   rk41.performCalculation = true;
   Vector3 Fg1 = rk41.Fg(m, g, angle);
   Vector3 Fgn1 = rk41.Fgn(this.N);
   Vector3 Fn1 = rk41.Fn();
   Vector3 Fgp1 = rk41.Fgp();
   Vector3 Ffric1 = rk41.Ffric(mS, mK, this.v);
   Vector3 Ffnet1 = rk41.Fnet();
   Vector3 a2 = rk41.acceleration();
   rk41.RK4Calculation(a2, this.p, this.v, Integer.parseInt(iterationsTextField.getText()), showRK4StepsCheckBox.isSelected());
   this.p = rk41.Getp();
   this.v = rk41.Getv();
}//GEN-LAST:event_RK4NextStepButtonActionPerformed

   public static void main(String args[])
   {
      /* Set the Nimbus look and feel */
      //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
       * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
       */
      try
      {
         for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels())
         {
            if ("Nimbus".equals(info.getName()))
            {
               javax.swing.UIManager.setLookAndFeel(info.getClassName());
               break;
            }
         }
      } catch (ClassNotFoundException ex)
      {
         java.util.logging.Logger.getLogger(RenderingApplication.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
      } catch (InstantiationException ex)
      {
         java.util.logging.Logger.getLogger(RenderingApplication.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
      } catch (IllegalAccessException ex)
      {
         java.util.logging.Logger.getLogger(RenderingApplication.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
      } catch (javax.swing.UnsupportedLookAndFeelException ex)
      {
         java.util.logging.Logger.getLogger(RenderingApplication.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
      }
      //</editor-fold>
        /* Create and display the form */

      java.awt.EventQueue.invokeLater(new Runnable()
      {
         public void run()
         {
            new DrawApplication().setVisible(true);
         }
      });
   }
//--------------------------------------------------------------------------------

   public class RenderingApplication extends JApplet
   {
      public void init()
      {
         getContentPane().add(new AnimationPanel());
      }

      public class AnimationPanel extends JPanel
      {
         MyMathLib lib = new MyMathLib();

         public AnimationPanel()
         {
         }

         //draws all the lines of hte cube on hte screen
         private void DrawCube(Graphics g)
         {
            g.setColor(cubeColor);

            //Back
            g.drawLine((int) cubeVectors[cubeSide.backTopLeft.ordinal()].GetX(), (int) cubeVectors[cubeSide.backTopLeft.ordinal()].GetY(),
                    (int) cubeVectors[cubeSide.backTopRight.ordinal()].GetX(), (int) cubeVectors[cubeSide.backTopRight.ordinal()].GetY());//TR-TL

            g.drawLine((int) cubeVectors[cubeSide.backBottomLeft.ordinal()].GetX(), (int) cubeVectors[cubeSide.backBottomLeft.ordinal()].GetY(),
                    (int) cubeVectors[cubeSide.backBottomRight.ordinal()].GetX(), (int) cubeVectors[cubeSide.backBottomRight.ordinal()].GetY());//BR-BL

            g.drawLine((int) cubeVectors[cubeSide.backTopLeft.ordinal()].GetX(), (int) cubeVectors[cubeSide.backTopLeft.ordinal()].GetY(),
                    (int) cubeVectors[cubeSide.backBottomLeft.ordinal()].GetX(), (int) cubeVectors[cubeSide.backBottomLeft.ordinal()].GetY());//TL-BL

            g.drawLine((int) cubeVectors[cubeSide.backTopRight.ordinal()].GetX(), (int) cubeVectors[cubeSide.backTopRight.ordinal()].GetY(),
                    (int) cubeVectors[cubeSide.backBottomRight.ordinal()].GetX(), (int) cubeVectors[cubeSide.backBottomRight.ordinal()].GetY());//TR-BR                        

            //Front
            g.drawLine((int) cubeVectors[cubeSide.frontTopLeft.ordinal()].GetX(), (int) cubeVectors[cubeSide.frontTopLeft.ordinal()].GetY(),
                    (int) cubeVectors[cubeSide.frontTopRight.ordinal()].GetX(), (int) cubeVectors[cubeSide.frontTopRight.ordinal()].GetY());//TR-TL

            g.drawLine((int) cubeVectors[cubeSide.frontBottomLeft.ordinal()].GetX(), (int) cubeVectors[cubeSide.frontBottomLeft.ordinal()].GetY(),
                    (int) cubeVectors[cubeSide.frontBottomRight.ordinal()].GetX(), (int) cubeVectors[cubeSide.frontBottomRight.ordinal()].GetY());//BR-BL

            g.drawLine((int) cubeVectors[cubeSide.frontTopLeft.ordinal()].GetX(), (int) cubeVectors[cubeSide.frontTopLeft.ordinal()].GetY(),
                    (int) cubeVectors[cubeSide.frontBottomLeft.ordinal()].GetX(), (int) cubeVectors[cubeSide.frontBottomLeft.ordinal()].GetY());//TL-BL

            g.drawLine((int) cubeVectors[cubeSide.frontTopRight.ordinal()].GetX(), (int) cubeVectors[cubeSide.frontTopRight.ordinal()].GetY(),
                    (int) cubeVectors[cubeSide.frontBottomRight.ordinal()].GetX(), (int) cubeVectors[cubeSide.frontBottomRight.ordinal()].GetY());//TR-BR

            //Sides
            g.drawLine((int) cubeVectors[cubeSide.backTopLeft.ordinal()].GetX(), (int) cubeVectors[cubeSide.backTopLeft.ordinal()].GetY(),
                    (int) cubeVectors[cubeSide.frontTopLeft.ordinal()].GetX(), (int) cubeVectors[cubeSide.frontTopLeft.ordinal()].GetY());//BTL-FTL

            g.drawLine((int) cubeVectors[cubeSide.backTopRight.ordinal()].GetX(), (int) cubeVectors[cubeSide.backTopRight.ordinal()].GetY(),
                    (int) cubeVectors[cubeSide.frontTopRight.ordinal()].GetX(), (int) cubeVectors[cubeSide.frontTopRight.ordinal()].GetY());//BTR-FTR

            g.drawLine((int) cubeVectors[cubeSide.backBottomLeft.ordinal()].GetX(), (int) cubeVectors[cubeSide.backBottomLeft.ordinal()].GetY(),
                    (int) cubeVectors[cubeSide.frontBottomLeft.ordinal()].GetX(), (int) cubeVectors[cubeSide.frontBottomLeft.ordinal()].GetY());//BBL-FBL

            g.drawLine((int) cubeVectors[cubeSide.backBottomRight.ordinal()].GetX(), (int) cubeVectors[cubeSide.backBottomRight.ordinal()].GetY(),
                    (int) cubeVectors[cubeSide.frontBottomRight.ordinal()].GetX(), (int) cubeVectors[cubeSide.frontBottomRight.ordinal()].GetY());//BBR-FBR
         }
         
         private void DrawXYZPlane(Graphics g)
         {
            g.setColor(XYZPlane);

            g.drawString("X", (int) X2CornerXYZPlane.GetX() + 5, (int) X2CornerXYZPlane.GetY() + 5);
            g.drawLine((int) X1CornerXYZPlane.GetX(), (int) X1CornerXYZPlane.GetY(), (int) X2CornerXYZPlane.GetX(), (int) X2CornerXYZPlane.GetY());
            g.drawString("Y", (int) Y1CornerXYZPlane.GetX() - 3, (int) Y1CornerXYZPlane.GetY() - 3);
            g.drawLine((int) Y1CornerXYZPlane.GetX(), (int) Y1CornerXYZPlane.GetY(), (int) Y2CornerXYZPlane.GetX(), (int) Y2CornerXYZPlane.GetY());
            g.drawString("Z", (int) X2CornerXYZPlane.GetX() + 5, (int) Y1CornerXYZPlane.GetY());
            g.drawLine((int) X2CornerXYZPlane.GetX(), (int) Y1CornerXYZPlane.GetY(), (int) X1CornerXYZPlane.GetX(), (int) Y2CornerXYZPlane.GetY());

            g.drawLine((int) x1CenterPlane.GetX(), (int) x1CenterPlane.GetY(), (int) x2CenterPlane.GetX(), (int) x2CenterPlane.GetY());
            g.drawLine((int) y1CenterPlane.GetX(), (int) y1CenterPlane.GetY(), (int) y2CenterPlane.GetX(), (int) y2CenterPlane.GetY());
         }

         private void DrawP1P2Points(Graphics g)
         {
            g.setColor(Color.ORANGE);

            g.drawString("P1", (int) p1.GetX(), (int) p1.GetY());
            g.drawString("P2", (int) p2.GetX(), (int) p2.GetY());
         }

         //Translation can be performed either with animation or straight away
         

         private void Scale()
         {
            float cX = ((cubeVectors[cubeSide.backTopLeft.ordinal()].GetX() + cubeVectors[cubeSide.backTopRight.ordinal()].GetX()) / 2);

            float cY = ((cubeVectors[cubeSide.backTopLeft.ordinal()].GetY() + cubeVectors[cubeSide.backBottomLeft.ordinal()].GetY()) / 2);

            float cZ = (cubeVectors[cubeSide.backTopLeft.ordinal()].GetZ() + cubeVectors[cubeSide.frontTopLeft.ordinal()].GetZ()) / 2;

            if (ScaleUp)
            {
               for (int i = 0; i < cubeVectors.length; i++)
               {
                  cubeVectors[i] = myMathLib.Transform3DScale(cubeVectors[i], 1.1f, 1.1f, 1.1f, cX, cY, cZ);
               }
               ScaleUp = false;
            }
            else if (ScaleDown)
            {
               for (int i = 0; i < cubeVectors.length; i++)
               {
                  cubeVectors[i] = myMathLib.Transform3DScale(cubeVectors[i], 0.9f, 0.9f, 0.9f, cX, cY, cZ);
               }
               ScaleDown = false;
            }
         }

         private void RotateAroundArbitraryAxis()
         {
            //work out the arbitrary matrix first and after apply it to each point
            float[][] arbitraryMatrix = lib.RotateAroundArbitraryAxis(p1, p2, rotationDegree, false);

            for (int i = 0; i < cubeVectors.length; i++)
            {
               cubeVectors[i] = myMathLib.multiplyVector3WithMatrix4by4(cubeVectors[i], arbitraryMatrix);
            }
         }
      }
   }

   private Vector3[] AssignCubeVectors(int sideLenghts)
   {
      COM = COMS;

      pointLenght = sideLenghts;

      Vector3 StartBackTopLeft = new Vector3(COM.GetX() - pointLenght, COM.GetY() - pointLenght, COM.GetZ() + pointLenght);
      Vector3 StartBackTopRight = new Vector3(COM.GetX() + pointLenght, COM.GetY() - pointLenght, COM.GetZ() + pointLenght);
      Vector3 StartBackBottomLeft = new Vector3(COM.GetX() - pointLenght, COM.GetY() + pointLenght, COM.GetZ() + pointLenght);
      Vector3 StartBackBottomRight = new Vector3(COM.GetX() + pointLenght, COM.GetY() + pointLenght, COM.GetZ() + pointLenght);

      Vector3 StartFrontTopLeft = new Vector3(COM.GetX() - pointLenght, COM.GetY() - pointLenght, COM.GetZ() - pointLenght);
      Vector3 StartFrontTopRight = new Vector3(COM.GetX() + pointLenght, COM.GetY() - pointLenght, COM.GetZ() - pointLenght);
      Vector3 StartFrontBottomLeft = new Vector3(COM.GetX() - pointLenght, COM.GetY() + pointLenght, COM.GetZ() - pointLenght);
      Vector3 StartFrontBottomRight = new Vector3(COM.GetX() + pointLenght, COM.GetY() + pointLenght, COM.GetZ() - pointLenght);

      Vector3[] cubeStartVectors =
      {
         StartBackTopLeft, StartBackTopRight, StartBackBottomLeft, StartBackBottomRight,
         StartFrontTopLeft, StartFrontTopRight, StartFrontBottomLeft, StartFrontBottomRight
      };

      return cubeStartVectors;
   }

   private void ResetCubeVectors()
   {
      cubeVectors = AssignCubeVectors(pointLenght);
   }

  
   //Java Automatically Generated Code
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu FileMenuBarItem;
    private javax.swing.JButton RK4CalculateButton;
    private javax.swing.JButton RK4NextStepButton;
    private javax.swing.JPanel RK4Panel;
    public static javax.swing.DefaultListModel RK4ResultList;
    private javax.swing.JButton applyRK4Variables2DButton;
    private javax.swing.JButton applyRK4Variables3DButton1;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JMenuItem exitProgramButton;
    public static javax.swing.JCheckBox forcesWorkingsCheckBox;
    public static javax.swing.JCheckBox gravityOnOffCheckBox;
    private javax.swing.JTextField gravityTextField;
    private javax.swing.JTextField iterationsTextField;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JList jList1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane3;
    private javax.swing.JTextField objectAngleTextField;
    private javax.swing.JTextField objectKineticForceTextField;
    private javax.swing.JTextField objectKineticForceTextField1;
    private javax.swing.JTextField objectMassTextField;
    private javax.swing.JTextField objectMassTextField1;
    private javax.swing.JTextField objectPositionTextField;
    private javax.swing.JTextField objectPositionTextField1;
    private javax.swing.JComboBox objectPresetComboBox;
    private javax.swing.JTextField objectStaticForceTextField;
    private javax.swing.JTextField objectStaticForceTextField1;
    private javax.swing.JTextField objectVelocityTextField;
    private javax.swing.JTextField objectVelocityTextField1;
    private javax.swing.JTextField planeNormalTextField;
    private javax.swing.JButton resetRK4;
    private javax.swing.JMenuItem saveRK4ResultsButton;
    private javax.swing.JCheckBox showRK4StepsCheckBox;
    // End of variables declaration//GEN-END:variables
}
