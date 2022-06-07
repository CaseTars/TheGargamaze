package com.mygdx.game;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.elements.Player;
import com.mygdx.game.elements.Wall;

public class Builder {
    private View view;
    private Player pCase;
    private Player pTars;
    private Space space; //n precisa
    
    public void build() throws IOException {
    	Space.size = 31; //receber do arquivo
    	space = new Space();
        view = new View();
        
        try{
        	String[][] assemblyFile = readAssemblyFile();
        	Space.size = assemblyFile.length;
        }
        catch(IOException erro) {  //rever esses erros
        	System.out.println(erro);
        }  
        
        String[][] assemblyFile = new String[Space.size][Space.size];
        //pegar o tamanho do labirinto
        
        for(int x = 0; x<Space.size; x++)
            for(int y = 0; y<Space.size; y++)
                space.getCell(x, y).connect(view.getCell(x, y));
                       
        pCase = new Player(1,1, 'C');
        pCase.connect(space);
        space.insert(pCase);
        
        pTars = new Player(1,2, 'T');
        pTars.connect(space);
        space.insert(pTars);        
        
        //teste
        Wall wall;
        for(int i = 0; i<4; i++) {
            wall = new Wall(2+i,7);
            space.insert(wall);
        }
        //teste
        
        for(int y = assemblyFile.length-1;y >= 0;y--) {
        	for(int x = assemblyFile[y].length-1;x >= 0;x++) {
            	System.out.println(assemblyFile[y][x]);
            }
        }
        
        
        Control control = new Control();
        
        control.conectCase(pCase);
        control.conectTars(pTars);
        
        Gdx.input.setInputProcessor(control);
    }
    
    View getView() {
        return view;
    }
    
    Player getCase() {
        return pCase;
    }
    
    Player getTars() {
        return pTars;
    }
    
    private String[][] readAssemblyFile() throws IOException { //arrumar as variaveis
    	FileReader arq = new FileReader("Maze.txt");
        BufferedReader lerArq = new BufferedReader(arq);
        
        Vector<String[]> v = new Vector<String[]>();
        
        String linha = lerArq.readLine(); // 
           while (linha != null) {
	    	   String ln[]  = linha.split(",");
	           v.add(ln);
	          // for(int i =0 ; i < ln.length;i++) {
	        	//   System.out.printf(ln[i]);
	        	//   System.out.println(" ");
	           //}

             linha = lerArq.readLine(); 
           }        	
        arq.close();
        return (String[][])v.toArray(new String[v.size()][]);  //rever o retorno
    }
}
