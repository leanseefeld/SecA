package br.furb.seca.model;

/**
 * Representa uma entidade que pode ser parcialmente carregada, sendo os demais dados carregados
 * apenas quando necess�rios.
 * 
 * @author Leander Seefeld
 */
public interface LazyLoadable {

    /**
     * Indica se a entidade possui todos os campos carregados em mem�ria.
     * 
     * @return {@code true} se a entidade possui todos os campos carregados em mem�ria.
     *         {@code false} se pelo menos um campo n�o foi carregado ainda.
     */
    boolean isLoaded();

    /**
     * Define se a entidade possui todos os campos carregados em mem�ria.
     * 
     * @param loaded
     *            {@code true} se a entidade possui todos os campos carregados em mem�ria.
     *            {@code false} se pelo menos um campo n�o foi carregado ainda. {@code true}
     */
    void setLoaded(boolean loaded);

}
