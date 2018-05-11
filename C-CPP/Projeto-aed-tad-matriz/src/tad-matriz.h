#define MAX 100

// TAD Matriz

typedef int TipoChave; // tipo definido para a chave
struct TipoPosicaoMatriz{
	  int linha;           // linha atual (inserção)
	  int coluna;          // coluna atual (inserção)
};
typedef struct TipoPosicaoMatriz Posicao;
struct TipoMatriz{     // estrutura para tipo matriz
  int tamanho;         // tamanho da matriz (num. de elementos)
  Posicao pos;         // posição atual (inserção)
  TipoChave **a;       // vetor bidimensional
  int capacidade;      // capacidade (max num)
  int dimensao;        // dimensao matrix dXd
};
// tipo definido para matriz
typedef struct TipoMatriz Matriz;

void InicializaMatriz(Matriz * m);
void InicializaMatriz2(Matriz * m, int capacidade);
int TamanhoMatriz(Matriz m);
int TamanhoLinhas(Matriz m);
void InsereMatriz(TipoChave chave, Matriz * m);
Posicao PosicaoDeMatriz(TipoChave chave, Matriz m);
int ElementoEmMatriz(Posicao pos, Matriz m);
void ImprimeMatriz(Matriz m);
int CheioMatriz(Matriz m);

void ImprimeInfoMatriz(Matriz m){
	printf("TAD Matriz\n");
	printf("======================================\n");
	printf("Dimensão: %d x %d\n", m.dimensao, m.dimensao);
	printf("Tamanho/Capacidade: %d/%d\n", m.tamanho, m.capacidade);
	printf("Linha/Coluna Atual: %d x %d\n", m.pos.linha, m.pos.coluna);
}

void InicializaMatriz2(Matriz * m, int dimensao){
	m->tamanho = 0;
	m->pos.linha = 0;
	m->pos.coluna = 0;
	m->capacidade = dimensao * dimensao;
	m->dimensao = dimensao;

	m->a = (TipoChave**) malloc(dimensao * sizeof(TipoChave*));
	//     Tipo de dados         quantidade    tamanho em bytes
	int i = 0, j=0;

	for (i = 0; i < dimensao; i++){
		//printf("line %d\n", i);
		m->a[i] = (TipoChave*) malloc(dimensao*sizeof(TipoChave));
		// para cada linha
		//        tipo de dados        quantidade     tamanho em bytes
		for (j = 0; j < dimensao; j++){
			//printf("column %d\n", j);
			m->a[i][j]=0;
		}
	}
	ImprimeInfoMatriz((*m));
}

void InicializaMatriz(Matriz * m)
{
	int cap = MAX;
	InicializaMatriz2(m, cap);
}

int TamanhoMatriz(Matriz m){
	return m.tamanho;
}

int TamanhoLinhas(Matriz m){
	return m.pos.linha;
}

void InsereMatriz(TipoChave chave, Matriz *m){
	if (m->tamanho == m->capacidade){
		printf("\nERRO: Matriz cheia!\n");
		return;
	}
	int linha = m->pos.linha;
	int coluna = m->pos.coluna;
	m->a[linha][coluna] = chave;
	m->pos.coluna++;
	m->tamanho++;
	if (m->pos.coluna == m->dimensao){
		m->pos.linha++;
		m->pos.coluna = 0;
	}
}


void ImprimeMatriz(Matriz m){
	ImprimeInfoMatriz(m);
	int linhas = m.dimensao;
	int colunas = m.dimensao;
	int i, j;
	for (i = 0; i < linhas; i++){
		//printf("Linha %d: ", i);
		for (j = 0; j < colunas; j++){
			printf("%5d", m.a[i][j]);
		} printf("\n");
	}
}

Posicao PosicaoDeMatriz(TipoChave chave, Matriz m){
	Posicao pos;
	pos.linha = -1;
	pos.coluna = -1;
	int linhas = m.dimensao;
	int colunas = m.dimensao;
	int i, j;
	for (i = 0; i < linhas; i++){
		for (j = 0; j < colunas; j++){
			TipoChave chave2 = m.a[i][j];
			if (chave == chave2){
				pos.linha = i;
				pos.coluna = j;
			}
		}
	}
	return pos;
}

int ElementoEmMatriz(Posicao pos, Matriz m){
	if (pos.linha >= m.dimensao || pos.coluna >= m.dimensao){
		printf("Erro: Posição inexistente!\n");
		return -1;
	}
	return m.a[pos.linha][pos.coluna];
}

int CheioMatriz(Matriz m){
	return m.tamanho - m.capacidade;
}

void FreeMatriz(Matriz *m){
	int tam = m->dimensao;
	int i = 0;
	for (i = 0; i < tam; i++){
		free(m->a[i]);
	}
	free(m->a);
}

