package Vista.Subsistemas;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;

import Datos.seg.camState;
import Observer.Observer;
import Transfer.seg.TListCam;
import Utils.NotifyData;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Pebbles()
 */
public class seg_view_genCam extends javax.swing.JFrame implements Observer {
	
	    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		private javax.swing.JLabel jLabel1;
	    private javax.swing.JLabel jLabel2;
	    private javax.swing.JLabel jLabel3;
	    private javax.swing.JLabel jLabel4;
	    private javax.swing.JLabel jLabel5;
	    private javax.swing.JLabel jLabel6;
	    private javax.swing.JPanel jPanel1;
	    private javax.swing.JPanel jPanel2;
	    private javax.swing.JPanel jPanel3;
	    private javax.swing.JPanel jPanel4;
	    private javax.swing.JPanel jPanel5;
	    private javax.swing.JPanel jPanel6;
	    
	    private List<String> pathCamList = new ArrayList<String>();
	    String genericCamPath = "Iconos/";
    /**
     * 
     * Creates new form frame1
     */
    public seg_view_genCam() {
        initComponents();
        compIn();
        pathCamList.add("/Iconos/Apagada.gif");//a�adir paths del resto
        
    }
public void compIn(){
    jLabel1.addMouseListener(new MouseAdapter(){
      public void mouseClicked(java.awt.event.MouseEvent evt) {
    	  System.out.print("clackS");
    	  
    	  java.awt.EventQueue.invokeLater(new Runnable() {
              public void run() {
            	  seg_view_framecamsingle fCam = new seg_view_framecamsingle("kaka");
            	  fCam.setVisible(true);
              }
          });
               
            }
    
    });
  // jPanel1.
    
    
    
}
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
    	
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        
        getContentPane().setPreferredSize(new Dimension(800,500));
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new java.awt.GridLayout(2, 2));//revisaaaaaaar 2x3 auto 
        //060520
   /*     for (JPanel p: panList) {
        //	panCreate(p, null, pathCamList.get(panList.indexOf(p)));
        	panCreate(p, null, genericCamPath + "Apagada" + ".gif");
        }*/
/////////////
        jLabel1.setIcon(new javax.swing.ImageIcon("/Iconos/Apagada.gif")); // NOI18N
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel1);
/////////////////////
        jPanel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel2MouseClicked(evt);
            }
        });

        jLabel2.setIcon(new javax.swing.ImageIcon("/Iconos/SalEmerg.gif")); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel2);

        jLabel3.setIcon(new javax.swing.ImageIcon("/Iconos/SalEmerg.gif")); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel3);

        jLabel4.setIcon(new javax.swing.ImageIcon("/Iconos/SalEmerg.gif")); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel4);
        jLabel5.setIcon(new javax.swing.ImageIcon("/Iconos/SalEmerg.gif")); // NOI18N

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel5);

        jLabel6.setIcon(new javax.swing.ImageIcon("/Iconos/SalEmerg.gif")); // NOI18N

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel6);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        
        
 
        
    }//GEN-LAST:event_jLabel1MouseClicked

    
    private void jPanel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MouseClicked
         String sese = null;
		seg_view_framecamsingle camAmp = new seg_view_framecamsingle(sese); 
    }//GEN-LAST:event_jPanel2MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
 
    // End of variables declaration//GEN-END:variables
    
    private JLabel panCreate(JPanel pan, Object objecte, String dirImg, Color c) {
    		
          JLabel labele= new JLabel();//"C:\\Users\\Pebbles()\\Documents\\NetBeansProjects\\mavenproject1\\src\\main\\java\\cctvoffgif1.gif"
          labele.setIcon(new javax.swing.ImageIcon(dirImg));
          labele.addMouseListener(new java.awt.event.MouseAdapter() {
              public void mouseClicked(java.awt.event.MouseEvent evt) {
                  jLabel1MouseClicked(evt);
              }
          });

          javax.swing.GroupLayout panLayout = new javax.swing.GroupLayout(pan);
          pan.setLayout(panLayout);
          panLayout.setHorizontalGroup(
        		  panLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addComponent(labele, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          );
          panLayout.setVerticalGroup(
        		  panLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addComponent(labele, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          );
          //pan.re todo setResizeable
          if(c != null) {
        	  labele.setBackground(c);
        	  pan.setBackground(c);
        	  labele.setVisible(false);
        	  }
          getContentPane().add(pan);
         
          
          return labele;
    }
    
    
    
	@Override
	public void update(NotifyData data) {
		
		switch (data.getN()) {
		
		case CAM_REFRESH:
			TListCam listCams = (TListCam) data.getData();
	
				this.getContentPane().removeAll();
		
		for(int i = 0; i < listCams.getSize() - 1; i++) {
			
		JPanel panAux = new JPanel();
		String path = genericCamPath;
		String extension = ".gif";
		
		if(listCams.getTCam(i).isOnRepairs() == true)
			path += "Apagada";
		else
			path += listCams.getTCam(i).getId_cam();
		path += extension;
		
		if(listCams.getTCam(i).getCamStat()== camState.off) 
			panCreate(panAux, null, "/Iconos/cOff.gif", null );//caso de que este off"
				
		else{//si esta on
			panCreate(panAux, null, path, null);
		}
}
		
		break;
		default:
			break;
    
}


	}
	}
