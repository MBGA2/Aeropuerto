package Utils;
import java.awt.*;  
//In place of star we use Color,Dimension,Font,Graphics,event.ActionEvent,event.ActionListner.   
//In place of star we use Calendar.   
import javax.swing.*;  
//In place of star we use Jframe.JPane1,WindowConstants.  
import java.awt.event.ActionListener;
import java.sql.Timestamp;

import javax.swing.Timer;

import Controladores.main_controller;

import java.awt.event.ActionEvent; 

public class SimpleDigitalClock extends JPanel {  
        /**
    *
    */
    private static final long serialVersionUID = 1L;
    String stringTime;
        int hour, minute, second;  
        Timestamp rite;
        int increase = 0;
        String aHour = "";  
        String bMinute = "";  
        String cSecond = "";  
        public Timestamp getDate() {
        	return rite;
        }
        public void setStringTime(String abc) {  
            this.stringTime = abc;  
        }  
        public int Number(int a, int b) {  
            return (a <= b) ? a : b;  
        }  
        public SimpleDigitalClock(main_controller cont) {  
        	rite = cont.getAeropuerto().getTime();
            Timer t = new Timer(1000, new ActionListener() {  
                public void actionPerformed(ActionEvent e) {  
                	//rite = new Date(rite.getTime()+1000+60000);
                	cont.getAeropuerto().setTime(new Timestamp(cont.getAeropuerto().getTime().getTime()+1000*60*3));
                	rite = cont.getAeropuerto().getTime();
                	cont.getAeropuerto().notifyAllO(new NotifyData(NTYPE.REFRESH,rite));
                	repaint();  
                }  
            });  
            t.start();  
        }  
        @SuppressWarnings("deprecation")
		@Override  
        public void paintComponent(Graphics v) {  
            super.paintComponent(v);  
            hour = rite.getHours(); 
            minute = rite.getMinutes();  
            second = rite.getSeconds();  
            if (hour < 10) {  
                this.aHour = "0";  
            }  
            if (hour >= 10) {  
                this.aHour = "";  
            }  
            if (minute < 10) {  
                this.bMinute = "0";  
            }  
            if (minute >= 10) {  
                this.bMinute = "";  
            }  
            if (second < 10) {  
                this.cSecond = "0";  
            }  
            if (second >= 10) {  
                this.cSecond = "";  
            }  
            setStringTime(aHour + hour + ":" + bMinute + minute + ":" + cSecond + second);  
            v.setColor(Color.BLACK);  
            int length = Number(this.getWidth(), this.getHeight());  
            Font Font1 = new Font("SansSerif", Font.PLAIN, length / 5);  
            v.setFont(Font1);  
            v.drawString(stringTime, (int) length / 6, length / 2);  
        }  
        @Override  
        public Dimension getPreferredSize() {  
            return new Dimension(200, 200);  
        }  
    }  
