package me.pajic.thgw.access;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

public interface AbstractChestBoatAccess {
    InteractionResult thgw$dropPrimedTnt(LivingEntity shooter, ItemStack flintAndSteel, InteractionHand hand);
}
