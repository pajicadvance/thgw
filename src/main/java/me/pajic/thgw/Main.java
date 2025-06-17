package me.pajic.thgw;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageType;
import net.neoforged.fml.common.Mod;

@Mod("thgw")
public class Main {
    public static final ResourceKey<DamageType> GHAST_TNT = ResourceKey.create(
            Registries.DAMAGE_TYPE,
            ResourceLocation.fromNamespaceAndPath("thgw", "ghast_tnt")
    );
}