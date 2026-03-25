package me.pajic.thgw.mixin;

import me.pajic.thgw.access.AbstractChestBoatAccess;
import me.pajic.thgw.access.PrimedTntAccess;
import net.minecraft.core.NonNullList;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.entity.npc.villager.Villager;
import net.minecraft.world.entity.vehicle.boat.AbstractBoat;
import net.minecraft.world.entity.vehicle.boat.AbstractChestBoat;
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

    @SuppressWarnings("resource")
	@Override
    public InteractionResult thgw$dropPrimedTnt(LivingEntity shooter, ItemStack flintAndSteel, InteractionHand hand) {
        if (getFirstPassenger() instanceof Villager) {
            for (int i = itemStacks.size() - 1; i >= 0; i--) {
                ItemStack itemStack = itemStacks.get(i);
                if (itemStack.is(Items.TNT)) {
                    PrimedTnt tnt = new PrimedTnt(level(), getX() + 0.5, getY(), getZ() + 0.5, shooter);
                    ((PrimedTntAccess) tnt).thgw$setFiredFromGhast(true);
                    level().addFreshEntity(tnt);
                    flintAndSteel.hurtAndBreak(1, shooter, hand);
                    itemStack.shrink(1);
                    return InteractionResult.SUCCESS;
                }
            }
        }
        return InteractionResult.FAIL;
    }
}
