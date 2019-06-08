package edu.ricm3.game.purgatoire;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.imageio.ImageIO;

public class Animation {
	private List<BufferedImage> m_sprites[];

	public enum AnimType {
		NORTH(1), SOUTH(2), EAST(3), IDLE(4);

		private final int value;

		AnimType(final int newValue) {
			value = newValue;
		}

		public int getValue() {
			return value;
		}
	}

	public Animation(String fileName) throws FileNotFoundException {
		m_sprites = spritesFromFile(fileName);
	}

	private static List<BufferedImage>[] spritesFromFile(String fileName) throws FileNotFoundException {
		List<BufferedImage>[] sprites = new List[4];
		BufferedImage[] splitImage = null;
		
		File file = new File(fileName);
		Scanner sc = new Scanner(file);

		int i = 0;

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
				tmp.add(splitImage[Integer.parseInt(string)]);
			}
			switch(type) {
			case "NORTH":
				sprites[AnimType.NORTH.getValue()] = tmp;
			case "SOUTH":
				sprites[AnimType.SOUTH.getValue()] = tmp;
			case "EAST":
				sprites[AnimType.EAST.getValue()] = tmp;
			case "IDLE":
				sprites[AnimType.IDLE.getValue()] = tmp;
			}

		}

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
}
