package me.kuak.rm.server.web.rs.config;

import java.util.List;

/**
 *
 * @author Juan Luis Cano <garfenter at adstter.com>
 * @param <T>
 */
public interface BaseConfigEndpoint<T> {

    /**
     * persists entity
     *
     * @param t entity to be persisted
     */
    void create(T t);

    /**
     * Returns a list of entities of type T
     *
     * @param position start position used for pagination
     * @param limit max number of results
     * @return
     */
    List<T> find(Integer position, Integer limit);

    /**
     * returns an Entity by its Id
     * @param id
     * @return
     */
    T findById(Integer id);

    /**
     * Updates the entity T
     *
     * @param id
     * @param t
     */
    void update(Integer id, T t);

    /**
     * Deletes the entity T
     *
     * @param id
     */
    void delete(Integer id);

}
