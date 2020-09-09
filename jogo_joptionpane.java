import java.util.Random;

import javax.swing.JOptionPane;
/*acho que nesse estilo de jogo não há a necessidade de criar threads, porém,
o esquema já ta criado de qualquer forma
*/

public class jogo_joptionpane implements Runnable 
{
	private int life_player = 100;
	private int life_enemy = 100;
	private Thread thread;
	private boolean isRunning = true;
	private String name;
	private String direction;
	private String action;
	private Random rand = new Random();
	
	public synchronized void start()
	{
		thread = new Thread(this);
		thread.start();
	}
	public synchronized void stop()
	{
		isRunning = false;
		try 
		{
			thread.join();
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
	}
	
	public jogo_joptionpane()
	{
		JOptionPane.showMessageDialog(null, "Bem vindo ao MEUJOGO!!", "Primeiro Jogo"
				,JOptionPane.INFORMATION_MESSAGE);
		name = JOptionPane.showInputDialog(null, "Por favor, insira seu nome.");
		JOptionPane.showMessageDialog(null, "Seja bem vindo, "+name+"!!", "Primeiro Jogo"
				,JOptionPane.INFORMATION_MESSAGE);

		Game();
	}
	public void Game()
	{
		direction = JOptionPane.showInputDialog(null, "Em qual direção gostaria de"
				+ "seguir? w = para frente, d = para direita, a = esquerda, s = para trás"
				, "Primeiro jogo", JOptionPane.QUESTION_MESSAGE);
		if (direction.equals("d"))
		{
			JOptionPane.showMessageDialog(null, "Você caiu do penhasco e morreu..."
					+ "Tente novamente!", "Primeiro jogo",JOptionPane.WARNING_MESSAGE);
			Game();
		}
		else if (direction.equals("s"))
		{
			JOptionPane.showMessageDialog(null, "Você tropeçou, bateu a cabeça e morreu..."
					+ "Tente novamente!", "Primeiro jogo",JOptionPane.WARNING_MESSAGE);
			Game();
		}
		else if (direction.equals("a"))
		{
			JOptionPane.showMessageDialog(null, "Você pisou em uma planta venenosa e"
					+ " morreu..."
					+ "Tente novamente!", "Primeiro jogo",JOptionPane.WARNING_MESSAGE);
			Game();
		}
	}
	public static void main(String[] args)
	{
		jogo_joptionpane game = new jogo_joptionpane();
		game.start();
		
	}
		
	public void run()
	{
		JOptionPane.showMessageDialog(null, "Você encontrou um inimigo! "
				, "Primeiro Jogo", JOptionPane.WARNING_MESSAGE);

		while (direction.equals("w") && life_player > 0 && life_enemy > 0)
		{
			
			action = JOptionPane.showInputDialog(null, "Você tem "+life_player+
					" e o inimigo "+life_enemy+" de vida, o que deseja fazer? "
							+ "a = atacar, c = correr","Primeiro Jogo", 
							JOptionPane.QUESTION_MESSAGE);
			if (action.equals("a"))
			{
				if (rand.nextInt(99) < 75)
				{
					JOptionPane.showMessageDialog(null,"Voce acertou o ataque!", 
							"Primeiro Jogo", JOptionPane.WARNING_MESSAGE);
					life_enemy -= 20;
				}
				else
				{
					JOptionPane.showMessageDialog(null, "O inimigo contra-atacou, "
							+ "você levou 20 de dano!", "Primeiro Jogo", 
							JOptionPane.WARNING_MESSAGE);
					life_player -= 20;
				}
			}
			if (action.equals("c"))
			{
				JOptionPane.showMessageDialog(null, "O inimigo atacou você"
						+ " pelas costas,você levou 20 de dano!", 
						"Primeiro Jogo", JOptionPane.WARNING_MESSAGE);
				life_player -= 20;
			}
				
		}
		if (life_player == 0)
		{
			JOptionPane.showMessageDialog(null, "       GAME OVER!\n "
					+ "   tente novamente...",
					"Primeiro Jogo", JOptionPane.WARNING_MESSAGE);
			Game();
		}
		if (life_enemy == 0)
		{
			JOptionPane.showMessageDialog(null, "PARABÊNS, VOCÊ VENCEU O JOGO!!!!!",
					"Primeiro Jogo", JOptionPane.WARNING_MESSAGE);
			System.exit(0);
		}
			
	}
	
}
