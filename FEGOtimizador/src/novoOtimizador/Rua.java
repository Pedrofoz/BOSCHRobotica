package novoOtimizador;

import lejos.geom.Point;
import lejos.robotics.navigation.Waypoint;


public class Rua 
{
	/*
	 * oi
	 */
	//Parametros
	private Integer id;
	protected String nome;
	protected Waypoint inicio;
	protected Waypoint fim;
	protected float velocidade;
	protected boolean[] direcao;
	/*
	 * boolean[0] -> sentido ir (inicio - fim)
	 * boolean[1] -> sentido volta (fim - inicio)
	 */
	protected boolean[] tipo;
	/*
	 * boolean[0] -> reta TRUE / curva FALSE
	 * boolean[1] -> curva à  direita TRUE / esquerda FALSE
	 */
	//Construtor
	public Rua()
	{
		//
	}
	
	public Rua(Integer id, String nome, Waypoint inicio, Waypoint fim, float velocidade, boolean[] direcao, boolean[] tipo) 
	{
		super();
		setId(id);
		setNome(nome);
		setInicio(inicio);
		setFim(fim);
		setVelocidade(velocidade);
		setDirecao(direcao);
		setTipo(tipo);
	}

	//Metodos Acessores
	public void setNome(String s)
	{
		this.nome = s;
	}
	public Waypoint getInicio()
	{
		return inicio;
	}
	public void setInicio(Waypoint inicio) 
	{
		this.inicio = inicio;
	}
	public Waypoint getFim() 
	{
		return fim;
	}
	public void setFim(Waypoint fim) 
	{
		this.fim = fim;
	}
	public float getVelocidade() 
	{
		return velocidade;
	}
	public void setVelocidade(float velocidade) 
	{
		this.velocidade = velocidade;
	}
	public boolean[] getDirecao() 
	{
		return direcao;
	}
	public void setDirecao(boolean[] direcao) 
	{
		this.direcao = direcao;
	}
	public boolean[] getTipo() 
	{
		return tipo;
	}
	public void setTipo(boolean[] direcao) 
	{
		this.tipo = direcao;
	}
	public String getNome() 
	{
		return nome;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}
