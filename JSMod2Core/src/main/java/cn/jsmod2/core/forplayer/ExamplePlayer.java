package cn.jsmod2.core.forplayer;

public class ExamplePlayer {

    private int steamId;

    private int makeId;

    private String skill;

    private PlayerEntity entity;

    public ExamplePlayer(int steamId, int makeId, String skill, PlayerEntity entity) {
        this.steamId = steamId;
        this.makeId = makeId;
        this.skill = skill;
        this.entity = entity;
    }

    public void setSkill(String skill){
        entity.send(new JsonRequester().add("skill",skill).parse());
    }
}
