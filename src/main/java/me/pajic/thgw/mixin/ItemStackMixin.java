package me.pajic.thgw.mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import me.pajic.thgw.access.AbstractChestBoatAccess;
import me.pajic.thgw.access.HappyGhastAccess;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.happyghast.HappyGhast;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.vehicle.boat.AbstractChestBoat;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin {

    @WrapMethod(method = "interactLivingEntity")
    private InteractionResult flintAndSteelInteractHappyGhast(Player player, LivingEntity livingEntity, InteractionHand interactionHand, Operation<InteractionResult> original) {
        ItemStack itemStack = (ItemStack) (Object) this;
        if (itemStack.is(Items.FLINT_AND_STEEL) && livingEntity instanceof HappyGhast ghast && !ghast.isBaby()) {
            AbstractChestBoat boat = ((HappyGhastAccess) ghast).thgw$getChestBoat();
            if (boat != null) {
                return ((AbstractChestBoatAccess) boat).thgw$dropPrimedTnt(player, itemStack, interactionHand);
            }
        }
        return original.call(player, livingEntity, interactionHand);
    }

    @WrapMethod(method = "use")
    private InteractionResult flintAndSteelUseWhileRidingGhast(Level level, Player player, InteractionHand interactionHand, Operation<InteractionResult> original) {
        ItemStack itemStack = (ItemStack) (Object) this;
        if (itemStack.is(Items.FLINT_AND_STEEL) && player.getVehicle() instanceof HappyGhast ghast) {
            AbstractChestBoat boat = ((HappyGhastAccess) ghast).thgw$getChestBoat();
            if (boat != null) {
                return ((AbstractChestBoatAccess) boat).thgw$dropPrimedTnt(player, itemStack, interactionHand);
            }
        }
        return original.call(level, player, interactionHand);
    }
}
