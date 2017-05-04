package br.univel.minhaArvove;

import java.util.ArrayList;
import java.util.List;

public class UniNodeImpl<T> implements UniNode<T> {

	private UniNode<T> pai;
	private List<UniNode<T>> filhos;
	private T conteudo;
	
	public UniNodeImpl(T t) {
		this.conteudo = t;
	}
	
	public T getConteudo() {
		return this.conteudo;
	}
	
	public UniNode<T> getPai() {
		return pai;
	}

	public List<UniNode<T>> getFilhos() {
		return filhos;
	}

	public void addFilho(UniNode<T> node) {
		if (this.filhos == null) {
			this.filhos = new ArrayList<>();
		}
		this.filhos.add(node);
		
	}

	public boolean isLeaf() {
		return this.filhos == null;
	}
}