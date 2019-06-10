package edu.ricm3.game.purgatoire.stunts;

import edu.ricm3.game.purgatoire.Animation.AnimType;
import edu.ricm3.game.purgatoire.AnimationPlayer;
import edu.ricm3.game.purgatoire.Singleton;
import ricm3.interpreter.IDirection;

public class HellObstacleStunt extends Stunt {

//	HellObstacleStunt(IAutomaton automaton, Entity entity, BufferedImage sprite) {
//		super(automaton, entity, sprite);
//
//		m_animation = new AnimationPlayer(Singleton.getObstacleHellAnim(), AnimType.IDLE, 2);
//		m_animation.resume();
//		
//		m_maxHP = Options.HELL_OBSTACLE_HP_MAX;
//		setDMG(Options.HELL_OBSTACLE_DMG);
//	}

	public HellObstacleStunt() {
		super(Singleton.getNewObstacleHellAut(),  new AnimationPlayer(Singleton.getObstacleHellAnim(), AnimType.IDLE, 2));
	}

	@Override
	public void pop(IDirection d) {
		System.out.println("pop hell obstacle");
	}

	@Override
	public void wizz(IDirection d) {
		System.out.println("wizz hell obstacle");
	}

	@Override
	public void hit(IDirection d) {
		System.out.println("hit hell obstacle");
	}

	@Override
	public void egg() {
		System.out.println("egg hell obstacle");
	}

	@Override
	public void takeDamage(int DMG) {
		System.out.println("takedamage hell obstacle");
	}

}
