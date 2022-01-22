package de.funboyy.challenge.utils;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import org.bukkit.entity.EntityType;

public class RandomEntity {

    private static RandomEntity instance;

    public static RandomEntity getInstance() {
        if (instance == null) {
            instance = new RandomEntity();
        }
        return instance;
    }

    private static final List<EntityType> BLOCKED_ENTITIES = Arrays.asList(
            EntityType.LLAMA_SPIT,
            EntityType.MINECART_MOB_SPAWNER,
            EntityType.MINECART_COMMAND,
            EntityType.EVOKER_FANGS,
            EntityType.SHULKER_BULLET,
            EntityType.SPECTRAL_ARROW,
            EntityType.WITHER_SKULL,
            EntityType.SNOWBALL,
            EntityType.ARROW,
            EntityType.LEASH_HITCH,
            EntityType.EGG,
            EntityType.AREA_EFFECT_CLOUD,
            EntityType.PAINTING,
            EntityType.TRIDENT
    );

    private static boolean isEntity(final EntityType type) {
        if (type.name().contains("FIREBALL")) {
            return false;
        }

        if (type.name().contains("ITEM")) {
            return false;
        }

        if (type.name().contains("EXP")) {
            return false;
        }

        if (type.name().contains("ENDER_")) {
            return false;
        }

        return !BLOCKED_ENTITIES.contains(type);
    }

    private static final List<EntityType> ENTITIES = Arrays.stream(EntityType.values()).filter(EntityType::isSpawnable)
            .filter(RandomEntity::isEntity).collect(Collectors.toList());


    private final Random random;

    public RandomEntity() {
        this.random = new Random();
    }

    public EntityType getEntity() {
        return ENTITIES.get(this.random.nextInt(ENTITIES.size()));
    }

}
