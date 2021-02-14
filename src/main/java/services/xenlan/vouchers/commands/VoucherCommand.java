package services.xenlan.vouchers.commands;

import lombok.*;
import org.bukkit.*;
import org.bukkit.command.*;
import org.bukkit.entity.*;
import org.mineacademy.fo.*;
import org.mineacademy.fo.command.*;
import services.xenlan.vouchers.objects.*;
import services.xenlan.vouchers.vouchers.*;

import java.util.*;

public class VoucherCommand extends SimpleCommand {

	public xVouchers main;
	public VoucherCommand(xVouchers main) {
		super("voucher|xvoucher|xv|vouchers|xvouchers");
		this.main = main;
	}

	@SneakyThrows
	@Override
	protected void onCommand() {
		if(args.length == 0) {
			sendUssage(getSender());
			return;
		} else if(args.length == 1) {
			if(args[0].equalsIgnoreCase("reload")) {
				xVouchers.config.reloadConfig();
				main.voucherManager.init();
				getSender().sendMessage(Common.colorize("&a&l[!] Reloaded Plugin."));
			} else if(args[0].equalsIgnoreCase("list")) {
				String voucherList = " ";
				for(Voucher voucher : main.voucherManager.getVouchers()) {
					voucherList+=ChatColor.GRAY + voucher.getVoucherName() + " ";
				}
				getSender().sendMessage(Common.colorize("&e&l[!] Vouchers:" + voucherList));
				return;
			} else {
				sendUssage(getSender());
				return;
			}
		} else if(args.length == 3) {
			if(args[0].equalsIgnoreCase("give")) {
				Player target = Bukkit.getPlayer(args[1]);
				if(target == null) {
					getPlayer().sendMessage(Common.colorize("&c&l[!] That player is offline"));
					return;
				} else {
					List<String> voucherNames = new ArrayList<>();
					for(Voucher voucher : main.voucherManager.getVouchers()) {
						voucherNames.add(voucher.getVoucherName());
					}
					for(String bundleName : voucherNames) {
						if(args[2].equalsIgnoreCase(bundleName)) {
							for(Voucher voucher : main.voucherManager.getVouchers()) {
								if(voucher.getVoucherName().equalsIgnoreCase(args[2])) {
									target.getInventory().addItem(voucher.getVoucherItem());
									getPlayer().sendMessage(Common.colorize("&a&l[!] &aGiven item."));
								}
							}
						}
					}
				}
			} else {
				sendUssage(getSender());
				return;
			}
		} else {
			sendUssage(getSender());
			return;
		}
	}

	public void sendUssage(CommandSender paramCommandSender) {
		paramCommandSender.sendMessage(Common.colorize("&8&m------------------------------------------------------"));
		paramCommandSender.sendMessage(Common.colorize("&6&l[!] &e&lxVouchers Commands:"));
		paramCommandSender.sendMessage(Common.colorize(" &7&l» &7/xv give (player) (name) - Give's a certain player a voucher."));
		paramCommandSender.sendMessage(Common.colorize(" &7&l» &7/xv list - List's all vouchers"));
		paramCommandSender.sendMessage(Common.colorize(" &7&l» &7/xv reload - Reloads the config.yml and messages.yml"));
		paramCommandSender.sendMessage(Common.colorize("&8&m------------------------------------------------------"));
	}

}
