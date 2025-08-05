import java.util.Stack;

class Node 
{
	private int x, y;
	private int dir;

	public Node(int i, int j) //constructor to initialise the node
	{
		this.x = i;
		this.y = j;
		
		this.dir = 0; 
	}

	public int getX() 
	{
		return x;
	}

	public void setX(int x) 
	{
		this.x = x;
	}

	public int getY() 
	{
		return y;
	}

	public void setY(int y)
	{
		this.y = y;
	}

	public int getDir() 
	{
		return dir;
	}

	public void setDir(int dir) 
	{
		this.dir = dir;
	}
}

public class Mazesolver 
{
	private static final int B = 4;
	private static final int J = 5;

	//matrix of size B*J
	int n = B, m = J;

	private static boolean[][] visited = new boolean[B][J];

    private static void setVisited(boolean b) //creates visited matrix of size B*J 
	{
		for (int i = 0; i < visited.length; i++) 
		{
			for (int j = 0; j < visited[i].length; j++)
			{
				visited[i][j] = b;
			}
		}

	}

	public static boolean isReachable(int maze[][]) 
	{
		// Initially starting at (0, 0).
		int i = 0, j = 0;
		
		// Coordinates of the final point (cheese)
		int fx, fy;
		fx = 1;
		fy = 4;

		Stack<Node> s = new Stack<Node>(); //creates a stack of node data type

		Node temp = new Node(i, j);

		s.push(temp);

		while (!s.empty()) 
		{
			temp = s.peek();
			int d = temp.getDir();
			i = temp.getX();
			j = temp.getY();

			// Increment the direction 
            temp.setDir(temp.getDir() + 1);
	
			// If we reach the cheese coordinates, return true
			//also prints the solved path
			if (i == fx && j == fy)
			{   
			    while(!(s.isEmpty())) {
			        Node men = (s.pop());
			        int k=men.getX();
			        int o=men.getY();
			        System.out.print(" <--"+"("+k+","+o+")");
			        
			        
			    }
			    System.out.println();
				return true;
			}

			if (d == 0) 
			{ 
				
				// Checking the Up direction.
				if (i - 1 >= 0 && maze[i - 1][j] == 1 && 
										visited[i - 1][j])
				{
					Node temp1 = new Node(i - 1, j);
					visited[i - 1][j] = false;
					s.push(temp1);
				}
			} 
			else if (d == 1) 
			{
				// Checking the left direction
				if (j - 1 >= 0 && maze[i][j - 1] == 1 && 
										visited[i][j - 1]) 
				{
					Node temp1 = new Node(i, j - 1);
					visited[i][j - 1] = false;
					s.push(temp1);
				}
			}
			else if (d == 2) 
			{ 
				// Checking the down direction
				if (i + 1 < B && maze[i + 1][j] == 1 && 
										visited[i + 1][j])
				{
					Node temp1 = new Node(i + 1, j);
					visited[i + 1][j] = false;
					s.push(temp1);
				}
			} 
			else if (d == 3) 
			{ 
				// Checking the right direction
				if (j + 1 < J && maze[i][j + 1] == 1 &&
										visited[i][j + 1])
				{
					Node temp1 = new Node(i, j + 1);
					visited[i][j + 1] = false;
					s.push(temp1);
				}
			}

			// If none of the direction can reach the final point, retract back to the first point.
			else
			{
				visited[temp.getX()][temp.getY()] = true;
				s.pop();
			}
		}

		// Return false if there is no path discovered and the stack is empty.
		return false;
	}

	//main code
	public static void main(String[] args) 
	{
		// Setting the visited array's initial value to true (unvisited)
		setVisited(true);

		int maze[][] = {{ 1, 0, 1, 1, 0 }, 
						{ 1, 1, 1, 0, 1 }, 
						{ 0, 1, 0, 1, 1 }, 
						{ 1, 1, 1, 1, 1 } };

		if (isReachable(maze)) 
		{
			System.out.println("Path Found!\n");
		}
		else{
			System.out.println("No Path Found!\n");
	}
	    
	}

}
