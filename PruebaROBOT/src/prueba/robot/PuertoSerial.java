/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prueba.robot;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.StringTokenizer;
import java.util.TooManyListenersException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.videoio.VideoCapture;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.StringTokenizer;
import java.util.TooManyListenersException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.border.BevelBorder;


import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.Rect;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.videoio.VideoCapture;
/**
 *
 * @author laboratorio
 */
public class PuertoSerial extends javax.swing.JFrame implements Runnable,SerialPortEventListener {

    /**
     * Creates new form PuertoSerial
     */
     public int patrones=1;
     public PuertoSerial() {
        initComponents();
        this.setLocationRelativeTo(null);      
    }
    
    class DaemonThread implements Runnable
    {
        boolean runnable = false;
        
        @Override
        public  void run()
        {
            synchronized(this)
            {
                while(runnable)
                {
                    if(webSource.grab())
                    {
                            try
                            {
                                webSource.retrieve(frame);
                                Imgcodecs.imencode(".bmp", frame, mem);
                                Image im = ImageIO.read(new ByteArrayInputStream(mem.toArray()));
                                BufferedImage buff = (BufferedImage) im;
                                Graphics g = jPanel2.getGraphics();
                                if(dibujar_rectangulos){
                                    dibujar_rectangulos(g);
                                }else{
                                    g.drawImage(buff, 10, 10, jPanel2.getWidth() - 10, jPanel2.getHeight() - 10, 0, 0, buff.getWidth(), buff.getHeight(), null);

                                }
                                
                             }
                             catch(Exception ex)
                             {
                                System.out.println(ex);
                             }
                    }
                }
            }
         }
   }
    
    public void detectar_rectangulos(){        
        Imgcodecs.imwrite("vacio2.jpg", frame);
        Mat template = Imgcodecs.imread("vacio2.jpg",Imgcodecs.CV_LOAD_IMAGE_COLOR);
        for(int i=1; i<5; i++){            
            Mat patron = Imgcodecs.imread(i+"enBandeja.jpg",Imgcodecs.CV_LOAD_IMAGE_COLOR);
            Core.MinMaxLocResult matched = reconocer_patron(template, i+"enBandeja.jpg");
            
            if(matched.maxVal > 0.8){ // si es que efectivamente encontró el patrón
                System.out.println("PATRON ENCONTRADO: "+i);
                if(i==1){
                    esta_uno = true;
                    System.out.println("SE ENCONTRO EL UNO");
                }else if(i==2){
                    esta_dos = true;
                }else if(i==3){
                    esta_tres = true;
                }       
                else if(i==4){
                    esta_cuatro = true;
                }
            } 
        }        
    }
    public void no_estan(){
        esta_uno = false;
        esta_dos = false;
        esta_tres = false;
        esta_cuatro = false;                
    }
    
    public void dibujar_rectangulos(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(10));
        g2.setColor(Color.RED);
        if(esta_uno){             
                g2.drawRect((int)uno.x, (int)uno.y, 215, 100);
        }
        if(esta_dos){
                g2.drawRect((int)dos.x, (int)dos.y, 215, 100);
        }
        if(esta_tres){
                g2.drawRect((int)tres.x, (int)tres.y, 200, 100);
        }
        if(esta_cuatro){
                g2.drawRect((int)cuatro.x, (int)cuatro.y, 200, 100);
        }
    }
    
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFileChooser1 = new javax.swing.JFileChooser();
        jCheckBoxMenuItem1 = new javax.swing.JCheckBoxMenuItem();
        jFileChooser2 = new javax.swing.JFileChooser();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        comenzar = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        inicia_camara = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        detener_camara = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        desconectar = new javax.swing.JButton();
        conectar = new javax.swing.JButton();
        enviar = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jPanel6 = new javax.swing.JPanel();
        detener_robot = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        capturar_imagen = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        jCheckBoxMenuItem1.setSelected(true);
        jCheckBoxMenuItem1.setText("jCheckBoxMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1920, 1080));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 540, 1000, 40));
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(34, 387, -1, -1));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1385, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(2225, 11, -1, 1385));

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 10));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1320, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 490, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 1340, 510));

        comenzar.setFont(new java.awt.Font("Century Gothic", 0, 24)); // NOI18N
        comenzar.setText("Comenzar");
        comenzar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comenzarActionPerformed(evt);
            }
        });
        getContentPane().add(comenzar, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 920, 160, 52));
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1550, 120, -1, -1));
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 900, -1, -1));
        getContentPane().add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 970, -1, -1));
        getContentPane().add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 1050, 40, 20));

        jLabel3.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1150, 620, 160, 20));

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));

        inicia_camara.setFont(new java.awt.Font("Century Gothic", 0, 24)); // NOI18N
        inicia_camara.setText("Iniciar cámara");
        inicia_camara.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inicia_camaraActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Century Gothic", 0, 30)); // NOI18N
        jLabel15.setText("Cámara");

        detener_camara.setFont(new java.awt.Font("Century Gothic", 0, 24)); // NOI18N
        detener_camara.setText("Detener cámara");
        detener_camara.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                detener_camaraActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(76, 76, 76)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addGap(53, 53, 53))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(inicia_camara, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(detener_camara, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(78, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel15)
                .addGap(27, 27, 27)
                .addComponent(inicia_camara, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(detener_camara, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(28, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1410, 580, 390, 240));

        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));

        jLabel9.setFont(new java.awt.Font("Century Gothic", 0, 24)); // NOI18N
        jLabel9.setText(".");

        jLabel8.setFont(new java.awt.Font("Century Gothic", 0, 24)); // NOI18N
        jLabel8.setText(".");

        jLabel7.setFont(new java.awt.Font("Century Gothic", 0, 24)); // NOI18N
        jLabel7.setText(".");

        jLabel6.setFont(new java.awt.Font("Century Gothic", 0, 24)); // NOI18N
        jLabel6.setText(".");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 621, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 621, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(31, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                .addGap(12, 12, 12)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 580, 670, 210));

        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));

        jLabel16.setFont(new java.awt.Font("Times New Roman", 0, 30)); // NOI18N
        jLabel16.setText("SCORBOT");

        desconectar.setFont(new java.awt.Font("Century Gothic", 0, 24)); // NOI18N
        desconectar.setText("Desconectar");
        desconectar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                desconectarActionPerformed(evt);
            }
        });

        conectar.setFont(new java.awt.Font("Century Gothic", 0, 24)); // NOI18N
        conectar.setText("Conectar");
        conectar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                conectarActionPerformed(evt);
            }
        });

        enviar.setFont(new java.awt.Font("Century Gothic", 0, 24)); // NOI18N
        enviar.setText("Enviar");
        enviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enviarActionPerformed(evt);
            }
        });

        jTextField1.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N

        jTextArea2.setEditable(false);
        jTextArea2.setColumns(20);
        jTextArea2.setRows(5);
        jScrollPane2.setViewportView(jTextArea2);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(102, 102, 102)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(conectar, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(35, 35, 35)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(desconectar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(enviar, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 55, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(243, 243, 243)
                .addComponent(jLabel16)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel16)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(54, 54, 54)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(desconectar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(conectar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(enviar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 580, 640, 460));

        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));

        detener_robot.setFont(new java.awt.Font("Century Gothic", 0, 24)); // NOI18N
        detener_robot.setText("Detener");
        detener_robot.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                detener_robotActionPerformed(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Times New Roman", 0, 30)); // NOI18N
        jLabel17.setText("Sistema");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(detener_robot, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(56, 56, 56))
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(185, 185, 185)
                .addComponent(jLabel17)
                .addContainerGap(197, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                .addComponent(detener_robot, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46))
        );

        getContentPane().add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 850, 480, 170));

        jTextArea1.setEditable(false);
        jTextArea1.setBackground(new java.awt.Color(240, 240, 240));
        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        jTextArea1.setRows(5);
        jTextArea1.setText("                Reconocer y ordenar\n\n                 Desarrollado por\n\n               Rodrigo Carvajal S.\n               Felipe Jiménez D.\n               Nicolás Millar M.\n");
        jTextArea1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        jScrollPane1.setViewportView(jTextArea1);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1400, 180, 400, 320));
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(1220, 50, -1, -1));

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/logo.png"))); // NOI18N
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(1490, 30, 200, 130));

        jLabel10.setText("jLabel10");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(1500, 200, -1, -1));

        jLabel13.setText("jLabel13");
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(1500, 200, -1, -1));

        jLabel18.setText("Reconocer y ordenar");
        getContentPane().add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(1510, 200, 190, 30));

        capturar_imagen.setFont(new java.awt.Font("Century Gothic", 0, 24)); // NOI18N
        capturar_imagen.setText("Crear patron");
        capturar_imagen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                capturar_imagenActionPerformed(evt);
            }
        });
        getContentPane().add(capturar_imagen, new org.netbeans.lib.awtextra.AbsoluteConstraints(1490, 910, 230, 50));

        jLabel19.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        getContentPane().add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(1410, 840, 390, 200));

        jLabel21.setFont(new java.awt.Font("Century Gothic", 0, 30)); // NOI18N
        jLabel21.setText("Patrones");
        getContentPane().add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(1540, 860, -1, -1));

        jButton1.setFont(new java.awt.Font("Century Gothic", 0, 24)); // NOI18N
        jButton1.setText("Reconocer patrones");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1460, 980, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void comenzarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comenzarActionPerformed
        // BOTÓN COMENZAR
        detener_robot.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.GREEN, Color.GREEN));
        PORT_NAME = "COM1";//(String)jComboBox1.getSelectedItem();
        CommPortIdentifier portID = null;
        Enumeration portEnum = CommPortIdentifier.getPortIdentifiers();
        while (portEnum.hasMoreElements()) {
            CommPortIdentifier ourrPortID = (CommPortIdentifier) portEnum.nextElement();
            if (PORT_NAME.equals(ourrPortID.getName())) {
                portID = ourrPortID;
                JOptionPane.showMessageDialog(null, "PUERTO " + PORT_NAME + " CONECTADO");
                break;
            }
        }
        try {

            serialport = (SerialPort) portID.open(this.getClass().getName(), TIME_OUT);
            serialport.setSerialPortParams(DATA_RATE, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
            tLectura = new Thread(this);
            tLectura.setName("Thread lectura");
            tLectura.start();
            Output = serialport.getOutputStream();
            Input = serialport.getInputStream();
            try {
                serialport.addEventListener(this);
            } catch (TooManyListenersException e) {
                System.out.println(e);
            }
            serialport.notifyOnDataAvailable(true);
        } catch (Exception e) {
            System.out.println(e);
        }
        // TODO add your handling code here:
        webSource = new VideoCapture(0); //Inicia la camara default
        if(myThread==null && thread_camara==null){
            myThread = new DaemonThread();
            thread_camara = new Thread(myThread);
            thread_camara.setName("THREAD CAMARA");
            //thread_camara.setDaemon(false);
            myThread.runnable = true;
            thread_camara.start();
        }       

        try {
            enviar_comando("RUN SCAN");        // TODO add your handling code here:
        } catch (IOException ex) {
            Logger.getLogger(PuertoSerial.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_comenzarActionPerformed

    private void detener_robotActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_detener_robotActionPerformed
        // TODO add your handling code here:
        try{
            System.out.println("Desconectando");
            if(serialport!=null){
                System.out.println("Serial por no nulo");
                enviar_comando("move 0");
                serialport.removeEventListener();
                serialport.close();
                jTextArea2.setText("");
                JOptionPane.showMessageDialog(null,"PUERTO "+PORT_NAME+"DESCONECTADO");
                
                myThread.runnable = false;
                
                System.out.println("Runnable de mythread: " + myThread.runnable);
                myThread = null;
                //thread_camara.setDaemon(false);
                thread_camara.interrupt();
                webSource.release();
                
                tLectura.interrupt();
                System.out.println("camara ESTA INTERRUMPIDO: " + thread_camara.isInterrupted() + "   Lectura esta interrumpido: " + tLectura.isInterrupted());
                System.out.println("camara ESTA vivo: " + thread_camara.isAlive() + "   Lectura esta interrumpido: " + tLectura.isAlive());
                System.out.println("camara ES daemon: " + thread_camara.isDaemon() + "   Lectura esta interrumpido: " + tLectura.isDaemon());
                
                detener_robot.setBorder(BorderFactory.createEmptyBorder());
                
            }
        }
        catch(Exception e){
        }
    }//GEN-LAST:event_detener_robotActionPerformed

    private void enviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enviarActionPerformed
        // Botón enviar
        String contenido = new String(jTextField1.getText());
        StringTokenizer st = new StringTokenizer(contenido,"\n");
        byte[] bufferLectura = new byte[20];
        int nBytes;
        try {
                while(Input.available()>0){
                    nBytes = Input.read(bufferLectura);
                    System.out.println(nBytes);
                    jTextArea2.append(new String(bufferLectura) + "\n"); 
                    //jTextArea2.append(new String(bufferLectura) + "\n");
                }
                
            } catch (IOException ex) {
                Logger.getLogger(PuertoSerial.class.getName()).log(Level.SEVERE, null, ex);
            }
        while(st.hasMoreTokens()){
            System.out.println(contenido);
            String linea = st.nextToken();
                             
            try{
                
                enviar_comando(linea);
            }catch (IOException ex){
                Logger.getLogger(PuertoSerial.class.getName()).log(Level.SEVERE,null,ex);
            }
        }
        jTextField1.setText("");
    }//GEN-LAST:event_enviarActionPerformed

    private void capturar_imagenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_capturar_imagenActionPerformed
        conectarActionPerformed(evt);
        webSource = new VideoCapture(0); //Inicia la camara default
        if(myThread==null && thread_camara==null){
            myThread = new DaemonThread();
            thread_camara = new Thread(myThread);
            thread_camara.setName("THREAD CAMARA");
            //thread_camara.setDaemon(false);
            myThread.runnable = true;
            thread_camara.start();
        }
        detener_camara.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.GREEN, Color.GREEN));
        try {
            enviar_comando("run scan2");
        } catch (IOException ex) {
            Logger.getLogger(PuertoSerial.class.getName()).log(Level.SEVERE, null, ex);
        }
        esperar(7);
        patrones=this.contar();
        Imgcodecs.imwrite("patron"+patrones+".jpg", frame);
        CreaP p;
        try {
            p = new CreaP(this, patrones);
            p.setVisible(true);
            patrones=p.c_patron();
        } catch (IOException ex) {
            Logger.getLogger(PuertoSerial.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_capturar_imagenActionPerformed

    private void conectarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_conectarActionPerformed
        // Botón conectar
        PORT_NAME = "COM1";
        CommPortIdentifier portID = null;
        Enumeration portEnum = CommPortIdentifier.getPortIdentifiers();
        
        while (portEnum.hasMoreElements()){
            CommPortIdentifier ourrPortID = (CommPortIdentifier) portEnum.nextElement();
            if (PORT_NAME.equals(ourrPortID.getName())){
                portID=ourrPortID;
                JOptionPane.showMessageDialog(null,"PUERTO "+PORT_NAME+" CONECTADO");
                break;
            }
        }
        
        try{
           serialport = (SerialPort)portID.open(this.getClass().getName(),TIME_OUT);
           serialport.setSerialPortParams(DATA_RATE,SerialPort.DATABITS_8,SerialPort.STOPBITS_1,SerialPort.PARITY_NONE);
           Thread tLectura = new Thread(this);
           tLectura.start();
           Output = serialport.getOutputStream();
           Input = serialport.getInputStream();
           try{
               serialport.addEventListener(this);
           } catch (TooManyListenersException e){
               System.out.println(e);
           }
        } catch (Exception e){
            System.out.println(e);
        }   
    }//GEN-LAST:event_conectarActionPerformed


    
    private void desconectarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_desconectarActionPerformed
                //Botón desconectar
        try{
            if(serialport!=null){
                enviar_comando("move 0");
                serialport.removeEventListener();
                serialport.close();
                jTextArea2.setText("");
                JOptionPane.showMessageDialog(null,"PUERTO "+PORT_NAME+"DESCONECTADO");
            }
        }
        catch(Exception e){

        }
    }//GEN-LAST:event_desconectarActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        webSource.release();
    }//GEN-LAST:event_formWindowClosed

    private void inicia_camaraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inicia_camaraActionPerformed
        //Botón iniciar cámara
         webSource = new VideoCapture(0);
        myThread = new DaemonThread();
        thread_camara = new Thread(myThread);
        myThread.runnable = true;
        thread_camara.start();
        //detener_camara.setBorder(BorderFactory.createLineBorder(Color.GREEN, 2));
        detener_camara.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.GREEN, Color.GREEN));
    }//GEN-LAST:event_inicia_camaraActionPerformed

    private void detener_camaraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_detener_camaraActionPerformed
        webSource.release();
        JOptionPane.showMessageDialog(null,"Cámara desconectada!");
        detener_camara.setBorder(BorderFactory.createEmptyBorder());
    }//GEN-LAST:event_detener_camaraActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        conectarActionPerformed(evt);
        webSource = new VideoCapture(0); //Inicia la camara default
        if(myThread==null && thread_camara==null){
            myThread = new DaemonThread();
            thread_camara = new Thread(myThread);
            thread_camara.setName("THREAD CAMARA");
            //thread_camara.setDaemon(false);
            myThread.runnable = true;
            thread_camara.start();
        }
        detener_camara.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.GREEN, Color.GREEN));
        try {
            enviar_comando("run scan2");
        } catch (IOException ex) {
            Logger.getLogger(PuertoSerial.class.getName()).log(Level.SEVERE, null, ex);
        }
        esperar(7);
        patrones=this.contar();
        Imgcodecs.imwrite("origen.jpg", frame);
        ReconocerP reconocer=new ReconocerP("origen.jpg",this);
        reconocer.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

     public static void esperar(int segundos){
        try {
            Thread.sleep(segundos * 1000);
         } catch (Exception e) {
            System.out.println(e);
         }
    }  
   
    public void enviar_comando(String comando) throws IOException{
        cadena = comando;
        pos = 0;
        if(serialport!=null){
            while(cadena!=null){
                try{
                    Output.write(leer());
                }catch(IOException a){
                    a.printStackTrace();
                }
            }
        }
        else{
            System.err.println("Puerto es nulo(no existe)");
        }
    }
    
    private int leer(){
        if(cadena!=null && pos==cadena.length()){
            cadena = null;
            pos=0;
            try{
                Thread.sleep(100);
            }catch(InterruptedException in){
                in.printStackTrace();
            }
        
            return caracterfinal;
        }
        return cadena.charAt(pos++);
    }

    @Override
    public  void run()
    {
        
            while(!shutdown){
                //System.out.println("Entra hilo");
                //Thread.sleep(2000);
            }            
            System.out.println("Terminó");           
        
        
     }
    
    @Override
    public void serialEvent(SerialPortEvent spe){
        System.out.println("Entra evento serial/n");
        switch(spe.getEventType()){
            case SerialPortEvent.BI:
            case SerialPortEvent.OE:
            case SerialPortEvent.FE:
            case SerialPortEvent.PE:
            case SerialPortEvent.CD:
            case SerialPortEvent.CTS:
            case SerialPortEvent.DSR:
            case SerialPortEvent.RI:
            case SerialPortEvent.OUTPUT_BUFFER_EMPTY:
                 break;
            case SerialPortEvent.DATA_AVAILABLE:
                byte[] bufferLectura = new byte[20];
                try{
                    while(Input.available()>0){
                        int nBytes = Input.read(bufferLectura);
                    }
                   jTextArea2.append(new String(bufferLectura) + "\n");
                   String mensaje = new String(bufferLectura);
                   System.out.println(mensaje);
                   if (mensaje.contains("saok")){
                        dibujar_rectangulos = false;
                        no_estan();
                        enviar_comando("RUN SCAN");}
                   if (mensaje.contains("dibuja")){
                       System.out.println("INTENTA DIBUJAR");
                       detectar_rectangulos();
                       dibujar_rectangulos = true;                        
                   }
                   if (mensaje.contains("para")){
                       System.out.println("PARO DE DIBUJAR");
                       dibujar_rectangulos = false;
                       no_estan();
                   }
                   if (mensaje.contains("alok")){
                   enviar_comando("RUN TOMA");}
                   
                   if(mensaje.contains("okgo")){                        
                        try{
                            Imgcodecs.imwrite("vacio.jpg", frame); //guarda la imagen capturada 
                        }catch(Exception e){
                            System.out.println("ERROR AL TOMAR FOTO:" + e);
                        }
                        
                        Mat orig = Imgcodecs.imread("vacio.jpg",Imgcodecs.CV_LOAD_IMAGE_COLOR);
                        int pieza_encontrada = -1;                     
                        String nombre = ""; 
                        pieza_encontrada = encontrarPieza(orig);
                        System.out.println("PIEZA ENCONTRADA: "+pieza_encontrada);
                   
                        switch(pieza_encontrada){
                            case 1://UNO
                                enviar_comando("RUN UNO");
                               jLabel2.setText("El numero observado es el uno.");
                              
                                break;
                            case 2://DOS
                                enviar_comando("RUN DOS");
                                jLabel2.setText("El numero observado es el dos.");
                                break;
                            case 3://TRES
                                enviar_comando("RUN TRES");
                                jLabel2.setText("El numero observado es el tres.");
                                break;
                            case 4://CUATRO
                                enviar_comando("RUN CUATR");
                                jLabel2.setText("El numero observado es el cuatro.");
                                break;
                            case 5://UNO
                                enviar_comando("RUN RUNO");
                               jLabel2.setText("El numero observado es el uno.");                              
                                break;
                            case 6://DOS
                                enviar_comando("RUN RDOS");
                                jLabel2.setText("El numero observado es el dos.");
                                break;
                            case 7://TRES
                                enviar_comando("RUN RTRES");
                                jLabel2.setText("El numero observado es el tres.");
                                break;
                            case 8://CUATRO
                                enviar_comando("RUN RCUAT");
                                jLabel2.setText("El numero observado es el cuatro.");
                                break;
                            
                     
                            default:
                                 jLabel2.setText("El porcentaje de coincidencia es muy bajo para procesar, inserte otro numero.");
                                 int seleccion = JOptionPane.showOptionDialog(null,
                                         "Al parecer no se detecta pieza alguna, ¿Desea Continuar?",
                                         "Selector de opciones",
                                         JOptionPane.YES_NO_CANCEL_OPTION,
                                         JOptionPane.QUESTION_MESSAGE,
                                         null,  //null para icono por defecto
                                         new Object[]{ "si", "no"},  //null para YES, NO y CANCEL
                                        "opcion 1");
                                         
                                         if (seleccion !=-1){
                                             System.out.println("seleccionada opcion" + (seleccion +1));
                                             if(seleccion==0){
                                                 enviar_comando("RUN OKGO"); 
                                             }
                                             else {
                                                try{
                                            if(serialport!=null){
                                            //thread_camara.setDaemon(true);
                                            
                                            enviar_comando("move 0");
                                            System.out.println("Puerto serial cerrado");
                                            shutdown = true;
                                            detener_robot.setBorder(BorderFactory.createEmptyBorder());
                                            
                                            serialport.removeEventListener();
                                            serialport.close();
                                            webSource.release();
                                            
                                            myThread.runnable = false;
                                            jTextArea2.setText("");
                                            tLectura.interrupt();
                                            thread_camara.interrupt();
                                            
                                            JOptionPane.showMessageDialog(null,"PUERTO "+PORT_NAME+"DESCONECTADO");   
                                            
                                            //System.exit(0);
                                                            }
                                                    }
                                               catch(Exception e){
                                            }
                                             }
                                                 
                                         }
                                break;
                        }        
                    } 
                }catch(IOException e){
                    System.out.println(e);  
                }
        }
    }
    
    public int encontrarPieza(Mat foto){
        int pieza_encontrada = -1;
        double Delta = -1; 
        int maxima_coincidencia=0;
        
        for(int i=1;i<9;i++){
            Mat patron = Imgcodecs.imread("patron"+i+".jpg",Imgcodecs.CV_LOAD_IMAGE_COLOR);
            int result_rows = foto.rows()-patron.rows() + 1;
            int result_cols = foto.cols()-patron.cols() + 1;
            Mat result = new Mat(result_rows,result_cols,CvType.CV_32FC1);

            //Buscando los emparejamientos
            
            Imgproc.matchTemplate(foto, patron, result, Imgproc.TM_CCOEFF_NORMED); 
   
            // / Encontrando el mejor emparejamiento con  minMaxLoc
            Core.MinMaxLocResult mmr = Core.minMaxLoc(result);                            

            //Imprimir en consola valores del emparejamiento escogido
            // Fija si existe match o no con la imagen y el patron
            System.out.println("maxVal: "+mmr.maxVal+", i: "+i);
           
            int aux = (int) (mmr.maxVal*100);
            
            if(i==1){
                jLabel6.setText("El porcentaje para el patron 1 es: "+aux+"%" );
            }if(i==2){
                jLabel7.setText("El porcentaje para el patron 2 es: "+aux+"%" );
            }if(i==3){
                jLabel8.setText("El porcentaje para el patron 3 es: "+aux+"%" );
            }if(i==4){
                jLabel9.setText("El porcentaje para el patron 4 es: "+aux+"%" );
            }   
            if(maxima_coincidencia < 80 && i>4){
                System.out.println("Hubo una coincidencia menor a 80: " + maxima_coincidencia);
                if(i==5){ 
                jLabel6.setText("El porcentaje para el patron 1 es: "+aux+"%" );    
                }if(i==6){
                jLabel7.setText("El porcentaje para el patron 2 es: "+aux+"%" );
                }if(i==7){
                jLabel8.setText("El porcentaje para el patron 3 es: "+aux+"%" );
                System.out.println("El porcentaje para el patron 3 es: "+aux+"%");
                }if(i==8){
                jLabel9.setText("El porcentaje para el patron 4 es: "+aux+"%" );
                }            
            }  
            if(aux>maxima_coincidencia) maxima_coincidencia = aux;
            if (mmr.maxVal > Delta){
                Delta = mmr.maxVal;                
                pieza_encontrada=i;               
            }
        }                
        if(Delta < 0.8){
            pieza_encontrada = -1;                                     
        }
        System.out.println("PIEZA: " + pieza_encontrada);
        return pieza_encontrada;
    }
    
    // devuelve un número tal que si el valor es mayor 
    public Core.MinMaxLocResult reconocer_patron(Mat plantilla, String patron_a_reconocer){
        Mat patron = Imgcodecs.imread(patron_a_reconocer,Imgcodecs.CV_LOAD_IMAGE_COLOR);
        int result_rows = plantilla.rows()-patron.rows() + 1;
        int result_cols = plantilla.cols()-patron.cols() + 1;
        Mat result = new Mat(result_rows,result_cols,CvType.CV_32FC1);

        //Buscando los emparejamientos
        Imgproc.matchTemplate(plantilla, patron, result, Imgproc.TM_CCOEFF_NORMED);                            
   
        // / Encontrando el mejor emparejamiento con  minMaxLoc
        Core.MinMaxLocResult mmr = Core.minMaxLoc(result); 
        return mmr;          
    }
    
    /*
    public void reconocer_patron(Mat foto){
        Mat patron = Imgcodecs.imread("banana.jpg",Imgcodecs.CV_LOAD_IMAGE_COLOR);
        int result_rows = foto.rows()-patron.rows() + 1;
        int result_cols = foto.cols()-patron.cols() + 1;
        Mat result = new Mat(result_rows,result_cols,CvType.CV_32FC1);

        //Buscando los emparejamientos
        Imgproc.matchTemplate(foto, patron, result, Imgproc.TM_CCOEFF_NORMED);                            
   
        // / Encontrando el mejor emparejamiento con  minMaxLoc
        Core.MinMaxLocResult mmr = Core.minMaxLoc(result); 
        if(mmr.maxVal > 0.8){
            jLabel2.setText("El nuevo patrón banana.jpg ha sido reconocido");
        }
    }
    */
    
    public int contar(){
        File file=new File("patron"+patrones+".jpg");
        int cantidad=2;
        while(file.exists()){
            file=new File("patron"+cantidad+".jpg");
            cantidad++;
        }
        return cantidad-1;
    }
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PuertoSerial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PuertoSerial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PuertoSerial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PuertoSerial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        java.awt.EventQueue.invokeLater(new Runnable() {
                public void run() {                
                     new PuertoSerial().setVisible(true);                   
                }
        });
    }
    private DaemonThread myThread = null;
    VideoCapture webSource = null;
    Mat pic = new Mat();
    Mat frame = new Mat();
    MatOfByte mem = new MatOfByte();
    Thread tLectura;
    private OutputStream Output = null;
    private InputStream Input = null;    
    SerialPort serialport;
    private String PORT_NAME;
    private static final int TIME_OUT=2000;
    private static final int DATA_RATE = 9600;
    private String mensaje;
    private final Character caracterfinal = '\r';
    private String cadena = null;
    private int pos = 0;
    private Thread thread_camara;
    JFrame aviso = new JFrame();
    volatile boolean shutdown = false;
    private int x1, x2, y1, y2;
    private boolean dibujar_rectangulos = false;
    private boolean esta_uno = false, esta_dos = false, esta_tres = false, esta_cuatro = false;
    private Point uno = new Point(805, 107), dos = new Point(840, 333), tres = new Point(316, 138), cuatro = new Point(354, 340);
    // uno: (805, 107), (1012, 95) ///dos: (840, 333), (1057, 324) /// tres: (316, 138), (516, 130)/// cuatro: (354, 340), (547, 332)
    
    //private Rectangle uno, dos, tres, cuatro;
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton capturar_imagen;
    private javax.swing.JButton comenzar;
    private javax.swing.JButton conectar;
    private javax.swing.JButton desconectar;
    private javax.swing.JButton detener_camara;
    private javax.swing.JButton detener_robot;
    private javax.swing.JButton enviar;
    private javax.swing.JButton inicia_camara;
    private javax.swing.JButton jButton1;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem1;
    private javax.swing.JFileChooser jFileChooser1;
    private javax.swing.JFileChooser jFileChooser2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
