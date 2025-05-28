package me.pajic.thgw.mixin;

import me.pajic.thgw.access.AbstractChestBoatAccess;
import net.minecraft.core.NonNullList;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.vehicle.AbstractBoat;
import net.minecraft.world.entity.vehicle.AbstractChestBoat;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.function.Supplier;

@Mixin(AbstractChestBoat.class)
public abstract class AbstractChestBoatMixin extends AbstractBoat implements AbstractChestBoatAccess {
    @Shadow private NonNullList<ItemStack> itemStacks;

    public AbstractChestBoatMixin(EntityType<? extends AbstractChestBoat> entityType, Level level, Supplier<Item> supplier) {
        super(entityType, level, supplier);
    }

    @Override
    public InteractionResult thgw$dropPrimedTnt(LivingEntity shooter, ItemStack flintAndSteel, InteractionHand hand) {
        if (getFirstPassenger() instanceof Villager) {
            for (int i = itemStacks.size() - 1; i >= 0; i--) {
                ItemStack itemStack = itemStacks.get(i);
                if (itemStack.is(Items.TNT)) {
                    level().addFreshEntity(new PrimedTnt(level(), getX() + 0.5, getY(), getZ() + 0.5, shooter));
                    flintAndSteel.hurtAndBreak(1, shooter, hand);
                    itemStack.shrink(1);
                    return InteractionResult.SUCCESS;
                }
            }
        }
        return InteractionResult.FAIL;
    }
}
