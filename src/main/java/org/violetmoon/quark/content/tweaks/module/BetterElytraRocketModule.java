package org.violetmoon.quark.content.tweaks.module;

import org.violetmoon.zeta.event.bus.PlayEvent;
import org.violetmoon.zeta.event.play.entity.player.ZRightClickItem;
import org.violetmoon.zeta.module.ZetaLoadModule;
import org.violetmoon.zeta.module.ZetaModule;

import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.FireworkRocketEntity;
import net.minecraft.world.item.FireworkRocketItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

@ZetaLoadModule(category = "tweaks")
public class BetterElytraRocketModule extends ZetaModule {

	@PlayEvent
	public void onUseRocket(ZRightClickItem event) {
		Player player = event.getEntity();
		ItemStack chest = player.getItemBySlot(EquipmentSlot.CHEST);
		if(!player.isFallFlying() && zeta.itemExtensions.get(chest).canElytraFlyZeta(chest, player)) {
			Level world = player.getCommandSenderWorld();
			ItemStack itemstack = event.getItemStack();

			if(itemstack.getItem() instanceof FireworkRocketItem) {
				if(!world.isClientSide) {
					world.addFreshEntity(new FireworkRocketEntity(world, itemstack, player));
					if(!player.getAbilities().instabuild)
						itemstack.shrink(1);
				}
				
				player.startFallFlying();
				player.jumpFromGround();

				event.setCanceled(true);
				event.setCancellationResult(world.isClientSide ? InteractionResult.SUCCESS : InteractionResult.CONSUME);
			}

		}

	}

}
