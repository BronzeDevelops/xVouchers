package services.xenlan.vouchers.listeners;

import org.bukkit.*;
import org.bukkit.event.*;
import org.bukkit.event.player.*;
import org.bukkit.inventory.*;
import org.mineacademy.fo.*;
import services.xenlan.vouchers.objects.*;
import services.xenlan.vouchers.vouchers.*;

public class VoucherInteract implements Listener {

	public xVouchers main;

	public VoucherInteract(xVouchers main) {
		this.main = main;
	}

	@EventHandler
	public void interact(PlayerInteractEvent e) {
		if(e.getItem()!=null&&e.getItem().getType()!=Material.AIR) {
			ItemStack i = e.getItem();
			if(i.getItemMeta().hasDisplayName()&&i.getItemMeta().hasLore()) {
				for (Voucher v : main.voucherManager.getVouchers()) {
					if (v.getVoucherItem().getType() == i.getType()&&v.getVoucherItem().getItemMeta().getDisplayName().equals(i.getItemMeta().getDisplayName())&v.getVoucherItem().getItemMeta().getLore().equals(i.getItemMeta().getLore())) {
						e.setCancelled(true);
						if(i.getAmount()==1) {
							e.getPlayer().setItemInHand(new ItemStack(Material.AIR));
						} else {
							e.getPlayer().getItemInHand().setAmount(i.getAmount() - 1);
						}
						for(String s : v.getVoucherCommands())
							Bukkit.dispatchCommand(Bukkit.getConsoleSender(), s.replaceAll("%player%", e.getPlayer().getName()));
						e.getPlayer().updateInventory();
						if(v.getVoucherSection().getBoolean("redeem-message"))
							Bukkit.broadcastMessage(Common.colorize(v.getVoucherSection().getString("redeem-msg")).replaceAll("%player%", e.getPlayer().getName()));
					}
				}
			}
		}
	}
}
