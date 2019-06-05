package net.noyark.scpslserver.jsmod2.utils.config;

public enum ConfigType {

    JSON(0),

    YAML(1),

    OAML(2),

    PROPERTIES(3);

    private int type;

    ConfigType(int type){
        this.type = type;
    }

   private int getType(){
        return type;
   }

    public void setType(int type) {
        this.type = type;
    }
}
