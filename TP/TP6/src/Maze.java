import java.util.*;

// Classe a remplir pour realiser le TP en utilisant la classe DisjointSet fournie
//Référence utilisée pour la résolution du laboratoire 6 : http://stackoverflow.com/questions/22288843/maze-solving-using-recursion-in-java

public class Maze{

	public Maze(int w, int h, int seed){
		width = w;
		height = h;

		LASTROOM = width*height-1;

		// Initialisation du labyrinthe avec tous les murs
		maze = new Vector<Wall>();

	        // A completer
		for(int i = 0; i < height; ++i){
			for(int j = 0; j < width; ++j){
				int k = j + i * height;
				if(i > 0)
					maze.add(new Wall(k, k - height));
				if(j > 0)
					maze.add(new Wall(k, k - 1));
			}
		}
		
		// Creation du graphe de la topologie du labyrinthe

		// A completer	
		
		graph = new Vector<Room>();
		for(int i = 0; i < height * width; i++)
			graph.addElement(new Room(i));
		// On trie les murs de maniere aleatoire
		generator = new Random(seed);
		for(int i = 0; i < maze.size(); ++i)
		{
			// A completer
			int indice = i + generator.nextInt(maze.size() - i); //generation d'un indice aleatorie entre i et la taille de labyrinthe.
			Wall tmp = maze.get(i);								//
			maze.set(i, maze.get(indice));						// permutation des walls
			maze.set(indice, tmp);								//
		}

		// Initialisation des structures annexes
		ds = new DisjointSet(width*height);
		path = new Vector<Integer>();
	}

	public void generate()
	{
		// A completer
		Stack<Wall> maPile = new Stack<Wall>();
		for(int i = 0; i < maze.size(); i++)
		{
			Wall leMur = maze.get(i);
			if(ds.find(leMur.room1) != ds.find(leMur.room2))
			{
				maPile.push(leMur);
				ds.union(leMur.room1, leMur.room2);
				graph.get(leMur.room1).paths.add(leMur.room2);
				graph.get(leMur.room2).paths.add(leMur.room1);
			}
		}
		while(!maPile.empty())
			maze.remove(maPile.pop());
	}

	public void solve()
	{
		// A completer
		int index = 0;
		path.add(graph.get(index).id); //Ajout de la premiere salle au chemin.
		resoudre(index);
	}
	
	//Méthode récursive permettant de parcourir le labyrinthe dans les 4 directions EST,OUEST,NORD et SUD à partir d'un point donné
	//afin de trouver le chemin le plus cours.
	//L'ajout de cette méthode a été approuvé par M. Wail Khemir, notre chargé de laboratoire.
	
	public boolean resoudre(int index)
	{
		boolean resolu = false;
		if(index != LASTROOM)	//Si la derniere piece n'est pas encore atteinte.
		{
			//direction NORD (index - width) si la direction est valide
			if (valide(index, width, LASTROOM, index - width))
			{
				path.add(graph.get(index - width).id); //prolongement du chemin vers la chambre du haut.
				if(resoudre(index - width)) //Vérification si le chemin débouche ou non
					resolu = true;
			}
			//direction SUD (index + width) si ce n'est pas résolu et la direction est valide
			if (!resolu && valide(index, 0, LASTROOM - width, index + width))
			{
				path.add(graph.get(index + width).id); //prolongement du chemin vers la chambre du bas.
				if(resoudre(index + width))	//Vérification si le chemin débouche ou non
					resolu = true;
			}
			//direction EST (index + 1) si ce n'est pas résolu et la direction est valide
			if (!resolu && valide(index, 0, LASTROOM - 1, index + 1))
			{
				path.add(graph.get(index + 1).id); //prolongement du chemin vers la chambre de droite
				if(resoudre(index + 1)) //Vérification si le chemin débouche ou non
					resolu = true;
			}
			//direction OUEST (index - 1) si ce n'est pas résolu et la direction est valide
			if (!resolu && valide(index, 1, LASTROOM, index - 1))
			{
				path.add(graph.get(index - 1).id); //prolongement du chemin vers la chambre de gauche
				if(resoudre(index - 1)) //Vérification si le chemin débouche ou non
					resolu = true;
			}
			if(!resolu)
				path.remove((Integer)graph.get(index).id);	//Retrait de la dernière salle ajoutée.
		}
		else
			resolu = true;		// labyrinthe résolu
		return resolu;
	}
	
	// Méthode permettant de valider la direction du chemin

	private boolean valide(int index, int borneInf, int borneSup, int direction)
	{
		boolean valide = false;
		int ancienID = 0;
		if(path.size() != 1)
			ancienID = path.get(path.size() - 2);
		if(index >= borneInf && index <= borneSup && graph.get(direction).id != ancienID 
				&& graph.get(index).paths.contains(graph.get(direction).id))
			valide = true;
		return valide;
	}

	public class Wall{
		
		public Wall(int r1, int r2){
			room1 = r1;
			room2 = r2;
		}

		public int room1;
		public int room2;
	}

	public class Room{
		
		public Room(int i){
			id = i;
			distance = -1;
			paths = new Vector<Integer>();
		}

		public int id;
		Vector<Integer> paths;
		int distance;		

	}

	public Vector<Wall> maze;
	public Vector<Room> graph;
	public Vector<Integer> path;

	public int LASTROOM;
	public int height;
	public int width;
	public Random generator;
	public DisjointSet ds;
}