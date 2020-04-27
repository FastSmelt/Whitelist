package gg.filerunner.whitelist.profile;

import gg.filerunner.whitelist.profile.constuctor.PlayerProfile;
import gg.filerunner.whitelist.profile.tokens.PlayerTokens;
import gg.filerunner.whitelist.storage.mongo.MongoRequest;

import lombok.Getter;
import lombok.Setter;

import org.bson.Document;

import java.util.UUID;

@Setter
@Getter
public class WhitelistProfile extends PlayerProfile {

    private final String name;
    private final UUID id;
    private UUID converser;

    public PlayerTokens statistics = new PlayerTokens();

    // TODO: optimize loading and saving
    public WhitelistProfile(String name, UUID id) {
        super(id, "ptokens");
        this.name = name;
        this.id = id;
        load();
    }

    @Override
    public void deserialize(Document document) {
        statistics.setCredits(document.getInteger("credits", 0));
    }

    @Override
    public MongoRequest serialize() {
        MongoRequest request = MongoRequest.newRequest("players", id);
        return MongoRequest.newRequest("players", id)
                .put("name", name)
                .put("lowername", name.toLowerCase())
                .put("credits", statistics.getCredits());
    }
}
