package dk.sdu.cbse.common.data;

import dk.sdu.cbse.common.services.IEntityProcessingService;
import dk.sdu.cbse.common.services.IPostEntityProcessingService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class World {

    private final Map<String, Entity> entityMap = new ConcurrentHashMap<>();

    // Lists to hold processors
    private final List<IEntityProcessingService> entityProcessors = new ArrayList<>();
    private final List<IPostEntityProcessingService> postEntityProcessors = new ArrayList<>();

    public String addEntity(Entity entity) {
        entityMap.put(entity.getID(), entity);
        return entity.getID();
    }

    public void removeEntity(String entityID) {
        entityMap.remove(entityID);
    }

    public void removeEntity(Entity entity) {
        entityMap.remove(entity.getID());
    }

    public Collection<Entity> getEntities() {
        return entityMap.values();
    }

    /**
     * Returnerer kun de Entities, hvis klasse nøjagtigt matcher en af de givne typer.
     */
    public <E extends Entity> List<E> getEntities(Class<E>... entityTypes) {
        List<E> result = new ArrayList<>();
        for (Entity e : getEntities()) {
            for (Class<E> type : entityTypes) {
                if (type.equals(e.getClass())) {
                    @SuppressWarnings("unchecked")
                    E casted = (E) e;
                    result.add(casted);
                }
            }
        }
        return result;
    }

    public Entity getEntity(String ID) {
        return entityMap.get(ID);
    }

    /**
     * Registrer en IEntityProcessingService, der kaldes hver frame.
     */
    public void addEntityProcessor(IEntityProcessingService proc) {
        entityProcessors.add(proc);
    }

    /**
     * Registrer en IPostEntityProcessingService, der kaldes hver frame efter entityProcessors.
     */
    public void addPostEntityProcessor(IPostEntityProcessingService postProc) {
        postEntityProcessors.add(postProc);
    }

    public List<IEntityProcessingService> getEntityProcessors() {
        return entityProcessors;
    }

    public List<IPostEntityProcessingService> getPostEntityProcessors() {
        return postEntityProcessors;
    }
}
