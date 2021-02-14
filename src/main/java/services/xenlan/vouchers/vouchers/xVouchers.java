package services.xenlan.vouchers.vouchers;

import lombok.*;
import org.mineacademy.fo.plugin.*;
import services.xenlan.vouchers.commands.*;
import services.xenlan.vouchers.files.*;
import services.xenlan.vouchers.listeners.*;
import services.xenlan.vouchers.manager.*;

public class xVouchers extends SimplePlugin {

	public static YamlDoc config;
	public VoucherManager voucherManager;

	@SneakyThrows
	@Override
	protected void onPluginStart() {
		config = new YamlDoc(getDataFolder(), "config.yml");
		config.init();
		this.voucherManager = new VoucherManager();
		voucherManager.init();

		registerCommand(new VoucherCommand(this));
		registerEvents(new VoucherInteract(this));
	}





}
