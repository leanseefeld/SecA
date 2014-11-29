package br.furb.seca.model;

/**
 * Representa uma entidade que pode ser parcialmente carregada, sendo os demais dados carregados
 * apenas quando necessários.
 * 
 * @author Leander Seefeld
 */
public interface LazyLoadable {

    /**
     * Indica se a entidade possui todos os campos carregados em memória.
     * 
     * @return {@code true} se a entidade possui todos os campos carregados em memória.
     *         {@code false} se pelo menos um campo não foi carregado ainda.
     */
    boolean isLoaded();

    /**
     * Define se a entidade possui todos os campos carregados em memória.
     * 
     * @param loaded
     *            {@code true} se a entidade possui todos os campos carregados em memória.
     *            {@code false} se pelo menos um campo não foi carregado ainda. {@code true}
     */
    void setLoaded(boolean loaded);

}
