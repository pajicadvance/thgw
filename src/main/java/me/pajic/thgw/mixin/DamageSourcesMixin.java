package me.pajic.thgw.mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import me.pajic.thgw.Main;
import me.pajic.thgw.access.PrimedTntAccess;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageSources;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(DamageSources.class)
public class DamageSourcesMixin {

    @WrapMethod(method = "explosion(Lnet/minecraft/world/entity/Entity;Lnet/minecraft/world/entity/Entity;)Lnet/minecraft/world/damagesource/DamageSource;")
    private DamageSource checkGhastTnt(Entity entity, Entity entity2, Operation<DamageSource> original) {
        if (entity2 instanceof Player && entity instanceof PrimedTnt && ((PrimedTntAccess) entity).thgw$getFiredFromGhast()) {
            return new DamageSource(entity2.level().registryAccess().getOrThrow(Main.GHAST_TNT), entity, entity2);
        }
        return original.call(entity, entity2);
    }
}
