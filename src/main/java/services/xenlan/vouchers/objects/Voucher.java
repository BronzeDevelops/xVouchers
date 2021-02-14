package services.xenlan.vouchers.objects;

import org.bukkit.configuration.*;
import org.bukkit.inventory.*;

import java.util.*;

public class Voucher {

	private String voucherName;


	private ItemStack voucherItem;
	private List<String> voucherCommands;


	private ConfigurationSection voucherSection;

	public Voucher(String name, ItemStack item, List<String> commands, ConfigurationSection voucherSection) {
		this.voucherName = name;
		this.voucherItem = item;
		this.voucherCommands = commands;
		this.voucherSection = voucherSection;
	}

	public String getVoucherName() {
		return voucherName;
	}

	public ItemStack getVoucherItem() {
		return voucherItem;
	}

	public List<String> getVoucherCommands() {
		return voucherCommands;
	}

	public ConfigurationSection getVoucherSection() {
		return voucherSection;
	}

}
