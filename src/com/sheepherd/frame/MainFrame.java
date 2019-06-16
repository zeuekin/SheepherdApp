package com.sheepherd.frame;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class MainFrame extends JFrame {
	//draw main frame
	
	JTextArea logTextArea = new JTextArea(24,65);
	static boolean hasInited = false;
	
	public MainFrame(String TITLE) {
		Container container = getContentPane();
		container.setLayout(new BorderLayout(10,5));
		setTitle(TITLE);
		setBounds(300,100,800,600);
		
		//Panel for setup parameters
		JPanel setupPanel = new JPanel();
		setupPanel.setBorder(BorderFactory.createTitledBorder("Setup Para"));
		container.add(setupPanel,BorderLayout.NORTH);
		//Panel for print logs
		JPanel logPanel = new JPanel();
		logPanel.setBorder(BorderFactory.createTitledBorder("Log"));
		container.add(logPanel,BorderLayout.CENTER);
		//Panel for setup parameters
		JPanel actPanel = new JPanel();
		actPanel.setBorder(BorderFactory.createTitledBorder("Action"));
		container.add(actPanel,BorderLayout.EAST);
		
		//setupPanel blocks drawing
		setupPanel.setLayout(new GridLayout(2,6,20,10));
		JTextField grasslandSizeText = new JTextField(10);
		JTextField cycleText = new JTextField(10);
		grasslandSizeText.setColumns(20);
		JLabel blank1 = new JLabel("(1<n<=20)");
		JLabel blank2 = new JLabel("(0<n<=100)");
		JLabel hasWolfLabel = new JLabel("Grassland killers:");
		JLabel grassKeepGrowLabel = new JLabel("Grass refresh:");
		JLabel grasslandSizeLabel = new JLabel("Grassland size: ");
		JLabel cycleLabel = new JLabel("Evolution cycle: ");
		setupPanel.add(grasslandSizeLabel);
		setupPanel.add(grasslandSizeText);
		setupPanel.add(cycleLabel);
		setupPanel.add(cycleText);
		JRadioButton jr1HasWolf = new JRadioButton("Wolves");
		JRadioButton jr2HasWolf = new JRadioButton("No wolves");
		ButtonGroup hasWolf = new ButtonGroup();
		hasWolf.add(jr1HasWolf);
		hasWolf.add(jr2HasWolf);
		JRadioButton jr1HasGrass = new JRadioButton("Growing");
		JRadioButton jr2HasGrass = new JRadioButton("Stop growing.");
		ButtonGroup hasGrass = new ButtonGroup();
		hasGrass.add(jr1HasGrass);
		hasGrass.add(jr2HasGrass);
		
		//setupPanel drawing
		setupPanel.add(grasslandSizeLabel);
		setupPanel.add(grasslandSizeText);
		setupPanel.add(blank1);
		setupPanel.add(cycleLabel);
		setupPanel.add(cycleText);
		setupPanel.add(blank2);
		setupPanel.add(hasWolfLabel);
		setupPanel.add(jr1HasWolf);
		setupPanel.add(jr2HasWolf);
		setupPanel.add(grassKeepGrowLabel);
		setupPanel.add(jr1HasGrass);
		setupPanel.add(jr2HasGrass);
				
		//logPanel drawing
		//JTextArea logTextArea = new JTextArea(20,30);
		logTextArea.setLineWrap(true);
		logTextArea.append("Ready to go ... \n");
		JScrollPane logScroll = new JScrollPane(logTextArea);
		logPanel.add(logScroll);
		
		
		//actPanel drawing
		actPanel.setLayout(new GridLayout(2,1,10,10));
		JButton startButton = new JButton("Go");
		JButton clearButton = new JButton("Clear");
		actPanel.add(startButton);
		actPanel.add(clearButton);
		startButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// exam the parameters, print setup message, then start
				int grassLandSizeInt = Integer.valueOf(grasslandSizeText.getText());
				int cycleInt = Integer.valueOf(cycleText.getText());
				
				if (grassLandSizeInt <= 20 && grassLandSizeInt > 1) {
					if (cycleInt <= 100 && cycleInt > 0) {
						if (hasInited == false) {
							String setupParas = new String();
							setupParas = "The game happens in a "+grasslandSizeText.getText()+
									" square grassland and runs for "+cycleText.getText()+" rounds.";
							logTextArea.append(setupParas+"\n" + "Simulation start...\n");
							Start.sim.init(grassLandSizeInt);
							hasInited = true;
						} 
						Start.sim.runCycles(grassLandSizeInt, cycleInt);
					} else {
						logTextArea.append("Setup parameter's NOT correct! \n");
					}
				} else {
					logTextArea.append("Setup parameter's NOT correct! \n");
				}

				
			}
		});
		clearButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				logTextArea.setText("");
				hasInited = false;
			}
		});
				
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	public void appendLog(String string) {
		logTextArea.append(string);
	}

}
