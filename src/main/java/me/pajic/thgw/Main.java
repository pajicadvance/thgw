package me.pajic.thgw;

import net.fabricmc.api.ModInitializer;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageType;

public class Main implements ModInitializer {

    public static final ResourceKey<DamageType> GHAST_TNT = ResourceKey.create(
            Registries.DAMAGE_TYPE,
            ResourceLocation.fromNamespaceAndPath("thgw", "ghast_tnt")
    );

    @Override
    public void onInitialize() {}
}
