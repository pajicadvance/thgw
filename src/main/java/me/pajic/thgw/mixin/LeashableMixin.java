package me.pajic.thgw.mixin;

import me.pajic.thgw.access.HappyGhastAccess;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Leashable;
import net.minecraft.world.entity.animal.happyghast.HappyGhast;
import net.minecraft.world.entity.vehicle.boat.AbstractChestBoat;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Leashable.class)
public interface LeashableMixin {

    @Inject(
            method = "setLeashedTo(Lnet/minecraft/world/entity/Entity;Z)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/Leashable;setLeashedTo(Lnet/minecraft/world/entity/Entity;Lnet/minecraft/world/entity/Entity;Z)V"
            )
    )
    private void linkChestBoatToHappyGhast(Entity entity, boolean bl, CallbackInfo ci) {
        Entity entity2 = (Entity) this;
        if (entity2 instanceof AbstractChestBoat boat && entity instanceof HappyGhast ghast) {
            ((HappyGhastAccess) ghast).thgw$setChestBoat(boat);
        } else if (entity2 instanceof HappyGhast ghast && entity instanceof AbstractChestBoat boat) {
            ((HappyGhastAccess) ghast).thgw$setChestBoat(boat);
        }
    }
}
