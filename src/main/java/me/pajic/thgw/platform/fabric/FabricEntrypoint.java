package me.pajic.thgw.platform.fabric;

//? fabric {

import me.pajic.thgw.THGW;
import dev.kikugie.fletching_table.annotation.fabric.Entrypoint;
import net.fabricmc.api.ModInitializer;

@Entrypoint("main")
public class FabricEntrypoint implements ModInitializer {

	@Override
	public void onInitialize() {
		THGW.onInitialize();
	}
}
//?}
