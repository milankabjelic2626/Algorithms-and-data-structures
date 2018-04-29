import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.*;

public class Main extends JApplet 
{	
	private Grad[] cvorovi = 
                        { 
                        new Grad("Sid", 75, 55), new Grad("Sremska Mitrovica", 50, 210),
			new Grad("Ruma", 75, 320), new Grad("Indjija", 275, 175),
			new Grad("Subotica", 400, 245), new Grad("Novi Sad", 440, 60),
			new Grad("Sabac", 700, 80), new Grad("Sombor", 720, 170),
			new Grad("Backa Palanka", 680, 260), new Grad("Becej", 700, 390),
			new Grad("Zrenjanin", 340, 375), new Grad("Kikinda", 480, 390) 
                        };

	private int[][] grane = 
                        { 
                        { 0, 1 }, { 0, 3 }, { 0, 5 }, { 1, 0 }, { 1, 2 },
			{ 1, 3 }, { 2, 1 }, { 2, 3 }, { 2, 4 }, { 2, 10 }, { 3, 0 },
			{ 3, 1 }, { 3, 2 }, { 3, 4 }, { 3, 5 }, { 4, 2 }, { 4, 3 },
			{ 4, 5 }, { 4, 7 }, { 4, 8 }, { 4, 10 }, { 5, 0 }, { 5, 3 },
			{ 5, 4 }, { 5, 6 }, { 5, 7 }, { 6, 5 }, { 6, 7 }, { 7, 4 },
			{ 7, 5 }, { 7, 6 }, { 7, 8 }, { 8, 4 }, { 8, 7 }, { 8, 9 },
			{ 8, 10 }, { 8, 11 }, { 9, 8 }, { 9, 11 }, { 10, 2 }, { 10, 4 },
			{ 10, 8 }, { 10, 11 }, { 11, 8 }, { 11, 9 }, { 11, 10 } 
                        };

	private Graf<Grad> graf = new KreirajGraf<Grad>(grane, cvorovi);
	private GraphView grafView = new GraphView(graf);
	private JTextField tf = new JTextField(10);
	private ApstraktniGraf<Grad>.Stablo stablo = null;
	
        public Main() 
        {
		setLayout(new BorderLayout(5, 5));
		add(grafView, BorderLayout.CENTER);
		JPanel mojPanel = new JPanel();
		mojPanel.add(new JLabel("Pocetni grad"));
		mojPanel.add(tf);
		JButton dfsBtn = new JButton("Prikazi DFS stablo");
		mojPanel.add(dfsBtn);
		JButton bfsBtn = new JButton("Prikazi BFS stablo");
		mojPanel.add(bfsBtn);
		add(mojPanel, BorderLayout.SOUTH);
                //DFS
		dfsBtn.addActionListener(new ActionListener() 
                {			
			@Override
			public void actionPerformed(ActionEvent e) 
                        {
				String imeGrada = tf.getText();
				stablo = null;
				List<Grad> lista = graf.getCvorovi();
				for (int i = 0; i < lista.size(); i++) 
                                {
					if(imeGrada.equals(lista.get(i).ime)) 
                                        {
						stablo = graf.dfs(i);						
						break;
					}
				}
				grafView.repaint();
			}
		});
		//BFS
		bfsBtn.addActionListener(new ActionListener() 
                {			
			@Override
			public void actionPerformed(ActionEvent e) 
                        {
				String imeGrada = tf.getText();
				stablo = null;
				List<Grad> list = graf.getCvorovi();
				for (int i = 0; i < list.size(); i++) 
                                {
					if(imeGrada.equals(list.get(i).ime)) 
                                        {
						stablo = graf.bfs(i);
						break;
					}
				}
				grafView.repaint();
			}
		});
	}
	
	class GraphView extends javax.swing.JPanel 
        {
		
		private Graf<? extends CvorInfo> graf;

		public GraphView(Graf<? extends CvorInfo> graph) 
                {
			this.graf = graph;
		}

		@Override
		protected void paintComponent(java.awt.Graphics g) 
                {
			super.paintComponent(g);

			// cranje cvorova
			List<? extends CvorInfo> cvorovi = graf
					.getCvorovi();                        
			for (int i = 0; i < graf.getSize(); i++) 
                        {
				int x = cvorovi.get(i).getX();
				int y = cvorovi.get(i).getY();
				String ime = cvorovi.get(i).getIme();                               
                                
				g.fillOval(x - 8, y - 8, 20, 20);                             
				g.drawString(ime, x - 9, y - 9); 
			}

			// cranje grana za par cvorova
			for (int i = 0; i < graf.getSize(); i++) 
                        {
				List<Integer> neighbors = graf.getSusedi(i);
				int x1 = graf.getCvor(i).getX();
				int y1 = graf.getCvor(i).getY();
				for (int v : neighbors) 
                                {
					int x2 = graf.getCvor(v).getX();
					int y2 = graf.getCvor(v).getY();

					g.drawLine(x1, y1, x2, y2); 
				}
			}
			
			if(stablo != null) 
                        {                           
				g.setColor(Color.RED);
				for (int i = 0; i < stablo.roditelj.length; i++) 
                                {
					if (stablo.roditelj[i] != -1) 
                                        {
						int duzina = 20;
						int x0 = cvorovi.get(i).getX();
						int y0 = cvorovi.get(i).getY();
						int x1 = cvorovi.get(stablo.roditelj[i]).getX();
						int y1 = cvorovi.get(stablo.roditelj[i]).getY();
						double ugao1 = Math.atan2(y1 - y0, x1 - x0);
						g.drawLine(x1, y1, x0, y0);
						
						double ugao2 = Math.toRadians(Math.toDegrees(ugao1) + 30);
						int x2 = (int)(x0 + Math.cos(ugao2) * duzina);
						int y2 = (int)(y0 + Math.sin(ugao2) * duzina);						
						g.drawLine(x0, y0, x2, y2);
						
						double ugao3 = Math.toRadians(Math.toDegrees(ugao1) - 30);
						int x3 = (int)(x0 + Math.cos(ugao3) * duzina);
						int y3 = (int)(y0 + Math.sin(ugao3) * duzina);						
						g.drawLine(x0, y0, x3, y3);						
					}
				}
			}
		}
	}

	public static void main(String[] args) 
        {
		JFrame mojFrame = new JFrame("Pretrazivanje grafa po dubini i sirini");
		Main app = new Main();                
		mojFrame.add(app);
		app.init();
		app.start();

		mojFrame.setLocationRelativeTo(null);
		mojFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mojFrame.setSize(800, 500);
		mojFrame.setLocationRelativeTo(null);
		mojFrame.setVisible(true);
	}
}
