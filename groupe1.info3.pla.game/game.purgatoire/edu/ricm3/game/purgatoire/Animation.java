package edu.ricm3.game.purgatoire;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import javax.imageio.ImageIO;

public class Animation {
	private List<BufferedImage> m_sprites[];
	private String m_name;

	public enum AnimType {
		NORTH(0), SOUTH(1), EAST(2), WEST(3), IDLE(4);

		private final int value;

		AnimType(final int newValue) {
			value = newValue;
		}

		public int getValue() {
			return value;
		}
	}

	public Animation(String fileName) {
		m_name = fileName;
		try {
			m_sprites = spritesFromFile(new File(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.exit(-1);
		}
	}

	public Animation(File file) {
		m_name = file.getName();
		try {
			m_sprites = spritesFromFile(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.exit(-1);
		}
	}

	private static List<BufferedImage>[] spritesFromFile(File file) throws FileNotFoundException {
		@SuppressWarnings("unchecked")
		List<BufferedImage>[] sprites = new List[5];
		BufferedImage[] splitImage = null;

		Scanner sc = new Scanner(file);

		// Opening and splitting the .png file using the first line of the file
		if (sc.hasNextLine()) {
			String line = sc.nextLine();
			String words[] = line.split("\\s*=\\s*");

			File imageFile = new File(words[1]);
			try {
				BufferedImage tmp = ImageIO.read(imageFile);
				splitImage = splitSprite(tmp);
			} catch (IOException ex) {
				ex.printStackTrace();
				System.exit(-1);
			}
		}

		// Defining animation with the next lines of the file
		while (sc.hasNextLine()) {
			String line = sc.nextLine();
			String words[] = line.split("\\s*=\\s*");
			String type = words[0];
			String numbers[] = words[1].split("\\s*;\\s*");

			ArrayList<BufferedImage> tmp = new ArrayList<BufferedImage>();
			for (String string : numbers) {
				tmp.add(splitImage[Integer.parseInt(string) - 1]);
			}
			switch (type) {
			case "NORTH":
				sprites[AnimType.NORTH.getValue()] = tmp;
				break;
			case "SOUTH":
				sprites[AnimType.SOUTH.getValue()] = tmp;
				break;
			case "EAST":
				sprites[AnimType.EAST.getValue()] = tmp;
				break;
			case "WEST":
				sprites[AnimType.WEST.getValue()] = tmp;
				break;
			case "IDLE":
				sprites[AnimType.IDLE.getValue()] = tmp;
				break;
			}

		}
		sc.close();
		return sprites;
	}

	private static BufferedImage[] splitSprite(BufferedImage image) {
		int width = image.getWidth(null);
		int height = image.getHeight(null);
		int cols = width / 32;
		int rows = height / 32;
		BufferedImage[] splitImage = new BufferedImage[cols * rows];

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				int x = j * 32;
				int y = i * 32;
				splitImage[(i * cols) + j] = image.getSubimage(x, y, 32, 32);
			}
		}

		return splitImage;
	}

	public int length(AnimType type) {
		return m_sprites[type.getValue()].size();
	}

	public BufferedImage get(AnimType type, int position) {
		return m_sprites[type.getValue()].get(position);
	}

	@Override
	public String toString() {
		return m_name;
	}

	public boolean equals(AnimType type1, AnimType type2) {
		if(type1.getValue() == type2.getValue()) return true;
		int length = m_sprites[type1.getValue()].size();
		if (length != m_sprites[type2.getValue()].size())
			return false;

		Iterator<BufferedImage> iter1 = m_sprites[type1.getValue()].iterator();
		Iterator<BufferedImage> iter2 = m_sprites[type2.getValue()].iterator();
		while(iter1.hasNext()) {
			if(iter1.next() != iter2.next()) return false;
		}

		return true;
	}

}
