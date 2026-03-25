package me.pajic.thgw;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageType;

@SuppressWarnings("LoggingSimilarMessage")
public class THGW {

	public static final String MOD_ID = /*$ mod_id*/ "thgw";

	public static final ResourceKey<DamageType> GHAST_TNT = ResourceKey.create(Registries.DAMAGE_TYPE, id("ghast_tnt"));

	public static void onInitialize() {}

	public static Identifier id(String path) {
		return Identifier.fromNamespaceAndPath(MOD_ID, path);
	}
}
