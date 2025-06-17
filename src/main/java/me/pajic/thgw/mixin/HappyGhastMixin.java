package me.pajic.thgw.mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import me.pajic.thgw.access.HappyGhastAccess;
import net.minecraft.core.UUIDUtil;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.HappyGhast;
import net.minecraft.world.entity.vehicle.AbstractChestBoat;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

import java.util.UUID;

@Mixin(HappyGhast.class)
public abstract class HappyGhastMixin extends Animal implements HappyGhastAccess {
    @Unique @Nullable UUID chestBoatId;

    protected HappyGhastMixin(EntityType<? extends Animal> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    @Nullable public AbstractChestBoat thgw$getChestBoat() {
        return chestBoatId == null ? null : (AbstractChestBoat) level().getEntity(chestBoatId);
    }

    @Override
    public void thgw$setChestBoat(AbstractChestBoat boat) {
        chestBoatId = boat.getUUID();
    }

    @WrapMethod(method = "readAdditionalSaveData")
    private void readChestBoatId(ValueInput valueInput, Operation<Void> original) {
        original.call(valueInput);
        chestBoatId = valueInput.read("ChestBoatId", UUIDUtil.CODEC).orElse(null);
    }

    @WrapMethod(method = "addAdditionalSaveData")
    private void addChestBoatId(ValueOutput valueOutput, Operation<Void> original) {
        original.call(valueOutput);
        valueOutput.storeNullable("ChestBoatId", UUIDUtil.CODEC, chestBoatId);
    }
}
