/*
 * Educational software for a basic game development
 * Copyright (C) 2018  Pr. Olivier Gruber
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package edu.ricm3.game.purgatoire;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SwingConstants;

import edu.ricm3.game.GameUI;
import ricm3.interpreter.IAutomaton;

public class GameMain {

	public static void main(String[] args) throws IllegalAccessException {
		menu();
		return;
	}

	@SuppressWarnings("unchecked")
	static void menu() {

		final JFrame frame = new JFrame();
		frame.getContentPane().setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();

		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.fill = GridBagConstraints.BOTH;

		gbc.gridy = 0;
		gbc.gridx = 2;
		frame.add(new JLabel(" Heaven Aut "), gbc);
		gbc.gridx = 3;
		frame.add(new JLabel(" Hell Aut "), gbc);
		gbc.gridx = 4;
		frame.add(new JLabel(" Heaven Ani "), gbc);
		gbc.gridx = 5;
		frame.add(new JLabel(" Hell Ani "), gbc);
		gbc.gridx = 7;
		frame.add(new JLabel(" Heaven Aut "), gbc);
		gbc.gridx = 8;
		frame.add(new JLabel(" Hell Aut "), gbc);
		gbc.gridx = 9;
		frame.add(new JLabel(" Heaven Ani "), gbc);
		gbc.gridx = 10;
		frame.add(new JLabel(" Hell Ani "), gbc);

		JSpinner[] spinners = new JSpinner[Singleton.m_existingEntitiesTypes.length];
		JComboBox<IAutomaton>[] comboAutHeaven1 = new JComboBox[Singleton.m_existingEntitiesTypes.length];
		JComboBox<IAutomaton>[] comboAutHell1 = new JComboBox[Singleton.m_existingEntitiesTypes.length];
		JComboBox<Animation>[] comboAniHeaven1 = new JComboBox[Singleton.m_existingEntitiesTypes.length];
		JComboBox<Animation>[] comboAniHell1 = new JComboBox[Singleton.m_existingEntitiesTypes.length];
		JComboBox<IAutomaton>[] comboAutHeaven2 = new JComboBox[Singleton.m_existingEntitiesTypes.length];
		JComboBox<IAutomaton>[] comboAutHell2 = new JComboBox[Singleton.m_existingEntitiesTypes.length];
		JComboBox<Animation>[] comboAniHeaven2 = new JComboBox[Singleton.m_existingEntitiesTypes.length];
		JComboBox<Animation>[] comboAniHell2 = new JComboBox[Singleton.m_existingEntitiesTypes.length];

		File config = new File("config");
		Scanner sc = null;
		try {
			sc = new Scanner(config);
		} catch (FileNotFoundException e2) {
			e2.printStackTrace();
			System.exit(-3);
		}

		gbc.gridy = 1;
		for (int i = 0; i < Singleton.m_existingEntitiesTypes.length; i++) {
			if (!sc.hasNextLine())
				throw new IllegalStateException("There alaways should be enought line here");
			String configLine = sc.nextLine();
			String[] configWords = configLine.split(" ");

			String line = Singleton.m_existingEntitiesTypes[i];
			gbc.gridx = 0;

			spinners[i] = new JSpinner();
			spinners[i].setValue(new Integer(configWords[0]));
			frame.add(spinners[i], gbc);

			gbc.gridx = 1;
			frame.add(new JLabel(" " + line + " with : "), gbc);

			gbc.gridx = 2;
			JComboBox<IAutomaton> comboAut = new JComboBox<IAutomaton>();
			JComboBox<Animation> comboAni = new JComboBox<Animation>();
			for (IAutomaton aut : Singleton.getAutomatons()) {
				comboAut.addItem(aut);
				if (aut.toString().equals(configWords[1]))
					comboAut.setSelectedItem(aut);
			}
			frame.add(comboAut, gbc);
			comboAutHeaven1[i] = comboAut;

			gbc.gridx = 3;
			comboAut = new JComboBox<IAutomaton>();
			for (IAutomaton aut : Singleton.getAutomatons()) {
				comboAut.addItem(aut);
				if (aut.toString().equals(configWords[2]))
					comboAut.setSelectedItem(aut);
			}
			frame.add(comboAut, gbc);
			comboAutHell1[i] = comboAut;

			gbc.gridx = 4;
			comboAni = new JComboBox<Animation>();
			for (Animation ani : Singleton.getAnimations()) {
				comboAni.addItem(ani);
				if (ani.toString().equals(configWords[3]))
					comboAni.setSelectedItem(ani);
			}
			frame.add(comboAni, gbc);
			comboAniHeaven1[i] = comboAni;

			gbc.gridx = 5;
			comboAni = new JComboBox<Animation>();
			for (Animation ani : Singleton.getAnimations()) {
				comboAni.addItem(ani);
				if (ani.toString().equals(configWords[4]))
					comboAni.setSelectedItem(ani);
			}
			frame.add(comboAni, gbc);
			comboAniHell1[i] = comboAni;

			gbc.gridx = 6;
			frame.add(new JLabel(" others with : "), gbc);

			gbc.gridx = 7;
			comboAut = new JComboBox<IAutomaton>();
			for (IAutomaton aut : Singleton.getAutomatons()) {
				comboAut.addItem(aut);
				if (aut.toString().equals(configWords[5]))
					comboAut.setSelectedItem(aut);
			}
			frame.add(comboAut, gbc);
			comboAutHeaven2[i] = comboAut;

			gbc.gridx = 8;
			comboAut = new JComboBox<IAutomaton>();
			for (IAutomaton aut : Singleton.getAutomatons()) {
				comboAut.addItem(aut);
				if (aut.toString().equals(configWords[6]))
					comboAut.setSelectedItem(aut);
			}
			frame.add(comboAut, gbc);
			comboAutHell2[i] = comboAut;

			gbc.gridx = 9;
			comboAni = new JComboBox<Animation>();
			for (Animation ani : Singleton.getAnimations()) {
				comboAni.addItem(ani);
				if (ani.toString().equals(configWords[7]))
					comboAni.setSelectedItem(ani);
			}
			frame.add(comboAni, gbc);
			comboAniHeaven2[i] = comboAni;

			gbc.gridx = 10;
			comboAni = new JComboBox<Animation>();
			for (Animation ani : Singleton.getAnimations()) {
				comboAni.addItem(ani);
				if (ani.toString().equals(configWords[8]))
					comboAni.setSelectedItem(ani);
			}
			frame.add(comboAni, gbc);
			comboAniHell2[i] = comboAni;

			gbc.gridy++;
		}

		gbc.gridy = 7;
		gbc.gridx = 5;

		JButton okay = new JButton("Okay");
		okay.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int size = Singleton.m_existingEntitiesTypes.length;
				IAutomaton[] HellAut = new IAutomaton[size];
				IAutomaton[] HeavenAut = new IAutomaton[size];
				Animation[] HellAnim = new Animation[size];
				Animation[] HeavenAnim = new Animation[size];
				int[] Firsts = new int[size];
				IAutomaton[] HellAutFirst = new IAutomaton[size];
				IAutomaton[] HeavenAutFirst = new IAutomaton[size];
				Animation[] HellAnimFirst = new Animation[size];
				Animation[] HeavenAnimFirst = new Animation[size];

				for (int i = 0; i < Singleton.m_existingEntitiesTypes.length; i++) {
					Firsts[i] = (Integer) spinners[i].getValue();
					HeavenAutFirst[i] = (IAutomaton) (comboAutHeaven1[i].getSelectedItem());
					HellAutFirst[i] = (IAutomaton) (comboAutHell1[i].getSelectedItem());
					HeavenAnimFirst[i] = (Animation) (comboAniHeaven1[i].getSelectedItem());
					HellAnimFirst[i] = (Animation) (comboAniHell1[i].getSelectedItem());
					HeavenAut[i] = (IAutomaton) (comboAutHeaven2[i].getSelectedItem());
					HellAut[i] = (IAutomaton) (comboAutHell2[i].getSelectedItem());
					HeavenAnim[i] = (Animation) (comboAniHeaven2[i].getSelectedItem());
					HellAnim[i] = (Animation) (comboAniHell2[i].getSelectedItem());
				}

				Singleton.set(HellAut, HeavenAut, HellAnim, HeavenAnim, Firsts, HellAutFirst, HeavenAutFirst,
						HellAnimFirst, HeavenAnimFirst);

				(new Sound("sprites/button_sound.wav")).start();
				intro(frame);
			}
		});
		frame.add(okay, gbc);

		gbc.gridy = 7;
		gbc.gridx = 6;

		JButton save = new JButton("Save");
		save.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				BufferedWriter config = null;
				try {
					config = new BufferedWriter(new FileWriter("config"));
				} catch (IOException e1) {
					e1.printStackTrace();
					System.exit(-3);
				}

				for (int i = 0; i < Singleton.m_existingEntitiesTypes.length; i++) {
					try {
						config.write((Integer) spinners[i].getValue() + " ");
						config.write(((IAutomaton) (comboAutHeaven1[i].getSelectedItem())).toString() + " ");
						config.write(((IAutomaton) (comboAutHell1[i].getSelectedItem())).toString() + " ");
						config.write(((Animation) (comboAniHeaven1[i].getSelectedItem())).toString() + " ");
						config.write(((Animation) (comboAniHell1[i].getSelectedItem())).toString() + " ");
						config.write(((IAutomaton) (comboAutHeaven2[i].getSelectedItem())).toString() + " ");
						config.write(((IAutomaton) (comboAutHell2[i].getSelectedItem())).toString() + " ");
						config.write(((Animation) (comboAniHeaven2[i].getSelectedItem())).toString() + " ");
						config.write(((Animation) (comboAniHell2[i].getSelectedItem())).toString() + " ");
						config.write("\n");
					} catch (IOException e1) {
						e1.printStackTrace();
						System.exit(-3);
					}
				}

				try {
					config.close();
				} catch (IOException e1) {
					e1.printStackTrace();
					System.exit(-3);
				}
			}
		});
		frame.add(save, gbc);

		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	static WorldType wt;
	static void intro(JFrame oldFrame) {
		final JFrame frame = new JFrame();
		frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
		frame.setMinimumSize(new Dimension(900, 800));
		ImagePanel img = new ImagePanel();
		img.setAlignmentX(Component.CENTER_ALIGNMENT);
		img.setPreferredSize(new Dimension(900, 650));
		img.setBackground(Color.BLACK);
		frame.add(img);

		frame.setBackground(Color.BLACK);
		frame.getRootPane().setBackground(Color.BLACK);
		frame.getLayeredPane().setBackground(Color.BLACK);
		frame.getContentPane().setBackground(Color.BLACK);

		JLabel label = new JLabel(
				"<html>You just woke up after a peaceful nap in a mysterious cave. <br/> You stretch and blink gently when you suddenly realize a MONSTROUS CREATURE is facing you. <br/>His breath is terrible and his eyes are terrifying. <br/> At this moment you know you can’t stay still much longer and you have to act. What do you want to do?<br/>  </html>",
				SwingConstants.CENTER);
		label.setAlignmentX(Component.CENTER_ALIGNMENT);
		label.setForeground(Color.WHITE);
		label.setBackground(Color.BLACK);
		frame.add(label);

		Container cont = new Container();
		cont.setLayout(new FlowLayout());
		JButton attack = new JButton("Attack!");
		JButton flee = new JButton("Flee");

		Container continuerContainer = new Container();
		continuerContainer.setLayout(new FlowLayout());
		JButton continuer = new JButton("Continue");
		continuerContainer.add(continuer);
		continuer.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				play(frame, wt);
			}
		});

		attack.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				label.setText(
						"<html>Oh oh… You just died… You really thought you could beat this monster? <br/> But it’s not over yet, you’ve been given the chance to become GOD in Heaven or SATAN in Hell, you choose! <br/> \n"
								+ "Your actions will be carefully monitored and your karma will depend on them. <br/> Be wise, and become the most powerful being of your world.<br/> <br/> \n"
								+ "\n"
								+ " As you chose to attack this monster, you have sinned: go to HELL!<br/>  </html>");
				img.current = img.image2;
				img.repaint();
				frame.remove(cont);
				frame.remove(cont);
				frame.add(continuerContainer);
				setWt(WorldType.HELL);
			}
		});
		cont.add(attack);
		cont.setBackground(Color.BLACK);

		flee.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				label.setText(
						"<html>Oh oh… You just died… You really thought you could escape this monster? <br/>  But it’s not over yet, you’ve been given the chance to become GOD in Heaven or SATAN in Hell, you choose! <br/> \n"
								+ "Your actions will be carefully monitored and your karma will depend on them. <br/> Be wise, and become the most powerful being of your world.<br/> <br/> \n"
								+ "\n"
								+ "As you chose to escape the danger, you chose the path of virtue: you’re allowed to go to HEAVEN!<br/>  <html>");
				img.current = img.image2;
				img.repaint();
				frame.remove(cont);
				frame.remove(cont);
				frame.add(continuerContainer);
				setWt(WorldType.HEAVEN);
			}
		});
		cont.add(flee);
		frame.add(cont);

		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

		oldFrame.dispatchEvent(new WindowEvent(oldFrame, WindowEvent.WINDOW_CLOSING));
	}

	static void setWt(WorldType wttmp) {
		wt = wttmp;
	}
	
	static void play(JFrame frame, WorldType wt) {
		// construct the game elements: model, controller, and view.
		Model model = new Model(wt);
		View view = new View(model);
		Controller controller = new Controller(model, view);
		try {
			Singleton.setController(controller);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			System.exit(-2);
		}
		Dimension d = new Dimension(540 + 2 * Options.UI_PANEL_SIZE, 744 + 40);
		frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
		new GameUI(model, view, controller, d);
	}

	public static class ImagePanel extends JPanel {
		private static final long serialVersionUID = 7190904833473088836L;

		public BufferedImage image1;
		public BufferedImage image2;
		public BufferedImage current;

		public ImagePanel() {
			try {
				image1 = ImageIO.read(new File("sprites/intro1.png"));
				image2 = ImageIO.read(new File("sprites/intro2.png"));
				current = image1;
			} catch (IOException ex) {
				ex.printStackTrace();
				System.exit(-5);
			}
		}

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(current, 0, 0, null); // see javadoc for more info on the parameters
		}

	}
}
