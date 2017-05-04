package br.univel.minhaArvove;

public class UniArvoreImpl<T> implements UniArvore<T> {

	class UniNode {
		
	}

	private T raiz;

	public UniArvoreImpl(T noRaiz) {
		this.raiz = noRaiz;
	}

//	public void mostrarTodosConsole() {
//		
//	}
	
	public T getRaiz() {
		return raiz;
	}
	
}