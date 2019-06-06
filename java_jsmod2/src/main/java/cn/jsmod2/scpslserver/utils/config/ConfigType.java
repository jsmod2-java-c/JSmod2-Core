package cn.jsmod2.scpslserver.utils.config;

public enum ConfigType {

    JSON(0),

    YAML(1<<1),

    OAML(1<<2),

    PROPERTIES(1<<2+1);

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
