# Projeto `The Gargamaze`

* Projeto Final de POO

# Descrição Resumida do Projeto/Jogo

> O propósito do jogo é a captura dos dois cristais principais para que consigam ganhar. Contudo, para isso, os jogadores precisam de habilidades que são adquiridas através da captura de outros cristais que ficam localizados no cantos do mapa, mas para isso precisam vencer desafios em um labirinto com pouca visibilidade e correr contra o seu pior inimigo: O tempo de duração de suas baterias. Além disso, no centro do mapa se encontra um buraco negro que distorce a passagem do tempo aos seus arredores, por conta disso, quanto mais longe do buraco negro os jogadores se encontram mais rapido suas baterias são descarregadas, e quando os jogadores entram no buraco negro, suas baterias são recarregadas. 

# Equipe
* `Thiago Máximo Pavão `           - `247381`
* `Kevin Caio Marques dos Santos ` - `247218`

# Arquivo Executável do Jogo
>[Download](https://drive.google.com/file/d/138DYyCo_OWfGWI8gYvdxZ3F5L54VvRnI/view?usp=sharing)

# Slides do Projeto

## Slides da Prévia
> [Prévia](https://docs.google.com/presentation/d/1mAtWsCDGBxF7lj8pcSTOYw43ReE8C6TG7_TtIIiZnzM/edit?usp=sharing)

## Slides da Apresentação Final

> [Apresentação Final](https://docs.google.com/presentation/d/1Nq9iXtoOGGFCMYAOnZVzsHJ7h0_kNV516n9Oh6eqHng/edit?usp=sharing)

# Relatório de evolução

Inicialmente o projeto começou com uma estrutura base, apenas dois jogadores recebendo comandos do teclado e pedindo para o espaço movimentá-los pelas células, e nesse ponto já existia uma interface gráfica.

![Figura da primeira versão funcional do jogo](imgs/first.png)

A partir disso acrescentamos a leitura de um arquivo .txt para a criação do labirinto em si. Nesse ponto surgiram algumas dúvidas e decisões importantes precisaram ser tomadas, como a de como representaríamos os elementos no arquivo, e além disso, tínhamos a intenção de que algumas partes do labirinto, como o centro e as paredes laterais, fossem sempre iluminadas. Optamos então por ter dois mapas em um mesmo arquivo .txt, um com os elementos e outro que mostrava somente quais teclas sempre seriam iluminadas. Nesse momento decidimos também como representar os botões e os seus respectivos portões que abrem, e foi decidido que, como não seriam muitos, essa informação seria passada através de coordenadas. Na hora dessa implementação algumas decisões erradas foram tomadas de modo que seria necessário fazer um downcasting, por isso tivemos que fazer algumas alterações na lógica de leitura.

Com a implementação de muitos elementos no jogo que tinham que se comunicar com outros, vimos a necessidade de acrescentar mais interfaces específicas de modo que seria possível essa comunicação mas também ela seria controlada e organizada.

Uma dificuldade enfrentada foi na implementação da interface gráfica, uma vez que teríamos uma quantidade razoável de texturas possíveis que deveriam ser mostradas em uma única célula. Uma solução encontrada foi a criação de uma matriz de células do view, cujas células comunicam-se com as células nas quais os elementos se encontram e com isso descobrem quais texturas devem ser exibidas no momento. E para minimizar a quantidade de texturas carregadas, elas foram carregadas somente uma vez como atributos estáticos de modo que todas as células do view tinham acesso a ela.

# Destaques de Código

Um dos destaques do nosso código seria a implementação da lanterna, usada para iluminar o mapa. Ela foi implementada para promover a iluminação em lugares específicos que queríamos que fossem iluminados, como as bordas, o centro, os cristais e os jogadores, enquanto que o resto do mapa fica escuro.

```Java
private void buildMaze() throws AssembleError {
    ...
    // Criação das lanternas dos cristais
    Lantern lantern = new Lantern();
    lantern.connect(crystal);
    lantern.connect(space);
    crystal.connect(lantern);
    space.addLantern(lantern);
    ...
}
```

Codigo criação do cristal do player

Codigo Array de Ilantern do space

```Java
public class Space implements ISpace {
    
	private Array<ILantern> lanterns = new Array<ILantern>();
    ...
}
```

Codigo Iluminacao da lanterna

```Java
public class Lantern implements ILantern {
    private ISpaceIluminate space;
    private IPosition element;
    ...
    public void iluminate() {
    	...
    	for(int dx = -radius;dx <= radius;dx++) {
    		for(int dy = -radius;dy <= radius;dy++) {
    		...
    		space.iluminate(getX() + dx, getY() + dy, clarity);
    		}
    	}
    }
    ...
}
```

Outro destaque é a forma como os as imagens são dispostas na tela, com o uso da sobreposição de imagens para evitar a criação de imagens para casos muito específicos. Por exemplo, em um caso em que um dos jogadores entra na frente dos botões, em uma mesma renderização é desenhado primeiro o botão e depois o jogador. E isso é feito com o uso de um array de texturas de uma mesma célula, um array com todas as texturas em ordem de prioridade é enviada para o view, responsável por desenhá-las na tela.

Codigo de texturas 1

```Java
public void update() {

    int nbElements = cell.nElements();
    
    textures.clear();
    textures.add(imgGround);
    
    if(!cell.visible()) }
    	textures.add(imgDark);
    ...
    else {
		for(int i=0;i < nbElements;i++){
			if(cell.visual(i).type() == 'B') {
				if(cell.visual(i).variation() == 'C') {
					if(cell.visual(i).state() == 'p') {
						textures.add(imgButtonPressedFrame);
						textures.add(imgBlueBG);
						textures.add(imgButtonPressed);
					}
				}
			...
			}
			else if(cell.visual(i).type() == 'P') {
				...
			}
		}    
    }
    ...
}
```

Codigo de texturas 2

``` Java
private void drawMap() {
	batch.begin();
	
	for(int x=0;x < size;x++){
			for(int y=0;y < size;y++){
				batch.setColor(1f,1f,1f,1f);
				ViewCell aux = cells[x][y];
				
				for(Texture texture: aux.getTexture()) {
					batch.draw(texture, aux.getX(), aux,getY(), ViewCell.size, ViewCell.size);
				}
				...
			}
	}
	batch.end();
}
```

Outro destaque é o controle do tempo de vida dos jogadores dependendo da posição em que este se encontra no mapa, quanto mais longe do buraco negro mais rápido o tempo passa. Essa implementação se alinha com a ideia do jogo, o buraco negro que se encontra no centro é o responsável por controlar essa variação do tempo.

Codigo do tempo buraco negro

```Java
public class Blackhole implements IUpdate, ITime, IVisualBH {
	...
	private IPlayerBH pCase, pTars;
	...	
	public void update() {
	   posCase = pCase.getIPosition();
	   posTars = pTars.getIPosition();
	   boolean caseIn = inside(posCase);
	   boolean tarsIn = inside(posTars);
	    
	   if(caseIn)
	       incCase = 20000;
	   else
	       incCase = - (float) distanceFactor(posCase);
	    
	   if(tarsIn)
	       incTars = 20000;
	   else
	       incTars = - (float) distanceFactor(posTars);
	   ...
	}
	...
	public void update(float t) {
        pCase.updateTimeRemaining(t * incCase);
        pTars.updateTimeRemaining(t * incTars);
    }	

```

Outro destaque é que o mapa é criado com base em um arquivo .txt. Por isso, o mapa pode ser criado em vários tamanhos e com diversas variações de puzzles e desafios, bastando somente alterar o .txt.

Código do Builder

```Java
public class Builder {
	private void readFile() throws IOException {
		FileHandle handle = Gdx.files.internal(mazePath);
		...
		readMazeMatrix(lines);
		...
		readVisibilityMatrix(lines);
		...
		readButtons(lines);
	}
}
```


# Destaques de Orientação a Objetos

# Destaques de Pattern

# Conclusão e trabalhos futuros

# Documentação dos componentes

# Diagramas


