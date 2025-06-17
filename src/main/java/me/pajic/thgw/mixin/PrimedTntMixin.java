package me.pajic.thgw.mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.mojang.serialization.Codec;
import me.pajic.thgw.access.PrimedTntAccess;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(PrimedTnt.class)
public class PrimedTntMixin implements PrimedTntAccess {
    @Unique boolean firedFromGhast = false;

    @Override
    public void thgw$setFiredFromGhast(boolean firedFromGhast) {
        this.firedFromGhast = firedFromGhast;
    }

    @Override
    public boolean thgw$getFiredFromGhast() {
        return firedFromGhast;
    }

    @WrapMethod(method = "readAdditionalSaveData")
    private void readFiredFromGhast(ValueInput valueInput, Operation<Void> original) {
        original.call(valueInput);
        firedFromGhast = valueInput.getBooleanOr("firedFromGhast", false);
    }

    @WrapMethod(method = "addAdditionalSaveData")
    private void addFiredFromGhast(ValueOutput valueOutput, Operation<Void> original) {
        original.call(valueOutput);
        valueOutput.store("firedFromGhast", Codec.BOOL, firedFromGhast);
    }
}
