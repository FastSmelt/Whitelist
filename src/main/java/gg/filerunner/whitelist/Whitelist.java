package gg.filerunner.whitelist;

import gg.filerunner.whitelist.modules.ProfileModule;
import gg.filerunner.whitelist.profile.manager.ProfileManager;
import gg.filerunner.whitelist.storage.mongo.MongoStorage;

import lombok.Getter;

import lombok.Setter;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
@Setter
public class Whitelist extends JavaPlugin {

    @Getter private static Whitelist instance;
    private MongoStorage mongoStorage;
    private ProfileManager profileManager;
    private ProfileModule profileModule;

    @Override
    public void onEnable() {
        instance = this;

        this.mongoStorage = new MongoStorage();

        loadManagers();
    }

    @Override
    public void onDisable() {
        profileManager.saveAllProfiles();
    }

    void loadManagers() {
        this.profileManager = new ProfileManager(this);

        this.profileModule = new ProfileModule();
    }
}
