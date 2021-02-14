package services.xenlan.vouchers.manager;

import org.bukkit.configuration.*;
import org.bukkit.event.*;
import org.mineacademy.fo.*;
import org.mineacademy.fo.menu.model.*;
import org.mineacademy.fo.remain.*;
import services.xenlan.vouchers.files.*;
import services.xenlan.vouchers.objects.*;
import services.xenlan.vouchers.vouchers.*;

import java.util.*;

public class VoucherManager implements Listener {


	private List<Voucher> vouchers;

	public void init() {
		YamlDoc config = xVouchers.config;
		this.vouchers = new ArrayList<>();
		ConfigurationSection section = config.getConfig().getConfigurationSection("vouchers");
		for(String voucherType : section.getKeys(false)) {
			ConfigurationSection voucherItem  = section.getConfigurationSection(voucherType).getConfigurationSection("item");
			CompMaterial material = CompMaterial.fromString(voucherItem.getString("item-material"));
			ItemCreator.ItemCreatorBuilder builder = ItemCreator
					.of(material)
					.glow(voucherItem.contains("enchanted") ? voucherItem.getBoolean("enchanted") : false)
					.hideTags(voucherItem.contains("hide-tags") ? voucherItem.getBoolean("hide-tags") : false);
			if(voucherItem.contains("item-name"))
				builder.name(Common.colorize(voucherItem.getString("item-name")));

			if(voucherItem.contains("lore")) {
				List<String> lores = voucherItem.getStringList("lore");
				lores.forEach(s -> Common.colorize(s));
				builder.clearFlags().clearLores().lores(lores);
			}

			List<String> voucherCommands = section.getConfigurationSection(voucherType).getStringList("commands");
			vouchers.add(new Voucher(voucherType, builder.build().make(), voucherCommands, section.getConfigurationSection(voucherType)));
		}

	}

	public List<Voucher> getVouchers() {
		return vouchers;
	}

}
