//SHOOTING GAME HAVING REDUCED FLICKER WITH CHEAT CODE: "polo" OR "POLO"
import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class game extends Applet implements KeyListener,Runnable,MouseListener,MouseMotionListener
{
boolean flyflag=false,startgame=false,cheat=false;
int xbase=375,xfly=0,xgoli[],ygoli[],numgoli,misses=0,hits=0,t,speed=1,incrgoli=12,xgoliold=0,ygoliold=0;
Thread t1;
String cheatarr;
public void init()
  {
   setBackground(Color.white);
   xgoli=new int[12];
   ygoli=new int[12];
   for(t=0;t<=11;t++)
     {
      xgoli[t]=0;
      ygoli[t]=0;
     }
   addKeyListener(this);
   addMouseListener(this);
   addMouseMotionListener(this);
   t1=new Thread(this);
   t1.start();
  }
public void run()
  {
   for(;;)
     {
      if(startgame==true)
        {
         if(flyflag==false)
            xfly+=5;
         else
            xfly-=5;
         if(xfly<=0 && flyflag==true)
           {
            flyflag=false;
            xfly=0;
           }
         if(xfly>=690 && flyflag==false)
           {
            flyflag=true;
            xfly=690;
           }
         if(numgoli>0)
            for(t=numgoli-1;t>=0;t--)
              {
               ygoli[t]-=incrgoli;
               if(cheat==false)
                  repaint(xgoli[t]-7,ygoli[t]-10,14,40);
              }
         if(ygoli[0]>=25 && ygoli[0]<=55)
           {
            xgoliold=xgoli[0];
            ygoliold=ygoli[0];
            if((xgoli[0]>=xfly-15 && xgoli[0]<=xfly+75) || cheat==true)
              {
               play(getCodeBase(),"hit.au");
               hits++;
              }
            else
               misses++;
            for(t=0;t<=numgoli;t++)
              {
               xgoli[t]=xgoli[t+1];
               ygoli[t]=ygoli[t+1];
              }
            numgoli--;
            repaint(5,0,740,10);
            repaint(xgoliold-7,ygoliold-10,14,40);
           }
         if(cheat==true)
           {
            for(t=0;t<numgoli && ygoli[t]<200;t++)
              {
               if(xgoli[t]<=xfly+30)
                  if((xfly+30-xgoli[t])>30)
                     xgoli[t]+=60;
                  else
                     if((xfly+30-xgoli[t])>20)
                        xgoli[t]+=20;
                     else
                        if((xfly+30-xgoli[t])>10)
                           xgoli[t]+=10;
                        else
                           xgoli[t]+=5;
               else
                  if((xgoli[t]-xfly+30)>30)
                     xgoli[t]-=60;
                  else
                     if((xgoli[t]-xfly+30)>20)
                        xgoli[t]-=20;
                     else
                        if((xgoli[t]-xfly+30)>10)
                           xgoli[t]-=10;
                        else
                           xgoli[t]-=5;
              }
            repaint(0,55,700,325);
           }
         try
           {
            Thread.sleep(25*(speed+1));
           }
         catch(Exception e){}
         repaint(xfly-5,25,72,30);
        }
     }
  }
public void paint(Graphics g)
  {
   if(startgame==false)
      g.drawString("CLICK OR PRESS SPACE TO START GAME",250,200);
   else
     {
      g.setColor(Color.blue);
      g.fillRect(xbase-15,390,30,10);
      g.fillRect(xbase-5,380,10,10);
      g.setColor(Color.red);
      g.drawOval(xfly,33,60,14);
      g.fillOval(xfly+15,25,30,30);
      g.setColor(Color.orange);
      for(t=numgoli-1;t>=0;t--)
        {
         g.fillRect(xgoli[t]-5,ygoli[t],10,10);
         g.fillOval(xgoli[t]-5,ygoli[t]-5,10,10);
        }
      g.setColor(Color.black);
      if(cheat==true)
         showStatus("GAME DESIGNED BY:- NISHANT SINGH (CHEAT MODE)");
      else
         showStatus("GAME DESIGNED BY:- NISHANT SINGH");
      g.drawString("SCORE: "+(2*hits-misses)*50,10,10);
      g.drawString("HITS: "+hits,350,10);
      g.drawString("MISSES: "+misses,650,10);
     }
  }
public void keyPressed(KeyEvent ke)
  {
   int code=ke.getKeyCode();
   if(code==KeyEvent.VK_LEFT && xbase>=15)
     {
      xbase-=8;
      repaint(xbase-35,380,70,20);
     }
   if(code==KeyEvent.VK_RIGHT && xbase<=735)
     {
      xbase+=8;
      repaint(xbase-35,380,70,20);
     }
   if(code==KeyEvent.VK_CONTROL && numgoli<10)
     {
      if(numgoli==0)
        {
         xgoli[numgoli]=xbase;
         ygoli[numgoli]=370;
         numgoli++;
        }
      else
         if(ygoli[numgoli-1]<=350)
           {
            xgoli[numgoli]=xbase;
            ygoli[numgoli]=370;
            numgoli++;
           }
      play(getCodeBase(),"fire.au");
     }
   if(code==KeyEvent.VK_UP && speed>0)
      speed--;
   if(code==KeyEvent.VK_DOWN && speed<3)
      speed++;
   if(code==KeyEvent.VK_SHIFT)
      incrgoli=3;
  }
public void mouseClicked(MouseEvent me)
  {
   cheatarr="";
   if(startgame==false)
     {
      startgame=!startgame;
      repaint();
      play(getCodeBase(),"start.au");
     }
  }
public void mouseMoved(MouseEvent me)
  {
   int temp=xbase,mousex=me.getX();
   xbase=mousex;
   repaint(temp-35,380,70,20);
   repaint(xbase-35,380,70,20);
  }
public void mouseDragged(MouseEvent me)
  {
   int temp=xbase,mousex=me.getX();
   xbase=mousex;
   repaint(temp-35,380,70,20);
   repaint(xbase-35,380,70,20);
   if(startgame==true)
     {
      if(numgoli<10)
        {
         if(numgoli==0)
           {
            xgoli[numgoli]=xbase;
            ygoli[numgoli]=370;
            numgoli++;
           }
         else
            if(ygoli[numgoli-1]<=350)
              {
               xgoli[numgoli]=xbase;
               ygoli[numgoli]=370;
               numgoli++;
              }
         play(getCodeBase(),"fire.au");
        }
     }
  }
public void mouseEntered(MouseEvent me)
  {
  }
public void mouseExited(MouseEvent me)
  {
  }
public void mousePressed(MouseEvent me)
  {
   if(startgame==true)
     {
      if(numgoli<10)
        {
         if(numgoli==0)
           {
            xgoli[numgoli]=xbase;
            ygoli[numgoli]=370;
            numgoli++;
           }
         else
            if(ygoli[numgoli-1]<=350)
              {
               xgoli[numgoli]=xbase;
               ygoli[numgoli]=370;
               numgoli++;
              }
         play(getCodeBase(),"fire.au");
        }
     }

  }
public void mouseReleased(MouseEvent me)
  {
  }
public void keyReleased(KeyEvent ke)
  {
   int code=ke.getKeyCode();
   if(code==KeyEvent.VK_SHIFT)
      incrgoli=12;
  }
public void keyTyped(KeyEvent ke)
  {
   char ch=ke.getKeyChar();
   if(ch==' ')
     {
      repaint();
      play(getCodeBase(),"start.au");
      startgame=!startgame;
     }
   if(ch=='p' || ch=='P' || ch=='o' || ch=='O' || ch=='l' || ch=='L')
     {
      cheatarr+=ch;
      if(cheatarr.equals("polo") || cheatarr.equals("POLO"))
        {
         cheatarr="";
         cheat=!cheat;
        }
     }
   else
     {
      cheatarr="";
     }
   if(cheatarr.length()>4)
      cheatarr="";
  }
}
